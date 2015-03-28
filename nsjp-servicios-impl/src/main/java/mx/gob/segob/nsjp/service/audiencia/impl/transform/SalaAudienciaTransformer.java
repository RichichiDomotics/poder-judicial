/**
 * 
 */
package mx.gob.segob.nsjp.service.audiencia.impl.transform;

import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.model.SalaAudiencia;
import mx.gob.segob.nsjp.service.audiencia.impl.SalaJAVSTransformer;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatDiscriminanteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

/**
 * @author AlejandroGA
 *
 */
public class SalaAudienciaTransformer {

	public static SalaAudienciaDTO transformarSalaAudienciaDTOBasico(SalaAudiencia sal){
		
		SalaAudienciaDTO salaAudDTO = new SalaAudienciaDTO();
		
		salaAudDTO.setSalaAudienciaId(sal.getSalaAudienciaId());
		salaAudDTO.setNombreSala(sal.getNombreSala());
		salaAudDTO.setDomicilioSala(sal.getDomicilioSala());
		salaAudDTO.setUbicacionSala(sal.getUbicacionSala());
		salaAudDTO.setEsActivo(sal.getEsActivo());
		
		return salaAudDTO;
	}
	
	public static SalaAudienciaDTO transformarSalaAudienciaDTO(SalaAudiencia sal){
		
		SalaAudienciaDTO salaAudDTO = new SalaAudienciaDTO();
		
		salaAudDTO.setSalaAudienciaId(sal.getSalaAudienciaId());
		salaAudDTO.setNombreSala(sal.getNombreSala());
		salaAudDTO.setDomicilioSala(sal.getDomicilioSala());
		salaAudDTO.setUbicacionSala(sal.getUbicacionSala());
		salaAudDTO.setEsActivo(sal.getEsActivo());
		
		if(sal.getEncargado()!=null)
			salaAudDTO.setFuncionarioDTO(FuncionarioTransformer.transformarFuncionarioBasico(sal.getEncargado()));
		
		if(sal.getCatDiscriminante()!=null)
			salaAudDTO.setCatDiscriminanteDTO(CatDiscriminanteTransformer.transformarCatDiscriminanteSimple(sal.getCatDiscriminante()));
		
		if(sal.getSalaJAVS()!=null)
			salaAudDTO.setSalaJAVSDTO(SalaJAVSTransformer.transformarSalaJAVSDTOBasico(sal.getSalaJAVS()));
		
		return salaAudDTO;
	}
	
	public static SalaAudiencia transformarSalaAudienciaBasico(SalaAudienciaDTO salaAudienciaDTO){
		return transformarSalaAudienciaUpdate(salaAudienciaDTO, null);
	}
	
	public static SalaAudiencia transformarSalaAudienciaUpdate(SalaAudienciaDTO salaAudienciaDTO, SalaAudiencia salaAudiencia){
		if( salaAudiencia==null)
			salaAudiencia = new SalaAudiencia();
		
		salaAudiencia.setSalaAudienciaId(salaAudienciaDTO.getSalaAudienciaId());
		salaAudiencia.setNombreSala(salaAudienciaDTO.getNombreSala());
		salaAudiencia.setDomicilioSala(salaAudienciaDTO.getDomicilioSala());
		salaAudiencia.setUbicacionSala(salaAudienciaDTO.getUbicacionSala());
		salaAudiencia.setEsActivo(salaAudienciaDTO.getEsActivo());
		
		return salaAudiencia;
	}

}
