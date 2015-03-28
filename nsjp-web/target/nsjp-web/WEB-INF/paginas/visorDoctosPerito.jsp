<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar IPH</title>

	<!--Hoja de estilos de Layout-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	
	<!--Hojas de estilos de los combos multiselect-->
	<!-- link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css" /-->
	
	<!--Hoja de estilos ultrasist-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	
	<!--Hoja de estilos windows engine (frames)-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	
	<!--Hojas de estilos para los componentes UI de Jquery-->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />	
	
	<!--Hoja de estilos para el grid-->
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/demo.css" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>  
	
	
	<!--scripts de java script-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-ui-1.8.11.custom.min.js"></script>
	
	<!--script de windows engine (frames)-->
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	
	<!--script de jquery UI-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	
	<!--script de los combos multiselect-->
	<!-- script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script-->
	
	<!--scripts del gird-->
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jqgrid/jquery.jqGrid.min.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.layout-1.3.0.js"></script>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/demo.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>

	<!--ESTILOS PARA LAS TABS-->
	<style>
	#tabs { height: 670px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 500px; overflow: auto; } 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	.tabEstilo  { height: 350px; overflow: auto; }
	#documentosList {
	text-align: center;
}
    </style>
    
 <script type="text/javascript">
   
	$(document).ready(function() {		
		alert("el numeroExpediente encontrado es:");
		});	
</script>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td width="31%">Archivos digitales de expediente</td>
    <td width="69%">Documento digital</td>
  </tr>
  <tr>
    <td valign="top"><br>
      <select name="documentosList" size="1" multiple id="documentosList" style="width:180px; height:300px">
    </select></td>
    <td><iframe width="100%" height="500" frameborder="1"></iframe></td>
  </tr>
</table>


</body>
</html>
