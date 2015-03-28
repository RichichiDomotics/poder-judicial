<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">

$(document).ready(function() {	

	cargaComboTipoLugarDetencion();
	$('#tipoLugarDetencion').change(cambiaTipoLugarDetencion);
	$('#centroDetencion').change(pintaDomicilioCentroDetencion);
	
		
});

//Funcion que carga el combo de tipo de lugar de detencion
function cargaComboTipoLugarDetencion() {

 $.ajax({
	  type: 'POST',
	  url: '<%= request.getContextPath()%>/cargarTipoLugarDetencion.do',
	  data: '',
	  async: false,
	  dataType: 'xml',
	  success: function(xml){
    	  $('#tipoLugarDetencion').empty();
    	  $('#tipoLugarDetencion').append('<option value="-1">- Seleccione -</option>');
    	  $(xml).find('catTipoLugarDetencion').each(function(){
			$('#tipoLugarDetencion').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
		 });
	  }
	});
}

//Funcion que carga el combo de tipo de lugar de detencion al haber alguna seleccion en tipo de lugar de detencion
function cambiaTipoLugarDetencion() {
	
	var opcionSeleccionada = $("#tipoLugarDetencion option:selected");
	
	$.ajax({
		async: false,									
		type: 'POST',
		 url: '<%= request.getContextPath()%>/cargarCentroDetencion.do',
		  data: 'tipoCentroDetencion=' + opcionSeleccionada.val(),
		  async: false,
		  dataType: 'xml',
		  success: function(xml){
	    	  $('#centroDetencion').empty();
	    	  $('#centroDetencion').append('<option value="-1">- Seleccione -</option>');
	    	  $(xml).find('catCentrosDetencion').each(function(){
				$('#centroDetencion').append('<option value="' + $(this).find('centroDetencionId').text() + '">' + $(this).find('nombre').text() + '</option>');
				
			});

		}
	});
}

//Funcion que carga la direccion del centro de detencion elegido
function pintaDomicilioCentroDetencion() {
	
	var opcionSeleccionada = $("#centroDetencion option:selected");
	
	$.ajax({
		async: false,									
		type: 'POST',
		 url: '<%= request.getContextPath()%>/consultarCentroDetencionPorId.do',
		  data: 'centroDetencion=' + opcionSeleccionada.val(),
		  async: false,
		  dataType: 'xml',
		  success: function(xml){
				
			  $('#calleDetencion').val($(xml).find('centroDetencion').find('direccion').find('calle').text());
			  $('#noExtDetencion').val($(xml).find('centroDetencion').find('direccion').find('numeroExterior').text());
			  $('#noIntDetencion').val($(xml).find('centroDetencion').find('direccion').find('numeroInterior').text());
			  $('#coloniaDetencion').val($(xml).find('centroDetencion').find('direccion').find('asentamiento').find('nombreAsentamiento').text());
			  $('#ciudadDetencion').val($(xml).find('centroDetencion').find('direccion').find('asentamiento').find('ciudadDTO').find('nombreCiudad').text());
			  $('#entFederativaDetencion').val($(xml).find('centroDetencion').find('direccion').find('asentamiento').find('municipioDTO').find('nombreEntidad').text());
	    	 
		}
	});
}

</script>

<title>Datos detención</title>
</head>
<body>
	<table align="center" width="700px">
		<tr>
			<td colspan="2" align="center">&nbsp;</td>
			<td colspan="3" align="center">&nbsp;</td>
		</tr>
		<tr>
			<td width="328" align="right">Fecha de Detenci&oacute;n: <input
				type="text" size="25" maxlength="20"
				id="datosGeneralesCmpFechaIngreso1"
				name="datosGeneralesCmpFechaIngreso2" />
			</td>
			<td width="16" align="center">&nbsp;</td>
			<td colspan="3" align="center">Hora de Detenci&oacute;n: <input
				type="text" size="25" maxlength="20"
				id="datosGeneralesCmpHoraIngreso1"
				name="datosGeneralesCmpFechaIngreso1" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">&nbsp;</td>
			<td colspan="3" align="center">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" align="center">Selecciona el lugar donde se
				encuentra detenido(a):</td>
			<td width="207" align="center"><select name="tipoLugarDetencion" id="tipoLugarDetencion">
			</select>
			</td>
			<td width="137" colspan="2" align="center"><select name="centroDetencion" id="centroDetencion">
			</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">&nbsp;</td>
			<td colspan="3" align="center">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="5" align="center"><table width="700px"
					cellspacing="0" cellpadding="0" border="0" class="fondoFuerteBordeAP">
					<tr>
						<td colspan="3" align="center"  class="toolbar fondoFuerteAP"><strong>Dirección</strong>
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
						<td width="358">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="3"><strong>Calle o Avenida: <input
								type="text" size="107" maxlength="70" id="calleDetencion"
								name="datosGeneralesCmpFechaIngreso3" disabled="disabled"/> </strong>
						</td>
					</tr>
					<tr>
						<td width="167"><strong> Numero Ext: <input
								type="text" size="10" maxlength="20" id="noExtDetencion"
								name="calleDetencion" disabled="disabled" /> </strong>
						</td>
						<td width="163"><strong>Numero Int: <input
								type="text" size="10" maxlength="20" id="noIntDetencion"
								name="calleDetencion2" disabled="disabled" /> </strong>
						</td>
						<td><strong>Asentamiento o Colonia: <input
								type="text" size="29" maxlength="20" id="coloniaDetencion"
								name="calleDetencion3" disabled="disabled"/> </strong>
						</td>
					</tr>
					<tr>
						<td colspan="2"><strong>Entidad Federativa: <input
								type="text" size="36" maxlength="20" id="entFederativaDetencion"
								name="calleDetencion5"  disabled="disabled"/> </strong>
						</td>
						<td><strong>Ciudad: <input type="text" size="49"
								maxlength="20" id="ciudadDetencion" name="ciudadDetencion" disabled="disabled" /> </strong>
						</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>