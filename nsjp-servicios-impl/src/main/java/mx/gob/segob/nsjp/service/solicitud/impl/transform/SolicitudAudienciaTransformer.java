/**
 * Nombre del Programa : SolicitudAudienciaTransformer.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Jun 2011
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
package mx.gob.segob.nsjp.service.solicitud.impl.transform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudAudiencia;
import mx.gob.segob.nsjp.model.SolicitudTranscripcionAudiencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.AudienciaTransformer;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.EventoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

import org.apache.log4j.Logger;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */ 
public class SolicitudAudienciaTransformer {
    /**
     * logger.
     */
    private final static Logger logger = Logger
            .getLogger(SolicitudAudienciaTransformer.class);
    /**
     * 
     * @param fromBD
     * @return
     */
    public static List<SolicitudAudienciaDTO> transformarSolicitudesAudiencias(
            List<SolicitudAudiencia> fromBD) {
        List<SolicitudAudienciaDTO> resp = new ArrayList<SolicitudAudienciaDTO>();

        for (SolicitudAudiencia row : fromBD) {
            resp.add(SolicitudAudienciaTransformer.transformarSolicitud(row));
        }

        return resp;
    }
    
    /**
     * 
     * @param row
     * @return
     */
    public static SolicitudAudienciaDTO transformarSolicitudBasico(
            SolicitudAudiencia row) {
        if (row == null) {
            return null;
        }
        logger.debug("Transformando :: " + row.getDocumentoId());
        final SolicitudAudienciaDTO dto = new SolicitudAudienciaDTO();
        dto.setDocumentoId(row.getDocumentoId());

        AudienciaDTO auDto = EventoTransformer.transformarAudienciaBasico(row.getAudiencia());

        dto.setFechaCreacion(row.getFechaCreacion());
        dto.setStrFechaCreacion(DateUtils.formatear(row.getFechaCreacion()));
        dto.setStrHoraCreacion(DateUtils.formatearHora(row.getFechaCreacion()));
        dto.setFechaLimite(row.getFechaLimite());
        dto.setStrFechaLimite(DateUtils.formatear(row.getFechaLimite()));
        dto.setStrHoraLimite(DateUtils.formatearHora(row.getFechaLimite()));
        dto.setAudiencia(auDto);
        dto.setExpedienteDTO(auDto.getExpediente());
        dto.setUsuarioSolicitante(FuncionarioTransformer.transformarFuncionario(row.getFuncionarioSolicitante()));
        dto.setNombreSolicitanteExternoInterno(row.getNombreSolicitante());
        dto.setNombreSolicitante(row.getNombreSolicitante());
        dto.setSolicitanteExterno(row.getSolicitanteExterno());
        // Es interno
        if (row.getFuncionarioSolicitante() != null) {
            dto.setNombreInstitucionSolicitante(dto.getUsuarioSolicitante()
                    .getDepartamento().getNombreDepto());
            dto.setNombreSolicitanteExternoInterno(dto.getUsuarioSolicitante()
                    .getPersona().getNombreCompleto());
        } 
        if (row.getConfInstitucion() != null) {
            // es externo
            dto.setNombreInstitucionSolicitante( row.getConfInstitucion().getNombreInst());
            dto.setInstitucion(new ConfInstitucionDTO(row.getConfInstitucion().getConfInstitucionId(),row.getConfInstitucion().getNombreInst()));
        }

        return dto;
    }
    
    /**
     * 
     * @param row
     * @return
     */
	public static SolicitudAudienciaDTO transformarSolicitud(
            SolicitudAudiencia row) {
        if (row == null) {
            return null;
        }
        logger.debug("Transformando :: " + row.getDocumentoId());
        final SolicitudAudienciaDTO dto = new SolicitudAudienciaDTO();
        dto.setDocumentoId(row.getDocumentoId());

        AudienciaDTO auDto = EventoTransformer.transformarAudienciaCompleto(row.getAudiencia());

        dto.setFechaCreacion(row.getFechaCreacion());
        dto.setStrFechaCreacion(DateUtils.formatear(row.getFechaCreacion()));
        dto.setStrHoraCreacion(DateUtils.formatearHora(row.getFechaCreacion()));
        dto.setFechaLimite(row.getFechaLimite());
        dto.setStrFechaLimite(DateUtils.formatear(row.getFechaLimite()));
        dto.setStrHoraLimite(DateUtils.formatearHora(row.getFechaLimite()));
        dto.setAudiencia(auDto);
        dto.setExpedienteDTO(auDto.getExpediente());
        if (row.getFuncionarioSolicitante()!=null) 
        	dto.setUsuarioSolicitante(FuncionarioTransformer.transformarFuncionario(row.getFuncionarioSolicitante()));
        dto.setNombreSolicitante(row.getNombreSolicitante());
        dto.setNombreSolicitanteExternoInterno(row.getNombreSolicitante());
        dto.setSolicitanteExterno(row.getSolicitanteExterno());
        if(row.getConfInstitucion() != null){
        	dto.setInstitucion(ConfInstitucionTransformer.transformarInstitucion(row.getConfInstitucion()));
        	dto.setNombreInstitucionSolicitante(row.getConfInstitucion().getNombreInst());
        }
        // Es interno
        if (row.getFuncionarioSolicitante()!=null) {
            dto.setNombreSolicitanteExternoInterno(row.getFuncionarioSolicitante()
                    .getNombreCompleto());
        }
        if (row.getInvolucradoSolicitante()!=null) {
        	dto.setInvolucradoDTO(InvolucradoTransformer.transformarInvolucradoBasico(row.getInvolucradoSolicitante()));
        }
        logger.debug("Transformando terminada ****/ " + row.getDocumentoId());
        return dto;
    }

    /**
     * 
     * @param in
     * @return
     */
    private static ValorDTO transformValor(Valor in) {
        if (in == null) {
            return null;
        }
        final ValorDTO out = new ValorDTO(in.getValorId(), in.getValor());
        return out;
    }

    public static List<SolicitudDTO> transformarSolicitudes(
            List<Solicitud> fromBD) {
        List<SolicitudDTO> resp = new ArrayList<SolicitudDTO>();
        SolicitudDTO dto = null;
        for (Solicitud sol : fromBD) {
            dto = new SolicitudDTO();
            
            dto.setDocumentoId(sol.getDocumentoId());
            if(sol.getNumeroExpediente()!= null){
	            final ExpedienteDTO expDto = ExpedienteTransformer
	            .transformarExpedienteBasico(sol.getNumeroExpediente()
	                    .getExpediente());
	            expDto.setNumeroExpediente(sol.getNumeroExpediente().getNumeroExpediente());
	            dto.setExpedienteDTO(expDto);
            }
            dto.setTipoSolicitudDTO(new ValorDTO(sol.getTipoSolicitud()
                    .getValorId(), sol.getTipoSolicitud().getValor()));
            dto.setFechaCreacion(sol.getFechaCreacion());
            dto.setEstatus(transformValor(sol.getEstatus()));
            if (sol.getFuncionarioSolicitante()!=null) {
//                dto.setNombreSolicitante(sol.getFuncionarioSolicitante().getNombreCompleto());
            } else {
                dto.setNombreSolicitante(sol.getNombreSolicitante());
            }
            if (sol.getConfInstitucion() != null) {
                dto.setInstitucion(new ConfInstitucionDTO(sol
                        .getConfInstitucion().getNombreInst()));
                dto.setNombreInstitucionSolicitante(sol.getConfInstitucion().getNombreInst());
            }
            dto.setFolioSolicitud(sol.getFolioSolicitud());
            resp.add(dto);
        }

        return resp;
    }


    public static SolicitudAudiencia transformarSolicitud(
			SolicitudAudienciaDTO solDTO) {
		SolicitudAudiencia solicitud = new SolicitudAudiencia();

		if (solDTO.getExpedienteDTO() != null){
			solicitud.setNumeroExpediente(new NumeroExpediente(solDTO.getExpedienteDTO().getNumeroExpedienteId()));
			if(solDTO.getExpedienteDTO().getCasoDTO() != null ){
				Caso caso = new Caso();
				caso.setNumeroGeneralCaso(solDTO.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso());
				Expediente expediente = new Expediente();
				expediente.setCaso(caso);
				solicitud.getNumeroExpediente().setExpediente(expediente);
			}
		}
		if (solDTO.getTipoSolicitudDTO() != null)
			solicitud.setTipoSolicitud(new Valor(solDTO.getTipoSolicitudDTO().getIdCampo()));
		if (solDTO.getUsuarioSolicitante() != null)
			solicitud.setFuncionarioSolicitante(new Funcionario(solDTO.getUsuarioSolicitante().getClaveFuncionario()));
		if (solDTO.getInstitucion() != null)
			solicitud.setConfInstitucion(new ConfInstitucion(solDTO.getInstitucion().getConfInstitucionId()));
		if (solDTO.getEstatus()!=null)
			solicitud.setEstatus(new Valor(solDTO.getEstatus().getIdCampo()));

		solicitud.setNumeroCasoAsociado(solDTO.getNumeroCasoAsociado());
		solicitud.setMotivo(solDTO.getMotivo());
		solicitud.setFechaLimite(solDTO.getFechaLimite());
		solicitud.setFechaModificacion(solDTO.getFechaModificacion());
		solicitud.setFechaCierre(solDTO.getFechaCierre());
		solicitud.setNombreSolicitante(solDTO.getNombreSolicitante());
		solicitud.setEsUrgente(solDTO.getEsUrgente());
		solicitud.setFolioSolicitud(solDTO.getFolioSolicitud());
		
		// Documento
		if (solDTO.getTipoDocumentoDTO() != null)
			solicitud.setTipoDocumento(new Valor(solDTO.getTipoDocumentoDTO()
					.getIdCampo()));
		if (solDTO.getFormaDTO() != null)
			solicitud.setForma(new Forma(solDTO.getFormaDTO().getFormaId()));
		if(solDTO.getAudiencia() != null){
			solicitud.setAudiencia(AudienciaTransformer.transformarAudiencia(solDTO.getAudiencia()));
		}
		
		solicitud.setFechaCreacion(solDTO.getFechaCreacion());
		solicitud.setNombreDocumento(solDTO.getNombreDocumento());
		solicitud.setDocumentoId(solDTO.getDocumentoId());
		if(solDTO.getDestinatario() != null){
			solicitud.setDestinatario(FuncionarioTransformer.transformarFuncionario(solDTO.getDestinatario()));
		}
		
		return solicitud;
	}


    public static SolicitudTranscripcionAudiencia transformarSolicitud(
			SolicitudTranscripcionAudienciaDTO solDTO) {
    	SolicitudTranscripcionAudiencia solicitud = new SolicitudTranscripcionAudiencia();

		if (solDTO.getExpedienteDTO() != null)
			solicitud.setNumeroExpediente(new NumeroExpediente(solDTO.getExpedienteDTO().getNumeroExpedienteId()));
		if (solDTO.getTipoSolicitudDTO() != null)
			solicitud.setTipoSolicitud(new Valor(solDTO.getTipoSolicitudDTO().getIdCampo()));
		if (solDTO.getUsuarioSolicitante() != null)
			solicitud.setFuncionarioSolicitante(new Funcionario(solDTO.getUsuarioSolicitante().getClaveFuncionario()));
		if (solDTO.getInstitucion() != null)
			solicitud.setConfInstitucion(new ConfInstitucion(solDTO.getInstitucion().getConfInstitucionId()));
		if (solDTO.getEstatus()!=null)
			solicitud.setEstatus(new Valor(solDTO.getEstatus().getIdCampo()));

		solicitud.setNumeroCasoAsociado(solDTO.getNumeroCasoAsociado());
		solicitud.setMotivo(solDTO.getMotivo());
		solicitud.setFechaLimite(solDTO.getFechaLimite());
		solicitud.setFechaModificacion(solDTO.getFechaModificacion());
		solicitud.setFechaCierre(solDTO.getFechaCierre());
		solicitud.setNombreSolicitante(solDTO.getNombreSolicitante());
		solicitud.setEsUrgente(solDTO.getEsUrgente());
		solicitud.setFolioSolicitud(solDTO.getFolioSolicitud());
		
		if(solDTO.getAudiencia() != null){
			solicitud.setAudiencia(AudienciaTransformer.transformarEntity(solDTO.getAudiencia()));
		}
		
		// Documento
		if (solDTO.getTipoDocumentoDTO() != null)
			solicitud.setTipoDocumento(new Valor(solDTO.getTipoDocumentoDTO()
					.getIdCampo()));
		if (solDTO.getFormaDTO() != null)
			solicitud.setForma(new Forma(solDTO.getFormaDTO().getFormaId()));

		solicitud.setFechaCreacion(solDTO.getFechaCreacion());
		solicitud.setNombreDocumento(solDTO.getNombreDocumento());
		solicitud.setDocumentoId(solDTO.getDocumentoId());
		if(solDTO.getDestinatario() != null){
			solicitud.setDestinatario(FuncionarioTransformer.transformarFuncionario(solDTO.getDestinatario()));
		}
		
		return solicitud;
	}

	public static SolicitudTranscripcionAudienciaDTO transformarSolicitud(
			SolicitudTranscripcionAudiencia solicitud) {
	       if (solicitud == null) {
	            return null;
	        }
	        logger.debug("Transformando :: " + solicitud.getDocumentoId());
	        final SolicitudTranscripcionAudienciaDTO dto = new SolicitudTranscripcionAudienciaDTO();
	        dto.setDocumentoId(solicitud.getDocumentoId());

	        AudienciaDTO auDto = EventoTransformer.transformarAudienciaCompleto(solicitud.getAudiencia());

	        dto.setFechaCreacion(solicitud.getFechaCreacion());
	        dto.setStrFechaCreacion(DateUtils.formatear(solicitud.getFechaCreacion()));
	        dto.setStrHoraCreacion(DateUtils.formatearHora(solicitud.getFechaCreacion()));
	        dto.setFechaLimite(solicitud.getFechaLimite());
	        dto.setStrFechaLimite(DateUtils.formatear(solicitud.getFechaLimite()));
	        dto.setStrHoraLimite(DateUtils.formatearHora(solicitud.getFechaLimite()));
	        dto.setAudiencia(auDto);
	        dto.setExpedienteDTO(auDto.getExpediente());
	        dto.setUsuarioSolicitante(FuncionarioTransformer.transformarFuncionario(solicitud.getFuncionarioSolicitante()));
	        dto.setNombreSolicitante(solicitud.getNombreSolicitante());
	        dto.setSolicitanteExterno(solicitud.getSolicitanteExterno());
	        // Es interno
	        if (dto.getUsuarioSolicitante() != null) {
	            dto.setNombreInstitucionSolicitante(dto.getUsuarioSolicitante()
	                    .getDepartamento().getNombreDepto());
	            if(dto.getUsuarioSolicitante().getPersona()!=null)
	            dto.setNombreSolicitanteExternoInterno(dto.getUsuarioSolicitante()
	                    .getPersona().getNombreCompleto());
	        } else {
	            // es externo
	            dto.setNombreInstitucionSolicitante(null);
	            dto.setNombreSolicitanteExternoInterno(dto.getNombreSolicitante());
	        }

	        return dto;
	}

}
