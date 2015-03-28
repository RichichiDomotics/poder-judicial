/**
* Nombre del Programa : AsignacionProgramaDAO.java
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.AsignacionPrograma;

import org.springframework.stereotype.Repository;

/**
 * 
 * Acceso a la información de AsignacionPrograma
 * 
 * @version 1.0
 * @author AntonioBV
 *
 */
@Repository
public interface AsignacionProgramaDAO extends GenericDao<AsignacionPrograma, Long> {
		
	/**
	 * M&eacute;todo que consulta una AsignacionPrograma por id
	 * @return AsignacionPrograma
	 * @throws NSJPNegocioException
	 */
	public AsignacionPrograma consultarAsignacionProgramaPorId(AsignacionPrograma asignacionPrograma)throws NSJPNegocioException;
		
}
