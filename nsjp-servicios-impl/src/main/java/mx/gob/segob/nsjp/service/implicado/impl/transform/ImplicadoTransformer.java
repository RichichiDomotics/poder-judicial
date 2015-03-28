/**
 * 
 */
package mx.gob.segob.nsjp.service.implicado.impl.transform;

import java.util.HashSet;
import java.util.Set;

import mx.gob.segob.nsjp.dto.involucrado.ImplicadoDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.Implicado;
import mx.gob.segob.nsjp.model.MedioDeContacto;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.service.persona.impl.transform.MedioDeContactoTransformer;

/**
 * @author rgama
 * 
 */
public class ImplicadoTransformer {

	public static Implicado transformarImplicadoDTO(ImplicadoDTO aoImplicado) {
		Implicado loObjeto = new Implicado();
		loObjeto.setImplicadoId(aoImplicado.getImplicadoId());
		loObjeto.setNombre(aoImplicado.getNombre());
		loObjeto.setApellidoMaterno(aoImplicado.getApellidoMaterno());
		

		Set<MedioDeContacto> medios = new HashSet<MedioDeContacto>();
		if (aoImplicado.getMedioDeContactos() != null) {        	
        	for(MedioDeContactoDTO medio : aoImplicado.getMedioDeContactos()){
        		if( medio instanceof TelefonoDTO){
        			Telefono telefono = MedioDeContactoTransformer.transformarTelefono((TelefonoDTO) medio);
        			medios.add(telefono);
        		}
        		if( medio instanceof CorreoElectronicoDTO){
        			CorreoElectronico correo = MedioDeContactoTransformer.transformarCorreo((CorreoElectronicoDTO) medio);
        			medios.add(correo);
        		}
        	}
        }
        loObjeto.setMedioDeContactos(medios);
        return loObjeto;
	}

	public static ImplicadoDTO transformarImplicado(Implicado aoImplicado) {
		ImplicadoDTO loObjeto = new ImplicadoDTO();
		loObjeto.setImplicadoId(aoImplicado.getImplicadoId());
		loObjeto.setNombre(aoImplicado.getNombre());
		loObjeto.setApellidoMaterno(aoImplicado.getApellidoMaterno());		

		Set<MedioDeContactoDTO> mediosDTO = new HashSet<MedioDeContactoDTO>();
		if (aoImplicado.getMedioDeContactos() != null) {        	
        	for(MedioDeContacto medio : aoImplicado.getMedioDeContactos()){
        		if( medio instanceof Telefono){
        			TelefonoDTO telefono = MedioDeContactoTransformer.transformarTelefono((Telefono) medio);
        			mediosDTO.add(telefono);
        		}
        		if( medio instanceof CorreoElectronico){
        			CorreoElectronicoDTO correo = MedioDeContactoTransformer.transformarCorreo((CorreoElectronico) medio);
        			mediosDTO.add(correo);
        		}
        	}
        }
        loObjeto.setMedioDeContactos(mediosDTO);
        return loObjeto;
	}

}
