<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Tutor</title>
<link type="text/css" href="<%=request.getContextPath()%>/resources/css/consultarTutor.css" rel="stylesheet" />
<script src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/modificarTutor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/tabs.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
<script type="text/javascript">
   $(document).ready(function(){
	  cargarRbtCondicionTutor('<%= request.getContextPath()%>');
	  /*Inician llamados para creación de tabs*/
	    creaTab('ConsultaTutor','ConsultaTutor');
	    cargaTipoDeIdentificacion('<%= request.getContextPath()%>');
	    mostrarPantallaModificarTutor('<%= request.getContextPath()%>');
	  });
</script>
</head>
<body>
	<h2>Modificar Tutor</h2>
  <table>
	<tbody>
		<tr>
			<td>Condición: </td>
			<td id='tdModTutTutorCondicion'></td>
		</tr>
		<tr>
			<td>Foto: </td>
			<td ><img src="<%=request.getContextPath()%>/resources/images/foto.png"></td>
		</tr>
		<tr>
			<td id="renglonPrueba">
			   
			</td>
		</tr>
		<tr>
			<td>Tipo de <br/>Identificaci&oacute;n: </td>
			<td><select id="cbxModTutTipoIdentificacion" style="width:130px;"></select></td>
		</tr>
		<tr>
			<td>Folio de la <br/>Identificaci&oacute;n: </td>
			<td><input type="text" id="txtModTutFolioIdentificacion"/></td>
		</tr>
		<tr>
			<td>¿Es servidor p&uacute;blico? : </td>
			<td><input type="checkbox" id="chkModTutEsServidorPub"></td>
		</tr>
	</tbody>
</table>
<br/>
<br/>
	<div class="tabsConsultaTutor">
				<ul class="tabNavigationConsultaTutor">
					<li><a id="tabDatosGConsultaTutor" href="#datosGConsultaTutor" >Datos generales</a></li>
					<li><a id="tabDomicilioConsultaTutor" href="#domicilioConsultaTutor" >Domicilio</a></li>
					<li><a id="tabMContactoConsultaTutork" href="#mContactoConsultaTutor" >Medios de contacto</a></li>
					<li><a id="tabTipoDocConsultaTutor" href="#tipoDocConsultaTutor">Tipo de documento de identificación</a></li>
				</ul>
				<div id="datosGConsultaTutor">
					<h2>Datos Generales</h2>
				</div>
				<div id="domicilioConsultaTutor">
					<h2>Domicilio</h2>
				</div>
				<div id="mContactoConsultaTutor">
					<h2>Medios de contacto</h2>
				</div>
				<div id="tipoDocConsultaTutor">
					<h2>Tipo de documento de identificación</h2>
				</div>
		</div>
</body>
</html>