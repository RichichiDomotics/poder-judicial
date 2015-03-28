<%@page
	import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atencion a Sollicitudes</title>


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/estilos.css"
	media="screen" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />


<!--Se importan los scripts necesarios-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
<script type="text/javascript">
	/**
	* Variables para solicitudes de audiencia
	*/
	var tipoAudGlob;
	var fechaGlob;
	var idNuevaCalidad;
	var idInvolucrado;
	idAudiencia = <%=request.getParameter("idAudiencia")%>;	    
	$(document).ready(function() {

		
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
					$(xml).find('list').find('involucradoViewDTO').each(function(){
						$('#juecesAudienciaPJENS').append('<option value="' + $(this).find('involucradoId').text() + '">' + $(this).find('nombre').text()+" "+$(this).find('apellidoPaterno').text()+" "+$(this).find('apellidoMaterno').text() + '</option>');
						$('#juecesAudienciaIntervPJENC').append('<option value="' + $(this).find('involucradoId').text() + '">' + $(this).find('nombre').text()+" "+$(this).find('apellidoPaterno').text()+" "+$(this).find('apellidoMaterno').text() + '</option>');
						$('#juecesAudienciaMandamientosPJENC').append('<option value="' + $(this).find('involucradoId').text() + '">' + $(this).find('nombre').text()+" "+$(this).find('apellidoPaterno').text()+" "+$(this).find('apellidoMaterno').text() + '</option>');
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
				rowList:[10,20,30],
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
				rowList:[10,20,30],
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
			rowList:[10,20,30],
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
	                   
	                    //alert(cargaMinutos);
	                   
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
		
		$("#divTipoMandamiento").dialog("open");
	  	$("#divTipoMandamiento").dialog({ autoOpen: true, 
			modal: true, 
		  	title: 'Tipo de Mandamiento', 
		  	dialogClass: 'alert',
		  	position: [500,220],
		  	width: 450,
		  	height: 260,
		  	maxWidth: 350,
		  	buttons:{"Aceptar":function() {
		  		tipoMandamiento = $('#tipoMandamiento option:selected').val();
				if(tipoMandamiento == 0){
					alertDinamico("Seleccione un tipo de mandamiento");
					return;
				}
		  		enviarMandamiento(tipoMandamiento);	
				
		  		$(this).dialog("close");
		  		},
				"Cancelar" : function() {
					$(this).dialog("close");
				}
		  	}
		});	 
				
	  	
		
	}
	/*
	* Complementa la función de creación de un nuevo mandamiento judicial
	*/
	function enviarMandamiento(tipo){
		resolutivo = jQuery("#gridAudienciasResolutivosPJENC").jqGrid('getGridParam','selrow');
		$.ajax({
			type: 'POST',
			url: '<%=request.getContextPath()%>/crearMandamientoJudicial.do?resolutivoId='+resolutivo+'&tipoMandamiento='+tipo,
			data: '', 
			async: false,
			dataType: 'xml',
			success: function(xml){
					
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
				        		"<iframe src='<%=request.getContextPath()%>/generarDocumentoSinCaso.do?documentoId="+documentoId+"&numeroUnicoExpediente="+numExp+"' width='1140' height='400' />");
				       
						
						
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

	
	function actualizarSituacionJuridica(){

		var parametros = delitosPersonaAOrdenar();

		$.ajax({
			async: true,
			type: 'POST',
			url: '<%=request.getContextPath()%>/actualizarSituacionJuridica.do',
			data: 'parametros='+parametros,
			dataType: 'xml',
			success: function(xml){			
			}
		});
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
		
		arrayIds = jQuery("#gridProbResponsablePJENC").getDataIDs();
		//alert(arrayIds);
		$('select[id^="delito_"]').each(function(){
			id = $(this).attr("id");
			//id.split("_");
			//alert(id.split("_")[1]);
			solicitudesAOrdenar += arrayIds[i]+"_"+id.split("_")[1]+ "_" + $(this).val()+ ",";
			i++;
		});
		return solicitudesAOrdenar; 
	}

	
		


	
	
	</script>
</head>
<body>
	<form name="verMandamientoForm" id="verMandamientoForm"
		action="<%=request.getContextPath()%>/ConsultarContenidoArchivoDigital.do"
		method="post">
		<input type="hidden" name="documentoId" />
	</form>

	
	
		<!--Termina Div Principal 2-->
		<div id="tabsconsultaprincipal-2">

			<table>
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
		
		<!-- div para el alert dinamico -->
	<div id="dialog-Alert" style="display: none">
		<table align="center">
			<tr>
				<td align="center"><span id="divAlertTexto"></span></td>
			</tr>
		</table>
	</div>
		
</body>
</html>