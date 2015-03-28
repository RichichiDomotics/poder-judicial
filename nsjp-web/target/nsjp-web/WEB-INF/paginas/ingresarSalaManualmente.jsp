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
		<li><a href="#tabsconsultaprincipal-1">Catálogo de Salas</a></li>
		<li><a href="#tabsconsultaprincipal-2">Designar Sala Temporal</a></li>
		<li><a href="#tabsconsultaprincipal-3">Agenda de la sala</a></li>
	</ul>
	<div id="tabsconsultaprincipal-1">
		<table width="555" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="350" >
					<table width="349" border="0" cellspacing="0" cellpadding="0">
				      <tr>
				        <td width="11%"><input type="checkbox" id="juez1" /></td>
				        <td width="89%">Sala 1</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="juez2" /></td>
				        <td>Sala 2</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="juez3" /></td>
				        <td>Sala 3</td>
				      </tr>
				      <tr>
				        <td><input type="checkbox" id="juez4" /></td>
				        <td>Sala 4</td>
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
		<table width="355" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td colspan="2" align="center" width="250" ><h3>Direcci&oacute;n de la Sala</h3>
			</td>
			</tr>
			<tr><td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="354" border="0" cellspacing="0" cellpadding="0">
				      <tr>
				        <td width="25%">Calle o Avenida:</td>
				        <td width="75%"><input type="text" style="width:90%;"></td>
				      </tr>
				      <tr>
				        <td>No. Exterior</td>
				        <td><input type="text"/></td>
				      </tr>
				      <tr>
				        <td>No. Interior</td>
				        <td><input type="text"/></td>
				      </tr>
				      <tr>
				        <td>Delegaci&oacute;n o <br/> Municipio :</td>
				        <td><input type="text" style="width:90%;"/></td>
				      </tr>
				      <tr>
				        <td>Entidad Federativa</td>
				        <td><input type="text" style="width:90%;"/></td>
				      </tr>
					</table>	
				</td>
			</tr>
			<tr>
				<td width="25%">Motivo : 
				</td>
				<td width="75%"> <textarea rows="5" cols="45"></textarea>
				</td>
			</tr>
			<tr>
				<td>Descripci&oacute;n : 
				</td>
				<td> <textarea rows="5" cols="45"></textarea>
				</td>
			</tr>
			<tr>
			<td colspan="2" align="right"><input type="button" value="Asignar Sala" class="btn_Generico"/>
			</td>
			</tr>
		</table>
	</div>
	<div id="tabsconsultaprincipal-3">
		&nbsp;
	</div>
</div>
</body>
</html>