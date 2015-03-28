/**
* Nombre del Programa : EnviarDocumentoAInstitucionServerService.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 16/02/2012
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
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO;

public interface EnviarDocumentoAInstitucionServerService {
	
	/**
	 * Interfaz del servicio que permite enviar un Documento a cualquier otra institución mediante un WebService. 
	 * El servicio obtiene la lista de expedientes asociados al <<Caso>>, si la lista devuelve mas de un expediente
	 * el servicio no creara el documento. En caso contrario crea el documento expediente unico que se recupero. 
	 * @param documentoWSDTO
	 * @param numeroDeCaso
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long enviarDocumentoAInstitucion(
			DocumentoWSDTO documentoWSDTO, String numeroDeCaso)throws NSJPNegocioException;


	
}
