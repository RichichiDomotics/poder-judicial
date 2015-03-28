/**
 * Nombre del Programa : AsentamientoDAO.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato para los metodos de acceso a datos de Aentamiento
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
package mx.gob.segob.nsjp.dao.domicilio;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Asentamiento;

/**
 * Contrato para los metodos de acceso a datos de Aentamiento.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public interface AsentamientoDAO extends GenericDao<Asentamiento, Long> {
    /**
     * 
     * @param idTipoAsentamiento
     * @return
     */
    List<Asentamiento> consultarPorTipo(Long idTipoAsentamiento);

    /**
     * 
     * @param idTipoAsentamiento
     * @return
     */
    List<Asentamiento> consultar(Long idMpio, Long idCiudad,
            Long idTipoAsentamiento);

    /**
     * 
     * @param idAsentamiento
     * @return
     */
    String obtenerCP(Long idAsentamiento);
    /**
     * 
     * @param cp
     * @return
     */
    List<Asentamiento> consultarPorCP(String cp);
}
