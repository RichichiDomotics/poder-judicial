/**
* Nombre del Programa  : RegistrarBitacoraService.java
* Autor                : CuauhtemocPS
* Compania             : Ultrasist
* Proyecto             : NSJP                    Fecha: 14/10/2011
* Marca de cambio      : N/A
* Descripcion General  : registra el movimiento del expediente en bitacora
* Programa Dependiente :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion   :N/A
* Dias de ejecucion    :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                :N/A
* Compania             :N/A
* Proyecto             :N/A                      Fecha: N/A
* Modificacion         :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.bitacora;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.model.RegistroBitacora;

public interface RegistrarBitacoraService {
	
	/**
	 * Registra los movimientos del expediente en una bitacora
	 * @param registroBitacora
	 * @throws NSJPNegocioException
	 */
	
	void registrarMovimientoDeExpedienteEnBitacora(RegistroBitacora registroBitacora)throws NSJPNegocioException;

}
