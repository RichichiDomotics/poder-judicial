/**
* Nombre del Programa : CatTipoNivelAcademicoDAO.java
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
package mx.gob.segob.nsjp.dao.niveles;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatTipoNivelAcademico;

/**
 * 
 * Acceso a la información de CatTipoNivelAcademico
 * 
 * @version 1.0
 * @author AntonioBV
 *
 */

public interface CatTipoNivelAcademicoDAO extends GenericDao<CatTipoNivelAcademico, Long> {
	/**
	 * M&eacute;todo que consulta todos los tipos de nivel acad&eacute;mico
	 * @return Lista de CatTipoNivelAcademico
	 * @throws NSJPNegocioException
	 */
	public List<CatTipoNivelAcademico> consultarCatTipoNivelAcademico()throws NSJPNegocioException;
		
}
