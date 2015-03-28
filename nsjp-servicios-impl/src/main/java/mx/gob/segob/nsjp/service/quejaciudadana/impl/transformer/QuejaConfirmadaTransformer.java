/**
 * 
 */
package mx.gob.segob.nsjp.service.quejaciudadana.impl.transformer;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.documento.NotaDTO;
import mx.gob.segob.nsjp.dto.quejaciudadana.QuejaConfirmadaDTO;
import mx.gob.segob.nsjp.model.Nota;
import mx.gob.segob.nsjp.model.QuejaConfirmada;
import mx.gob.segob.nsjp.service.documento.impl.tranform.NotaTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

/**
 * @author adrian
 *
 */
public class QuejaConfirmadaTransformer {

	public static QuejaConfirmadaDTO transformarQuejaConfirmada(
			QuejaConfirmada scr) {
		QuejaConfirmadaDTO dto =new QuejaConfirmadaDTO();
		
		if(scr.getExpediente()!=null)
			dto.setExpedienteDTO(ExpedienteTransformer.transformarExpedienteBasico(scr.getExpediente()));
		if(scr.getFuncionarioDenunciado()!=null)
			dto.setFuncionarioDenunciadoDTO(FuncionarioTransformer.transformarFuncionarioBasico(scr.getFuncionarioDenunciado()));
		if(scr.getNotas()!=null){
			List<NotaDTO> notasDTO=new ArrayList<NotaDTO>();
			for (Nota nota : scr.getNotas()) {
				notasDTO.add(NotaTransformer.transformarNota(nota));
			}
			dto.setNotasDTO(notasDTO);
		}
		
		return dto;
	}

}
