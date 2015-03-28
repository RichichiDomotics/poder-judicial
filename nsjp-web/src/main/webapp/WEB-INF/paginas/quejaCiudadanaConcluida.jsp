<%@page import="mx.gob.segob.nsjp.comun.enums.funcionario.Puestos"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.actividad.Actividades"%>
<%@page import="mx.gob.segob.nsjp.comun.enums.forma.Formas"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Queja Ciudadana Concluida</title>

<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/estilos.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/layout_complex.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css" />

<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/js/prettify.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
<script type="text/javascript"
	src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		

<script type="text/javascript">
	
	
	var idQueja;
	
	$(document).ready(function() {
		idQueja ='<%= request.getParameter("idQueja")%>';
		$("#tabsPrincipal").tabs();
		
		var config = {toolbar:
			[
				['Source','-','Cut','Copy','Paste','-','Undo','Redo','-','Find','Replace','-','RemoveFormat'],
				['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
				['NumberedList','BulletedList','-','Outdent','Indent'],
				['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
				['Table','HorizontalRule'],
				'/',
				['Styles','Format','Font','FontSize','TextColor','BGColor','Maximize']
			]
		};

		$('.jquery_ckeditor').ckeditor(config);

		consultadeQueja(idQueja);
		$('input:text').attr("disabled", true);
		$('#tabsconsultaprincipal-3').hide();

	});
	
	function  consultadeQueja(idQueja){

	  $.ajax({
		type: 'POST',
		url:  '<%= request.getContextPath()%>/consultarQuejaCiudadanaXIdSSPPolicia.do?idQueja='+idQueja+'',
		dataType: 'xml',
		async: false,
		success: function(xml){
		
			$('#cQuejaNumero').val($(xml).find('quejaCiudadanaDTO').find('folioQueja').text());			   
			$("#cQNomQuejoso").val($(xml).find('quejaCiudadanaDTO').find('nombreQuejoso').text()+" "+$(xml).find('quejaCiudadanaDTO').find('apellidoPatQuejoso').text()+" "+$(xml).find('quejaCiudadanaDTO').find('getApellidoMatQuejoso').text());
			$("#cQTipoQueja").val($(xml).find('quejaCiudadanaDTO').find("tipoQuejaDTO").find('valor').text());
			$("#cQCalidadQuejoso").val($(xml).find('quejaCiudadanaDTO').find('calidadAfectado').find('valor').text());
			$('#cQNomInvolucradoCompara').val($(xml).find('quejaCiudadanaDTO').find('nombreDenunciado').text()+" "+$(xml).find('quejaCiudadanaDTO').find('apellidoPatDenunciado').text()+" "+$(xml).find('quejaCiudadanaDTO').find('apellidoMatDenunciado').text());
			$("#cQNumExpedienteCompara").val($(xml).find('quejaCiudadanaDTO').find('numeroExpediente').first().text());
			$("#cQCampoQueja").val($(xml).find('quejaCiudadanaDTO').find('descripcionQueja').text()); 
			
			var fecha = $(xml).find('quejaCiudadanaDTO').find('fechaRegistro').first().text();
			var hora = fecha.split(" ")[1];
			hora = hora.substring(0,5);
			fecha = fecha.split(" ")[0];						
									
			$("#cQFechaQueja").val(fecha.split("-")[2]+"/"+ fecha.split("-")[1]+"/"+fecha.split("-")[0]);

			if($(xml).find('quejaCiudadanaDTO').find("motivoRechazoDTO") !=null){
				$("#cQMotivoRechazo").val($(xml).find('quejaCiudadanaDTO').find("motivoRechazoDTO").find('valor').text());
			}
			$("#cQEstatus").val($(xml).find('quejaCiudadanaDTO').find("estatusQuejaDTO").find('valor').text());
			  }
	    });
	
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


	</script>
</head>
<body>
<table width="100%">
	<tr>
		<td align="right""><input class="back_button" type="button"
			onclick="guardaQejaCiudadana()" value="Guardar"
			style="display: none;"></td>
	</tr>
	<tr>
		<td>
		<div id="tabsPrincipal">
		<ul>
			<li><a href="#tabsconsultaprincipal-1">Datos de la Queja</a></li>
			<li><a href="#tabsconsultaprincipal-2">Confirmación de Datos</a></li>
		</ul>
		<div id="tabsconsultaprincipal-1">
		<fieldset><legend>Datos del Quejoso</legend>
		<table width="600" border="0">
			<tr align="right">
				<td width="165">Folio de la Queja:</td>
				<td width="160"><input name="" type="text" id="cQuejaNumero" /></td>
				<td width="153">Nombre del Quejoso</td>
				<td width="158"><input name="" type="text" id="cQNomQuejoso" /></td>
			</tr>
			<tr align="right">
				<td>Calidad del Afectado</td>
				<td><input name="" type="text" id="cQCalidadQuejoso" /></td>
				<td>Tipo de Queja</td>
				<td><input name="" type="text" id="cQTipoQueja" /></td>
			</tr>
			<tr align="right">
				<td>Fecha de Queja</td>
				<td><input name="" type="text" id="cQFechaQueja" /></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</fieldset>
		<fieldset><legend>Queja</legend>
		<table>
			<tr>
				<td><textarea cols="100" rows="15" id="cQCampoQueja"
					 disabled="disabled">
						</textarea></td>
			</tr>
		</table>
		</fieldset>
		</div>
		<div id="tabsconsultaprincipal-2">
			<table width="486" border="0">
				<tr align="right">
					<td width="183">Nombre del involucrado:</td>
					<td width="156"><input type="text" id="cQNomInvolucradoCompara" /></td>
				</tr>
				<tr align="right">
					<td>Número de Expediente:</td>
					<td><input type="text" id="cQNumExpedienteCompara" /></td>
				</tr>
				<tr align="right">
					<td>Motivo de Rechazo:</td>
					<td><input type="text" id="cQMotivoRechazo" /></td>
				</tr>
				<tr align="right">
					<td>Estatus de la Queja Ciudadana:</td>
					<td><input type="text" id="cQEstatus" /></td>
				</tr>
			</table>
		</div>
		<div id="tabsconsultaprincipal-3">
			<table width="1042"  height="490" border="0" cellspacing="0" cellpadding="0">
		              <tr>
		                <td width="250" height="450px;" align="center" valign="top">
	                        <table id="gridDocumentosDigitales"></table>
	                        <div id="pagerGridDocumentosDigitales"></div>
		                </td>
		                <td width="900" align="center" valign="top">
		               	  <iframe id='visorDocsFrame' width="900" height="450" src="">		               	  
		               	  </iframe>
		                </td>
		              </tr>
	        </table>
	     </div>

		</div>
		</td>
	</tr>
</table>
</body>
</html>