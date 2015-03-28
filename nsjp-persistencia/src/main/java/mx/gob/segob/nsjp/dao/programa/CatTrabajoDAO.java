/**
* Nombre del Programa : CatTrabajoDAO.java
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

import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatTrabajo;

/**
 * 
 * Acceso a la información de CatTrabajo
 * 
 * @version 1.0
 * @author AntonioBV
 *
 */
@Repository
public interface CatTrabajoDAO extends GenericDao<CatTrabajo, Long> {
	/**
	 * M&eacute;todo que consulta todos los trabajos.
	 * @return Lista de CatTrabajo
	 * @throws NSJPNegocioException
	 */
	public List<CatTrabajo> consultarCatTrabajo()throws NSJPNegocioException;
		
	/**
	 * M&eacute;todo que consulta un trabajo por id
	 * @return CatTrabajo
	 * @throws NSJPNegocioException
	 */
	public CatTrabajo consultarCatTrabajoPorId(CatTrabajo catTrabajo)throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que elimina un trabajo por id
	 * @return boolean true si lo elimina, false si no
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarTrabajoPorId(CatTrabajo catTrabajo) throws NSJPNegocioException;	
	
}
