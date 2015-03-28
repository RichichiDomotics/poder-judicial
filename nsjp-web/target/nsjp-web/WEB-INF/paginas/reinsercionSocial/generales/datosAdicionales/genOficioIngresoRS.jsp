<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>

<script type="text/javascript">
	jQuery().ready(function () {
	
		jQuery( "#fechaDeIngreso" ).datepicker ( {
			maxDate: "+0D",
			showOn: "button",
			buttonImage: "<%= request.getContextPath()%>/resources/images/date.png",
			buttonImageOnly: true	 
		});

	
		jQuery( "#datosAdicionalesModal" ).dialog({

			title: "Seleccionar Centro De Detención",
			buttons: {
				"Continuar": function() {
					asignarCentroDetencion();
				},
				"Cancelar": function() {
					jQuery( this ).dialog( "close" );					
				}
			}
		});	
	});

	function asignarCentroDetencion() {

		var centroDetencionId = jQuery("#centroDetencionId").val(); 
		var fechaDeIngreso = jQuery("#fechaDeIngreso").val();
		if(centroDetencionId == "-1"){
			customAlert("Debe seleccionar un <strong>Centro de Detención</strong>", "Validación");
			return false;
		}
		
		if(!esFecha(fechaDeIngreso)){
			customAlert("Debe ingresar una <strong>Fecha de Ingreso</strong>", "Validación");
			return false;
		}
		
		
		jQuery( "#datosAdicionalesModal" ).dialog( "close" );
		bloquearPantalla();		
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/asignarCentroDetencionASentencia.do?fechaDeIngreso='+fechaDeIngreso+'&centroDetencionId='+centroDetencionId+'&sentenciaId='+sentenciaId,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				var asignacionCentroDetencionId = jQuery(xml).find('respuesta').find('asignacionCentroDetencionId').text(); 
				if (!isNaN(asignacionCentroDetencionId)) {
					if(asignacionCentroDetencionId > 0){
						enviarDatosFinales();
					}
				}
				
				
			}
		});
	}	


</script>

<fieldset>
	<table width="99%" border="0">
		<tr>
			<td align="right">
				<strong>Centro de Detención:</strong>
			</td>
			<td align="left">
				<html:select name="EnvioDocumentosReinsercionForm" property="cereso"
					styleId="centroDetencionId" styleClass="texto">
					<option value="-1">
						SELECCIONE UNA OPCI&Oacute;N
					</option>
					<html:optionsCollection name="EnvioDocumentosReinsercionForm"
						property="lstCentrosDetencion" value="centroDetencionId" label="nombre" />
				</html:select>
			</td>
		</tr>	
		<tr>
			<td align="right">
				<strong>Fecha de Ingreso:</strong>
			</td>
			<td align="left">
				<html:text name="EnvioDocumentosReinsercionForm" property="fechaDeIngreso" 
				 styleId="fechaDeIngreso" size="15" />
			</td>
		</tr>	
	</table>
</fieldset>
