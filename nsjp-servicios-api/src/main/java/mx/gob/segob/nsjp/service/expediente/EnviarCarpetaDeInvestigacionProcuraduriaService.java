/**
* Nombre del Programa : EnviarCarpetaDeInvestigacionProcuraduriaService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.expediente;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Servicio que invoca al WS para enviar la Carpeta de investigación de Procuraduría a Defensoría
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface EnviarCarpetaDeInvestigacionProcuraduriaService {

	 /**
     * Enviar Carpera de Procuraduria a Defensoria
     * 
     * Declaracion del servicio que invoca al WS para, de acuerdo a: 
     * numeroGeneralCaso. Para consultar los expedientes asociados al caso, en Procuraduria.
     * folioSolicitud: Folio de la solicitud a la cual se va a guardar la informacion del lado de Defensoria.
     * 
     * @param numeroGeneralCaso  Del caso asociado al expediente
     * @param folioSolicitud  De defensoria en donde se guradará la información solicitada del expediente de procuraduria
     * @return  expedienteDTO asociado a la consulta obtenida, incluyendo los involucrados y objetos del expediente 
     * @throws NSJPNegocioException
     */
    ExpedienteDTO enviarCarpetaDeInvestigacion(
    		String numeroGeneralCaso, String folioSolicitud)
            throws NSJPNegocioException;

}
