/**
* Nombre del Programa : NivelAcademicoDAO.java
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
import mx.gob.segob.nsjp.model.NivelAcademico;

/**
 * 
 * Acceso a la información de NivelAcademico
 * 
 * @version 1.0
 * @author AntonioBV
 *
 */

public interface NivelAcademicoDAO extends GenericDao<NivelAcademico, Long> {
	/**
	 * M&eacute;todo que consulta todos los niveles acad&eacute;micos
	 * @return Lista de NivelAcademico
	 * @throws NSJPNegocioException
	 */
	public List<NivelAcademico> consultarNivelAcademico()throws NSJPNegocioException;
		
}
