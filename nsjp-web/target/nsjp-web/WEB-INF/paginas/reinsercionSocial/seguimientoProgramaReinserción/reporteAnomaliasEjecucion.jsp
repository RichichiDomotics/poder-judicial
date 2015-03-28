<%@page import="java.util.Date"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>

<fieldset>
	
	<table border="0">
		<tr>
			<td align="right">
				<strong>Tipo de Anomal&iacute;a:</strong> 
			</td>
			<td align="left">
				<html:select name="ReinsercionSocial" property="tipoAnomaliaRAE"
					styleId="tipoAnomaliaRAE" styleClass="texto">
					<option value="-1">
						SELECCIONE UNA OPCI&Oacute;N
					</option>
					<html:optionsCollection name="ReinsercionSocial"
						property="lstTipoAnomaliasRAE" />
				</html:select>
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="right">
				<strong>Descripci&oacute;n:</strong>
			</td>
			<td align="left" colspan="3">
				<html:textarea name="ReinsercionSocial" property="descripcionTMD"
					styleId="descripcionTMD" styleClass="texto" style="width: 100%" >
				</html:textarea>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				&nbsp;
			</td>
			<td align="center">
				<html:button styleId="btnBuscar" property="" styleClass="btn_buscar"
					onclick=";">Continuar</html:button>
			</td>
		</tr>		
			
	</table>
</fieldset>