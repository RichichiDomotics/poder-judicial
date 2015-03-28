/**
* Nombre del Programa : SolicitarBeneficiosPreliberacionService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servivo para registrar una solicitud beneficios de preliberacion proveniente de defensoria o seguridad publica.
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
package mx.gob.segob.nsjp.service.solicitud;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudBeneficiosPreliberacionWSDTO;

/**
 * Contrato del servivo para registrar una solicitud beneficios de preliberacion proveniente de defensoria o seguridad publica.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface SolicitarBeneficiosPreliberacionService {

	/**
	 * Resgitra la solicitud beneficios preliberacion y la asocia a la carpeta de ejecucioncorrespondiente 
	 * @param solBeneficiosPreliberacion
	 * @author cesarAgustin
	 * @return
	 * @throws NSJPNegocioException
	 */
	SolicitudBeneficiosPreliberacionWSDTO registrarSolicitudBeneficiosPreliberacion (SolicitudBeneficiosPreliberacionWSDTO solBeneficiosPreliberacion)
																	throws NSJPNegocioException;
	
}
