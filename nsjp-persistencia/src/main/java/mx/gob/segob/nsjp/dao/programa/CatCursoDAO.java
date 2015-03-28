/**
* Nombre del Programa : CatCursoDAO.java
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatCurso;

import org.springframework.stereotype.Repository;

/**
 * 
 * Acceso a la información de CatCurso
 * 
 * @version 1.0
 * @author AntonioBV
 *
 */
@Repository
public interface CatCursoDAO extends GenericDao<CatCurso, Long> {
	
	public final static String algo = "";
	
	/**
	 * M&eacute;todo que consulta todos las categor&iacute;as de un curso
	 * @return Lista de CatCurso
	 * @throws NSJPNegocioException
	 */
	public List<CatCurso> consultarCatCurso()throws NSJPNegocioException;
		
	/**
	 * M&eacute;todo que consulta un curso por id
	 * @return CatCurso
	 * @throws NSJPNegocioException
	 */
	public CatCurso consultarCatCursoPorId(CatCurso catCurso)throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que elimina un curso por id
	 * @return boolean true si lo elimina, false si no
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarCursoPorId(CatCurso catCurso) throws NSJPNegocioException;	
	
}
