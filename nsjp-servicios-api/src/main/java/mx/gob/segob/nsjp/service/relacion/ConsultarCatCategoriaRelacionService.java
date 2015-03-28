/**
* Nombre del Programa : ConsultarCatCategoriaRelacionService.java
* Autor                            : adrian
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
package mx.gob.segob.nsjp.service.relacion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.relacion.CatCategoriaRelacionDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface ConsultarCatCategoriaRelacionService {

	/**
     * Consulta todas las categorias de relación que sean o no documento
     * @param esDocumento: Boolean que indica si se quiere que sea o no documento
     * @return
     * @throws NSJPNegocioException
     */
	List<CatCategoriaRelacionDTO> consultarCatCategoriaRelacionSiDocumento(
			Boolean esDocumento)throws NSJPNegocioException;

}
