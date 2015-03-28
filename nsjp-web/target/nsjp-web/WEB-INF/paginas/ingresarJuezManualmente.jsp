<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ingresar juez manualmente</title>

	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/estilos.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css" />
	
	<script type="text/javascript">
	$(document).ready(function() {
		
		$( "#tabsprincipalconsulta" ).tabs();
		$("#juez1").click(checkboxListener);
		$('#juez2').click(checkboxListener);
		$('#juez3').click(checkboxListener);
		$('#juez4').click(checkboxListener);
		
	});

	function checkboxListener(){
		
		if ($("#juez1").is(':checked')){
			$('#juez2').attr('checked', false);
			$('#juez3').attr('checked', false);
			$('#juez4').attr('checked', false);
		}
		if ($("#juez2").is(':checked')){
			$('#juez1').attr('checked', false);
			$('#juez3').attr('checked', false);
			$('#juez4').attr('checked', false);
		}
		if ($("#juez3").is(':checked')){
			$('#juez1').attr('checked', false);
			$('#juez2').attr('checked', false);
			$('#juez4').attr('checked', false);
		}
		if ($("#juez4").is(':checked')){
			$('#juez1').attr('checked', false);
			$('#juez2').attr('checked', false);
			$('#juez3').attr('checked', false);
		}		
	}
	</script>
</head>
<body>

<div id="tabsprincipalconsulta">
	<ul>
		<li><a href="#tabsconsultaprincipal-1">Catálogo de Jueces</a></li>
		<li><a href="#tabsconsultaprincipal-2">Enviar Notificación</a></li>
		<li><a href="#tabsconsultaprincipal-3">Eventos</a></li>
		<li><a href="#tabsconsultaprincipal-4">Agenda</a></li>
	</ul>
	<div id="tabsconsultaprincipal-1">
		<table width="555" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="350" >
					<table width="349" border="0" cellspacing="0" cellpadding="0">
				      <tr>
				        <td width="11%"><input type="checkbox" id="juez1" /></td>
				        <td width="89%">Juez. Armando Castañeda Tenango</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="juez2" /></td>
				        <td>Juez. Cuauhtémoc Paredes Serrano</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="juez3" /></td>
				        <td>Juez. Jorge Ignacio Fernández Ortíz</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="juez4" /></td>
				        <td>Juez. Erick Arturo de la Peña Soto</td>
				      </tr>
					</table>
				</td>
				<td width="200" >
					<input type="button" value="Designar Autom&aacute;ticamente" class="btn_Generico"/>
				</td> 
			</tr>
		</table>		
	</div>
	<div id="tabsconsultaprincipal-2">
		&nbsp;
	</div>
	<div id="tabsconsultaprincipal-3">
		&nbsp;
	</div>
	<div id="tabsconsultaprincipal-4">
		&nbsp;
	</div>
</div>
</body>
</html>