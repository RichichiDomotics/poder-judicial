/**
* Nombre del Programa : modificarProbableResponsable.js
* Autor               : Arturo León
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 08/03/2011
* Marca de cambio     : N/A
* Descripcion General : Archivo javaScript con las funciones para la vista de ModificarProbableResponsable
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

/**
 * Función que crea los radiobuttons para Condición en modificarProbableResponsable
 * @param contexto Contexto de la aplicación
 */
function cargarRbtCondicion(contexto) {
	$.ajax({
		type: 'POST',
	    url:  contexto + '/CargarCondicionProbableResponsable.do',
	    data: '',
	    dataType: 'xml',
	    async: false,
	    success: function(xml){
	    	$('#tdProbResponsableCondicion').empty();
		    $(xml).find('condicionDTO').each(function(){
			    $('#tdProbResponsableCondicion').append('<input type="radio" name="rbtProbResponsableCondicion" value="' + $(this).find('glCatalogoId').text() +'">' + $(this).find('gcDescripcion').text() + '<br/>');
		    });
	    }
	});
}