/**
* Nombre del Programa : BitacoraMovimientoDAO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de DAO para BitacoraMovimiento.
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
package mx.gob.segob.nsjp.dao.bitacora;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.BitacoraMovimiento;

/**
 * Contrato de DAO para BitacoraMovimiento.
 * 
 * @version 1.0
 * @author GustavoBP
 */
public interface BitacoraMovimientoDAO extends GenericDao<BitacoraMovimiento, Long> {

	/**
	 * Consulta los registros de Bitacora Movimiento por cualquiera o
	 * combinación de los siguientes filtros:
	 * 
	 * @param numeroExpediente
	 * @param idCategoriaElemento  de acuerdo al catálogo CategoríaElemento
	 * @return
	 */
	List<BitacoraMovimiento> consultarBitacoraMovimientoPorFiltro(
			String numeroExpediente, Long idCategoriaElemento );
}
