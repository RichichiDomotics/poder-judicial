/**
 * Nombre del Programa : AlmacenDAO.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 29-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dao.almacen;

import java.util.List;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Caso;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface AlmacenDAO extends
        GenericDao<Almacen, Long>{

    /**
     * 
     * @param idTipoAlmacen
     * @param estatus
     * @param caso
     * @return
     */
    List<Almacen> consultarAlmacenesPorTipo(Long idTipoAlmacen, Boolean estatus, Caso caso);

    boolean validarCadenaCustodiaEnAlmacen(CadenaDeCustodia cadenaDeCustodia, Almacen almacen);

    /**
     * 
     * @param numeroExpedienteId
     * @return
     */
	Almacen obtenerAlmacenByNumExpediente(Long numeroExpedienteId);

	/**
	 * Regresa la información relacionada a un almacen recibiendo un ID
	 * @param almacenId
	 * @return
	 */
	Almacen consultarDetalleAlmacenPorId (Long almacenId);
	
	/**
	 * 
	 * @param estatus
	 * @return
	 */
	List<Almacen> consultarAlmacenesExpedienteByEstatus(Boolean estatus, Boolean tipo);
}
