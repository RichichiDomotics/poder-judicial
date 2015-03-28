<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Representante Legal</title>

	<link type="text/css" href="<%=request.getContextPath()%>/resources/css/ingresarIndividuo.css" rel="stylesheet" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/tabs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/modificarProbableResponsable.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
	<script type="text/javascript" charset="utf-8">
	$(document).ready(function(){
	    /*Inician llamados para creación de tabs*/
		 creaTab('RLegal','RLegal');
	});	  
	</script>
</head>
	<body>
		<h2>Modificar Representante Legal</h2>
		<table>
			<tbody>
				<tr>
					<td>Condición: </td>
					<td id='tdRLegalCondicion'></td>
				</tr>
				<tr>
					<td>Es mayor de edad ?  </td>
					<td><input type="checkbox" id='chbRLegalMayorDeEdad'/></td>
				</tr>
				<tr>
					<td>Está detenido ?  </td>
					<td><input type="checkbox" id='chbRLegalEstaDetenido'/></td>
				</tr>
				<tr>
					<td>Tipo de persona:  </td>
					<td>
						<input id='rbtRLegalPersonaFisica' type="radio" name="rbtRLegalTipoPersona" value="0">Física <input id='rbtRLegalPersonaMoral' type="radio" name="rbtRLegalTipoPersona" value="1">Moral<br/>
				 	</td>
				</tr>
			</tbody>
		</table>
		<br/>
		<br/>
		
		<div class="tabsRLegal">
			<ul class="tabNavigationRLegal">
				<li><a id="tabDatosGRLegal" href="#datosGRLegal">Datos generales</a></li>
				<li><a id="tabDomicilioRLegal" href="#domicilioRLegal">Domicilio</a></li>
				<li><a id="tabMRLegal" href="#mRLegal">Medios de contacto</a></li>
				<li><a id="tabTipoDocRLegal" href="#tipoDocRLegal">Tipo y subtipo de documento</a></li>
			</ul>
			<div id="datosGRLegal">
				<h2>Modificar Datos Generales</h2>
			</div>
			<div id="domicilioRLegal">
				<h2>Modificar Domicilio</h2>
			</div>
			<div id="mRLegal">
				<h2>Modificar Medios de contacto</h2>
			</div>
			<div id="tipoDocRLegal">
				<h2>Modificar Tipo y subtipo de documento</h2>
			</div>
		</div>
	</body>
</html>