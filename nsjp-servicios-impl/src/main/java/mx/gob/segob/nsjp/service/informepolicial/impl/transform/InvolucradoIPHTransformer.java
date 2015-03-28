/**
 * 
 */
package mx.gob.segob.nsjp.service.informepolicial.impl.transform;

import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InvolucradoIPHDTO;
import mx.gob.segob.nsjp.model.InvolucradoIph;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

/**
 * @author adrian
 *
 */
public class InvolucradoIPHTransformer {

	public static InvolucradoIPHDTO transformarInvolucradoIPH(
			InvolucradoIph invIph) {
		InvolucradoIPHDTO dto=new InvolucradoIPHDTO();
		
		InformePolicialHomologadoDTO informeDTO=new InformePolicialHomologadoDTO();
		informeDTO.setInformePolicialHomologadoId(invIph.getInformePolicialHomologado().getInformePolicialHomologadoId());
		
		dto.setInformePolicialHomologado(informeDTO);
//		dto.setInvolucrado(InvolucradoTransformer.transformarInvolucrado(invIph.getInvolucrado()));
		
		return dto;
	}

}
