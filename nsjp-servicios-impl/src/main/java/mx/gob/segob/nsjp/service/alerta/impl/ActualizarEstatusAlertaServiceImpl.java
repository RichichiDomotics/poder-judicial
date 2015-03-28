/**
 * Nombre del Programa : ActualizarEstatusAlertaServiceImpl.java
 * Autor                            : gama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 02 Sep 2011
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
package mx.gob.segob.nsjp.service.alerta.impl;

import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.alerta.AlertaDAO;
import mx.gob.segob.nsjp.dto.alarmas.AlertaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.model.Alerta;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.alerta.ActualizarEstatusAlertaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación para la actualizacio del estatus de una Evidencia.
 * 
 * @version 1.0
 * @author gama
 * 
 */
@Service
@Transactional
public class ActualizarEstatusAlertaServiceImpl
        implements
        ActualizarEstatusAlertaService {
    /**
     * Logger
     */
    private final static Logger logger = Logger
            .getLogger(ActualizarEstatusAlertaServiceImpl.class);
    @Autowired
    private AlertaDAO alertaDAO;

    public void actualizaEstatusyFechaAlerta(AlertaDTO alerta,
    		EstatusAlarmaAlerta estatusAlerta) throws NSJPNegocioException {
        logger.debug("Cambiando al estatus :: " + estatusAlerta.getValorId());
        Alerta loEvidencia = this.alertaDAO.read(alerta.getAlertaId());
        if(estatusAlerta.getValorId() != null && estatusAlerta.getValorId() > 0)
        	loEvidencia.setEstatusAlarmaAlerta((new Valor(estatusAlerta.getValorId())));
        if(alerta.getFechaAlerta() != null )
        	loEvidencia.setFechaAlerta(alerta.getFechaAlerta());
        this.alertaDAO.update(loEvidencia);
    }    
}
