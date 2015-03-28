<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Testigo</title>

	<link type="text/css" href="<%=request.getContextPath()%>/resources/css/ingresarIndividuo.css" rel="stylesheet" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/tabs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/modificarProbableResponsable.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<script type="text/javascript" charset="utf-8">
	$(document).ready(function(){
	    /*Inician llamados para creación de tabs*/
		creaTab('Testigo','Testigo');
	});	  
	</script>
</head>
	<body>
		<h2>Modificar Testigo</h2>
		<table>
			<tbody>
				<tr>
					<td>Condición: </td>
					<td id='tdTestigoCondicion'></td>
				</tr>
				<tr>
					<td>Es mayor de edad ?  </td>
					<td><input type="checkbox" id='chbTestigoMayorDeEdad'/></td>
				</tr>
				<tr>
					<td>Está detenido ?  </td>
					<td><input type="checkbox" id='chbTestigoEstaDetenido'/></td>
				</tr>
				<tr>
					<td>Tipo de persona:  </td>
					<td>
						<input id='rbtTestigoPersonaFisica' type="radio" name="rbtTestigoTipoPersona" value="0">Física <input id='rbtTestigoPersonaMoral' type="radio" name="rbtTestigoTipoPersona" value="1">Moral<br/>
				 	</td>
				</tr>
			</tbody>
		</table>
		<br/>
		<br/>
		
		<div class="tabsTestigo">
			<ul class="tabNavigationTestigo">
				<li><a id="tabDatosGTestigo" href="#datosGTestigo">Datos generales</a></li>
				<li><a id="tabDomicilioTestigo" href="#domicilioTestigo">Domicilio</a></li>
				<li><a id="tabMTestigo" href="#mTestigo">Medios de contacto</a></li>
				<li><a id="tabTipoDocTestigo" href="#tipoDocTestigo">Tipo y subtipo de documento</a></li>
			</ul>
			<div id="datosGTestigo">
				<h2>Modificar Datos Generales</h2>
			</div>
			<div id="domicilioTestigo">
				<h2>Modificar Domicilio</h2>
			</div>
			<div id="mContactoTestigo">
				<h2>Modificar Medios de contacto</h2>
			</div>
			<div id="tipoDocTestigo">
				<h2>Modificar Tipo y subtipo de documento</h2>
			</div>
		</div>
	</body>
</html>