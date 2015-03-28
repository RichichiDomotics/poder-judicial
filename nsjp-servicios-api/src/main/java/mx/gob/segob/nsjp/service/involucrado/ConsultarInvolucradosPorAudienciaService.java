/**
* Nombre del Programa : ConsultarInvolucradosPorAudienciaService.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/06/2011
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
package mx.gob.segob.nsjp.service.involucrado;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface ConsultarInvolucradosPorAudienciaService {

	/**
	 * Operación que realiza la funcionalidad de consultar los involucrados en una audiencia.
	 * 
	 * @param calidad: La calidad del involucrado (si no recibe calidad, consulta todas las calidades).
	 * @param audiencia: El identificador de la audiencia
	 * @return Devuelve un listado de involucrados asociados a la audiencia, en caso contrario null.
	 * @throws NSJPNegocioException
	 */
	List<InvolucradoDTO> consultarInvolucradosPorAudiencia(Long calidadValor,
			AudienciaDTO audienciaDTO)throws NSJPNegocioException;

}
