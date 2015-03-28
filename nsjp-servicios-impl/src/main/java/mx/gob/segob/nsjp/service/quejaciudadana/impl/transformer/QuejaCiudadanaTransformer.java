/**
 * 
 */
package mx.gob.segob.nsjp.service.quejaciudadana.impl.transformer;

import java.util.HashSet;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.quejaciudadana.EstatusQueja;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.dto.quejaciudadana.QuejaCiudadanaDTO;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.Implicado;
import mx.gob.segob.nsjp.model.MedioDeContacto;
import mx.gob.segob.nsjp.model.QuejaCiudadana;
import mx.gob.segob.nsjp.model.QuejaConfirmada;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.persona.impl.transform.MedioDeContactoTransformer;

/**
 * @author adrian
 *
 */
public class QuejaCiudadanaTransformer {

	public static QuejaCiudadana transformarQuejaDTO(QuejaCiudadanaDTO dto) {
		QuejaCiudadana queja=new QuejaCiudadana();
		Implicado quejoso = new Implicado();
		Implicado denunciado = new Implicado();
		Implicado afectado = new Implicado();
			
		Boolean esAfectado = false;
		
		queja.setFolioQueja(dto.getFolioQueja());         	
		queja.setFechaRegistro(dto.getFechaRegistro());      	
		queja.setDescripcionQueja(dto.getDescripcionQueja());		
		
		quejoso.setNombre(dto.getNombreQuejoso());
		quejoso.setApellidoPaterno(dto.getApellidoPatQuejoso());
		quejoso.setApellidoMaterno(dto.getApellidoMatQuejoso());
		if (dto.getCalidadQuejoso()!=null)
			quejoso.setTipoCalidad(new Valor(dto.getCalidadQuejoso().getIdCampo()));
		if (!dto.getMediosDenunciado().isEmpty())
		{
			Set<MedioDeContacto> mediosQuejoso = new HashSet<MedioDeContacto>();
			for (MedioDeContactoDTO medioDeContactoDTO : dto.getMediosDenunciado()) {
				if (medioDeContactoDTO instanceof TelefonoDTO) {
					Telefono telefono = MedioDeContactoTransformer.transformarTelefono((TelefonoDTO)medioDeContactoDTO);
					telefono.setImplicado(quejoso);
					mediosQuejoso.add(telefono);
				}
				if (medioDeContactoDTO instanceof CorreoElectronicoDTO) {
					CorreoElectronico correo = MedioDeContactoTransformer.transformarCorreo((CorreoElectronicoDTO)medioDeContactoDTO);
					correo.setImplicado(quejoso);
					mediosQuejoso.add(correo);					
				}
			}
			quejoso.setMedioDeContactos(mediosQuejoso);
		}
		queja.setQuejoso(quejoso);			
			
		denunciado.setNombre(dto.getNombreDenunciado());
		denunciado.setApellidoPaterno(dto.getApellidoPatDenunciado());
		denunciado.setApellidoMaterno(dto.getApellidoMatDenunciado());
		if (dto.getCalidadDenunciado()!=null)
			denunciado.setTipoCalidad(new Valor(dto.getCalidadDenunciado().getIdCampo()));
		queja.setDenunciado(denunciado);		
		
		if (dto.getNombreAfectado()!=null && dto.getNombreAfectado().length()>0) {
			afectado.setNombre(dto.getNombreAfectado());
			esAfectado=true;
		}
		if (dto.getApellidoPatAfectado()!=null && dto.getApellidoPatAfectado().length()>0) {
			afectado.setApellidoPaterno(dto.getApellidoPatAfectado());
			esAfectado=true;
		}
		if (dto.getApellidoMatAfectado()!=null && dto.getApellidoMatAfectado().length()>0) {
			afectado.setApellidoMaterno(dto.getApellidoMatAfectado());
			esAfectado=true;
		}	
		if (dto.getCalidadAfectado()!=null) {
			afectado.setTipoCalidad(new Valor(dto.getCalidadAfectado().getIdCampo()));
		}
		if (esAfectado) 
			queja.setAfectado(afectado);
		
		queja.setNumeroExpediente(dto.getNumeroExpediente());
	    
	    if(dto.getTipoQuejaDTO()!=null)
	    	queja.setTipoQueja(new Valor(dto.getTipoQuejaDTO().getIdCampo()));
	    if(dto.getEstatusQuejaDTO()!=null)
	    	queja.setEstatusQueja(new Valor(dto.getEstatusQuejaDTO().getIdCampo()));	   
	    if(dto.getQuejaConfirmadaDTO()!=null)
	    	queja.setQuejaConfirmada(new QuejaConfirmada(dto.getQuejaConfirmadaDTO().getQuejaConfirmadaId()));
	    if(dto.getMotivoRechazoDTO()!=null){
	    	queja.setMotivoRechazo(new Valor(dto.getMotivoRechazoDTO().getIdCampo()));
	    	queja.setEstatusQueja(new Valor(EstatusQueja.TERMINADA.getValorId()));
	    }
//		
		return queja;
	}

	/**
	 * Metodo que solo transforma:
	 * quejaCiudadanaId
	 * folioQueja
	 * nombreInvDenunciado;
	 * @param read
	 * @return
	 */
	public static QuejaCiudadanaDTO transformarQuejaSimple(QuejaCiudadana read) {
		QuejaCiudadanaDTO dto=new QuejaCiudadanaDTO();
		
		dto.setQuejaCiudadanaId(read.getDocumentoId());
		dto.setFolioQueja(read.getFolioQueja());	
		
		if (read.getQuejoso()!=null) {
			dto.setNombreQuejoso(read.getQuejoso().getNombre());
			dto.setApellidoPatQuejoso(read.getQuejoso().getApellidoPaterno());
			dto.setApellidoMatQuejoso(read.getQuejoso().getApellidoMaterno());
		}
		if (read.getDenunciado()!=null) {
			dto.setNombreDenunciado(read.getDenunciado().getNombre());
			dto.setApellidoPatDenunciado(read.getDenunciado().getApellidoPaterno());
			dto.setApellidoMatDenunciado(read.getDenunciado().getApellidoMaterno());
			if (read.getDenunciado().getTipoCalidad()!=null)
				dto.setCalidadDenunciado(new ValorDTO(read.getDenunciado().getTipoCalidad().getValorId(),read.getDenunciado().getTipoCalidad().getValor()));
		}
		if (read.getAfectado()!=null) {
			dto.setNombreAfectado(read.getAfectado().getNombre());
			dto.setApellidoPatAfectado(read.getAfectado().getApellidoPaterno());
			dto.setApellidoMatAfectado(read.getAfectado().getApellidoMaterno());
			if (read.getAfectado().getTipoCalidad()!=null)
				dto.setCalidadAfectado(new ValorDTO(read.getAfectado().getTipoCalidad().getValorId(), read.getAfectado().getTipoCalidad().getValor()));
		}
		if(read.getTipoQueja()!=null)
			dto.setTipoQuejaDTO(new ValorDTO(read.getTipoQueja().getValorId(), read.getTipoQueja().getValor()));
		if(read.getMotivoRechazo()!=null)
			dto.setMotivoRechazoDTO(new ValorDTO(read.getMotivoRechazo().getValorId(), read.getMotivoRechazo().getValor()));
		dto.setNumeroExpediente(read.getNumeroExpediente());
		
		if(read.getEstatusQueja()!=null)
			dto.setEstatusQuejaDTO(new ValorDTO(read.getEstatusQueja().getValorId(), read.getEstatusQueja().getValor()));
		
		return dto;
		
	}

	public static QuejaCiudadanaDTO transformarQueja(QuejaCiudadana queja) {
		QuejaCiudadanaDTO dto=transformarQuejaSimple(queja);
		
		dto.setDescripcionQueja(queja.getDescripcionQueja());
		dto.setFechaRegistro(queja.getFechaRegistro());						
		
		if(queja.getQuejaConfirmada()!=null)
			dto.setQuejaConfirmadaDTO(QuejaConfirmadaTransformer.transformarQuejaConfirmada(queja.getQuejaConfirmada()));
		
		return dto;
	}
	
}
