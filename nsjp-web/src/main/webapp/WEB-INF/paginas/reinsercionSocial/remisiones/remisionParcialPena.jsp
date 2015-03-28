<%@page import="java.util.Date"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>

<script type="text/javascript">
<%Long noCache = new Date().getTime();%>
	$(document).ready(
			function() {
				
		$( "#tabsContenido<%=noCache%>").tabs();
	
		$("#fechaAudienciaRRD").datepicker({
			maxDate : "+0D"
		});
		
		
	});
</script>
<div id="tabsContenido<%=noCache%>">
	<ul>
		<li>
			<a href="#tabs-1">Elaborar oficio de reparaci&oacute;n de da&ntilde;os</a>
		</li>
		<li>
			<a href="#tabs-2">Elaborar solicitud de Audiencia de reducci&oacute;n por reparaci&oacute; del da&ntilde;o</a>
		</li>
	</ul>
	<div id="tabs-1">
		<fieldset>
			<table border="0">
				<tr>
					<td align="right">
						<strong>Remitente:</strong>
					</td>
					<td align="left">
						<html:text name="ReinsercionSocial" property="remitenteRRD"
							styleId="remitenteRRD" styleClass="texto"/>
					</td>
					<td align="right">
						&nbsp;
					</td>
					<td align="left">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="right">
						<strong>Motivos de la reparaci&oacute;n del da&ntilde;o:</strong>
					</td>
					<td align="left" colspan="3">
						<html:textarea name="ReinsercionSocial" property="motivoRRD"
							styleId="motivoRRD" styleClass="texto"> 
						</html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right" colspan="2">
						<strong>Decisi&oacute;n:</strong> 
					</td>
					<td align="left" colspan="2">
						<html:select name="ReinsercionSocial" property="decisionRRD"
							styleId="decisionRRD" styleClass="texto">
							<option value="-1">
								SELECCIONE UNA OPCI&Oacute;N
							</option>
							<html:optionsCollection name="ReinsercionSocial"
								property="lstAprobacion" />
						</html:select>
					</td>				
				</tr>				
				<tr>
					<td colspan="3">
						&nbsp;
					</td>
					<td align="center">
						<html:button styleId="btnBuscar" property=""
							styleClass="btn_buscar" onclick=";">Guardar</html:button>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
	<div id="tabs-2">
		<fieldset>
			<table border="0">
				<tr>
					<td align="right">
						<strong>Fecha de audiencia:</strong>
					</td>
					<td align="left">
						<html:text name="ReinsercionSocial" property="fechaAudienciaRRD"
							styleId="fechaAudienciaRRD" styleClass="texto" />
					</td>
					<td align="right">
						&nbsp;
					</td>
					<td align="left">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="3">
						&nbsp;
					</td>
					<td align="center">
						<html:button styleId="btnBuscar" property=""
							styleClass="btn_buscar" onclick=";">Guardar</html:button>
					</td>
				</tr>
			</table>
		</fieldset>
	</div>
</div>