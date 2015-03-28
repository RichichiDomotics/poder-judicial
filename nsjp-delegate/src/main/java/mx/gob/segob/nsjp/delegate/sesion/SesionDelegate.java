/**
* Nombre del Programa : SesionDelegate.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del delegate para los metodos de comunicacion de Sesion entre la vista y los servicios
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
package mx.gob.segob.nsjp.delegate.sesion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;
import mx.gob.segob.nsjp.dto.sesion.NotaEvolucionDTO;
import mx.gob.segob.nsjp.dto.sesion.SesionDTO;

/**
 * Contrato del delegate para los metodos de comunicacion de Sesion entre la vista y los servicios.
 * @version 1.0
 * @author rgama
 *
 */
public interface SesionDelegate {
	
	
	
	/**
	 * Permite guardar/actualizar la informacion de una EntrevistaInicial
	 * @param aoEntrevistaInicialDTO
	 * 	 Obligatorio <b>numeroExpediente.numeroExpedienteId</b>.
	 * @return EntrevistaInicialDTO
	 * @throws NSJPNegocioException
	 */
	EntrevistaInicialDTO guardarEntrevistaInicial(EntrevistaInicialDTO aoEntrevistaInicialDTO)
		throws NSJPNegocioException;
	
	/**
	 * Permite guardar/actualizar la informacion de una EntrevistaComplementaria
	 * @param aoEntrevistaComplementariaDTO
	 * 	 Obligatorio <b>numeroExpediente.numeroExpedienteId</b>.
	 * @return EntrevistaComplementariaDTO
	 * @throws NSJPNegocioException
	 */
	EntrevistaComplementariaDTO guardarEntrevistaComplementaria(EntrevistaComplementariaDTO aoEntrevistaComplementariaDTO)
		throws NSJPNegocioException;
	
	
	/**
	 * Permite guardar/actualizar la informacion de una NotaEvolucion
	 * @param aoNotaEvolucionDTO
	 * 	 Obligatorio <b>numeroExpediente.numeroExpedienteId</b>.
	 * @return NotaEvolucionDTO
	 * @throws NSJPNegocioException
	 */
	NotaEvolucionDTO guardarNotaEvolucion(NotaEvolucionDTO aoNotaEvolucionDTO)
		throws NSJPNegocioException;
	
	/**
	 * Consulta EntrevistaInicial por identificador
	 * @param aoEntrevistaInicialDTO
	 * 		Obligatorio <b>sesionId</b>.
	 * @return
	 * @throws NSJPNegocioException
	 */
    EntrevistaInicialDTO consultarEntrevistaInicialPorId(EntrevistaInicialDTO aoEntrevistaInicialDTO)
            throws NSJPNegocioException;
    
	/**
	 * Consulta EntrevistaComplementaria por identificador
	 * @param aoEntrevistaComplementariaDTO
	 * 		Obligatorio <b>sesionId</b>.
	 * @return
	 * @throws NSJPNegocioException
	 */
    EntrevistaComplementariaDTO consultarEntrevistaComplementariaPorId(EntrevistaComplementariaDTO aoEntrevistaComplementariaDTO)
            throws NSJPNegocioException;
    
	/**
	 * Consulta NotaEvolucion por identificador
	 * @param aoNotaEvolucionDTO
	 * 		Obligatorio <b>sesionId</b>.
	 * @return
	 * @throws NSJPNegocioException
	 */
    NotaEvolucionDTO consultarNotaEvolucionPorId(NotaEvolucionDTO aoNotaEvolucionDTO)
            throws NSJPNegocioException;      
    
	/**
	 * Consulta todas las sesiones asociados a un Numero de Expediente
	 * @param aoExpedienteDTO
	 * 		Obligatorio <b>numeroExpedienteId</b>.
	 * @return List<SesionDTO>
	 * @throws NSJPNegocioException
	 */
    List<SesionDTO> consultarSesionesPorIdNumeroExpediente(ExpedienteDTO aoExpedienteDTO)
            throws NSJPNegocioException;
    
}
