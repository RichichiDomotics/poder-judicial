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
		);
</script>
<fieldset>
	
	<table border="0">
		<tr>
			<td align="right">
				<strong>CERESO actual:</strong> 
			</td>
			<td align="left">
				<html:select name="ReinsercionSocial" property="ceresoActualTS"
					styleId="ceresoActualTS" styleClass="texto">
					<option value="-1">
						SELECCIONE UNA OPCI&Oacute;N
					</option>
					<html:optionsCollection name="ReinsercionSocial"
						property="ceresosDTO" value="centroDetencionId" label="nombre" />
				</html:select>
			</td>
			<td align="right">&nbsp;</td>
			<td align="left">&nbsp;</td>
		</tr>
		<tr>
			<td align="right">
				<strong>CERESO destino:</strong> 
			</td>
			<td align="left">
				<html:select name="ReinsercionSocial" property="ceresoDestinoTS"
					styleId="ceresoDestinoTS" styleClass="texto">
					<option value="-1">
						SELECCIONE UNA OPCI&Oacute;N
					</option>
					<html:optionsCollection name="ReinsercionSocial"
						property="ceresosDTO" value="centroDetencionId" label="nombre" />
				</html:select>
			</td>
			<td align="right">&nbsp;</td>
			<td align="left">&nbsp;</td>
		</tr>
		<tr>
			<td align="right">
				<strong>Motivo:</strong> 
			</td>
			<td align="left">
				<html:select name="ReinsercionSocial" property="motivoTS"
					styleId="motivoTS" styleClass="texto">
					<option value="-1">
						SELECCIONE UNA OPCI&Oacute;N
					</option>
					<html:optionsCollection name="ReinsercionSocial"
						property="lstMotivosTS" />
				</html:select>
			</td>
			<td align="right">&nbsp;</td>
			<td align="left">&nbsp;</td>
		</tr>		
		<tr>
			<td align="right">
				<strong>Observaciones:</strong>
			</td>
			<td align="left" colspan="3">
				<html:textarea name="ReinsercionSocial" property="observacionesTS"
					styleId="observacionesTS" styleClass="texto" style="width: 100%" >
				</html:textarea>
			</td>
		</tr>					
		<tr>
			<td align="right" colspan="2">
				<strong>Decisi&oacute;n:</strong> 
			</td>
			<td align="left" colspan="2">
				<html:select name="ReinsercionSocial" property="decisionTS"
					styleId="decisionTS" styleClass="texto">
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
				<html:button styleId="btnBuscar" property="" styleClass="btn_buscar"
					onclick=";">Guardar</html:button>
			</td>
		</tr>		
			
	</table>
</fieldset>