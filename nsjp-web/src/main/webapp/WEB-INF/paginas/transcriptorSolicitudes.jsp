<%@page import="mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Administracion de Audiencia Informatica</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.windows-engine.css" />

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/comun.js"></script>
	
	<script type="text/javascript">

	var documentoId;
	var archivoDigitalId = "";
	var estatus;
	
	numeroExpediente = "";
	
	$(document).ready(function() {
		
		solicitudId = "<%= request.getParameter("solicitudId")%>"; 
		solicitante = "<%= request.getParameter("solicitante")%>";
		
		$("#tabsprincipalconsulta" ).tabs();
		$("#generarDocumento").click(generarDocumentoView);
		$("#verDocumento").click(abrirPDF);
		
		//obtenemos el id de la solicitud para recuperar el detalle y el documento
		
		//se consulta el detalle de la solicitud
		consultarDetalleSolicitudDeTranscripcion(solicitudId);

		/*
		*Obtenemos el estatus para saber si generamos el documento o 
		*o solo lo abrimos en modo de consulta
		*/
		if(estatus == "<%=EstatusSolicitud.EN_PROCESO.getValorId()%>" 
			|| estatus == "<%=EstatusSolicitud.ABIERTA.getValorId()%>"){
						
			$( "#generarDocumento" ).show();
			$( "#verDocumento" ).hide();
		}
		else{
			$( "#generarDocumento" ).hide();
			$( "#verDocumento" ).show();
		}
	});

	//Funcion usada para generar un documento
	function generarDocumentoView() {

		//alert("solicitudId="+solicitudId);
		//alert("numeroExpediente="+numeroExpediente);
		
		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Transcripcion de Audiencia", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumento",
	    		'<iframe src="<%=request.getContextPath()%>/generarDocumentoSinCaso.do?documentoId='+solicitudId+'&numeroUnicoExpediente='+numeroExpediente+'" width="1140" height="400" />');
	    		
	}


	/**
	*Funcion que consulta el detalle de la solicitud de transcripcion
	*/
	function consultarDetalleSolicitudDeTranscripcion(solicitudId){
		

		if(solicitudId != null){
			
			$.ajax({
				type: 'POST',
				url: '<%= request.getContextPath()%>/consultarDetalleSolicitudTranscripcionAudioVideo.do',
				data: 'solicitudId='+ solicitudId, 
				async: false,
				dataType: 'xml',
				success: function(xml){
					var errorCode;
					errorCode=$(xml).find('response').find('code').text();
					if(parseInt(errorCode)==0){
		  
						limpiaDatosDetalleSolicitud();
	    				//Mostramos los campos con el detalle de la solicitud		
	    				$("#numCaso").val($(xml).find('numeroGeneralCaso').first().text());
	    				$("#numCausa").val($(xml).find('numeroExpediente').first().text());
	    				numeroExpediente = $(xml).find('numeroExpediente').first().text();
						var fechaSol = $(xml).find('strFechaCreacion').first().text();
						var horaSol = $(xml).find('strHoraCreacion').first().text();
	    				
	    				$("#fechHoraSol").val(fechaSol+" "+horaSol);
	    				$("#nombreSolicitante").val($(xml).find('nombreSolicitante').first().text());
	    				$("#institucionSolicitante").val($(xml).find('solicitudTranscripcionAudienciaDTO').find('institucion').find('nombreInst').first().text());
	    				$("#tipoAudiencia").val($(xml).find('tipoAudiencia').find('valor').first().text());
	    				$("#audienciaId").val($(xml).find('audiencia').find('id').first().text());
	    				var fechaIniAud = $(xml).find('audiencia').find('strFechaInicio').first().text();
	    				var horaIniAud = $(xml).find('audiencia').find('strHoraInicio').first().text();
	    				
	    				$("#fechHoraInicioAudiencia").val(fechaIniAud+" "+horaIniAud);
	    				
	    				var fechaFinAud = $(xml).find('audiencia').find('strFechaFin').first().text();
	    				var horaFinAud = $(xml).find('audiencia').find('strHoraFin').first().text();
	    				
	    				$("#fechHoraFinAudiencia").val(fechaFinAud+" "+horaFinAud);

	    				$(xml).find('solicitudTranscripcionAudienciaDTO').find('expedienteDTO').find('estatus').remove();
	    				
	    				estatus = $(xml).find('solicitudTranscripcionAudienciaDTO').find('estatus').find('idCampo').first().text();
	    				
	    				documentoId = $(xml).find('documentoId').first().text();
	    				archivoDigitalId = $(xml).find('archivoDigitalId').first().text();
	    			}
					else{
						//Mostrar mensaje de error
					}
				}
			});		
		}
		else{
			//alert("NO ESXISTE ID DE LA AUDIENCIA ");
		}	
	}

	/*
	*Funcion que limpia los datos de la solicitud
	*/
	function limpiaDatosDetalleSolicitud(){

		$("#numCaso").val("");
		$("#numCausa").val("");
		$("#fechHoraSol").val("");
		$("#nombreSolicitante").val("");
		$("#institucionSolicitante").val("");
		$("#tipoAudiencia").val("");
		$("#audienciaId").val("");
		$("#fechHoraInicioAudiencia").val("");
		$("#fechHoraFinAudiencia").val("");
	}

	/*
	*Funcion que abre el PDF para ver el documento  
	*de transcripcion de audiencia
	*/
	function abrirPDF(){
		
		if(archivoDigitalId != null && archivoDigitalId != ""){
			document.frmDoc.archivoDigitalId.value = archivoDigitalId;
			document.frmDoc.submit();
		}
	}
	
	/*
	* Actualiza el estado de la solicitud al terminar de generar el documento
	*
	*/
	function documentoGenerado(){
		
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/finalizarSolicitudTranscripcionAudioVideo.do',
			data: 'solicitudId='+ solicitudId, 
			async: false,
			dataType: 'xml',
			success: function(xml){
				var errorCode;
				alertDinamico("FINALIZA LA TRANSCRIPCION");
				
			}
		});		
	}

	</script>
</head>
<body>

<div id="tabsprincipalconsulta">
	<ul>
		<li><a href="#tabsconsultaprincipal-1">Generales</a></li>		
	</ul>
	<div id="tabsconsultaprincipal-1" style="height: 400">
		<table width="858" border="0" cellspacing="5" cellpadding="0">
		    <tr>
		        <td width="22%" align="right"><strong>N&uacute;mero de Caso:</strong></td>
		      <td width="25%" align="left"><input type="text" id="numCaso" style="width: 200px; border: 0; background: #DDD;"	readonly="readonly" /></td>
		        <td width="26%" align="right"><strong>Institución:</strong></td>
		        <td width="27%"><input type="text" id="institucionSolicitante" style="width: 200px; border: 0; background: #DDD;"	readonly="readonly" /></td>
		    </tr>
		    <tr>
		        <td align="right"><strong>N&uacute;mero de Causa:</strong></td>
		      <td align="left"><input type="text" id="numCausa" style="width: 200px; border: 0; background: #DDD;" readonly="readonly"  /></td>
		        <td align="right"><strong>Tipo de Audiencia:</strong></td>
		        <td ><input type="text" id="tipoAudiencia" style="width: 200px; border: 0; background: #DDD;"	readonly="readonly" /></td>
		    </tr>
		    <tr>
		        <td align="right"><strong>Fecha/Hora Solicitud:</strong></td>
		      <td align="left"><input type="text" id="fechHoraSol" style="width: 200px; border: 0; background: #DDD;"	readonly="readonly" /></td>
		        <td align="right"><strong>Identificador Audiencia:</strong></td>
		        <td><input type="text" id="audienciaId" style="width: 200px; border: 0; background: #DDD;"	readonly="readonly" /></td>
		    </tr>
		    <tr>
		        <td align="right"><strong>Solicitante:</strong></td>
		      <td align="left"><input type="text" id="nombreSolicitante" style="width: 200px; border: 0; background: #DDD;" readonly="readonly"  /></td>
		        <td align="right"><strong>Fecha/Hora Inicio Audiencia:</strong></td>
		        <td><input type="text" id="fechHoraInicioAudiencia" style="width: 200px; border: 0; background: #DDD;"	readonly="readonly" /></td>        
		    </tr>
		    <tr>
		        <td align="right">
		        </td>
		      	<td align="left">
		      	</td>
		        <td align="right"><strong>Fecha/Hora Fin Audiencia:</strong></td>
		        <td><input type="text" id="fechHoraFinAudiencia" style="width: 200px; border: 0; background: #DDD;"	readonly="readonly" /></td>
		    </tr>
		    <tr>
		        <td align="center">&nbsp;</td>
		        <td align="center">&nbsp;</td>
		        <td align="center">
		            <div>
		                <input type="button" id="generarDocumento" value="Generar Documento" class="btn_Generico"/>
		            </div>
		            <div>
		                <input type="button" id="verDocumento" value="ver Documento" class="btn_Generico"/>
		            </div>
		        </td>
		        <td align="center">&nbsp;</td>
		    </tr>
		</table>
	</div>
</div>

<!--Forma asociada para mostrar los documento de transcripcion de audiencia-->
<form name="frmDoc" action="<%= request.getContextPath() %>/ConsultarContenidoArchivoDigital.do" method="post">
	<input type="hidden" name="archivoDigitalId" value=""/>														
</form>

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