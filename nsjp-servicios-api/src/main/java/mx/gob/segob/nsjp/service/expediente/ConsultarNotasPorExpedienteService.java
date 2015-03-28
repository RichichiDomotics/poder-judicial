/**
* Nombre del Programa : ConsultarNotasPorExpedienteService.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08 07 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para Consultar Notas de un expediente
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
import mx.gob.segob.nsjp.dto.expediente.NotaExpedienteDTO;

/**
 * Contrato del servicio para Consultar notas de un expediente
 * @version 1.0
 * @author rgama
 *
 */
public interface ConsultarNotasPorExpedienteService {
	
	/**
     * Permite consultar las notas asociadas a un expediente
	 * @param expedienteDTO.NumeroExpedienteId: Representa el numero del expediente 
     * @return List<NotaExpedienteDTO>
     */
    public List<NotaExpedienteDTO> consultarNotas(ExpedienteDTO expediente) throws NSJPNegocioException;

    /**
     * Servicio para consultar Nota por Id
     * 
     * @param idNota
     * @return
     * @throws NSJPNegocioException
     */
    NotaExpedienteDTO consultarNotaPorId(Long idNota)throws NSJPNegocioException ;

}
