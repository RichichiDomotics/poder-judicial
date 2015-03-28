/**
* Nombre del Programa : EliminarAsociacionExpedienteUsuarioService.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Servicio para eliminar asociacion expediente usuario
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
 * Servicio para eliminar asociacion expediente usuario 
 * @version 1.0
 * @author rgama
 *
 */
public interface EliminarAsociacionExpedienteUsuarioService {
	
	/**
	 * Servicio que permite Eliminar la Asociacion del Expediente Usuario, 
	 * siempre y cuando exista dicha relacion 
	 * @param expedienteDTO.numeroExpedienteId Representa el id del Numero del expediente
	 * @param idFuncionario: Representa el id del Funcionario
	 * @throws NSJPNegocioException
	 */
	public void eliminarAsociacionExpedienteUsuario(ExpedienteDTO expedienteDTO, Long idFuncionario)
	throws NSJPNegocioException;
}
