package mx.gob.segob.nsjp.service.informepolicial.impl.transform;


import mx.gob.segob.nsjp.dto.informepolicial.DelitoIphIdDTO;
import mx.gob.segob.nsjp.model.DelitoIphId;

public class DelitoIphIdTransformer {
	public static DelitoIphId transformarDelitoIphId(DelitoIphIdDTO delitoIphIdDTO)
	{
		DelitoIphId delitoIphId = new DelitoIphId();
		if(delitoIphIdDTO.getCatDelitoIdDTO()!=null)
			delitoIphId.setCatDelitoId(delitoIphId.getCatDelitoId());
		if(delitoIphIdDTO.getInformePolicialHomologadoIdDTO()!=null)
			delitoIphId.setInformePolicialHomologadoId(delitoIphIdDTO.getInformePolicialHomologadoIdDTO());
		
		return delitoIphId;
	}
	
	public static DelitoIphIdDTO transformarDelitoIphId(DelitoIphId delitoIphId)
	{
		DelitoIphIdDTO delitoIphIdDTO = new DelitoIphIdDTO();
		
		if(delitoIphId.getCatDelitoId()!=null)
			delitoIphIdDTO.setCatDelitoIdDTO(delitoIphId.getCatDelitoId());
		if(delitoIphId.getInformePolicialHomologadoId()!=null)
			delitoIphIdDTO.setInformePolicialHomologadoIdDTO(delitoIphId.getInformePolicialHomologadoId());
		
		return delitoIphIdDTO;
	}
}
