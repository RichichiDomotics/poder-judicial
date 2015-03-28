<%@page import="mx.gob.segob.nsjp.comun.enums.calidad.Calidades"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
<title>Atención de Audiencias</title>

	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>				
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"  />	
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/layout_complex.css"  />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"  />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<!--ESTILOS PARA LAS TABS-->
	<style>
	#tabs { height: 770px; } 
	.tabs-bottom { position: relative; } 
	
	.tabs-bottom .ui-tabs-panel { height: auto; overflow: auto; } 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	</style>
	
	<script type="text/javascript">

	var audienciaId="32";
	var numeroExpediente="NSJYUCPJ20112633337";
	
	$(document).ready(function() {

		/*
		*LLamadas COMUN
		*/
			//Recuperamos el id de la audiencia ACTUAL
			audienciaId=<%= request.getParameter("idAudiencia")%>;
			//alert(audienciaId);
			//crea todas la tabs
			$("#tabsPJENS").tabs();

		/*
		*LLamdas para la ceja Pruebas
		*/
			$( "#tabschildPruebasPJENS" ).tabs();
			
			//LLamadas para la sub ceja Datos de Pruebas
			cargaGridDatosPrueba();
			$("#tipoDatoPruebaPJENS").change(controlDatoDePrueba);
			$("#tipoMediaPruebaPJENS").change(controlMedioDePrueba);
			
			//LLamadas para la sub ceja de Medios de Pruebas
			cargaGridMediosDePrueba();
				
			//Llamadas para la sub ceja de Prueba
			cargaGridPrueba();
				
	});//FIN ON READY

	//*************************************************************FUCIONALIDAD PARA LA CEJA PRUEBAS*********************************************************/
	
	//////////////////////FUNCIONALIDAD PARA LA CEJA DATOS DE PRUEBA
	
	var banderaDatosPrueba = true;
	/**
	*Funcion que carga el grid datos de Prueba
	*/
	function cargaGridDatosPrueba(){

		if(banderaDatosPrueba == true){
			jQuery("#gridDatosDePruebaPJENS").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultarDatosDePruebaPorFiltro.do?audienciaId='+audienciaId+'&numeroExpediente='+numeroExpediente+'',
				datatype: "xml",
				colNames:['Nombre del Dato','Número de Identificación', 'Reg. de Cadena de Custodia','Registrado','Aceptado','Rechazado'], 
				colModel:[ 					
				           	{name:'nombreDato',index:'nombreDato', width:100, align:'left'}, 
				           	{name:'numIdentificacion',index:'numIdentificacion', width:80, align:'left'},
				        	{name:'rcc',index:'rcc', width:100, align:'left'}, 
				           	{name:'registrado',index:'registrado', width:50, align:'center'},
				           	{name:'aceptado',index:'aceptado', width:50, align:'center'},
				           	{name:'rechazado',index:'rechazado', width:50, align:'center'},
						],
				pager: jQuery('#pagerGridDatosDePruebaPJENS'),
				rowNum:10,
				autoWidth:false,
				width:800,
				//autoheight:true,
				rowList:[10,20,30],
				sortname: 'NombreDato',
				viewrecords: true,
				sortorder: "desc",
				caption:"Datos de Prueba",
				toolbar: [true,"top"],
				//multiselect:true,
				ondblClickRow: function(rowid) {
					consultarDatoDePrueba(rowid);
				} 
			}).navGrid('#gridDatosDePruebaPJENS',{edit:false,add:false,del:false});
			$("#gview_gridDatosDePruebaPJENS .ui-jqgrid-bdiv").css('height', '200px');

			banderaDatosPrueba=false;
			
			$("#t_gridDatosDePruebaPJENS").append("<input type='button' id='btnAgregarDatoPrueba' class='btn_grande' value='Agregar' style='height:20px;width:60px;font-size:-3'/>"+" "+"<input type='button' id='btnRelacionarDatoPrueba' class='btn_grande' value='Relacionar' style='height:20px;width:70px;font-size:-3'/>");			
			
			
			$("#btnAgregarDatoPrueba","#t_gridDatosDePruebaPJENS").click(function(){
				//alert("Agregar");
				agregarDatoDePrueba();
			});

			$("#btnRelacionarDatoPrueba","#t_gridDatosDePruebaPJENS").click(function(){
				//alert("Relacionar");
				var rowid = jQuery("#gridDatosDePruebaPJENS").jqGrid('getGridParam','selrow');
				if(rowid){
					var renglon = jQuery("#gridDatosDePruebaPJENS").jqGrid('getRowData',rowid);
					//verifica si la sub string checked se encuentra en la cadena input,
					//de NO ser asi devuelve -1
					if(renglon.aceptado.indexOf("checked") != -1){
						
						relacionarMediosDePrueba(rowid);
					}
					else{
						alert("El dato de prueba debe ser aceptado para \n relacionarlo con un medio de prueba");
					}
				}
				else{
					alert("Seleccione un dato de prueba a relacionar");
				}
			});
		}
		else{
			jQuery("#gridDatosDePruebaPJENS").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarDatosDePruebaPorFiltro.do?audienciaId='+audienciaId+'&numeroExpediente='+numeroExpediente+'',datatype: "xml" });
			$("#gridDatosDePruebaPJENS").trigger("reloadGrid");
		}
	}

	
	/*
	*Funcion para cambiar el estado del dato de prueba
	*/
	function rechazarDatoPrueba(datoPruebaId){
		//alert("Rechazar Dato prueba, id="+datoPruebaId);

		$.ajax({
	   		type: 'POST',
	   		url: '<%= request.getContextPath()%>/actualizarEstadoDatoPrueba.do?esAceptado=false&numeroExpediente='+numeroExpediente+'',
	   		data:'datoPruebaId='+datoPruebaId,
	   		dataType: 'xml',
	   		async: false,
	   		success: function(xml){
	   			cargaGridDatosPrueba();
	   			alert("El dato de prueba ha sido rechazado");
	   			$(this).dialog("close");
	   			$("#motivoRechazoDatoPrueba").val("");
	   		}
		});
	}
	
	/*
	*Funcion para cambiar el estado del dato de prueba
	*/
	function aceptarDatoPrueba(datoPruebaId){
		//alert("Aceptar Dato prueba, id="+datoPruebaId);
		
		$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/actualizarEstadoDatoPrueba.do?esAceptado=true&numeroExpediente='+numeroExpediente+'',
    		data:'datoPruebaId='+datoPruebaId,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			alert("El dato de prueba ha sido aceptado");	
    			cargaGridDatosPrueba();
    		}
		});
	}
	

	
	//Funcion usada para asignarle id a la ventana de consultar datos de prueba
	var consultarDatoPruebaWindow = 1;
	/*
	*Funcion para consultar datos de prueba vs medios de prueba
	*/
	function consultarDatoDePrueba(idDatoPrueba){

		consultarDatoPruebaWindow++;
		$.newWindow({id:"iframewindowConsultarDatoPrueba"+consultarDatoPruebaWindow, statusBar: true, posx:180,posy:60,width:1140,height:420,title:"Consultar Dato de Prueba", type:"iframe"});
    	$.updateWindowContent("iframewindowConsultarDatoPrueba"+consultarDatoPruebaWindow,'<iframe src="<%= request.getContextPath() %>/consultarDetalleDatoPruebaPJENS.do?idDatoPrueba='+idDatoPrueba+'&numeroExpediente='+numeroExpediente+'" width="1140" height="420" />');
	}


	
	/*
	*Funcion que abre la modal para relacionar los datos de prueba 
	*con los medios de prueba
	*/
	function relacionarMediosDePrueba(idDatoPrueba){
		
		cargaGridMediosPrueba(idDatoPrueba);
		$("#divRelacionarMediosDePruebaPJENS").dialog("open");
		$("#divRelacionarMediosDePruebaPJENS").dialog({ autoOpen: true, modal: true, 
		title: 'Relacionar medios de prueba', 
		dialogClass: 'alert', position: [350,50],
		width: 800, height:480, maxWidth: 800, maxHeight:550,
		buttons:{
			"Relacionar":function() {			  		

				var iDsMediosPrueba=""; 
				iDsMediosPrueba = jQuery("#gridRelacionarMediosDePruebaPJENS").jqGrid('getGridParam','selarrrow');
				guardarRelacionMediosDePruebaVsDatoPrueba(idDatoPrueba,iDsMediosPrueba);
			   	$(this).dialog("close");
			},
			"Cancelar":function() {
				confirmar=confirm("¿Realmente desea salir?");
				if (confirmar){
						$(this).dialog("close");
				}				  		
			}
		}
		});
	}

	
	/**
	*Funcion que guarda la relacion entre los medios de prueba
	*recibe un arreglo con los ids de medios de prueba y el id del dato de prueba
	*con el que se relacionaran
	*/
	function guardarRelacionMediosDePruebaVsDatoPrueba(idDatoPrueba,iDsMediosPrueba){

		$.ajax({
    		type: 'POST',
    		url: '<%= request.getContextPath()%>/relacionarMedioPruebaConDatoPrueba.do?iDsMediosPrueba='+iDsMediosPrueba+'&idDatoPrueba='+idDatoPrueba+'&numeroExpediente='+numeroExpediente+'',
    		data:'',
    		dataType:'xml',
    		async:false,
    		success: function(xml){
	   			if(parseInt($(xml).find('code').text())==0)
	    		  {
	   				alert("La relación ha sido guardada");
					//Limpiar los ids seleccionados
	   	    	}
	  			else{
	  				alert("Ocurrió un error durante la relación");
	      		}
    		}
		});
	} 


	//Variable para controlar si se carga por primera vez el grid
	var banderaRelacionarMediosPrueba = true;
	
	/**
	*Funcion que carga el grid medios de Prueba
	*/
	function cargaGridMediosPrueba(idDatoPrueba){

		if(banderaRelacionarMediosPrueba == true){

			jQuery("#gridRelacionarMediosDePruebaPJENS").jqGrid({ 
				url:'<%=request.getContextPath()%>/consultarMediosPruebaXDatoPrueba.do?idDatoPrueba='+idDatoPrueba+'&numeroExpediente='+numeroExpediente+'',
				datatype: "xml",
				colNames:['Nombre del Medio','Número de Identificación'], 
				colModel:[ 					
				           	{name:'nombreDato',index:'nombreDato', width:350, align:'center'}, 
				           	{name:'numIdentificacion',index:'numIdentificacion', width:350, align:'center'},
						],
				pager: jQuery('#pagerGridRelacionarMediosDePruebaPJENS'),
				rowNum:10,
				autoWidth:false,
				width:750,
				height:250,
				//autoheight:true,
				rowList:[10,20,30],
				sortname: 'NombreDato',
				viewrecords: true,
				sortorder: "desc",
				caption:"Medios de Prueba",
				toolbar: [true,"top"],
				multiselect:true
			}).navGrid('#gridRelacionarMediosDePruebaPJENS',{edit:false,add:false,del:false});
			banderaRelacionarMediosPrueba = false;
					
			$("#t_gridRelacionarMediosDePruebaPJENS").append("<input type='button' class='btn_grande' value='Agregar' style='height:20px;width:60px;font-size:-3'/>");
			$("input","#t_gridRelacionarMediosDePruebaPJENS").click(function(){
				agregarMedioDePrueba();
			});
		}
		else{
			jQuery("#gridRelacionarMediosDePruebaPJENS").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarMediosPruebaXDatoPrueba.do?idDatoPrueba='+idDatoPrueba+'&numeroExpediente='+numeroExpediente+'',datatype: "xml" });
			$("#gridRelacionarMediosDePruebaPJENS").trigger("reloadGrid");
		}
	}
	
	
	/*
	*Funcion que despliega la ventana para agregar un dato de prueba
	*/
	function agregarDatoDePrueba(){

		seleccioneTipoDatoPrueba();
		$('#tipoDatoPruebaPJENS').attr('selectedIndex', 0);
		$("#divAgregarDatoDePrueba").dialog("open");
		$("#divAgregarDatoDePrueba").dialog({ autoOpen: true, modal: true, 
		title: 'Agregar Dato de Prueba', 
		dialogClass: 'alert', position: [500,50],
		width: 495, height: 350, maxWidth: 495,
		buttons:{
			"Agregar":function() {
				guardarDatoPrueba(null);
			   	//$(this).dialog("close");
			},
			"Cancelar":function() {
				confirmar=confirm("¿Realmente desea salir del registro del dato de prueba?");
				if (confirmar){
					limpiarYSalirDatoDePrueba();
					$(this).dialog("close");
				}				  		
			}
		}
		});	 
	}


	/*
	*Funciones para agregar medios de prueba dependiendo de la seleccion
	*/
	function guardarDatoPrueba(datoPruebaId){
		
		var seleccion = $("#tipoDatoPruebaPJENS option:selected").val();
		var validacion=false;
		var paramDatoPrueba=""; 
		
		if(seleccion == "0"){
			validacion=false;
			alert("Seleccione un tipo de dato de prueba");	
		}
		//Validacion de documentos
		if(seleccion == "1"){
			if($('#txtNombreDatoPruebaPJENS').val() == ""){
				alert("Ingrese un nombre");
			}
			else{
				if($('#txtNumeroIdeDatoPruebaPJENS').val() == ""){
					alert("Ingrese un número de identificación");
				}
				else{
					
					if($('#txtAreaDescripcionDatoPruebaPJENS').val() == ""){
						alert("Ingrese una descripción");
					}
					else{
						validacion=true;
						paramDatoPrueba += 'audienciaId=' + audienciaId;
						paramDatoPrueba += '&tipoDatoPrueba=' + "documento";
						paramDatoPrueba += '&nombreDato=' + $('#txtNombreDatoPruebaPJENS').val();
						paramDatoPrueba += '&numIdDato=' + $('#txtNumeroIdeDatoPruebaPJENS').val();
						paramDatoPrueba += '&rccDato=' + $('#txtRccDatoPruebaPJENS').val();
						paramDatoPrueba += '&descDato=' + $('#txtAreaDescripcionDatoPruebaPJENS').val();
						paramDatoPrueba += '&numeroExpediente=' + numeroExpediente;
						paramDatoPrueba += '&datoPruebaId=' + datoPruebaId;
					}
				}
			}
		}

		if(seleccion == "2"){

			if($("#cbxTipoObjetoDatoPruebaPJENS option:selected").val() == "0" ){
				alert("Seleccione un tipo de objeto");
			}
			else{
				if($('#txtNombreDatoPruebaPJENS').val() == ""){
					alert("Ingrese un nombre");
				}
				else{
					if($('#txtNumeroIdeDatoPruebaPJENS').val() == ""){
						alert("Ingrese un número de identificación");
					}
					else{
						
						if($('#txtAreaDescripcionDatoPruebaPJENS').val() == ""){
							alert("Ingrese una descripción");
						}
						else{
							validacion=true;
							paramDatoPrueba += 'audienciaId=' + audienciaId;
							paramDatoPrueba += '&tipoDatoPrueba=' + "objeto";
							paramDatoPrueba += '&tipoObjetoDatoPrueba=' + $("#cbxTipoObjetoDatoPruebaPJENS option:selected").index();
							paramDatoPrueba += '&nombreDato=' + $('#txtNombreDatoPruebaPJENS').val();
							paramDatoPrueba += '&numIdDato=' + $('#txtNumeroIdeDatoPruebaPJENS').val();
							paramDatoPrueba += '&rccDato=' + $('#txtRccDatoPruebaPJENS').val();
							paramDatoPrueba += '&descDato=' + $('#txtAreaDescripcionDatoPruebaPJENS').val();
							paramDatoPrueba += '&numeroExpediente=' + numeroExpediente;
							paramDatoPrueba += '&datoPruebaId=' + datoPruebaId;
						}
					}
				}
			}
		}

		if(validacion==true){
			//alert(paramDatoPrueba);
			$.ajax({
	    		type: 'POST',
	    		url: '<%= request.getContextPath()%>/agregarDatoPrueba.do',
	    		data:paramDatoPrueba,
	    		dataType: 'xml',
	    		async: false,
	    		success: function(xml){
	    			alert("Dato de prueba guardado");
	    			$("#divAgregarDatoDePrueba").dialog("close");
	    			limpiarYSalirDatoDePrueba();
	    			cargaGridDatosPrueba();				
	    		}
			});
		}
	}

	
	/*
	*Funcion que controla el mostrar los campos dependiendo de la seleccion
	*del usuario
	*/
	function controlDatoDePrueba(){

		seleccioneTipoDatoPrueba();
		limpiaTipoDatoDePrueba();
				
		var seleccionTipoDato = $("#tipoDatoPruebaPJENS option:selected").val();		
		
		if(seleccionTipoDato == "0"){
			seleccioneTipoDatoPrueba();	
		}
		if(seleccionTipoDato == "1"){

			//Documentos
			seleccioneTipoDatoPrueba();			
			$('#etiNombreDatoPruebaPJENS').show();
			$('#divTxtNombreDatoPruebaPJENS').show();
			$('#etiNumIdeDatoPruebaPJENS').show();
			$('#divTxtNumeroIdeDatoPruebaPJENS').show();
			$('#etiRccDatoPruebaPJENS').show();
			$('#divTxtRccDatoPruebaPJENS').show();
			$('#etiDescripcionPJENS').show();
			$('#divTxtAreaDescripcionDatoPruebaPJENS').show();
		}
		if(seleccionTipoDato == "2"){

			//Objetos
			seleccioneTipoDatoPrueba();
			consultarCatalogoDeObjetosDatoDePrueba();
			$('#etiTipoObjetoDatoPruebaPJENS').show();
			$('#divCbxTipoObjetoDatoPruebaPJENS').show();
			$('#etiNombreDatoPruebaPJENS').show();
			$('#divTxtNombreDatoPruebaPJENS').show();
			$('#etiNumIdeDatoPruebaPJENS').show();
			$('#divTxtNumeroIdeDatoPruebaPJENS').show();
			$('#etiRccDatoPruebaPJENS').show();
			$('#divTxtRccDatoPruebaPJENS').show();
			$('#etiDescripcionPJENS').show();
			$('#divTxtAreaDescripcionDatoPruebaPJENS').show();
			
		}
	}

	/*
	*Oculta todos los div de todos los objetos
	*/
	function seleccioneTipoDatoPrueba(){
		
		$('#etiTipoObjetoDatoPruebaPJENS').hide();
		$('#divCbxTipoObjetoDatoPruebaPJENS').hide();

		$('#etiNombreDatoPruebaPJENS').hide();
		$('#divTxtNombreDatoPruebaPJENS').hide();

		$('#etiNumIdeDatoPruebaPJENS').hide();
		$('#divTxtNumeroIdeDatoPruebaPJENS').hide();

		$('#etiRccDatoPruebaPJENS').hide();
		$('#divTxtRccDatoPruebaPJENS').hide();

		$('#etiDescripcionPJENS').hide();
		$('#divTxtAreaDescripcionDatoPruebaPJENS').hide();
	}

	/*
	*Funcion que carga los combos de las de objetos
	*/
	function consultarCatalogoDeObjetosDatoDePrueba() {
     $.ajax({
    	  type: 'POST',
    	  url: '<%= request.getContextPath()%>/consultarTiposObjeto.do',
    	  data: '',
    	  async: false,
    	  dataType: 'xml',
    	  success: function(xml){
	    	  $('#cbxTipoObjetoDatoPruebaPJENS').empty();
	    	  $('#cbxTipoObjetoDatoPruebaPJENS').append('<option value="0">- Seleccione -</option>');
	    	  $(xml).find('catTipoObjetos').each(function(){
				$('#cbxTipoObjetoDatoPruebaPJENS').append('<option value="' + $(this).find('valor').text() + '">' + $(this).find('valor').text() + '</option>');
				});
    	  }
    	});
	}


	/*
	*Limpia todos los campos de todos los objetos
	*/
	function limpiaTipoDatoDePrueba(){

		$('#txtAreaDescripcionDatoPruebaPJENS').val("");
		$('#txtRccDatoPruebaPJENS').val("");
		$('#txtNumeroIdeDatoPruebaPJENS').val("");
		$('#txtNombreDatoPruebaPJENS').val("");
		$('#cbxTipoObjetoDatoPruebaPJENS').attr('selectedIndex', 0);
	}

	/*
	*Funcion que limpia todos los campos al salir de la ventana
	*y setea el valor seleccione por default
	*/
	function limpiarYSalirDatoDePrueba(){
		limpiaTipoDatoDePrueba();
		$('#tipoDatoPruebaPJENS').attr('selectedIndex',0);

	}

	
	/*
	*Funcion que despliega la ventana para agregar un Medio de prueba
	*/
	function agregarMedioDePrueba(){

		seleccioneTipoMedioPrueba();
		$('#tipoMediaPruebaPJENS').attr('selectedIndex', 0);
		controlMedioDePrueba();
		$("#divAgregarMedioDePrueba").dialog("open");
		$("#divAgregarMedioDePrueba").dialog({ autoOpen: true, modal: true, 
		title: 'Medio de Prueba', 
		dialogClass: 'alert', position: [500,50],
		width: 495, height: 400, maxWidth: 495,
		buttons:{
			"Agregar":function() {			  		
				guardarMedioDePrueba();
				//limpiaTipoMediaPrueba();
			   	//$(this).dialog("close");
			},
			"Cancelar":function() {
				confirmar=confirm("¿Realmente desea salir del registro del medio de prueba?");
				if (confirmar){
					limpiarYSalirMedioPrueba();
					$(this).dialog("close");
				}				  		
			}
		}
		});	 
	}

	/*
	*Funciones para agregar medios de prueba dependiendo de la seleccion
	*/
	function guardarMedioDePrueba(){
		
		var seleccion = $("#tipoMediaPruebaPJENS option:selected").val();
		var paramMedioPrueba=""; 
		
		if(seleccion == "0"){

			alert("Seleccione un tipo de medio de prueba");	
		}
		//Validacion de documentos
		if(seleccion == "1"){
			if($("#cbxSubTipoDocumentoMedioPruebaPJENS option:selected").val() == "0"){
				alert("Ingrese un subtipo de documento");
			}
			else{
				if($('#txtNombreDocumentoMedioPruebaPJENS').val() == ""){
					alert("Ingrese un nombre");
				}
				else{
					if($('#txtNumeroIdeDocumentoMedioPruebaPJENS').val() == ""){
						alert("Ingrese un número de identificación");
					}
					else{
						//if($('#txtAdjuntarDocumentoMedioPruebaPJENS').val() == ""){
						//	alert("Adjunte un archivo");
						//}
						//else{
							if($('#txtAreaDescripcionDocMedioPruebaPJENS').val() == ""){
								alert("Ingrese una descripción");
							}
							else{
								
								guardarMedioPruebaConDocDig();
								alert("El medio de prueba ha sido guardado \n y relacionado al dato");
								$("#divAgregarMedioDePrueba").dialog("close");
								$("#divRelacionarMediosDePruebaPJENS").dialog("close");
								cargaGridMediosDePrueba();
							}
						//}
					}
				}
			}
		}
		
		if(seleccion == "2"){
			if($('#cbxCalidadPersonaMedioPruebaPJENS option:selected').val() == 0){
				alert("Ingrese una calidad");
			}
			else{
				var calidad = $('#cbxCalidadPersonaMedioPruebaPJENS option:selected').val();
				$("#divAgregarMedioDePrueba").dialog("close");
				creaNuevoIndividuo(calidad);
				
			}
		}
	}

	//funcion que guarda las solicitudes de audiencia, de transcripcion de audiencia y  de audio/video
	function guardarMedioPruebaConDocDig(){


			forma = document.medioPruebaForm;

			//alert(forma.archivoAdjunto.value);
			forma.datoPruebaId.value= jQuery("#gridDatosDePruebaPJENS").jqGrid('getGridParam','selrow');
			forma.audienciaId.value = audienciaId;
			forma.tipoMedioPrueba.value = "documento";
			forma.subTipoMedioPrueba.value = $("#cbxSubTipoDocumentoMedioPruebaPJENS option:selected").index();
			forma.nombreDoc.value = $('#txtNombreDocumentoMedioPruebaPJENS').val();
			forma.numIdDoc.value = $('#txtNumeroIdeDocumentoMedioPruebaPJENS').val();
			forma.refUbicacionFisica.value = $('#txtRefUbicacionFisicaMedioPruebaPJENS').val();
			forma.descDocumento.value = $('#txtAreaDescripcionDocMedioPruebaPJENS').val();
			forma.numeroExpediente.value = numeroExpediente;
			forma.submit();
	}

	//Variable para controlar el id de las ventanas
	var idWindowIngresarIndividuo=1;

	/*
	*Crea una nueva ventana para ingresar individuos
	*/
	function creaNuevoIndividuo(calidad) {

		$("#divRelacionarMediosDePruebaPJENS").dialog("close");
		var stringCalidad = "";
		var datoPrueba;		

		var idDatoPrueba = jQuery("#gridDatosDePruebaPJENS").jqGrid('getGridParam','selrow');
		if(idDatoPrueba){

			datoPrueba = jQuery("#gridDatosDePruebaPJENS").jqGrid('getRowData',idDatoPrueba);
		}
				
		switch(parseInt(calidad)){

			case <%=Calidades.TESTIGO.getValorId()%>:
				stringCalidad="Testigo";
				//alert("testigo");
			break;

			case <%=Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId()%>:
				stringCalidad="Probable Responsable";
				//alert("ProbResponsable");
			break;

			case 3:
				stringCalidad="Perito";
				//alert("perito");
			break;
			
			case 4:
				stringCalidad="Policía Ministerial";
			break;

			default: alert("Error calidad inexistente");				
		}
						
		idWindowIngresarIndividuo++;
		$.newWindow({id:"iframewindowIngresarIndividuo" + idWindowIngresarIndividuo, statusBar: true, posx:200,posy:50,width:1050,height:600,title:"Ingresar: "+stringCalidad+" - Relacionado a: "+datoPrueba.nombreDato, type:"iframe"});
	    $.updateWindowContent("iframewindowIngresarIndividuo" + idWindowIngresarIndividuo,'<iframe src="<%= request.getContextPath() %>/IngresarTestigoPJENS.do?audienciaId='+audienciaId+'&numeroExpediente='+numeroExpediente +'&calidad='+calidad+'&idDatoPrueba='+idDatoPrueba+'" width="1050" height="600" />');
	    $("#iframewindowIngresarIndividuo"+idWindowIngresarIndividuo+".window-closeButton").fadeIn();
	    //alert("Abre ventana");
	    //$("#divRelacionarMediosDePruebaPJENS").dialog("open");
	}

	/*
	*Funcion que controla el mostrar los campos dependiendo de la seleccion
	*del usuario
	*/
	function controlMedioDePrueba(){

		var seleccionTipoMedio = $("#tipoMediaPruebaPJENS option:selected").val();

		limpiaTipoMediaPrueba();
		
		if(seleccionTipoMedio == "0"){
			seleccioneTipoMedioPrueba();			
		}
		if(seleccionTipoMedio == "1"){
			seleccioneTipoMedioPrueba();
			//Documentos
			$('#etiSubTipoDocumentoMedioPruebaPJENS').show();
			$('#etiNombreDocumentoMedioPruebaPJENS').show();
			$('#etiNumeroIdeDocumentoMedioPruebaPJENS').show();
			$('#etiAdjuntarDocumentoMedioPruebaPJENS').show();
			$('#etiRefUbicacionDocumentoMedioPruebaPJENS').show();
			$('#etiDescripcionDocMedioPruebaPJENS').show();
			$('#divSubTipoDocumentoMedioPruebaPJENS').show();
			$('#divTxtNombreDocumentoMedioPruebaPJENS').show();
			$('#divNumeroIdeDocumentoMedioPruebaPJENS').show();
			$('#divTxtAdjuntarDocumentoMedioPruebaPJENS').show();
			$('#divTxtRefUbicacionFisicaMedioPruebaPJENS').show();
			$('#divTxtAreaDescripcionDocMedioPruebaPJENS').show();
		}
		if(seleccionTipoMedio == "2"){
			seleccioneTipoMedioPrueba();
			//Persona
			$('#etiCalidadPersonaMedioPruebaPJENS').show();
			$('#divCbxCalidadPersonaMedioPruebaPJENS').show();
		}
	}
	

	/*
	*Oculta todos los div de todos los objetos
	*/
	function seleccioneTipoMedioPrueba(){
		//Documentos
		$('#etiSubTipoDocumentoMedioPruebaPJENS').hide();
		$('#etiNombreDocumentoMedioPruebaPJENS').hide();
		$('#etiNumeroIdeDocumentoMedioPruebaPJENS').hide();
		$('#etiAdjuntarDocumentoMedioPruebaPJENS').hide();
		$('#etiRefUbicacionDocumentoMedioPruebaPJENS').hide();
		$('#etiDescripcionDocMedioPruebaPJENS').hide();
		$('#divSubTipoDocumentoMedioPruebaPJENS').hide();
		$('#divTxtNombreDocumentoMedioPruebaPJENS').hide();
		$('#divNumeroIdeDocumentoMedioPruebaPJENS').hide();
		$('#divTxtAdjuntarDocumentoMedioPruebaPJENS').hide();
		$('#divTxtRefUbicacionFisicaMedioPruebaPJENS').hide();
		$('#divTxtAreaDescripcionDocMedioPruebaPJENS').hide();
		//Persona
		$('#etiCalidadPersonaMedioPruebaPJENS').hide();
		$('#divCbxCalidadPersonaMedioPruebaPJENS').hide();
	}
	

	/*
	*Limpia todos los campos de todos los objetos
	*/
	function limpiaTipoMediaPrueba(){

		//DOCUMENTOS
		$('#cbxSubTipoDocumentoMedioPruebaPJENS').attr('selectedIndex', 0);
		$('#txtNombreDocumentoMedioPruebaPJENS').val("");
		$('#txtNumeroIdeDocumentoMedioPruebaPJENS').val("");
		//resetea la forma
		document.medioPruebaForm.reset();		
		$('#txtRefUbicacionFisicaMedioPruebaPJENS').val("");
		$('#txtAreaDescripcionDocMedioPruebaPJENS').val("");

		//PERSONA
		$('#cbxCalidadPersonaMedioPruebaPJENS').attr('selectedIndex', 0);

		
	}

	/*
	*Funcion que limpia todos los campos al salir de la ventana
	*y setea el valor seleccione por default
	*/
	function limpiarYSalirMedioPrueba(){
		limpiaTipoMediaPrueba();
		$('#tipoMediaPruebaPJENS').attr('selectedIndex',0);

	}

	//Funcion que carga los combos de las de objetos
	function consultarCatalogoDeObjetos() {
     $.ajax({
    	  type: 'POST',
    	  url: '<%= request.getContextPath()%>/consultarTiposObjeto.do',
    	  data: '',
    	  async: false,
    	  dataType: 'xml',
    	  success: function(xml){
	    	  $('#cbxTipoObjetoMedioPruebaPJENS').empty();
	    	  $('#cbxTipoObjetoMedioPruebaPJENS').append('<option value="0">- Seleccione -</option>');
	    	  $(xml).find('catTipoObjetos').each(function(){
				$('#cbxTipoObjetoMedioPruebaPJENS').append('<option value="' + $(this).find('valor').text() + '">' + $(this).find('valor').text() + '</option>');
				});
    	  }
    	});
	}

	
	//////////////////////FUNCIONALIDAD PARA LA SUB CEJA MEDIOS DE PRUEBA
	
	var banderaMediosPrueba = true;
	/*
	*Funcion que carga el grid de medios de prueba
	*/
	function cargaGridMediosDePrueba(){

		if(banderaMediosPrueba == true){

			jQuery("#gridMediosDePruebaPJENS").jqGrid({

				url:'<%=request.getContextPath()%>/consultarMediosPruebaAsociadosAlExpediente.do?idDatoPrueba=null&numeroExpediente='+numeroExpediente+'',
				datatype: "xml",
				colNames:['Nombre del Medio','Número de Identificación'], 
				colModel:[ 					
				           	{name:'nombreMedio',index:'nombreMedio', width:350, align:'center'}, 
				           	{name:'numIdentificacion',index:'numIdentificacion', width:350, align:'center'},
						],
				pager: jQuery('#pagerGridMediosDePruebaPJENS'),
				rowNum:10,
				autoWidth:false,
				width:750,
				height:250,
				//autoheight:true,
				rowList:[10,20,30],
				sortname: 'nombreMedio',
				viewrecords: true,
				sortorder: "desc",
				caption:"Resumen de medios de prueba",
				toolbar: [true,"top"],
				multiselect:true,
				ondblClickRow: function(rowid) {
					consultarMedioDePrueba(rowid)
				} 
			}).navGrid('#gridMediosDePruebaPJENS',{edit:false,add:false,del:false});
			//$("#gview_gridMediosDePruebaPJENS .ui-jqgrid-bdiv").css('height', '200px');
			banderaMediosPrueba = false;
			//$("#t_gridMediosDePruebaPJENS").append("<input type='button' id='btnAgregarMedioPrueba' class='btn_grande' value='Agregar' style='height:20px;width:60px;font-size:-3'/>"+" "+"<input type='button' id='btnDesahogarMedioPrueba' class='btn_grande' value='Desahogar' style='height:20px;width:70px;font-size:-3'/>");
			//$("#btnAgregarMedioPrueba","#t_gridMediosDePruebaPJENS").click(function(){
			//	agregarMedioDePrueba();
			//});
						
			$("#t_gridMediosDePruebaPJENS").append("<input type='button' id='btnDesahogarMedioPrueba' class='btn_grande' value='Desahogar' style='height:20px;width:70px;font-size:-3'/>");
			$("#btnDesahogarMedioPrueba","#t_gridMediosDePruebaPJENS").click(function(){
				alert("Desahogar medio prueba");
			});
		}
		else{
			jQuery("#gridMediosDePruebaPJENS").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarMediosPruebaAsociadosAlExpediente.do?idDatoPrueba=null&numeroExpediente='+numeroExpediente+'',datatype: "xml" });
			$("#gridMediosDePruebaPJENS").trigger("reloadGrid");
		}	
	}

	
	//variable usada para asignarle id a la ventana de consultar datos de prueba
	var consultarMedioDePruebaWindow = 1;
	
	/*
	*Funcion para consultar datos de prueba vs medios de prueba
	*/
	function consultarMedioDePrueba(idMedioPrueba){
		
		consultarMedioDePruebaWindow++;
		$.newWindow({id:"iframewindowConsultarMedioDePrueba"+consultarMedioDePruebaWindow, statusBar: true, posx:180,posy:60,width:1140,height:420,title:"Consultar Medio de Prueba", type:"iframe"});
    	$.updateWindowContent("iframewindowConsultarMedioDePrueba"+consultarMedioDePruebaWindow,'<iframe src="<%= request.getContextPath() %>/consultarDetalleMedioPruebaPJENS.do?idMedioPrueba='+idMedioPrueba+'" width="1140" height="420" />');
	}

	//////////////////////FUNCIONALIDAD PARA LA SUB CEJA DE PRUEBA
	
	
	var banderaPrueba = true;
	/*
	*Funcion que carga el grid de medios de prueba
	*/
	function cargaGridPrueba(){

		if(banderaPrueba == true){

			jQuery("#gridPruebaPJENS").jqGrid({ 
				//url:'<%=request.getContextPath()%>/gridConsultaObjetos.do?audienciaId='+idAudienciaSiguiente,
				//datatype: "xml", 
				datatype: "local",
				colNames:['Nombre','Número de Identificación','Reg. de Cadena de Custodia','En proceso','Desahogada'], 
				colModel:[ 					
				           	{name:'nombrePrueba',index:'nombrePrueba', width:100, align:'center'}, 
				           	{name:'numIdentificacion',index:'numIdentificacion', width:80, align:'center'},
				        	{name:'rcc',index:'rcc', width:100, align:'center'},
				        	{name:'enProceso',index:'enProceso', width:50, align:'center', formatter:'checkbox'},
				        	{name:'desahogada',index:'desahogada', width:50, align:'center', formatter:'checkbox'},
				           
						],
				pager: jQuery('#pagerGridPruebaPJENS'),
				rowNum:10,
				autoWidth:false,
				width:750,
				height:250,
				//autoheight:true,
				rowList:[10,20,30],
				sortname: 'nombreMedio',
				viewrecords: true,
				sortorder: "desc",
				caption:"Resumen de medios de prueba",
				toolbar: [true,"top"],
				multiselect:true
			}).navGrid('#gridPruebaPJENS',{edit:false,add:false,del:false});
			$("#gview_gridPruebaPJENS .ui-jqgrid-bdiv").css('height', '200px');
			banderaPrueba = false;
			$("#t_gridPruebaPJENS").append("<input type='button' id='btnAgregarMedioPrueba' class='btn_grande' value='Agregar' style='height:20px;width:60px;font-size:-3'/>"+" "+"<input type='button' id='btnDesahogarMedioPrueba' class='btn_grande' value='Desahogar' style='height:20px;width:70px;font-size:-3'/>");			
						
		}
		else{
			jQuery("#gridPruebaPJENS").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/.do',datatype: "xml" });
			$("#gridPruebaPJENS").trigger("reloadGrid");
		}	
	}
	
	</script>
</head>
<body>
	
	<!--Comienzan Tabs Principales-->
	<div id="tabsPJENS">
		<ul>
			<li id="pruebas">
				<a href="#tabsPJENS-1">Pruebas</a>
			</li>
		</ul>
		
		<!--Comienza Tab Pruebas-->
		<div id="tabsPJENS-1">
		
			<!--Comienzan sub cejas de pruebas-->
			<div id="tabschildPruebasPJENS" class="tabs-bottom">
					
				<ul>
					<li><a href="#tabschildPruebasPJENS-1">Datos de Prueba</a></li>
					<li><a href="#tabschildPruebasPJENS-2">Medios de Prueba</a></li>
					<li><a href="#tabschildPruebasPJENS-3">Prueba</a></li>
				</ul>
					
				<!--Comienza Sub Tab Datos de prueba  -->
				<div id="tabschildPruebasPJENS-1">
				
					<table width="1000" border="0" cellspacing="0" cellpadding="0">
					  <tr>
					    <td width="100">&nbsp;</td>
					    <td width="400">&nbsp;</td>
					    <td width="400">&nbsp;</td>
					    <td width="100">&nbsp;</td>
					  </tr>
					  <tr>
					    <td>&nbsp;</td>
					    <td colspan="2" rowspan="11">
							<table  id="gridDatosDePruebaPJENS" width="100%"></table>
							<div id="pagerGridDatosDePruebaPJENS"></div>
						</td>
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
					  </tr>
					  <tr>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
					  </tr>
					</table>
					
				</div>
				<!--Termina Sub Tab Datos de prueba  -->
				
				
				<!--Comienza Sub Tab Medios de prueba  -->
				<div id="tabschildPruebasPJENS-2">
				
					<table width="1000" border="0" cellspacing="0" cellpadding="0">
					  <tr>
					    <td width="100">&nbsp;</td>
					    <td width="400">&nbsp;</td>
					    <td width="400">&nbsp;</td>
					    <td width="100">&nbsp;</td>
					  </tr>
					  <tr>
					    <td>&nbsp;</td>
					    <td colspan="2" rowspan="11">
							<table  id="gridMediosDePruebaPJENS" width="100%"></table>
							<div id="pagerGridMediosDePruebaPJENS"></div>
						</td>
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
					  </tr>
					  <tr>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
					  </tr>
					</table>
										
				</div>
				<!--Termina Sub Tab Medios de prueba  -->
				
				
				<!--Comienza Sub Tab prueba  -->
				<div id="tabschildPruebasPJENS-3">
				
					<table width="1000" border="0" cellspacing="0" cellpadding="0">
					  <tr>
					    <td width="100">&nbsp;</td>
					    <td width="400">&nbsp;</td>
					    <td width="400">&nbsp;</td>
					    <td width="100">&nbsp;</td>
					  </tr>
					  <tr>
					    <td>&nbsp;</td>
					    <td colspan="2" rowspan="11">
							<table  id="gridPruebaPJENS" width="100%"></table>
							<div id="pagerGridPruebaPJENS"></div>
						</td>
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
					  </tr>
					  <tr>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
					    <td>&nbsp;</td>
					  </tr>
					</table>

				</div>
				<!--Termina Sub Tab prueba  -->
				
			</div>
			<!--Terminan sub cejas de pruebas-->
			
		</div>
		<!--Termina Tab para pruebas-->
		
	</div>
	<!--Terminan Tabs Principales-->
	
	
	<!---------------------------------------------------Comienzan divs para ventanas modales------------------------------------------------------------------>
	
	<!--Comienza div agregar dato de prueba-->
	<div  id="divAgregarDatoDePrueba" style="display: none">
	
		<table width="440" cellspacing="0" cellpadding="0" border="0">
	      <tr>
	        <td width="5">&nbsp;</td>
	        <td width="230" align="right">&nbsp;</td>
	        <td width="200">&nbsp;</td>
	        <td width="5">&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	            <strong>Tipo de dato de prueba:</strong>
	        </td>
	        <td>
	            <select id="tipoDatoPruebaPJENS" style="width:200px">
	              <option value="0">-Seleccione-</option>
	              <option value="1">Documento</option>
	              <option value="2">Objeto</option>
	            </select>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	            <div id="etiTipoObjetoDatoPruebaPJENS">
	                <strong>Tipo de Objeto:</strong>
	            </div>
	        </td>
	        <td>
	            <div id="divCbxTipoObjetoDatoPruebaPJENS">
	                <select id="cbxTipoObjetoDatoPruebaPJENS" style="width:200px">
	              	</select>
	            </div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="etiNombreDatoPruebaPJENS">
	        		<strong>Nombre del dato:</strong>
	        	</div>
	        </td>
	        <td>
	        	<div id="divTxtNombreDatoPruebaPJENS">
	        		<input type="text" id="txtNombreDatoPruebaPJENS" style="width:200px"/>
	        	</div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="etiNumIdeDatoPruebaPJENS">
	            	<strong>N&uacute;mero de identificaci&oacute;n:</strong>
	            </div>
	        </td>
	        <td>
	        	<div id="divTxtNumeroIdeDatoPruebaPJENS">
	        		<input type="text" id="txtNumeroIdeDatoPruebaPJENS" style="width:200px"/>
	        	</div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="etiRccDatoPruebaPJENS">
	            	<strong>Reg. Cadena de custodia:</strong>
	            </div>
	        </td>
	        <td>
	        	<div id="divTxtRccDatoPruebaPJENS">
	        		<input type="text" id="txtRccDatoPruebaPJENS" style="width:200px"/>
	        	</div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="etiDescripcionPJENS">
	        		<strong>Descripción:</strong>
	        	</div>
	        </td>
	        <td>
	        	<div id="divTxtAreaDescripcionDatoPruebaPJENS">
	        		<textarea cols="31" rows="5" id="txtAreaDescripcionDatoPruebaPJENS"></textarea>
	        	</div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	    </table>
	    
	</div>
	<!--Termina div agregar dato de prueba-->
	
	<!--Comienza div rechazar dato de prueba-->
	<div id="divRechazarDatoDePruebaPJENS" style="display: none">
		<table width="400" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="14">&nbsp;</td>
	        <td width="374">&nbsp;</td>
	        <td width="12">&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td height="200" align="center" valign="middle">
	        	<textarea id="motivoRechazoDatoPrueba" cols="60" rows="12"></textarea>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td>&nbsp;</td>
	        <td>&nbsp;</td>
	      </tr>
	    </table>
	</div>
	<!--Termina div rechazar dato de prueba-->
	
	
	<!--Comienza div Relacionar Medios de Prueba-->
	<div id="divRelacionarMediosDePruebaPJENS" style="display: none">
		<table width="700" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="150">&nbsp;</td>
		    <td width="300">&nbsp;</td>
		    <td colspan="2">&nbsp;</td>
		  </tr>
		  <tr>
		    <td height="250" colspan="4">
		    	<table  id="gridRelacionarMediosDePruebaPJENS" width="100%"></table>
				<div id="pagerGridRelacionarMediosDePruebaPJENS"></div>
		    </td>
		  </tr>
		</table>
	</div>
	<!--Relacionar div Relacionar Medios de Prueba-->
	
	<!--Comienza div agregar Medio de prueba-->
	<div  id="divAgregarMedioDePrueba" style="display: none">
		
		<table width="440" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="5">&nbsp;</td>
	        <td width="230" align="right">&nbsp;</td>
	        <td width="200">&nbsp;</td>
	        <td width="5">&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<strong>Tipo de Medio de Prueba:</strong>
	        </td>
	        <td>
	            <select name="tipoMediaPruebaPJENS" id="tipoMediaPruebaPJENS" style="width:200px">
	              <option value="0">-Seleccione-</option>
	              <option value="1">Documento</option>
	              <option value="2">Persona</option>
	            </select>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="etiSubTipoDocumentoMedioPruebaPJENS">
			    	<strong>Subtipo del documento:</strong>
		        </div>
	            <div id="etiCalidadPersonaMedioPruebaPJENS">
			    	<strong>Calidad:</strong>
			    </div>
	        </td>
	        <td>
	        	<div id="divSubTipoDocumentoMedioPruebaPJENS">
	                <select id="cbxSubTipoDocumentoMedioPruebaPJENS" style="width:200px">
	                  <option value="0">-Seleccione-</option>
	                  <option value="1">Archivo de texto</option>
	                  <option value="2">Archivo de audio</option>
	                  <option value="3">Archivo de video</option>
	                  <option value="4">Imagenes/Fotografías</option>
	                </select>
	            </div>
	        	
	        	<div id="divCbxCalidadPersonaMedioPruebaPJENS">
	                <select id="cbxCalidadPersonaMedioPruebaPJENS" style="width:200px">
	                  <option value="0">-Seleccione-</option>
	                  <option value="<%=Calidades.TESTIGO.getValorId()%>">Testigo</option>
	                  <option value="<%=Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId()%>">Prob. Responsable</option>
	                  <option value="3">Perito</option>
	                  <option value="4">Policía ministerial</option>
	                </select>
	            </div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="etiNombreDocumentoMedioPruebaPJENS">
			    	<strong>Nombre del documento:</strong>
			    </div>
	        </td>
	        <td>
	        	<div id="divTxtNombreDocumentoMedioPruebaPJENS">
			    	<input type="text" id="txtNombreDocumentoMedioPruebaPJENS" style="width:200px"/>
			    </div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	       		<div id="etiNumeroIdeDocumentoMedioPruebaPJENS">
			    	<strong>N&uacute;mero de identificaci&oacute;n:</strong>
			    </div>
	        </td>
	        <td>
	        	<div id="divNumeroIdeDocumentoMedioPruebaPJENS">
			    	<input type="text" id="txtNumeroIdeDocumentoMedioPruebaPJENS" style="width:200px"/>
			    </div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="etiAdjuntarDocumentoMedioPruebaPJENS">
			    	<span class="au av ra rc ta" ><strong>Adjuntar:</strong></span>
			    </div>
	        </td>
	        <td>
	        	<div id="divTxtAdjuntarDocumentoMedioPruebaPJENS">
					<div id="divAdjuntarDoc" class="au av ra rc ta">
						
				        	<form id="medioPruebaForm" name="medioPruebaForm" 
				        	action="<%= request.getContextPath() %>/agregarMedioPrueba.do" method="post" enctype="multipart/form-data">
								<input type="file" name="archivoAdjunto">
								<input type="hidden" name="datoPruebaId"/>
								<input type="hidden" name="audienciaId"/>
								<input type="hidden" name="tipoMedioPrueba"/>
								<input type="hidden" name="subTipoMedioPrueba"/>
								<input type="hidden" name="nombreDoc"/>
								<input type="hidden" name="numIdDoc"/>
								<input type="hidden" name="refUbicacionFisica"/>
								<input type="hidden" name="descDocumento"/>
								<input type="hidden" name="numeroExpediente"/>
							</form>
					</div>
			    </div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="etiRefUbicacionDocumentoMedioPruebaPJENS">
			    	<strong>Ref. ubicación física:</strong>
			    </div>
	        </td>
	        <td>
	        	<div id="divTxtRefUbicacionFisicaMedioPruebaPJENS">
			    	<input type="text" id="txtRefUbicacionFisicaMedioPruebaPJENS" style="width:200px"/>
			    </div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	      <tr>
	        <td>&nbsp;</td>
	        <td align="right">
	        	<div id="etiDescripcionDocMedioPruebaPJENS">
			    	<strong>Descripción:</strong>
			    </div>
	        </td>
	        <td>
	        	<div id="divTxtAreaDescripcionDocMedioPruebaPJENS">
			    	<textarea cols="31" rows="5" id="txtAreaDescripcionDocMedioPruebaPJENS"></textarea>
			    </div>
	        </td>
	        <td>&nbsp;</td>
	      </tr>
	    </table>
					
	</div>
	<!--Termina div agregar Medio de prueba Documento-->
	
	<!--Terminan divs para ventanas modales-->
</body>
</html>