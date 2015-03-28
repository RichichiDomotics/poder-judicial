<%@page import="java.util.Date"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>

<script type="text/javascript">
	$(document).ready(
			function() {
				$( "#fechaCertificado" ).datepicker(
					{ 
						maxDate: "+0D" });
			}
		);
</script>

<fieldset>
	
	<table border="0">
		<tr>
			<td align="right">
				<strong>Certificado del nivel:</strong> 
			</td>
			<td align="left">
				<html:select name="ReinsercionSocial" property="nivelCertificado"
					styleId="nivelCertificado" styleClass="texto">
					<option value="-1">
						SELECCIONE UNA OPCI&Oacute;N
					</option>
					<html:optionsCollection name="ReinsercionSocial"
						property="lstNiveles" />
				</html:select>
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="right">
				<strong>Con Fecha:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="fechaCertificado"	
							styleId="fechaCertificado" styleClass="texto" />
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>		</tr>
		<tr>
			<td colspan="3">
				&nbsp;
			</td>
			<td align="center">
				<html:button styleId="btnBuscar" property="" styleClass="btn_buscar"
					onclick=";">Guardar</html:button>
			</td>
		</tr>			
	</table>
</fieldset>