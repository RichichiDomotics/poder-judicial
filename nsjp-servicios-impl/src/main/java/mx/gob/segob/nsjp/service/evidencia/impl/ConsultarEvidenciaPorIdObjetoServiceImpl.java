package mx.gob.segob.nsjp.service.evidencia.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciaPorIdObjetoService;
import mx.gob.segob.nsjp.service.evidencia.impl.transform.EvidenciaTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsultarEvidenciaPorIdObjetoServiceImpl implements
		ConsultarEvidenciaPorIdObjetoService {

	@Autowired
	private EvidenciaDAO evidenciaDAO;
	
	@Override
	public EvidenciaDTO consultarEvidenciaPorIdObjeto(Long idObjeto, boolean tranformarObjeto)
			throws NSJPNegocioException {
		Evidencia evidencia = evidenciaDAO.consultarEvidenciaXObjetoId(idObjeto);
		
		return EvidenciaTransformer.transformarEvidencia(evidencia, tranformarObjeto);
	}

}
