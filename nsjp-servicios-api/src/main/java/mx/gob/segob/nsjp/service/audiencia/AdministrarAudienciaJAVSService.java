/**
* Nombre del Programa : AdministrarAudienciaJAVSService.java
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
package mx.gob.segob.nsjp.service.audiencia;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Implementación de los servicios que permiten consumir el Cliente 
 * que se conecta a los WS de .net alojados en el servidor de JAVS.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface AdministrarAudienciaJAVSService {

	/**
	 * Se conecta al Servidor del JAVS para agendar la audiencia.
	 *  
	 * @param audienciaId
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long agendarAudiencia(Long audienciaId) throws NSJPNegocioException;
	
	/**
	 * Se conecta al Servidor del JAVS para reagendar la audiencia.
	 *  
	 * @param audienciaId
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long reagendarAudiencia(Long audienciaId) throws NSJPNegocioException;
	
	/**
	 * Consulta si una audiencia se agendo en JAVS.
	 *  
	 * @param audienciaId
	 * @return
	 * @throws NSJPNegocioException
	 */	
	Boolean esJAVS(Long audienciaId) throws NSJPNegocioException;
	
	/**
	 * Se conecta al Servidor del JAVS para consultar la audiencia.
	 *  
	 * @param audienciaId
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long consultarAudiencia(Long audienciaId) throws NSJPNegocioException;

	/**
	 * Se conecta al Servidor del JAVS para consultar el estado de la audiencia.
	 *  
	 * @param audienciaId
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long consultarEstadoAudiencia(Long audienciaId) throws NSJPNegocioException;
	/**
	 * Se conecta al Servidor del JAVS para eliminar la audiencia.
	 *  
	 * @param audienciaId
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long eliminarAudiencia(Long audienciaId) throws NSJPNegocioException;
	
	
	/**
	 * Permite cambiar el estatus de una audiencia a CANCELADA
	 * @param audienciaId
	 * @return 
	 * @throws NSJPNegocioException 
	 */
	Long cancelarAudiencia(Long audienciaId) throws NSJPNegocioException;
	
	/**
	 * Permite cambiar el estatus de una audiencia a CANCELADA
	 * @param audienciaId
	 * @return 
	 * @throws NSJPNegocioException 
	 */
	Long cancelarAudienciaSistema(Long audienciaId) throws NSJPNegocioException;
	
	/**
	 * Permite eliminar una audiencia en JAVS
	 * @param audienciaId
	 * @return 
	 * @throws NSJPNegocioException 
	 */	
	boolean cancelarAudienciaJAVS(Long audienciaId) throws NSJPNegocioException;
}
