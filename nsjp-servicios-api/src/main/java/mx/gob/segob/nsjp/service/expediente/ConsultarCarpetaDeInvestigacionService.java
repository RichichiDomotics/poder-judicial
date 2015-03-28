/**
* Nombre del Programa : ConsultarCarpetaDeInvestigacionService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/07/2011
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
 * Servicio que permite consultar la Carpeta de investigacion en Procuraduria.
 * Posteriormente se manda, la carpeta, a defensoria.
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface ConsultarCarpetaDeInvestigacionService {
    
	/**
	 * 
	 * 
	 * @param numeroGeneralCaso
	 * @param folioSolicitud
	 * @return
	 * @throws NSJPNegocioException
	 */
	ExpedienteDTO consultarCarpetaDeInvestigacion(String numeroGeneralCaso)
			throws NSJPNegocioException;
}
