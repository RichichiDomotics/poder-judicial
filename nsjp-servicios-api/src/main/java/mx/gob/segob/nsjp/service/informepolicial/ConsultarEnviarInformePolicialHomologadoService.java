/**
* Nombre del Programa : ConsultarEnviarInformePolicialHomologadoService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/09/2011
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
package mx.gob.segob.nsjp.service.informepolicial;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;

/**
 * Interfaz para el servicio que permite Consultar el IPH por Folio
 * y posteriormente invocar al WS para ser enviado PGJ
 *  
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface ConsultarEnviarInformePolicialHomologadoService {

	/**
	 * Servicio que se encarga de consultar un IPH por folio e invocar
	 * al WS para ser enviado a PGJ
	 * 
	 * @param informePolicialHomologadoDTO
 	 * @param idAgencia Agencia a la cual se enviara el IPH
	 * @return
	 * @throws NSJPNegocioException
	 */
	InformePolicialHomologadoDTO consultarEnviarInformePolicialHomologado( Long folioIPH, Long idAgencia) throws NSJPNegocioException;
	
}
