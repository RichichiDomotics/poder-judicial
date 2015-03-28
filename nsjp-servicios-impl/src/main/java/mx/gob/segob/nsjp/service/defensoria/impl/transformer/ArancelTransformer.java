/**
 * 
 */
package mx.gob.segob.nsjp.service.defensoria.impl.transformer;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.defensoria.ArancelDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Arancel;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

/**
 * @author adrian
 * 
 */
public class ArancelTransformer {

	public static Arancel transformarArancelDTO(ArancelDTO dto) {
		Valor clase = null;
		NumeroExpediente numeroExpediente = null;
		Funcionario funcionario=null;
		if (dto.getClase_valDTO() != null)
			clase = new Valor(dto.getClase_valDTO().getIdCampo());
		if (dto.getExpedienteDTO() != null)
			numeroExpediente = new NumeroExpediente(dto.getExpedienteDTO()
					.getNumeroExpedienteId(), dto.getExpedienteDTO()
					.getNumeroExpediente(), null);
		if(dto.getFuncionarioDTO()!=null)
			funcionario=new Funcionario(dto.getFuncionarioDTO().getClaveFuncionario());
				
		Arancel arancel = new Arancel(dto.getArancel_id(), dto.getFechaInicio(),
				dto.getFechaFin(), dto.getHoras(), dto.getMonto(),
				dto.getFechaPago(), clase, numeroExpediente,funcionario);
		arancel.setFechaRegistro(dto.getFechaRegistro());
		return arancel;
	}

	public static ArancelDTO transformarArancelDTO(Arancel scr) {
		ArancelDTO dto=new ArancelDTO();
		
		dto.setArancel_id(scr.getArancel_id());
		dto.setFechaRegistro(scr.getFechaRegistro());
		dto.setFechaInicio(scr.getFechaInicio());
		dto.setFechaFin(scr.getFechaFin());
		dto.setHoras(scr.getHoras());
		dto.setMonto(scr.getMonto());
		dto.setFechaPago(scr.getFechaPago());
		
		if(scr.getClase_val()!=null)
			dto.setClase_valDTO(new ValorDTO(scr.getClase_val().getValorId(), scr.getClase_val().getValor()));
		if(scr.getNumeroExpediente()!=null){
			ExpedienteDTO expDTO=new ExpedienteDTO();
			expDTO.setNumeroExpedienteId(scr.getNumeroExpediente().getNumeroExpedienteId());
			expDTO.setNumeroExpediente(scr.getNumeroExpediente().getNumeroExpediente());
			dto.setExpedienteDTO(expDTO);
		}
		if(scr.getFuncionario()!=null)
			dto.setFuncionarioDTO(FuncionarioTransformer.transformarFuncionarioBasico(scr.getFuncionario()));
		
		return dto;
	}

}
