package mx.gob.segob.nsjp.service.medida.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.MedidaCautelarDAO;
import mx.gob.segob.nsjp.dto.documento.MedidaCautelarDTO;
import mx.gob.segob.nsjp.model.MedidaCautelar;
import mx.gob.segob.nsjp.service.infra.SSPClienteService;
import mx.gob.segob.nsjp.service.medida.EnviarMedidaCautelarService;
import mx.gob.segob.nsjp.service.medidascautelares.impl.transform.MedidaCautelarTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnviarMedidaCautelarServiceImpl implements
		EnviarMedidaCautelarService {

	@Autowired
	private SSPClienteService sspClienteService;
	@Autowired
	private MedidaCautelarDAO medidaCautelarDAO;
	
	@Override
	public MedidaCautelarDTO enviarMedidaCautelarASSP(Long idMedidaCautelar)
			throws NSJPNegocioException {
		MedidaCautelar medida = medidaCautelarDAO.read(idMedidaCautelar);
		MedidaCautelarDTO mdTO = MedidaCautelarTransformer.transformarMedidaCautelar(medida);
		sspClienteService.enviarMedidaCautelar(medida);
		return mdTO;
	}

}
