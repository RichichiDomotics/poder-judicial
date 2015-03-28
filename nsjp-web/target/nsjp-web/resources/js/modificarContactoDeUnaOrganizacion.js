/**
* Nombre del Programa : modificarContactoDeUnaOrganizacion.js
* Autor               : Armando Castaneda
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 10/03/2011
* Marca de cambio     : N/A
* Descripcion General : Archivo javaScript con las funciones para la vista de 
						modificar contacto de una organizacion;
* Programa Dependiente: N/A
* Programa Subsecuente: N/A
* Cond. de ejecucion  : N/A
* Dias de ejecucion   : N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor               :N/A
* Compania            :N/A
* Proyecto            :N/A                                   Fecha: N/A
* Modificacion        :N/A
*------------------------------------------------------------------------------
*/
//Funcion que carga los combos de tipo de identificacion
function cargaTipoDeIdentificacion(contexto) {
  $.ajax({
	  type: 'POST',
	  url: contexto + '/ingresarTipoDeDocumentoDeIdentificacion.do',
	  data: '',
	  dataType: 'xml',
	  success: function(xml){
		  $('#cbxModCOrganizacionalTipoIdentificacion').empty();
		  $('#cbxModCOrganizacionalTipoIdentificacion').append('<option value="-1">- Seleccione - </option>');
		  $(xml).find('catTipoIdentificacion').each(function(){
			$('#cbxModCOrganizacionalTipoIdentificacion').append('<option value="'
					 + $(this).find('gcNombre').text() + '">' 
					 + $(this).find('gcDescripcion').text() 
					 + '</option>');
		   });
	  }
	});
}