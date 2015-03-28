/**
 * Nombre del Programa : AgendaAlarmaDAOImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 27-jul-2011
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
package mx.gob.segob.nsjp.dao.agendaalarma.impl;

import mx.gob.segob.nsjp.dao.agendaalarma.AgendaAlarmaDAO;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.model.AgendaAlarma;
import org.springframework.stereotype.Repository;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
public class AgendaAlarmaDAOImpl
    extends GenericDaoHibernateImpl<AgendaAlarma, Long>
    implements AgendaAlarmaDAO {
}
