<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/layout_complex.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=request.getContextPath()%>/resources/css/jqgrid/ui.jqgrid.css" />
					
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery.layout-1.3.0.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/layout_complex.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/reloj.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jqgrid/jquery.jqGrid.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<script type="text/javascript">
	function selectTipoExpediente(){
		if($("#tipoExpediente").val()=="tecnico"){
			$("#divrestaurativa").css("display", "none");
			$("#divtecnico").css("display", "block");
			$("#divadscrito").css("display", "none");
		}
		
		if($("#tipoExpediente").val()=="adscrito"){
			$("#divrestaurativa").css("display", "none");
			$("#divtecnico").css("display", "none");
			$("#divadscrito").css("display", "block");
		}
		
		if($("#tipoExpediente").val()=="restaurativa"){
			$("#divrestaurativa").css("display", "block");
			$("#divtecnico").css("display", "none");
			$("#divnormal").css("display", "none");
			
		}
		
	}
	
	</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table width="37%" border="0" align="center">
  <tr>
    <td width="203" align="right">Tipo de Expediente:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  
    <td width="203">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="tipoExpediente" id="tipoExpediente" onchange="selectTipoExpediente()">
      <option >- Selecciona -</option>
      <!-- <option value="adscrito">Adscrito</option> -->
      <option value="tecnico">T&eacute;cnico</option>
      <!-- <option value="Normal">Normal</option> -->
     <!--  <option value="restaurativa">Restaurativa</option> -->
    </select></td>
  </tr>
</table>

<div id="divtecnico" style="display: none;">
Hola
</div>

<div id="divnormal" style="display: none;">
Hola1
</div>

<div id="divrestaurativa" style="display: none;">
Hola3
</div>

</body>
</html>