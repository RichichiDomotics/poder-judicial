/**
* Nombre del Programa : CatCategoriaRelacionDAO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/07/2011
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
package mx.gob.segob.nsjp.dao.relacion;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatCategoriaRelacion;

/**
 * Contrato para los metodos de acceso a datos de CatCategoriaRelacion.
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface CatCategoriaRelacionDAO extends GenericDao<CatCategoriaRelacion, Long> {

	/**
     * Consulta todas las categorias de relación que sean o no documento
     * @param esDocumento: Boolean que indica si se quiere que sea o no documento
     * @return
     */
	List<CatCategoriaRelacion> consultarCatCategoriaRelacionSiDocumento(
			Boolean esDocumento);

}
