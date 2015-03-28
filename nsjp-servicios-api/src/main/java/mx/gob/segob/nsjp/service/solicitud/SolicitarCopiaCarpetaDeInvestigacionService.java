/**
* Nombre del Programa : SolicitarCopiaCarpetaDeInvestigacion.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para registrar la solicitud de la copia de carpeta de investigacion
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
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO;

/**
 * Contrato del servicio para registrar la solicitud de la copia de carpeta de investigacion. 
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface SolicitarCopiaCarpetaDeInvestigacionService {

	/**
	 * 
	 * @author cesarAgustin
	 * @param solicitudAudienciaBasicoWSDTO
	 * @return Solicitud registrada
	 * @throws NSJPNegocioException
	 */
    SolicitudAudienciaBasicoWSDTO solicitarCopiaCarpetaDeInvestigacion(SolicitudAudienciaBasicoWSDTO solicitudAudienciaBasicoWSDTO,Long catDiscriminanteOrigen) throws NSJPNegocioException;
}
