/**
 * 
 */
package mx.gob.segob.nsjp.service.persona.impl.transform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.model.Valor;

/**
 * @author cesar
 *
 */
public class MedioDeContactoTransformer {
	
	/**
	 * 
	 * @param telefonos
	 * @return
	 */
	public static List<TelefonoDTO> transformarTelefonos (List<Telefono> telefonos) {
		
		List<TelefonoDTO> telefonosDTO = new ArrayList<TelefonoDTO>();
		
		for (Telefono telefono : telefonos) {
			TelefonoDTO telefonoDTO = new TelefonoDTO();
			
			telefonoDTO.setNumeroTelefonico(telefono.getNumeroTelefonico());
			telefonoDTO.setCodigoPais(telefono.getCodigoPais());
			telefonoDTO.setCodigoArea(telefono.getCodigoArea());
			telefonoDTO.setMedioDeContadoId(telefono.getMedioDeContactoId());
			
			if (telefono.getValor()!=null)
				telefonoDTO.setValorTipoTelefono(new ValorDTO(telefono.getValor().getValorId(), telefono.getValor().getValor()));
			
			telefonosDTO.add(telefonoDTO);
		}
		
		return telefonosDTO;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Telefono transformarTelefono (TelefonoDTO telefonoDTO) {
		Telefono telefono = new Telefono();
		
		if (telefonoDTO.getMedioDeContadoId()!=null && telefonoDTO.getMedioDeContadoId()>0)
			telefono.setMedioDeContactoId(telefonoDTO.getMedioDeContadoId());
		
		telefono.setNumeroTelefonico(telefonoDTO.getNumeroTelefonico());
		telefono.setCodigoPais(telefonoDTO.getCodigoPais());
		telefono.setCodigoArea(telefonoDTO.getCodigoArea());
		if (telefonoDTO.getValorTipoTelefono()!=null) {
			telefono.setValor(new Valor(telefonoDTO.getValorTipoTelefono().getIdCampo()));
		}
		
		return telefono;
	}	
	
	/**
	 * 
	 * @param telefonos
	 * @return
	 */
	public static TelefonoDTO transformarTelefono (Telefono telefono) {
		
			TelefonoDTO telefonoDTO = new TelefonoDTO();
			
			telefonoDTO.setNumeroTelefonico(telefono.getNumeroTelefonico());
			telefonoDTO.setCodigoPais(telefono.getCodigoPais());
			telefonoDTO.setCodigoArea(telefono.getCodigoArea());
			telefonoDTO.setMedioDeContadoId(telefono.getMedioDeContactoId());
			telefonoDTO.setTelefonoId(telefono.getMedioDeContactoId());
			
			if (telefono.getValor() != null)
				telefonoDTO.setValorTipoTelefono(new ValorDTO(telefono.getValor().getValorId(), telefono.getValor().getValor()));
			
		return telefonoDTO;
	}
	
	/**
	 * 
	 * @param correos
	 * @return
	 */
	public static List<CorreoElectronicoDTO> tranformarCorreos (List<CorreoElectronico> correos) {
		
		List<CorreoElectronicoDTO> correosDTO = new ArrayList<CorreoElectronicoDTO>();
		
		for (CorreoElectronico correo : correos) {
			CorreoElectronicoDTO correoDTO = new CorreoElectronicoDTO();
			
			correoDTO.setMedioDeContadoId(correo.getMedioDeContactoId());
			correoDTO.setDireccionElectronica(correo.getDireccionElectronica());
			
			correosDTO.add(correoDTO);
		}
		
		return correosDTO;
	}
		
	
	/**
	 * 
	 * @param correoDTO
	 * @return
	 */
	public static CorreoElectronico transformarCorreo (CorreoElectronicoDTO correoDTO) {
		CorreoElectronico correo = new CorreoElectronico();
		
		/*if (correoDTO.getMedioDeContadoId()!=null && correoDTO.getMedioDeContadoId()>0)
			correo.setMedioDeContactoId(correoDTO.getMedioDeContadoId());*/
		
		correo.setDireccionElectronica(correoDTO.getDireccionElectronica());
		
		return correo;
	}
	
	/**
	 * 
	 * @param correoDTO
	 * @return
	 */
	public static CorreoElectronicoDTO transformarCorreo (CorreoElectronico correo) {
		CorreoElectronicoDTO correoDTO = new CorreoElectronicoDTO();
		correoDTO.setCorreoElectronicoId(correo.getMedioDeContactoId());
		correoDTO.setMedioDeContadoId(correo.getMedioDeContactoId());
		correoDTO.setDireccionElectronica(correo.getDireccionElectronica());
		
		return correoDTO;
	}
	
}
