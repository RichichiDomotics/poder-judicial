package mx.gob.segob.nsjp.service.informepolicial.impl.transform;

import mx.gob.segob.nsjp.dto.informepolicial.DelitoIphDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.model.DelitoIph;

public class DelitoIphTransformer {
	
	public static DelitoIphDTO transformarDelitoIph(DelitoIph delitoIph)
	{		
		DelitoIphDTO delitoIphDTO = new DelitoIphDTO();		
		
		if(delitoIph.getId()!=null)
			delitoIphDTO.setId(DelitoIphIdTransformer.transformarDelitoIphId(delitoIph.getId()));					
		if(delitoIph.getCatDelito()!=null)
			delitoIphDTO.setCatDelito(CatDelitoTransformer.transformarCatDelito(delitoIph.getCatDelito()));
		if(delitoIph.getInformePolicialHomologado()!=null){
			InformePolicialHomologadoDTO informe=new InformePolicialHomologadoDTO();
			informe.setInformePolicialHomologadoId(delitoIph.getInformePolicialHomologado().getInformePolicialHomologadoId());
			delitoIphDTO.setInformePolicialHomologado(informe);
		}

		return delitoIphDTO;
	}
	
	public static DelitoIph transformarDelitoIph(DelitoIphDTO delitoIphDTO)
	{
		DelitoIph delitoIph = new DelitoIph();
		
		if(delitoIphDTO.getId()!=null)
			delitoIph.setId(DelitoIphIdTransformer.transformarDelitoIphId(delitoIphDTO.getId()));
		if(delitoIphDTO.getCatDelito()!=null)
			delitoIph.setCatDelito(CatDelitoTransformer.transformarCatDelito(delitoIphDTO.getCatDelito()));
		if(delitoIphDTO.getInformePolicialHomologado()!=null)
			delitoIph.setInformePolicialHomologado(InformePolicialHomologadoTransformer.transformIPH(delitoIphDTO.getInformePolicialHomologado()));
		
		return delitoIph;
	}
}
