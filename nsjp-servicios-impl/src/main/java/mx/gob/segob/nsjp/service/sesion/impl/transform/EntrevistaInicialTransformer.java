package mx.gob.segob.nsjp.service.sesion.impl.transform;

import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;
import mx.gob.segob.nsjp.model.EntrevistaInicial;

/**
 * @author rgama
 * 
 */
public class EntrevistaInicialTransformer{

	public static EntrevistaInicial transformarEntrevistaInicialDTO(EntrevistaInicialDTO dto) {
		EntrevistaInicial acu = new EntrevistaInicial();
		acu.setEsVictimaDirecta(dto.getEsVictimaDirecta());
		acu.setEsPresente(dto.getEsPresente());		
		acu.setMotivoAtencion(dto.getMotivoAtencion());		
		return acu;
	}

	public static EntrevistaInicialDTO transformarEntrevistaInicial(EntrevistaInicial aoEntrevistaInicial) {
		EntrevistaInicialDTO acu = new EntrevistaInicialDTO();
		acu.setEsVictimaDirecta(aoEntrevistaInicial.getEsVictimaDirecta());
		acu.setEsPresente(aoEntrevistaInicial.getEsPresente());
		acu.setMotivoAtencion(aoEntrevistaInicial.getMotivoAtencion());
		return acu;
	}
}
