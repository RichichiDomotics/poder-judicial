<%@page import="mx.gob.segob.nsjp.comun.enums.actividad.Actividades"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<script type="text/javascript">
<!--
	$.ajaxSetup({ cache: false });
	var contextoPagina = "${pageContext.request.contextPath}";
	var cNumeroExpediente = "";
	var numeroExpedienteId = "";
	var expedienteId = "";
				
	//Comienza funcion on ready del documento
	$(document).ready(function() {
		$("#cbxAccionesTab").change(function(e){
			seleccionaActuacion();
		});
		
		cNumeroExpediente = jQuery("#carpeta").val();
		numeroExpedienteId = jQuery("#numeroExpedienteId").val();
		expedienteId = jQuery("#expedienteId").val();
		sentenciaId = jQuery("#sentenciaId").val();
		cargaActuaciones("1");
	});		
	
	/*
	*Funcion que realiza la carga del combo de Actuaciones
	*/
	function cargaActuaciones(id) {

		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/cargarActuaciones.do?id='+id+'&numeroExpediente='+cNumeroExpediente+'&numeroExpedienteId='+numeroExpedienteId,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				$('#cbxAccionesTab').append('<option value="-1">--Seleccione una opci&oacute;n--</option>');
				$(xml).find('catActuaciones').each(function(){
					$('#cbxAccionesTab').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
				});            
			}
		});
	}	
	 
	function registrarActividadExpediente(actuacionID,estatusId,banderaMensaje){
		//Cambia la actividad del expediente
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/registraActividadExpediente.do?idExpediente='+expedienteId+'&idNumeroExpediente='+numeroExpedienteId+'&actuacion='+actuacionID+'&estatus='+estatusId+'&numExpe='+cNumeroExpediente,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				if(parseInt(banderaMensaje)==1)
				{
					customAlert("Actividad nueva registrada");
				}
			}
		});
	}	 
	 
	 
	function seleccionaActuacion(){
		var selected = $("#cbxAccionesTab option:selected");
		var confActividadId=selected.val();
		var actividad=0;
		var formaID=4;
		var titulo="op";
		var usaeditor="";
		var estatusId="";
		$.ajax({
			type: 'POST',
			url: '<%= request.getContextPath()%>/obtenerConfActividadDocumento.do?idConf='+confActividadId,
			data: '',
			dataType: 'xml',
			async: false,
			success: function(xml){
				actividad=$(xml).find('confActividadDocumentoDTO').find('tipoActividadId').text();
				formaID=$(xml).find('confActividadDocumentoDTO').find('formaId').text();
				titulo=$(xml).find('confActividadDocumentoDTO').find('nombreDocumento').text();
				usaeditor=$(xml).find('confActividadDocumentoDTO').find('usaEditor').text();
				estatusId=$(xml).find('confActividadDocumentoDTO').find('estadoCambioExpediente').find('idCampo').text();
			}
		});
		actuacion=actividad;

		if(usaeditor == "true"){			
			var parametros = "?formaId="+formaID+"&numeroUnicoExpediente="+cNumeroExpediente + "&actividadId=" + actividad + "&sentenciaId=" + sentenciaId;
			 
			registrarActividadExpediente(actividad,estatusId,0);
			customVentana("iframewindowElaborarDocumentoRS", ""+titulo, "/elaborarDocumentosRS.do", parametros, function (){
				$('#cbxAccionesTab').empty();
				cargaActuaciones("");
				llenarGridDocumentos(listaDesplegada);	
			});
    			
		}

	}	 
	 
//-->
</script>
		<fieldset>
			<table border="0">
				<tr>
					<td>
					Actuaciones: <html:select name="DatosGeneralesReinsercionForm" property="actuacionSeleccionada"
									styleId="cbxAccionesTab">
								 	<!--  option value="-1">--Seleccione una opci&oacute;n--</option -->
								 	<!-- html : optionsCollection name="DatosGeneralesReinsercionForm" property="actuaciones" label="nombre" value="programaId" /-->
								 </html:select>
					</td>
				</tr>
			</table>
		</fieldset>