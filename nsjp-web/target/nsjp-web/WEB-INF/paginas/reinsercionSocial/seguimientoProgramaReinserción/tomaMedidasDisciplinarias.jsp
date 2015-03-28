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
				$( "#fechaTMD" ).datepicker(
					{ 
						maxDate: "+0D" 
					}
				);
				$( "#horaTMD" ).timepicker({
//						hourGrid: 4,
//						minuteGrid: 10
				});			
			}
		);
</script>
<fieldset>
	
	<table border="0">
		<tr>
			<td align="right">
				<strong>Motivo:</strong> 
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="motivoTMD"
					styleId="motivoTMD" styleClass="texto" />
			</td>
			<td align="right">
				<strong>Fecha:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="fechaTMD"
					styleId="fechaTMD" styleClass="texto" />
			</td>
			<td align="right">
				<strong>Hora:</strong>
			</td>
			<td align="left">
				<html:text name="ReinsercionSocial" property="horaTMD"
					styleId="horaTMD" styleClass="texto" />
			</td>			
		</tr>
		<tr>
			<td align="right">
				<strong>Descripci&oacute;n:</strong>
			</td>
			<td align="left" colspan="5">
				<html:textarea name="ReinsercionSocial" property="descripcionAnomaliaRAE"
					styleId="descripcionAnomaliaRAE" styleClass="texto" style="width: 100%" >
				</html:textarea>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				&nbsp;
			</td>
			<td align="center">
				<html:button styleId="btnBuscar" property="" styleClass="btn_buscar"
					onclick=";">Guardar</html:button>
			</td>
		</tr>		
			
	</table>
</fieldset>