/**
* Nombre del Programa : ConsultarCausasPorIdCasoService.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio de Consultar Causas por IdCaso
* en base al area y al usuario logueado en el sistema
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

public interface ConsultarCausasPorIdCasoService {
	/**
	 * Metodo que permite consultar las causas asociadas a un caso
	 * @param idCaso
	 * @return List<ExpedienteDTO>
	 * @throws NSJPNegocioException
	 */
    List<ExpedienteDTO> consultarCausasPorIdCasoService(Long idCaso) throws NSJPNegocioException;
}
