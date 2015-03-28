<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Atencion a Sollicitudes</title>

	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/multiselect/prettify.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" 	src="<%=request.getContextPath()%>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script src="<%=request.getContextPath()%>/js/prettify.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.multiselect.js"></script>	
	<script type="text/javascript">
	var idAudiencia=<%= request.getAttribute("idEvento")%>;
	$(document).ready(function() {
		$("#tabsprincipalconsulta").tabs();
		
		pintaDetalle();
		});
	
	
	function pintaDetalle(){
		var param;
		param=idAudiencia;
		
		 $.ajax({
		      type: 'POST',
	    	  url: '<%= request.getContextPath()%>/consultarDetalleSolicitudOtros.do',
	    	  data: 'idSolicitud='+param,
	    	  dataType: 'xml',
	    	  async: false,
	    	  success: function(xml){
	    		 //        
		    	$('#numCasoAtencionAudienciaDetalle2').val($(xml).find('solicitudDTO').find('expedienteDTO').find('numeroExpediente').text());

		    	//$('#MPDetalle').val($(xml).find('expedienteDto').find('numeroExpediente').text());

		    	$('#numCasoAtencionAudienciaDetalle').val($(xml).find('solicitudDTO').find('expedienteDTO').find('casoDTO').find('numeroGeneralCaso').text());

		    	$('#institucion').val($(xml).find('solicitudDTO').find('nombreInstitucionSolicitante').text());
		    	//$('#defensorDetalle').val($(xml).find('expedienteDto').find('numeroExpediente').text());
				
				$('#numCasoAtencionAudienciaDetalle4').val($(xml).find('solicitudDTO').find('nombreSolicitante').text());

				 var fecha=$(xml).find('solicitudDTO').find('fechaCreacion').text();
				 var strfecha = obtenerFecha(fecha);
					var strhora = obtenerHora(fecha);
				$('#numCasoAtencionAudienciaDetalle5').val(strfecha);

				
				$('#numCasoAtencionAudienciaDetalle6').val(strhora);
				
				$('#numCasoAtencionAudienciaDetalle7').val($(xml).find('solicitudDTO').find('documentoId').text());
				
				$('#tipisolicitud').val($(xml).find('solicitudDTO').find('tipoSolicitudDTO').find('valor').text());

				$('#idestatus').val($(xml).find('solicitudDTO').find('estatus').find('valor').text());
				  

		    

	    	  }
	    	});
	}

	function obtenerFecha(fecha){
		var fh = fecha.split(" ");
		return fh[0].split("-")[2]+"/"+fh[0].split("-")[1]+"/"+fh[0].split("-")[0];	
	}
	
	function obtenerHora(fecha){
		var fh = fecha.split(" ");
		return fh[1].substring(0,5);
	}
	</script>
</head>
<body>

<div id="tabsprincipalconsulta">
	<ul>
		<li><a href="#tabsconsultaprincipal-1">Detalle de Solicitud</a></li>
		
	</ul>
	<div id="tabsconsultaprincipal-1">
	
			<table width="720" border="0" align="center" cellpadding="0" cellspacing="5">
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td width="26%" align="right"><strong>Número de Caso:</strong></td>
			<td width="24%"><input type="text" id="numCasoAtencionAudienciaDetalle" style="width:150px; border: 0; background:#DDD;" readonly="readonly"/></td>
			<td width="12%"><strong> Solicitud:</strong></td>
			<td width="38%"><input type="text" id="tipisolicitud" style="width:150px; border: 0; background:#DDD;" readonly="readonly">
			<!--<select id="solicitud">
			  <option>Audio y Video</option>
			  <option>Transcripción de Audiencia</option>
			  <option>Otro-Externo</option>
		    </select>--></td>
		</tr>
		<tr>
			<td align="right"><strong>Número de Expediente:</strong></td>
			<td><input type="text" id="numCasoAtencionAudienciaDetalle2" style="width:150px; border: 0; background:#DDD;" readonly="readonly"/></td>
			<td id="estado"><strong>Estado:</strong></td>
			<td id="estadoCampo"><input type="text" id="idestatus" style="width:150px; border: 0; background:#DDD;" readonly="readonly"></td>
		</tr>
		<tr>
			<td align="right"><strong>Institución Solicitante: </strong></td>
			<td><input type="text" id="institucion" style="width:150px; border: 0; background:#DDD;" readonly="readonly">
			<!--  <select id="institucion"><option>Ministerio Publico</option><option>Defensoria</option><option>Otro</option><option>Externo</option></select>--></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td align="right"><strong>Solicitante:</strong></td>
			<td><input type="text" id="numCasoAtencionAudienciaDetalle4" style="width:150px; border: 0; background:#DDD;" readonly="readonly"/></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td align="right"><strong>Fecha de Solicitud:</strong></td>
			<td><input type="text" id="numCasoAtencionAudienciaDetalle5" style="width:150px; border: 0; background:#DDD;" readonly="readonly"/></td>
			<td></td>
			<td><input type="button" value="Atender Solicitud" id="atenderSolicitud" style="display: none;" class="btn_Generico"/></td>
		</tr>
		<tr>
			<td align="right"><strong>Hora de Solicitud:</strong></td>
			<td><input type="text" id="numCasoAtencionAudienciaDetalle6" style="width:150px; border: 0; background:#DDD;" readonly="readonly"/></td>
			<td></td>
			<td></td>
			</tr>
		<tr>
			<td align="right"><strong>Audiencia:</strong></td>
			<td><input type="text" id="numCasoAtencionAudienciaDetalle7" style="width:150px; border: 0; background:#DDD;" readonly="readonly"/></td>
			<td></td>
			<td></td>
			</tr>
			<tr>
				<td align="right">&nbsp;</td>
				<td>&nbsp;</td>
				<td></td>
				<td></td>
			  </tr>
			<tr>
			  <td align="right">&nbsp;</td>
			  <td>&nbsp;</td>
			  <td></td>
			  <td></td>
			  </tr>
			<tr>
			  <td align="right">&nbsp;</td>
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
			  </tr>
	    </table> 
	
	</div>
	
</div>
</body>
</html>