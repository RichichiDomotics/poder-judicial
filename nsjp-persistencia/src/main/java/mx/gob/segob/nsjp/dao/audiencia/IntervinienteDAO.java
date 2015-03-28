/**
* Nombre del Programa : IntervinienteDAO.java
* Autor                            : LuisMG
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/10/2011
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
package mx.gob.segob.nsjp.dao.audiencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;

import mx.gob.segob.nsjp.model.Interviniente;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author LuisMG
 *
 */
public interface IntervinienteDAO extends GenericDao<Interviniente, Long> {
	
	List<Interviniente> consultarIntervinientePorAudiencia(Long idAudiencia) throws NSJPNegocioException;

}
