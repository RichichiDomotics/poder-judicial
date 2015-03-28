/**
* Nombre del Programa : LugarDelegate.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del Delegate para los servicios relacionados con Lugar
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
package mx.gob.segob.nsjp.delegate.lugar;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;

/**
 * Contrato del Delegate para los servicios relacionados con Lugar.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface LugarDelegate {
	
	/**
	 * Ingresa uno de los diferentes tipos de lugar.
	 * @param lugarDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public LugarDTO ingresarLugar (LugarDTO lugarDTO) throws NSJPNegocioException; 

	/**
	 * Operación que permite completar por un código postal un asentamiento
	 * @param codigoPostal
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<AsentamientoDTO> completarAsentamientoXCodigoPostal(String codigoPostal)throws NSJPNegocioException;
	/**
	 * Permite obtener el codigo postal en base al id de un asentamiento
	 * @param idAsentamiento
	 * @return
	 * @throws NSJPNegocioException
	 */
	public String obtenerCodigoPostalXIdAsentamiento(Long idAsentamiento) throws NSJPNegocioException;
	/**
	 * Obtiene la informacion de un asentmiento de acuerdo al id enviado como parametro
	 * @param idAsentamiento
	 * @return
	 * @throws NSJPNegocioException
	 */
	public AsentamientoDTO obtenerAentamientoPrId (Long idAsentamiento) throws NSJPNegocioException;
}
