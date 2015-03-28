<%@page import="mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.TipoMandamiento"%>
<%@page
	import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atencion a Sollicitudes</title>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />

<!--Se importan los scripts necesarios-->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.blockUI.js"></script>
<script type="text/javascript">

	var contextoPagina = "${pageContext.request.contextPath}";

	/**
	* Variables para solicitudes de audiencia
	*/
	
	var tipoAudGlob;
	var fechaGlob;
	var idNuevaCalidad;
	var idInvolucrado;
	idAudiencia = <%=request.getParameter("idAudiencia")%>;	    
	
	
	$(document).ready(function() {

	jQuery(document).ajaxStop(jQuery.unblockUI);

	var dates =	$("#fInicioSentencia, #fFinSentencia").datepicker(
		{
//			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 2,
			onSelect: function( selectedDate ) {
				var option = this.id == "fInicioSentencia" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(
				instance.settings.dateFormat ||
				$.datepicker._defaults.dateFormat,
				selectedDate, instance.settings );
				dates.not( this ).datepicker( "option", option, date );
									
			},
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		}
	);

		
		//alert(idAudiencia);
		//crea las tabs
		$( "#tabsprincipalconsulta" ).tabs();
		//Se cargan funciones de la ceja solicitudes en audiencia
		consultaDetalleEvento(idAudiencia);
		consultarJuecesDeAudiencia(idAudiencia);
		
		//Se cargan funciones de la ceja solicitudes en audiencia
		cargarGridSolicitudesTranscripcionAudiencia(idAudiencia,'<%=TiposSolicitudes.TRANSCRIPCION_DE_AUDIENCIA.getValorId()%>');
		cargarGridSolicitudesAudioVideoAudiencia(idAudiencia,'<%=TiposSolicitudes.AUDIO_VIDEO.getValorId()%>');
		
		//Se cargan funciones de la ceja estado de intervinientes
		cargarGridProbResponsable(idAudiencia);
		
		//Se cargan funciones de la ceja mandamientos judiciales
		cargaResolutivosAudiencia(idAudiencia);
		$("#GenerarMandamiento").click(generarMandamiento);
		$("#ordenar").click(ordenarSolicitudes);
		cargaTipoMandamiento();
		cargaTipoSentencia();
		consultarImputadosParaMandamientoXAudiencia();
		$('#tipoMandamiento').change(controlTipoMandamiento);
	});
	
//***********************************************************FUNCIONALIDAD COMUN PARA TODAS LAS CEJAS**************************************************************/
 	
	/*
	*Funcion que consulta el detalle del evento y llena 
	*los campos de la TAB Detalle evento
	*/
	function consultarJuecesDeAudiencia(idAudiencia){
		
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/consultarJuecesDeAudiencia.do',
			data: 'audienciaId='+ idAudiencia, 
			//data: 'audienciaId='+3,
			async: false,
			dataType: 'xml',
			success: function(xml){
				var errorCode;
				errorCode=$(xml).find('response').find('code').text();
				if(parseInt(errorCode)==0){
					$(xml).find('listaJueces').find('juez').each(function(){
						$('#juecesAudienciaPJENS').append('<option value="' + $(this).find('involucradoId').text() + '">' + $(this).find('nombre').first().text()+" "+$(this).find('apellidoPaterno').first().text()+" "+$(this).find('apellidoMaterno').first().text() + '</option>');
						$('#juecesAudienciaIntervPJENC').append('<option value="' + $(this).find('involucradoId').text() + '">' + $(this).find('nombre').first().text()+" "+$(this).find('apellidoPaterno').first().text()+" "+$(this).find('apellidoMaterno').first().text() + '</option>');
						$('#juecesAudienciaMandamientosPJENC').append('<option value="' + $(this).find('involucradoId').text() + '">' + $(this).find('nombre').first().text()+" "+$(this).find('apellidoPaterno').first().text()+" "+$(this).find('apellidoMaterno').first().text() + '</option>');
					});
				}
				else{
					//alert(mensaje error);
				}
			}
		});
	}
 	
 	
 	/*
	*Funcion que consulta el detalle del evento y llena 
	*los campos de la TAB Detalle evento
	*/
	function consultaDetalleEvento(idAudiencia){

		//alert(idAudiencia);
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/detalleAudienciasDelDiaPJENS.do',
			data: 'idEvento='+ idAudiencia, 
			async: false,
			dataType: 'xml',
			success: function(xml){

					//limpia todos los capos de las diferentes cejas
    				limpiaDatosDetalleEvento();

 					//Ceja Solucitudes en audiencia   				
    			    tipoAudGlob	= $("#tipoAudienciaPJENS").val($(xml).find('audienciaDTO').find('tipoAudiencia').find('valor').first().text());
					    			    
    				$("#numCasoPJENS").val($(xml).find('audienciaDTO').find('expediente').find('casoDTO').find('numeroGeneralCaso').first().text());
    				$("#numExpPJENS").val($(xml).find('numeroExpediente').first().text());
    				$("#audienciaPJENS").val($(xml).find('id').first().text());
    				$("#caracterPJENS").val($(xml).find('caracter').first().text());

    				//Ceja Estado Intervinientes   				
    			    $("#tipoAudienciaIntervPJENC").val($(xml).find('audienciaDTO').find('tipoAudiencia').find('valor').first().text());
    				$("#audienciaIntervPJENC").val($(xml).find('id').first().text());			

    				//Ceja Mandamientos Judiciales   				
    			    $("#tipoAudienciaMandamientosPJENC").val($(xml).find('audienciaDTO').find('tipoAudiencia').find('valor').first().text());
    				$("#audienciaMandamientosPJENC").val($(xml).find('id').first().text());			
      				 
    				var fecha= $(xml).find('fechaEvento').text();
					formateaFechaHora(fecha);

					var fechaFin= $(xml).find('fechaHoraFin').text();
					formateaFechaHoraFin(fechaFin);
    				//$("#juezPJENS").val($(xml).find('juez').first().text());

    				
			}
		});
	}

	
	/*
	*Funcion que limpia las cajas de texto
	*antes de llenarlas con los datos de la
	*nueva consulta
	*/	
	function limpiaDatosDetalleEvento(){

		//Limpia datos de la ceja solicitudes de audiencia
		$("#tipoAudienciaPJENS").val("");
		$("#numCasoPJENS").val("");
		$("#numExpPJENS").val("");
		$("#audienciaPJENS").val("");
		$("#caracterPJENS").val("");
		$("#fechaAudienciaPJENS").val("");
		$("#horaAudienciaPJENS").val("");
		//$("#juezPJENS").val("");

		//Limpia datos de la ceja solicitudes de audiencia
		$("#audienciaIntervPJENC").val("");
		$("#tipoAudienciaIntervPJENC").val("");
		$("#juecesAudienciaIntervPJENC").val("");
		$("#fechaAudienciaIntervPJENC").val("");
		$("#horaAudienciaIntervJENC").val("");
		$("#fechaFinAudienciaIntervPJENC").val("");
		$("#horaFinAudienciaIntervPJENC").val("");

		//Limpia datos de la ceja Mandamientos judiciales
		$("#audienciaMandamientosPJENC").val("");
		$("#tipoAudienciaMandamientosPJENC").val("");
		$("#juecesAudienciaMandamientosPJENC").val("");
		$("#fechaAudienciaMandamientosPJENC").val("");
		$("#horaAudienciaMandamientosPJENC").val("");
		$("#fechaFinAudienciaMandamientosPJENC").val("");
		$("#horaFinAudienciaMandamientosPJENC").val("");
	}

	
	/*
	*Funcion que da formato a la fecha
	*/
	function formateaFechaHora(fechaHora){
		
		var fechaFrac = fechaHora.split(" ")[0];
		var horaFrac = fechaHora.split(" ")[1];

		horaFracPos = horaFrac.indexOf(":", 0);
		hora=horaFrac.substring(0,horaFracPos+3);
		//Ceja solicitudes en audiencia		    				
		$("#fechaAudienciaPJENS").val(fechaFrac);
		$("#horaAudienciaPJENS").val(hora);
		//Ceja estado intervinientes
		$("#fechaAudienciaIntervPJENC").val(fechaFrac);
		$("#horaAudienciaIntervJENC").val(hora);
		//Ceja estado intervinientes
		$("#fechaAudienciaMandamientosPJENC").val(fechaFrac);
		$("#horaAudienciaMandamientosPJENC").val(hora);		
	}


	/*
	*Funcion que da formato a la fecha
	*/
	function formateaFechaHoraFin(fechaHora){
		
		var fechaFrac = fechaHora.split(" ")[0];
		var horaFrac = fechaHora.split(" ")[1];

		horaFracPos = horaFrac.indexOf(":", 0);
		hora=horaFrac.substring(0,horaFracPos+3);
		//Ceja solicitudes en audiencia		    				
		$("#fechaFinAudienciaPJENS").val(fechaFrac);
		$("#horaFinAudienciaPJENS").val(hora);
		//Ceja estado intervinientes
		$("#fechaFinAudienciaIntervPJENC").val(fechaFrac);
		$("#horaFinAudienciaIntervPJENC").val(hora);
		//Ceja estado intervinientes
		$("#fechaFinAudienciaMandamientosPJENC").val(fechaFrac);
		$("#horaFinAudienciaMandamientosPJENC").val(hora);		
	}
	
//***********************************************************FUNCIONALIDAD CEJA SOLICITUDES EN AUDIENCIA**************************************************************/
	
	
	/**
	*Funcion que carga el grid con las solicitudes de transcripcion
	*de audiencia
	*/
	gridTranscripcionCargado = false;
	function cargarGridSolicitudesTranscripcionAudiencia(idAudiencia,enumTipoSolicitud){
		if(!gridTranscripcionCargado){
			jQuery("#gridSolTranscripcionAudienciaPJENC").jqGrid({
				url:'<%=request.getContextPath()%>/consultarSolicitudesTransAudioVideoPorEstatus.do?idAudiencia='+idAudiencia+'&enumTipoSolicitud='+enumTipoSolicitud+'',
				datatype: "xml", 
				colNames:['Nombre','Institución','Ordenado','Atendido'], 
				colModel:[  {name:'NombreSol',index:'nombreSol', width:200, align:'center'},						
				           	{name:'Institucion',index:'institucion', width:150, align:'center'}, 
				           	{name:'Ordenado',index:'ordenado', width:70,align:'center'}, 
				           	{name:'Atendido',index:'Atendido', width:70, align:'center'},
						],
				pager: jQuery('#pagerGridSolTranscripcionAudienciaPJENC'),
				rowNum:10,
				rowList:[25,50,100],
				width:490,
				sortname:'detalle',
				viewrecords:true,
				sortorder:"desc",
				caption:"Solicitantes"
			}).navGrid('#pagerGridSolTranscripcionAudienciaPJENC',{edit:false,add:false,del:false});
			
			
			gridTranscripcionCargado = true;
		}else{
			jQuery("#gridSolTranscripcionAudienciaPJENC").jqGrid('setGridParam', 
					{url:
						'<%=request.getContextPath()%>/consultarSolicitudesTransAudioVideoPorEstatus.do?idAudiencia='+idAudiencia+'&enumTipoSolicitud='+enumTipoSolicitud,datatype: "xml" });
			$("#gridSolTranscripcionAudienciaPJENC").trigger("reloadGrid");
		}
		
	}


	/**
	*Funcion que carga el grid con las solicitudes audio video
	*de audiencia
	*/
	gridSolicitudesAVCargado = false;
	function cargarGridSolicitudesAudioVideoAudiencia(idAudiencia,enumTipoSolicitud){

		if(!gridSolicitudesAVCargado){
			
			jQuery("#gridSolAudioVideoAudienciaPJENC").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultarSolicitudesTransAudioVideoPorEstatus.do?idAudiencia='+idAudiencia+'&enumTipoSolicitud='+enumTipoSolicitud,
				datatype: "xml", 
				colNames:['Nombre','Instituci&oacute;n','Ordenado','Atendido'], 
				colModel:[  {name:'NombreSol',index:'nombreSol', width:200, align:'center'},						
				           	{name:'Institucion',index:'institucion', width:150, align:'center'}, 
				           	{name:'Ordenado',index:'ordenado', width:70,align:'center'}, 
				           	{name:'Atendido',index:'Atendido', width:70, align:'center'},
						],
				pager: jQuery('#pagerGridSolAudioVideoAudienciaPJENC'),
				rowNum:10,
				rowList:[25,50,100],
				width:490,
				sortname:'detalle',
				viewrecords:true,
				sortorder:"desc",
				caption:"Solicitantes"
			}).navGrid('#pagerGridSolAudioVideoAudienciaPJENC',{edit:false,add:false,del:false});
			gridSolicitudesAVCargado=true;
		}else{
			jQuery("#gridSolAudioVideoAudienciaPJENC").jqGrid('setGridParam', 
					{url: '<%=request.getContextPath()%>/consultarSolicitudesTransAudioVideoPorEstatus.do?idAudiencia='+idAudiencia+'&enumTipoSolicitud='+enumTipoSolicitud,datatype: "xml" });
			$("#gridSolAudioVideoAudienciaPJENC").trigger("reloadGrid");
		}
		
		
	}

//***********************************************************FUNCIONALIDAD CEJA ESTADO DE INTERVINIENTES**************************************************************/
	
	/**
	*Funcion que carga el grid con el probable responsable y la lista de delitos
	*/
	function cargarGridProbResponsable(idAudiencia){
		
		jQuery("#gridProbResponsablePJENC").jqGrid({ 
			url:'<%=request.getContextPath()%>/consultarProbablesResponsablesPorVictimaYDelito.do?audienciaId='+idAudiencia+'',
			datatype: "xml", 
			colNames:['Nombre Probable Responsable','Nombre Víctima','Delito','Calidad Actual','Nueva Calidad'], 
			colModel:[  {name:'NombreImputado',index:'nombreImputado', width:200, align:'center'},						
			           	{name:'NombreVictima',index:'nombreVictima', width:200, align:'center'}, 
			           	{name:'Delito',index:'delito', width:120,align:'center'}, 
			           	{name:'CalidadActual',index:'calidadActual', width:150, align:'center'},
			           	{name:'NuevaCalidad',index:'NuevaCalidad', width:200, align:'center'},
					],
			pager: jQuery('#pagerGridProbResponsablePJENC'),
			rowNum:10,
			rowList:[25,50,100],
			width:820,
			sortname:'detalle',
			viewrecords:true,
			sortorder:"desc",
			caption:"Probable Responsable / Delito",
			onSelectRow: function(rowID) {
				idInvolucrado=rowID;
				var id2 = jQuery("#gridProbResponsablePJENC").jqGrid('getGridParam','selrow');
                var ret2 = jQuery("#gridProbResponsablePJENC").jqGrid('getRowData',id2);
                idNuevaCalidad= $('#'+id2+'_NuevaCalidad').val();
			},
			loadComplete : function(){
   				jsonParam = delitosPersonaAOrdenar();
   				limpiarJSON();
   				consultarNUSDeLosInvolucrados();
			}
		}).navGrid('#pagerGridProbResponsablePJENC',{edit:false,add:false,del:false});
	}


//***********************************************************FUNCIONALIDAD MANDAMIENTOS JUDICIALES**************************************************************/
	/*
	*Funcion que carga el grid con los resolutivos para dicha audiencia
	*/
	function cargaResolutivosAudiencia(idAudiencia){
		
		jQuery("#gridAudienciasResolutivosPJENC").jqGrid({ 
			url:'<%=request.getContextPath()%>/consultarResolutivosAudienciaPJENS.do?idEvento='+ idAudiencia,
			datatype: "xml", 
			colNames:['Temp. Video','Resolutivo',], 
			colModel:[ 	{name:'temporizadorVideo',index:'temporizadorVideo', width:150, align:"center"},
						{name:'resolutivo',index:'resolutivo', width:400,align:"center"}
					],
			pager: jQuery('#pagergGidAudienciasResolutivosPJENC'),
			rowNum:10,
			rowList:[10,20,30],
			width: 725,
			sortname: 'detalle',
			scrollrows : true,
			viewrecords: true,
			caption:"Resolutivos de Audiencia",
			sortorder: "desc",
			editurl: "http://localhost:8080/nsjp-web/encargadoCausas.jsp",
			loadComplete: function(){				
				var registros =jQuery("#gridAudienciasResolutivosPJENC").jqGrid('getDataIDs'); 
				var total = registros.length;
				$("#gridAudienciasResolutivosPJENC").jqGrid('setSelection',registros[total-1]);
			 },
			ondblClickRow: function(id){
				var data = jQuery("#gridAudienciasResolutivosPJENC").jqGrid('getRowData',id);
				modificarResolutivo(id, data);
			}
		}).navGrid('#pagergGidAudienciasResolutivosPJENC',{edit:false,add:false,del:false});
	}

	/*
	*Funcion que abre la ventana modal para ver los resolutivos de audiencia
	*/
	function modificarResolutivo(id, data) { 
		
		$('#tempVideo').val(data.temporizadorVideo);
		$('#resolutivo').val(data.resolutivo);
		$("#divAgregarResolutivo").dialog("open");
		$("#divAgregarResolutivo").dialog({
			autoOpen:true,
			modal:true, 
			title:'Agregar Resolutivo', 
			dialogClass:'alert',
			position:[200,50],
			width:350,
			height: 350,
			//maxWidth: 300,
			buttons:{"Aceptar":function() {			  		
					
			   		$(this).dialog("close");
				}
			}
		});
	}
	
	
	/*
	* Función que genera un Mandamiento y después abre la pantalla de mandamiento judicial
	*
	*/
	function generarMandamiento(){

		resolutivo = jQuery("#gridAudienciasResolutivosPJENC").jqGrid('getGridParam','selrow');
		
		if(resolutivo == null){
			alertDinamico("Seleccione un resolutivo para generar el mandamiento judicial");
			return;
		}

		limpiaDivTipoMandamiento();
		agregarCalendarios();

		$("#divTipoMandamiento").dialog("open");
	  	$("#divTipoMandamiento").dialog({ 
	  		autoOpen: true, 
			modal: true, 
		  	title: 'Tipo de Mandamiento', 
		  	dialogClass: 'alert',
		  	position: [500,220],
		  	width: 450,
		  	height: 300,
		  	maxWidth: 450,
		  	maxHeigth:300,
		  	buttons:{"Aceptar":function() {
			  	
		  		if(validarDatosMandamiento() == false){
		  			return;
			  	}else{
			  		if(tipoMandamiento == <%=TipoMandamiento.SENTENCIA.getValorId()%>){

		  				if(validaCamposFechaSentencia() == false){
		  					return;
			  			}
		  				else{
		  					enviarMandamiento();	
					  		$(this).dialog("close");
			  			}
				  	}else{
				  		enviarMandamiento();	
				  		$(this).dialog("close");
					}
				}
				
		  	  },
				"Cancelar" : function() {
					$(this).dialog("close");
				}
		  	}
		});	 
	}

	/*
	*Funcion que agrega el calendario a los campos fecha de inicio y fecha de fin
	*/
	function agregarCalendarios(){

		$('#fechaInicioSentencia').val('');
		$('#fechaFinSentencia').val('');

		$("#fechaInicioSentencia").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
	
		$("#fechaFinSentencia").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
	}

	//Funcion que valida si los campos estan llenos al enviar 
	function validaCamposFechaSentencia() {

		if ($('#fechaInicioSentencia').val() == '' || $('#fechaFinSentencia').val() == '') {
			alertDinamico("Debe ingresar tanto la fecha de inicio como la de fin");
			validaFecha=false;
		}else{

			var fechaIniVal = $('#fechaInicioSentencia').val();
			var fechaFinVal = $('#fechaFinSentencia').val();

			var inicio = fechaIniVal.split("/");
			var fin = fechaFinVal.split("/");

			if(fin[2]>inicio[2]){
				validaFecha=true;
			}
			else{
				if(fin[2]<inicio[2]){
					validaFecha=false;
				}
				else{
					if(fin[1]>inicio[1]){
						validaFecha=true;
					}	
					else{
						if(fin[1]<inicio[1]){
							validaFecha=false;
						}
						else{
							if(fin[0]>=inicio[0]){
								validaFecha=true;
							}
							else{
								validaFecha=false;
							}
						}
					}
				}
			}
				
			if(validaFecha==false){	
				alertDinamico("La fecha final debe de ser mayor a la fecha inicial");
			}
			
		}
		
		return validaFecha;
	}
	
	/*
	*Funcion para validar los datos para generar un mandamiento
	*/
	function validarDatosMandamiento(){
		
		tipoMandamiento = $('#tipoMandamiento option:selected').val();
		nombreImputado = $('#nombreDelImputado option:selected').val(); 
		tipoSentencia = $('#tipoSentencia option:selected').val();
		
		if(tipoMandamiento == 0){
			alertDinamico("Seleccione un tipo de mandamiento");
			return false;
		}
		if(tipoMandamiento == <%=TipoMandamiento.SENTENCIA.getValorId()%>){
			if(tipoSentencia == 0){
				alertDinamico("Seleccione un tipo de sentencia");
				return false;
			}
		}
		if(nombreImputado == 0){
			alertDinamico("Seleccione un imputado");
			return false;
		}
		return true;
	}

	/*
	*Funcion para mostrar u ocultar el tipo de sentencia dependiendo
	*de la seleccion del usuario
	*/
	function controlTipoMandamiento(){

		var tipoMandamiento = $('#tipoMandamiento option:selected').val();
		
		if(tipoMandamiento == 0){
			limpiaDivTipoMandamiento();
		}
		else if(tipoMandamiento == <%=TipoMandamiento.SENTENCIA.getValorId()%>){
			$('#divEtiTipoSentencia').show();
			$('#divCbxTipoSentencia').show();
			$('#divEtiFechaInicioSentencia').show();
			$('#divFechaInicioSentencia').show();
			$('#divEtiFechaFinSentencia').show();
			$('#divFechaFinSentencia').show();
		}else{
			$('#divEtiTipoSentencia').hide();
			$('#divCbxTipoSentencia').hide();
			$('#divEtiFechaInicioSentencia').hide();
			$('#divFechaInicioSentencia').hide();
			$('#divEtiFechaFinSentencia').hide();
			$('#divFechaFinSentencia').hide();
			limpiaDatosSentencia();
		}
	}

	/*
	*Limpia el Div de tipos de mandamiento
	*/
	function limpiaDivTipoMandamiento(){

		$('#tipoMandamiento').attr('selectedIndex', 0);
		$('#nombreDelImputado').attr('selectedIndex', 0);
		$('#tipoSentencia').attr('selectedIndex', 0);
		$('#fechaInicioSentencia').val("");
		$('#fechaFinSentencia').val("");
		//oculta el tipo de sentencia
		$('#divEtiTipoSentencia').hide();
		$('#divCbxTipoSentencia').hide();
		$('#divEtiFechaInicioSentencia').hide();
		$('#divFechaInicioSentencia').hide();
		$('#divEtiFechaFinSentencia').hide();
		$('#divFechaFinSentencia').hide();
	}

	/*
	*Limpia los campor de sentencia
	*/
	function limpiaDatosSentencia(){

		$('#tipoSentencia').attr('selectedIndex', 0);
		$('#fechaInicioSentencia').val("");
		$('#fechaFinSentencia').val("");
	}
	

	/*
	* Complementa la función de creación de un nuevo mandamiento judicial
	*/
	function enviarMandamiento(){

		var parametrosMandamiento = "";
		
		parametrosMandamiento += 'resolutivoId=' + jQuery("#gridAudienciasResolutivosPJENC").jqGrid('getGridParam','selrow');
		parametrosMandamiento += '&tipoMandamiento=' + $('#tipoMandamiento option:selected').val();
		parametrosMandamiento += '&idImputado=' + $('#nombreDelImputado option:selected').val();
		parametrosMandamiento += '&tipoSentencia=' + $('#tipoSentencia option:selected').val();
		parametrosMandamiento += '&fechaInicio=' + $('#fechaInicioSentencia').val();
		parametrosMandamiento += '&fechaFin=' + $('#fechaFinSentencia').val();

		//alert(parametrosMandamiento);
		
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/crearMandamientoJudicial.do',
			data: parametrosMandamiento, 
			async: false,
			dataType: 'xml',
			success: function(xml){
				
				//alert("archivoDigitalId="+$(xml).find("archivoDigitalId").first().text());
					
				if($(xml).find("archivoDigitalId").first().text()!=""){
										
					//el documento ya está generado, consultarlo
					document.verMandamientoForm.documentoId.value=$(xml).find("documentoId").first().text();
					document.verMandamientoForm.submit();
				}else{
					
					documentoId = $(xml).find("documentoId").first().text();
					numExp = $(xml).find("numeroExpediente").first().text();
					
					//mandamiento recién creado, mostrar el editor
					$.newWindow({id:"iframewindowGenerarMandamientoJudicial", 
						statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Mandamiento", 
						type:"iframe"});
			        $.updateWindowContent("iframewindowGenerarMandamientoJudicial",
			        		"<iframe src='<%=request.getContextPath()%>/generarDocumentoSincrono.do?documentoId="+documentoId+"&numeroUnicoExpediente="+numExp+"' width='1140' height='400' />");
				}
			}
		});
	}

	
	/**
	* Funcion que realiza la carga del catálogo de tipos de mandamiento
	*/
	function cargaTipoMandamiento() {
		
		$.ajax({
			async: true,
			type: 'POST',
			url: '<%=request.getContextPath()%>/consultarCatalogoTipoMandamiento.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				
				$(xml).find('tipoMandamiento').each(function(){
					$('#tipoMandamiento').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	
	/**
	* Funcion que realiza la carga del catálogo de tipos de sentencia
	*/
	function cargaTipoSentencia() {
		
		$.ajax({
			async: true,
			type: 'POST',
			url: '<%=request.getContextPath()%>/consultarCatalogoTipoSentencia.do',
			data: '',
			dataType: 'xml',
			success: function(xml){
				
				$(xml).find('tipoSentencia').each(function(){
					$('#tipoSentencia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					$('#tipoDeSentencia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	/**
	* Funcion que realiza la carga los involucrados de la audiencia
	*/
	function consultarImputadosParaMandamientoXAudiencia() {
		
		$.ajax({
			async: true,
			type: 'POST',
			url: '<%=request.getContextPath()%>/consultarImputadosParaMandamientoXAudiencia.do?idAudiencia='+idAudiencia+'',
			data: '',
			dataType: 'xml',
			success: function(xml){
				
				$(xml).find('imputado').each(function(){
					$('#nombreDelImputado').append('<option value="' + $(this).find('involucradoId').text() + '">' + $(this).find('nombreCompleto').text() + '</option>');
				});
			}
		});
	}

	

	
	/**
	* Realizar el ordenamiento de las solicitudes seleccionadas
	* Esto es mandarlas a backend a cambiar el estatus de las solicitudes
	*/
	function ordenarSolicitudes(){
		solicitudesAOrdenar = "";
		$('input[id^="ordenar_"]').each(function(){
			
			if($(this).attr("checked") == true){
				id = $(this).attr("id");
				solicitudesAOrdenar += id.split("_")[1] + ",";
			}
			
			
		});
		$.ajax({
			async: true,
			type: 'POST',
			url: '<%=request.getContextPath()%>/ordenarSolicitudesTranscripcionyAV.do?solicitudesIds='+solicitudesAOrdenar,
			data: '',
			dataType: 'xml',
			success: function(xml){
				
				//Se recargan grids de solicitudes
				cargarGridSolicitudesTranscripcionAudiencia(idAudiencia,'<%=TiposSolicitudes.TRANSCRIPCION_DE_AUDIENCIA.getValorId()%>');
				cargarGridSolicitudesAudioVideoAudiencia(idAudiencia,'<%=TiposSolicitudes.AUDIO_VIDEO.getValorId()%>');
			}
		});
		
	}

	var jsonParam = [{}];
	
	function actualizarSituacionJuridica(){	
	
//		for (var key in jsonParam) {
//				if (jsonParam[key].situacionJuridica == <%=SituacionJuridica.SENTENCIADO.getValorId()%>){
//					if(!jsonParam[key].todosLosDatos){
//						obtenerDatosSentencia(key);
//						return;
//				}
//			}
//		}			
		
		llenarDatosSentencia();
		
	}
	
	function limpiarJSON(){
			for (var key in jsonParam) {
			if(typeof jsonParam[key].involucradoId === "undefined"){
				//try para funcion recursiva que elimina los elementos vacios del arreglo de JSON 
				try {
					jsonParam.splice(key, 1);
					limpiarJSON();
				} catch (error) {
					return;
				}
			}
		}
	}
	

	/*
	*Funcion para obtener los ids del 
	*DELITO
	*INVOLUCRADO
	*NUEVA CALIDAD
	*/
	function delitosPersonaAOrdenar() {
		var solicitudesAOrdenar = "";
		var i=0;
		var arrayIds = new Array();
		var jsonList = [{}];
		
		
		arrayIds = jQuery("#gridProbResponsablePJENC").getDataIDs();
		
		$('select[id^="delito_"]').each(function(){
			id = $(this).attr("id");
			var datosRegistro = jQuery("#gridProbResponsablePJENC").jqGrid('getRowData', arrayIds[i]);
			jsonList.push({
							involucradoId: arrayIds[i], 
							otro: id.split("_")[1], 
							situacionJuridica: $(this).val(),
							nombreImputado: datosRegistro.NombreImputado,
							tipoSentencia: 0,
							esLesionado: 0,
							fechaInicio: "",
							fechaFin: "",
							puntosPorAcumular: 0,
							todosLosDatos: false,
							yaEstaSentenciado: ($(this).val() == <%=SituacionJuridica.SENTENCIADO.getValorId()%>) ? true : false,
							nus: 0,
							listaNUS: [{}]
							});
			i++;
		});
			
		return jsonList; 
	}

	/**
	* Función invocada una vez creado y almacenado en la base el archivo digital correspondiente al documento
	* Se envía el mandamiento judicial a SSP
	*/
	function documentoGeneradoSincrono(documentoId){
		
		$.closeWindow("iframewindowGenerarMandamientoJudicial");
		
		$.ajax({
			async: true,
			type: 'POST',
			url: '<%=request.getContextPath()%>/enviarMandamientoJudicial.do',
			data: 'mandamientoId='+documentoId,
			dataType: 'xml',
			success: function(xml){	
				alertDinamico("Mandamiento judicial enviado exitosamente");
			}

		});

		document.verMandamientoForm.documentoId.value = documentoId;
		document.verMandamientoForm.submit();
		
	}

	function llenarDatosSentencia(){
	
		for (var key in jsonParam) {
			actualizarDatos(key);
			if (jsonParam[key].situacionJuridica == <%=SituacionJuridica.SENTENCIADO.getValorId()%>){
				if(!jsonParam[key].todosLosDatos && !jsonParam[key].yaEstaSentenciado){
					obtenerDatosSentencia(key);
					return;
				}
			}
		}
		
		enviarDatosActualizarSituacionJuridica();
	}
	
	function actualizarDatos(key){
			var datosRegistro = jQuery("#gridProbResponsablePJENC").jqGrid('getRowData', jsonParam[key].involucradoId);								 
			jsonParam[key].situacionJuridica= jQuery("#delito_"+jsonParam[key].otro).val();
	}

	function enviarDatosActualizarSituacionJuridica(){
	 	bloquearPantalla();
		$.ajax({
			async: true,
			type: 'POST',
			url: '<%=request.getContextPath()%>/actualizarSituacionJuridica.do',
			contentType: "application/json; charset=utf-8",
        	dataType: "json",
        	data: JSON.stringify(jsonParam),
			success: function(json){		
			}
		});
	
	
	}

	function llenarForma(index){
		var html = "";
		
		var unico = false;
		var listNUS = jsonParam[index].listaNUS;		
		if (listNUS.unico == true || listNUS.unico =="TRUE"){
			unico = true;
		}else{
			html = "<table>";
			html += "<tr>";
			html += "<td colspan=\"4\">Seleccione un NUS para el sentenciado:</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td>&nbsp;</td>";
			html += "<td>Nombre</td>";
			html += "<td>Fecha Incio</td>";
			html += "<td>Fecha Fin</td>";
			html += "<td>NUS</td>";
			html += "</tr>"; 
		}
		
		
		
		jQuery.each(listNUS, function(k, v){
				if(k != "unico"){
					if(unico){
						html += "<input type=\"radio\" name=\"nuevoNUS\" ";
						html += " checked=\"checked\" ";
						html += "value=\"" + v +"\" style=\"display:none;\">";
						html = "<center><strong>" + v + "</strong></center>";
					} else {
						var myObject = eval('(' + v + ')');
						html += "<tr>";
						html += "<td><input type=\"radio\" name=\"nuevoNUS\" ";
						html += ((myObject.nombreCompleto == "Nuevo") ? " checked=\"checked\" " : " ");
						html += "value=\"" + myObject.NUS +"\"></td>";
						html += "<td>" + myObject.nombreCompleto +"</td>";
						html += "<td>" + myObject.fechaIncio +"</td>";
						html += "<td>" + myObject.fechaIncio +"</td>";
						html += "<td>" + myObject.NUS +"</td>";
						html += "</tr>";
					}
				}
			}
		);
		if(!unico){
			html += "</table>";
		}
		
		jQuery("#nusImputado").html(html);
	
		jQuery("#nombreImputado").val(jsonParam[index].nombreImputado);
		jQuery("#tipoDeSentencia").val(jsonParam[index].tipoSentencia);
		//Radio Buttons son 0-based por lo que el index de Sí = 0, index de No = 1
		if(jsonParam[index].esLesionado == 1){
			jQuery("input:radio[name=bLesion]:nth(0)").attr("checked",true);		
		} else if (jsonParam[index].esLesionado == 0){		
			jQuery("input:radio[name=bLesion]:nth(1)").attr("checked",true);
		}		
		jQuery("#fInicioSentencia").val(jsonParam[index].fechaInicio);
		jQuery("#fFinSentencia").val(jsonParam[index].fechaFin);
		jQuery("#puntosPorAcumular").val(jsonParam[index].puntosPorAcumular);	
	}
	
	function llenarObjeto(index){
		
		jsonParam[index].nus = jQuery("input:radio[name=nuevoNUS]:checked").val();	

		jsonParam[index].tipoSentencia = jQuery("#tipoDeSentencia").val();
		jsonParam[index].esLesionado = jQuery("input:radio[name=bLesion]:checked").val();
		jsonParam[index].fechaInicio = jQuery("#fInicioSentencia").val();
		jsonParam[index].fechaFin = jQuery("#fFinSentencia").val();
		jsonParam[index].puntosPorAcumular = jQuery("#puntosPorAcumular").val();	
		
		if(jsonParam[index].tipoSentencia <= 0){
			customAlert("Se debe seleccionar un tipo de sentencia.", "Validación");
			return false;
		}
		
		if(!validaCamposFecha(jsonParam[index].fechaInicio, jsonParam[index].fechaFin)){
			return false;
		}
		
		if(jsonParam[index].puntosPorAcumular <= 0){
			customAlert("Se debe ingresar el numero de puntos a acumular.", "Validación");
			return false;
		}
		
		jsonParam[index].todosLosDatos = true;
		return true;
	} 

	function obtenerDatosSentencia(index){
		
		llenarForma(index);
		
		
		
		jQuery("#divGenerarSentencia").dialog(
			{ 
		  		autoOpen:	false, 
				modal:		true, 
			  	title:		'Datos De La Sentencia',
			  	width:		'auto',
			  	height:		'auto',
				resizable: false,
				closeOnEscape: false,			  	
			  	buttons:	{
			  					"Aceptar": function() {
			  						if(llenarObjeto(index)){
										$(this).dialog("close");								
										llenarDatosSentencia();
									}		  				
			  	  				},
								"Cancelar": function() {
									$(this).dialog("close");
								}
			  				}
			}
		);	 
		
		
		$("#divGenerarSentencia").dialog( "open" );
		
	}

	/**
	* Consulta el NUS de los involucrados
	*/
	function consultarNUSDeLosInvolucrados(){
	 	bloquearPantalla();
		$.ajax({
			async: true,
			type: 'POST',
			url: '<%=request.getContextPath()%>/obtenerNUSDelInvolucreado.do',
			contentType: "application/json; charset=utf-8",
        	dataType: "json",
        	data: JSON.stringify(jsonParam),
			success: function(jsonObject){
				jsonParam = jsonObject;
			}
		});
	
	
	}


	//***********************************************************FUNCIONALIDAD PARA SENTENCIAS***********************************************************/

	
	
	</script>
</head>
<body>
	<form name="verMandamientoForm" id="verMandamientoForm"
		action="<%=request.getContextPath()%>/ConsultarContenidoArchivoDigital.do"
		method="post">
		<input type="hidden" name="documentoId" />
	</form>

	<div id="tabsprincipalconsulta">
		<ul>
			<li id="detalleAudiencia"><a href="#tabsconsultaprincipal-1">Solicitudes
					en Audiencia</a>
			</li>
			<li id="transcripcionAudiencia"><a
				href="#tabsconsultaprincipal-2">Estado de Intervinientes</a>
			</li>
			<li id="audioVideoAudiencia"><a href="#tabsconsultaprincipal-3">Mandamientos
					Judiciales</a>
			</li>
		</ul>

		<!--Comienza tabprincipal 1-->
		<div id="tabsconsultaprincipal-1">

			<table width="1000" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="139" align="right"><strong>Audiencia:</strong>
					</td>
					<td colspan="2"><strong> <input type="text"
							id="audienciaPJENS"
							style="width: 200px; border: 0; background: #DDD;"
							readonly="readonly" /> </strong>
					</td>
					<td width="76">&nbsp;</td>
					<td width="79">&nbsp;</td>
					<td width="89">&nbsp;</td>
					<td width="99" align="right"><strong>Fecha Inicio:</strong>
					</td>
					<td colspan="2"><input type="text" id="fechaAudienciaPJENS"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td width="111">&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><strong>Tipo de Audiencia:</strong>
					</td>
					<td colspan="2"><strong> <input type="text"
							id="tipoAudienciaPJENS"
							style="width: 200px; border: 0; background: #DDD;"
							readonly="readonly" /> </strong>
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right"><strong>Hora Inicio:</strong>
					</td>
					<td colspan="2"><input type="text" id="horaAudienciaPJENS"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><strong>N&uacute;mero de Caso:</strong>
					</td>
					<td colspan="2"><input type="text" id="numCasoPJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right"><strong>Fecha Fin:</strong>
					</td>
					<td colspan="2"><input type="text" id="fechaFinAudienciaPJENS"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><strong>N&uacute;mero de Causa:</strong>
					</td>
					<td colspan="2"><input type="text" id="numExpPJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right"><strong>Hora Fin:</strong>
					</td>
					<td colspan="2"><input type="text" id="horaFinAudienciaPJENS"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><strong>Car&aacute;cter:</strong>
					</td>
					<td colspan="2"><input type="text" id="caracterPJENS"
						style="width: 200px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right"><strong>Juez(ces):</strong>
					</td>
					<td colspan="2"><select id="juecesAudienciaPJENS"
						style="width: 150px;  border: 0; background:#DDD;"></select> <!--<input type="text" id="juecesAudienciaPJENS" style="width: 200px; border: 0; background: #DDD;" readonly="readonly" /></td>-->
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4" align="center"><strong>Transcripci&oacute;n
							de Audiencia:</strong>
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td colspan="3" align="center"><strong>Audio/Video:</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td width="96">
						<!--<strong>Enviado</strong>-->
					</td>
					<td width="117">
						<!-- <strong>Recibido</strong>-->
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right">&nbsp;</td>
					<td width="95">
							<!--<strong>Enviado</strong>-->
					</td>
					<td width="99">
							<!--<strong>Recibido</strong>-->
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">
					
						<!--<strong>Original</strong>-->
					</td>
					<td>
						<!--<input type="checkbox" id="chkTranscripcionEnviado"	disabled="disabled" checked="checked" />-->
					</td>
					<td>
						<!--<input type="submit" id="verTranscripcion" value="Ver">-->
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right">
						<!--<strong>Original</strong>-->
					</td>
					<td>
						<!--<input type="checkbox" id="chkAudioVideoEnviado" disabled="disabled" checked="checked" />-->
					</td>
					<td>
						<!--<input type="submit" id="verAudioVideo" value="Ver">-->
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4" rowspan="7" align="right">
						<table id="gridSolTranscripcionAudienciaPJENC"></table>
						<div id="pagerGridSolTranscripcionAudienciaPJENC"></div></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td colspan="4" rowspan="7" align="right">
						<table id="gridSolAudioVideoAudienciaPJENC"></table>
						<div id="pagerGridSolAudioVideoAudienciaPJENC"></div></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
					<td>&nbsp;</td>
					<td colspan="2" align="center"><input type="submit"
						name="ordenar" id="ordenar" value="Ordenar" class="btn_Generico"></td>
					<td align="right">&nbsp;</td>
					<td colspan="2">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>

		</div>
		<!--Termina Div Principal 2-->
		<div id="tabsconsultaprincipal-2">

			<table width="950" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="228" align="right"><strong>Audiencia:</strong>
					</td>
					<td width="200"><strong> <input type="text"
							id="audienciaIntervPJENC"
							style="width: 200px; border: 0; background: #DDD;"
							readonly="readonly" /> </strong>
					</td>
					<td width="92" align="right"><strong>Fecha Inicio:</strong>
					</td>
					<td width="163"><input type="text"
						id="fechaAudienciaIntervPJENC"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td colspan="2" align="right"><strong>Fecha Fin:</strong>
					</td>
					<td width="186"><input type="text"
						id="fechaFinAudienciaIntervPJENC"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right"><strong>Tipo:</strong>
					</td>
					<td><strong> <input type="text"
							id="tipoAudienciaIntervPJENC"
							style="width: 200px; border: 0; background: #DDD;"
							readonly="readonly" /> </strong>
					</td>
					<td align="right"><strong>Hora Inicio:</strong>
					</td>
					<td><input type="text" id="horaAudienciaIntervJENC"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
					<td colspan="2" align="right"><strong>Hora Fin:</strong>
					</td>
					<td><input type="text" id="horaFinAudienciaIntervPJENC"
						style="width: 150px; border: 0; background: #DDD;"
						readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td align="right"><strong>Juez(ces):</strong>
					</td>
					<td><select id="juecesAudienciaIntervPJENC"
						style="width: 200px;  border: 0; background:#DDD;"></select> <!--<input type="text" id="juecesAudienciaIntervPJENC" style="width: 200px; border: 0; background: #DDD;" readonly="readonly" />-->
					</td>
					<td align="right">&nbsp;</td>
					<td>&nbsp;</td>
					<td colspan="2" align="right">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td colspan="2">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="5" rowspan="6">
						<table id="gridProbResponsablePJENC"></table>
						<div id="pagerGridProbResponsablePJENC"></div></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td colspan="2">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td colspan="3"><input type="submit" id="guardar"
						value="Guardar" onclick="actualizarSituacionJuridica();" class="btn_Generico"/>
					</td>
					<td width="74">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>

		</div>
		<!--Termina Div Principal 2-->
		<!--Termina Div Principal 3-->
		<div id="tabsconsultaprincipal-3">

			<table width="950" border="0" cellspacing="0" cellpadding="0">
			    <tr>
			        <td width="228" align="right"><strong>Audiencia:</strong>
			        </td>
			        <td width="200"><strong> <input type="text"
			                id="audienciaMandamientosPJENC"
			                style="width: 200px; border: 0; background: #DDD;"
			                readonly="readonly" /> </strong>
			        </td>
			        <td width="92" align="right"><strong>Fecha Inicio:</strong>
			        </td>
			        <td width="150"><input type="text"
			            id="fechaAudienciaMandamientosPJENC"
			            style="width: 150px; border: 0; background: #DDD;"
			            readonly="readonly" />
			        </td>
			        <td colspan="2" align="right"><strong>Fecha Fin:</strong>
			        </td>
			        <td width="186"><input type="text"
			            id="fechaFinAudienciaMandamientosPJENC"
			            style="width: 150px; border: 0; background: #DDD;"
			            readonly="readonly" />
			        </td>
			    </tr>
			    <tr>
			        <td align="right"><strong>Tipo:</strong>
			        </td>
			        <td><strong> <input type="text"
			                id="tipoAudienciaMandamientosPJENC"
			                style="width: 200px; border: 0; background: #DDD;"
			                readonly="readonly" /> </strong>
			        </td>
			        <td align="right"><strong>Hora Inicio:</strong>
			        </td>
			        <td><input type="text" id="horaAudienciaMandamientosPJENC"
			            style="width: 150px; border: 0; background: #DDD;"
			            readonly="readonly" />
			        </td>
			        <td colspan="2" align="right"><strong>Hora Fin:</strong>
			        </td>
			        <td><input type="text" id="horaFinAudienciaMandamientosPJENC"
			            style="width: 150px; border: 0; background: #DDD;"
			            readonly="readonly" />
			        </td>
			    </tr>
			    <tr>
			        <td align="right"><strong>Juez(ces):</strong>
			        </td>
			        <td><select id="juecesAudienciaMandamientosPJENC"
			            style="width: 200px;  border: 0; background:#DDD;"></select> <!--<input type="text" id="juecesAudienciaMandamientosPJENC" style="width: 200px; border: 0; background: #DDD;" readonly="readonly" />-->
			        </td>
			        <td align="right">&nbsp;</td>
			        <td>&nbsp;</td>
			        <td colspan="2" align="right">&nbsp;</td>
			        <td>&nbsp;</td>
			    </tr>
			    <tr>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			        <td colspan="2">&nbsp;</td>
			        <td>&nbsp;</td>
			    </tr>
			    <tr>
			        <td>&nbsp;</td>
			        <td colspan="5" rowspan="6">
			            <table id="gridAudienciasResolutivosPJENC" width="100%"></table>
			            <div id="pagergGidAudienciasResolutivosPJENC"></div></td>
			        <td>&nbsp;</td>
			    </tr>
			    <tr>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			    </tr>
			    <tr>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			    </tr>
			    <tr>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			    </tr>
			    <tr>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			    </tr>
			    <tr>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			    </tr>
			    <tr>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			        <td>&nbsp;</td>
			        <td colspan="2">&nbsp;</td>
			        <td>&nbsp;</td>
			    </tr>
			    <tr>
			        <td align="right">&nbsp;</td>
			      <td align="center"><input type="submit" id="GenerarMandamiento"
			            value="Generar Mandamiento" class="btn_Generico"/></td>
			        <td colspan="3" align="center">
						<!--<input type="submit" id="consultarMandamiento" value="Consultar Mandamiento" />-->
			       	</td>
			      <td width="79"><input type="submit" id="cerrarMandamientos"
			            value="Cerrar Mandamientos" class="btn_Generico"/></td>
			        <td>&nbsp;</td>
			    </tr>
			</table>
			    

		</div>
		<!--Termina Div Principal 3-->
	</div>
	<!--Terminan Divs Principales-->

	<!--Div para la ventana modal que muestra el detalle del resolutivo de audiencia-->
	<div id="divAgregarResolutivo" style="display: none">
		<table width="331" cellspacing="0" cellpadding="0">
			<tr>
				<td width="329" align="left"><strong>Temporizador de
						Video:</strong>
				</td>
			</tr>
			<tr>
				<td align="left"><input type="text" id="tempVideo"
					disabled="disabled" />
				</td>
			</tr>
			<tr>
				<td align="left"><strong>Resolutivo:</strong>
				</td>
			</tr>
			<tr>
				<td align="left"><textarea rows="10" cols="50" id="resolutivo"
						disabled="disabled"></textarea>
				</td>
			</tr>
		</table>
	</div>
	
	<!--Div para ventana modal del tipo de mandamieto-->
	<div id="divTipoMandamiento" style="display: none">
	
		<table width="400" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="20">&nbsp;</td>
	        <td colspan="2" align="center">
	        	<strong>Tipo de Mandamiento Judicial</strong>
	       	</td>
	        <td width="20">&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td width="160" align="right">&nbsp;</td>
	        <td width="200">&nbsp;</td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<strong>Tipo de Mandamiento:</strong>
	       	</td>
	        <td>
	        	<select id="tipoMandamiento" style="width: 200px;">
	              <option value="0">-Seleccione-</option>
	            </select>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<strong>Nombre del Imputado:</strong>
	       	</td>
	        <td>
	        	<select id="nombreDelImputado" style="width: 200px;">
	          		<option value="0">-Seleccione-</option>
	        	</select>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="divEtiTipoSentencia">
		        	<strong>Tipo de Sentencia:</strong>
	            </div>
	        </td>
	        <td>
	        	<div id="divCbxTipoSentencia">
	                <select id="tipoSentencia" style="width: 200px;">
	                    <option value="0">-Seleccione-</option>
	                </select>
	        	</div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="divEtiFechaInicioSentencia">
	        		<strong>Fecha Inicio:</strong>
	        	</div>
	        </td>
	        <td>
	        	<div id="divFechaInicioSentencia">
	        		<input type="text" id="fechaInicioSentencia" style="width: 100px;"/>
	        	</div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="divEtiFechaFinSentencia">
	        		<strong>Fecha Fin:</strong>
	        	</div>
	       	</td>
	        <td>
	        	<div id="divFechaFinSentencia">
	        		<input type="text" id="fechaFinSentencia" style="width: 100px;"/>
	        	</div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">&nbsp;</td>
	        <td>&nbsp;</td>
	        <td>&nbsp;</td>
	      </tr>
	    </table>
	</div>
	
	<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center"><span id="divAlertTexto"></span></td>
			</tr>
		</table>
	</div>
	
	
	<div id="divGenerarSentencia" style="display: none">
		<fieldset>
			<legend>N&uacute;mero &Uacute;nico De Sentenciado</legend>
			<div id="nusImputado" ></div>
		</fieldset>
		<fieldset>
			<legend>Datos De La Sentencia</legend>
			<table width="400" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<strong>Nombre del Imputado:</strong>
	       	</td>
	        <td>
	        	<input type="text" id="nombreImputado" style="width: 200px;" readonly="readonly">
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
		        <strong>Tipo De Sentencia:</strong>
	        </td>
	        <td>
                <select id="tipoDeSentencia" style="width: 200px;">
                    <option value="0">-Seleccione-</option>
                </select>
	        </td>
	        <td>&nbsp;</td>
	      </tr>	      
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<strong>¿Presenta Lesiones?:</strong>
	       	</td>
	        <td>
	        	<input type="radio" name="bLesion" value="1"> Sí
				<input type="radio" name="bLesion" value="0" checked="checked"> No
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<strong>Fecha Inicio:</strong>
	        </td>
	        <td>
	        	<input type="text" id="fInicioSentencia" style="width: 100px;"/>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<strong>Fecha Fin:</strong>
	       	</td>
	        <td>
	        	<input type="text" id="fFinSentencia" style="width: 100px;"/>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<strong>Puntos Por Acumular:</strong>
	       	</td>
	        <td>
	        	<input type="text" id="puntosPorAcumular" style="width: 100px;"/>
	        </td>
	        <td>&nbsp;</td>
	      </tr>	      
	    </table>
		</fieldset>
		
	</div>
	
</body>
</html>