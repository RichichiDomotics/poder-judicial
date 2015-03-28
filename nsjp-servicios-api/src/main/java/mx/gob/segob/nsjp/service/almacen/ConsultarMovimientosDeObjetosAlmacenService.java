/**
* Nombre del Programa : ConsultarMovimientosDeObjetosAlmacenService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para consultar los Movimientos de Objetos del Almacen
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
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;

/**
 * Contrato del servicio para consultar los Movimientos de Objetos del Almacen
 * Por medio de la evidencias asociadas a la Cadena Custodia, que a su vez, esta
 * relacionada a un Expediente (Número de Expediente).
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface ConsultarMovimientosDeObjetosAlmacenService {

	/**
	 * Servicio que permite consultar los movimientos de Objetos 
	 * del almacen. 
	 * Se consultar las evidencias asociadas a la Cadena Custodia, que asu vez, esta
	 * relacionada a un Expediente (Número de Expediente).
	 * 
	 * @param numeroExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<EvidenciaDTO> consultarEvidenciasAsociadasCadenaCustodiaPorNumeroExpediente( String numeroExpediente)  throws NSJPNegocioException;
	
}
