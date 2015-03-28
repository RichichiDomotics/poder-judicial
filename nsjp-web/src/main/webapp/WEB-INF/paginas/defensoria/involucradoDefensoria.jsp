<%@page import="mx.gob.segob.nsjp.comun.util.DateUtils"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@page import="mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO"%>
<%@page import="mx.gob.segob.nsjp.dto.catalogo.ValorDTO"%>
<%@page import="mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO"%>
<%@page import="mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>    
	<script type="text/javascript">
		var numeroExpediente = 0;
		var idExpediente= 0;
		var verAlias = 0;
		var idindi = 0;
		
		$(document).ready(function() {
			//activa la funcionalidad del acordeon
			$('#cedulaIdentificacion').easyAccordion({ 
				  autoStart: false, 
				  slideInterval: 3000
			});

			//tabs hijos para pestaña de hechos
			$("#tabs").tabs();
			
			//tabs hijos para pestaña de hechos
			$("#tabsHechos").tabs();
			
			var $widget = $("#ocupacion,#alias,#nacionalidad").multiselect(),state = true;
		});
		
	</script>
	<style type="text/css">
		.inputInvolucrado{
			BORDER-BOTTOM: 1px solid #c0c2c4; 
			BORDER-LEFT: 1px solid #c0c2c4; 
			BACKGROUND: #fff; 
			BORDER-TOP: 1px solid #c0c2c4; 
			BORDER-RIGHT: 1px solid #c0c2c4;
		}
		
		.campo{
			width:290px;
		}
		
		select{
			width:295px;
		}
		
		#cedulaIdentificacion {
			WIDTH: 1030px;
			HEIGHT: 450px;
			PADDING-TOP: 10px;
			PADDING-BOTTOM: 0px;
			PADDING-LEFT: 2px;
			PADDING-RIGHT: 0px;
			background-image: url(/nsjp-web/resources/images/back_datos_gral.png);
			background-repeat: no-repeat;
			border: 0px solid #000;
		}
		#cedulaIdentificacion DL {
			WIDTH: 1030px; HEIGHT: 460px
		}
		/*acordeon editar*/
		#cedulaIdentificacion DT {
			TEXT-ALIGN: right;
			PADDING-BOTTOM: 16px;
			PADDING-TOP: 2px;
			PADDING-LEFT: 0px;
			TEXT-TRANSFORM: none;	
			FONT-FAMILY: Arial, Helvetica, sans-serif;
			LETTER-SPACING: 1px;
			LINE-HEIGHT: 15px;
			/*acomodo texto*/PADDING-RIGHT: 40px;
			/*distancia persianas*/HEIGHT: 15px;
			COLOR: #f5f5f5;
			FONT-SIZE: 12px;
			FONT-WEIGHT: normal;	
			background-image: url(/nsjp-web/resources/images/barra_ver_act.png);
			background-repeat: no-repeat;
			background-position: 28px;
		}
		#cedulaIdentificacion DT.active {
			BACKGROUND: url(/nsjp-web/resources/images/barra_ver_inact.png);
			background-repeat: no-repeat; 
			COLOR: #f5f5f5; 
			CURSOR: pointer;
			background-position: 30px;
		}
		#cedulaIdentificacion DT.hover {
			COLOR: #f5f5f5
		}
		#cedulaIdentificacion DT.hover.active {
			COLOR: #f5f5f5
		}
		#cedulaIdentificacion DD {
			BORDER-BOTTOM: #dbe9ea 0px solid; 
			BORDER-LEFT: 0px; 
			PADDING-BOTTOM: 1px; 
			PADDING-LEFT: 1px; 
			PADDING-RIGHT: 1px; 
			/*BACKGROUND: url(/nsjp-web/images/jquery/plugins/easyaccordion/slide.jpg) repeat-x left bottom;*/ 
			BORDER-TOP: #dbe9ea 0px solid; 
			MARGIN-RIGHT: 1px; 
			BORDER-RIGHT: #dbe9ea 0px solid; 
			PADDING-TOP: 1px
		}
		/*distancia y color de numero*/
		#cedulaIdentificacion .slide-number {
			COLOR: #68889b; FONT-WEIGHT: bold; LEFT: 30px
		}
		#cedulaIdentificacion .active .slide-number {
			COLOR: #fff
		}
		#cedulaIdentificacion A {
			COLOR: #58595b;
			font-family: Arial, Helvetica, sans-serif;
		}
		#cedulaIdentificacion DD IMG {
			MARGIN: 0px; FLOAT: right
		}
		#cedulaIdentificacion H2 {
			MARGIN-TOP: 10px; FONT-SIZE: 2.5em
		}
		#cedulaIdentificacion .more {
			DISPLAY: block; PADDING-TOP: 10px
		}
		
		#tabs { height: 100% } 
		.tabs-bottom { position: relative; } 
		.tabs-bottom .ui-tabs-panel { height: 400px; overflow: visible; } 
		.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
		.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
		.ui-tabs-selected { margin-top: -3px !important; }
		.tabEstilo  { height: 350px; overflow: auto; }
	</style>
</head>
<%
		InvolucradoDTO involucrado = ((InvolucradoDTO)request.getAttribute("involucrado"));
		Date fecha = involucrado.getFechaCreacionElemento();
		String fechaIngreso = DateUtils.formatear(fecha);
		Boolean deshabilitar = (Boolean) request.getAttribute("consultar");
%>
<body>
	<table  align="center" width="1025px" height="176" border=0 class="back_denuncia">
    <tr vAlign=top>
    	<td colspan="2"></td>
    </tr>	
    <tr vAlign=top>
    	<td width="37%" align="center" valign="middle">
	    	<table width="360" border="0" cellpadding="0" cellspacing="0" class="linea_derecha_gris">
		<tr>
			<td width="10" height="109">&nbsp;</td>
		    <td width="4">&nbsp;</td>
		    <td width="123"><IMG id=iVictimaCmpFoto alt=foto src="<%= request.getContextPath() %>/resources/images/img_denunciante.png"></td>
    	</tr>
	 	</table>
		</td>
	    <td width="45%" align="left" valign="top">
		    <table width="92%" border="0" cellspacing="0" cellpadding="0">
	      	<tr>
	        	<td height="23">&nbsp;</td>
	      	</tr>
			</table>
			<logic:iterate name="involucrado" property="nombresDemograficoDTO" id="nombre">
				<logic:equal name="nombre" property="esVerdadero" value="true">
					<bean:define name="nombre" id="nombreInvolucrado"></bean:define>
				</logic:equal>
			</logic:iterate>
	   		<table border=0 class=celda2 cellSpacing=0 cellPadding=0 width="63%" height=91>
	     	<tr>
	       		<td width="36%" height=25 align=left class="txt_gral_victima">Nombre:</td>
	       		<td width="64%">
				<% if(deshabilitar){%>
	       			<html:text name="nombreInvolucrado" property="nombre" styleId="nombre" styleClass="inputInvolucrado" disabled="true" ></html:text>
				<% }else{%>
	       			<html:text name="nombreInvolucrado" property="nombre" styleId="nombre" styleClass="inputInvolucrado"></html:text>
				<% }%>
	  			</td>
			</tr>
			<tr>
				<td width="36%" height=28 align=left class="txt_gral_victima">Apellido Paterno:</td>
				<td height=28 width="64%">
				<% if(deshabilitar){%>
					<html:text name="nombreInvolucrado" property="apellidoPaterno" styleId="paterno" styleClass="inputInvolucrado" disabled="true"></html:text>
				<% }else{%>
					<html:text name="nombreInvolucrado" property="apellidoPaterno" styleId="paterno" styleClass="inputInvolucrado"></html:text>
				<% }%>
	   			</td>
			</tr>
			<tr>
				<td width="36%" height=29 align=left class="txt_gral_victima">Apellido Materno:</td>
				<td height=29>
				<% if(deshabilitar){%>
					<html:text name="nombreInvolucrado" property="apellidoMaterno" styleId="materno" styleClass="inputInvolucrado" disabled="true"></html:text>
				<% }else{%>
					<html:text name="nombreInvolucrado" property="apellidoMaterno" styleId="materno" styleClass="inputInvolucrado"></html:text>
				<% }%>
	           </td>
			</tr>
			</table>
			<table width="64%%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			</table>
			<table width="64%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="23" align="left">
					<input type=button class="btn_guardar"  id="btnGuardarDatos" value="Guardar" >
				</td>
			</tr>
			</table>
		</td>
	</tr>
	<tr vAlign=top>
		<td colSpan=2>&nbsp;</td>
	</tr>
	</table> 		

	<center>
	<div id="cedulaIdentificacion" >
		<dl>
	    	<dt id="cejaDatosGenerales">Datos Generales</dt>
	       	<dd>
				<table border="0" width="100%" height="415x" class="fondoClaroAp">
					<tr>
						<td id="etiquetaFechaRegistro" align="right">Fecha de Registro:</td>
						<td colspan="1">
						<% if(deshabilitar){%>
							<input class="campo" type="text"  maxlength="50" id="fechaIngreso" name="fechaIngreso" value="<%=fechaIngreso %>" disabled="disabled" />
						<% }else{%>
							<input class="campo" type="text"  maxlength="50" id="fechaIngreso" name="fechaIngreso" value="<%=fechaIngreso %>" />
						<% }%>
						</td>
						<td align="right">Ocupaci&oacute;n:</td>
						<td >
							<select name="ocupacion" id="ocupacion" multiple="multiple" tabindex="11">
								<option id="0">- Selecciona -</option>
<%
	List<CatalogoDTO> opcioneso = (List<CatalogoDTO>)request.getAttribute("ocupacion");
	List<ValorDTO> ocupaciones = involucrado.getValorIdOcupacion();
	for(CatalogoDTO opcion : opcioneso){
		for(ValorDTO opciong : ocupaciones){
			if(opcion.getClave().longValue() ==  opciong.getIdCampo()){
%>									<option value="<%=opcion.getClave()%>" selected="selected" ><%=opcion.getValor()%></option><%
			}else{		
%>									<option value="<%=opcion.getClave()%>"><%=opcion.getValor()%></option><%
			}
		}
	}
%>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">Nombre(s):</td>
						<td class="field">
						<% if(deshabilitar){%>
							<html:text name="nombreInvolucrado" property="nombre" styleId="nombre" styleClass="campo" disabled="true"></html:text>
						<% }else{%>
							<html:text name="nombreInvolucrado" property="nombre" styleId="nombre" styleClass="campo"></html:text>
						<% }%>
						</td>
<%
	List<AliasInvolucradoDTO> aliases = involucrado.getAliasInvolucradoDTO();
	if(aliases != null && !aliases.isEmpty()){
%>
						<td align="right" class="tdAliasCmp">Alias:</td>
						<td class="tdAliasCmp">
							<select id="alias" multiple="multiple" name="datosGeneralesCmpAlias" tabindex="12"></select>
							<select name="alias" id="alias" multiple="multiple" tabindex="12">
								<option id="0">- Selecciona -</option>
<%
		aliases = involucrado.getAliasInvolucradoDTO();
		for(AliasInvolucradoDTO opciong : aliases){
%>								<option value="<%=opciong.getAlias()%>" selected="selected" ><%=opciong.getAlias()%></option>
							</select>
						</td>
<%
		}
	}else{
%>
						<td align="right" class="tdAliasCmp">&nbsp;</td>
						<td class="tdAliasCmp">&nbsp;</td>
<%
	}
%>
					</tr>
					<tr>
						<td align="right">Apellido Paterno:</td>
						<td>
						<% if(deshabilitar){%>
							<html:text name="nombreInvolucrado" property="apellidoPaterno" styleId="paterno" styleClass="campo" disabled="true"></html:text>
						<% }else{%>
							<html:text name="nombreInvolucrado" property="apellidoPaterno" styleId="paterno" styleClass="campo"></html:text>
						<% }%>
						</td>
						<td align="right">Nacionalidad:</td>
				        <td>
							<select name="nacionalidad" id="nacionalidad" multiple="multiple" tabindex="14">
								<option id="0">- Selecciona -</option>
<%
	opcioneso = (List<CatalogoDTO>)request.getAttribute("nacionalidad");
	ocupaciones = involucrado.getValorIdNacionalidad();
	for(CatalogoDTO opcion : opcioneso){
		for(ValorDTO opciong : ocupaciones){
			if(opcion.getClave().longValue() ==  opciong.getIdCampo()){
%>									<option value="<%=opcion.getClave()%>" selected="selected" ><%=opcion.getValor()%></option><%
			}else{		
%>									<option value="<%=opcion.getClave()%>"><%=opcion.getValor()%></option><%
			}
		}
	}
%>
							</select>
				        </td>
					</tr>
					<tr>
						<td align="right">Apellido Materno:</td>
						<td>
						<% if(deshabilitar){%>
							<html:text name="nombreInvolucrado" property="apellidoMaterno" styleId="materno" styleClass="campo" disabled="true"></html:text>
						<% }else{%>
							<html:text name="nombreInvolucrado" property="apellidoMaterno" styleId="materno" styleClass="campo"></html:text>
						<% }%>
							
						</td>
				        <td>
				        	&nbsp;
				        </td>
				        <td>
				        	&nbsp;
				        </td>
					</tr>
				    <tr>
						<td align="right">Sexo:</td>
						<td>
						<% if(deshabilitar){%>
						<logic:equal name="nombre" property="sexo" value="M">
						Masculino <input type="radio" id="datosGeneralesCmpSexoM" name="rbtSexoDatosGenerales" value="M" checked="checked" tabindex="4" disabled="disabled"></input>
						&nbsp; &nbsp; &nbsp; 
						Femenino <input type="radio" id="datosGeneralesCmpSexoF" name="rbtSexoDatosGenerales" value="F" tabindex="5" disabled="disabled"></input>
						</logic:equal>
						<logic:notEqual name="nombre" property="sexo" value="M">
						Masculino <input type="radio" id="datosGeneralesCmpSexoM" name="rbtSexoDatosGenerales" value="M" tabindex="4" disabled="disabled"></input>
						&nbsp; &nbsp; &nbsp; 
						Femenino <input type="radio" id="datosGeneralesCmpSexoF" name="rbtSexoDatosGenerales" value="F" checked="checked" tabindex="5" disabled="disabled"></input>
						</logic:notEqual>
						<% }else{%>
						<logic:equal name="nombre" property="sexo" value="M">
						Masculino <input type="radio" id="datosGeneralesCmpSexoM" name="rbtSexoDatosGenerales" value="M" checked="checked" tabindex="4"></input>
						&nbsp; &nbsp; &nbsp; 
						Femenino <input type="radio" id="datosGeneralesCmpSexoF" name="rbtSexoDatosGenerales" value="F" tabindex="5"></input>
						</logic:equal>
						<logic:notEqual name="nombre" property="sexo" value="M">
						Masculino <input type="radio" id="datosGeneralesCmpSexoM" name="rbtSexoDatosGenerales" value="M" tabindex="4"></input>
						&nbsp; &nbsp; &nbsp; 
						Femenino <input type="radio" id="datosGeneralesCmpSexoF" name="rbtSexoDatosGenerales" value="F" checked="checked" tabindex="5"></input>
						</logic:notEqual>
						<% }%>

						</td>
						<td rowspan="7" colspan="2" valign="top">
							<table>
								<tr>
									<td align="center" colspan="2" class="seccion">Datos de Nacimiento</td>
								</tr>
								<tr>
									<td align="right">Fecha de Nacimiento:</td>		
									<td><input type="text" id="datosNacimientoCmpFechaNacimiento" name="datosNacimientoCmpFechaNacimiento" maxlength="10" readonly="readonly" style="width: 180px;" onchange="calculaRFC_CURP();calculaEdadAproximada($('#datosNacimientoCmpFechaNacimiento').val())"/></td>
								</tr>
								<tr>
									<td align="right">Edad Aproximada:</td>
									<td><input type="text" id="datosNacimientoCmpEdadAproximada" name="datosNacimientoCmpEdadAproximada" style="width: 180px;"/></td>
								</tr>
								<tr>
									<td align="right">Pais de Nacimiento:</td>
									<td>
										<select id="cbxPaisNacimiento" name="cbxPaisNacimiento"  style="width: 180px;">
											<option value="-1">-Seleccione-</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">Estado:</td>
									<td>
										<select id="cbxEntFederativaNacimiento" name="cbxEntFederativaNacimiento" style="width: 180px;" onchange="calculaRFC_CURP()">
										<option value="-1">-Seleccione-</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">Municipio:</td>
									<td>
										<select id="cbxCiudadNacimiento" name="cbxCiudadNacimientos" style="width: 180px;">
										<option value="-1">-Seleccione-</option>
										</select>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td align="right">CURP:</td>
						<td>
						<% if(deshabilitar){%>
							<html:text name="nombreInvolucrado" property="curp" styleId="curp" styleClass="campo" disabled="true"></html:text>
						<% }else{%>
							<html:text name="nombreInvolucrado" property="curp" styleId="curp" styleClass="campo"></html:text>
						<% }%>
						</td>
					</tr>
					<tr>
						<td align="right">RFC:</td>
						<td>
						<% if(deshabilitar){%>
							<html:text name="nombreInvolucrado" property="rfc" styleId="rfc" styleClass="campo" disabled="true"></html:text>
						<% }else{%>
							<html:text name="nombreInvolucrado" property="rfc" styleId="rfc" styleClass="campo"></html:text>
						<% }%>
						</td>
					</tr>
					<tr>
						<td align="right">Idioma o Lengua:</td>
						<td>
						<% if(deshabilitar){%>
							<select name="idioma" id="idioma" tabindex="10" disabled="disabled">
						<% }else{%>
							<select name="idioma" id="idioma" tabindex="10">
						<% }%>
							<bean:define name="involucrado" property="valorIdIdioma" id="idioma"/>
							<bean:define name="idioma" property="idCampo" id="clave"/>
								<option id="0">- Selecciona -</option>
<%
	List<CatalogoDTO> opciones = (List<CatalogoDTO>)request.getAttribute("idiomas");
	for(CatalogoDTO opcion : opciones){
		if(opcion.getClave().longValue() ==  ((Long)clave).longValue()){
%>									<option value="<%=opcion.getClave()%>" selected="selected" ><%=opcion.getValor()%></option><%
		}else{		
%>									<option value="<%=opcion.getClave()%>"><%=opcion.getValor()%></option><%
		}
	}
%>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">Escolaridad:</td>
						<td>
						<% if(deshabilitar){%>
							<select name="escolaridad" id="escolaridad" tabindex="10" disabled="disabled">
						<% }else{%>
							<select name="escolaridad" id="escolaridad" tabindex="10">
						<% }%>
							<bean:define name="involucrado" property="valorIdEscolaridad" id="escolaridad"></bean:define>
							<bean:define name="escolaridad" property="idCampo" id="clave"/>
								<option>- Selecciona -</option>
<%
	opciones = (List<CatalogoDTO>)request.getAttribute("escolaridad");
	for(CatalogoDTO opcion : opciones){
		if(opcion.getClave().longValue() ==  ((Long)clave).longValue()){
%>									<option value="<%=opcion.getClave()%>" selected="selected" ><%=opcion.getValor()%></option><%
		}else{		
%>									<option value="<%=opcion.getClave()%>"><%=opcion.getValor()%></option><%
		}
	}
%>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">Estado Civil:</td>
						<td>
						<% if(deshabilitar){%>
							<select name="estadoCivil" id="estadoCivil" tabindex="10" disabled="disabled">
						<% }else{%>
							<select name="estadoCivil" id="estadoCivil" tabindex="10">
						<% }%>
							<bean:define name="involucrado" property="valorIdEstadoCivil" id="estadoCivil"></bean:define>
							<bean:define name="estadoCivil" property="idCampo" id="clave"/>
								<option id="0">- Selecciona -</option>
<%
	opciones = (List<CatalogoDTO>)request.getAttribute("estadoCivil");
	for(CatalogoDTO opcion : opciones){
		if(opcion.getClave().longValue() ==  ((Long)clave).longValue()){
%>									<option value="<%=opcion.getClave()%>" selected="selected" ><%=opcion.getValor()%></option><%
		}else{		
%>									<option value="<%=opcion.getClave()%>"><%=opcion.getValor()%></option>
<%
		}
	}
%>
							</select>
						</td>
					</tr>
				</table>
	       	</dd>
			<dt id="cejaDomicilio">Domicilio</dt>
	  		<dd>
				<jsp:include page="../ingresarDomicilioView.jsp"></jsp:include>
	  		</dd>
			<dt id="cejaMediosContacto">Medios de Contacto</dt>
	   		<dd>
				<jsp:include page="../ingresarMediosContactoView.jsp"/>
			</dd>
			<dt id="cejaMediaFiliacion">Media Filiación</dt>
			<dd>
				<jsp:include page="../mediaFiliacionDefensoria.jsp"></jsp:include>
			</dd>
			<dt id="cejaDetenido">Delitos</dt>
			<dd>
	        	<jsp:include page="../seleccionarDelitoView.jsp"></jsp:include>
			</dd>
			<dt id="cejaDetenido">Hechos</dt>
			<dd>
			<!--Inicia pestañas de hechos dentro de acordeon hechos -->
				<div id="tabsHechos" class="tabEstilo"> 
					<ul>
						<li id="hechosIngHechosTab"><a href="#tabsHechos-1">Hechos</a></li>
						<li id="fechaHoraTab"><a href="#tabsHechos-3">Fecha y hora de los hechos</a></li>
					</ul>
		    	<!--Inicia pestaña Hechos -->
		 			<div id="tabsHechos-1">
		   				<textarea class="jquery_ckeditor" cols="150" id="editor1" name="editor1" rows="10"></textarea>
					</div>
				<!--Termina pestaña Hechos -->
		
				<!--Inicia pestaña Fecha y hora de los hechos -->
					<div id="tabsHechos-3">
						<table id="datosDetenido" width="400" height="150" border="0" cellspacing="0" cellpadding="10">
		           		<tr>
		                	<td colspan="2" height="20" valign="middle" class="seccion">Ingresar datos de detenci&oacute;n</td>
		               	</tr>
		               	<tr>
		                	<td align="left">
		                    	<input type="submit" id="ingresarTiempoEspecificamentePResponsable" value="Espec&iacute;ficamente"></input>
		                 	</td>
		                 	<td width="55%" rowspan="3">
		                   		<div id="divEspecifico" style="display: block;">
									<jsp:include page="/WEB-INF/paginas/ingresarTiempoEspecificamente.jsp" flush="true"></jsp:include>
								</div>
								<div id="divLapso" style="display: none;">
									<jsp:include page="/WEB-INF/paginas/ingresarTiempoLapso.jsp" flush="true"></jsp:include>
								</div>
								<div id="divOtro" style="display: none;">
		  							Evento en el tiempo: <br/>
		      						&nbsp;&nbsp;&nbsp;<textarea rows="5" cols="100" id="textNarrativa" ></textarea>
					  			</div>						                		
							</td>
						</tr>
		 				<tr>
				 			<td align="left">
		    					<input style="width:112px" type="submit" id="ingresarTiempoLapsoPResponsable" value="Lapso" />
							</td>
						</tr>
						<tr>
							<td align="left">
			    				<input style="width:112px" type="submit" id="ingresarTiempoOtroPResponsable" value="Otro" />
							</td>
		               	</tr>
		             	</table>
					</div>
				<!--Termina pestaña Fecha y hora de los hechos -->
				</div>
	 	<!--Termina pestañas de hechos dentro de acordeon hechos -->
			</dd>
		</dl>
	</div>
	</center>
</body>
</html>