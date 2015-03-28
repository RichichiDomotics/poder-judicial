package mx.gob.segob.nsjp.service.informepolicial.impl.transform;

import mx.gob.segob.nsjp.dto.informepolicial.FaltaAdministrativaIphDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.model.FaltaAdministrativaIph;

public class FaltaAdministrativaIphTransformer {
	public static FaltaAdministrativaIph transformarFaltaAdministrativaIph(FaltaAdministrativaIphDTO faltaAdministrativaIphDTO)
	{
		FaltaAdministrativaIph faltaAdministrativaIph = new FaltaAdministrativaIph();		
		faltaAdministrativaIph.setCatFaltaAdministrativa(CatFaltaAdministrativaTransformer.transformarFaltaAdministrativa(faltaAdministrativaIphDTO.getCatFaltaAdministrativa()));
		faltaAdministrativaIph.setId(FaltaAdministrativaIphIdTransformer.transformarFaltaAdministrativaIphId(faltaAdministrativaIphDTO.getId()));
		faltaAdministrativaIph.setInformePolicialHomologado(InformePolicialHomologadoTransformer.transformIPH(faltaAdministrativaIphDTO.getInformePolicialHomologado()));
		return faltaAdministrativaIph;
	}
	
	public static FaltaAdministrativaIphDTO transformarFaltaAdministrativaIph(FaltaAdministrativaIph faltaAdministrativaIph)
	{
		FaltaAdministrativaIphDTO faltaAdministrativaIphDTO = new FaltaAdministrativaIphDTO();
		
		faltaAdministrativaIphDTO.setCatFaltaAdministrativa(CatFaltaAdministrativaTransformer.transformarFaltaAdministrativa(faltaAdministrativaIph.getCatFaltaAdministrativa()));
		faltaAdministrativaIphDTO.setId(FaltaAdministrativaIphIdTransformer.transformarFaltaAdministrativaIphId(faltaAdministrativaIph.getId()));
		InformePolicialHomologadoDTO informe=new InformePolicialHomologadoDTO();
		informe.setInformePolicialHomologadoId(faltaAdministrativaIph.getInformePolicialHomologado().getInformePolicialHomologadoId());
		faltaAdministrativaIphDTO.setInformePolicialHomologado(informe);
		return faltaAdministrativaIphDTO;
	}
}
