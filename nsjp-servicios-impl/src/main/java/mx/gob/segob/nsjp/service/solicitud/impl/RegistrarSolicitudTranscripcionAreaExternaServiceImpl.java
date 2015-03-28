/**
 * Nombre del Programa : RecibirNotificacionAudienciaServiceImpl.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 2 Sep 2011
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
package mx.gob.segob.nsjp.service.solicitud.impl;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionWSDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudTranscripcionAreaExternaService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
@Service("registrarSolicitudTranscripcionAreaExternaService")
@Transactional
public class RegistrarSolicitudTranscripcionAreaExternaServiceImpl
        implements RegistrarSolicitudTranscripcionAreaExternaService {
	@Autowired
    private AudienciaDAO audDao;
    /**
     * 
     */
    private final static Logger logger = Logger
            .getLogger(RegistrarSolicitudTranscripcionAreaExternaServiceImpl.class);
    
    @Autowired
    private RegistrarSolicitudService registrarSolicitudService;



	@Override
	public SolicitudTranscripcionWSDTO registrarSolicitudTranscripcion(
			SolicitudTranscripcionWSDTO solicitudWSDTO) throws NSJPNegocioException {
		logger.info("Inicia - registrarSolicitudTranscripcion(...)");
		logger.debug("solicitudWSDTO.getAudiencia().getFolioAudiencia() :: "+solicitudWSDTO.getAudiencia().getFolioAudiencia());
        if (solicitudWSDTO==null || StringUtils.isEmpty(solicitudWSDTO
                        .getNombreSolicitante())) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        
        Audiencia audBd = audDao.obtnerAudienciaByFolio(solicitudWSDTO.getAudiencia().getFolioAudiencia());
        
        if (audBd==null) {
            return null;
        }
        
        SolicitudTranscripcionAudienciaDTO loSolTranscripcionDTO = new SolicitudTranscripcionAudienciaDTO();
        //Tipo Solicitud
        loSolTranscripcionDTO.setTipoSolicitudDTO(new ValorDTO(solicitudWSDTO.getIdTipoSolicitud()));
        //Fecha Solicitud
        loSolTranscripcionDTO.setFechaCreacion(solicitudWSDTO.getFechaSolicitud());
        //Nombre solicitante
        loSolTranscripcionDTO.setNombreSolicitante(solicitudWSDTO.getNombreSolicitante());
        //Id de la audiencia
        if(solicitudWSDTO.getAudiencia() != null && solicitudWSDTO.getAudiencia().getAudienciaId() != null)
        	loSolTranscripcionDTO.setAudiencia((new AudienciaDTO(solicitudWSDTO.getAudiencia().getAudienciaId())));
        //ConfInstitucion origen
        loSolTranscripcionDTO.setInstitucion((new ConfInstitucionDTO(solicitudWSDTO.getConfInstitucionId())));
        //Folio de la solicitud
        loSolTranscripcionDTO.setFolioSolicitud(solicitudWSDTO.getFolioSolicitud());
        
        //Campos obligatorios
        loSolTranscripcionDTO.setTipoDocumentoDTO(new ValorDTO(TipoDocumento.SOLICITUD.getValorId()));
		loSolTranscripcionDTO.setFormaDTO(new FormaDTO(1L));
		loSolTranscripcionDTO.setNombreDocumento("Audiencia");
		loSolTranscripcionDTO.setEstatus(new ValorDTO(EstatusSolicitud.ABIERTA.getValorId()));
		ExpedienteDTO expDto = new ExpedienteDTO();
		expDto.setNumeroExpedienteId(audBd.getNumeroExpediente().getNumeroExpedienteId());
		loSolTranscripcionDTO.setExpedienteDTO(expDto);
		try{
			loSolTranscripcionDTO = registrarSolicitudService.registrarSolicitudTranscripcionAudiencia(loSolTranscripcionDTO);
			solicitudWSDTO.setSolicitudId(loSolTranscripcionDTO.getDocumentoId());
			
		}catch(Exception e){
			solicitudWSDTO.setSolicitudId(0L);
		}
		 
		 logger.info("Fin - registrarSolicitudTranscripcion(...)");
		 return solicitudWSDTO;
    }  
}

