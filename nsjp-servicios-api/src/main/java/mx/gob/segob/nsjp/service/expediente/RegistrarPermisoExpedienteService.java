/**
* Nombre del Programa : RegistrarPermisoExpedienteService.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 4 Oct 2011
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

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * @author cesar
 *
 */
public interface RegistrarPermisoExpedienteService {
	
	/**
	 * 
	 * @param claveFuncionario
	 * @param numExpId
	 * @throws NSJPNegocioException
	 */
	public void registrarPermisoExpedienteFuncionario(Long claveFuncionario, Long numExpId, Date fechaLimite, Boolean permiso) throws NSJPNegocioException;

	/**
	 * 
	 * @param claveFuncionario
	 * @param numExpId
	 * @throws NSJPNegocioException
	 */
	public void eliminarPermisoExpedienteFuncionario(Long claveFuncionario,
			Long numExpId) throws NSJPNegocioException;

}
