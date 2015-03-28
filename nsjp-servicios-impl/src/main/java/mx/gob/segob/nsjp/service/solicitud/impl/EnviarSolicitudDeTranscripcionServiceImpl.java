/**
 * Nombre del Programa : EnviarSolicitudDeTranscripcionServiceImpl.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 02-Sep-2011
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
package mx.gob.segob.nsjp.service.solicitud.impl;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.infra.PJClienteService;
import mx.gob.segob.nsjp.service.solicitud.EnviarSolicitudDeTranscripcionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author rgama
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class EnviarSolicitudDeTranscripcionServiceImpl implements
 EnviarSolicitudDeTranscripcionService {
	
    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(EnviarSolicitudDeTranscripcionServiceImpl.class);

    @Autowired
    private PJClienteService clientePJWebService;
    @Autowired
    private AudienciaDAO audDao;

    
    @Override
    @Transactional
    public SolicitudTranscripcionAudienciaDTO enviarSolicitudDeTranscripcion(SolicitudTranscripcionAudienciaDTO
            solicitudTranscripcionDto) throws NSJPNegocioException {
    	if (logger.isDebugEnabled())
			logger.debug("/SERVICIO PARA ENVIAR SOLICITUD TRANSCRIPCION/");
    	
        
    	if(solicitudTranscripcionDto==null || solicitudTranscripcionDto.getTipoSolicitudDTO() == null || solicitudTranscripcionDto.getTipoSolicitudDTO().getIdCampo() == null
    			|| solicitudTranscripcionDto.getTipoSolicitudDTO().getIdCampo() == null 
    			|| solicitudTranscripcionDto.getNombreSolicitante()== null || solicitudTranscripcionDto.getAudiencia() == null
    			|| solicitudTranscripcionDto.getAudiencia().getId() == null )
    		throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        
    	Audiencia audExist = this.audDao.obtnerAudienciaByFolio(solicitudTranscripcionDto.getAudiencia().getFolioAudiencia());
    	
    	if (audExist ==null) {
    	    Audiencia nueva = new Audiencia();
    	    nueva.setEstatus(new Valor(EstatusAudiencia.FINALIZADA.getValorId()));
    	    nueva.setTipo(new Valor(TipoAudiencia.JUICIO_ORAL.getValorId()));
    	    nueva.setConsecutivo((short) 1);
    	    solicitudTranscripcionDto.getAudiencia().setId(this.audDao.create(nueva));
    	} else {
    	    solicitudTranscripcionDto.getAudiencia().setId(audExist.getAudienciaId());
    	}
    	
        SolicitudTranscripcionAudienciaDTO solicitudAudiencia = clientePJWebService.enviarSolicitudTranscripcion(solicitudTranscripcionDto);               
        return solicitudAudiencia;
    }

   
}
