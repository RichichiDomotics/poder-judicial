<!-- 
Visor atencion a solicitudes periciales
Funcionalidad para:
	-Ver los datos de la solicitud
	-Ver los documentos enviados por el AMP
	-Realizar actuaciones
	-Ver los documentos propios
 -->
<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.Puestos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Atención a solicitudes periciales</title>
	
	<!--Se importan las css necesarias-->
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css" />


<!--Se importan los scripts necesarios-->
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	
	
	
<script type="text/javascript">
	
	/*
	*Variables Generales
	*/
	var documentoId; 
	var solicitudId;
	
	/*
	*Variables para la ceja Documentos
	*/
	var primeraVezGridDocumentosDigitales = true;

	/*
	*Variables para la ceja Actuaciones
	*/
	var idWindowGenerarDictamenInforme=1;
	var idWindowSolicitudDeEvidencia=1;
	
	/*
	*Variables para la ceja Documentos Propios
	*/
	var primeraVezGridDocumentosDigitalesPropios = true;
	var idExpedienteDoc=parent.idExpedienteDoc;
	var numidExpedienteDoc=parent.numidExpedienteDoc;
	
	/*
	*On ready del documento
	*/	
	$(document).ready(function() {

		//Obtenemos parametros
		documentoId = <%=request.getParameter("documentoId")%>;
		solicitudId = <%=request.getParameter("solicitudId")%>;
		//crear tabs		
		$("#tabsPrincipal").tabs();
		
		/*
		*LLamadas para la ceja de detalle de solicitud
		*/
		consultarDetalleSolicitud();
		/*
		*LLamadas para la ceja documentos
		*/
		cargaGridDocumentosDigitales();
		cargaDatosSolicitud(solicitudId);
		cargaGridEvidencias(solicitudId);
		/*
		*LLamadas para la ceja actuaciones
		*/		
		//Escuchador de cambio en el combo box
		$("#lstActuaciones").change(mostrarOcultarElementos);
		//Oculta inicialmente los elementos
		ocultaActuaciones();

		/*
		*LLamadas para la ceja documentos propios
		*/
		cargaGridDocumentosDigitalesPropios();
			
	});//FIN ONREADY
/**************************************************************FUNCIONALIDAD PARA LA CEJA SOLICITUD************************************************************/

	function consultarDetalleSolicitud(){

		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultarDetalleDeSolicitud.do',
    		data: 'solicitudPericialId='+solicitudId,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var errorCode;
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){	
					
					$('#atnServPericialFolioSol').val($(xml).find('folioDocumento').first().text());
					$('#atnServPericialEspecialidadSol').val($(xml).find('especialidad').find('valor').last().text());
					$('#atnServPericialNumCaso').val($(xml).find('numeroGeneralCaso').first().text());
					$('#atnServPericialNumExp').val($(xml).find('numeroExpediente').first().text());
					$('#atnServPericialNombSolicitante').val($(xml).find('nombreFuncionario').first().text()+" "+$(xml).find('apellidoPaternoFuncionario').first().text()+" "+$(xml).find('apellidoMaternoFuncionario').first().text());
					$('#atnServPericialFechLim').val($(xml).find('fechaLimiteStr').first().text());
    			}
				else{
					//Mostrar mensaje de error
				}
    		}
    	});
	}

/**************************************************************FUNCIONALIDAD PARA LA CEJA EVIDENCIAS************************************************************/
	function cargaGridEvidencias(solicitudId){
		jQuery("#gridDetalleCadenaCustodia").jqGrid({ 
			url:'<%= request.getContextPath()%>/consultarEvidenciasSolicitud.do?solicitudId='+solicitudId+'',
			data:'',
			datatype: "xml", 
			colNames:['Número de Evidencia','Cadena de Custodia','Objeto'/*,'Código de Barras'*/], 
			colModel:[ 	{name:'NumeroEvidencia',index:'numeroEvidencia', width:150},
			           	{name:'CadenaCustodia',index:'cadenaCustodia', width:150},
			           	{name:'Objeto',index:'objeto', width:150}/*,
			           	{name:'CodigoBarras',index:'codigoBarras', width:150}*/
					],
			pager: jQuery('#pagerCadenaCustodia'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'CadenaCustodia',
			viewrecords: true,
			sortorder: "desc",
			multiselect: false
			}).navGrid('#pagerCadenaCustodia',{edit:false,add:false,del:false});
	}


	function cargaDatosSolicitud(solicitudId){
		$.ajax({
	    	  type: 'POST',
	    	  url:  '<%= request.getContextPath()%>/consultaDetalleSolicitudPericial.do',
	    	  data: 'solicitudPericialId='+solicitudId,
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    		  pintaDatosSolicitud(xml);
			  }
	    });
	}

	function pintaDatosSolicitud(xml){
	   if($(xml).find('observaciones') != null){
		   $('#areaDescripcion').val($(xml).find('observaciones').text());
	    }
	}

	
/**************************************************************FUNCIONALIDAD PARA LA CEJA DOCUMENTOS************************************************************/
	/*
	*Funcion que carga el grid con los nombre de los documentos digitales asociados 
	*al id de la solicitud de serv. periciales
	*/
	function cargaGridDocumentosDigitales(){

		if(primeraVezGridDocumentosDigitales == true){
			jQuery("#gridDocumentosDigitales").jqGrid({
				url:'<%=request.getContextPath()%>/consultarDocumentosPropiosAsociadosASolicitudPericial.do?solicitudId='+solicitudId+'',
				datatype: "xml", 
				colNames:['Área del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
				colModel:[ 	{name:'area',index:'area', width:200,hidden:true},
							{name:'FechaActividad',index:'fechaActividad', width:170,hidden:true},							
							{name:'NombreActividad',index:'nombreActividad', width:400,hidden:true},
				           	{name:'Tipo',index:'tipo', width:155,hidden:true}, 
							{name:'Nombre',index:'nombre', width:255},
				           	{name:'Fecha',index:'fecha', width:170,hidden:true}
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
			jQuery("#gridDocumentosDigitales").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarDocumentosPropiosAsociadosASolicitudPericial.do?solicitudId='+solicitudId+'',datatype: "xml" });
			$("#gridDocumentosDigitales").trigger("reloadGrid");
		}
	}

	/*
	*Funcion que abre el PDF para ver los documentos asociados al numero de causa
	*/
	function abrirDocsDigAsociadosASol(documentoAsocId){
		if(documentoAsocId != null && documentoAsocId != ""){
			// Versión Anterior
			// $("#visorDocsFrame").attr("src","<%= request.getContextPath()%>/consultarArchivoDigitalIframe.do?documentoId="+documentoAsocId+"&inFrame=true");
			//Versión de Integración
			// $("#visorDocsFrame").attr("src","<%= request.getContextPath()%>/consultarArchivoDigitalIframe.do?archivoDigitalId="+documentoAsocId+"&inFrame=1");
			//Versión nueva
			// $("#visorDocsFrame").attr("src","<%= request.getContextPath()%>/consultarArchivoDigitalIframe.do?archivoDigitalId="+documentoAsocId+"&inFrame=1");
			//Versión de Prueba
			$("#visorDocsFrame").attr("src","<%= request.getContextPath()%>/consultarArchivoDigitalIframe.do?archivoDigitalId="+documentoAsocId+"&inFrame=true");
		}
		else{
			alert("El documento no puede ser mostrado");
		}
	}
	
/*******************************************************COMIENZA FUNCIONALIDAD PARA ACTUACIONES PERICIALES ***************************************************/
	
	/*************************************************************FUNCIONALIDAD COMUN*********************************************************************/
	
	/*
	*Funcion que oculta o muestra elementos en la 
	*pantalla dependiendo de la opcion seleccionada
	*/
	function mostrarOcultarElementos(){
		var selected = $("#lstActuaciones option:selected").val();

		//Oculta todo
		if(selected == "0"){
			ocultaActuaciones();
		}
		//Agregar documentos
		if(selected == "1"){
			$("#agregarDocs").show();
			$("#realizarDictamen").hide();
			$("#realizarInforme").hide();
		}
		//Realizar dictamen
		if(selected == "2"){
			$("#agregarDocs").hide();
			$("#realizarDictamen").show();
			$("#realizarInforme").hide();
			controlGenerarDocumento('Dictamen');
		}
		//Realizar dictamen
		if(selected == "3"){
			$("#agregarDocs").hide();
			$("#realizarDictamen").hide();
			$("#realizarInforme").show();
			controlGenerarDocumento('Informe');
		}

		//solicitud de evidencia
		if(selected == "4"){
			$("#agregarDocs").hide();
			$("#realizarDictamen").hide();
			$("#realizarInforme").show();
			muestraVisorSolicitudDeEvindecia();
		}

		//Devolucion evidencia
		if(selected == "5"){
			$("#agregarDocs").hide();
			$("#realizarDictamen").hide();
			$("#realizarInforme").show();
			controlGenerarDocumento('DevolucionEvidencia');
		}
		//Devolucion evidencia
		if(selected == "6"){
			$("#agregarDocs").hide();
			$("#realizarDictamen").hide();
			$("#realizarInforme").show();
			controlGenerarDocumento('OficioDestruccion');
		}
		//Devolucion evidencia
		if(selected == "7"){
			$("#agregarDocs").hide();
			$("#realizarDictamen").hide();
			$("#realizarInforme").show();
			controlGenerarDocumento('SolAmpliacionPeriodo');
		}
	}


	/*
	*Funcion que oculta todos los elementos de la pantalla
	*/
	function ocultaActuaciones(){
		
		$("#agregarDocs").hide();
		$("#realizarDictamen").hide();
		$("#realizarInforme").hide();
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


	/********************************************************FUNCIONALIDAD PARA REALIZAR DICTAMEN E INFORME***********************************************/
	
	/*
	*Funcion que crea el documento recibe el tipo de documento
	*que se desea crear
	*/
	function crearDocumento(forma){

		var documentoId = "";
		
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/crearDictamenParaSolicitudPericial.do',
			data: 'solicitudPericialId='+solicitudId+'&formaId='+forma,
			dataType: 'xml',
			async: false,
			success: function(xml){

				var errorCode;
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){
					documentoId = $(xml).find('documentoId').first().text();					
					//$("#gridSolicitud").trigger("reloadGrid");
				}
				else{
					//Mostrar mensaje de error
				}
			}
		});
		return documentoId;
	}

	
	/*
	*Funcion que sube el expediente a sesion
	*param:Id de la forma que se desea crear
	*return;numero de expediente
	*/
	function subirExpedienteSesion(forma){
		
		//colocar el expediente en sesion
		exp = "";
		$.ajax({
	   		type: 'POST',
	   		url: '<%=request.getContextPath()%>/colocarExpedienteParaSolicitudPericial.do',
	   		data: 'solicitudPericialId='+solicitudId+'&formaId='+forma,
	   		dataType: 'xml',
	   		async: false,
	   		success: function(xml){
	   			var errorCode;
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){	
					exp = $(xml).find('numeroExpediente').first().text();
					
					
	   			}
				else{
					//Mostrar mensaje de error
				}
	   		}
	    }); 
		return exp;
	}

	
	/*
	*Funcion que abre el visor para generar documentos 
	*/
	
	function abrirDocumento(documentoId,exp,tipo){

		idWindowGenerarDictamenInforme++;
		$.newWindow({id:"iframewindowGenerarDocumentoPericial"+idWindowGenerarDictamenInforme, statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Realizar "+tipo, type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumentoPericial"+idWindowGenerarDictamenInforme,"<iframe src='<%= request.getContextPath() %>/generarDocumentoSinCaso.do?documentoId="+documentoId+"&numeroUnicoExpediente="+exp+"' width='1140' height='400' />");
	    
	    
	}
	/**
	* Función que es llamada por el generador de documentos guando se realiza un guardado definitivo del documento
	* Esta función actualiza el estado de la solicitud pericial y adjunta el archivo digital recién generado a las solicitudes padre
	*/
	function documentoGenerado(){
		
		$.ajax({
	   		type: 'POST',
	   		url: '<%=request.getContextPath()%>/finalizarDictamenInformePericialPeritoAMP.do',
	   		data: 'solicitudId='+solicitudId,
	   		dataType: 'xml',
	   		async: false,
	   		success: function(xml){
	   			var errorCode;
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){	
					
					
					
	   			}
				else{
					//Mostrar mensaje de error
				}
	   		}
	    }); 
		
		
	}
	
	
	/*
	*Funcion que controla la creacion de un documento
	*param:String tipo de documento
	*/
	function controlGenerarDocumento(tipo){	
		//forma = tipo == 'Dictamen'?<%=Formas.DICTAMEN_PERICIAL.getValorId()%>:<%=Formas.INFORME_PERICIAL.getValorId()%>;
		if(tipo == 'Dictamen'){
			forma = <%=Formas.DICTAMEN_PERICIAL.getValorId()%>;
		}
		if(tipo == 'Informe'){
			forma = <%=Formas.INFORME_PERICIAL.getValorId()%>;
		}
		if(tipo == 'DevolucionEvidencia'){
			forma = <%=Formas.DEVOLUCION_DE_EVIDENCIAS.getValorId()%>;
		}
		if(tipo == 'OficioDestruccion'){
			forma = <%=Formas.DESTRUCCION_DE_EVIDENCIA.getValorId()%>;
		}
		if(tipo == 'SolAmpliacionPeriodo'){
			forma = <%=Formas.PRESERVACION_DE_EVIDENCIAS.getValorId()%>;	
		}
		
		var documentoId =crearDocumento(forma);
		var exp = subirExpedienteSesion(forma);
		abrirDocumento(documentoId,exp,tipo);
	}

	/********************************************************FUNCIONALIDAD PARA REALIZAR SOLICITUD DE EVIDENCIA***********************************************/
	
	/*
	*Funcion que abre el visor para generar documentos 
	*/
	function muestraVisorSolicitudDeEvindecia(){

		idWindowSolicitudDeEvidencia++;
		$.newWindow({id:"iframewindowVisorSolicitudDeEvidencia"+idWindowSolicitudDeEvidencia, statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Solicitud de Evidencia", type:"iframe"});
	    $.updateWindowContent("iframewindowVisorSolicitudDeEvidencia"+idWindowSolicitudDeEvidencia,"<iframe src='<%= request.getContextPath() %>/solicitudDeEvidencia.do?rowid="+solicitudId+"' width='1140' height='400' />");
	}

	
/*******************************************************FUNCIONALIDAD PARA LA CEJA DOCUMENTOS PROPIOS**********************************************************/
	/*
	*Funcion que carga el grid con los nombre de los documentos digitales asociados 
	*al id de la solicitud de serv. periciales
	*/
	function cargaGridDocumentosDigitalesPropios(){

		if(primeraVezGridDocumentosDigitalesPropios == true){
			jQuery("#gridDocumentosDigitalesPropios").jqGrid({
				url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+numidExpedienteDoc+'',
				datatype: "xml", 
				colNames:['Área del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
				colModel:[ 	{name:'area',index:'area', width:200},
							{name:'FechaActividad',index:'fechaActividad', width:170},							
							{name:'NombreActividad',index:'nombreActividad', width:400},
				           	{name:'Tipo',index:'tipo', width:155}, 
							{name:'Nombre',index:'nombre', width:255},
				           	{name:'Fecha',index:'fecha', width:170}
							],
				pager: jQuery('#pagerGridDocumentosDigitalesPropios'),
				rowNum:20,
				rowList:[10,20,30],
				width:1100,
				sortname: 'nombreDocumento',
				viewrecords: true,
				sortorder: "desc",
				//multiselect:true,
				ondblClickRow: function(rowid) {
					if (rowid) {
						abrirDocsDigAsociadosASolPropios(rowid);							
					}
				},
				loadComplete: function(){
					jQuery("#gridDocumentosDigitalesPropios").jqGrid('hideCol',["area","fechaActividad","nombreActividad","tipo","fecha"]);
				}
			}).navGrid('#pagerGridDocumentosDigitalesPropios',{edit:false,add:false,del:false});
			$("#gview_gridDocumentosDigitalesPropios .ui-jqgrid-bdiv").css('height', '455px');
			primeraVezGridDocumentosDigitalesPropios= false;
		}
		else{
			jQuery("#gridDocumentosDigitalesPropios").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+numidExpedienteDoc+'',datatype: "xml" });
			$("#gridDocumentosDigitalesPropios").trigger("reloadGrid");
		}
	}

	/*
	*Funcion que abre el PDF para ver los documentos asociados al numero de causa 
	*/
	function abrirDocsDigAsociadosASolPropios(documentoAsocId){				
			// Versión anterior
			// $("#visorDocsPropiosFrame").attr("src","<%= request.getContextPath()%>/ConsultarContenidoArchivoDigital.do?archivoDigitalId="+documentoAsocId+"&inFrame=true");
			//Versión integración
			// $("#visorDocsPropiosFrame").attr("src","<%= request.getContextPath()%>/ConsultarContenidoArchivoDigital.do?documentoId="+documentoAsocId);
			//Versión Nueva
			// $("#visorDocsPropiosFrame").attr("src","<%= request.getContextPath()%>/consultarArchivoDigitalIframe.do?documentoId="+documentoAsocId+"&inFrame=0");
			//Versión de Prueba
			//$("#visorDocsPropiosFrame").attr("src","<%= request.getContextPath()%>/consultarArchivoDigitalIframe.do?documentoId="+documentoAsocId);
			$("#visorDocsPropiosFrame").attr("src","<%= request.getContextPath()%>/ConsultarContenidoArchivoDigital.do?documentoId="+documentoAsocId+"&inFrame=true");
		if(documentoAsocId != null && documentoAsocId != ""){
			$("#visorDocsPropiosFrame").attr("src","<%= request.getContextPath()%>/ConsultarContenidoArchivoDigital.do?documentoId="+documentoAsocId);
		}
		else{
			alert("El documento no puede ser mostrado");
		}
	}
	
	function visorDetalleDocumento(){
		jQuery("#gridDetalleFrmPrincipal").jqGrid({ 
			url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+idExpedienteDoc, 
			datatype: "xml",
			colNames:['Área del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
			colModel:[ 	{name:'area',index:'area', width:200},
						{name:'FechaActividad',index:'fechaActividad', width:170},							
						{name:'NombreActividad',index:'nombreActividad', width:400},
			           	{name:'Tipo',index:'tipo', width:155}, 
						{name:'Nombre',index:'nombre', width:255},
			           	{name:'Fecha',index:'fecha', width:170}
						],
			pager: jQuery('#pager1'),
			rowNum:0,
			rowList:[0,0,0],
			autowidth: false,
			width:1100,
			sortname: 'turno',
			viewrecords: true,
			id: 'divgrid',
			onSelectRow: function(id){
				consultaPDF(id);
				},
			sortorder: "desc"
		}).navGrid('#pager1',{edit:false,add:false,del:false});
		$("#gview_gridDetalleFrmPrincipal .ui-jqgrid-bdiv").css('height', '500px');

		
	}	
</script>

</head>

<body>
<table width="100%">
	<tr>
		<td>
			<div id="tabsPrincipal">
				<ul>
					<li><a href="#tabsconsultaprincipal-1">Solicitud</a></li>
					<li><a href="#tabsconsultaprincipal-5">Evidencia</a></li>
					<li><a href="#tabsconsultaprincipal-2">Documentos</a></li>
					<li><a href="#tabsconsultaprincipal-3">Actuaciones</a></li>
					<li><a href="#tabsconsultaprincipal-4">Documentos Propios</a></li>
				</ul>
				
				<!--Comienza div para los datos de la solicitud-->
				<div id="tabsconsultaprincipal-1">
				
					<fieldset style="width: 700px;">
					<legend>Datos de la solicitud:</legend>
						<table width="100%" border="0" height="90%">
							<tr>
								<td align="right">
									Folio de la Solicitud:
								</td>
								<td>
									<input type="text" class="" size="50" maxlength="50" id="atnServPericialFolioSol" disabled="disabled"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									Especialidad Solicitada:
								</td>
								<td>
									<input type="text" class="" size="50" maxlength="50" id="atnServPericialEspecialidadSol" disabled="disabled"/>
								</td>
							</tr>
							<tr>
								<td align="right" >
									N&uacute;mero de Caso:
								</td>
								<td>
									<input type="text" class="" size="50" maxlength="50" id="atnServPericialNumCaso" disabled="disabled"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									N&uacute;mero de Expediente:
								</td>
								<td>
									<input type="text" class="" size="50" maxlength="50" id="atnServPericialNumExp" disabled="disabled"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									Nombre del Solicitante:
								</td>
								<td>
									<input type="text" class="" size="50" maxlength="50" id="atnServPericialNombSolicitante" disabled="disabled"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									Fecha L&iacute;mite:
								</td>
								<td>
									<input type="text" class="" size="50" maxlength="50" id="atnServPericialFechLim" disabled="disabled"/>
								</td>
							</tr>
						</table>
					</fieldset>
					
				</div>
				<!-- TAB de EVIDENCIAS -->
				<div id="tabsconsultaprincipal-5">
					<table>
						<tr>
						<td>
						<fieldset style="width: 500px;">
						<legend>Evidencias Solicitadas</legend>
						<table>
							<tr>
								<td width="100%">
									<table id="gridDetalleCadenaCustodia"></table>
									<div id="pagerCadenaCustodia"></div>
								</td>
							</tr>
						</table>
						</fieldset>
						</td>
						<td>
						<fieldset style="width: 500px;">
						<legend>Recomendaciones</legend>
						<table width="100%" border="0" height="90%">
							<tr>
					          <td>
					            <textarea id="areaDescripcion" cols="45" rows="5" style="width: 500px; height:200px;" disabled="disabled"></textarea>
				              </td>
					        </tr>
						</table>
						</fieldset>
						</td>
						</tr>
					</table>
				</div>
				<!-- FIN TAB de EVIDENCIAS -->
				<!--Comienza div ver los documentos relacionados a la solicitud-->
				<div id="tabsconsultaprincipal-2">
				
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
		            
				</div>
				<!--Termina div para adjuntar documentos al enviar la solicitud-->
				
				<!--Comienza div para las actuaciones del perito-->
				<div id="tabsconsultaprincipal-3">
				
					<table width="1150" height="550" border="0" cellspacing="0" cellpadding="0">
					  <tr>
					    <td height="20" align="center">
					    	<fieldset style="width: 1150px;">
								<legend>Actuaciones:</legend>
									<table width="100%" border="0" height="90%">
										<tr>
											<td>
												<select id="lstActuaciones" style="width:200px;">
													<option value="0">-Seleccione-</option>
													<option value="1">Anexar documentos</option>
													<option value="2">Realizar dictamen</option>
													<option value="3">Realizar informe</option>
													<option value="4">Solicitud de evidencia</option>
													<option value="5">Devolucion evidencia</option>
													<option value="6">Oficio de destrucci&oacute;n</option>
													<option value="7">Solicitud de ampliaci&oacute;n de periodo</option>
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
					    				<td align="right"><span class="au av ra rc ta" ><strong>Anexar documento digital:</strong></span></td>
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
					       	
					       	<!--Comienza realizar dictamen-->
					    	<div id="realizarDictamen">
								<!--Realizar Dictamen-->
					       	</div>
					       	<!--Comienza realizar dictamen-->
					       	
					       	<!--Comienza realizar informe-->
					    	<div id="realizarInforme">
								<!--Realizar Informe-->
					       	</div>
					       	<!--Comienza realizar informe-->
					       	
					    </td>
					  </tr>
					</table>
										
				</div>
				<!--Termina div para las actuaciones del perito-->
				
				<!--Comienza div para ver los documentos propios del perito-->
				<div id="tabsconsultaprincipal-4">
					
					<table width="1150"  height="530" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="250" align="center" valign="top">
	                        <table id="gridDocumentosDigitalesPropios"></table>
	                        <div id="pagerGridDocumentosDigitalesPropios"></div>
	                       
			
		                 </td>
		                <td width="900" align="center" valign="top">
		               	  <iframe id='visorDocsPropiosFrame' width="900" height="500" src="">		               	  
		               	  </iframe> 
		                </td>
		              </tr>
		            </table>
					
				</div>
				<!--Termina div para ver los documentos propios del perito-->
							
			</div>
		</td>
	</tr>
</table>

</body>
</html>