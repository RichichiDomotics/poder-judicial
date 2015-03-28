/**
* Nombre del Programa : ConsultarElementosXIdExpedienteCatRelacionService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/07/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.elemento;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDTO;

/**
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface ConsultarElementosXIdExpedienteCatRelacionService {

	/**
	 * Consultar los elementos asociados al expediente y de acuerdo
	 * a la categoria de relacion que es solicitada.
	 * El parametro esSujeto permite determinar cual de los dos valores
	 * del CatCategoriaRelacion (Persona - Objeto, Persona - Organización)
	 * se debe de efectuar la consulta:
	 *  esSujeto: true --> Primer valor
	 *  esSujeto: false --> Segundo valor
	 * 
	 * @param idExpediente
	 * @param idCatCategoriaRelacion
	 * @param esSujeto
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ElementoDTO> consultarElementosXIdExpedienteCatRelacion(Long idExpediente, Long idCatCategoriaRelacion, Boolean esSujeto)
    throws NSJPNegocioException;


	/**
	 * Servicio que consultar lo elementos asociados a un expediente, regresando 
	 * la relacion con otros elementos, el tipo de relacíon y la Categoria de la Relacion
	 * 
	 * @param idExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<RelacionDTO> consultarElementosXIdExpediente(String numeroExpediente)
	   		throws NSJPNegocioException;
	/**
	 * Metodo que permite consultar las relaciones aosciadas a un identificador de complemento y un id de catRelacion
	 * @param idExpediente
	 * @param idCatCategoriaRelacion
	 * @param esSujeto
	 * @return
	 * @throws NSJPNegocioException
	 */
	   public List<RelacionDTO> consultarRelacionesByComplementoIdAndTipoRelacion(Long idComplemento, Long idCatRelacion)
		throws NSJPNegocioException;
	   
	   String generarCadenaElemento(ElementoDTO elemento);
		
}
