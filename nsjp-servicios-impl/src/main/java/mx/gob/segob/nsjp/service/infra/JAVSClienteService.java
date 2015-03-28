/**
* Nombre del Programa : JAVSClienteService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 8 Nov 2011
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
package mx.gob.segob.nsjp.service.infra;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Cliente para conectarse a los Web Services expuestos en el servidor donde reside JAVS.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface JAVSClienteService {

	/**
	 * Se conecta al Servidor del JAVS para agendar la audiencia.
	 * Para hacer la validación se manda el Usuario al que pertence el Funcionario 
	 * asociado al numero de Expediente de la Audiencia.
	 *  
	 * @param idAudiencia
	 * @param claveUsuario
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long agendarAudiencia(Long idAudiencia, String claveUsuario) throws NSJPNegocioException;
	
	/**
	 * Se conecta al Servidor del JAVS para consultar la audiencia.
	 * Para hacer la validación se manda el Usuario al que pertence el Funcionario 
	 * asociado al numero de Expediente de la Audiencia.
	 *  
	 * @param idAudiencia
	 * @param claveUsuario
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long consultarAudiencia(Long idAudiencia, String claveUsuario) throws NSJPNegocioException;
	
	
	/**
	 * Se conecta al Servidor del JAVS para eliminar la audiencia.
	 * Para hacer la validación se manda el Usuario al que pertence el Funcionario 
	 * asociado al numero de Expediente de la Audiencia.
	 *  
	 * @param idAudiencia
	 * @param claveUsuario
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long eliminarAudiencia(Long idAudiencia, String claveUsuario) throws NSJPNegocioException;
	
	/**
	 * Se conecta al Servidor del JAVS para consultar el estado de una audiencia.
	 * Para hacer la validación se manda el Usuario al que pertence el Funcionario 
	 * asociado al numero de Expediente de la Audiencia.
	 *  
	 * @param idAudiencia
	 * @param claveUsuario
	 * @return
	 * @throws NSJPNegocioException
	 */

	Long estadoAudiencia(Long idAudiencia, String claveUsuario) throws NSJPNegocioException;
}
