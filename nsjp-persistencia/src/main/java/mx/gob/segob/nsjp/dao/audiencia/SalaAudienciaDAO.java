/**
 * Nombre del Programa : SalaAudienciaDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 10 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato para el acceso a datos de las sala de audiencia
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
package mx.gob.segob.nsjp.dao.audiencia;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.SalaAudiencia;

/**
 * Contrato para el acceso a datos de las sala de audiencia.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface SalaAudienciaDAO extends GenericDao<SalaAudiencia, Long> {
    /**
     * Verifica que haya disponibilidad de una sala par auna dia traves de la
     * sumatoria de las salas
     * 
     * @param salaId
     * @param dia
     * @return
     */
    boolean existeDisponibilidad(Long salaId, Date dia, Long limiteOcupacion);

    /**
     * Recupera las horas ocupadas de una sala.
     * 
     * @param salaId
     * @param dia
     * @return
     */
    List<Audiencia> consultarHoras(Long salaId, Date dia);
    /**
     * 
     * @return
     */
    List<SalaAudiencia> consultarSalasMinimo();
    
    Long consultarNumeroSalas();
   /**
    * Busca salas audiencia por nombre, domicilio y ubicacion de la sala
    * @param filtro DTO con los criterios de búsqueda
    * @return Lista de salas encontradas
    */
    List<SalaAudiencia> consultarSalasPorFiltro(SalaAudiencia filtro);
    
    List<SalaAudiencia> consultarNombresSalas(Long catDiscriminante);

    /**
     * Obtiene todos los registros de la entidad SalaAudiencia
     * @author CesarAgustin
     * @return Lista de entidad SalaAudiencia
     */
	List<SalaAudiencia> consultarTodos();
}
