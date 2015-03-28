<%@page import="mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente"%>
<%@page import="mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO"%>
<%@page import=" mx.gob.segob.nsjp.dto.usuario.UsuarioDTO"%>
<%@page import="mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/estilos.css"/>	
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/layout_complex.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.richtext.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.alerts.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.timeentry.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.colorpicker.css"/>
   	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.easyaccordion.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/jquery.windows-engine.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/cssGrid/ui.jqgrid.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/prettify.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/multiselect/jquery.multiselect.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath()%>/resources/css/ui-lightness/jquery-ui-1.8.11.custom.css"/>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-ui-1.8.10.custom.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.maskedinput.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/validate/mktSignup.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.timepicker.js"></script>	
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/wdCalendar/Plugins/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.windows-engine.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.timeentry.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery.easyAccordion.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ingresarMediosContacto.js"></script>
    
   	<script type="text/javascript" src="<%= request.getContextPath()%>/js/prettify.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.multiselect.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/i18n/grid.locale-en.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/jsGrid/jquery.tablednd.js"></script>
	
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/funcionesComunes.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath()%>/js/defensoria/cmpInvolucradosExpediente.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>  
  	
	<style type="text/css">

		#tabs { height: 100% } 
		.tabs-bottom { position: relative; } 
		.tabs-bottom .ui-tabs-panel { height: 500px; overflow: visible; } 
		.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
		.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
		.ui-tabs-selected { margin-top: -3px !important; }
		.tabEstilo  { height: 350px; overflow: auto; }
		
		.tabbedFrame{
			border: 0 none;
			height: 450px;
		}

	</style>
    <script>
<% 
	String numeroExpediente = request.getParameter("numeroExpedienteSt");
	ExpedienteDTO exp = (ExpedienteDTO)request.getSession().getAttribute("KEY_SESSION_EXPEDIENTE_TRABAJO"+numeroExpediente);
	long etapaId = exp.getEtapa().getIdCampo();
	long idExpediente = exp.getExpedienteId();
	long idNumeroExpediente = exp.getNumeroExpedienteId();
	 numeroExpediente = exp.getNumeroExpediente();
	UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("KEY_SESSION_USUARIO_FIRMADO");
	FuncionarioDTO funcionario = usuario.getFuncionario();
%>
	var contextPath = '<%=request.getContextPath()%>';
	$(document).ready(function() {
		$("#tabsInvolucrados").tabs();
		$("#tabsInvolucrados").tabs('selected', 8);
		inicilizarTabInvolucrados(<%=idExpediente%>, <%=idNumeroExpediente%>, '<%=numeroExpediente%>', <%=etapaId%>);	
		manageTabs();
	});
	
	function manageTabs(){
	}
	
    </script>
    
    
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Menu Defensor</title>			
</head>
<body>

<!-- Inicia Tabs Involucrados -->
<div id="tabsInvolucrados"  class="tabs-bottom">
	<ul>
		<li id="tabInvolucradoDefendido"><a href="#tabsInvolucrado-1">Para quien se solicita</a></li>
		<li id="tabInvolucrdSolicitante"><a href="#tabsInvolucrado-2">Solicitante ciudadano</a></li>
		<li id="tabCedulaIdentificacion"><a href="#tabsInvolucrado-3">Cedula de Identificacion</a></li>
		<li id="tabInvDefendidoInvitado"><a href="#tabsInvolucrado-4">Invitado</a></li>
		<li id="tabSolicitanteJusticiaR"><a href="#tabsInvolucrado-5">Solicitante de justicia restaurativa</a></li>
		<li id="tabInvolucrdDenunciante"><a href="#tabsInvolucrado-6">Denunciante</a></li>
		<li id="tab_Involucrado_Victima"><a href="#tabsInvolucrado-7">Victimas</a></li>
		<li id="tab_ProbableResponsable"><a href="#tabsInvolucrado-8">Probables Responsable</a></li>
		<li id="tab_Involucrado_Testigo"><a href="#tabsInvolucrado-9">Testigos</a></li>
		<li id="tabInvolucradoTraductor"><a href="#tabsInvolucrado-10">Traductor-Intérprete</a></li>
		<li id="tabInvolucrdQuienDetuvo"><a href="#tabsInvolucrado-11">Quién Detuvo</a></li>
	</ul>

	<div id="tabsInvolucrado-1"><!-- PARA QUIEN SE SOLITA calidad DEFENDIDO -->
		PARA QUIEN SE SOLITA calidad DEFENDIDO
		<iframe class="tabbedFrame" id="iframe_involucradoDefendido" width="100%" height="100%"></iframe>
	</div>
	<div id="tabsInvolucrado-2"><!-- SOLICITANTE CIUDADANO calidad SOLICITANTE -->
		SOLICITANTE CIUDADANO calidad SOLICITANTE
		<iframe class="tabbedFrame" id="iframe_InvolucrdSolicitant" width="100%" height="100%"></iframe>
	</div>
	<div id="tabsInvolucrado-3"><!-- CEDULA DE IDENTIFICACION calidad DEFENDIDO -->
		CEDULA DE IDENTIFICACION calidad DEFENDIDO
		<iframe class="tabbedFrame" id="iframe_CedulaIdentificacion" width="100%" height="100%"></iframe>
	</div>
	<div id="tabsInvolucrado-4"><!-- INVITADO calidad no se todavia -->
		INVITADO calidad no se todavia
		<iframe class="tabbedFrame" id="iframe_InvDefendidoInvitado" width="100%" height="100%"></iframe>
	</div>
	<div id="tabsInvolucrado-5"><!-- SOLICITANTE DE JUSTICIA RESTAURATIVA no se todavia -->
		SOLICITANTE DE JUSTICIA RESTAURATIVA no se todavia
		<iframe class="tabbedFrame" id="iframe_SolicitanteJusticiaR" width="100%" height="100%"></iframe>
	</div>
	<div id="tabsInvolucrado-6"><!-- DENUNCIANTE calidad DENUNCIANTE -->
		<DIV>DENUNCIANTE calidad DENUNCIANTE</DIV>
		<iframe class="tabbedFrame" id="iframe_InvolucrdDenunciante" width="100%" height="100%"></iframe>
	</div>
	<div id="tabsInvolucrado-7"><!-- VICTIMAS calidad VICTIMA-->
		<DIV>VICTIMAS calidad VICTIMA</DIV>
		<iframe class="tabbedFrame" id="iframe_Involucrado_Victima" width="100%" height="100%"></iframe>
	</div>
	<div id="tabsInvolucrado-8"><!-- PROBABLES RESPONSABLES calidad PROBABLE_RESPONSABLES-->
		<table width="25%" cellpadding="0" cellspacing="0" id="tblProbableResponsable">
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a id="nuevoProbResponsable"><input type="button" value='<bean:message key="ingProbaleResponsable"/>' class="btn_Generico/></a>
				</td>
			</tr>
		</table>
	</div>
	<div id="tabsInvolucrado-9"><!-- TESTIGOS calidad TESTIGO-->
		<table width="25%" cellpadding="0" cellspacing="0" id="tblTestigo">
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a id="nuevoTestigo"><input type="button" value="Ingresar Testigo" class="btn_Generico"/></a>
				</td>
			</tr>
		</table>
	</div>
	<div id="tabsInvolucrado-10"><!-- TRADUCTOR-INTERPRETE calidad TRADUCTOR-->
		<iframe id="iframeInvolucradoTraductor" width="100%" height="100%" src=""/>
	</div>
	<div id="tabsInvolucrado-11"><!-- QUIEN DETUVO calidad QUIEN_DETUVO-->
		<iframe id="iframeInvolucrdQuienDetuvo" width="100%" height="100%" src=""/>
	</div>
</div>
<!-- Termina Tabs Involucrados -->
</body>
</html>	