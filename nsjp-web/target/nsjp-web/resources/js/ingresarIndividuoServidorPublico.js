/**
* Nombre del Programa : ingresarIndividuoServidorPublico.js
* Autor               : Arturo León
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 09/05/2011
* Marca de cambio     : N/A
* Descripcion General : Archivo javaScript con las funciones para la vista de IngresarIndividuoServidorPublico
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
/*$('#cmbTipoTeléfono option:selected').val()*/

/**
 * Función que crea inicializa los controles asignados en la pantalla de Ingresar medio de contacto
 * @param contexto Contexto de la aplicación
 */
function inicializaServidorPublico(contexto){
	cargarCombo(contexto,'/CargarCatalogoNivelDependencia.do','#cmbServPublicoNivelDependencia');//Combo Nivel de dependencia
	cargarCombo(contexto,'/CargarCatalogoPuesto.do','#cmbServPublicoPuesto');//Combo Puesto
	$('#cmbServPublicoTipoDependencia').attr('disabled','-1');
    $('#cmbServPublicoDependencias').attr('disabled','-1');
	$('#cmbServPublicoNivelDependencia').change(function(){
		if($(this).val() == -1){
			$('#cmbServPublicoTipoDependencia').attr('selectedIndex',0);
			$('#cmbServPublicoTipoDependencia').attr('disabled','-1');
			$('#cmbServPublicoDependencias').attr('selectedIndex',0);
		    $('#cmbServPublicoDependencias').attr('disabled','-1');
		}else{
			$('#cmbServPublicoTipoDependencia').empty();
			$('#cmbServPublicoTipoDependencia').append('<option value="-1">-Seleccione-</option>');
			var retornaResultado = false;
			$.ajax({
				async: false,									
				type: 'POST',
				url: contexto + '/CargarCatalogoTipoDependencia.do',
				data: 'nivelDependenciaServPublico=' + $(this).val(),
				dataType: 'xml',
				success: function(xml){
					$(xml).find('catalogoDTO').each(function(){
						$('#cmbServPublicoTipoDependencia').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						retornaResultado = true;
					});
				}
			});
			if(retornaResultado){
				$('#cmbServPublicoTipoDependencia').removeAttr('disabled');
			}else{
				alert('No se encontraron resultados para esa selección');
			}
		}
	});
	$('#cmbServPublicoTipoDependencia').change(function(){
		if($(this).val() == -1){
			$('#cmbServPublicoDependencias').attr('selectedIndex',0);
		    $('#cmbServPublicoDependencias').attr('disabled','-1');
		}else{
			$('#cmbServPublicoDependencias').empty();
			$('#cmbServPublicoDependencias').append('<option value="-1">-Seleccione-</option>');
			var retornaResultado = false;
			$.ajax({
				async: false,									
				type: 'POST',
				url: contexto + '/CargarCatalogoDependencia.do',
				data: 'tipoDependenciaServPublico=' + $(this).val(),
				dataType: 'xml',
				success: function(xml){
					$(xml).find('catalogoDTO').each(function(){
						$('#cmbServPublicoDependencias').append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
						retornaResultado = true;
					});
				}
			});
			if(retornaResultado){
				$('#cmbServPublicoDependencias').removeAttr('disabled');
			}else{
				alert('No se encontraron resultados para esa selección');
			}
		}
	});
}

/**
* Función que carga un combo
*/
function cargarCombo(contexto, nombreAccion,nombreCombo) {
    $.ajax({
    	  type: 'POST',
    	  url:  contexto + nombreAccion,
    	  data: '',
    	  dataType: 'xml',
    	  async: false,
    	  success: function(xml){
    	      $(xml).find('catalogoDTO').each(function(){
			      $(nombreCombo).append('<option value="' + $(this).find('clave').text() + '">' + $(this).find('valor').text() + '</option>');
			  });
    	  }
    });
}

/**
 * Función encargada de construir un String con los datos de los arreglos de telefonos y correos
 */
function obtenerDatosServidorPublico(){
	var parametros = 'dependenciaServPublico=' + $('#cmbServPublicoDependencias option:selected').val();
	parametros += '&puestoServPublico='+ $('#cmbServPublicoDependencias option:selected').val();
	parametros += '&numEmpleadoServPublico='+ $('#txtServPublicoNumEmpleado').val();
	parametros += '&nivelDependenciaServPublico='+ $('#cmbServPublicoNivelDependencia option:selected').val();
	parametros += '&tipoDependenciaServPublico='+ $('#cmbServPublicoTipoDependencia option:selected').val();
	
	//alert("servidor Publico:" + parametros);
	return parametros;
}