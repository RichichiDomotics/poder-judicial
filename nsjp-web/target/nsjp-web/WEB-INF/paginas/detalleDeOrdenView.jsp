<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#CCCCCC">   
	<table border="0" width="100%">
		<tr>
			<td  align="right">Nombre(s):</td>
			<td  class="field">
				<input type="text" class="" style="width: 180px;" maxlength="30" id="datosGeneralesCmpNombres" name="datosGeneralesCmpNombres"  value="" onkeydown="espejoDatos();" onkeypress="espejoDatos();" onkeyup="espejoDatos();"/>
			</td>
			<td align="right">Apellido Paterno:</td>
			<td>
				<input type="text" style="width: 180px;" maxlength="30" class="" id="datosGeneralesCmpApaterno" name="datosGeneralesCmpApaterno" onkeydown="espejoDatos();" onkeypress="espejoDatos();" onkeyup="espejoDatos();"/>
			</td>
			<td align="right">Apellido Materno:</td>
			<td>
				<input type="text" style="width: 180px;" maxlength="30" class="" id="datosGeneralesCmpMaterno"  name="datosGeneralesCmpMaterno" onkeydown="espejoDatos();" onkeypress="espejoDatos();" onkeyup="espejoDatos();"/>
			</td>
		</tr>
		<tr>
			<td align="right">Delito:</td>
			<td>
				<input type="text" style="width: 180px;" maxlength="18" class="" id="datosGeneralesCmpCurp"  name="datosGeneralesCmpCurp"/>
			</td>
			<td align="right">Asunto:</td>
			<td>
				<input type="text" class="" style="width: 180px;" maxlength="13" id="datosGeneralesCmpRfc" name="datosGeneralesCmpRfc"/>
			</td>
		</tr>
		<tr>
			 <td   valign="top" align="left" colspan="6">Detalle de la Orden
			</td>
		</tr>
		<tr>
			<td valign="top" align="left" colspan="6">
	        
	          <textarea name="textMotivo" id="textMotivo" cols="101" rows="10"></textarea>
	       </td>
		</tr>
		<tr>
			<td align="right" colspan="3">
				<input type="button" value="Registrar Informe"  id="botonGuarda" onclick="guarda()"	class="btn_Generico"/>
			</td>
			<td align="left" colspan="3">
				<!--<div id="boton" ><input type="button" value="Elaborar Evaluación"  id="botonGenerarActa" class="btn_Generico"/> </div>-->
			</td>
		</tr>
	</table>
</body>
</html>