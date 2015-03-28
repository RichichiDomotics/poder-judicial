/**
* Nombre del Programa : ProgramaDAO.java
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
package mx.gob.segob.nsjp.dao.programa;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatPrograma;
import mx.gob.segob.nsjp.model.CentroDetencion;

/**
 * 
 * Acceso a la información de Programas
 * 
 * @version 1.0
 * @author AntonioBV
 *
 */

public interface CatProgramaDAO extends GenericDao<CatPrograma, Long> {
	/**
	 * M&eacute;todo que consulta todos los programas
	 * @return Lista de CatPrograma
	 * @throws NSJPNegocioException
	 */
	public List<CatPrograma> consultarProgramas()throws NSJPNegocioException;
		
	/**
	 * M&eacute;todo que consulta un programa por id
	 * @return CatPrograma
	 * @throws NSJPNegocioException
	 */
	public CatPrograma consultarProgramaPorId(CatPrograma catPrograma) throws NSJPNegocioException;	
	
	/**
	 * M&eacute;todo que elimina un programa por id
	 * @return boolean true si lo elimina, false si no
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarProgramaPorId(CatPrograma catPrograma) throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que consulta todos los programas que se 
	 * encuentran asociados a un centro de detencion.
	 * @return Lista de CatPrograma
	 * @throws NSJPNegocioException
	 */
	public List<CatPrograma> consultarProgramasPorCentroDetencion(CentroDetencion centroDetencion)throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que consulta todos los programas que se 
	 * encuentran asociados a un centro de detencion, que no 
	 * se encuentren previamente asignados y cuya fecha de 
	 * inicio sea mayor a la fecha actual.
	 * @return Lista de CatPrograma
	 * @throws NSJPNegocioException
	 */
	public List<CatPrograma> consultarProgramasDisponibles(CentroDetencion centroDetencion, List<CatPrograma> programasAsignados, 
														   Date fechaActual) throws NSJPNegocioException;
}
