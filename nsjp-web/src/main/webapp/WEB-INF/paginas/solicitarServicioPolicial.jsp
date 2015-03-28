<!-- 
CU
Solicitar Servicio Pericial

Da de alta una solicitud de pericial ya sea Dictamen o Asesoría
Cada una se dirige a un funcionario diferente.


 -->

<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.Puestos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Solicitar Servicio Pericial</title>
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<!--Scrip para el idioma del calendario-->
	<script src="<%=request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>	
	
	<script type="text/javascript">
	
	numeroExpedienteId = <%=request.getParameter("numeroExpedienteId")%>;
	numeroExpediente = '<%=request.getParameter("numeroExpediente")%>';
	//documentoId = 0;

	 // Variable para definir el área de donde proviene la solicitud.
	 // Para Procuraduria el valor es 1
	 // Para Defensoria el valor es 2
	var area = <%=request.getParameter("area")%>;
	var dictamenId = <%= mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes.DICTAMEN.getValorId() %>;
/**Variables**/
	var firstGridDetalleCadenaCustodia = true;

	var puestoId;	
	
/******Valiables para la ceja de documentos******/
	var primeraVezGridDocumentosDigitales = true;
		
	$(document).ready(function() {
		
		//verifica si el numero de expediente es nulo
		//verificaExpediente(numeroExpedienteId);
		
		$("#tabsPrincipal").tabs();
		$("#guardarNarraTiva").css("display", "none");
		$("#solServPericialNumExpediente").val(numeroExpediente);
		$("#solServPericialTipoServ").change(consultaFuncionario);
		$("#btnEnviarSolicitud").click(enviarSolicitud);
		//$("#solServPericialConsCadenaCustodia").click(gridCadenaCustodiaInicial);
		$("#solServPericialAddCadenaCustodia").click(addCadenaRegistrada);

		$("#solServPericialAddCadenaCustodia").click(agregarEvidencias);
		$("#solServPericialDelCadenaCustodia").click(eliminarEvidencias);

		$("#solServPericialFechaLimite").attr('readonly','readonly');
		ocultaMuestraTabVisor("tabsconsultaprincipal-5",0);
		
		if(area == 1){
			$('#solServPericialTipoServ').find("option[value='"+dictamenId+"']").attr("selected","selected");
			$('#solServPericialTipoServ').attr('disabled','disabled');
		}
				
		//Agregar estilo del date picker para seleccionar anio y mes
		$("#solServPericialFechaLimite").datepicker({
			dateFormat: 'dd/mm/yy',
			yearRange: '1900:2100',
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true			
		});
		
		//Carga los datos del usuario firmado
		cargaDatosDelUsuario();
		cargaEspecialidadPericial();
		cargaFechaCaptura();
		gridCadenaCustodiaInicial(numeroExpedienteId,0);
		gridCadenaCustodiaAsignados();
		consultaFuncionario();
		
	/******Valiables para la ceja de documentos******/
		//Carga los documentos adjuntos que se adjuntaran a la solicitud de periciales
		cargaGridDocumentosDigitales();
		
	});

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
	
	/**
	* Carga el funcionario a mostrar en la tab de Avisar a Funcionario según puesto del destinatario
	*/
	function consultaFuncionario(){
		
		tipoServicio = $("#solServPericialTipoServ option:selected").val();
		
		if(tipoServicio == '<%=TiposSolicitudes.ASESORIA.getValorId()%>'){
			puestoId = <%=Puestos.COORDINADOR_PERICIAL_DE_DEFENSORIA.getValorId()%>;	
		}else if(tipoServicio == '<%=TiposSolicitudes.DICTAMEN.getValorId()%>'){
			if(area == 1){
				puestoId = <%=Puestos.COORDINADOR_DE_SERVICIOS_PERICIALES.getValorId()%>;
			}else{
				puestoId = <%=Puestos.COORDINADOR_DE_DEFENSORES.getValorId()%>;
			}
		}
		puestoId= <%=Puestos.COMANDANTE_POLICIA_MINISTERIAL.getValorId()%>;	
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultarFuncionarioANotificar.do',
    		data: 'puestoId='+puestoId,
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var errorCode;
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){	
					$('#solServPericialNombreFuncionario').val($(xml).find('nombreFuncionario').first().text() + " " +
							$(xml).find('apellidoPaternoFuncionario').first().text() + " "+
							$(xml).find('apellidoMaternoFuncionario').first().text());
					$('#solServPericialPuestoFuncionario').val($(xml).find('puesto').find('valor').text());
    			}
				else{
					//Mostrar mensaje de error
				}
    		}
    	});
		/*}else{
			//limpiar campo
			$('#solServPericialNombreFuncionario').val("");
		}*/
		
	}

	/*Funcion que dispara el Action para consultar los datos de usuario*/
    function cargaDatosDelUsuario(){
    	
    	$('#solServPericialAreaAdmin').val('');
		$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultarDatosUsuario.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){

    			var errorCode;
				errorCode=$(xml).find('response').find('code').text();				
				if(parseInt(errorCode)==0){	
					$('#solServPericialAreaAdmin').val( $(xml).find('usuarioDTO').find('areaActual').find('nombre').first().text());
					$('#solServPericialNombre').val(
							$(xml).find('usuarioDTO').find('funcionario').find('nombreFuncionario').first().text() + " " +
							$(xml).find('usuarioDTO').find('funcionario').find('apellidoPaternoFuncionario').first().text() + " "+
							$(xml).find('usuarioDTO').find('funcionario').find('apellidoMaternoFuncionario').first().text());
					$('#solServPericialPuesto').val($(xml).find('usuarioDTO').find('funcionario').find('puesto').find('valor').first().text() );
					
    			}
				else{
					//Mostrar mensaje de error
				}
    		}
    	});
    }

    /*Funcion que dispara el Action para consultar Las especialidades Pericialess*/		
    function cargaEspecialidadPericial(){
    	
    	  	$.ajax({
    		type: 'POST',
    		url: '<%=request.getContextPath()%>/consultarCatalogoEspecialidadPericial.do',
    		data: '',
    		dataType: 'xml',
    		async: false,
    		success: function(xml){
    			var option;
    			
    			$(xml).find('catEspecialidadPericial').each(function(){
    				
    				$('#solServPericialEspecialidad').append('<option value="' + $(this).find('clave').text() + '">'+ $(this).find('valor').text() + '</option>');
    			});
    		}
    	});
    }

	function cargaFechaCaptura(){
    	$.ajax({
    		type: 'POST',
    	    url: '<%=request.getContextPath()%>/ConsultarFechaCaptura.do',
    	    data: '',
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    			$('#solServPericialFecha').val($(xml).find('fechaActual').text());
    		}
		});
    }

	/*Funcion que cierra la ventana*/
	function cerrarVentana(){
		parent.cerrarVentana();
	}

	/*
	*Funcion que obtiene toda la informacion para enviar la solicitud pericial
	*/
	function enviarSolicitud(){

		//numeroExpediente = + $('#solServPericialNumExpediente').val();
		tipoServicio = $("#solServPericialTipoServ option:selected").val();
		especialidad = $("#solServPericialEspecialidad option:selected").val();
		estudios = $("#solServPericialEstudios option:selected").val();
		fechaLimite = $("#solServPericialFechaLimite").val();
		arrayIds = jQuery("#gridDetalleCadenaCustodiaAsignados").getDataIDs();
		observaciones = $("#areaDescripcion").val();
		//Obtiene los ids de los documentos que seran visibles para la solicitud
		idsDoctsSelecc = obtenerDocumentosSeleccionados();
		
		if(fechaLimite == "" || tipoServicio == 0 ){
			alert("Debe introducir valor para los siguientes campos:\n*Tipo de Servicio\n*Fecha Límite");
		}else{
			arrayIds="1";
			especialidad="1970";
			//Datos de la ceja Solicitante
			var parametros = '&numeroExpedienteId=' + numeroExpedienteId;
			parametros += '&tipoServicio=' + tipoServicio;
			parametros += '&especialidad=' + especialidad;
			parametros += '&estudios=' + estudios;
			parametros += '&fechaLimite=' +  fechaLimite;
			parametros += '&arrayIds=' +  arrayIds;
			parametros += '&area=' +  area;
			parametros += '&observaciones=' + observaciones;
			parametros += '&destinatarioId=' + puestoId;
			parametros += '&idsDoctsSelecc=' + idsDoctsSelecc;

			if(idsDoctsSelecc.length < 1){
				if(confirm("No ha anexado documentos,\n¿Desea continuar enviando la solicitud?")) {									
					guardarSolicitud(parametros);
				}
			}
			else{						
				guardarSolicitud(parametros);
			}
		}
	}

	/*
	*Funcion que guarda la solicitud pericial
	*/
	function guardarSolicitud(parametros){
		$.ajax({
			///enviarSolicitudServicioPericial.do
    		type: 'POST',
    	    url:'<%=request.getContextPath()%>/enviarEvidenciasSolicitudServicioPericial.do',
    	    data:parametros,
    	    dataType: 'xml',
    	    async: false,
    	    success: function(xml){
    	    	var errorCode;
				errorCode=$(xml).find('response').find('code').text();
				if(parseInt(errorCode)==0){
					alert("La solicitud fue enviada correctamente");
				}else{
					//alert("La solicitud fue enviada correctamente");
				}
    			parent.cerrarVentana();
    			parent.cargaGridEvidenciasNuevas();
    		}
		});
	}

	
	function gridCadenaCustodiaInicial(){
		var folioCadenaCustodia = $("#solServPericialCadenaCustodia").val();

		if(firstGridDetalleCadenaCustodia == true){
		jQuery("#gridDetalleCadenaCustodia").jqGrid({ 
			url:'<%= request.getContextPath()%>/consultarCadenaCustodiaXNumeroExpediente.do?numeroExpedienteId='+numeroExpedienteId+'&folioCadenaCustodia='+folioCadenaCustodia+'', 
			data:'',
			datatype: "xml", 
			colNames:['Número de Evidencia','Cadena de Custodia','Objeto','Código de Barras'],
			colModel:[ {name:'NumeroEvidencia',index:'numeroEvidencia', width:150},
						{name:'CadenaCustodia',index:'cadenaCustodia', width:150},
						{name:'Objeto',index:'objeto', width:150},
						{name:'CodigoBarras',index:'codigoBarras', width:150}
			],
			pager: jQuery('#pagerCadenaCustodia'),
			rowNum:10,
			rowList:[10,20,30],
			autowidth: true,
			sortname: 'CadenaCustodia',
			viewrecords: true,
			sortorder: "asc",
			multiselect: true
			}).navGrid('#pagerCadenaCustodia',{edit:false,add:false,del:false});

			firstGridDetalleCadenaCustodia=false;
		}else{
			jQuery("#gridDetalleCadenaCustodia").jqGrid('setGridParam', {url:'<%= request.getContextPath()%>/consultarCadenaCustodiaXNumeroExpediente.do?numeroExpedienteId='+numeroExpedienteId+'&folioCadenaCustodia='+folioCadenaCustodia+'',datatype: "xml" });
			$("#gridDetalleCadenaCustodia").trigger("reloadGrid");			
		}
	}
	
	function gridCadenaCustodiaAsignados(){
		jQuery("#gridDetalleCadenaCustodiaAsignados").jqGrid({ 
			url:'local', 
			data:'',
			datatype: "xml", 
			colNames:['Número de Evidencia','Cadena de Custodia','Objeto','Código de Barras'],
			colModel:[ {name:'NumeroEvidencia',index:'numeroEvidencia', width:150},
						{name:'CadenaCustodia',index:'cadenaCustodia', width:150},
						{name:'Objeto',index:'objeto', width:150},
						{name:'CodigoBarras',index:'codigoBarras', width:150}
			],
			pager: jQuery('#pagerCadenaCustodiaAsignados'),
			rowNum:50,
			rowList:[50],
			autowidth: true,
			sortname: 'CadenaCustodia',
			viewrecords: true,
			sortorder: "asc",
			multiselect: true
			}).navGrid('#pagerCadenaCustodiaAsignados',{edit:false,add:false,del:false});
		}
		
    function addCadenaRegistrada(){
		var selecciones = jQuery("#gridDetalleCadenaCustodia").jqGrid('getGridParam','selarrrow');
		if (selecciones) {
			selecciones = selecciones +"";
			var id = selecciones.split(",");
			var registrosSeleccionados = "(";
			for(var a= 0; a<id.length;a++){
				var ret = jQuery("#gridDetalleCadenaCustodia").jqGrid('getRowData',id[a]);
				var numEvidencia =  ret.NumeroEvidencia;
				numEvidencia = numEvidencia.substring(numEvidencia.length-9,numEvidencia.length-6);
				registrosSeleccionados = registrosSeleccionados + numEvidencia + ",";
			}
			registrosSeleccionados  = registrosSeleccionados.substring(0, registrosSeleccionados.length - 1);
			registrosSeleccionados = registrosSeleccionados + ')';
			$('#solServPericialCadenasRegistradas').append('<option>'+$('#solServPericialCadenaCustodia').val() + registrosSeleccionados +'</option>');
		} else {
			alert("Seleccione un registro");
		} 
		
	}

    //Llena el combo de estudios en base a la especialidad seleccionada
	function buscaEstudios(){
		var selected = $("#solServPericialEspecialidad option:selected").val();
		$( "#solServPericialEstudios" ).attr('selectedIndex',0);
		$('#solServPericialEstudios').empty();
		$('#solServPericialEstudios').append('<option value="0">-Seleccione-</option>');
		
		$.ajax({
			async: false,									// la accion cargar los estudios
			type: 'POST',
			url: '<%= request.getContextPath()%>/consultarCatalogoEstudiosXEspecialidad.do?especialidadId='+selected+'',
			dataType: 'xml',
			success: function(xml){
				$(xml).find('estudio').each(function(){
					$('#solServPericialEstudios').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});
			}
		});
	}

	/*
	*Funcion que agrega una evidencia del grid de evidencias al grid de evidencias asignadas
	*/
	function agregarEvidencias(){
		//var row = jQuery("#gridDetalleCadenaCustodia").jqGrid('getGridParam','selrow');
		var arraySelRows = jQuery("#gridDetalleCadenaCustodia").getGridParam('selarrrow');

		if (arraySelRows.length > 0) { 
			for(i=0; i<arraySelRows.length; i++){
				var ret = jQuery("#gridDetalleCadenaCustodia").jqGrid('getRowData',arraySelRows[i]);
				var arrayIds = jQuery("#gridDetalleCadenaCustodiaAsignados").getDataIDs();
				var cont=0;
				while(cont<arrayIds.length){
					if(arraySelRows[i] == arrayIds[cont]){
						alert("La evidencia ya se encuentra asignada");
						break;
					}
					cont++;		
				}
				if(cont==arrayIds.length){
					jQuery("#gridDetalleCadenaCustodiaAsignados").jqGrid('addRowData',arraySelRows[i],ret);
					//jQuery("#gridDetalleCadenaCustodia").jqGrid('delRowData',arraySelRows[i]);
				}				
			}
		} 
		else{
			 alert("Por favor seleccione un renglon");
		} 		
	} 

	/*
	*Funcion que elimina una evidencia del grid de evidencias seleccionadas
	*/
	function eliminarEvidencias(){
		var arraySelRows = new Array();
		arraySelRows  = jQuery("#gridDetalleCadenaCustodiaAsignados").getGridParam('selarrrow');

		if (arraySelRows.length > 0) {
			for(var i=arraySelRows.length-1; i>=0; i--){
				jQuery("#gridDetalleCadenaCustodiaAsignados").jqGrid('delRowData',arraySelRows[i]);
			}
		} 
		else{
			 alert("Por favor seleccione un renglon");
		} 	
	}


/****************************************************************FUNCIONALIDAD PARA LA CEJA DOCUMENTOS************************************************************/
	/*
	*Funcion que carga el grid con los nombre de los documentos digitales asociados 
	*al id de la solicitud de serv. periciales
	*/
	function cargaGridDocumentosDigitales(){

		if(primeraVezGridDocumentosDigitales == true){
			jQuery("#gridDocumentosDigitales").jqGrid({
				url:'<%=request.getContextPath()%>/consultarDocumentosPorOficioInvestigacionPolicial.do?idExpedienteop='+numeroExpedienteId+'',
				datatype: "xml", 
				colNames:['Área del responsable','Fecha de la actividad','Nombre de la actividad','Tipo de documento','Nombre de Documento','Fecha del documento'],
				colModel:[ 	{name:'area',index:'area', width:200},
							{name:'fechaActividad',index:'fechaActividad', width:170},							
							{name:'nombreActividad',index:'nombreActividad', width:400},
				           	{name:'tipo',index:'tipo', width:155}, 
							{name:'nombre',index:'nombre', width:255},
				           	{name:'fecha',index:'fecha', width:170}
							],
				pager: jQuery('#pagerGridDocumentosDigitales'),
				rowNum:20,
				rowList:[10,20,30],
				width:250,
				sortname: 'nombreDocumento',
				viewrecords: true,
				sortorder: "desc",
				multiselect:true,
				ondblClickRow: function(rowid) {
					if (rowid) {
						abrirDocsDigAsociadosASol(rowid);							
					}
				},
				loadComplete: function(){
					jQuery("#gridDocumentosDigitales").jqGrid('hideCol',["area","fechaActividad","nombreActividad","tipo","fecha"]);
				}
			}).navGrid('#pagerGridDocumentosDigitales',{edit:false,add:false,del:false});
			$("#gview_gridDocumentosDigitales .ui-jqgrid-bdiv").css('height', '455px');
			primeraVezGridDocumentosDigitales= false;
		}
		else{
			jQuery("#gridDocumentosDigitales").jqGrid('setGridParam', {url:'<%=request.getContextPath()%>/consultarDocumentos.do?idExpedienteop='+numeroExpedienteId+'',datatype: "xml" });
			$("#gridDocumentosDigitales").trigger("reloadGrid");
		}
	}

	/*
	*Funcion que abre el PDF para ver los documentos asociados al numero de causa
	*/
	function abrirDocsDigAsociadosASol(documentoId){
		if(documentoId != null && documentoId != ""){
			$("#visorDocsFrame").attr("src","<%= request.getContextPath()%>/ConsultarContenidoArchivoDigital.do?documentoId="+documentoId+"&inFrame=true");
		}
		else{
			alert("El documento no puede ser mostrado");
		}
	}

	/*
	*Funcion que obtiene los ids de los documentos seleccionados, para relacionarlos
	*con la solicitud, y sean visibles para el perito
	*/
	function obtenerDocumentosSeleccionados(){
		var arraySelRows = jQuery("#gridDocumentosDigitales").getGridParam('selarrrow');
		//alert(arraySelRows);
		return arraySelRows;
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
					<li class="tabsconsultaprincipal-5"><a href="#tabsconsultaprincipal-5">Evidencia</a></li>
					<li><a href="#tabsconsultaprincipal-2">Documentos</a></li>
					<li><a href="#tabsconsultaprincipal-4">Enviar Solicitud</a></li>
				</ul>
				<div id="tabsconsultaprincipal-1">
				<fieldset style="width: 700px;">
				<legend>Responsable</legend>
					<table width="100%" border="0" height="90%">
						<tr>
							<td align="right">
								Nombre del servidor P&uacute;blico:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solServPericialNombre" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right" >
								Cargo:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solServPericialPuesto" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								&Aacute;rea Administrativa:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solServPericialAreaAdmin" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Fecha de Solicitud:
							</td>
							<td>
								<input type="text" class="" size="50" maxlength="50" id="solServPericialFecha" disabled="disabled"/>
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset style="width: 700px;">
				<legend>Datos de la Solicitud</legend>
					<table width="100%" border="0" height="90%">
						<tr>
							<td align="right">
								Número de Expediente:
							</td>
							<td>
								<input type="text" size="50" maxlength="50" id="solServPericialNumExpediente" disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								Tipo de servicio:
							</td>
							<td>
								<select id="solServPericialTipoServ" style="width: 180px;">
									<option value="<%= mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes.POLICIA_MINISTERIAL.getValorId() %>">Policía Ministerial</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								Fecha L&iacute;mite:
							</td>
							<td>
								<input type="text" id="solServPericialFechaLimite" size="50" maxlength="50"/>
							</td>
						</tr>
						
					</table>					
				</fieldset>
				</div>
				
				<!--Comienza div para adjuntar documentos al enviar la solicitud-->
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
				
				<div id="tabsconsultaprincipal-5" class="tabsconsultaprincipal-5">
					<table width="100%" border="0">
						<tr>
							<td align="right">
								Cadena de custodia:
							</td>
							<td colspan="2">
								<input type="text" size="50" maxlength="50" id="solServPericialCadenaCustodia"/>
								<input type="button" id="solServPericialConsCadenaCustodia" value="Consultar" onclick="gridCadenaCustodiaInicial()" class="btn_Generico"/>
							</td>
				          <td>Recomendaciones:</td>
				          							
						</tr>
						<tr>
							<td colspan="3">
								<table id="gridDetalleCadenaCustodia"></table>
								<div id="pagerCadenaCustodia"></div>
							</td>
							<!--<td>
								Cadenas Registradas:<br/>
								<select id="solServPericialCadenasRegistradas" size="3" style="width: 180px;"></select>
							</td>-->

				          <td>
				            <textarea id="areaDescripcion" cols="45" rows="5" style="width: 500px; height:200px;" ></textarea>
			              </td>											
													
						</tr>
						<tr>
							<td colspan="3">
								<table id="gridDetalleCadenaCustodiaAsignados"></table>
								<div id="pagerCadenaCustodiaAsignados"></div>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="button" id="solServPericialAddCadenaCustodia" value="Agregar Evidencia" class="btn_Generico"/>
								<input type="button" id="solServPericialDelCadenaCustodia" value="Eliminar Evidencia" class="btn_Generico"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="tabsconsultaprincipal-4">
					<table width="45%" border="0" height="90%">
				        <tr>
				        	<td align="right">Nombre Funcionario:</td>
				            <td align="left">
				                <input type="text" class="" size="50" maxlength="50" id="solServPericialNombreFuncionario" disabled="disabled"/>
				            </td>
				        </tr>
				        <tr>
				        	<td align="right">Puesto:</td>
				            <td align="left">				                
				                <input type="text" class="" size="50" maxlength="50" id="solServPericialPuestoFuncionario" disabled="disabled"/>
				            </td>
				        </tr>
				        <tr>
				        	<td>&nbsp;</td>
				        </tr>
						<tr>
							<td align="left">
								<input type="button" id="btnEnviarSolicitud" value="Enviar Solicitud" class="btn_Generico">
							</td>
						</tr>
				    </table>
				</div>
			</div>
		</td>
	</tr>
</table>

	<!--Forma asociada para mostrar los documento de transcripcion de audiencia-->
<!--	<form name="frmDocDigital" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do?archivoDigitalId='+documentoId+'&inFrame=true" method="post">-->
<!--		<input type="hidden" name="archivoDigitalId" value=""/>														-->
<!--	</form>-->
	
</body>
</html>