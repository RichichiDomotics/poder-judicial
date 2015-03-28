/**
* Nombre del Programa : DefensorDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos de la entidad Defensor
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
package mx.gob.segob.nsjp.dao.funcionario;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Funcionario;

/**
 * Contrato de metodos de acceso a datos de la entidad Defensor.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface DefensorDAO extends GenericDao<Funcionario, Long> {
	
	/**
	 * Obtiene los defensores activos
	 * @return List<Funcionario> 
	 */
	
	List<Funcionario> consultarDefensoresActivos();
	
	/**
	 * Obtiene los defensores por tipo de defensoria
	 * @param defensa
	 * @return List<Funcionario>
	 */
	List<Funcionario> consultarDefensoresActivosPorTipoDefensa(Long defensa);
	
}
