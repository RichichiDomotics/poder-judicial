/**
* Nombre del Programa : ConsultarDelitosPorImputadoService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para el servicio que permite la consulta de los delitos asociados a un inputado en un expediente
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
package mx.gob.segob.nsjp.service.delito;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;

/**
 * Contrato para el servicio que permite la consulta de los delitos asociados a un inputado en un expediente
 * @version 1.0
 * @author rgama
 *
 */
public interface ConsultarDelitosPorImputadoService {

	public List<DelitoDTO> consultarDelitosPorImputado(Long idInvolucrado, Long idExpediente) throws NSJPNegocioException;
}
