/**
* Nombre del Programa 		: InventarioPertenenciaDAO.java
* Autor                     : EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 								Fecha: 28 Mar 2012
* Marca de cambio        	: N/A
* Descripcion General    	: Objeto de acceso a datos para InventarioPertenencia
* Programa Dependiente  	: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion        : N/A
* Dias de ejecucion 		: N/A 								Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 								Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.dao.reinsercion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO;
import mx.gob.segob.nsjp.model.InventarioPertenencia;
import mx.gob.segob.nsjp.model.Pertenencia;

/**
 * Objeto de acceso a datos para InventarioPertenencia
 * @version 1.0
 * @author EdgarTE
 *
 */
public interface InventarioPertenenciaDAO extends GenericDao<InventarioPertenencia, Long> {
	
	/**
	 * M&eacute;todo que lleva a cabo la consulta de todas las pertenencias
	 * asociadas a un inventario.
	 * 
	 * @param inventarioPertenenciaDTO
	 *            - El inventario del cual se van a obtener las pertenencias.
	 * @return List<PertenenciaDTO> - Lista con las pertenencias asociadas al
	 *         inventario.
	 */
	public List<Pertenencia> consultarPertenenciasPorInventario(
			InventarioPertenenciaDTO inventarioPertenenciaDTO)
			throws NSJPNegocioException;

}
