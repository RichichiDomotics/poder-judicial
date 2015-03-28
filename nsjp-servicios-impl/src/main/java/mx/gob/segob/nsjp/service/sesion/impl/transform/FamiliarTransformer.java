package mx.gob.segob.nsjp.service.sesion.impl.transform;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;
import mx.gob.segob.nsjp.model.EntrevistaComplementaria;
import mx.gob.segob.nsjp.model.Familiar;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.relacion.impl.CatRelacionTransformer;

/**
 * @author rgama
 * 
 */
public class FamiliarTransformer {

	public static Familiar transformarFamiliarDTO(FamiliarDTO dto) {
		
		Familiar acu = new Familiar();		
		
		acu.setFamiliarId(dto.getFamiliarId());
		acu.setNombre(dto.getNombre());
		acu.setApellidoPaterno(dto.getApellidoPaterno());
		acu.setApellidoMaterno(dto.getApellidoMaterno());
		acu.setEdad(dto.getEdad());
		
		if(dto.getRelacion() != null)
			acu.setRelacion(CatRelacionTransformer.transformarCatRelacion(dto.getRelacion()));
		if(dto.getEscolaridad() != null && dto.getEscolaridad().getIdCampo() != null)
			acu.setEscolaridad(new Valor(dto.getEscolaridad().getIdCampo()));
		if(dto.getEstadoCivil() != null && dto.getEstadoCivil().getIdCampo() != null)
			acu.setEstadoCivil(new Valor(dto.getEstadoCivil().getIdCampo()));
		if(dto.getOcupacion() != null && dto.getOcupacion().getIdCampo() != null)
			acu.setOcupacion(new Valor(dto.getOcupacion().getIdCampo()));
		if(dto.getEntrevistaComplementaria() != null)
			acu.setEntrevistaComplementaria(new EntrevistaComplementaria(dto.getEntrevistaComplementaria().getSesionId()));		
		return acu;
	}

	public static FamiliarDTO transformarFamiliar(Familiar aoFamiliar) {
		FamiliarDTO acu = new FamiliarDTO();		
		
		acu.setFamiliarId(aoFamiliar.getFamiliarId());
		acu.setNombre(aoFamiliar.getNombre());
		acu.setApellidoPaterno(aoFamiliar.getApellidoPaterno());
		acu.setApellidoMaterno(aoFamiliar.getApellidoMaterno());
		acu.setEdad(aoFamiliar.getEdad());
		
		if(aoFamiliar.getRelacion() != null)
			acu.setRelacion(CatRelacionTransformer.transformarCatRelacion(aoFamiliar.getRelacion()));
		if(aoFamiliar.getEscolaridad() != null && aoFamiliar.getEscolaridad().getValorId() != null)
			acu.setEscolaridad(new ValorDTO(aoFamiliar.getEscolaridad().getValorId(),aoFamiliar.getEscolaridad().getValor()));
		if(aoFamiliar.getEstadoCivil() != null && aoFamiliar.getEstadoCivil().getValorId() != null)
			acu.setEstadoCivil(new ValorDTO(aoFamiliar.getEstadoCivil().getValorId(),aoFamiliar.getEstadoCivil().getValor()));
		if(aoFamiliar.getOcupacion() != null && aoFamiliar.getOcupacion().getValorId() != null)
			acu.setOcupacion(new ValorDTO(aoFamiliar.getOcupacion().getValorId(),aoFamiliar.getOcupacion().getValor()));
		if(aoFamiliar.getEntrevistaComplementaria() != null)
			acu.setEntrevistaComplementaria(new EntrevistaComplementariaDTO(aoFamiliar.getEntrevistaComplementaria().getSesionId()));		
		return acu;	
	}
}
