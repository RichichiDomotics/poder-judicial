/**
 * Nombre del Programa : ActualizarEstatusNotificacionServiceImpl.java
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

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.NotificacionDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.notificacion.ActualizarEstatusNotificacionService;
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
public class ActualizarEstatusNotificacionServiceImpl implements
        ActualizarEstatusNotificacionService {

    /**
     * Logger de la clase.
     */
    private final static Logger logger =
            Logger.getLogger(ActualizarEstatusNotificacionServiceImpl.class);
    @Autowired
    private NotificacionDAO notificacionDAO;

    @Override
    public void actualizarEstatusNotificacion(NotificacionDTO notificacionDto,
            ValorDTO nuevoEstado) throws NSJPNegocioException {
    	if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ACTUALIZAR EL ESTATUS DE UN DOCUMENTO TIPO NOTIFICACIÓN DE AUDIENCIA ****/");
    	
        if (notificacionDto == null || nuevoEstado == null
                || notificacionDto.getDocumentoId() == null
                || nuevoEstado.getIdCampo() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Notificacion notificacion = notificacionDAO.read(notificacionDto.getDocumentoId());
        notificacion.setEstatus(new Valor(nuevoEstado.getIdCampo()));
        notificacionDAO.update(notificacion);
        
    }
}
