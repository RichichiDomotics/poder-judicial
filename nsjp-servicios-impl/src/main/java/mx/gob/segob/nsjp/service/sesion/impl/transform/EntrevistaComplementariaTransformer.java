package mx.gob.segob.nsjp.service.sesion.impl.transform;

import java.util.HashSet;
import java.util.Set;

import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;
import mx.gob.segob.nsjp.model.EntrevistaComplementaria;
import mx.gob.segob.nsjp.model.Familiar;

/**
 * @author rgama
 * 
 */
public class EntrevistaComplementariaTransformer{

	public static EntrevistaComplementaria transformarEntrevistaComplementariaDTO(EntrevistaComplementariaDTO dto) {
		EntrevistaComplementaria acu = new EntrevistaComplementaria();
		acu.setMotivoConsulta(dto.getMotivoConsulta());
		acu.setConcienciaProblema(dto.getConcienciaProblema());
		acu.setImpresionDiagnostico(dto.getImpresionDiagnostico());
		acu.setHipotesisFamilia(dto.getHipotesisFamilia());
		
		// Transformar la lista de familas
		Set<Familiar> llFamiliares = new HashSet<Familiar>(); 
		if(dto.getFamiliares() != null && dto.getFamiliares().size() > 0){
			for (FamiliarDTO loFamiliar : dto.getFamiliares() ) {
				llFamiliares.add(FamiliarTransformer.transformarFamiliarDTO(loFamiliar));
			}
		}
		acu.setFamiliares(llFamiliares);
		
		return acu;
	}

	public static EntrevistaComplementariaDTO transformarEntrevistaComplementaria(EntrevistaComplementaria aoEntrevistaComplementaria) {
		EntrevistaComplementariaDTO acu = new EntrevistaComplementariaDTO();
		acu.setMotivoConsulta(aoEntrevistaComplementaria.getMotivoConsulta());
		acu.setConcienciaProblema(aoEntrevistaComplementaria.getConcienciaProblema());
		acu.setImpresionDiagnostico(aoEntrevistaComplementaria.getImpresionDiagnostico());
		acu.setHipotesisFamilia(aoEntrevistaComplementaria.getHipotesisFamilia());
		// Transformar la lista de familas
		Set<FamiliarDTO> llFamiliaresDTO = new HashSet<FamiliarDTO>(); 
		if(aoEntrevistaComplementaria.getFamiliares() != null && aoEntrevistaComplementaria.getFamiliares().size() > 0){
			for (Familiar loFamiliar : aoEntrevistaComplementaria.getFamiliares() ) {
				llFamiliaresDTO.add(FamiliarTransformer.transformarFamiliar(loFamiliar));
			}
		}
		acu.setFamiliares(llFamiliaresDTO);
		return acu;
	}
	
}
