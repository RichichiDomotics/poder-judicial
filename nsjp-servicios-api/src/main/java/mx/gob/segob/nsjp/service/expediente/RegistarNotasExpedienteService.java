/**
* Nombre del Programa : ActualizarNotasExpedienteService.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del Servicio para la actualizacion las notas de un Expediente
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
 * Contrato del Servicio para la actualizacion las notas de un Expediente
 * @version 1.0
 * @author rgama
 *
 */
public interface RegistarNotasExpedienteService {
	
	
	/**
	 * Metodo que permite registrar/actualizar las notas asociadas a un Expediente
	 * @param expedienteDTO.NumeroExpedienteId: Representa el numero del expediente a actualizar
	 * @param notas: Son las NotasExpedienteDTO que se desean guardar/actualizar.
	 * 	Si se manda el campo NotaExpedienteDTO.idNota entonces se acutualiza la nota, 
	 *  en caso contrario se crea la nota  
	 * @throws NSJPNegocioException
	 */
	public List<NotaExpedienteDTO> registrarActualizarNotasExpediente(List<NotaExpedienteDTO> notas,  ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
	
	
	

}
