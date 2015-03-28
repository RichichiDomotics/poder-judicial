package mx.gob.segob.nsjp.service.medida.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.medida.MedidaDAO;
import mx.gob.segob.nsjp.service.medida.CambiarEstatusMedidaService;

@Service
@Transactional
public class CambiarEstatusMedidaServiceImpl implements
		CambiarEstatusMedidaService {

	@Autowired 
	MedidaDAO medidaDAO;
	@Override
	public void cambiarEstatusMedida(Long idMedida, Long idEstatus)
			throws NSJPNegocioException {
		
		medidaDAO.cambiarEstatusMedida(idMedida, idEstatus);
	}

}
