package mx.gob.segob.nsjp.service.medida.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.medida.MedidaDAO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.model.Medida;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.medida.CambiarEstatusAMedidasConFechaFinVencidaService;
import mx.gob.segob.nsjp.service.medida.CambiarEstatusMedidaService;
import mx.gob.segob.nsjp.service.medida.impl.transform.MedidaTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CambiarEstatusAMedidasConFechaFinVencidaServiceImpl implements
 CambiarEstatusAMedidasConFechaFinVencidaService {

	@Autowired 
	MedidaDAO medidaDAO;
	
	@Autowired 
	CambiarEstatusMedidaService cambiarEstatusMedidaService;
	
	@Override
	public List<MedidaDTO> cambiarEstatusAMedidasConFechaFinVencidaService()
			throws NSJPNegocioException {
		List<Medida> medidasVencidas = medidaDAO.obtenerMedidasConFechaFinVencida();
		Set<Medida> listaSinDuplicados = new HashSet<Medida>();
		List<MedidaDTO> medidasConNuevoEstatus = new ArrayList<MedidaDTO>();
		
		//Se eliminan la lista con duplicados
		for (Medida medidaCautelarBD : medidasVencidas) {
			listaSinDuplicados.add(medidaCautelarBD);
		}		
		//Se transforman las medidas
		for (Medida medida : listaSinDuplicados) {
			medidasConNuevoEstatus.add(MedidaTransformer.transformarMedida(medida));
			//System.out.println("Cambio el estatus de " + )
			Medida loMedida = new Medida();
			loMedida= medidaDAO.read(medida.getDocumentoId());
			loMedida.setEstatus(new Valor(EstatusMedida.ATENDIDA.getValorId()));
			medidaDAO.update(loMedida);
		}
		return medidasConNuevoEstatus;
	}
}
