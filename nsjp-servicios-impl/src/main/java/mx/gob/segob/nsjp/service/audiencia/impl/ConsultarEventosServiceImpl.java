/**
 * Nombre del Programa : ConsultarEventosServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 31 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación del servicio para consultar los eventos
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.BandejaNotificador;
import mx.gob.segob.nsjp.comun.enums.audiencia.Eventos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.documento.NotificacionDAO;
import mx.gob.segob.nsjp.dto.audiencia.EventoDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.service.audiencia.ConsultarEventosService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.EventoTransformer;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio para consultar los eventos.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class ConsultarEventosServiceImpl implements ConsultarEventosService {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(ConsultarEventosServiceImpl.class);

    @Autowired
    private AudienciaDAO audienciDao;
    @Autowired
    private ConsultarIndividuoService consultarIndividuoService;
    @Autowired
    private ConsultarFuncionarioService consultarFuncionarioService;
    @Autowired
    private NotificacionDAO notifiDao;

    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.audiencia.ConsultarEventosService#
     * consultarEventos(mx.gob.segob.nsjp.dto.audiencia.EventoDTO)
     */
    @Override
    public List<EventoDTO> consultarEventos(EventoDTO filtro)
            throws NSJPNegocioException {
        if (logger.isDebugEnabled()) {
            logger.debug("filtro ::  " + filtro);
        }

        if (filtro.getBandeja().equals(BandejaNotificador.NUEVO)) {
            final List<EventoDTO> resp = new ArrayList<EventoDTO>();
            final List<Audiencia> audBDs = this.audienciDao
                    .consultarAudienciasPendientesNotificacion(filtro
                            .getBandeja());

            resp.addAll(EventoTransformer.tranformarAudienciasMaestro(audBDs));
            // TODO VAP consultar recursos
            return resp;
        } else {
            if (filtro.getBandeja().equals(BandejaNotificador.SEGUIMIENTO)) {

                if (filtro.getTipoEvento().equals(Eventos.AUDENCIA)) {
                    final List<Audiencia> audBDs = this.audienciDao
                            .consultarAudienciasPendientesNotificacion(filtro
                                    .getBandeja());

                    return EventoTransformer
                            .tranformarAudienciasMaestro(audBDs);
                } else {
                    // TODO VAP consultar recursos
                }

            } else {
                final Long idExp = filtro.getExpediente() != null ? filtro
                        .getExpediente().getNumeroExpedienteId() : null;
                final List<Audiencia> audBDs = this.audienciDao
                        .consultarAudienciasporExpediente(idExp);
                // TODO VAP consultar recursos
                return EventoTransformer.tranformarAudienciasMaestro(audBDs);
            }

        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public EventoDTO obtenerEvento(EventoDTO input) throws NSJPNegocioException {
        if (input.getTipoEvento().equals(Eventos.AUDENCIA)) {
            Audiencia aud = audienciDao.read(input.getId());
            if (aud == null) {
                return null;
            }
            final EventoDTO response = EventoTransformer
                    .tranformarAudienciaMaestro(aud);
            if (aud.getExpediente() != null && aud.getExpediente().getCaso() != null) {
                final CasoDTO cas = new CasoDTO(aud.getExpediente().getCaso()
                        .getCasoId(), aud.getExpediente().getCaso()
                        .getNumeroGeneralCaso());
                response.getExpediente().setCasoDTO(cas);
            }
            InvolucradoDTO invoBueno = null;
            for (InvolucradoAudiencia ins : aud.getInvlucradoAudiencias()) {
                invoBueno = InvolucradoTransformer.transformarInvolucrado(ins
                        .getInvolucrado());

                invoBueno = consultarIndividuoService
                        .obtenerIndividuoDTO(invoBueno);

                final List<Notificacion> notis = this.notifiDao
                        .consultarNotificacionesPorAudienciaInvolucrado(
                                input.getId(), invoBueno.getElementoId());

                invoBueno.setNotificaciones(EventoTransformer
                        .transformarNotificaciones(notis));

                response.addInvolucrado(invoBueno);
            }
            
            FuncionarioDTO funcionario = null;
            
            for (FuncionarioAudiencia func : aud.getFuncionarioAudiencias()) {
            	funcionario = FuncionarioTransformer.transformarFuncionarioBasico(func.getFuncionario());
            	funcionario = consultarFuncionarioService.obtenerFuncionarioDTO(funcionario);
            	
                final List<Notificacion> notis = this.notifiDao
                .consultarNotificacionesPorAudienciaFuncionario(
                        input.getId(), funcionario.getClaveFuncionario());
                
                funcionario.setNotificaciones(EventoTransformer
                        .transformarNotificaciones(notis));
            	
                response.addFuncionariol(funcionario);
            }
            
            if (logger.isDebugEnabled()) {
                logger.debug("response :: " + response);
            }
            
            
            return response;

        } else {

            // TODO VAP consultar recursos
        }
        return null;
    }
}
