/**
* Nombre del Programa : PeriodoAcumulacionPuntosDAO.java
* Autor                            : EdgarTE
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Mar 2012
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
import mx.gob.segob.nsjp.model.PeriodoAcumulacionPuntos;
import mx.gob.segob.nsjp.model.Sentencia;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
@Repository
public interface PeriodoAcumulacionPuntosDAO extends GenericDao<PeriodoAcumulacionPuntos, Long> {

	/**
	 * @param periodoAcumulacionPuntos
	 * @return
	 */
	public PeriodoAcumulacionPuntos consultarPeriodoAcumulacionPuntosPorId(PeriodoAcumulacionPuntos periodoAcumulacionPuntos);
	
	/**
	 * @param sentencia
	 * @return
	 */
	public List<PeriodoAcumulacionPuntos> consultarPeriodosAcumulacionPuntosPorSentencia(Sentencia sentencia) throws NSJPNegocioException;
	
}
