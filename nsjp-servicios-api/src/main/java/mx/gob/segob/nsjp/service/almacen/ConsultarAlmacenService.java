/**
* Nombre del Programa : ConsultarAlmacenService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar las consultar de Almacen
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
package mx.gob.segob.nsjp.service.almacen;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Contrato del servicio para realizar las consultar de Almacen.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ConsultarAlmacenService {

	 /**
     * Recupe la informacion del Almacen en el que se encuentra el expediente enviado como parametro
     * @author cesarAgustin
     * @param expedienteDTO
     * 			<li>numeroExpedienteId<li> Identificador de expediente
     * @return Almacen obtenido
     * @throws NSJPNegocioException
     */
	public AlmacenDTO obtenerAlmacenDelExpediente(ExpedienteDTO expedienteDTO) throws NSJPNegocioException;

	 /**
     * Obtiene los almacenes para expediente que se encuentren en el estatus requerido
     * @author cesarAgustin
     * @param estatus
     * @return Lista de almacenes
     * @throws NSJPNegocioException
     */
	public List<AlmacenDTO> consultarAlmacenesExpedientePorEstatus(
			Boolean estatus, Boolean tipo) throws NSJPNegocioException;
	
	public AlmacenDTO consultarDetalleAlmacenPorId (Long almacenId) throws NSJPNegocioException;

	
}
