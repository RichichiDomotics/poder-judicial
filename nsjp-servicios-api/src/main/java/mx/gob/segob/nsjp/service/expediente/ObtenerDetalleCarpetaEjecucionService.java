/**
* Nombre del Programa : ObtenerDetalleCarpetaEjecucionService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de servicio para obtener la informacion de una carpeta de ejecucion
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
package mx.gob.segob.nsjp.service.expediente;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Contrato de servicio para obtener la informacion de una carpeta de ejecucion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ObtenerDetalleCarpetaEjecucionService {

	/**
	 * Obtiene la informacion a detalle de la carpeta de ejecucion
	 * @author cesarAgustin
	 * @param expedienteDTO
	 * 			<li>numeroExpedienteId<li>
	 * @return Carpeta ejecucion obtenida
	 * @throws NSJPNegocioException
	 */
	public ExpedienteDTO obtenerDetalleCarpetaEjecucion (ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
}
