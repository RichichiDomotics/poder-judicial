/**
 * 
 */
package mx.gob.segob.nsjp.service.audiencia.impl;

import mx.gob.segob.nsjp.dto.audiencia.SalaJAVSDTO;
import mx.gob.segob.nsjp.model.SalaJAVS;

/**
 * @author GustavoBP
 *
 */
public class SalaJAVSTransformer {

	public static SalaJAVSDTO transformarSalaJAVSDTOBasico(SalaJAVS salaJAVS){
		SalaJAVSDTO salaJAVSDTO = new SalaJAVSDTO();
		
		salaJAVSDTO.setSalaJAVSId(salaJAVS.getSalaJAVSId());
		salaJAVSDTO.setDireccionIP(salaJAVS.getDireccionIP());
		salaJAVSDTO.setPassword(salaJAVS.getPassword());
		salaJAVSDTO.setUsuarioJAVS(salaJAVS.getUsuario());
		
		return salaJAVSDTO;
	}
	
	public static SalaJAVS transformarSalaJAVSBasico(SalaJAVSDTO salaJAVSDTO){
		return transformarSalaJAVSUpdate(salaJAVSDTO, null);
	}
	
	public static SalaJAVS transformarSalaJAVSUpdate(SalaJAVSDTO salaJAVSDTO, SalaJAVS salaJAVS){
		if(salaJAVS ==null)
			salaJAVS = new SalaJAVS();
		
		salaJAVS.setSalaJAVSId(salaJAVSDTO.getSalaJAVSId());
		salaJAVS.setDireccionIP(salaJAVSDTO.getDireccionIP());
		salaJAVS.setPassword(salaJAVSDTO.getPassword());
		salaJAVS.setUsuario(salaJAVSDTO.getUsuarioJAVS());
		
		return salaJAVS;
	}
}
