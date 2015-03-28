/**
* Nombre del Programa : EnviarDocumentoIncumplimientoMedidaService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 03/11/2011
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
package mx.gob.segob.nsjp.service.medida;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO;

/**
 * Interfaz del servicio que permite enviar el Documento de Incumplimiento de la Medida
 * a otra institución mediante un WebService.
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface EnviarDocumentoIncumplimientoMedidaService {

	/**
	 *  Servicio que permite enviar el Documento de Incumplimiento de Media a otra institución
	 *  
	 * @param documentoWSDTO
	 * @param numeroCausa
	 * @param numeroCarpetaEjecucion
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long enviarDocumentoDeIncumplimientoDeMedida(
			DocumentoWSDTO documentoWSDTO, String numeroCausa, String numeroCarpetaEjecucion)throws NSJPNegocioException;
}
