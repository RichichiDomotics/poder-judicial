/**
 * Nombre del Programa : EventoCitaDAO.java
 * Autor                            : cesar
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 Jul 2011
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
package mx.gob.segob.nsjp.dao.tarea;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.tarea.TipoEvento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.EventoCita;
import mx.gob.segob.nsjp.model.Funcionario;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface EventoCitaDAO extends GenericDao<EventoCita, Long> {

    List<EventoCita> consultarEventosCitasPorFuncionario(Funcionario funcionario);

    public List<EventoCita> consultarEventosCitasPorFunciconario(Long idFuncionario, Date fInicio, Date fFinal) throws NSJPNegocioException;

    /**
     *
     * @param tipoEvento
     * @return
     */
    public List<EventoCita> consultarEventosPorTipoYFecha(TipoEvento tipoEvento, Date fechaEvento);
}
