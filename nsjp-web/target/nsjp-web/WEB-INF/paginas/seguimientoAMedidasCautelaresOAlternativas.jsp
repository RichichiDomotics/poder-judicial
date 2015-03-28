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
		
	
	<script type="text/javascript">
	
	    
	$(document).ready(function() {
	
		$( "#tabsprincipalconsulta" ).tabs();
		$("#generarDocumento").click(generarDocumentoViwe);

	});

	function generarDocumentoViwe() {
		$.newWindow({id:"iframewindowGenerarDocumento", statusBar: true, posx:200,posy:50,width:1140,height:400,title:"Generar Documento", type:"iframe"});
	    $.updateWindowContent("iframewindowGenerarDocumento",'<iframe src="<%=request.getContextPath()%>/generarDocumento.do" width="1140" height="400" />');
	    		
	}

	</script>
</head>
<body>

<div id="tabsprincipalconsulta">
	<ul>
		<li><a href="#tabsconsultaprincipal-1">Generales</a></li>		
	</ul>
	<div id="tabsconsultaprincipal-1" style="height: 400">
		<table width="640" border="0" cellspacing="0" cellpadding="0">
	<tr>
	    <td width="87" height="27" align="right"><strong>Imputado:</strong></td>
	    <td colspan="3"><strong>
	      <input type="text" id="audienciaPJENS" style="width: 200px; border: 0; background: #DDD;" readonly="readonly" />
	    </strong></td>
	    <td width="106" align="right"><strong>Medida:</strong></td>
	    <td colspan="3"><strong>
	      <input type="text" id="audienciaPJENS4" style="width: 200px; border: 0; background: #DDD;" readonly="readonly" />
	    </strong></td> 
  </tr>
	  <tr>
	    <td height="24" colspan="4" align="left"><strong>Encargado de Seguimiento</strong></td>
	    <td align="right"><strong>Cautelar:</strong></td>
	    <td width="49"><input type="radio" name="radio" id="RdoBtnCautelar" value="RdoBtnCautelar" /></td>
	    <td width="80" align="right"><strong>Alternativa:</strong></td>
	    <td width="92"><input type="radio" name="radio" id="RdoBtnCautelar2" value="RdoBtnCautelar" /></td>
	  
  </tr>
	  <tr>
	    <td align="right"><strong>Nombre: </strong></td>
	    <td colspan="3"><strong>
	      <input type="text" id="audienciaPJENS2" style="width: 200px; border: 0; background: #DDD;" readonly="readonly" />
	    </strong></td>
	    <td align="right">&nbsp;</td>
	    <td colspan="3">&nbsp;</td>
	    
  </tr>
	  <tr>
	    <td height="28" colspan="4" align="left"><strong>Juez que ordena</strong></td>
	    <td align="right"><strong>Fecha Inicio:</strong></td>
	    <td colspan="3"><strong>
	      <input type="text" id="audienciaPJENS5" style="width: 200px; border: 0; background: #DDD;" readonly="readonly" />
	    </strong></td>
	    
  </tr>
	  <tr>
	    <td height="32" align="right"><strong>Nombre:</strong></td>
	    <td colspan="3"><strong>
	      <input type="text" id="audienciaPJENS3" style="width: 200px; border: 0; background: #DDD;" readonly="readonly" />
	    </strong></td>
	    <td align="right"><strong>Fecha Fin:</strong></td>
	    <td colspan="3"><strong>
	      <input type="text" id="audienciaPJENS8" style="width: 200px; border: 0; background: #DDD;" readonly="readonly" />
	    </strong></td>
	  
  </tr>
	  <tr>
	    <td colspan="4" align="right">&nbsp;</td>
	    <td align="right"><strong>Peridiocidad:</strong></td>
	    <td colspan="3"><strong>
	      <input type="text" id="audienciaPJENS7" style="width: 200px; border: 0; background: #DDD;" readonly="readonly" />
	    </strong></td>
	    
  </tr>
	  <tr>
	    <td colspan="8" align="center"><input type="submit" id="verTrascripcion" value="Ver Documento" /></td>
	    
  </tr>
	  <tr>
	    <td align="right">&nbsp;</td>
	    <td width="94">&nbsp;</td>
	    <td width="100" align="right">&nbsp;</td>
	    <td width="32">&nbsp;</td>
	    <td align="right">&nbsp;</td>
	    <td colspan="3">&nbsp;</td>
	    
  </tr>
	  <tr>
	    <td colspan="8" align="center"><textarea name="notas" id="notas" cols="90" rows="5"></textarea></td>
  </tr>
</table>
	</div>
</div>
</body>
</html>