<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Ingresar Medidas Cautelares</title>


	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<!--css para ventanas-->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
					
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
		<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
			
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-es.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>

		<!--Hoja de estilo para los popups-->
      	<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
		<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
		<script	src="<%=request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
		<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>

		<script type="text/javascript">

		var idInvolucrado = "";
		rowid = "";
		var archivoDigitalId;
		var idDocumentoGlobal;
		var numexpedienteid = "<%=request.getParameter("numeroExpedienteId")%>";
		var op="";

		
		jQuery().ready(function () {
			
			rowid = '<%= request.getParameter("rowid")%>';
			op= "<%=request.getParameter("opOculta")%>";
			idVentana = "<%=request.getParameter("idVentana")%>";
			
			$( "#tabsprincipalconsulta" ).tabs();
			$("#medidaCautelarCmpFechaInicio").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			$("#medidaCautelarCmpFechaFin").datepicker({
				dateFormat: 'dd/mm/yy',
				yearRange: '1900:2100',
				changeMonth: true,
				changeYear: true,
				showOn: "button",
				buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
				buttonImageOnly: true			
			});
			
			cargarMedidasCautelares();
			cargarEncargadoSeguimiento();
			
			obtenerCatalogoPeriodicidad();
			
			
			//Codigo para obtener los datos de la pantalla
			$("#guardarMedida").click(guardarMedidaCautelar);
			
			$("#consultaMedida").click(consultaDocumento);

			$('#iMedidaCautelarWorkSheet').show();
			$('#iMedidaCautelarConsulta').hide();
			
			obtenerDatosMedidaCautelar(rowid);
			cargaGridDocumentosRelacionados();
			
			/*if(op=="1"){
				//eso solo sale en procu sino descomentar la alerta por si sale lo de  procu
				//$("#guardarMedida").hide();
				$("#cmpSeguimientolbl").hide();
				$("#cmpSeguimiento").hide();
				ocultaMuestraTabVisor("tabsconsultaprincipal-2",0);
				$("#guardarMedida").click(guardarMedidaCautelarProcu);
				$("#cancelarMedida").hide();
			}*/			
			
			$("#medidaCautelarCmpFechaInicio").attr("disabled","disabled");
			$("#medidaCautelarCmpFechaFin").attr("disabled","disabled");
			
		});

		/*
		* Función que carga el catálogo de periodicidad en el combo-box correspondiente cbxPeriodicidad
		*
		*/
		function obtenerCatalogoPeriodicidad(){
			$.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/consultarCatalogoPeriodicidad.do',
				data: '',
				dataType: 'xml',
				success: function(xml){
					$(xml).find('catPeriodicidad').each(function(){
						$('#cbxPeriodicidad').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
				}
			});
		}
		/*
		*Funcion que realiza la carga del combo de Medidas Cautelares
		*/
		function cargarMedidasCautelares() {
		  
			$.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoMedidasCautelares.do',
				data: '',
				dataType: 'xml',
				success: function(xml){
					$(xml).find('medidasCautelares').each(function(){
						$('#cbxMedidaCautelar').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
				}
			});
		}
		/*
		*Funcion que realiza la carga del combo de Encargados de seguimiento
		*/
		function cargarEncargadoSeguimiento() {
		  
			$.ajax({
				async: false,
				type: 'POST',
				url: '<%= request.getContextPath()%>/ConsultarCatalogoEncargadosSeguimiento.do',
				data: '',
				dataType: 'xml',
				success: function(xml){
					$(xml).find('encargadoSeguimiento').each(function(){
						$('#cbxEncargado').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					});
				}
			});
		}

		function obtenerDatosMedidaCautelar(rowid){
	    	
			$.ajax({
	    		type: 'POST',
	    		url: '<%=request.getContextPath()%>/ConsultarMedidasCautelaresInvolucradoPJENC.do',
	    		data: 'rowid='+rowid,
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){

	    			idInvolucrado=$(xml).find('medidaCautelar').find('involucrado').find('elementoId').text();
	    			medidaCautelarId = $(xml).find('medidaCautelar').find('documentoId').first().text();
				
					var nombre=$(xml).find('medidaCautelar').find('involucrado').find('nombresDemograficoDTO').find('nombre').first().text();
					document.getElementById('iMCNombre').value=nombre;
					var apellidoPaterno=$(xml).find('medidaCautelar').find('involucrado').find('nombresDemograficoDTO').find('apellidoPaterno').first().text();
					document.getElementById('iMCApellidoPaterno').value=apellidoPaterno;
					var apellidoMaterno=$(xml).find('medidaCautelar').find('involucrado').find('nombresDemograficoDTO').find('apellidoMaterno').first().text();
					document.getElementById('iMCApellidoMaterno').value=apellidoMaterno;

					var medidaCautelar = $(xml).find('medidaCautelar').find('valorTipoMedida').find('idCampo').text();
					tipoPeriodicidad = $(xml).find('medidaCautelar').find('valorPeriodicidad').find('idCampo').text();
					
					$('#cbxPeriodicidad').find("option[value='"+tipoPeriodicidad+"']").attr("selected","selected");
					if(medidaCautelar != ''  && medidaCautelar != null){
						$('#cbxMedidaCautelar').find("option[value='"+medidaCautelar+"']").attr("selected","selected");
						
						var guardaDef = $(xml).find('medidaCautelar').find('guardadoDefinitivo').text();
						deshabilitaCampos();
						if(guardaDef == 'true'){
							$('#iMedidaCautelarWorkSheet').hide();
							$('#iMedidaCautelarConsulta').show();
						}
					}
					
					var seguimiento=$(xml).find('medidaCautelar').find('seguimiento').text();
					if(seguimiento != '' && seguimiento != null){
						//$('#cbxEncargado').find("option[value='"+seguimiento+"']").attr("selected","selected");
						$('#cmpSeguimiento').val(seguimiento);
					}

					var fechaInicio=$(xml).find('medidaCautelar').find('strFechaInicio').text();
					if(fechaInicio != '' && fechaInicio != null){
						//var fechaTmp =fechaInicio.indexOf(":",0);
						//$("#medidaCautelarCmpFechaInicio").val(fechaInicio.substring(0,fechaTmp-3));
						$("#medidaCautelarCmpFechaInicio").val(fechaInicio);
					}	

					var fechaFin=$(xml).find('medidaCautelar').find('strFechaFin').text();
					if(fechaFin != '' && fechaFin != null){
						//var fechaTmp = fechaFin.indexOf(":",0); 
						//$("#medidaCautelarCmpFechaFin").val(fechaFin.substring(0,fechaTmp-3));
						$("#medidaCautelarCmpFechaFin").val(fechaFin);
					}
	    		}	
	    	});
			if(op=="1"){
				//eso solo sale en procu sino descomentar la alerta por si sale lo de  procu
				$("#cmpSeguimientolbl").hide();
				$("#cmpSeguimiento").hide();
				ocultaMuestraTabVisor("tabsconsultaprincipal-2",0);
				//$("#guardarMedida").click(guardarMedidaCautelarProcu);
				$("#cmpMCDesactivar").hide();
			}
			
		}
		
		medidaCautelarId  = "";

		/*
		*Funcion para guaradar la medida cautelar
		*/
		//OJO!!! guardarMedidaCautelarProcu
		function guardarMedidaCautelar(){

			if(validaParametrosDeGuardado() == true){
	
				var params = '';
				params += recuperoDatosMedidaCautelar();
				var formaId = '<%=Formas.MEDIDA_CAUTELAR.getValorId()%>'; 
				var numeroUnicoExpediente = numexpedienteid;
				
				//$("#guardarMedida").attr('disable','disable');
				
				$.ajax({								
			    	  type: 'POST',
			    	  url: '<%= request.getContextPath()%>/guardarMedidaCautelar.do?idInvolucrado='+idInvolucrado+'',
			    	  data: params,				
			    	  dataType: 'xml',
			    	  async: false,
			    	  success: function(xml){
			    		  //Si no tenía medida cautelar
			    		  
			    		  	window.parent.cargaGridInvolucradosCausaPJENC(numeroUnicoExpediente);
			    		  	
			    		  if(rowid.split(",")[1] == ""){
			    			  
			    				alertDinamico("Se guardó con éxito la medida cautelar");
			    				
			    				medidaCautelarId=$(xml).find('medidaCautelarForm').find('medidaCautelarId').text();
			    			  
								$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:5,posy:5,width:1140,height:400,title:"Solicitud de Medida Cautelar", type:"iframe"});
						        $.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/generarDocumentoSincrono.do?documentoId="+medidaCautelarId+"&formaId="+formaId+"&esconderArbol=1&numeroUnicoExpediente="+numeroUnicoExpediente+"' width='1140' height='400' />");
				    	 		
						        //TODO CERRAR VENTANA
						        //window.parent.cerrarVentanaMedidasCautelares(idVentana);
			    		  	}
			    		  //Si ya tenia medida cautelar
			    		  else{
			    			  medidaCautelarId=rowid.split(",")[1];
			    			  $.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:5,posy:5,width:1140,height:400,title:"Solicitud de Medida Cautelar", type:"iframe"});
						      $.updateWindowContent("iframewindowGenerarDocumento","<iframe src='<%= request.getContextPath() %>/generarDocumentoSincrono.do?documentoId="+medidaCautelarId+"&formaId="+formaId+"&esconderArbol=1&numeroUnicoExpediente="+numeroUnicoExpediente+"' width='1140' height='400' />");
				    		}
			    	 }
			    	});
			}
		}

		function fecha1EsMayor(fecha1, fecha2){
			
			var xDia=fecha1.substring(0, 2);
			var xMes=fecha1.substring(3, 5);			
			var xAnio=fecha1.substring(6);
			
			var yDia=fecha2.substring(0, 2);			
			var yMes=fecha2.substring(3, 5);
			var yAnio=fecha2.substring(6);
			
			if (xAnio > yAnio){
				return(true);
			}else{
				if (xAnio < yAnio){
					return(false);
				}
				else{
					if(xMes > yMes){
						return(true);
					}
					else{
						if(xMes<yMes){
							return(false);						
						}					
						else{
							if(xDia<=yDia){
								return(false);
							}
							else{							
								return(true);
							} 
						}
					}
				}
			}
		}
			
		/*
		*Funcion que valida que se hayan ingresado todos los campos correctamente
		*/
		function validaParametrosDeGuardado(){

			if( $('#cbxMedidaCautelar option:selected').val() == "-1"){
				alertDinamico("Seleccione un tipo de medida cautelar");
				return false;
			}
			//if($('#cmpSeguimiento').val() == ""){
			//	return false;
			//}
			if($('#medidaCautelarCmpFechaInicio').val() == ""){
				alertDinamico("Seleccione una fecha de inicio");
				return false;
			}

			//Falta validar las coherencia de las fechas
			if($('#medidaCautelarCmpFechaFin').val() == ""){
				alertDinamico("Seleccione una fecha de fin");
				return false;
			}
			
			if(fecha1EsMayor($('#medidaCautelarCmpFechaInicio').val(),$('#medidaCautelarCmpFechaFin').val())==true){
				alertDinamico("La fecha inicial debe ser menor o igual a la fecha final");
				return false;
			}
			
			if($('#descripcionMedidaCautelar').val() == ""){
				alertDinamico("Ingrese una descripción");
				return false;
			}
			if( $('#cbxPeriodicidad option:selected').val() == "-1"){
				alertDinamico("Seleccione una periodicidad");
				return false;
			}   
	        
		    return true;
		}


		function guardarMedidaCautelarProcu(){
			var params = '';
			params += recuperoDatosMedidaCautelar();
			var formaId = '<%=Formas.MEDIDA_CAUTELAR.getValorId()%>'; 
			var numeroUnicoExpediente = numexpedienteid;			
			if(validaParametrosDeGuardado() == true){				
				$.ajax({								
		    	  type: 'POST',
		    	  url: '<%= request.getContextPath()%>/guardarMedidaCautelar.do?idInvolucrado='+idInvolucrado+'',
		    	  data: params,				
		    	  dataType: 'xml',
		    	  success: function(xml){
		    		  alertDinamico("Se guardó con éxito la medida cautelar");
		    		  
		    	 }
		    	});
		    }			
		}

		function recuperoDatosMedidaCautelar(){
	        //Lugar de nacimiento esta pendiente ya que es un campo en BD pero en la pantalla vienen 3 campos, pais, estado y municipio
	        var parametros = '&medidaCautelar=' + $('#cbxMedidaCautelar option:selected').val();
	        parametros += '&seguimiento=' + $('#cmpSeguimiento').val();
	        parametros += '&fechaInicio=' + $('#medidaCautelarCmpFechaInicio').val();        
	        parametros += '&fechaFin=' + $('#medidaCautelarCmpFechaFin').val(); 

	        var activo = $(':radio[name=rbtMCDesactivar]:checked').val();
	        parametros += '&activo=' + activo;
	        parametros += '&descripcionMedidaCautelar='+  $('#descripcionMedidaCautelar').val();
	        parametros += '&periodicidad=' + $('#cbxPeriodicidad option:selected').val();
	        parametros += '&rowid='+rowid;
	        parametros += '&numeroExpediente='+numexpedienteid;
			return parametros;
		}

		function habilitaCampos(){
	        $('#cbxMedidaCautelar').attr("disabled","");
	        $('#cmpSeguimiento').attr("disabled","");
	        $('#medidaCautelarCmpFechaInicio').attr("disabled","");        
	        $('#medidaCautelarCmpFechaFin').attr("disabled",""); 
	        $('#cbxPeriodicidad').attr("disabled",""); 
	        $('#descripcionMedidaCautelar').attr("disabled",""); 
		}

		function deshabilitaCampos(){
	        $('#cbxMedidaCautelar').attr("disabled","disabled");
	        $('#cmpSeguimiento').attr("disabled","disabled");
	        $('#medidaCautelarCmpFechaInicio').attr("disabled","disabled");        
	        $('#medidaCautelarCmpFechaFin').attr("disabled","disabled"); 
	        $('#cbxPeriodicidad').attr("disabled","disabled"); 
	        $('#descripcionMedidaCautelar').attr("disabled","disabled"); 
	        
	        
		}

		function consultaDocumento(){
			document.frmDocumento.documentoId.value = medidaCautelarId;
			document.frmDocumento.submit();
		}


		/**
		*Funcion que carga el grid de documentos relacionados
		*/
		function cargaGridDocumentosRelacionados(){
			
			jQuery("#gridDocumentos").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultaGridDocumentosMedidasCautelares.do?numexpedienteid='+numexpedienteid, 
				datatype: "xml", 
				colNames:['Fecha de Elaboracion','Nombre'], 
				colModel:[ 					
				           	{name:'fechaElab',index:'fechaElab', width:150, align:'center'}, 
				           	{name:'Nombre',index:'Nombre', width:150, align:'center'}, 
						],
				pager: jQuery('#pagerGridDocumentos'),
				rowNum:10,
				autoWidth:false,
				width:700,
				rowList:[10,20,30],
				sortname: 'fechaElab',
				viewrecords: true,
				sortorder: "fechaElab",
				ondblClickRow: function(rowid) {
					archivoDigitalId=rowid;
					abrirPDF();
				} 
			}).navGrid('#pagerGridDocumentos',{edit:false,add:false,del:false});

			$("#gview_pagerGridDocumentos .ui-jqgrid-bdiv").css('height', '150px');
				
		}

		/*
		*Funcion que consulta el detalle del evento y llena 
		*los campos de la TAB Detalle evento
		*/
		function consultaDetalleEvento(){

			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/detalleSolicitudRecurso.do',
				data: 'idDocumento='+ idDocumentoGlobal, 
				async: false,
				dataType: 'xml',
				success: function(xml){
					  
	    				
	    				//$("#documento").val($(xml).find('nombreArchivo').first().text());

	    				archivoDigitalId=$(xml).find('archivoDigitalId').first().text();
	    				//expedienteId=$(xml).find('solicitudDTO').find('expedienteDTO').find('expedienteId').first().text();
	    				
				}
			});
		}
		
		function abrirPDF(){
			document.frmDoc.archivoDigitalId.value = archivoDigitalId;
			document.frmDoc.submit();
		}
	/**
	* Función que es invocada cuando se termina la creación del archivo digital de la medida
	*/
		function documentoGeneradoSincrono(documentoGeneradoId){
		
			
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/enviarMedidaCautelarASSP.do?medidaCautelarId='+documentoGeneradoId,
				data: '', 
				async: false,
				dataType: 'xml',
				success: function(xml){
					  
					document.frmDocumento.documentoId.value = documentoGeneradoId;
					document.frmDocumento.submit();
	    				
					$.closeWindow("iframewindowGenerarDocumento");
	    					    				
				}
			});
		
		}

		/************ FUNCION PARA OCULTAR-MOSTRAR LOS TABS DEL VISOR***************/
		function ocultaMuestraTabVisor(claseTab,bandera)
		{
			if(parseInt(bandera)==0)//oculta
			{
				$("."+claseTab).hide();
			}
			else///muestra
			{
				$("."+claseTab).show();
			}
		}
		
		</script>
	</head>
<body>
<!-- div para el alert dinamico -->
<div id="dialog-Alert" style="display: none">
	<table align="center">
		<tr>
        	<td align="center">
            	<span id="divAlertTexto"></span>
            </td>
        </tr>
     </table>              
</div>  

	<div id="tabsprincipalconsulta">
		<ul>
			<li><a href="#tabsconsultaprincipal-1">Medidas cautelares</a></li>
			<li class="tabsconsultaprincipal-2"><a href="#tabsconsultaprincipal-2">Documentos</a></li>
		</ul>
		
		<div id="tabsconsultaprincipal-1">
			<input type="hidden" name="xml" id="xml" />
			
			<table border="0">
				<tr valign="top">
					<td>
						<table id="iMedidaCautelarViewHeader" width="100%" border="0">
							<tr>
								<td>
									<table border="0">
										<tr valign="top">
											<td width="30%">
											</td>
											
											<td width="40%">
												<table style="border: 0; background:#DDD;" width="100%" height="143" cellpadding="0"  cellspacing="0" class="celda2">
							                        <tr>
							                          <td width="60%" height="30" align="right">Nombre:</td>
							                          <td width="29%"><input type="text" value="" readonly="readonly" title="Escribir nombre" size="50" maxlength="40" id="iMCNombre" style="background:#DDD;border: 0;" readonly="readonly"/></td>
							                        </tr>
							                        <tr>
							                          <td width="60%" height="28" align="right">Apellido paterno:</td>
							                          <td width="29%" height="28"><input type="text" value="" readonly="readonly" readonly="readonly" size="50" maxlength="40" id="iMCApellidoPaterno" style="background:#DDD;border: 0;" readonly="readonly"/></td>
							                        </tr>
							                        <tr>
							                          <td width="60%"  height="35" align="right">Apellido materno:</td>
							                        <td height="35"><input type="text" value="" readonly="readonly"  readonly="readonly" size="50" maxlength="40" id="iMCApellidoMaterno" style="background:#DDD;border: 0;" readonly="readonly"/></td>
							                        </tr>
							                    </table>
											</td>
										</tr>
									</table>
								</td>
						</table>
					</td>
					<td>
						<table border="0">
							<tr>
								<td width="20%" align="right">Medida cautelar:</td>
								<td width="20%">
						          <select id="cbxMedidaCautelar">
									<option value="-1">-Seleccione-</option>
						          </select>
						        </td>
							</tr>
							<tr>
								<td width="20%" align="right" id="cmpSeguimientolbl"><span style="display: none;">Encargado de seguimiento:</span></td>
								<td width="20%">
								<input type="text" id="cmpSeguimiento" size="40" style="display: none;">					
						        </td>
						    </tr>
							<tr>
								<td width="20%" align="right">Fecha de inicio:</td>
								<td><input type="text" id="medidaCautelarCmpFechaInicio" style="width: 180px;" /></td>
							</tr>
							<tr>
								<td width="20%" align="right">Fecha de fin:</td>
								<td><input type="text" id="medidaCautelarCmpFechaFin" style="width: 180px;" /></td>
							</tr>
							<tr>
								<td width="20%" align="right">Periodicidad:</td>
								<td width="20%"><select id="cbxPeriodicidad">
									<option value="-1">-Seleccione-</option>
						          </select>
						        </td>
							</tr>
							<tr>
								<td width="20%" align="right">Descripci&oacute;n:</td>
								<td><textarea style="width: 200px;" id="descripcionMedidaCautelar"></textarea></td>
							</tr>
							
						</table>
					</td>
				</tr>
				<tr>	
		          <td id="cmpMCDesactivar" height="25" colspan="2" align="center">
					<!--<div id="cancelarMedida" style="display: none;">-->
		          		<strong>&iquest;Cancelar medida cautelar?:</strong>
						<input type="radio" name="rbtMCDesactivar" id="rbtMcDesactivarNo" value="0" checked="checked"/> 
						<label for="rbtMcDesactivarNo"><strong>No</strong></label>
			            <input type="radio" name="rbtMCDesactivar" id="rbtMcDesactivarSi" value="1" />
						<label for="rbtMcDesactivarSi"><strong>Si</strong></label>
					<!--	</div>-->
		          </td>
				</tr>
				<tr valign="top">
					<td>
					  <table id="iMedidaCautelarWorkSheet" width="100%"  border="0">
						<tr valign="top">
							<td align="center">
								<input type="button" value="Continuar medida cautelar" id="guardarMedida" class="btn_Generico"/>
							</td>
						</tr>
					  </table>
					</td>
					<td>
					  <table id="iMedidaCautelarConsulta" width="100%"  border="0">
						<tr valign="top">
							<td align="center">
								<input type="button" value="Consultar documento digital" id="consultaMedida" class="btn_Generico"/>
							</td>
						</tr>
					  </table>
					</td>
				</tr>
			</table>
	
			<form name="frmDocumento" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
				<input type="hidden" name="documentoId" />
			</form>
	</div>

	<div id="tabsconsultaprincipal-2">
	
		<table width="700" border="0">
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<div>
						<table id="gridDocumentos" ></table>
						<div id="pagerGridDocumentos" style="width: 300"></div>
					</div>
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
	</div>
	
</div>

<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
	<input type="hidden" name="archivoDigitalId" value=""/>
</form>
					
</body>
</html>