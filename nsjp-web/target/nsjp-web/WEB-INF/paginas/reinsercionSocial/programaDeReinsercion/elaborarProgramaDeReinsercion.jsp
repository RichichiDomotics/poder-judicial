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
				<strong>Nivel inicial:</strong> 
			</td>
			<td align="left">
				<html:select name="ReinsercionSocial" property="nivelInicial"
					styleId="nivelInicial" styleClass="texto">
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
				<strong>Desempe&ntilde;o:</strong>
			</td>
			<td align="left">
				<html:select name="ReinsercionSocial" property="desempenioInicial"
					styleId="desempenioInicial" styleClass="texto">
					<option value="-1">
						SELECCIONE UNA OPCI&Oacute;N
					</option>
					<html:optionsCollection name="ReinsercionSocial"
						property="lstDesempenios" />
				</html:select>
			</td>
			<td align="right">
				<strong>Califiaci&oacute;n:</strong>
			</td>
			<td align="left">
				<html:select name="ReinsercionSocial" property="desempenioInicial"
					styleId="desempenioInicial" styleClass="texto">
					<option value="-1">
						SELECCIONE UNA OPCI&Oacute;N
					</option>
					<html:optionsCollection name="ReinsercionSocial"
						property="lstCalificacionDesempenio" />
				</html:select>
			</td>		
		</tr>
		<tr>
			<td align="right">
				<strong>Observaciones:</strong>
			</td>
			<td align="left" colspan="5">
				<html:textarea name="ReinsercionSocial" property="observaciones"
					styleId="observaciones" styleClass="texto" style="width: 100%" >
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