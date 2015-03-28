<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar contacto de una organizacion</title>

	<link type="text/css" href="<%=request.getContextPath()%>/resources/css/ingresarIndividuo.css" rel="stylesheet" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/tabs.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/modificarContactoDeUnaOrganizacion.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<script type="text/javascript" charset="utf-8">

		$(document).ready(function(){
		    /*Inician llamados para creación de tabs*/
		    creaTab('COrganizacional','COrganizacional');
		    cargaTipoDeIdentificacion('<%= request.getContextPath()%>');
		});
		
	</script>	
</head>
	<body>
		<h2>Modificar Contacto De Una Organizacion</h2>
		<table>
			<tbody>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>Foto:</td>
					<td><img src="<%=request.getContextPath()%>/resources/images/foto.png"></td>
				</tr>
				<tr>
					<td>Tipo de <br/>Identificaci&oacute;n: </td>
					<td><select id="cbxModCOrganizacionalTipoIdentificacion" style="width:130px;"></select></td>
				</tr>
				<tr>
					<td>Folio de identificacion:  </td>
					<td>
						<input id='txtAreaContactoOrganizacionalPersonaFisica' type="text" name="rbtContactoOrganizacionalTipoPersona" value="0">
				 	</td>
				</tr>
			</tbody>
		</table>
		<br/>
		<br/>
		
		<div class="tabsCOrganizacional">
			<ul class="tabNavigationCOrganizacional">
				<li><a id="tabDatosGCOrganizacional" href="#datosGCOrganizacional">Datos generales</a></li>
				<li><a id="tabDomicilioCOrganizacional" href="#domicilioCOrganizacional">Domicilio</a></li>
				<li><a id="tabMContactoCOrganizacional" href="#mContactoCOrganizacional">Medios de contacto</a></li>
				<li><a id="tabTipoDocCOrganizacional" href="#tipoDocCOrganizacional">Tipo y subtipo de documento</a></li>
			</ul>
			<div id="datosGCOrganizacional">
				<h2>Modificar Datos Generales</h2>
			</div>
			<div id="domicilioCOrganizacional">
				<h2>Modificar Domicilio</h2>
			</div>
			<div id="mContactoCOrganizacional">
				<h2>Modificar Medios de contacto</h2>
			</div>
			<div id="tipoDocCOrganizacional">
				<h2>Modificar Tipo y subtipo de documento</h2>
			</div>
		</div>
	</body>
</html>