/**
 * 
 */
package mx.gob.segob.nsjp.service.elemento;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;


/**
 * @author MelitonBC
 *
 */
public interface AnularElementoService {
	
	/**
	 * Metodo para para Anular el Elemento de acuerdo al idElemento,
	 * considerando las relaciones asociadas a él, de acuerdo a:
	 * -Si el elemento es un Objeto se elimina:
	 * 		-Evidencia
	 * -Si el elemento es un Involucrado se elimina:
	 * 		-Relaciones con otros involucrados
	 * 		-Relaciones con Delito Persona.  
	 * 
	 * @param idElemento
	 * @throws NSJPNegocioException
	 */
	public Boolean anularElemento(Long idElemento) throws NSJPNegocioException;

	/**
	 * Servicio que se encarga de consultar las  
	 * relaciones asociadas al elemento ya sea para:
	 * -Si el elemento es un Objeto se consulta:
	 * 		-Evidencia
	 * -Si el elemento es un Involucrado se consulta:
	 * 		-Relaciones con otros involucrados
	 * 		-Relaciones con Delito Persona. 
	 * 
	 * Regresa una lista de cadenas, indicando :
	 * -Si el elemento es un Objeto indica:
	 * 		-Evidencia
	 * -Si el elemento es un Involucrado indica:
	 * 		-La calidad del elemento relacionado
	 * 		-El número de delitos relacionados
	 * tipo de calidad de los elementos  
	 * @param idElemento
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<String> consultarRelacionesElemento(Long idElemento) throws NSJPNegocioException;

}
