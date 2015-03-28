/**
* Nombre del Programa : ConsultarDelitoService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para el servicio para la consulta de los delitos registrados
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
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Contrato para el servicio para la consulta de los delitos registrados.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ConsultarDelitoService {

	/**
	 * Consulta los delitos registrados para el expediente enviado con parametro
	 * @param expedienteDTO
	 * @return lista de los delitos registrados para el expediente
	 * @throws NSJPNegocioException
	 */
	public List<DelitoDTO> consultarDelitoExpediente (ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
	
	/**
	 * Consulta si la media atimetica de los delitos que pertenecen a un expediente es mayor que la
	 * establecida y devuelve true si es menor, false en caso contrario.
	 * Devuelve null si es que la lista de delitos es cero o si la lista es nula 
	 * 
	 * @param inputExpDto
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Boolean consultarMediaAritmeticaDelitosExpediente (ExpedienteDTO inputExpDto) throws NSJPNegocioException;
}
