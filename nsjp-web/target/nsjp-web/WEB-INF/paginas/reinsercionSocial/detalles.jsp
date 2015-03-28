<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>
<fieldset>
	<legend>
		Datos del Expediente:
	</legend>
	<table width="99%" border="0">
		<tr>
			<td align="right">
				<strong>NUC:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="nuc" styleId="nuc"
					styleClass="texto" />
			</td>
			<td align="right">
				<strong>Nombre:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="nombre"
					styleId="nombre" styleClass="texto" />
			</td>
			<td align="right">
				<strong>Apellido Paterno:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="apellidoPaterno"
					styleId="apellidoPaterno" styleClass="texto" />
			</td>
			<td align="right">
				<strong>Apellido Materno:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="apellidoMaterno"
					styleId="apellidoMaterno" styleClass="texto" />
			</td>
		</tr>
		<tr>
			<td align="right">
				<strong>CERESO:</strong>
			</td>
			<td align="left">
				<html:select name="ReinsercionSocial" property="cereso"
					styleId="cereso" styleClass="texto">
					<option value="-1">
						SELECCIONE UNA OPCI&Oacute;N
					</option>
					<html:optionsCollection name="ReinsercionSocial"
						property="ceresosDTO" value="centroDetencionId" label="nombre" />
				</html:select>
			</td>
			<td align="right">
				<strong>Caso:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="caso" styleId="caso"
					styleClass="texto" />
			</td>
			<td align="right">
				<strong>Causa:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="causa" styleId="causa"
					styleClass="texto" />
			</td>
			<td align="right">
				<strong>Carpeta:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="carpeta"
					styleId="carpeta" styleClass="texto" />
			</td>
		</tr>		
		
		<logic:equal name="ReinsercionSocial" property="ocultarBuscar" value="MOSTRAR">
			<tr>
				<td colspan="7">
					&nbsp;
				</td>
				<td align="center">
					<html:button styleId="btnBuscar" property="" styleClass="btn_buscar"
						onclick="buscar();">Buscar</html:button>
				</td>
			</tr>		
		</logic:equal>
	</table>
</fieldset>
