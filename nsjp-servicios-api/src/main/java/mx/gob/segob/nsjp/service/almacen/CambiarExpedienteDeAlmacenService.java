/**
* Nombre del Programa : CambiarExpedienteDeAlmacenService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio pra realizar el cambio de almacen a un expediente
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Contrato del servicio pra realizar el cambio de almacen a un expediente.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface CambiarExpedienteDeAlmacenService {

	 /**
     * Realiza el cambio del expediente de un almacen a otro
     * @author cesarAgustin
     * @param almacenActual <li>identificadorAlmacen<li> Identificador del almacen actual
     * @param almacenNuevo <li>identificadorAlmacen<li> Identificador del almacen al que se pasara el expediente
     * @param expedienteDTO <li>numeroExpedienteId<li> Identificador del expediente que se movera
     * @throws NSJPNegocioException
     */
	public void cambiaExpedienteDeAlmacen(AlmacenDTO almacenActual,
			AlmacenDTO almacenNuevo, ExpedienteDTO expedienteDTO)throws NSJPNegocioException;
}
