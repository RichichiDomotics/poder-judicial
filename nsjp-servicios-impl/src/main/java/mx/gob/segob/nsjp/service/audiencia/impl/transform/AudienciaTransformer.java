/**
 * Nombre del Programa : AudienciaTransformer.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 23/06/2011
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
package mx.gob.segob.nsjp.service.audiencia.impl.transform;

import java.util.LinkedList;
import java.util.Set;

import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDefensoriaWSDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaTemporalDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoAudienciaDefWSDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.DelitoPersona;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.SalaAudiencia;
import mx.gob.segob.nsjp.model.SalaTemporal;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatalogoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
public class AudienciaTransformer {

	public static AudienciaDTO transformarDTO(Audiencia aud) {
		AudienciaDTO dto = new AudienciaDTO();		
		if(aud.getNumeroExpediente() != null){
			dto.setExpediente(ExpedienteTransformer.transformarExpedienteBasico(aud.getNumeroExpediente()));
			
//			expediente.setNumeroExpedienteId(aud.getExpediente()
//					.getNumeroExpedienteId());
//			expediente.setNumeroExpediente(aud.getExpediente()
//					.getNumeroExpediente());
		}
		dto.setId(aud.getAudienciaId());
		dto.setFechaEvento(aud.getFechaAudiencia());
		dto.setTipoAudiencia(new ValorDTO(aud.getTipo().getValorId(),aud.getTipo().getValor()));
		dto.setSituacionesEspeciales(aud.getSituacionesEspeciales());
		dto.setCaracter(aud.getSolicitud()!=null && aud.getSolicitud().getEsUrgente()!=null? 
				        (aud.getSolicitud().getEsUrgente()?"Privada":"Pública"):"Pública");
		
		if(aud.getSalaAudiencia()!= null){
			SalaAudienciaDTO salaAudiencia = new SalaAudienciaDTO();
			salaAudiencia.setNombreSala(aud.getSalaAudiencia().getNombreSala());
			salaAudiencia.setDomicilioSala(aud.getSalaAudiencia().getDomicilioSala());
			salaAudiencia.setUbicacionSala(aud.getSalaAudiencia().getUbicacionSala());
			dto.setSala(salaAudiencia);			
		}else{
			if(aud.getSalaTemporal()!= null){
				
				SalaTemporalDTO salaTemporalDTO = new SalaTemporalDTO();
				
				if(aud.getSalaTemporal().getSalaTemporalId() != null){
					salaTemporalDTO.setSalaTemporalId(aud.getSalaTemporal().getSalaTemporalId());
				}
				if(aud.getSalaTemporal().getDomicilioSala() != null){
					salaTemporalDTO.setDomicilioSala(aud.getSalaTemporal().getDomicilioSala());
				}
				if(aud.getSalaTemporal().getUbicacionSala() != null){
					salaTemporalDTO.setUbicacionSala(aud.getSalaTemporal().getUbicacionSala());
				}
				if(aud.getSalaTemporal().getMotivo() != null){
					salaTemporalDTO.setMotivo(aud.getSalaTemporal().getMotivo());
				}
				
				dto.setSalaTemporal(salaTemporalDTO);
			}
		}
		
		dto.setDuracionEstimada(aud.getDuracionEstimada());
		dto.setFolioAudiencia(aud.getFolioAudiencia());
		
		return dto;
	}
	/**
	 * Transforma los datos de un objeto del tipo AudienciaDTO
	 * a un objeto entity de Audiencia
	 * @param src
	 * @return
	 */
	public static Audiencia transformarAudiencia(AudienciaDTO src) {
		if(src == null){
			return null;
		}
		Audiencia dest = new Audiencia();
		
		ExpedienteDTO expediente = new ExpedienteDTO();
		
		dest.setAudienciaId(src.getId());
		dest.setConsecutivo(src.getConsecutivo());
		dest.setDuracionEstimada(src.getDuracionEstimada());
		dest.setEstatus(CatalogoTransformer.transformValor(src.getEstatusAudiencia()));
		dest.setFechaAsignacionSala(src.getFechaAsignacionSala());
		dest.setFechaAudiencia(src.getFechaEvento());
		dest.setFechaHoraFin(src.getFechaHoraFin());
		dest.setTipo(CatalogoTransformer.transformValor(src.getTipoAudiencia()));
		dest.setSituacionesEspeciales(src.getSituacionesEspeciales());
		
		
		return dest;
	}

	public static Audiencia transformarEntity(AudienciaDTO dto) {
		Audiencia aud =new Audiencia();
		
		aud.setAudienciaId(dto.getId());
		
		return aud;
	}
	
	public static AudienciaDefensoriaWSDTO transformarWSDTOBasico(Audiencia entity) {
		AudienciaDefensoriaWSDTO wsdto = new AudienciaDefensoriaWSDTO();
		
		Caso caso = entity.getNumeroExpediente().getExpediente().getCaso();
		if (caso!=null) {
		    wsdto.setNumeroCaso(caso.getNumeroGeneralCaso());
		}
		wsdto.setAudienciaId(entity.getAudienciaId());
		wsdto.setTipoAudienciaId(entity.getTipo().getValorId());
		wsdto.setFechaAudiencia(entity.getFechaAudiencia());
		if(entity.getSolicitud()!=null && entity.getSolicitud().getEsUrgente() != null  && entity.getSolicitud().getEsUrgente()){
			wsdto.setCaracter("Privada");
		}else{
			wsdto.setCaracter("Pública");
		}
		
		if(entity.getSalaAudiencia()!=null){
			SalaAudiencia sala = entity.getSalaAudiencia();
			wsdto.setNombreSala(sala.getNombreSala());
			wsdto.setDomicilioSala(sala.getDomicilioSala());
			wsdto.setUbicacionSala(sala.getUbicacionSala());
		} else if(entity.getSalaTemporal()!=null){
			SalaTemporal sala = entity.getSalaTemporal();
			//En nombre de la sala temporal es considerado como Motivo 
			wsdto.setNombreSala(sala.getMotivo());
			wsdto.setDomicilioSala(sala.getDomicilioSala());
			wsdto.setUbicacionSala(sala.getUbicacionSala());
		}
		return wsdto;
	}
	
	public static AudienciaDefensoriaWSDTO transformarWSDTOCompleto(Audiencia entity) {
		AudienciaDefensoriaWSDTO wsdto = new AudienciaDefensoriaWSDTO();
		
		wsdto = transformarWSDTOBasico(entity);
		Set<InvolucradoAudiencia> involucrados = entity.getInvlucradoAudiencias();
		InvolucradoAudienciaDefWSDTO involucrado;
		for (InvolucradoAudiencia inv : involucrados) {
			if(wsdto.getInvolucrados() == null){
				wsdto.setInvolucrados(new LinkedList<InvolucradoAudienciaDefWSDTO>());
			}
			involucrado = new InvolucradoAudienciaDefWSDTO();
			Involucrado persona = inv.getInvolucrado();
			NombreDemografico nombre = persona.getNombreDemograficos().iterator().next();
			involucrado.setNombre(nombre.getNombre()+" "+nombre.getApellidoPaterno()+' '+nombre.getApellidoMaterno());
			involucrado.setCalidad(persona.getCalidad().getTipoCalidad().getValorId());
			Set<DelitoPersona> delitos = persona.getDelitosCometidos();
			if(delitos != null && !delitos.isEmpty()){
				DelitoPersona del = delitos.iterator().next();
				for (DelitoPersona delito : delitos) {
					if(delito.getDelito().getEsPrincipal()){
						del = delito;
					}
				}
				involucrado.setDelito(del.getDelito().getCatDelito().getNombre());
			}
			involucrado.setDetenido(persona.getEsDetenido() != null && persona.getEsDetenido());
			wsdto.getInvolucrados().add(involucrado);
		}
		return wsdto;
	}
	
	public static AudienciaDefensoriaWSDTO transformarWSDTOBasicoConCaracter(Audiencia entity) {
        AudienciaDefensoriaWSDTO wsdto = new AudienciaDefensoriaWSDTO();
        
        Caso caso = entity.getNumeroExpediente().getExpediente().getCaso();
        if (caso!=null) {
            wsdto.setNumeroCaso(caso.getNumeroGeneralCaso());
        }
        wsdto.setAudienciaId(entity.getAudienciaId());
        wsdto.setTipoAudienciaId(entity.getTipo().getValorId());
        wsdto.setFechaAudiencia(entity.getFechaAudiencia());
        
        
        Valor tipoAud = entity.getTipo();
    
    for (Valor ext : tipoAud.getRegistro().getValors()) {
        if (ext.getCampoCatalogo().getNombreCampo().equals("Carácter")) {
              wsdto.setCaracter(ext.getValor());
            break;
        }
    }
    
        if(entity.getSalaAudiencia()!=null){
              SalaAudiencia sala = entity.getSalaAudiencia();
              wsdto.setNombreSala(sala.getNombreSala());
              wsdto.setDomicilioSala(sala.getDomicilioSala());
              wsdto.setUbicacionSala(sala.getUbicacionSala());
        } else if(entity.getSalaTemporal()!=null){
              SalaTemporal sala = entity.getSalaTemporal();
              //En nombre de la sala temporal es considerado como Motivo 
              wsdto.setNombreSala(sala.getMotivo());
              wsdto.setDomicilioSala(sala.getDomicilioSala());
              wsdto.setUbicacionSala(sala.getUbicacionSala());
        }
        return wsdto;
  }
	
	public static AudienciaDTO transformarBasico(Audiencia aud) {
		AudienciaDTO dto = new AudienciaDTO();
		dto.setId(aud.getAudienciaId());
		dto.setFechaEvento(aud.getFechaAudiencia());
		dto.setTipoAudiencia(new ValorDTO(aud.getTipo().getValorId(),aud.getTipo().getValor()));
		dto.setFolioAudiencia(aud.getFolioAudiencia());
		return dto;
	}


}
