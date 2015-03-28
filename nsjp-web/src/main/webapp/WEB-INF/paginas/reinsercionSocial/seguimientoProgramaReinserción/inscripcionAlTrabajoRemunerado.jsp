<%@page import="java.util.Date"%>
<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>
<script type="text/javascript">
<!--


	$(document).ready(
			function() {
				$( "#fechaInicioSTR" ).datepicker(
					{ 
						maxDate: "+0D" 
					}
				);
				$( "#horarioEntradaSTR" ).timepicker({
//						hourGrid: 4,
//						minuteGrid: 10
				});		
				$( "#horarioSalidaSTR" ).timepicker({
//						hourGrid: 4,
//						minuteGrid: 10
				});							
			}
		);

//-->
</script>
<fieldset>

	<table border="0">
		<tr>
			<td colspan="2">
				<fieldset>
					<legend>
						Externos:
					</legend>
					<table border="0">
						<tr>
							<td align="right">
								<strong>Empresa:</strong>
							</td>
							<td align="left">
								<html:text name="ReinsercionSocial" property="empresaSTR"
									styleId="empresaSTR" styleClass="texto" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<strong>Convenio:</strong>
							</td>
							<td align="left">
								<html:text name="ReinsercionSocial" property="convenioSTR"
									styleId="convenioSTR" styleClass="texto" />
							</td>
						</tr>
					</table>
				</fieldset>
			</td>
			<td colspan="2">
				<fieldset>
					<legend>
						Internos:
					</legend>
					<table border="0">
						<tr>
							<td align="right">
								<strong>Trabajo:</strong>
							</td>
							<td align="left">
								<html:select name="ReinsercionSocial" property="desempenioInicial"
									styleId="desempenioInicial" styleClass="texto">
									<option value="-1">
										SELECCIONE UNA OPCI&Oacute;N
									</option>
									<html:optionsCollection name="ReinsercionSocial"
										property="lstTrabajoInterno" />
								</html:select>
							</td>
						</tr>
						<tr>
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
			<td>&nbsp;</td>
			<td align="right">
				<strong>Fecha de inicio:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="fechaInicioSTR"
					styleId="fechaInicioSTR" styleClass="texto" />
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td align="right">
				<strong>Hora de entrada:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="horarioEntradaSTR"
					styleId="horarioEntradaSTR" styleClass="texto" />
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr>			
			<td>&nbsp;</td>
			<td align="right">
				<strong>Hora de salida:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="horarioSalidaSTR"
					styleId="horarioSalidaSTR" styleClass="texto" />
			</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td align="right">
				<strong>Periodo:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="periodoSTR"
					styleId="periodoSTR" styleClass="texto" />
			</td>
			<td>&nbsp;</td>
		</tr>														
		<tr>
			<td align="right">
				<strong>Observaciones:</strong>
			</td>
			<td align="left" colspan="3">
				<html:textarea name="ReinsercionSocial" property="observacionesSTR"
					styleId="observacionesSTR" styleClass="texto" style="width: 100%" >
				</html:textarea>
			</td>
		</tr>			
		<tr>
			<td align="right" colspan="2">
				<strong>Decisi&oacute;n:</strong>
			</td>
			<td align="left" colspan="2">
				<html:select name="ReinsercionSocial" property="aprobacionSTR"
					styleId="aprobacionSTR" styleClass="texto">
					<option value="-1">
						SELECCIONE UNA OPCI&Oacute;N
					</option>
					<html:optionsCollection name="ReinsercionSocial"
						property="lstAprobacion" />
				</html:select>			
			</td>
		</tr>		
		<tr>
		<td align="center" colspan="3">
		 &nbsp;
		</td>
			<td align="center" colspan="1">
				<html:button styleId="btnBuscar" property="" styleClass="btn_buscar"
					onclick=";">Responder</html:button>
			</td>
		</tr>
	</table>
</fieldset>