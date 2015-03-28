package mx.gob.segob.nsjp.service.informepolicial.impl.transform;

import mx.gob.segob.nsjp.dto.catalogo.CatFaltaAdministrativaDTO;
import mx.gob.segob.nsjp.model.CatFaltaAdministrativa;

public class CatFaltaAdministrativaTransformer {
	public static CatFaltaAdministrativa transformarFaltaAdministrativa(CatFaltaAdministrativaDTO catFaltaAdministrativaDTO)
	{
		CatFaltaAdministrativa catFaltaAdministrativa = new CatFaltaAdministrativa();
		catFaltaAdministrativa.setCatFaltaAdministrativaId(catFaltaAdministrativaDTO.getCatFaltaAdministrativaId());
		if(catFaltaAdministrativaDTO.getClaveFalta()!=null)
			catFaltaAdministrativa.setClaveFalta(catFaltaAdministrativaDTO.getClaveFalta());
		if(catFaltaAdministrativaDTO.getDescripcionFalta()!=null)
			catFaltaAdministrativa.setDescripcionFalta(catFaltaAdministrativaDTO.getDescripcionFalta());
		if(catFaltaAdministrativaDTO.getNombreFalta()!=null)
			catFaltaAdministrativa.setNombreFalta(catFaltaAdministrativaDTO.getNombreFalta());		
		return catFaltaAdministrativa;
	}
	
	public static CatFaltaAdministrativaDTO transformarFaltaAdministrativa(CatFaltaAdministrativa catFaltaAdministrativa)
	{
		CatFaltaAdministrativaDTO catFaltaAdministrativaDTO = new CatFaltaAdministrativaDTO();
		
		catFaltaAdministrativaDTO.setCatFaltaAdministrativaId(catFaltaAdministrativa.getCatFaltaAdministrativaId());
		if(catFaltaAdministrativa.getClaveFalta()!=null)
			catFaltaAdministrativaDTO.setClaveFalta(catFaltaAdministrativa.getClaveFalta());
		if(catFaltaAdministrativa.getDescripcionFalta()!=null)
			catFaltaAdministrativaDTO.setDescripcionFalta(catFaltaAdministrativa.getDescripcionFalta());
		if(catFaltaAdministrativa.getNombreFalta()!=null)
			catFaltaAdministrativaDTO.setNombreFalta(catFaltaAdministrativa.getNombreFalta());
		return catFaltaAdministrativaDTO;
	}
	
}
