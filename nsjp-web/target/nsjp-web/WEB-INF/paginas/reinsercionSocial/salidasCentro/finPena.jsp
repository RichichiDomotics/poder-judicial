<%@page import="java.util.Date"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>

<script type="text/javascript">
	$(document).ready(function() {
		$("#fechaCumplimientoFP").datepicker({
			maxDate : "+0D"
		});

		$("#fechaAudienciaFP").datepicker({
			maxDate : "+0D"
		});

	});
</script>
<table>
	<tr>
		<td>
			<fieldset>
				<legend>Elaborar informe final de cumplimiento de pena</legend>
				<table border="0">
					<tr>
						<td align="right">
							<strong>Motivo del final de pena:</strong>
						</td>
						<td align="left">
							<html:text name="ReinsercionSocial" property="motivoFP"
								styleId="motivoFP" styleClass="texto" />
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
							<strong>Observaciones:</strong>
						</td>
						<td align="left" colspan="3">
							<html:textarea name="ReinsercionSocial"
								property="observacionesFP" styleId="observacionesFP"
								styleClass="texto" style="width: 100%">
							</html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							<strong>Fecha final del cumplimiento de la pena:</strong>
						</td>
						<td align="left">
							<html:text name="ReinsercionSocial"
								property="fechaCumplimientoFP" styleId="fechaCumplimientoFP"
								styleClass="texto" />
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
					</tr>
				</table>
			</fieldset>
		</td>
	</tr>
	<tr>
		<td>
			<fieldset>
			<legend>Solicitud de Audiencia de ejecuci&oacute;n</legend>
				<table border="0">
					<tr>
						<td align="right">
							<strong>Fecha de audiencia de ejecuci&oacute;n</strong>
						</td>
						<td align="left">
							<html:text name="ReinsercionSocial" property="fechaAudienciaFP"
								styleId="fechaAudienciaFP" styleClass="texto" />
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
							<strong>Tribunal:</strong>
						</td>
						<td align="left">
							<html:select name="ReinsercionSocial" property="tribunalFP"
								styleId="tribunalFP" styleClass="texto">
								<option value="-1">
									SELECCIONE UNA OPCI&Oacute;N
								</option>
								<html:optionsCollection name="ReinsercionSocial"
										property="lstTribunales" />
							</html:select>	
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
							<strong>Juez:</strong>
						</td>
						<td align="left">
							<html:select name="ReinsercionSocial" property="juezFP"
								styleId="juezFP" styleClass="texto">
								<option value="-1">
									SELECCIONE UNA OPCI&Oacute;N
								</option>
								<html:optionsCollection name="ReinsercionSocial"
										property="lstJuez" />
							</html:select>								
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
					</tr>
				</table>
			</fieldset>
		</td>
	</tr>
	<tr>
		<td align="right">
			<html:button styleId="btnBuscar" property="" styleClass="btn_buscar"
				onclick=";">Guardar</html:button>
		</td>
	</tr>

</table>

