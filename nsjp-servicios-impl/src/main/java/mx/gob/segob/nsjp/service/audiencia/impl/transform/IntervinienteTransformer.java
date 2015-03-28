package mx.gob.segob.nsjp.service.audiencia.impl.transform;


import mx.gob.segob.nsjp.dto.audiencia.IntervinienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Interviniente;

import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

public class IntervinienteTransformer {
	/**
	 * Clase para encapsular el entity intervinente en una clase para trabajar con el negocio
	 * @param interviniente
	 * @return
	 */
	public static IntervinienteDTO transformarDTO(Interviniente interviniente) {
		IntervinienteDTO intervinienteDTO = new IntervinienteDTO();
		if (interviniente.getIntervinienteId() != null) {
			intervinienteDTO.setAudiencia(AudienciaTransformer
					.transformarDTO(interviniente.getAudiencia()));
			intervinienteDTO.setInvolucrado(InvolucradoTransformer
					.transformarInvolucrado(interviniente.getInvolucrado()));
			intervinienteDTO.setFuncionario(FuncionarioTransformer
					.transformarFuncionario(interviniente.getFuncionario()));
		}

		intervinienteDTO.setIntervinienteId(interviniente.getIntervinienteId());
		intervinienteDTO.setEsAceptado(interviniente.getEsAceptado());
		intervinienteDTO.setEsParteFiscalia(interviniente.getEsParteFiscalia());
		intervinienteDTO.setEsPresente(interviniente.getEsPresente());
		intervinienteDTO.setEsSuperVeniente(interviniente.getEsSuperVeniente());
		intervinienteDTO.setEsTitular(interviniente.getEsTitular());
		
		return intervinienteDTO;
	}
	
    public static Interviniente modificarInterviniente(IntervinienteDTO intervinienteDTO){
    	Interviniente interviniente = new Interviniente();
    	interviniente.setIntervinienteId(intervinienteDTO.getIntervinienteId());
		interviniente.setEsAceptado(intervinienteDTO.isEsAceptado());
		interviniente.setEsParteFiscalia(intervinienteDTO.isEsParteFiscalia());
		interviniente.setEsPresente(intervinienteDTO.isEsPresente());
		interviniente.setEsSuperVeniente(intervinienteDTO.isEsSuperVeniente());
		
		interviniente.setEsTitular(intervinienteDTO.isEsTitular());
    	return interviniente;
    }
	
}
