/**
 * Nombre del Programa : EventoTransformer.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 31 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Transformador para eventos
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
package mx.gob.segob.nsjp.service.audiencia.impl.transform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.audiencia.Eventos;
import mx.gob.segob.nsjp.comun.enums.documento.Notificaciones;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.EventoDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.IntentoDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Detencion;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.Intento;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.model.Resolutivo;
import mx.gob.segob.nsjp.model.SolicitudAudiencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.DetencionTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;

/**
 * Transformador para eventos.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class EventoTransformer {

    /**
     * 
     */
    private final static Logger logger = Logger.getLogger(EventoTransformer.class);
    
    public static List<EventoDTO> tranformarAudienciasMaestro(
            List<Audiencia> input) {
        final List<EventoDTO> resp = new ArrayList<EventoDTO>();
        for (Audiencia row : input) {
            resp.add(EventoTransformer.tranformarAudienciaMaestro(row));
        }
        return resp;
    }

    /**
     * @param resp
     * @param row
     */
    public static EventoDTO tranformarAudienciaMaestro(Audiencia row) {
        final EventoDTO dto = new EventoDTO();
        dto.setFechaEvento(row.getFechaAudiencia());
        if(row.getSolicitud() != null){
        	dto.setFechaSolicitud(row.getSolicitud().getFechaCreacion());
        }
        dto.setId(row.getAudienciaId());
        dto.setTipoEvento(Eventos.AUDENCIA);
        dto.setTipo(EventoTransformer.transformValor(row.getTipo()));
        final Expediente expTemp = row.getExpediente();
        final ExpedienteDTO expDto = new ExpedienteDTO();
        if(expTemp != null){
        	expDto.setExpedienteId(expTemp.getExpedienteId());
        }        
        if(row.getNumeroExpediente() != null){
	        expDto.setNumeroExpediente(row.getNumeroExpediente().getNumeroExpediente());
	        expDto.setNumeroExpedienteId(row.getNumeroExpediente()
	                .getNumeroExpedienteId());
        }
        dto.setExpediente(expDto);
        final SalaAudienciaDTO sal = new SalaAudienciaDTO();
        if (row.getSalaAudiencia() != null) {
            dto.setUbicacionEvento(row.getSalaAudiencia().getNombreSala());
            dto.setLugarEvento(row.getSalaAudiencia().getDomicilioSala());
        }

        if (row.getSalaTemporal() != null) {
            dto.setUbicacionEvento(row.getSalaTemporal().getUbicacionSala());
            dto.setLugarEvento(row.getSalaTemporal().getDomicilioSala());
        }
        
        if(row.getEstatus() != null)
        	dto.setEstatusAudiencia(new ValorDTO(row.getEstatus().getValorId(), row.getEstatus().getValor()));

        return dto;
    }

    /**
     * 
     * @param in
     * @return
     */
    public static ValorDTO transformValor(Valor in) {
        if (in == null) {
            return null;
        }
        final ValorDTO out = new ValorDTO(in.getValorId(), in.getValor());
        return out;
    }

    /**
     * 
     * @param notis
     * @return
     */
    public static List<NotificacionDTO> transformarNotificaciones(
            Collection<Notificacion> notis) {

        final List<NotificacionDTO> resp = new ArrayList<NotificacionDTO>();

        for (Notificacion row : notis) {
            resp.add(EventoTransformer.transformarNotificacion(row));
        }

        return resp;
    }

    /**
     * 
     * @param row
     * @return
     */
    private static NotificacionDTO transformarNotificacion(Notificacion row) {
        if (row == null) {
            return null;
        }
        final NotificacionDTO dto = new NotificacionDTO();

        dto.setConsecutivoNotificacion(row.getConsecutivoNotificacion());
        dto.setDocumentoId(row.getDocumentoId());
        dto.setDomicilio(row.getDomicilio());
        dto.setFechaCitado(row.getFechaCitado());
        dto.setFechaCreacion(row.getFechaCreacion());
        dto.setFolioDocumento(row.getFolioDocumento());
        dto.setLugar(row.getLugar());
        dto.setLugarCitado(row.getLugarCitado());
        dto.setMotivo(row.getMotivo());
        dto.setNombreDocumento(row.getNombreDocumento());
        dto.setPenalidades(row.getPenalidades());
        dto.setPersonaAutoriza(row.getPersonaAutoriza());
        dto.setPuestoAutoriza(row.getPuestoAutoriza());
        if(row.getEsFisica() != null && row.getEsFisica()){
        	dto.setTipo(Notificaciones.FISICA);
        }else{
        	dto.setTipo(Notificaciones.ELECTRONICA);
        }
        if (row.getArchivoDigital() != null) {
            dto.setArchivoDigital(new ArchivoDigitalDTO(row.getArchivoDigital()
                    .getArchivoDigitalId()));
        }
        dto.setIntentos(EventoTransformer.transformarIntentos(row.getIntentos()));

        return dto;
    }

    /**
     * 
     * @param intentos
     * @return
     */
    private static List<IntentoDTO> transformarIntentos(
            Collection<Intento> intentos) {

        final List<IntentoDTO> resp = new ArrayList<IntentoDTO>();

        for (Intento row : intentos) {
            resp.add(EventoTransformer.transformarIntento(row));
        }

        return resp;
    }

    /**
     * 
     * @param row
     * @return
     */
    private static IntentoDTO transformarIntento(Intento row) {
        if (row == null) {
            return null;
        }
        final IntentoDTO out = new IntentoDTO();
        out.setConsecutivoIntento(row.getConsecutivoIntento());
        out.setFechaIntento(row.getFechaIntento());
        out.setIntentoId(row.getIntentoId());
        return out;
    }

    
    /**
     * Método que tranforma los campos mínimos de la audiencia.
     * @see transformarAudienciaCompleto
     * @param in
     * @return
     */
    public static AudienciaDTO transformarAudienciaBasico(Audiencia in) {
        
        if (in == null) {
            return null;
        }
        logger.debug("in.getAudienciaId() "+ in.getAudienciaId());
        final AudienciaDTO dto = new AudienciaDTO();
        dto.setId(in.getAudienciaId());
        if (in.getSalaAudiencia() != null) {
            dto.setDomicilioEvento(in.getSalaAudiencia().getDomicilioSala());
            dto.setUbicacionEvento(in.getSalaAudiencia().getUbicacionSala());
            SalaAudienciaDTO sal = new SalaAudienciaDTO();
            sal.setDomicilioSala(in.getSalaAudiencia().getDomicilioSala());
            sal.setNombreSala(in.getSalaAudiencia().getNombreSala());
            sal.setUbicacionSala(in.getSalaAudiencia().getUbicacionSala());
            dto.setSala(sal);
        }

        if (in.getSalaTemporal() != null) {
            dto.setDomicilioEvento(in.getSalaTemporal().getDomicilioSala());
            dto.setUbicacionEvento(in.getSalaTemporal().getUbicacionSala());
            SalaAudienciaDTO sal = new SalaAudienciaDTO();
            sal.setDomicilioSala(in.getSalaTemporal().getDomicilioSala());
            sal.setUbicacionSala(in.getSalaTemporal().getUbicacionSala());
            sal.setNombreSala(in.getSalaTemporal().getUbicacionSala());
            dto.setSala(sal);
        }

        Valor tipoAud = in.getTipo();
        dto.setTipoAudiencia(transformValor(tipoAud));

        for (Valor ext : tipoAud.getRegistro().getValors()) {
            if (ext.getCampoCatalogo().getNombreCampo().equals("Carácter")) {
                dto.setCaracter(ext.getValor());
                break;
            }
        }
        if (in.getNumeroExpediente()!=null){
            Expediente expediente = in.getNumeroExpediente().getExpediente();
            Expediente expedienteP = null;
            if(in.getNumeroExpediente().getNumeroExpedientePadre() != null){
            	expedienteP = in.getNumeroExpediente().getNumeroExpedientePadre().getExpediente();
            }
            
            ExpedienteDTO expedienteDTO = ExpedienteTransformer.transformarExpedienteBasico(expediente);
            if(expedienteP != null){
            	expedienteDTO.setCausaPadre(ExpedienteTransformer.transformarExpedienteBasico(expedienteP));
            }
            
            dto.setExpediente(expedienteDTO);
            dto.getExpediente().setNumeroExpediente(in.getNumeroExpediente().getNumeroExpediente());
            dto.getExpediente().setNumeroExpedienteId(in.getNumeroExpediente().getNumeroExpedienteId());
        }
        
        dto.setFechaAsignacionSala(in.getFechaAsignacionSala());
        dto.setFechaEvento(in.getFechaAudiencia());
        dto.setDuracionEstimada(in.getDuracionEstimada());
        dto.setFechaHoraFin(in.getFechaHoraFin());
        dto.setStrFechaInicio(DateUtils.formatear(in.getFechaAudiencia()));
        dto.setStrHoraInicio(DateUtils.formatearHora(in.getFechaAudiencia()));
        dto.setStrFechaFin(DateUtils.formatear(in.getFechaHoraFin()));
        dto.setStrHoraFin(DateUtils.formatearHora(in.getFechaHoraFin()));
        dto.setEstatusAudiencia(new ValorDTO(in.getEstatus().getValorId(), in.getEstatus().getValor()));
        return dto;
    }
    
    /**
     * 
     * @param audiencia
     * @return
     */
    public static AudienciaDTO transformarAudienciaCompleto(Audiencia audiencia) {
        if (audiencia == null) {
            return null;
        }
        
        final AudienciaDTO audiencciaDTO = new AudienciaDTO();
        audiencciaDTO.setId(audiencia.getAudienciaId()); /*NO LO COMENTEN, LOS DEMAS ESTAMOS USANDO EL ESTANDART*/
        
        if(audiencia.getSalaAudiencia() != null) {
            audiencciaDTO.setDomicilioEvento(audiencia.getSalaAudiencia().getDomicilioSala());
            audiencciaDTO.setUbicacionEvento(audiencia.getSalaAudiencia().getUbicacionSala());
            SalaAudienciaDTO sal = new SalaAudienciaDTO();
	            sal.setDomicilioSala(audiencia.getSalaAudiencia().getDomicilioSala());
	            sal.setNombreSala(audiencia.getSalaAudiencia().getNombreSala());
	            sal.setUbicacionSala(audiencia.getSalaAudiencia().getUbicacionSala());
	            sal.setSalaAudienciaId(audiencia.getSalaAudiencia().getSalaAudienciaId());
            audiencciaDTO.setSala(sal);
        }
        
        audiencciaDTO.setEstatusAudiencia(new ValorDTO(audiencia.getEstatus().getValorId(), audiencia.getEstatus().getValor()));

        if (audiencia.getSalaTemporal() != null) {
            audiencciaDTO.setDomicilioEvento(audiencia.getSalaTemporal().getDomicilioSala());
            audiencciaDTO.setUbicacionEvento(audiencia.getSalaTemporal().getUbicacionSala());
            SalaAudienciaDTO sal = new SalaAudienciaDTO();
	            sal.setDomicilioSala(audiencia.getSalaTemporal().getDomicilioSala());
	            sal.setUbicacionSala(audiencia.getSalaTemporal().getUbicacionSala());
	            sal.setNombreSala(audiencia.getSalaTemporal().getUbicacionSala());
	            sal.setSalaAudienciaId(audiencia.getSalaTemporal().getSalaTemporalId());
            audiencciaDTO.setSala(sal);
        }
        
        if (audiencia.getFuncionarioAudiencias() != null) {
            FuncionarioDTO juezDTO = new FuncionarioDTO();
        	LinkedList<FuncionarioDTO> jueces = new LinkedList<FuncionarioDTO>(); 
        	for(FuncionarioAudiencia ja : audiencia.getFuncionarioAudiencias()){
        		if(ja.getFuncionario() != null){
        			juezDTO = FuncionarioTransformer.transformarFuncionario(ja.getFuncionario());
        		}
        		jueces.add(juezDTO);
        	}
            audiencciaDTO.setFuncionarios(jueces);
        }
        
        Valor tipoAud = audiencia.getTipo();
        audiencciaDTO.setTipoAudiencia(transformValor(tipoAud));

        for (Valor ext : tipoAud.getRegistro().getValors()) {
            if (ext.getCampoCatalogo().getNombreCampo().equals("Carácter")) {
                audiencciaDTO.setCaracter(ext.getValor());
                break;
            }
        }
        
        if(!audiencia.getInvlucradoAudiencias().isEmpty()){ 
        	InvolucradoDTO involucrDTO = null;
        	for(InvolucradoAudiencia ia : audiencia.getInvlucradoAudiencias()){
        		involucrDTO = InvolucradoTransformer.transformarInvolucradoBasico(ia.getInvolucrado());
        		if (BooleanUtils.isTrue(ia.getInvolucrado().getEsDetenido()) && ia.getInvolucrado().getDetencions()!=null) {
                    for (Detencion detencionPojo : ia.getInvolucrado()
                            .getDetencions()) {
                        involucrDTO.addDetencion(DetencionTransformer
                                .transformarDetencionBasico(detencionPojo));
                    }
        		}
        		audiencciaDTO.getInvolucrados().add(involucrDTO);
        	}
        }
        
        if(!audiencia.getResolutivos().isEmpty()){
    		Set<Resolutivo> resolutivos = audiencia.getResolutivos();
    		for (Resolutivo res : resolutivos) {
    			audiencciaDTO.getResolutivos().add(ResolutivoTransformer.transformarResolutivo(res));
    		}
        }

        if (audiencia.getNumeroExpediente()!=null){
            Expediente expediente = audiencia.getNumeroExpediente().getExpediente();
            Expediente expedienteP = null;
            if(audiencia.getNumeroExpediente().getNumeroExpedientePadre() != null){
            	expedienteP = audiencia.getNumeroExpediente().getNumeroExpedientePadre().getExpediente();
            }
            
            ExpedienteDTO expedienteDTO = ExpedienteTransformer.transformarExpedienteBasico(expediente);
            if(expedienteP != null){
            	expedienteDTO.setCausaPadre(ExpedienteTransformer.transformarExpedienteBasico(expedienteP));
            }
            
            audiencciaDTO.setExpediente(expedienteDTO);
            audiencciaDTO.getExpediente().setNumeroExpediente(audiencia.getNumeroExpediente().getNumeroExpediente());
            audiencciaDTO.getExpediente().setNumeroExpedienteId(audiencia.getNumeroExpediente().getNumeroExpedienteId());
        }
        audiencciaDTO.setFechaAsignacionSala(audiencia.getFechaAsignacionSala());
        audiencciaDTO.setFechaEvento(audiencia.getFechaAudiencia());
        audiencciaDTO.setDuracionEstimada(audiencia.getDuracionEstimada());
        audiencciaDTO.setFechaHoraFin(audiencia.getFechaHoraFin());
        return audiencciaDTO;
    }
    
    
    /**
     * Actualiza un objeto de Audiencia en base a los datos fuentes de un AudienciaDTO
     * @param dest
     * @param src
     */
    public static void tranformarAudienciaUpdateBasico(Audiencia dest, AudienciaDTO src){
    	if(dest == null || src == null){
    		return;
    	}
    	
    	dest.setFechaAsignacionSala(src.getFechaAsignacionSala());
    	dest.setDuracionEstimada(src.getDuracionEstimada());
    	dest.setFechaAudiencia(src.getFechaEvento());
    	dest.setFechaHoraFin(src.getFechaHoraFin());
    	
    	dest.setTipo(new Valor(src.getTipoAudiencia().getIdCampo()));
    	
    }
    
    /**
     * Transforma un objeto del tipo <code>SolicitudAudienciaBasicoWSDTO</code>
     * en su correspondiente objeto de entidad SolicitudAudiencia
     * @param src
     * @return
     */
    public static SolicitudAudiencia transformarSolicitudAudienciaBasico(SolicitudAudienciaBasicoWSDTO src){
    	SolicitudAudiencia dest = null;
    	if(src != null){
    		dest = new SolicitudAudiencia();
    		dest.setMotivo(src.getAudiencia().getMotivo());
    		dest.setFechaLimite(src.getFechaLimite());
    		dest.setAreaOrigen(src.getAreaSolicitanteId());
    		dest.setSolicitanteExterno(src.getSolicitanteExternoId());
    		dest.setNombreSolicitante(src.getNombreSolicitante());
    		dest.setFechaCreacion(new Date());
    		dest.setAudiencia(new Audiencia());
    		dest.getAudiencia().setFechaAudiencia(src.getAudiencia().getFechaHoraAudiencia());
    		dest.getAudiencia().setDuracionEstimada(src.getAudiencia().getDuracionEstimada());
    		dest.getAudiencia().setTipo(new Valor(src.getAudiencia().getTipoAudienciaId()));
    	}
    	
    	return dest;
    }    
}
