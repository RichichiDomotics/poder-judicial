/**
 * Nombre del Programa : AsignarSalaTemporalImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Servicio para asigna la sala temporal.
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
package mx.gob.segob.nsjp.service.audiencia.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.SalaTemporalDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.SalaTemporal;
import mx.gob.segob.nsjp.service.audiencia.AsignarSalaTemporalService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para asigna la sala temporal.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional
public class AsignarSalaTemporalServiceImpl
        implements
            AsignarSalaTemporalService {

    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(AsignarSalaTemporalServiceImpl.class);

    @Autowired
    private SalaTemporalDAO salaTempDao = null;

    @Autowired
    private AudienciaDAO audiencaDao = null;

    @Override
    public void asignarSalaTemporal(AudienciaDTO audConSala)
            throws NSJPNegocioException {
        if (logger.isDebugEnabled()) {
            logger.debug("Audiencia :: " + audConSala.getId());
            logger.debug("sala :: " + audConSala.getSala());
        }

        final SalaTemporal st = new SalaTemporal();

        st.setDomicilioSala(audConSala.getSala().getDomicilioSala());
        st.setUbicacionSala(audConSala.getSala().getUbicacionSala());
        st.setMotivo(audConSala.getSala().getMotivo());
        Long idSalaTmp = this.salaTempDao.create(st);

        st.setSalaTemporalId(idSalaTmp);

        final Audiencia aud = this.audiencaDao.read(audConSala.getId());
        aud.setSalaTemporal(st);
        aud.setFechaAudiencia(audConSala.getFechaEvento());
        aud.setDuracionEstimada(audConSala.getDuracionEstimada());
        aud.setFechaAsignacionSala(new Date());

        this.audiencaDao.update(aud);
        logger.info("asignarSalaTemporal(...) [OK]");
    }

}
