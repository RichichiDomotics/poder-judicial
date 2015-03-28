package mx.gob.segob.nsjp.service.funcionario.impl.transform;

import mx.gob.segob.nsjp.dto.funcionario.BitacoraDefensorDTO;
import mx.gob.segob.nsjp.model.BitacoraDefensor;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

public class BitacoraDefensorTransformer {

	public static BitacoraDefensor transformar(BitacoraDefensorDTO bitacoraDTO) {
		BitacoraDefensor bitacora = new BitacoraDefensor();
		
			bitacora.setBitacoraDefensorId(bitacoraDTO.getBitacoraDefensorId());
			bitacora.setEsJudicializacion(bitacoraDTO.getEsJudicializacion());
			bitacora.setEsLiberacion(bitacoraDTO.getEsLiberacion());
				Expediente exp = ExpedienteTransformer.transformarExpediente(bitacoraDTO.getExpediente());
			bitacora.setExpediente(exp);
				Funcionario func = FuncionarioTransformer.transformarFuncionario(bitacoraDTO.getFuncionario());
			bitacora.setFuncionario(func);
			bitacora.setEsAceptado(bitacoraDTO.getEsAceptado());
			bitacora.setFechaJudicializacion(bitacoraDTO.getFechaJudicializacion());
			bitacora.setFechaLiberacion(bitacoraDTO.getFechaLiberacion());
			bitacora.setLecturaDerechos(bitacoraDTO.getLecturaDerechos());
			bitacora.setObservaciones(bitacoraDTO.getObservaciones());
		
		return bitacora;
	}
}
