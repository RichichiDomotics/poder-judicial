/**
* Nombre del Programa : ObjetoDAO.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Interfaz del objeto de acceso a datos para los tipos de elemento "Objeto" de un Expediente
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
package mx.gob.segob.nsjp.dao.objeto;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Objeto;

/**
 * Interfaz del objeto de acceso a datos para los tipos de elemento "Objeto" de un Expediente
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface ObjetoDAO extends GenericDao<Objeto, Long>{
	/**
	 * Consulta los tipos de elemento "Objetos" asociados a un expediente
	 * @param expedienteId Expediente a buscar
	 * @return Lista de los objetos encontrados para el expediente
	 */
	List<Objeto> consultarObjetosByExpediente(Long expedienteId);
	
	/**
	 * Consulta los elementos "Objetos" asociados a un expediente por tipo de Objeto
	 * @param expedienteId
	 * @param idTipoObjeto
	 * @return
	 */
	public List<Objeto> consultarObjetosPorTipoByExpediente(Long expedienteId, Long idTipoObjeto);

	/**
	 * Consulta los objetos que pertenencen al <code>detenido</code>
	 * @param detenido Involucrado a quien pertenecen los objetos consultados
	 * @return Lista de objetos que pertenecen al detenido
	 * @throws NSJPNegocioException
	 */
	List<Objeto> consultarInventarioPertenenciasDetenido(Long elementoId);
	
}
