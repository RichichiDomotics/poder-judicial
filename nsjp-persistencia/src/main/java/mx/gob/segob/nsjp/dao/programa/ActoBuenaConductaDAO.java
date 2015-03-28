/**
* Nombre del Programa : ActoBuenaConductaDAO.java
* Autor                            : EdgarTE
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19/03/2012
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

import org.springframework.stereotype.Repository;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.ActoBuenaConducta;
import mx.gob.segob.nsjp.model.Sentencia;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
@Repository
public interface ActoBuenaConductaDAO extends GenericDao<ActoBuenaConducta, Long> {
	
	/**
	 * @param actoBuenaConducta
	 * @return
	 */
	public ActoBuenaConducta consultarActoBuenaConductaPorId(ActoBuenaConducta actoBuenaConducta);
	
	/**
	 * @param sentencia
	 * @return
	 */
	public List<ActoBuenaConducta> consultarActosBuenaConductaPorSentencia (Sentencia sentencia) throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que lleva a cabo la consulta de todos los 
	 * actos de buena conducta que no han sido acumulados en 
	 * un per&iacute;odo. 
	 * @param sentencia - La sentencia a partir de la cual se
	 * 			consultan los actos de buena conducta.
	 * @return <List>ActoBuenaConducta - Los actos de buena 
	 * 			conducta de la sentencia que no han sido acumulados.
	 */
	public List<ActoBuenaConducta> consultarActosBuenaConductaSinAcumular (Sentencia sentencia) throws NSJPNegocioException;
	
}
