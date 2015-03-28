package mx.gob.segob.nsjp.service.solicitud.impl.transform;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.SolicitudTranscripcionAudiencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.AudienciaTransformer;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.EventoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

public class SolicitudTranscripcionAudienciaTransformer {
	
	public static SolicitudTranscripcionAudiencia transformarSolicitudTranscripcionAudiencia(SolicitudTranscripcionAudienciaDTO solDTO)
	{
		SolicitudTranscripcionAudiencia sol = new SolicitudTranscripcionAudiencia();
		if(sol.getTipoSolicitud()!=null)
			sol.setTipoSolicitud(new Valor(solDTO.getTipoSolicitudDTO().getIdCampo()));
		if(solDTO.getDocumentoId()!=null)
			sol.setDocumentoId(solDTO.getDocumentoId());
		if(solDTO.getExpedienteDTO().getNumeroExpedienteId()!=null)
			sol.setNumeroExpediente(new NumeroExpediente(solDTO.getExpedienteDTO().getNumeroExpedienteId()));		
		if(solDTO.getNumeroCasoAsociado()!=null)
			sol.setNumeroCasoAsociado(solDTO.getNumeroCasoAsociado());
		if(solDTO.getMotivo()!=null)
			sol.setMotivo(solDTO.getMotivo());
		if(solDTO.getEstatus()!=null)
			sol.setEstatus(new Valor(solDTO.getEstatus().getIdCampo()));
		if(solDTO.getFechaLimite()!=null)
			sol.setFechaLimite(solDTO.getFechaLimite());
		if(solDTO.getFechaModificacion()!=null)
			sol.setFechaModificacion(solDTO.getFechaModificacion());
		if(solDTO.getFechaCierre()!=null)
			sol.setFechaCierre(solDTO.getFechaCierre());
		if(solDTO.getUsuario().getFuncionario()!=null)
			sol.setFuncionarioSolicitante(FuncionarioTransformer.transformarFuncionario(solDTO.getUsuario().getFuncionario()));
		if(solDTO.getInstitucion().getConfInstitucionId()!=null)
			sol.setConfInstitucion(new ConfInstitucion(solDTO.getInstitucion().getConfInstitucionId()));
		//if(solDTO.getSolicitanteExterno()!=null)
		//	sol.setAreaSolicitanteExterna(solDTO.getSolicitanteExterno());
		if(solDTO.getSolicitanteExterno()!=null)
			sol.setSolicitanteExterno(solDTO.getSolicitanteExterno());
		if(solDTO.getNombreSolicitante()!=null)
			sol.setNombreSolicitante(solDTO.getNombreSolicitante());
		if(solDTO.getEsUrgente()!=null)
			sol.setEsUrgente(solDTO.getEsUrgente());
		if(solDTO.getInvolucradoDTO()!=null)
			sol.setInvolucradoSolicitante(InvolucradoTransformer.transformarInvolucrado(solDTO.getInvolucradoDTO()));
		if(solDTO.getDestinatario()!=null)
			sol.setDestinatario(FuncionarioTransformer.transformarFuncionario(solDTO.getDestinatario()));
		if(solDTO.getFolioSolicitud()!=null)
			sol.setFolioSolicitud(solDTO.getFolioSolicitud());
		if(solDTO.getAsuntoSolicitud()!=null)
			sol.setAsuntoSolicitud(solDTO.getAsuntoSolicitud());
		if(solDTO.getObservaciones()!=null)
			sol.setObservaciones(solDTO.getObservaciones());
		return sol;
	}
	
	public static SolicitudTranscripcionAudienciaDTO transformarSolicitudTranscripcionAudiencia(SolicitudTranscripcionAudiencia sol)
	{
		if(sol == null)
			return null;
		SolicitudTranscripcionAudienciaDTO solDTO = new SolicitudTranscripcionAudienciaDTO();
		
		if(sol.getTipoSolicitud()!=null)
			solDTO.setTipoSolicitudDTO(new ValorDTO(sol.getTipoSolicitud().getValorId()));
		if(sol.getDocumentoId()!=null)
			solDTO.setDocumentoId(sol.getDocumentoId());
		if(sol.getNumeroExpediente()!=null)
			
			solDTO.setExpedienteDTO(ExpedienteTransformer.transformarExpedienteBasico(sol.getNumeroExpediente()));
		if(sol.getNumeroCasoAsociado()!=null)
			solDTO.setNumeroCasoAsociado(sol.getNumeroCasoAsociado());		
		if(sol.getMotivo()!=null)
			solDTO.setMotivo(sol.getMotivo());
		
		if(sol.getEstatus()!=null)
			solDTO.setEstatus(new ValorDTO(sol.getEstatus().getValorId()));
		if(sol.getFechaLimite()!=null)
			solDTO.setFechaLimite(sol.getFechaLimite());
		if(sol.getFechaModificacion()!=null)
			solDTO.setFechaModificacion(sol.getFechaModificacion());
		if(sol.getFechaCierre()!=null)
			solDTO.setFechaCierre(sol.getFechaCierre());
		if(sol.getFuncionarioSolicitante()!=null)
				solDTO.setUsuarioSolicitante(FuncionarioTransformer.transformarFuncionario(sol.getFuncionarioSolicitante()));
		solDTO.setFechaCreacion(sol.getFechaCreacion());
		solDTO.setStrFechaCreacion(DateUtils.formatear(sol.getFechaCreacion()));
		solDTO.setStrHoraCreacion(DateUtils.formatearHora(sol.getFechaCreacion()));
	
		
		if(sol.getConfInstitucion()!=null){
			solDTO.setInstitucion(new ConfInstitucionDTO(sol.getConfInstitucion().getClave()));
			solDTO.getInstitucion().setNombreInst(sol.getConfInstitucion().getNombreInst());
		}
		//if(solDTO.getSolicitanteExterno()!=null)
		//	sol.setAreaSolicitanteExterna(solDTO.getSolicitanteExterno());
		if(sol.getSolicitanteExterno()!=null)
			solDTO.setSolicitanteExterno(sol.getSolicitanteExterno());
		if(sol.getNombreSolicitante()!=null)
			solDTO.setNombreSolicitante(sol.getNombreSolicitante());
		if(sol.getEsUrgente()!=null)
			solDTO.setEsUrgente(sol.getEsUrgente());
		if(sol.getInvolucradoSolicitante()!=null)
			solDTO.setInvolucradoDTO(InvolucradoTransformer.transformarInvolucrado(sol.getInvolucradoSolicitante()));
		if(sol.getDestinatario()!=null)
			solDTO.setDestinatario(FuncionarioTransformer.transformarFuncionario(sol.getDestinatario()));
		if(sol.getFolioSolicitud()!=null)
			solDTO.setFolioSolicitud(sol.getFolioSolicitud());
		if(sol.getAsuntoSolicitud()!=null)
			solDTO.setAsuntoSolicitud(sol.getAsuntoSolicitud());
		if(sol.getObservaciones()!=null)
			solDTO.setObservaciones(sol.getObservaciones());
		
		if(sol.getAudiencia() != null){
			solDTO.setAudiencia(EventoTransformer.transformarAudienciaBasico(sol.getAudiencia()));
		}
		
		solDTO.setArchivoDigital(ArchivoDigitalTransformer.transformarArchivoDigitalBasico(sol.getArchivoDigital()));
		
		return solDTO;
	}
}
