/**
 * Nombre del Programa : ActualizarEstatusSolicitudServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 21 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación para la actualizacio del estatus de una solicitud.
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
package mx.gob.segob.nsjp.service.solicitud.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.solicitud.ActualizarEstatusSolicitudService;

/**
 * Implementación para la actualizacio del estatus de una solicitud.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional
public class ActualizarEstatusSolicitudServiceImpl
        implements
            ActualizarEstatusSolicitudService {
    /**
     * Logger
     */
    private final static Logger logger = Logger
            .getLogger(ActualizarEstatusSolicitudServiceImpl.class);
    @Autowired
    private SolicitudDAO solDao;
    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.solicitud.ActualizarEstatusSolicitudService
     * #actualizaEstatusSolicitud(mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO,
     * mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud)
     */
    @Override
    public void actualizaEstatusSolicitud(SolicitudDTO solicitud,
            EstatusSolicitud estatus) throws NSJPNegocioException {
        logger.debug("Cambiando al estatus :: " + estatus);
        Solicitud solBD = this.solDao.read(solicitud.getDocumentoId());
        solBD.setEstatus(new Valor(estatus.getValorId()));
        this.solDao.update(solBD);

    }
    
    @Override
    public void actualizaEstatusSolicitud(String folioSolicitud, EstatusSolicitud estatus) throws NSJPNegocioException {
        logger.debug("Cambiando al estatus :: " + estatus);
        Solicitud solBD = solDao.obtenerSolicitudPorFolio(folioSolicitud);
        solBD.setEstatus(new Valor(estatus.getValorId()));
        this.solDao.update(solBD);

    }
    

    
}
