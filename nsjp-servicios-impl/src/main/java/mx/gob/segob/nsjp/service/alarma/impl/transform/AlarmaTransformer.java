/**
 * 
 */
package mx.gob.segob.nsjp.service.alarma.impl.transform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.alarmas.AlarmaDTO;
import mx.gob.segob.nsjp.dto.alarmas.AlertaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.model.Alarma;
import mx.gob.segob.nsjp.model.Alerta;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Valor;

/**
 * @author adrian
 *
 */
public class AlarmaTransformer {

	public static Alarma transformarAlarmaDTO(AlarmaDTO dto) {
		Alarma alar=new Alarma();
		
		if(dto.getAlarmaId()!=null)
			alar.setAlarmaId(dto.getAlarmaId());
		
		alar.setFechaAlarma(dto.getFechaAlarma());
		alar.setMotivo(dto.getMotivo());
		alar.setFolioEvento(dto.getFolioEvento());
		alar.setDatosAsociados(dto.getDatosAsociados());
		
		if(dto.getFuncionario()!=null)
			alar.setFuncionario(new Funcionario(dto.getFuncionario().getClaveFuncionario()));
		if(dto.getEstatusAlarmaAlerta()!=null)
			alar.setEstatusAlarmaAlerta(new Valor(dto.getEstatusAlarmaAlerta().getIdCampo()));
		if(dto.getTipoEventoAlarma()!=null)
			alar.setTipoEventoAlarma(new Valor(dto.getTipoEventoAlarma().getIdCampo()));
		
		if(dto.getAlertas()!=null){
			List<Alerta> alertas=new ArrayList<Alerta>();
			for (AlertaDTO ale : dto.getAlertas()) {
				alertas.add(AlertaTransformer.transformarAlertaDTO(ale));
			}
			alar.setAlertas(alertas);
		}
		
		return alar;
	}

	public static AlarmaDTO transformarAlarma(Alarma ala) {
		AlarmaDTO dto =new AlarmaDTO();
		
		dto.setAlarmaId(ala.getAlarmaId());
		dto.setFechaAlarma(ala.getFechaAlarma());
		dto.setMotivo(ala.getMotivo());
		dto.setFolioEvento(ala.getFolioEvento());
		dto.setDatosAsociados(ala.getDatosAsociados());
		
		if(ala.getEstatusAlarmaAlerta()!=null)
			dto.setEstatusAlarmaAlerta(new ValorDTO(ala.getEstatusAlarmaAlerta().getValorId(), ala.getEstatusAlarmaAlerta().getValor()));
		if(ala.getTipoEventoAlarma()!=null)
			dto.setTipoEventoAlarma(new ValorDTO(ala.getTipoEventoAlarma().getValorId(), ala.getTipoEventoAlarma().getValor()));
		if(ala.getAlertas()!=null){
			List<AlertaDTO> alertas=new ArrayList<AlertaDTO>();
			for (Alerta ale : ala.getAlertas()) {
				alertas.add(AlertaTransformer.transformarAlerta(ale));
			}
			dto.setAlertas(alertas);
		}
		
		return dto;
	}

}
