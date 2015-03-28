package mx.gob.segob.nsjp.service.informepolicial.impl.transform;

import mx.gob.segob.nsjp.dto.informepolicial.FaltaAdministrativaIphIdDTO;
import mx.gob.segob.nsjp.model.FaltaAdministrativaIphId;

public class FaltaAdministrativaIphIdTransformer {
	public static FaltaAdministrativaIphId transformarFaltaAdministrativaIphId(FaltaAdministrativaIphIdDTO faltaIphIdDTO)
	{
		FaltaAdministrativaIphId faltaIphId = new FaltaAdministrativaIphId();
		if(faltaIphIdDTO.getCatFaltaAdministrativaIdDTO()!=null)
			faltaIphId.setCatFaltaAdministrativaId(faltaIphIdDTO.getCatFaltaAdministrativaIdDTO());
		if(faltaIphIdDTO.getInformePolicialHomologadoIdDTO()!=null)
			faltaIphId.setInformePolicialHomologadoId(faltaIphIdDTO.getInformePolicialHomologadoIdDTO());
		
		return faltaIphId;	
	}
	
	public static FaltaAdministrativaIphIdDTO transformarFaltaAdministrativaIphId(FaltaAdministrativaIphId faltaIphId)
	{
		FaltaAdministrativaIphIdDTO faltaIphIdDTO = new FaltaAdministrativaIphIdDTO();
		
		if(faltaIphId.getCatFaltaAdministrativaId()!=null)
			faltaIphIdDTO.setCatFaltaAdministrativaIdDTO(faltaIphId.getCatFaltaAdministrativaId());
		if(faltaIphId.getInformePolicialHomologadoId()!=null)
			faltaIphIdDTO.setInformePolicialHomologadoIdDTO(faltaIphId.getInformePolicialHomologadoId());		
		return faltaIphIdDTO;
	}
}
