<%@page import="mx.gob.segob.nsjp.comun.enums.documento.Periodicidad"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida"%>
<%@page import="java.util.Calendar"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"  />	
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"  />
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" >
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" >
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css" >
   	<link rel="stylesheet" type="text/css" href="/nsjp-web/resources/css/jquery.timeentry.css"/>
    
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>	
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script src="<%= request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
	<script src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
	<script src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
	<script type="text/javascript" src="/nsjp-web/resources/js/jquery.timeentry.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Medidas Alternas</title>
	
	<script type="text/javascript">	
		//variables globales
		
	var medidaAlternaId = <%=request.getParameter("rowid")%>;
	var estatus = 0;
	
	/*
	*Variables Generales
	*/
	var medidaId = <%=request.getParameter("rowid")%>;
	var documentoId;
	var numUnicoExp;

	
	//mes que se muestra en la caja de texto
	var mesActual;
	//anio que se muestra en la caja de texto
	var anioActual;
	//Arreglo usado como carrusel para obtener los meses
	var meses =["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
	//bandera de cambio para recorrer el carrusel de meses
	var banderaCambio=0;
	//primera consulta muestra los grids de salas
	var primeraConsulta= "true";
	var fechaReal="";
	//hora seleccionada en el grid
	var horaSeleccionada="";
	//Minuto seleccionado en el grid
	var minutoSeleccionado="";	

	/*
	*Variables para la ceja Documentos
	*/
	var primeraVezGridDocumentosDigitales = true;

	/*
	*Variables para la ceja Actuaciones
	*/
	var idWindowGenerarDictamenInforme=1;
	var idWindowSolicitudDeEvidencia=1;
		
		
	$(document).ready(function() {
		estatus= '<%=request.getParameter("estatus")%>';
		mesActual='<%=(java.util.Calendar.getInstance().get(Calendar.MONTH))%>';
		anioActual='<%=(java.util.Calendar.getInstance().get(Calendar.YEAR))%>';
		$("#tabsprincipalconsulta").tabs({disabled: [1]});

		consultarCatalogoPeriodicidad();
		consultarMedidaAlternaPorId(medidaAlternaId);

		/*$("#divSemana").hide();
		$("#divMes").hide();*/
		
	$("#tabsprincipalconsulta").tabs();
				
	/*$("#fechaInicioSSP").datepicker({
		dateFormat: 'dd/mm/yy',
		yearRange: '1900:2100',
		changeMonth: true,
		changeYear: true
		
	});

	$('#horaInicioSSP').timepicker({
           onSelect: function(time, inst) {
               $('#floating_selected_time').html('You selected ' + time);
           }
       });*/

		var mesSeleccionado = meses[mesActual];		

		//escrbimos en pantalla la fecha actual
		$('#mes').val(mesSeleccionado);
		$('#anio').val(anioActual);

		/**
		*Funcion que recarga el grid con la funcionalidad de la agenda
		*/
		jQuery("#gridAgendaPJENA").jqGrid({ 
			url:'<%=request.getContextPath()%>/calendarioActual.do', 
			datatype: "xml", 
			colNames:['Lu','Ma','Mi','Ju','Vi','Sa','Do'], 
			colModel:[  {name:'lunes',index:'lunes', width:40, align:'center'},						
			           	{name:'martes',index:'martes', width:40, align:'center'}, 
			           	{name:'miercoles',index:'miercoles', width:40,align:'center'}, 
			           	{name:'jueves',index:'jueves', width:40, align:'center'}, 
			           	{name:'viernes',index:'viernes', width:40, align:'center'}, 
			           	{name:'sabado',index:'sabado', width:40, align:'center'}, 
			           	{name:'domingo',index:'domingo', width:40, align:'center'}, 
										
					],
			rowNum:10,
			//toolbar: [true,"top"],
			rowList:[10,20,30],
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "asc",
			cellEdit: true,
			scrollOffset:0,
			onSelectCell: function(idRow,dia,fecha) {
				seleccionCelda(idRow,dia,fecha);
				deshabilitarHabilitarComponentes("seleccionFecha");
					},	
			loadComplete: function(){
				//Cambia la bandera para avanzar de mes
				banderaCambio=0;
			} 
		});
		gridSeguimiento();

		
		/*
		*LLamadas para la ceja documentos
		*/
		cargaGridDocumentosDigitales();
		
		//Oculta inicialmente los elementos
		ocultaActuaciones();

	});
			 
			 
			 function gridSeguimiento() {
				jQuery("#gridSeguimiento").jqGrid({
					url : '<%= request.getContextPath()%>/consultarCalendarizacionPorMedidaIdReducido.do?medidaCautelarId='+medidaAlternaId+'', 
					datatype: "xml", 
					//async: false,
					colNames:['Cumplimiento', 'Fecha', 'Hora'], 
					colModel:[ 	
								{name:'Cumplimiento',index:'Cumplimiento', sortable:false}, 
					           	{name:'Fecha', index:'Fecha', sortable:false},
					           	{name:'Hora', index:'Hora', sortable:false},																				
							],
					pager: jQuery('#paginadorSeguimiento'),
					rowNum:10,
					rowList:[10,20,30],
					//autowidth: true,
					height:200,
					sortname: 'Cumplimiento',
					viewrecords: true,
					sortorder: "desc",
					gridComplete: 
						function(){ 
							//alert("habilitaCumplimiento()");
							var now = new Date();
							var monthnumber = now.getMonth();
							monthnumber = monthnumber + 1;
							monthnumber = monthnumber < 10 ? "0" + monthnumber : monthnumber;
							var monthday    = now.getDate();
							monthday = monthday < 10 ? "0" + monthday : monthday;
							var year = now.getYear();
							if(year < 2000) { year = year + 1900; }									
							var strFecha = monthday + "/" + monthnumber + "/" + year;
								
							var ids = jQuery("#gridSeguimiento").jqGrid('getDataIDs');
							//alert(ids.length);
							if(ids.length > 0){
								$('#btnCalendarizar').hide();
								$("#btnCancelarSeguimiento").show();
								$("#tabsprincipalconsulta").tabs('enable', 1);
							} 
										
							for(var i=0;i < ids.length;i++){ 
								var cl = ids[i];
								if(strFecha == jQuery('#gridSeguimiento').getRowData(cl)["Fecha"] && jQuery('#gridSeguimiento').getRowData(cl)["Hora"] == ""){ 
									btnReg = "<input style='height:25px;width:70px;' type='button' value='Registrar' id='btnRegistrar_" + cl + "' onclick=\"actualizarCumplimiento(this, '"+cl+"');\" />";
								}else{
									btnReg = "<input style='height:25px;width:70px;' type='button' value='Registrar' id='btnRegistrar_" + cl + "' onclick=\"actualizarCumplimiento(this, '"+cl+"');\" disabled='disabled' />";
								} 
								jQuery("#gridSeguimiento").jqGrid('setRowData',ids[i],{Cumplimiento:btnReg}); 
							} 
						},
					onSelectRow: function(rowID) {
					}
				}).navGrid('#paginadorSeguimiento',{edit:false,add:false,del:false});
				//revisamos el estatus de la medida
				if(estatus==<%=mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida.EN_PROCESO.getValorId()%>)
				{
					//alert("entre boto hide");
					$('#btnCalendarizar').hide();
					$("#btnCancelarSeguimiento").show();
					$("#tabsprincipalconsulta").tabs('enable', 1);
				}
				else if(estatus==<%=mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida.NO_ATENDIDA.getValorId()%>)
				{
					//alert("entre boto hide");
					$('#btnCalendarizar').show();
					$("#btnCancelarSeguimiento").hide();
				}
				else if(estatus==<%=mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida.CANCELADA.getValorId()%>)
				{
					killTabSeguimiento();
				}
			}

			 /**
				*Funcion que obtiene el valor de la celda seleccionada en la agenda
				*/
				function seleccionCelda(idRow,dia,fecha){
					
					//verifica si la sub string inhabil se encuentra en la cadena fecha,
					//de NO ser asi devuelve -1
					if(fecha.indexOf("inhabil")==-1){
						var fechaPos1=fecha.indexOf(">", 0);
						var fechaPos2=fecha.indexOf("<", fechaPos1);
						fechaReal="";
						fechaReal=fecha.substring(fechaPos1+1, fechaPos2);
						muestraFechaSeleccionada(fechaReal);
						controlSalas(fechaReal);
						//alert("entra No. Fila=" + idRow +"  Dia=" + dia + "  Fecha="+ fechaReal);
						//$("#gridAgendaPJENA").jqGrid('setCell',"1","lunes","",{'font-weight': 'bold',color: 'white','text-align':'center','background-color':'red'});			
					}
					else{
						alert("Seleccione un dia hábil");
					}
				}
							

///////////////////////////////////////////////COMIENZA FUNCIONALIDAD DEL CALENDARIO //////////////////////////////////////////////////	
	/**
	*Funcion que obtiene el valor del desplazamiento para los meses "atras" o "adelante",
	*pasa el valor al action para que este ejecute el movimiento y recarga el grid de 
	*agenda 
	*/
	function atrasAdelanteMes(movimiento){
		recorridoMeses(movimiento);
		jQuery("#gridAgendaPJENA").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/calendarioActual.do?mov='+ movimiento +'',datatype: "xml" });
		$("#gridAgendaPJENA").trigger("reloadGrid");
	}

	/**
	*Funcion que recorre el carrusel de los mese y anio si la bandera de cambio se encuentra encendida
	*los meses no se recorran, hasta que el grid sea refrescado y baje la bandera 
	*/
	function recorridoMeses(movimiento){
	
				if(banderaCambio==0){
				if(movimiento=="atras"){
				
				if(mesActual == 0){
				mesActual=11;
				if(anioActual == 0)
				anioActual=3000;
				else
				anioActual--;
				}
				
				else
				mesActual--;
				}
				else{
				if(mesActual == 11){
				mesActual=0;
				anioActual++;
				}
				else
				mesActual++;
	}
	var mesSeleccionado = meses[mesActual];
	
	//setea los valor de mes y anio a la caja de texto
	$('#mes').val(mesSeleccionado);
	$('#anio').val(anioActual);
	//levanta la bandera de cambio
	banderaCambio=1;
	}	
}
///////////////////////////////////////////////TERMINA FUNCIONALIDAD DEL CALENDARIO //////////////////////////////////////////////////

	/**Funcion que consulta el catalogo de periodicidad para llenar el combo*/
	function consultarCatalogoPeriodicidad(){
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoPeriodicidad.do',
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$(xml).find('catPeriodicidad').each(function(){
					$('#cbxPeriodicidad').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	//Funcion que consulta los datos de una medida cautelar de acuerdo al row seleccionado del grid
	function consultarMedidaAlternaPorId(medidaAlternaId){
		$.ajax({
	    	  type: 'POST',
	    	  url:  '<%= request.getContextPath()%>/consultarMedidaAlternaPorId.do',
	    	  data: 'medidaAlternaId='+medidaAlternaId+'&estatusId='+estatus,
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    		  pintarDatosMedidaAlterna(xml);
	    		  if($('#fechaInicioSSP').val() != "" && $('#horaInicioSSP').val() != "" && $('#cbxPeriodicidad').val() != -1){
	    			  //$("#tabsprincipalconsulta").tabs('enable', 1);
	    			  $('#btnCalendarizar').show();
		    	  }else{
		    		  $('#btnCalendarizar').hide();
			      }
    			window.parent.cargaGridAlternas(estatus);
		    	  //visualizaPeriodicidad($('#cbxPeriodicidad').val());
			  }
	    });
	}

	//Funcion que obtiene de un xml la información requerida a mostrar en la pestaña 'Medidas Alternas'  
	function pintarDatosMedidaAlterna(xml){
		if($(xml).find('MedidaAlternaDTO') != null){
			$('#campoCasoSSP').val($(xml).find('numeroCaso').text());
			$('#campoCausaSSP').val($(xml).find('numeroCausa').text());
			$('#campoEjecucionSSP').val($(xml).find('numeroCarpetaEjecucion').text());
			$('#campoJuezSSP').val($(xml).find('juezOrdena').text());
			$('#campoMedidaSSP').val($(xml).find('valorTipoMedida').find('valor').text());
			$('#campoSujetoSSP').val(
				$(xml).find('nombre').text() + ' ' + $(xml).find('apellidoPaterno').text() + ' ' + $(xml).find('apellidoMaterno').text());				
			$('#fechaInicioSSP').val($(xml).find('fechaInicioStr').text());
			$('#fechaFinSSP').val($(xml).find('fechaFinStr').text());
			$('#areaRecomendaciones').val($(xml).find('descripcionMedida').text());
			
			$('#cbxPeriodicidad').val($(xml).find('valorPeriodicidad').find('idCampo').text());
			if($('#cbxPeriodicidad').val() != -1){
				$('#cbxPeriodicidad').attr('disabled','disabled');
			}
			
			var fechaInicio = $(xml).find('fechaInicio').text();
			if(fechaInicio != ""){
				var arrFechaInicio = fechaInicio.split(" ");
				var arrHoraInicio = arrFechaInicio[1].split(":");
				var horaInicio = arrHoraInicio[0] + ':' + arrHoraInicio[1];
				$('#horaInicioSSP').val(horaInicio);
			}
			//AGREGAMOS EL NUMERO DE EXPEDIENTE ID PARA CREAR DOCUMENTOS
			numeroExpedienteId=$(xml).find('numeroExpedienteId').first().text();
			numUnicoExp=$(xml).find('numeroExpediente').first().text();
			//AGREGADO PARA LA INTEGRACION CON LAS ACTUACIONES

			//FALTA IMPLEMENTAR QUE MUESTRE LAS RECOMENDACIONES [areaRecomendaciones]			
		}
	}

	//Funcion que muestra u oculta la periodicidad de acuerdo al dato seleccionado en el combo
	function visualizaPeriodicidad(cbxVal){
	  	  /*if(cbxVal == <%=Periodicidad.MENSUAL.getValorId()%>){
			  $('#divMes').show();
			  $('#divSemana').hide();
	   	  }else if(cbxVal == <%=Periodicidad.SEMANAL.getValorId()%>){
			  $('#divSemana').show();
			  $('#divMes').hide();
	   	  }else{
	   		  $('#divSemana').hide();
			  $('#divMes').hide();
	   	  }*/
	   	  if(cbxVal != -1){
	   		$('#btnCalendarizar').show();
		  }else{
			  $('#btnCalendarizar').hide();
		  }	    			  
	}

	//Funcion que envia parametros al servicio que guarda las fechas de cumplimiento en la base de datos
	function generarCalendarizacion(){
		$("#tabsprincipalconsulta").tabs('enable', 1);

		var parametros = 'medidaCautelarId=' + medidaAlternaId 
					   + '&fechaInicio=' + $('#fechaInicioSSP').val() 
					   + '&fechaFin=' + $('#fechaFinSSP').val()
					   + '&periodicidadId=' + $('#cbxPeriodicidad').val()
					   + '&estatus=' + estatus;

		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/generarCalendarizacion.do',
			data: parametros,
			dataType: 'xml',
			async: false,
			success: function(xml){
				alert('Calendarización registrada de manera correcta.');
				$('#btnCalendarizar').hide();
				jQuery("#gridSeguimiento").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarCalendarizacionPorMedidaIdReducido.do?medidaCautelarId='+medidaAlternaId+'',datatype: "xml" });
				$("#gridSeguimiento").trigger("reloadGrid");
			}
		});			
	}

	//Funcion que registra la hora en la cual se llevo a cabo el cumplimiento y se guarda en la base de datos
	function actualizarCumplimiento(obj, id){
		var rowData = jQuery('#gridSeguimiento').getRowData(id); 
      	//var rowHora = rowData['Hora'];
      	var now = new Date();
      	var strHora = (now.getHours() < 10 ? "0" + now.getHours() : now.getHours())  
      				+ ':' 
      				+ (now.getMinutes() < 10 ? "0" + now.getMinutes() : now.getMinutes());
		jQuery('#gridSeguimiento').jqGrid('setCell', id, 'Hora', strHora, '', '', '');

		rowData = jQuery('#gridSeguimiento').getRowData(id);

		//alert("registrando hora [" + rowData["Hora"] + "] de cumplimiento ...");

		var strFechaCompleta = rowData["Fecha"] + " " + rowData["Hora"];
		
		var parametros = 'fechaCompromisoId=' + id + '&fechaCumplimiento=' + strFechaCompleta;

		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/actualizarCumplimiento.do',
			data: parametros,
			dataType: 'xml',
			async: false,
			success: function(xml){
				alert('Cumplimiento registrado de manera correcta.');
				obj.disabled = 'disabled';
			}
		});				
		
	}


	/**************************************************************FUNCIONALIDAD PARA LA CEJA DOCUMENTOS************************************************************/
	/*
	*Funcion que carga el grid con los nombre de los documentos digitales asociados 
	*al id de la solicitud de serv. periciales
	*/
	function cargaGridDocumentosDigitales(){

		/*if(primeraVezGridDocumentosDigitales == true){
			jQuery("#gridDocumentosDigitales").jqGrid({
				url:'<%=request.getContextPath()%>/consultarDocumentosAsociadosAAlternaCautelar.do?medidaId='+medidaId+'',
				datatype: "xml", 
				colNames:['Nombre de Documento'],
				colModel:[ {name:'nombre',index:'nombre', width:255},
							],
				pager: jQuery('#pagerGridDocumentosDigitales'),
				rowNum:20,
				rowList:[10,20,30],
				width:250,
				sortname: 'nombreDocumento',
				viewrecords: true,
				sortorder: "desc",
				//multiselect:true,
				ondblClickRow: function(rowid) {
					if (rowid) {
						abrirDocsDigAsociadosASol(rowid);							
					}
				}
			}).navGrid('#pagerGridDocumentosDigitales',{edit:false,add:false,del:false});
			$("#gview_gridDocumentosDigitales .ui-jqgrid-bdiv").css('height', '455px');
			
			primeraVezGridDocumentosDigitales= false;
		}
		else{
			jQuery("#gridDocumentosDigitales").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarDocumentosAsociadosAMedidaAlterna.do?medidaId='+medidaId+'',datatype: "xml" });
			$("#gridDocumentosDigitales").trigger("reloadGrid");
		}*/
	}

	/*
	*Funcion que abre el PDF para ver los documentos asociados al numero de causa
	*/
	function abrirDocsDigAsociadosASol(documentoAsocId){
		if(documentoAsocId != null && documentoAsocId != ""){
			$("#visorDocsFrame").attr("src","<%= request.getContextPath()%>/ConsultarContenidoArchivoDigital.do?archivoDigitalId="+documentoAsocId+"&inFrame=true");
		}
		else{
			alert("El documento no puede ser mostrado");
		}
	}


/*******************************************************COMIENZA FUNCIONALIDAD PARA ACTUACIONES PERICIALES ***************************************************/
	
	/*************************************************************FUNCIONALIDAD COMUN*********************************************************************/
	
	/*
	*Funcion que oculta todos los elementos de la pantalla
	*/
	function ocultaActuaciones(){
		
		$("#agregarDocs").hide();
	}

	/********************************************************FUNCIONALIDAD PARA ANEXAR DOCUMENTOS*******************************************************/
	
	/*
	*Funcion para anexar un documento a la solicitud pericial
	*/
	function anexarDocumento(){

		
		//forma = document.anexarDocumentoForm; 
		//forma.documentoId.value = documentoId;
		//forma.submit();
			
	}


	/********************************************************FUNCIONALIDAD PARA GENERAR DOCUMENTOS***********************************************/
	
	function actuacionesQueja(){
		var selected = $("#lstActuaciones option:selected").val();

		//Oculta todo
		if(selected == "0"){
			ocultaActuaciones();
		}
		
		//Agregar documentos
		if(selected == "1"){
			$("#agregarDocs").show();
		}
		
		//Realizar dictamen
		if(selected != "1" && selected != "0"){
			$("#agregarDocs").hide();
			generarDocumentoSinCaso(selected);
		}
	}

	 function generarDocumentoSinCaso(formaId) {
		 //alert("formaId- " + formaId);
		if(formaId==105){
			formaId = <%=Formas.COLABORACION_PARA_CUMPLIMIENTO_ALTERNATIVAS.getValorId()%>;
			 alert("formaId- 1" + formaId);
		 }else if(formaId==106){
 			formaId = <%=Formas.INCUMPLIMIENTO_MEDIDA_ALTERNATIVA.getValorId()%>;
 			 alert("formaId- 2" + formaId);
		 }else if(formaId==107){
 			formaId = <%=Formas.CUMPLIMIENTO_MEDIDA_ALTERNATIVA.getValorId()%>;
 			 alert("formaId-3 " + formaId);
		 }
 		
		 var titulo = "Generar Oficio de Medida Alternativa";
		 var numExpediente="";
		 $.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:titulo, type:"iframe"});
	     $.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/generarDocumentoSinCaso.do?esconderArbol=0&formaId="+formaId+"&numeroUnicoExpediente="+numExpediente+"' width='1140' height='400' />");
	 }
	 
	 function killTabSeguimiento(){
			$("#tabsprincipalconsulta").tabs( "select" , 0);
			$("#tabsprincipalconsulta").tabs( "remove" , 1);
		}

		function cancelarSeguiemiento()
		{
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/cancelarMedida.do?medidaCautelarId='+medidaAlternaId+'',
				dataType: 'xml',
				async: false,
				success: function(xml){
					//Revisamos la respuesta del servidor
					if($(xml).find('bandera').text()=='1')
					{
						killTabSeguimiento();
						alert("Se canceló exitosamente el seguimiento.");
					}
					else
					{
						alert("No se logró cancelar el seguimiento");
					}
				}
			});
		}

	
</script>
	
	<!--ESTILOS PARA LAS TABS-->
	
	<style>
	#tabs { height: 100% } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 550px; overflow: visible; } 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	.tabEstilo  { height: 350px; overflow: auto; }
	</style>
	
</head>
<body>
<div id="tabsprincipalconsulta">
	<ul>
		<li id="tabMedidasAlternas" ><a href="#tabsconsultaprincipal-1">Medidas Alternas</a></li>
		<li id="tabSeguimientos" ><a href="#tabsconsultaprincipal-3">Seguimientos</a></li>
		<li id="tabDocumentos" ><a href="#tabsconsultaprincipal-4">Documentos</a></li>
		<li id="tabActuaciones" ><a href="#tabsconsultaprincipal-5">Actuaciones</a></li>
		
	</ul>

		<div id="tabsconsultaprincipal-1">
			<fieldset>
			<table width="580" border="0">
			  <tr>
			    <td width="125" align="right">Caso</td>
			    <td width="172" ><input name="" id="campoCasoSSP" type="text" disabled="disabled" /></td>
			    <td width="120" align="right">Causa</td>
			    <td width="145" ><input name="" id="campoCausaSSP" type="text" disabled="disabled" /></td>
			  </tr>
			  <tr>
			    <td align="right">Carpeta de Ejecuci&oacute;n</td>
			    <td><input name="" id="campoEjecucionSSP" type="text" disabled="disabled" /></td>
			    <td align="right">Juez que Ordena</td>
			    <td><input name="" id="campoJuezSSP" type="text" disabled="disabled" /></td>
			  </tr>
			  <tr>
			    <td align="right">Sujeto a Medida</td>
			    <td><input name="" id="campoSujetoSSP" type="text" disabled="disabled" /></td>
			    <td align="right">Medida</td>
			    <td><input name="" id="campoMedidaSSP" type="text" disabled="disabled" /></td>
			  </tr>
			  <tr>
			    <td width="125" align="right">Fecha Inicio</td>
			    <td width="174"><input name="" id="fechaInicioSSP" type="text" disabled="disabled"/></td>
			  	<td width="116" align="right">Hora Inicio</td>
			    <td width="144"><input name="" id="horaInicioSSP" type="text" disabled="disabled"/></td> 
			  </tr>
			  <tr>
				<td align="right">Fecha Terminaci&oacute;n</td>
			  	<td width="174"><input name="" id="fechaFinSSP" type="text" disabled="disabled"/></td>
			    <td align="right">Periodicidad</td>
			    <td>
			    	<select name="cbxPeriodicidad" id="cbxPeriodicidad" onchange="visualizaPeriodicidad(this.value)">
			    		<option value="-1">-Seleccione-</option>
				    </select>
				</td>
			  </tr>
			  <tr>
			  	<td colspan="2">&nbsp;</td>
			  	<td colspan="1" align="right">Recomendaciones</td>
			  </tr>  
			  <tr>
			  	
			  	<td colspan="2" align="center"><input type="button" id="btnCalendarizar" value="Calendarizar"  onclick="generarCalendarizacion();" align="left" class="btn_Generico"/></td>
			   	<td colspan="2"><textarea id="areaRecomendaciones" cols="45" rows="5" style="width: 250px; height:100px;" disabled="disabled"></textarea></td>
			  </tr>
			</table>
			</fieldset>

		</div>
			
			
		<div id="tabsconsultaprincipal-3">		
			<div id="divGridSeguimiento">
               <table id="gridSeguimiento"></table>
               <div id="paginadorSeguimiento"></div>
            </div>
            <br/>
            <input type="button" value="Cancelar Seguimiento" onclick="cancelarSeguiemiento()" id="btnCancelarSeguimiento" class="btn_Generico"/>
		</div>
			
		<div id="tabsconsultaprincipal-4">
			<!--Comienza div ver los documentos relacionados a la solicitud-->
			<!--<div id="tabsconsultaprincipal-2">-->
			<table width="1150"  height="530" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="250" align="center" valign="top">
                       <table id="gridDocumentosDigitales"></table>
                       <div id="pagerGridDocumentosDigitales"></div>
                </td>
                <td width="900" align="center" valign="top">
               	  <iframe id='visorDocsFrame' width="900" height="500" src="">		               	  
               	  </iframe>
                </td>
              </tr>
            </table>
			<!--</div>-->
			<!--Termina div para adjuntar documentos al enviar la solicitud-->
		</div>
			
			
		<!--Comienza div para las actuaciones del perito-->
		<div id="tabsconsultaprincipal-5">
		
			<table width="1150" height="550" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td height="20" align="center">
			    	<fieldset style="width: 1150px;">
						<legend>Actuaciones:</legend>
							<table width="100%" border="0" height="90%">
								<tr>
									<td>
										<select id="lstActuaciones" style="width:200px;" onchange="actuacionesQueja();">
											<option value="0">-Seleccione-</option>
											<option value="1">Adjuntar archivo digital</option>
											<option value="105">Oficio de colaboraci&oacute;n para el cumplimiento de medida alterna </option>
											<option value="106">Oficio de incumplimiento de medida alterna</option>
											<option value="107">Oficio de cumplimiento de medida alterna</option>
								        </select>
									</td>
								</tr>
							</table>
					</fieldset>
			    </td>
			  </tr>
			  <tr>
			    <td height="530" valign="top">
			       
			       <!--Comienza Agregar Documentos-->
			    	<div id="agregarDocs">
			    	
			    		<table border="0">
			    			<tr>
			    				<td align="right"><span class="au av ra rc ta" ><strong>Ruta su documento digital:</strong></span></td>
						        <td>
						        	<div id="divAdjuntarDoc" class="au av ra rc ta">
							        	<form id="anexarDocumentoForm" name="anexarDocumentoForm" 
							        	action="<%= request.getContextPath() %>/registrarSolicitudPJATP.do" method="post" enctype="multipart/form-data" >
											<input type="file" name="archivoAdjunto" > 
											<input type="hidden" name="documentoId"/>
										</form>
									</div>
						        </td>
			    			</tr>
			    			<tr>
			    				<td></td>
			    				<td>
			    					<input id="anexarDoc" type="button" value="Anexar" onclick="anexarDocumento();" class="btn_Generico"/>
			    					<input id="limpiarAnexarDoc" type="button" value="Limpiar" class="btn_Generico"/>
			    				</td>
			    			</tr>
			    		</table>
			    		
			       	</div>
			       	<!--Comienza Agregar Documentos-->
			       	
			    </td>
			  </tr>
			</table>
								
		</div>
		<!--Termina div para las actuaciones-->
			
</div>	
</body>
</html>