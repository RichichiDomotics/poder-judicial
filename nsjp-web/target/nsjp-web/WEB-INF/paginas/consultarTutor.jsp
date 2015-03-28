<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultar Tutor</title>
<link type="text/css" href="<%=request.getContextPath()%>/resources/css/consultarTutor.css" rel="stylesheet" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/tabs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
	<script type="text/javascript" charset="utf-8">
	$(document).ready(function(){
		//Mandamos llamar la funcion que inicializa la pagina
		cargaInicialPagina();
	});
	
	/**
	 Funcion para crear los TABS de la pagina para poder mostrar los CU hijo
	*/
	function cargaInicialPagina()
	{
	    /*Inician llamados para creación de tabs*/
	    creaTab('ConsultaTutor','ConsultaTutor');
	}
	
	function consultaDGsT(pagina)
	{
		location.href=pagina;		
	}
	
	function onsultaDomT(pagina)
	{
		location.href=pagina;		
	}
	
	function consultaConT(pagina)
	{
		location.href=pagina;	
	}
	
	function consultaDocT(pagina)
	{
		location.href=pagina;		
	}
	</script>
</head>
<body>
<h2>Consultar Tutor</h2>
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
	<br/>
</body>
</html>