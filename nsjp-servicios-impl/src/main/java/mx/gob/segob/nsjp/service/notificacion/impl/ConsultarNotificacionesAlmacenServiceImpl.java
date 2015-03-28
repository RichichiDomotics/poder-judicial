/**
 * Nombre del Programa : ConsultarNotificacionesAlmacenServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 03-ago-2011
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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.NotificacionDAO;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.service.notificacion.ConsultarNotificacionesAlmacenService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.AlmacenTransformer;
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
public class ConsultarNotificacionesAlmacenServiceImpl implements ConsultarNotificacionesAlmacenService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(ConsultarNotificacionesAlmacenServiceImpl.class);

    @Autowired
    private NotificacionDAO notificacionDao;

    @Override
    public List<NotificacionDTO> consultarNotificacionesAlmacen(AlmacenDTO almacenDto,
            long tipoMovimiento, long estadoNotificacion) throws NSJPNegocioException {
        if (almacenDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Almacen almacen = AlmacenTransformer.transformarAlmacen(almacenDto);
        List<Notificacion> notificaciones = notificacionDao.
                consultarNotificacionesAlmacen(almacen, tipoMovimiento, estadoNotificacion);
        List<NotificacionDTO> notificacionesDto = Collections.EMPTY_LIST;
        if (!notificaciones.isEmpty()) {
            notificacionesDto = new LinkedList<NotificacionDTO>();
            for (Notificacion notificacion : notificaciones) {
                NotificacionDTO notificacionDto =
                        NotificacionTransformer.transformarNotificacion(notificacion);
                notificacionesDto.add(notificacionDto);
            }
        }
        return notificacionesDto;
    }
}
