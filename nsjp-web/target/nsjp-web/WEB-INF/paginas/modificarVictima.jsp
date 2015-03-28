<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Victima</title>

	<link type="text/css" href="<%=request.getContextPath()%>/resources/css/ingresarIndividuo.css" rel="stylesheet" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/tabs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/modificarProbableResponsable.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<script type="text/javascript" charset="utf-8">
	$(document).ready(function(){
	    /*Inician llamados para creación de tabs*/
		creaTab('Victima','Victima');
	});	  
	</script>
</head>
	<body>
		<h2>Modificar Victima</h2>
		<table>
			<tbody>
				<tr>
					<td>Condición: </td>
					<td id='tdVictimaCondicion'></td>
				</tr>
				<tr>
					<td>Es mayor de edad ?  </td>
					<td><input type="checkbox" id='chbVictimaMayorDeEdad'/></td>
				</tr>
				<tr>
					<td>Está detenido ?  </td>
					<td><input type="checkbox" id='chbVictimaEstaDetenido'/></td>
				</tr>
				<tr>
					<td>Tipo de persona:  </td>
					<td>
						<input id='rbtVictimaPersonaFisica' type="radio" name="rbtVictimaTipoPersona" value="0">Física <input id='rbtVictimaPersonaMoral' type="radio" name="rbtVictimaTipoPersona" value="1">Moral<br/>
				 	</td>
				</tr>
			</tbody>
		</table>
		<br/>
		<br/>
		
		<div class="tabsVictima">
			<ul class="tabNavigationVictima">
				<li><a id="tabDatosGVictima" href="#datosGVictima">Datos generales</a></li>
				<li><a id="tabDomicilioVictima" href="#domicilioVictima">Domicilio</a></li>
				<li><a id="tabMVictima" href="#mVictima">Medios de contacto</a></li>
				<li><a id="tabTipoDocVictima" href="#tipoDocVictima">Tipo y subtipo de documento</a></li>
			</ul>
			<div id="datosGVictima">
				<h2>Modificar Datos Generales</h2>
			</div>
			<div id="domicilioVictima">
				<h2>Modificar Domicilio</h2>
			</div>
			<div id="mContactoVictima">
				<h2>Modificar Medios de contacto</h2>
			</div>
			<div id="tipoDocVictima">
				<h2>Modificar Tipo y subtipo de documento</h2>
			</div>
		</div>
	</body>
</html>