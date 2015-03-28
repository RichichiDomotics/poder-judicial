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
				$( "#fechaInicioPermisoEPS" ).datepicker(
					{ 
						maxDate: "+0D" 
					}
				);
				
				$( "#fechaFinPermisoEPS" ).datepicker(
					{ 
						maxDate: "+0D" 
					}
				);
				
				$( "#horaSalidaEPS" ).timepicker({
//						hourGrid: 4,
//						minuteGrid: 10
				});

				$( "#horaRegresoEPS" ).timepicker({
//						hourGrid: 4,
//						minuteGrid: 10
				});							
							
			}
		);
</script>
<fieldset>
	<legend>Elaborar evaluaci&oacute;n de necesidad de salida</legend>
	<table border="0">
		<tr>
			<td align="right">
				<strong>Motivo de salida:</strong> 
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="motivoEPS"
					styleId="motivoEPS" styleClass="texto" />
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="right">
				<strong>Descripci&oacute;n:</strong>
			</td>
			<td align="left" colspan="3">
				<html:textarea name="ReinsercionSocial" property="descripcionEPS"
					styleId="descripcionEPS" styleClass="texto" style="width: 100%" >
				</html:textarea>
			</td>
		</tr>
		<tr>
			<td align="right">
				<strong>Fecha Inicio Permiso:</strong> 
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="fechaInicioPermisoEPS"
					styleId="fechaInicioPermisoEPS" styleClass="texto" />
			</td>
			<td align="right">
				<strong>Fecha Fin Permiso:</strong> 
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="fechaFinPermisoEPS"
					styleId="fechaFinPermisoEPS" styleClass="texto" />
			</td>		
		</tr>
		<tr>
			<td align="right">
				<strong>Hora de salida:</strong> 
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="horaSalidaEPS"
					styleId="horaSalidaEPS" styleClass="texto" />
			</td>
			<td align="right">
				<strong>Hora de regreso:</strong> 
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="horaRegresoEPS"
					styleId="horaRegresoEPS" styleClass="texto" />
			</td>		
		</tr>
		<tr>
			<td align="right">
				<strong>Observaciones:</strong>
			</td>
			<td align="left" colspan="3">
				<html:textarea name="ReinsercionSocial" property="observacionesEPS"
					styleId="observacionesEPS" styleClass="texto" style="width: 100%" >
				</html:textarea>
			</td>
		</tr>					
		<tr>
			<td align="right" colspan="2">
				<strong>Decisi&oacute;n:</strong> 
			</td>
			<td align="left" colspan="2">
				<html:select name="ReinsercionSocial" property="descicionEPS"
					styleId="descicionEPS" styleClass="texto">
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