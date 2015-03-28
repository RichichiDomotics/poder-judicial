/**
 * Nombre del Programa : SolicitudTransformer.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Transforma el objeto en Solicitud a SolicitudDTO y viceversa
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
import java.util.Date;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.solicitud.TipoMandamiento;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.archivo.SolicitudAdjuntosDTO;
import mx.gob.segob.nsjp.dto.archivo.SolicitudAdjuntosIdDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.AcuseReciboDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudBeneficiosPreliberacionWSDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudMandamientoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoDTO;
import mx.gob.segob.nsjp.model.AcuseRecibo;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.FuncionarioExterno;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudAdjuntos;
import mx.gob.segob.nsjp.model.SolicitudAudiencia;
import mx.gob.segob.nsjp.model.SolicitudDefensor;
import mx.gob.segob.nsjp.model.SolicitudMandamiento;
import mx.gob.segob.nsjp.model.SolicitudTrasladoImputado;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.acuse.impl.transform.AcuseTransformer;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.AudienciaTransformer;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.EventoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.funcionarioexterno.impl.transform.FuncionarioExternoTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

/**
 * Transforma el objeto en Solicitud a SolicitudDTO y viceversa.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public class SolicitudTransformer {

	public static SolicitudDefensor solDefensorTransformer(
			SolicitudDefensorDTO solDefensorDTO) {
		SolicitudDefensor solDefensor = new SolicitudDefensor();

		// Atributos Solicitud
		solDefensor.setAreaOrigen(solDefensorDTO.getAreaOrigen());
		solDefensor.setAreaDestino(solDefensorDTO.getAreaDestino());
		
		solDefensor.setNumeroCasoAsociado(solDefensorDTO
				.getNumeroCasoAsociado());
		if (solDefensorDTO.getTipoSolicitudDTO() != null)
			solDefensor.setTipoSolicitud(new Valor(solDefensorDTO
					.getTipoSolicitudDTO().getIdCampo()));
		// Atributos Forma
		// solDefensor.setDescForma(solDefensorDTO.getDescForma());
		// if (solDefensorDTO.getTipoFormaDTO()!=null)
		// solDefensor.setTipoForma(new
		// Valor(solDefensorDTO.getTipoFormaDTO().getIdCampo()));

		if (solDefensorDTO.getExpedienteDTO() != null)
			solDefensor.setNumeroExpediente(new NumeroExpediente(solDefensorDTO
					.getExpedienteDTO().getNumeroExpedienteId()));
		if (solDefensorDTO.getTipoSolicitudDTO() != null)
			solDefensor.setTipoSolicitud(new Valor(solDefensorDTO
					.getTipoSolicitudDTO().getIdCampo()));
		if (solDefensorDTO.getUsuarioSolicitante() != null)
			solDefensor.setFuncionarioSolicitante(new Funcionario(
					solDefensorDTO.getUsuarioSolicitante()
							.getClaveFuncionario()));
		if (solDefensorDTO.getInstitucion() != null)
			solDefensor.setConfInstitucion(new ConfInstitucion(solDefensorDTO
					.getInstitucion().getConfInstitucionId()));
		if (solDefensorDTO.getFuncionario() != null)
			solDefensor.setFuncionarioSolicitante(new Funcionario(
					solDefensorDTO.getFuncionario().getClaveFuncionario()));

		solDefensor.setNumeroCasoAsociado(solDefensorDTO
				.getNumeroCasoAsociado());
		solDefensor.setMotivo(solDefensorDTO.getMotivo());
		solDefensor.setFechaLimite(solDefensorDTO.getFechaLimite());
		solDefensor.setFechaModificacion(solDefensorDTO.getFechaModificacion());
		solDefensor.setFechaCierre(solDefensorDTO.getFechaCierre());
		solDefensor.setNombreSolicitante(solDefensorDTO.getNombreSolicitante());
		// solicitud.setEstatus(new EstatusSolicitud.ABIERTA);

		// Documento
		if (solDefensorDTO.getTipoDocumentoDTO() != null)
			solDefensor.setTipoDocumento(new Valor(solDefensorDTO
					.getTipoDocumentoDTO().getIdCampo()));
		if (solDefensorDTO.getFormaDTO() != null)
			solDefensor.setForma(new Forma(solDefensorDTO.getFormaDTO()
					.getFormaId()));

		solDefensor.setFechaCreacion(solDefensorDTO.getFechaCreacion());

		return solDefensor;
	}

	public static Solicitud solicitudTransformer(SolicitudDTO solDTO) {
		final Solicitud solicitud = new Solicitud();

		if (solDTO.getExpedienteDTO() != null){
			NumeroExpediente numeroExpediente = new NumeroExpediente();
			numeroExpediente.setNumeroExpedienteId(solDTO.getExpedienteDTO().getNumeroExpedienteId());
			numeroExpediente.setNumeroExpediente(solDTO.getExpedienteDTO().getNumeroExpediente());
			solicitud.setNumeroExpediente(numeroExpediente);
		}
		if (solDTO.getTipoSolicitudDTO() != null)
			solicitud.setTipoSolicitud(new Valor(solDTO.getTipoSolicitudDTO()
					.getIdCampo()));
		if (solDTO.getUsuarioSolicitante() != null)
			solicitud.setFuncionarioSolicitante(new Funcionario(solDTO
					.getUsuarioSolicitante().getClaveFuncionario()));
		if (solDTO.getInstitucion() != null)
			solicitud.setConfInstitucion(new ConfInstitucion(solDTO
					.getInstitucion().getConfInstitucionId()));
		if (solDTO.getEstatus()!=null)
			solicitud.setEstatus(new Valor(solDTO.getEstatus().getIdCampo()));

		solicitud.setNumeroCasoAsociado(solDTO.getNumeroCasoAsociado());
		solicitud.setMotivo(solDTO.getMotivo());
		solicitud.setFechaLimite(solDTO.getFechaLimite());
		solicitud.setFechaModificacion(solDTO.getFechaModificacion());
		solicitud.setFechaCierre(solDTO.getFechaCierre());
		solicitud.setNombreSolicitante(solDTO.getNombreSolicitante());

		solicitud.setEsUrgente(solDTO.getEsUrgente()==null?false:solDTO.getEsUrgente());
		solicitud.setFolioSolicitud(solDTO.getFolioSolicitud());
		
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
		solicitud.setAsuntoSolicitud(solDTO.getAsuntoSolicitud());
		solicitud.setObservaciones(solDTO.getObservaciones());
		
		solicitud.setAreaOrigen(solDTO.getAreaOrigen());
		solicitud.setAreaDestino(solDTO.getAreaDestino());
		
		if (solDTO.getSolicitanteInstExterna() != null && 
				solDTO.getSolicitanteInstExterna().getFuncionarioExternoId() != null){
			FuncionarioExterno solicitante = new FuncionarioExterno();
			solicitante.setFuncionarioExternoId(solDTO.getSolicitanteInstExterna().getFuncionarioExternoId());
			solicitud.setFuncionarioSolExt(solicitante);
		}
		
		if (solDTO.getDestinatarioInstExterna() != null && 
				solDTO.getDestinatarioInstExterna().getFuncionarioExternoId() != null){
			FuncionarioExterno destinatario = new FuncionarioExterno();
			destinatario.setFuncionarioExternoId(solDTO.getDestinatarioInstExterna().getFuncionarioExternoId());
		}
		
		return solicitud;
	}

	/**
	 * Crea una SolicitudDTO con un 1. tipoSolicitudDto 2. fechaCreacion 3.
	 * fechaCierre 4. nombreInstitucionSolicitante 5.
	 * nombreSolicitanteExternoInterno 6. estatus 7. expedienteDto
	 * 
	 * @param solicitud
	 * @return
	 */
	public static SolicitudDTO solicitudTransformer(
			Solicitud solicitud) {
		SolicitudDTO solicitudDTO = new SolicitudDTO();
		Valor valor;
		ValorDTO valorDTO;

		// Solicitud
		solicitudDTO.setFolioSolicitud(solicitud.getFolioSolicitud());
		solicitudDTO.setDocumentoId(solicitud.getDocumentoId());
		solicitudDTO.setFechaCreacion(solicitud.getFechaCreacion());
		solicitudDTO.setStrFechaCreacion(DateUtils.formatear(solicitud.getFechaCreacion()));
		solicitudDTO.setStrHoraCreacion(DateUtils.formatearHora(solicitud.getFechaCreacion()));
        solicitudDTO.setNombreSolicitante(solicitud.getNombreSolicitante());
        if(solicitud.getFechaLimite()!=null)
        	solicitudDTO.setFechaLimite(solicitud.getFechaLimite());
        
        
		valor = solicitud.getTipoSolicitud();
		if (valor != null) {
			valorDTO = new ValorDTO(valor.getValorId(), valor.getValor());
			solicitudDTO.setTipoSolicitudDTO(valorDTO);
		}

		Set<AcuseRecibo> acuces = solicitud.getAcuseRecibos();
		if (acuces != null && !acuces.isEmpty()) {
			solicitudDTO.setFechaCierre(acuces.iterator().next()
					.getFechaAcuse());
		}

		// Instituci�n solicitante
		if (solicitud.getConfInstitucion() != null) {
			solicitudDTO.setInstitucion(ConfInstitucionTransformer.transformarInstitucion(solicitud.getConfInstitucion()));
			solicitudDTO.setNombreInstitucionSolicitante(solicitud.getConfInstitucion().getNombreInst());
		}

		// Nombre del solicitante
		solicitudDTO.setNombreSolicitanteExternoInterno(solicitud
				.getNombreSolicitante());

		// Estado
		valor = solicitud.getEstatus();
		if (valor != null) {
			valorDTO = new ValorDTO(valor.getValorId(), valor.getValor());
			solicitudDTO.setEstatus(valorDTO);
		}
		

		// N�mero de causa
		// N�mero de TOCA (si existe) (Nota 2)
		// Identificador de Audiencia (si existe) (Nota 2)
		if (solicitud.getNumeroExpediente() != null) {
			ExpedienteDTO expedienteDTO = ExpedienteTransformer
					.transformaExpediente(solicitud.getNumeroExpediente().getExpediente());
			expedienteDTO.setNumeroExpedienteId(solicitud.getNumeroExpediente().getNumeroExpedienteId());
			if(solicitud.getExpediente().getNumeroExpediente()!=null)
				expedienteDTO.setNumeroExpediente(solicitud.getExpediente().getNumeroExpediente());
			else
				expedienteDTO.setNumeroExpediente(solicitud.getNumeroExpediente().getNumeroExpediente());
			solicitudDTO.setExpedienteDTO(expedienteDTO);
		}

		Set<AcuseRecibo> acuses = solicitud.getAcuseRecibos();
		List<AcuseReciboDTO> acusesReciboDTO = new ArrayList<AcuseReciboDTO>();
		for (AcuseRecibo acuseRecibo : acuses) {
			acusesReciboDTO.add(AcuseTransformer.transformarDTO(acuseRecibo));
		}
		solicitudDTO.setAcusesReciboDTO(acusesReciboDTO);

		if(solicitud.getArchivosAdjuntos()!= null && !solicitud.getArchivosAdjuntos().isEmpty()){
			SolicitudAdjuntosDTO archivoDTO = null;
			SolicitudAdjuntosIdDTO idDTO = null;
			for(SolicitudAdjuntos archivo : solicitud.getArchivosAdjuntos()){
				archivoDTO = new SolicitudAdjuntosDTO();
				archivoDTO.setArchivoDigital(ArchivoDigitalTransformer.transformarArchivoDigital(archivo.getArchivoDigital()));
				archivoDTO.setSolicitud(solicitudDTO);
				idDTO = new SolicitudAdjuntosIdDTO();
				idDTO.setArchivoDigitalId(archivo.getId().getArchivoDigitalId());
				idDTO.setSolicitudId(archivo.getId().getSolicitudId());
				archivoDTO.setId(idDTO);
				solicitudDTO.getArchivosAdjuntos().add(archivoDTO);
			}
		}
		
		if(solicitud.getDestinatario() != null)
			solicitudDTO.setDestinatario(FuncionarioTransformer.transformarFuncionario(solicitud.getDestinatario()));
		
		if(solicitud.getFuncionarioSolicitante()!= null)
			solicitudDTO.setUsuarioSolicitante(FuncionarioTransformer.transformarFuncionario(solicitud.getFuncionarioSolicitante()));
		
		solicitudDTO.setEsUrgente(solicitud.getEsUrgente()==null?false:solicitud.getEsUrgente() );
		
		solicitudDTO.setMotivo(solicitud.getMotivo());
		
		solicitudDTO.setAsuntoSolicitud(solicitud.getAsuntoSolicitud());
		solicitudDTO.setObservaciones(solicitud.getObservaciones());
		
		solicitudDTO.setNumeroCasoAsociado(solicitud.getNumeroCasoAsociado());
		
		if (solicitud.getInvolucradoSolicitante()!=null) 
			solicitudDTO.setInvolucradoDTO(InvolucradoTransformer.transformarInvolucradoBasico(solicitud.getInvolucradoSolicitante()));
		
		if( solicitud.getResponsableDocumento()!= null)
			solicitudDTO.setResponsableDocumento(FuncionarioTransformer.transformarFuncionario(solicitud.getResponsableDocumento()));
		
		solicitudDTO.setAreaOrigen(solicitud.getAreaOrigen());
		solicitudDTO.setAreaDestino(solicitud.getAreaDestino());
		
		if( solicitud.getFuncionarioSolExt() != null ){
			solicitudDTO.setSolicitanteInstExterna(FuncionarioExternoTransformer.transformar(solicitud.getFuncionarioSolExt()));
		}
		
		if (solicitud.getFuncionarioDestExt() != null ){
			solicitudDTO.setDestinatarioInstExterna(FuncionarioExternoTransformer.transformar(solicitud.getFuncionarioDestExt()));
		}
		
		return solicitudDTO;

	}

	public static SolicitudMandamientoDTO solicitudTransformer(
			SolicitudMandamiento solicitud){
		SolicitudMandamientoDTO solicitudDTO = new SolicitudMandamientoDTO();
		Valor valor;
		ValorDTO valorDTO;

		// Solicitud
		solicitudDTO.setFolioSolicitud(solicitud.getFolioSolicitud());
		solicitudDTO.setDocumentoId(solicitud.getDocumentoId());
		solicitudDTO.setFechaCreacion(solicitud.getFechaCreacion());
		solicitudDTO.setStrFechaCreacion(DateUtils.formatear(solicitud.getFechaCreacion()));
		solicitudDTO.setStrHoraCreacion(DateUtils.formatearHora(solicitud.getFechaCreacion()));
        
		valor = solicitud.getTipoMandamiento();
		if (valor != null) {
			TipoMandamiento tm = TipoMandamiento.getByValor(valor.getValorId());
			valorDTO = new ValorDTO(tm.getValorId(), tm.name());
			solicitudDTO.setTipoMandamiento(valorDTO);
		}
		
		valor = solicitud.getTipoSolicitud();
		if (valor != null) {
			valorDTO = new ValorDTO(valor.getValorId(), valor.getValor());
			solicitudDTO.setTipoSolicitudDTO(valorDTO);
		}

		Set<AcuseRecibo> acuces = solicitud.getAcuseRecibos();
		if (acuces != null && !acuces.isEmpty()) {
			solicitudDTO.setFechaCierre(acuces.iterator().next()
					.getFechaAcuse());
		}

		// Instituci�n solicitante
		if (solicitud.getConfInstitucion() != null) {
			solicitudDTO.setInstitucion(ConfInstitucionTransformer.transformarInstitucion(solicitud.getConfInstitucion()));
			solicitudDTO.setNombreInstitucionSolicitante(solicitud.getConfInstitucion().getNombreInst());
		}

		// Nombre del solicitante
		solicitudDTO.setNombreSolicitanteExternoInterno(solicitud
				.getNombreSolicitante());

		// Estado
		valor = solicitud.getEstatus();
		if (valor != null) {
			valorDTO = new ValorDTO(valor.getValorId(), valor.getValor());
			solicitudDTO.setEstatus(valorDTO);
		}

		// N�mero de causa
		// N�mero de TOCA (si existe) (Nota 2)
		// Identificador de Audiencia (si existe) (Nota 2)
		if (solicitud.getExpediente() != null) {
			ExpedienteDTO expedienteDTO = ExpedienteTransformer
					.transformaExpediente(solicitud.getNumeroExpediente().getExpediente());
			expedienteDTO.setNumeroExpedienteId(solicitud.getNumeroExpediente().getNumeroExpedienteId());
			solicitudDTO.setExpedienteDTO(expedienteDTO);
		}

		Set<AcuseRecibo> acuses = solicitud.getAcuseRecibos();
		List<AcuseReciboDTO> acusesReciboDTO = new ArrayList<AcuseReciboDTO>();
		for (AcuseRecibo acuseRecibo : acuses) {
			acusesReciboDTO.add(AcuseTransformer.transformarDTO(acuseRecibo));
		}
		solicitudDTO.setAcusesReciboDTO(acusesReciboDTO);

		if(solicitud.getArchivosAdjuntos()!= null && !solicitud.getArchivosAdjuntos().isEmpty()){
			SolicitudAdjuntosDTO archivoDTO = null;
			SolicitudAdjuntosIdDTO idDTO = null;
			for(SolicitudAdjuntos archivo : solicitud.getArchivosAdjuntos()){
				archivoDTO = new SolicitudAdjuntosDTO();
				archivoDTO.setArchivoDigital(ArchivoDigitalTransformer.transformarArchivoDigital(archivo.getArchivoDigital()));
				archivoDTO.setSolicitud(solicitudDTO);
				idDTO = new SolicitudAdjuntosIdDTO();
				idDTO.setArchivoDigitalId(archivo.getId().getArchivoDigitalId());
				idDTO.setSolicitudId(archivo.getId().getSolicitudId());
				archivoDTO.setId(idDTO);
				solicitudDTO.getArchivosAdjuntos().add(archivoDTO);
			}
		}
		
		return solicitudDTO;

	}
	
	public static SolicitudAudienciaDTO solicitudTranscripcionTransformer(
			SolicitudAudiencia solicitud) {
		SolicitudAudienciaDTO solicitudDTO = new SolicitudAudienciaDTO();
		Valor valor;
		ValorDTO valorDTO;

		// Solicitud
		solicitudDTO.setDocumentoId(solicitud.getDocumentoId());
		solicitudDTO.setFolioSolicitud(solicitud.getFolioSolicitud());

		valor = solicitud.getTipoSolicitud();
		if (valor != null) {
			valorDTO = new ValorDTO(valor.getValorId(), valor.getValor());
			solicitudDTO.setTipoSolicitudDTO(valorDTO);
		}

		// Fecha - hora solicitud (Nota 1)
		solicitudDTO.setFechaCreacion(solicitud.getFechaCreacion());
		// Fecha - hora de entrega (si existe)

		Set<AcuseRecibo> acuces = solicitud.getAcuseRecibos();
		if (acuces != null && !acuces.isEmpty()) {
			solicitudDTO.setFechaCierre(acuces.iterator().next()
					.getFechaAcuse());
		}

		// Instituci�n solicitante
		if (solicitud.getConfInstitucion() != null) {
			solicitudDTO.setInstitucion(ConfInstitucionTransformer
					.transformarInstitucion(solicitud.getConfInstitucion()));
			solicitudDTO.setNombreInstitucionSolicitante(solicitud
					.getConfInstitucion().getNombreInst());
		}

		// Nombre del solicitante
		solicitudDTO.setNombreSolicitanteExternoInterno(solicitud
				.getNombreSolicitante());

		// Estado
		valor = solicitud.getEstatus();
		if (valor != null) {
			valorDTO = new ValorDTO(valor.getValorId(), valor.getValor());
			solicitudDTO.setEstatus(valorDTO);
		}

		// N�mero de causa
		// N�mero de TOCA (si existe) (Nota 2)
		// Identificador de Audiencia (si existe) (Nota 2)
		if (solicitud.getExpediente() != null) {
			ExpedienteDTO expedienteDTO = ExpedienteTransformer
					.transformaExpediente(solicitud.getExpediente());
			solicitudDTO.setExpedienteDTO(expedienteDTO);
		}

		Set<AcuseRecibo> acuses = solicitud.getAcuseRecibos();
		List<AcuseReciboDTO> acusesReciboDTO = new ArrayList<AcuseReciboDTO>();
		for (AcuseRecibo acuseRecibo : acuses) {
			acusesReciboDTO.add(AcuseTransformer.transformarDTO(acuseRecibo));
		}
		solicitudDTO.setAcusesReciboDTO(acusesReciboDTO);
		solicitudDTO.setAudiencia(EventoTransformer.transformarAudienciaBasico(solicitud.getAudiencia()));
		
		return solicitudDTO;
	}

	/**
	 * 
	 * @param solicitud
	 * @return
	 */
	public static SolicitudDTO solicitudTransformerBasico (Solicitud solicitud) {
		SolicitudDTO solicitudDTO = new SolicitudDTO();
		
		solicitudDTO.setDocumentoId(solicitud.getDocumentoId());
		solicitudDTO.setFolioSolicitud(solicitud.getFolioSolicitud());
		solicitudDTO.setNombreSolicitante(solicitud.getNombreSolicitante());		
		solicitudDTO.setFechaCreacion(solicitud.getFechaCreacion());
		solicitudDTO.setStrFechaCreacion(DateUtils.formatear(solicitud.getFechaCreacion()));
		solicitudDTO.setStrHoraCreacion(DateUtils.formatearHora(solicitud.getFechaCreacion()));
		solicitudDTO.setSolicitanteExterno(solicitud.getSolicitanteExterno());
		solicitudDTO.setNombreDocumento(solicitud.getNombreDocumento());
		if(solicitud.getFechaLimite()!=null)
			solicitudDTO.setFechaLimite(solicitud.getFechaLimite());
		if (solicitud.getNumeroExpediente()!=null) {
			solicitudDTO.setExpedienteDTO(ExpedienteTransformer.transformarExpedienteBasico(solicitud.getNumeroExpediente()));
		}
		if (solicitud.getConfInstitucion()!=null){
			solicitudDTO.setNombreInstitucionSolicitante(solicitud.getConfInstitucion().getNombreInst());		
		}
		
		return solicitudDTO;
	}

	public static Solicitud solicitudWSTransformerBasico(
			SolicitudAudienciaBasicoWSDTO solBasicoWSDTO) {
		Solicitud solicitud = new Solicitud();
		
		solicitud.setAreaOrigen(solBasicoWSDTO.getAreaSolicitanteId());
		solicitud.setNombreSolicitante(solBasicoWSDTO.getNombreSolicitante());
		solicitud.setFechaLimite(solBasicoWSDTO.getFechaLimite());
		solicitud.setNumeroCasoAsociado(solBasicoWSDTO.getNumeroCasoAsociado());
		solicitud.setSolicitanteExterno(solBasicoWSDTO.getSolicitanteExternoId());
					
		return solicitud;
	}

	public static SolicitudAudiencia solicitudTransformer(
			SolicitudBeneficiosPreliberacionWSDTO solBeneficiosPreliberacion) {
		SolicitudAudiencia solicitud = new SolicitudAudiencia();
		
		//Solicitud
		solicitud.setAreaOrigen(solBeneficiosPreliberacion.getAreaSolicitanteId());
		solicitud.setNombreSolicitante(solBeneficiosPreliberacion.getNombreSolicitante());
		solicitud.setFechaLimite(solBeneficiosPreliberacion.getFechaLimite());
		solicitud.setNumeroCasoAsociado(solBeneficiosPreliberacion.getNumeroCasoAsociado());
		solicitud.setSolicitanteExterno(solBeneficiosPreliberacion.getSolicitanteExternoId());		
		solicitud.setConfInstitucion(new ConfInstitucion(solBeneficiosPreliberacion.getConfInstitucionId()));
		solicitud.setFolioSolicitud(solBeneficiosPreliberacion.getFolioSolicitud());
//		solicitud.setFolioDocumento(solBeneficiosPreliberacion.getFolioSolicitud());
				
		//Solicitud Audiecia		
		
		return solicitud;
	}

	public static SolicitudTrasladoImputado solicitudTrasladoImputadoTransformer(
			SolicitudTrasladoImputadoDTO solicitudTrasladoImputadoDTO) {

		SolicitudTrasladoImputado solTraslado = new SolicitudTrasladoImputado();
		Involucrado involucrado = new Involucrado();
		
		//SolicitudTraslado
		solTraslado.setAudiencia(new Audiencia(solicitudTrasladoImputadoDTO.getAudiencia().getId()));
		involucrado.setElementoId(solicitudTrasladoImputadoDTO.getInvolucrado().getElementoId());
		solTraslado.setInvolucrado(involucrado);
		
		//Solicitud
		if (solicitudTrasladoImputadoDTO.getExpedienteDTO() != null)
			solTraslado.setNumeroExpediente(new NumeroExpediente(solicitudTrasladoImputadoDTO.getExpedienteDTO().getNumeroExpedienteId()));
//		if (solicitudTrasladoImputadoDTO.getTipoSolicitudDTO() != null)
//			solTraslado.setTipoSolicitud(new Valor(solicitudTrasladoImputadoDTO.getTipoSolicitudDTO().getIdCampo()));
		if (solicitudTrasladoImputadoDTO.getUsuarioSolicitante() != null)
			solTraslado.setFuncionarioSolicitante(new Funcionario(solicitudTrasladoImputadoDTO.getUsuarioSolicitante().getClaveFuncionario()));
		if (solicitudTrasladoImputadoDTO.getInstitucion() != null)
			solTraslado.setConfInstitucion(new ConfInstitucion(solicitudTrasladoImputadoDTO.getInstitucion().getConfInstitucionId()));
//		if (solicitudTrasladoImputadoDTO.getEstatus()!=null)
//			solTraslado.setEstatus(new Valor(solicitudTrasladoImputadoDTO.getEstatus().getIdCampo()));
		if (solicitudTrasladoImputadoDTO.getDestinatario()!=null)
			solTraslado.setDestinatario(new Funcionario(solicitudTrasladoImputadoDTO.getDestinatario().getClaveFuncionario()));
			
		solTraslado.setNumeroCasoAsociado(solicitudTrasladoImputadoDTO.getNumeroCasoAsociado());
		solTraslado.setMotivo(solicitudTrasladoImputadoDTO.getMotivo());
		solTraslado.setFechaLimite(solicitudTrasladoImputadoDTO.getFechaLimite());
		solTraslado.setFechaModificacion(solicitudTrasladoImputadoDTO.getFechaModificacion());
		solTraslado.setFechaCierre(solicitudTrasladoImputadoDTO.getFechaCierre());
		solTraslado.setNombreSolicitante(solicitudTrasladoImputadoDTO.getNombreSolicitante());
		solTraslado.setEsUrgente(solicitudTrasladoImputadoDTO.getEsUrgente()==null?false:solicitudTrasladoImputadoDTO.getEsUrgente());
		solTraslado.setFolioSolicitud(solicitudTrasladoImputadoDTO.getFolioSolicitud());
		
		// Documento
//		if (solicitudTrasladoImputadoDTO.getTipoDocumentoDTO() != null)
//			solTraslado.setTipoDocumento(new Valor(solicitudTrasladoImputadoDTO.getTipoDocumentoDTO().getIdCampo()));
//		if (solicitudTrasladoImputadoDTO.getFormaDTO() != null)
//			solTraslado.setForma(new Forma(solicitudTrasladoImputadoDTO.getFormaDTO().getFormaId()));

//		solTraslado.setFechaCreacion(solicitudTrasladoImputadoDTO.getFechaCreacion());
//		solTraslado.setNombreDocumento(solicitudTrasladoImputadoDTO.getNombreDocumento());
//		solTraslado.setDocumentoId(solicitudTrasladoImputadoDTO.getDocumentoId());
		if(solicitudTrasladoImputadoDTO.getDestinatario() != null){
			solTraslado.setDestinatario(FuncionarioTransformer.transformarFuncionario(solicitudTrasladoImputadoDTO.getDestinatario()));
		}
		solTraslado.setAsuntoSolicitud(solicitudTrasladoImputadoDTO.getAsuntoSolicitud());
		solTraslado.setObservaciones(solicitudTrasladoImputadoDTO.getObservaciones());
		
		solTraslado.setAreaOrigen(solicitudTrasladoImputadoDTO.getAreaOrigen());
		solTraslado.setAreaDestino(solicitudTrasladoImputadoDTO.getAreaDestino());
		if(solicitudTrasladoImputadoDTO.getFechaTraslado()!= null)
			solTraslado.setFechaTraslado(solicitudTrasladoImputadoDTO.getFechaTraslado());
		else
			solTraslado.setFechaTraslado(new Date());
		
		solTraslado.setTiempo(solicitudTrasladoImputadoDTO.getTiempo());
		
		return solTraslado;
	}

	public static SolicitudTrasladoImputadoDTO solicitudTrasladoImputadoTransformer(
			SolicitudTrasladoImputado input) {
		SolicitudTrasladoImputadoDTO solTrasladoDTO = new SolicitudTrasladoImputadoDTO();
		
		//SolicitudTraslado
		solTrasladoDTO.setAudiencia(AudienciaTransformer.transformarDTO(input.getAudiencia()));
		solTrasladoDTO.setInvolucrado(InvolucradoTransformer.transformarInvolucradoBasico(input.getInvolucrado()));		
		
		//Solicitud
		if (input.getNumeroExpediente()!=null)
			solTrasladoDTO.setExpedienteDTO(ExpedienteTransformer.transformarExpedienteBasico(input.getNumeroExpediente()));				
		if (input.getTipoSolicitud() != null)
			solTrasladoDTO.setTipoSolicitudDTO(new ValorDTO(input.getTipoSolicitud().getValorId(), input.getTipoSolicitud().getValor()));		
		if (input.getSolicitanteExterno()!= null)
			solTrasladoDTO.setSolicitanteExterno(input.getSolicitanteExterno());		
		if (input.getConfInstitucion() != null)
			solTrasladoDTO.setConfInstitucion(ConfInstitucionTransformer.transformarInstitucion(input.getConfInstitucion()));
		if (input.getEstatus()!=null)
			solTrasladoDTO.setEstatus(new ValorDTO(input.getEstatus().getValorId(), input.getEstatus().getValor()));

		solTrasladoDTO.setNumeroCasoAsociado(input.getNumeroCasoAsociado());
		solTrasladoDTO.setMotivo(input.getMotivo());
		solTrasladoDTO.setFechaLimite(input.getFechaLimite());
		solTrasladoDTO.setFechaModificacion(input.getFechaModificacion());
		solTrasladoDTO.setFechaCierre(input.getFechaCierre());
		solTrasladoDTO.setNombreSolicitante(input.getNombreSolicitante());
		solTrasladoDTO.setEsUrgente(input.getEsUrgente());
		solTrasladoDTO.setFolioSolicitud(input.getFolioSolicitud());
		
		// Documento
//		if (solicitudTrasladoImputadoDTO.getTipoDocumentoDTO() != null)
//			solTraslado.setTipoDocumento(new Valor(solicitudTrasladoImputadoDTO.getTipoDocumentoDTO().getIdCampo()));
//		if (solicitudTrasladoImputadoDTO.getFormaDTO() != null)
//			solTraslado.setForma(new Forma(solicitudTrasladoImputadoDTO.getFormaDTO().getFormaId()));

		solTrasladoDTO.setFechaCreacion(input.getFechaCreacion());
		solTrasladoDTO.setNombreDocumento(input.getNombreDocumento());
		solTrasladoDTO.setDocumentoId(input.getDocumentoId());
		if(input.getDestinatario() != null){
			solTrasladoDTO.setDestinatario(FuncionarioTransformer.transformarFuncionario(input.getDestinatario()));
		}
		solTrasladoDTO.setAsuntoSolicitud(input.getAsuntoSolicitud());
		solTrasladoDTO.setObservaciones(input.getObservaciones());
		
		solTrasladoDTO.setAreaOrigen(input.getAreaOrigen());
		solTrasladoDTO.setAreaDestino(input.getAreaDestino());
		
		return solTrasladoDTO;
	}
	
	/**
	 * M&acutee;todo que asigna el documento enviado a la solicitud enviada.
	 * @param documento que se agregara a la solicitud.
	 * @param solicitud a la que se le agrega el documento.
	 * @return solicitud con el documento
	 */
	public static Solicitud combinarDocumentoConSolicitud(Documento documento, Solicitud solicitud) {
		
		solicitud.setDocumentoId(documento.getDocumentoId());
		solicitud.setFolioDocumento(documento.getFolioDocumento());
		solicitud.setNombreDocumento(documento.getNombreDocumento());
		solicitud.setFechaCreacion(documento.getFechaCreacion());
		solicitud.setVersion(documento.getVersion());
		solicitud.setNumeroFojas(documento.getNumeroFojas());
		solicitud.setResponsableDocumento(documento.getResponsableDocumento());
		solicitud.setOrigenDocumento(documento.getOrigenDocumento());
		solicitud.setTipoDocumento(documento.getTipoDocumento());
		solicitud.setForma(documento.getForma());
		solicitud.setArchivoDigital(documento.getArchivoDigital());
		solicitud.setTextoParcial(documento.getTextoParcial());
		solicitud.setResponsableDocumento(documento.getResponsableDocumento());
		solicitud.setEsEnviado(documento.getEsEnviado());
		solicitud.setEsModificable(documento.getEsModificable());
		solicitud.setConfInstitucion(documento.getConfInstitucion());
		solicitud.setEsCompartido(documento.getEsCompartido());
		solicitud.setCatDiscriminanteOrigen(documento.getCatDiscriminanteOrigen());
		solicitud.setClaveDiscriminanteOrigen(documento.getClaveDiscriminanteOrigen());
		solicitud.setIdFuncionarioSolicitante(documento.getIdFuncionarioSolicitante());
		solicitud.setEsGuardadoParcial(documento.getEsGuardadoParcial());
		solicitud.setDescripcion(documento.getDescripcion()); 
		return solicitud;
	}
	
	
	
}
