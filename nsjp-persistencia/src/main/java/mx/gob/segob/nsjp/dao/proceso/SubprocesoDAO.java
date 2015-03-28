/**
* Nombre del Programa : SubprocesoDAO.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/01/2012
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
package mx.gob.segob.nsjp.dao.proceso;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Proceso;
import mx.gob.segob.nsjp.model.Subproceso;

/**
 * 
 * Acceso a la información de Subprocesos
 * 
 * @version 1.0
 * @author AntonioBV
 *
 */

public interface SubprocesoDAO extends GenericDao<Subproceso, Long> {

	/**
	 * M&eacute;todo que consulta todos los subprocesos
	 * @return Lista de Subprocesos
	 * @throws NSJPNegocioException
	 */
	
	public List<Subproceso> consultarSubprocesos()throws NSJPNegocioException;

	/**
	 * M&eacute;todo que consulta todos los subprocesos de un Proceso
	 * @param proceso: id del proceso  
	 * @return Lista de Subprocesos
	 * @throws NSJPNegocioException
	 */
	
	public List<Subproceso> consultarSubprocesosPorProceso(Proceso proceso)throws NSJPNegocioException;
}
