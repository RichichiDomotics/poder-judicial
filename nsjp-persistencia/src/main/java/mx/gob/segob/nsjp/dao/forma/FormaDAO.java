/**
* Nombre del Programa : FormaDAO.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/05/2011
* Marca de cambio        : N/A
* Descripcion General    : Definición del objeto de acceso a datos para el catálogo de formas
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
package mx.gob.segob.nsjp.dao.forma;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Forma;

/**
 * Definición del objeto de acceso a datos para el catálogo de formas
 * @author Emigdio Hernández
 *
 */
public interface FormaDAO extends GenericDao<Forma, Long >{

	/**
	 * Operación que realiza la funcionalidad de consultar las plantillas 
	 * registradas en el sistema de acuerdo a  un criterio de tipo de documento.
	 * 
	 * @param tipoDocumento
	 * @return Devuelve un listado de plantillas, en caso contrario, regresa null.
	 */
	List<Forma> consultarFormaPlantilla(String tipoDocumento);
	/**
	 * Consulta el inventario de formas disponibles en el sistema filtradas
	 * por cierto tipo de forma, si el tipo de forma enviado es 0 entonces
	 * no filtra los elementos
	 * @param tipoFormaId el tipo de forma a filtrar
	 * @return Listado de formas
	 */
	public List<Forma> consultarPlantillaPorTipo(Long tipoForma);
	/**
	 * Consulta el inventario de formas disponibles en el sistema
	 * @return Listado de formas
	 */
	List<Forma> consultarFormasDisponibles();
	/**
	 * Consulta una forma en base a su nombre
	 * @param nombreForma Nombre de la forma a buscar
	 * @return Forma encontrada, null si no se encuentra
	 */
	Forma consultarFormaPorNombre(String nombreForma);

}
