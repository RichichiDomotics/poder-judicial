/**
 * Nombre del Programa : ConsultaNotificacionServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 20-jul-2011
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
package mx.gob.segob.nsjp.service.notificacion.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.NotificacionDAO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.service.notificacion.ConsultaNotificacionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConsultaNotificacionServiceImpl implements ConsultaNotificacionService {

    /**
     * Logger de la clase.
     */
    private final static Logger logger = Logger.getLogger(ConsultaNotificacionServiceImpl.class);
    @Autowired
    private NotificacionDAO notificacionDao;

    @Override
    public NotificacionDTO consultaNotificacion(NotificacionDTO consulta)
            throws NSJPNegocioException {
        Notificacion notificacion = NotificacionTransformer.transformarNotificacion(consulta);
        Notificacion consultada = notificacionDao.read(consulta.getDocumentoId());
        return consultada != null ? NotificacionTransformer.transformarNotificacion(consultada) : null;
    }
}
