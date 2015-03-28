<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ingresar juez manualmente</title>
		
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<!--Scrip para el idioma del calendario-->
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	
	<script type="text/javascript">
	    
	$(document).ready(function() {

		$('#internarCentroSalud').change(ocultaTabCentroSalud);
		$('#arrestoDomiciliario').change(ocultaTabArrestoDomiciliario);	
		$('#suspenderDerechos').change(ocultaTabSuspenderDerechos);
		$('#separacionDomicilio').change(ocultaTabSeparacionDomicilio);
		$('#localizadoresElectronicos').change(ocultaTabLocalizadoresElectronicos);
		$('#salidaTerritorial').change(ocultaTabSalidaTerritorial);
		$('#presentacionesPeriodicas').change(ocultaTabPresentacionesPeriodicas);
		$('#prohibirConvivencia').change(ocultaTabProhibirConvivencia);
		$('#someterVigilancia').change(ocultaTabSometerVigilancia);
		$('#prisionPreventiva').change(ocultaTabPrisionPreventiva);	
		$('#fechaInicio').datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		$('#fechaFin').datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		$("#txtFecha").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		$("#tabsprincipalconsulta" ).tabs();
		$("#tabsprincipalconsultaDos").tabs();
		$("#liDomNotif").hide();
		$("#liDom").hide();
		$("#tabs-2").hide();
		$("#rowDomicilioNotif").hide();		
		$('#cbxPais').val(10);
		$('#cbxPaisSeparacion').val(10);
		$("#cbxEntFederativaSeparacion").change(onSelectChangeEntFedSeparacion);
		$("#cbxCiudadSeparacion").change(onSelectChangeCiudadMunicipioTipoAsentamientoSeparacion);
		$("#cbxDelegacionMunicipioSeparacion").change(onSelectChangeCiudadMunicipioTipoAsentamientoSeparacion);
		$("#cbxTipoAsentamientoSeparacion").change(onSelectChangeCiudadMunicipioTipoAsentamientoSeparacion);
		
							
		onSelectChangePais();
		onSelectChangePaisSeparacion();
		hideControlsSeparacion("no");			//Funciones para esconder los controles 
		cargaPaisesSeparacion();	
		$( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
		.removeClass( "ui-corner-all ui-corner-top" )
		.addClass( "ui-corner-bottom" );
		ocultaTabsInferiores();
		
		jQuery("#gridDetalleProbablesResponsablesDos").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploInvoludradoCalidad.xml', 
			datatype: "xml", 
			colNames:['Imputado','Calidad'], 
			colModel:[ 	{name:'Imputado',index:'imputado', width:200}, 
						{name:'Calidad',index:'calidad', width:100}
					],
			pager: jQuery('#pagerDos2'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc",
			onSelectRow: function(rowid) {
				clickRenglonInvolucrados(rowid);
				}
		}).navGrid('#pagerDos2',{edit:false,add:false,del:false});

		$("#gview_gridDetalleProbablesResponsablesDos .ui-jqgrid-bdiv").css('height', '100px');
		
		jQuery("#gridDetalleDelitos").jqGrid({ 
			url:'<%=request.getContextPath()%>/EjemploDelitos.xml', 
			datatype: "xml", 
			colNames:['Delito'], 
			colModel:[ 	{name:'Delito',index:'delito', width:80}
					],
			pager: jQuery('#pagerTres2'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'detalle',
			viewrecords: true,
			sortorder: "desc"
		}).navGrid('#pagerTres2',{edit:false,add:false,del:false});
		
		$("#gview_gridDetalleDelitos .ui-jqgrid-bdiv").css('height', '100px');
	});

	function ocultaTabsInferiores (){

		 $("#tabsInternar").hide();
		 $("#tabsconsultaprincipalDos-1").hide();
		 $("#tabsArresto").hide();
		 $("#tabsconsultaprincipalDos-2").hide();
		 $("#tabsSuspender").hide();
		 $("#tabsconsultaprincipalDos-3").hide();
		 $("#tabsSeparacion").hide();
		 $("#tabsconsultaprincipalDos-4").hide();
		 $("#tabsLocalizadores").hide();
		 $("#tabsconsultaprincipalDos-5").hide();
		 $("#tabsProhibirSalida").hide();
		 $("#tabsconsultaprincipalDos-6").hide();
		 $("#tabsPresentaciones").hide();
		 $("#tabsconsultaprincipalDos-7").hide();
		 $("#tabsProhibirConvivencia").hide();
		 $("#tabsconsultaprincipalDos-8").hide();
		 $("#tabsSometer").hide();
		 $("#tabsconsultaprincipalDos-9").hide();
		 $("#tabsPrision").hide();
		 $("#tabsconsultaprincipalDos-10").hide();
		 


		}

	 function ocultaTabCentroSalud(){

		 if(!$('#internarCentroSalud').is(':checked')){
			 $("#tabsInternar").hide();
			 $("#tabsconsultaprincipalDos-1").hide();
			 
			 }
		 else{

			 $("#tabsInternar").show();
			 $("#tabsconsultaprincipalDos-1").show();
			 
			 }		 		 		
		  }

	 function ocultaTabArrestoDomiciliario(){

		 if(!$('#arrestoDomiciliario').is(':checked')){
			 $("#tabsArresto").hide();
			 $("#tabsconsultaprincipalDos-2").hide();
			
			 }
		 else{

			 $("#tabsArresto").show();
			 $("#tabsconsultaprincipalDos-2").show();
			 }		 		 		
		  }
	 function ocultaTabSuspenderDerechos(){

		 if(!$('#suspenderDerechos').is(':checked')){
			 $("#tabsSuspender").hide();
			 $("#tabsconsultaprincipalDos-3").hide();
			 }
		 else{

			 $("#tabsSuspender").show();
			 $("#tabsconsultaprincipalDos-3").show();
			 }		 		 		
		  }
	 function ocultaTabSeparacionDomicilio(){

		 if(!$('#separacionDomicilio').is(':checked')){
			 $("#tabsSeparacion").hide();
			 $("#tabsconsultaprincipalDos-4").hide();
			 }
		 else{

			 $("#tabsSeparacion").show();
			 $("#tabsconsultaprincipalDos-4").show();
			 }		 		 		
		  }
	 function ocultaTabLocalizadoresElectronicos(){

		 if(!$('#localizadoresElectronicos').is(':checked')){
			 $("#tabsLocalizadores").hide();
			 $("#tabsconsultaprincipalDos-5").hide();
			 }
		 else{

			 $("#tabsLocalizadores").show();
			 $("#tabsconsultaprincipalDos-5").show();
			 }		 		 		
		  }
	 function ocultaTabSalidaTerritorial(){

		 if(!$('#salidaTerritorial').is(':checked')){
			 $("#tabsProhibirSalida").hide();
			 $("#tabsconsultaprincipalDos-6").hide();
			 }
		 else{

			 $("#tabsProhibirSalida").show();
			 $("#tabsconsultaprincipalDos-6").show();
			 }		 		 		
		  }
	 function ocultaTabPresentacionesPeriodicas(){

		 if(!$('#presentacionesPeriodicas').is(':checked')){
			 $("#tabsPresentaciones").hide();
			 $("#tabsconsultaprincipalDos-7").hide();
			 }
		 else{

			 $("#tabsPresentaciones").show();
			 $("#tabsconsultaprincipalDos-7").show();
			 }		 		 		
		  }
	 function ocultaTabProhibirConvivencia(){

		 if(!$('#prohibirConvivencia').is(':checked')){
			 $("#tabsProhibirConvivencia").hide();
			 $("#tabsconsultaprincipalDos-8").hide();
			 }
		 else{

			 $("#tabsProhibirConvivencia").show();
			 $("#tabsconsultaprincipalDos-8").show();
			 }		 		 		
		  }
	 function ocultaTabSometerVigilancia(){

		 if(!$('#someterVigilancia').is(':checked')){
			 $("#tabsSometer").hide();
			 $("#tabsconsultaprincipalDos-9").hide();
			 }
		 else{

			 $("#tabsSometer").show();
			 $("#tabsconsultaprincipalDos-9").show();
			 }		 		 		
		  }
		  function ocultaTabPrisionPreventiva(){

				 if(!$('#prisionPreventiva').is(':checked')){
					 $("#tabsPrision").hide();
					 $("#tabsconsultaprincipalDos-10").hide();
					 }
				 else{

					 $("#tabsPrision").show();
					 $("#tabsconsultaprincipalDos-10").show();
					 }		 		 		
				  }

		  /**
			  * Muestra u oculta los combo box's o cajas de texto dependiendo
			  * de el país seleccionado tiene o no, entidades federativas.
			  * Esto para el domicilio.
			  */	
			  function hideControlsSeparacion(existenEntidadesSeparacion) {
				  
				if(existenEntidadesSeparacion == "si" ){
				  $('#cbxEntFederativaSeparacion').show();
				  $('#cbxCiudadSeparacion').show();
				  $('#cbxDelegacionMunicipioSeparacion').show();
				  $('#cbxAsentamientoColoniaSeparacion').show();
				  $('#cbxTipoAsentamientoSeparacion').show();
				  $('#cbxTipoCalleSeparacion').show();
				  $('#codigoPostalButtonSeparacion').show();
				  $('#entidadFederativaSeparacion').hide();
				  $('#areaDelegacionMunicipioSeparacion').hide();
				  $('#areaCiudadSeparacion').hide();
				  $('#areaColoniaSeparacion').hide();
				  $('#areaAsentamientoSeparacion').hide();
				  $('#areaTipoCalleSeparacion').hide();
				}
				else{
				  $('#cbxEntFederativaSeparacion').hide();
				  $('#cbxCiudadSeparacion').hide();
				  $('#cbxDelegacionMunicipioSeparacion').hide();
				  $('#cbxAsentamientoColoniaSeparacion').hide();
				  $('#cbxTipoAsentamientoSeparacion').hide();
				  $('#cbxTipoCalleSeparacion').hide();
				  $('#codigoPostalButtonSeparacion').hide();
				  $('#entidadFederativaSeparacion').show();
				  $('#areaDelegacionMunicipioSeparacion').show();
				  $('#areaCiudadSeparacion').show();
				  $('#areaColoniaSeparacion').show();
				  $('#areaAsentamientoSeparacion').show();
				  $('#areaTipoCalleSeparacion').show();
				}			
			  }
			
			 /**
			  * Limpia los combos:Ent Federativa, Ciudad, DelegacionMunicipio, AsentamientoColonia
			  * Tipo de Asentamiento y Tipo de Calle, para el Domicilio
			  */  
			  function cleanAllCombosSeparacion(){
				  
				$('#cbxEntFederativaSeparacion').empty();
				$('#cbxEntFederativaSeparacion').append('<option value="-1">-Seleccione-</option>');	//Por default seleccione
				$('#cbxCiudadSeparacion').empty();
				$('#cbxCiudadSeparacion').append('<option value="-1">-Seleccione-</option>');	
				$('#cbxDelegacionMunicipioSeparacion').empty();
				$('#cbxDelegacionMunicipioSeparacion').append('<option value="-1">-Seleccione-</option>');
				$('#cbxAsentamientoColoniaSeparacion').empty();
				$('#cbxAsentamientoColoniaSeparacion').append('<option value="-1">-Seleccione-</option>');
				$('#cbxTipoAsentamientoSeparacion').empty();
				$('#cbxTipoAsentamientoSeparacion').append('<option value="-1">-Seleccione-</option>');
				$('#cbxTipoCalleSeparacion').empty();
				$('#cbxTipoCalleSeparacion').append('<option value="-1">-Seleccione-</option>');
			  }
			
			 /**
			  * Limpia los combos que dependen del combo entidad federativa, para 
			  * el domicilio
			  */  
			  function cleanCombosDependsEntidadFedSeparacion(){
				$('#cbxCiudadSeparacion').empty();
				$('#cbxCiudadSeparacion').append('<option value="-1">-Seleccione-</option>');
				$('#cbxDelegacionMunicipioSeparacion').empty();
				$('#cbxDelegacionMunicipioSeparacion').append('<option value="-1">-Seleccione-</option>');
				$('#cbxAsentamientoColoniaSeparacion').empty();
				$('#cbxAsentamientoColoniaSeparacion').append('<option value="-1">-Seleccione-</option>');
			  }
											

			 /**
			  * Limpia los combos que dependen del combo Ciudad, para 
			  * el domicilio
			  */  	
			  function cleanCombosDependsCiudadSeparacion(){
				$('#cbxDelegacionMunicipioSeparacion').empty();
				$('#cbxDelegacionMunicipioSeparacion').append('<option value="-1">-Seleccione-</option>');	
				$('#cbxAsentamientoColoniaSeparacion').empty();
				$('#cbxAsentamientoColoniaSeparacion').append('<option value="-1">-Seleccione-</option>');
			  }
			
			 /**
			  * Limpia los combos que dependen del combo Delegacion/Municipio, para 
			  * el domicilio
			  */ 	
			  function cleanCombosDependsDelegMunSeparacion(){
				$('#cbxAsentamientoColoniaSeparacion').empty();
				$('#cbxAsentamientoColoniaSeparacion').append('<option value="-1">-Seleccione-</option>');
			  }

			
			 /**
			  * Limpia los combos que dependen de la consulta por codigo postal
			  * para el domicilio
			  */ 	
			  function cleanAllCombosCodigoPostalSeparacion(){
				$('#cbxEntFederativaSeparacion').empty();
				$('#cbxCiudadSeparacion').empty();
				$('#cbxDelegacionMunicipioSeparacion').empty();
				$('#cbxAsentamientoColoniaSeparacion').empty();
			  }

			//*****FUNCIONES PARA EL CU INGRESAR DOMICILIO*********//

			/**
			*Funcion que limpia el combo box de asentamiento colonia
			*/
			function cleanComboAsentColoniaSeparacion(){
				$('#cbxAsentamientoColoniaSeparacion').empty();
				$('#cbxAsentamientoColoniaSeparacion').append('<option value="-1">-Seleccione-</option>');
			}

			
			/*
			*Funcion que realiza la carga del combo de Paises
			*/
			function cargaPaisesSeparacion() {
				 
				$.ajax({
					type: 'POST',
					url: '<%= request.getContextPath()%>/cargarPaises.do',
					data: '',
					dataType: 'xml',
					async: false,
					success: function(xml){
						$(xml).find('catPaises').each(function(){
							$('#cbxPaisSeparacion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						});
					}
				});
			}

			
			/**
			* Si existe un cambio en el combo de paises se realiza la consulta de 
			* entidades federativas, y si la consulta es NO vacía se leventa la 
			* bandera para mostrar los combo box. Esto para el domicilio
			*/ 	
			function onSelectChangePaisSeparacion() {
				  
				var selected = $("#cbxPaisSeparacion option:selected");
				var existenEntidadesSeparacion = "no";
					
						
				cleanAllCombosSeparacion();							//Limpia todos los combo box´s		
				hideControlsSeparacion(existenEntidadesSeparacion);				//Si la opcion seleccionada no contiene entidades federativas se esconden los cbx's
				$.ajax({
					async: false,									// la accion cargar estados y llena el combo con la consulta
					type: 'POST',
					url: '<%= request.getContextPath()%>/cargarEntFederativas.do',
					data: 'glCatPaisId=' + selected.val(),	//Parametro para hacer la consulta de Entidades por Id del País
					dataType: 'xml',
					success: function(xml){
						$(xml).find('catEntidadesFed').each(function(){
							$('#cbxEntFederativaSeparacion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
							existenEntidadesSeparacion = "si";	
						});
						hideControlsSeparacion(existenEntidadesSeparacion);	
						cargaTipoAsentSeparacion();
						cargaTipoCalleSeparacion();
					}
				});
			}

			/**
			* Si existe un cambio en el combo de Entidades federativas se realiza la consulta de 
			* entidades Ciudades. Esto para el domicilio
			*/ 	
			function onSelectChangeEntFedSeparacion() {
					  
				var selected = $("#cbxEntFederativaSeparacion option:selected");
								
				cleanCombosDependsEntidadFedSeparacion();
				$.ajax({											// la accion cargar cidades
					async: false,
					type: 'POST',
					url: '<%= request.getContextPath()%>/cargarCiudades.do',
					data: 'glCatEntidadFederativaId=' + selected.val(), 	//hace la consulta por el id de la Entidad Federativa
					dataType: 'xml',
					success: function(xml){
						$(xml).find('catCiudades').each(function(){											//LLena el comboBox con la consulta
							$('#cbxCiudadSeparacion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						});
					}
				});
				onSelectChangeCiudad();				
			}


			/**
			* Si existe un cambio en el combo de Ciudades se realiza la consulta de 
			* Delegaciones/Municipios.
			*/ 	
			function onSelectChangeCiudadSeparacion() {
					    
				var selected = $("#cbxEntFederativaSeparacion option:selected");
				  
				//cleanCombosDependsCiudad();
				$.ajax({
					async: false,														// Ejecuta la accion cargar Delegacion/Municipio
					type: 'POST',
					url: '<%= request.getContextPath()%>/cargarDelgMun.do',
					data: 'glCatEntidadFederativaId=' + selected.val(), 				//hace la consulta por el id de la Entidad Federativa
					dataType: 'xml',
					success: function(xml){
						$(xml).find('catDelegMun').each(function(){				//LLena el comboBox con la consulta
							$('#cbxDelegacionMunicipioSeparacion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');	
						});
					}	
				});
			}	  

			/**
			* Si existe un cambio en el combo de Ciudade, delegacion, o tipo de asentamiento
			* se realiza la consulta de por medio de esos tres id´s 
			*/ 	
			function onSelectChangeCiudadMunicipioTipoAsentamientoSeparacion() {

				
				cleanComboAsentColoniaSeparacion();
				$.ajax({
					async: false,														// Ejecuta la accion cargar Delegacion/Municipio
					type: 'POST',
					url: '<%= request.getContextPath()%>/cargarAsentColonia.do',
					data: '',
					dataType: 'xml',
					success: function(xml){
						$(xml).find('catAsentColonia').each(function(){			//LLena el comboBox con la consulta
							$('#cbxAsentamientoColoniaSeparacion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');	
						});
					}
				});
			}

			
			/**
			* Si existe un cambio en el combo de Ciudades se realiza la consulta de 
			* Delegaciones/Municipios.
			*/ 	
			function onSelectChangeDelgMunSeparacion() {
				  
				var selected = $("#cbxDelegacionMunicipioSeparacion option:selected");
				    
				cleanCombosDependsDelegMunSeparacion();
				$.ajax({														// Ejecuta la accion cargar Delegacion/Municipio
					async: false,
					type: 'POST',
					url: '<%= request.getContextPath()%>/cargarAsentColonia.do',
					data: 'glDelgMunId=' + selected.val(),						//Realiza la consulta de acuerdo al id de la delegacion o municipio
					dataType: 'xml',
					success: function(xml){
					  $(xml).find('catAsentColonia').each(function(){			//LLena el comboBox con la consulta
						$('#cbxAsentamientoColoniaSeparacion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');	
					   });
					}
				});
			}

			
		
				
			/*
			*Funcion que realiza la carga del combo de tipo de asentamiento	
			*/
			function cargaTipoAsentSeparacion() {	
			  
				$.ajax({
					type: 'POST',
					url: '<%= request.getContextPath()%>/cargarTipoAsent.do',
					data: '',
					dataType: 'xml',
					success: function(xml){
						$(xml).find('catTipoAsentamiento').each(function(){
							$('#cbxTipoAsentamientoSeparacion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						});  
				  	}
				});
			  }

			
			 /*
			  *Funcion que realiza la carga del combo de tipo de calle,
			  *para el domicilio 
			  */
			  function cargaTipoCalleSeparacion() {
				
				$.ajax({
				  type: 'POST',
				  url: '<%= request.getContextPath()%>/cargarTiposDeCalle.do',
				  data: '',
				  dataType: 'xml',
				  success: function(xml){
					var option;
					$(xml).find('catTipoCalle').each(function(){
						$('#cbxTipoCalleSeparacion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
					 });			
				  }
				});
			  }

		
	</script>
	
	<style>
	#tabs {} 
	.tabs-bottom { height:360px ; position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 330px; overflow: auto; } 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	</style>	
	
		
</head>
<body>

<div id="tabsprincipalconsulta">
	<ul>
		<li><a href="#tabsconsultaprincipal-1">Expediente</a></li>
		<li><a href="#tabsconsultaprincipal-2">Medidas Cautelares</a></li>
	</ul>
	
	<div id="tabsconsultaprincipal-1" style="height: 400">
	
		<table width="1000" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="350" valign="top">
					<table width="349" border="0" cellspacing="5" cellpadding="0">
					  <tr>
				      	<td width="45%" ><strong>Caso:</strong> 0002</td>
				        <td width="55%">&nbsp;</td>
				      </tr>
				      <tr>
				      	<td><strong>Expediente:</strong> 002219</td>
				        <td>&nbsp;</td>
				      </tr>
				      <tr>
				      	<td align="left"><input id="btnASolicitarTraslado" type="button" value="Solicitar Traslado" class="btn_Generico"/></td>
				      	<td align="left"><input id="btnSegGarEconomica" type="button" value="Dar seguimiento a &#10; garant&iacute;a econ&oacute;mica" style="width: 130px;" class="btn_Generico"/></td>
				      </tr>
					</table>	
				</td>
				<td width="300" >
					<table id="gridDetalleProbablesResponsablesDos"></table>
					<div id="pagerDos2" width="300"></div>
				</td>
				<td width="50" >&nbsp;
					
				</td>
				<td width="300" valign="top" id="tdGridDelitos">
					<table id="gridDetalleDelitos"></table>
					<div id="pagerTres2" width="200"></div>
				</td>
			</tr>
			<tr>
			  <td valign="top">&nbsp;</td>
			  <td >&nbsp;</td>
			  <td >&nbsp;</td>
			  <td valign="top" id="tdGridDelitos2">&nbsp;</td>
		  </tr>
		
		</table>
     </div>
     
   	  <div  id="tabsconsultaprincipal-2" style="height: 400">
   	  	
   	  		  <table width="1000px" cellspacing="0" cellpadding="0">
   	  		
        	<tr>
			  <td colspan="4" valign="top">&nbsp;</td>
		  </tr>
			<tr>
			  <td colspan="4" valign="top" align="center">Ingresar Persona a Medida Cautelar</td>
		  </tr>
  <tr>
    <td colspan="2" rowspan="6"><table width="530px" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="5" align="center">Datos Generales</td>
        </tr>
      <tr>
        <td width="113">&nbsp;</td>
        <td width="46">&nbsp;</td>
        <td width="94">&nbsp;</td>
        <td width="56">&nbsp;</td>
        <td width="219">&nbsp;</td>
      </tr>
      <tr>
        <td align="right">Periodo:</td>
        <td align="right">Inicio:</td>
        <td><input type="text" id="fechaInicio"  size="15"/></td>
        <td>Termino:</td>
        <td><input type="text" id="fechaFin"  size="15"/></td>
      </tr>
      <tr>
        <td valign="top">&nbsp;</td>
        <td colspan="4">&nbsp;</td>
      </tr>
      <tr>
        <td valign="top" align="right">Motivo:</td>
        <td colspan="4"><textarea name="txtAreaDescripcionInmueble" cols="43" rows="3" id="txtAreaDescripcionInmueble"></textarea></td>
      </tr>
    </table></td>
    <td colspan="2" align="center">Tipo de Medida Cautelar</td>
    </tr>
  <tr>
    <td width="289">&nbsp;</td>
    <td width="180">&nbsp;</td>
    </tr>
  <tr>
    <td width="289"><input type="checkbox" id="internarCentroSalud"/>
      Internar a centro de salud</td>
    <td width="180"><input type="checkbox" id="arrestoDomiciliario" />
      Arresto domiciliario</td>
    </tr>
  <tr>
    <td><input type="checkbox" id="suspenderDerechos"/>
      Suspender derechos</td>
    <td><input type="checkbox"  id="separacionDomicilio"/>
      Separaci&oacute;n de domicilio</td>
    </tr>
  <tr>
    <td><input type="checkbox" id="localizadoresElectronicos"/>
      Localizadores electr&oacute;nicos</td>
    <td><input type="checkbox" id="salidaTerritorial" />
      Prohibir salida territorial</td>
    </tr>
  <tr>
    <td><input type="checkbox" id="presentacionesPeriodicas"/>
      Presentaciones peri&oacute;dicas</td>
    <td><input type="checkbox" id="prohibirConvivencia" />
      Prohibir convivencia</td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input type="checkbox" id="someterVigilancia" />
      Someter a vigilancia</td>
    <td><input type="checkbox" id="prisionPreventiva"/>
      Prisi&oacute;n preventiva</td>
    </tr>
  <tr>
    <td width="311">&nbsp;</td>
    <td width="218">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
  <tr>
    <td colspan="4">
     </td>
  </tr>
  </table>
   		
   		   <div id="tabsprincipalconsultaDos" class="tabs-bottom">
    
				<ul>
					<li id="tabsInternar"><a href="#tabsconsultaprincipalDos-1">Internar a centro de salud</a></li>
					<li id="tabsArresto"><a href="#tabsconsultaprincipalDos-2">Arresto domiciliario</a></li>
					<li id="tabsSuspender"><a href="#tabsconsultaprincipalDos-3">Suspender derechos</a></li>
					<li id="tabsSeparacion"><a href="#tabsconsultaprincipalDos-4">Separaci&oacute;n de domicilio</a></li>
					<li id="tabsLocalizadores"><a href="#tabsconsultaprincipalDos-5">Localizadores electr&oacute;nicos</a></li>
					<li id="tabsProhibirSalida"><a href="#tabsconsultaprincipalDos-6">Prohibir salida territorial</a></li>
					<li id="tabsPresentaciones"><a href="#tabsconsultaprincipalDos-7">Presentaciones peri&oacute;dicas</a></li>
					<li id="tabsProhibirConvivencia"><a href="#tabsconsultaprincipalDos-8">Prohibir convivencia</a></li>
					<li id="tabsSometer"><a href="#tabsconsultaprincipalDos-9">Someter a vigilancia</a></li>
					<li id="tabsPrision"><a href="#tabsconsultaprincipalDos-10">Prisi&oacute;n preventiva</a></li>
			   </ul>
	
					<div id="tabsconsultaprincipalDos-1">
					
					
	<table><tr><td>&nbsp;</td></tr><tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Centro de Salud:<select>
	<option>Tlalpan</option>
	<option>La Raza</option>
	<option>Juarez</option>
	<option>Siglo XXI</option>
	</select></td></tr></table>                       
	
	
	</div>

	<div id="tabsconsultaprincipalDos-2">
	<jsp:include page="ingresarDomicilioView.jsp"></jsp:include>
	
	
	</div>
	<div id="tabsconsultaprincipalDos-3">
	<table width="530px">
	<tr><td>&nbsp;</td></tr>
								<tr><td width="50">&nbsp;</td>
									<td width="165">Lista de derechos:</td>
									<td width="199" align="right"><select><option>Conducir Vehiculo</option><option>Ejercer profesión Vehiculo</option></select></td>
									<td width="96">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
									<td width="199">&nbsp;</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
									<td valign="top">Descripci&oacute;n:</td>
									<td valign="top"><textarea name="textarea" cols="30"
											rows="2"></textarea>
									</td>
									<td valign="top">&nbsp;</td>
								</tr>


							</table>
	
	
	</div>
	<div id="tabsconsultaprincipalDos-4">
	    
      <table width="762px"  height="313px" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="150" height="25" align="right" >Pa&iacute;s:</td>
          <td width="225" height="25">
            <select id="cbxPaisSeparacion" style="width:200px;">						
                <option value="-1">-Seleccione-</option>
            </select>
          </td>
          <td width="130" height="25" align="right">Calle o Avenida:</td>
          <td colspan="3" height="25" >
            <input type="text" id="areaCalleSeparacion" size="40"/>
          </td>
        </tr>
        <tr>
          <td align="right" height="25">C&oacute;digo Postal:</td>
          <td height="25">
            <input type="text" id="codigoPostalSeparacion" size="8" maxlength="5" />
            <input type="button" value="Enviar" id="codigoPostalButton" class="btn_Generico"/>
          </td>
          <td align="right" height="25">N&uacute;mero Ext.:</td>
          <td width="95" height="25" align="left">
            <input type="text" id="areaNumeroExteriorSeparacion" size="8" maxlength="8"/>
          </td>
          <td width="90" height="25" align="right">N&uacute;mero Int.:</td>
          <td width="58"  height="25" align="left"><input type="text" id="areaNumeroInteriorSeparacion" id="textfield" size="8" maxlength="8"/></td>
        </tr>
        <tr>
          <td height="25" align="right">Entidad Federativa:</td>
          <td height="25">
            <select id="cbxEntFederativaSeparacion" style="width:200px;">
                <option value="-1">-Seleccione-</option>
            </select>
            <input value="" size="20" maxlength="15" type="text" id="entidadFederativaSeparacion" />
          </td>
          <td height="25" align="right">Entre calle:</td>
          <td height="25" colspan="3"><input type="text" id="areaEntreCalleSeparacion" size="40"/></td>
        </tr>
        <tr>
          <td height="25" align="right">Ciudad:</td>
          <td height="25">
            <select id="cbxCiudadSeparacion"  style="width:200px;">
                <option value="-1">-Seleccione-</option>
            </select>
            <input type="text" id="areaCiudadSeparacion"/>
          </td>
          <td height="25" align="right">y calle:</td>
          <td height="25" colspan="3"><input type="text" id="areaYCalleSeparacion" size="40"/></td>
        </tr>
        <tr>
          <td height="25" align="right">Delegaci&oacute;n/Municipio:</td>
          <td height="25">
            <select id="cbxDelegacionMunicipioSeparacion" style="width:200px;">
                <option value="-1">-Seleccione-</option>
            </select>
            <input type="text" id="areaDelegacionMunicipioSeparacion"/>					
          </td>
          <td height="25" align="right">Alias:</td>
          <td height="25" colspan="3" ><input type="text" id="areaAliasSeparacion" size="40"/></td>
        </tr>
        <tr>
          <td height="25" align="right">Asentamiento o Colonia:</td>
          <td height="25">
            <select id="cbxAsentamientoColoniaSeparacion" style="width:200px;">
                <option value="-1">-Seleccione-</option>
            </select>
            <input type="text" id="areaColoniaSeparacion" />
          </td>
          <td height="25" align="right">Edificio:</td>
          <td height="25" colspan="3" ><input type="text" id="areaEdificioSeparacion" size="40"/></td>
        </tr>
        <tr>
          <td height="25" align="right">Tipo de Asentamiento:</td>
          <td height="25">
            <select id="cbxTipoAsentamientoSeparacion" style="width:200px;">
              <option value="-1">-Seleccione-</option>						
            </select>
            <input type="text" id="areaAsentamientoSeparacion" />
          </td>
          <td height="25" align="right">Referencias:</td>
          <td height="25" colspan="3" >
            <textarea id="areaReferenciasSeparacion" cols="45" rows="5" style="width: 240px; height:50px;" ></textarea></td>
        </tr>
        <tr>
          <td height="25" align="right">Tipo de Calle:</td>
          <td height="25">
            <select id="cbxTipoCalleSeparacion" style="width:200px;">
              <option value="-1">-Seleccione-</option>
            </select>
            <input type="text" id="areaTipoCalleSeparacion" />
          </td>  
          <td height="25" align="right">Coord. Georeferenciales:</td>
          <td height="25" colspan="3">
            <jsp:include page="/WEB-INF/paginas/datosCoordenadasGPSView.jsp" flush="true"></jsp:include>
          </td>
        </tr>
       
      </table>
   
	</div>
	<div id="tabsconsultaprincipalDos-5" >
		 <table><tr><td>&nbsp;</td></tr><tr><td>&nbsp;&nbsp;&nbsp;Localizador Electronico:<select id="cmbLocalizador"><option>Radio</option></select></td></tr></table>                       
					               	
	</div>
	<div id="tabsconsultaprincipalDos-6">
	<table width="530px">
	
	<tr><td>&nbsp;</td></tr>
								<tr>
									<td width="40">Territorio:</td>
									<td width="40"><select name="cmbProhibicion2"
										id="cmbProhibicion2">
											<option>Estado</option>
											<option>Delegación ó Municipio</option>
											<option>Otro</option>
									</select>
									</td>
									<td width="282" align="right">Delegaci&oacute;n &oacute;
										Municipio:</td>
									<td><select name="cmbProhibicion" id="cmbProhibicion">
											<option>Iztacalco</option>
											<option>Coyoacan</option>
									</select>
									</td>
								</tr>
								<tr>
									<td>Estado:</td>
									<td><select name="cmbProhibicion3" id="cmbProhibicion3">
											<option>México</option>
											<option>Chiapas</option>
											<option>Puebla</option>
									</select>
									</td>
									<td align="right" valign="top">Otro:</td>
									<td width="150"><textarea name="textarea" cols="30"
											rows="2"></textarea>
									</td>
								</tr>
								<tr>
									<td colspan="2" valign="top">&nbsp;</td>
									<td valign="top">&nbsp;</td>
									<td valign="top">&nbsp;</td>
								</tr>


							</table>
	
	
	</div>
	<div id="tabsconsultaprincipalDos-7" >
			<table width="530px"><tr><td>&nbsp;</td></tr><tr><td colspan="2">&nbsp;&nbsp;Presentarse en:<select id="cmbInstitucion"><option>Defensoria</option></select></td></tr>
					                			<tr><td colspan="2">Presentarse con:<select id="cmbEncargadoIns"><option>Juan Hernandez - Coordinador</option></select></td></tr>
					                			<tr><td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Día(s):<input type="text" size="20" /></td></tr>
					                    		<tr><td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hora:<input taype="text" size="20" /></td></tr>
					                    		<tr><td width="81">&nbsp;</td>
					                    		  <td width="437">&nbsp;</td>
					                    		</tr>
					                    		<tr>
					                    		  <td valign="top" >Descripci&oacute;n:</td>
					                    		  <td valign="top" >&nbsp;&nbsp;<textarea name="textarea" cols="15" rows="1"></textarea></td>
                                                </tr>        	
		   	</table>
	
	</div>
	<div id="tabsconsultaprincipalDos-8" >
	
	<table width="530px">
	<tr><td>&nbsp;</td></tr>
								<tr><td width="50px">&nbsp;</td>
									<td width="82">Víctimas:</td>
									<td width="282" align="right">Tipo de Prohibici&oacute;n:
									</td>
									<td><select name="cmbProhibicion" id="cmbProhibicion">
											<option>Defensoria</option>
									</select>
									</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
									<td colspan="3">&nbsp;<input type="checkbox" />Uno</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
									<td colspan="3">&nbsp;<input type="checkbox" />Dos</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
									<td colspan="3">&nbsp;<input type="checkbox" />Tres</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
									<td width="150">&nbsp;</td>
								</tr>
								<tr>
								<td>&nbsp;</td>
									<td valign="top">Descripci&oacute;n:</td>
									<td valign="top"><textarea name="textarea" cols="30"
											rows="2"></textarea>
									</td>
									<td valign="top">&nbsp;</td>
								</tr>


							</table>
	
	</div>
	<div id="tabsconsultaprincipalDos-9" style="height: 400">
	
	</div>
	<div id="tabsconsultaprincipalDos-10" style="height: 400">
	<table>
	<tr><td>&nbsp;</td></tr><tr><td colspan="2">Reclusorios:<select id="cmbReclu">
					                	<option>Norte</option>
					                	<option>Sur</option>
					                	</select></td></tr>
					                	<tr><td width="81">&nbsp;</td>
					                    		  <td width="437">&nbsp;</td>
					                    		</tr>
					                    		<tr>
					                    		  <td valign="top" >Descripci&oacute;n:</td>
					                    		  <td valign="top" ><textarea name="textarea" cols="15" rows="1"></textarea></td>
                                                </tr>  
					              </table>
	
			</div>
		</div>
        
     </div>
</div>
</body>
</html>
<!--ESTILOS PARA LAS TABS-->
	
