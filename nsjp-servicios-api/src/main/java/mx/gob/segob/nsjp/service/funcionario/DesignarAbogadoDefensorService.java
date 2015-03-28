/**
* Nombre del Programa : DesignarAbogadoDefensor.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Servicio para designar un abogado defensor
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
package mx.gob.segob.nsjp.service.funcionario;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.AvisoDesignacionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;

/**
 * Servicio para designar un abogado defensor.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface DesignarAbogadoDefensorService {

	/**
	 * Asigna un abogado defensor a un expediente.
	 * @param solicitudDefensorDTO
	 * @throws NSJPNegocioException
	 */
	public SolicitudDefensorDTO designarAbogadoDefensor (SolicitudDefensorDTO solicitudDefensorDTO) 
				throws NSJPNegocioException;
	
	
	/**
	 * Actualiza la carga de trabajo de un abogado defensor
	 * @param solicitudDefensorDTO
	 * @throws NSJPNegocioException
	 */
	void actualizaCargaTrabajoAbogadoDefensor(SolicitudDefensorDTO solicitudDefensorDTO)
			throws NSJPNegocioException;


	
	
}
