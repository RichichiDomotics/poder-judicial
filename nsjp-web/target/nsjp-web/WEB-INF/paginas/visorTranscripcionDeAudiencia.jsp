<%@page import="mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitud de Trancripci&oacute;n de Audiencia</title>
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
		    
		$(document).ready(function() {

			//Obtenemos los valores para pintar los datos en los campos correspondientes				
			var idAudiencia = "<%=((AudienciaDTO) request.getAttribute("transcripcionAudienciaDTO")).getId()%>";
			var tipoAudiencia = "<%=((AudienciaDTO) request.getAttribute("transcripcionAudienciaDTO")).getTipoAudiencia().getValor()%>";
			var fechaAudiencia = "<%=((AudienciaDTO) request.getAttribute("transcripcionAudienciaDTO")).getFechaEvento()%>";

			//damos formato a la cadena de fecha
			var espacioPos1=fechaAudiencia.indexOf(":",0);
			var fechaFormateada=fechaAudiencia.substring(0,espacioPos1-3);

			//pintamos los valores en los campos correspondientes
			$('#idTranscripcionAudiencia').val(idAudiencia);
			$('#tipoTranscripcionAudiencia').val(tipoAudiencia);
			$('#fechaTranscripcionAudiencia').val(fechaFormateada);

			//se crean las tabs
			$( "#tabsTranscripcionAudiencia" ).tabs();
			
		});

		jQuery().ready(function () {
			$('#guardarNarraTiva').hide();
			cargarDocumento();
		});
		function cargarDocumento(){
			$.ajax({
		    	type: 'POST',
		    	url: '<%=request.getContextPath()%>/CargarDocumento.do',
		    	data: 'formaId=<%=request.getAttribute("formaId")!=null?request.getAttribute("formaId"):""%>'+
		    	'&documentoId=<%=request.getAttribute("documentoId")!=null?request.getAttribute("documentoId"):""%>',
		    	dataType: 'xml',
		    	success: function(xml){
		    		
		    		$('.jquery_ckeditor').val( $(xml).find('body').text());
		    	}
			});
		}
		
		function guardarTranscricpcion(){
			//mostramos los divs en el padre de la pestaña de Acciones.
			try{window.parent.muestraDIVSCanalizacion();}catch(e){}
			var recuperaTexto=$('.jquery_ckeditor').val();
			document.frmDoc.texto.value = recuperaTexto;
			document.frmDoc.submit();
		}
		
	</script>
</head>
<body>

	<div id="tabsTranscripcionAudiencia">
		<ul>
			<li><a href="tabsTranscripcionAudiencia-1">Datos Audiencia</a></li>
			
		</ul>
		<div id="tabsconsultaprincipal-1">
		
			<table width="897" border="0" cellspacing="0" cellpadding="0">
			  <tr>
			    <td colspan="6" align="center"><strong>&nbsp;</strong></td>
			  </tr>
			  <tr>
			    <td width="170" align="right"><strong>Identificador audiencia:</strong></td>
			    <td width="155">
			    
			    				<input type="text"
									id="idTranscripcionAudiencia"
									style="width: 150px; border: 0; background: #DDD;"
									readonly="readonly" /></td>
			    <td width="105" align="right"><strong>Tipo audiencia:</strong></td>
			    <td width="155">
			    				<input type="text"
									id="tipoTranscripcionAudiencia"
									style="width: 150px; border: 0; background: #DDD;"
									readonly="readonly" /></td>
			    <td width="143" align="right"><strong>Fecha audiencia:</strong></td>
			    <td width="155">
			    				<input type="text"
									id="fechaTranscripcionAudiencia"
									style="width: 150px; border: 0; background: #DDD;"
									readonly="readonly" /></td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td colspan="4">
			    <div style="margin-top: 0; margin-bottom: auto; vertical-align: top;margin-right: auto; margin-left: auto"  >
				<jsp:include page="/WEB-INF/paginas/ingresarNarrativaView.jsp" flush="true"></jsp:include>
				</div>
				<p>
			    <form name="frmDoc" action="<%= request.getContextPath() %>/GenerarDocumentoTranscripcion.do" method="post">
					<input type="hidden" name="texto" value=""/>
					<input type="hidden" name="solicitudId" value="<%=request.getParameter("idEvento")!=null?request.getParameter("idEvento"):"" %>"/>
					<input type="hidden" name="formaId" value="<%=request.getAttribute("formaId")!=null?request.getAttribute("formaId"):"" %>"/>
					<input type="button" name="guardarTranscripcion" value="Guardar" onclick="guardarTranscricpcion()" class="btn_Generico" />
				</form>
				</p>
			    </td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td align="center">
			    </td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			  </tr>
			</table>
			
		</div>
		
	</div>

</body>
</html>