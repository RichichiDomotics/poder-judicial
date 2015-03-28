package mx.gob.segob.nsjp.service.leycodigo.impl.transform;

import mx.gob.segob.nsjp.dto.LeyCodigoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.model.LeyCodigo;

public final class LeyCodigoTransform {

	public static LeyCodigoDTO tranformar(LeyCodigo norma){
		LeyCodigoDTO normaDTO = new LeyCodigoDTO();
		
		normaDTO.setDescripcion(norma.getDescripcion());
		ValorDTO valor = new ValorDTO(norma.getTipoNorma().getValorId(), norma.getTipoNorma().getValor());
		normaDTO.setTipoNorma(valor);
		normaDTO.setUrl(norma.getUrl());
		normaDTO.setNombre(norma.getNombre());
		normaDTO.setLeyCodigoId(norma.getLeyCodigoId());
		
		return normaDTO;
	}
	
}
