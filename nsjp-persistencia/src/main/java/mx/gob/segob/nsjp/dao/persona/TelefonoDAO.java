/**
* Nombre del Programa : TelefonoDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Contarato para los metodos de acceso a datos de la entidad Telefono
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
package mx.gob.segob.nsjp.dao.persona;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Telefono;

/**
 * Contarato para los metodos de acceso a datos de la entidad Telefono.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface TelefonoDAO extends GenericDao<Telefono, Long> {

	/**
	 * 
	 * @param elementoId
	 * @return
	 */
	List<Telefono> consultarTelefonosByPersona(Long elementoId);
	
	/**
	 * 
	 * @param claveFuncionario
	 * @return
	 */
	List<Telefono> consultarTelefonosByFuncionario(Long claveFuncionario);

}
