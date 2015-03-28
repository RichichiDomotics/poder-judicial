/**
 * 
 */
package mx.gob.segob.nsjp.service.evidencia.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.evidencia.CambiarEstatusEvidenciaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adrian
 *
 */
@Service
@Transactional
public class CambiarEstatusEvidenciaServiceImpl implements
		CambiarEstatusEvidenciaService {

	private final static Logger logger = Logger
    .getLogger(CambiarEstatusEvidenciaServiceImpl.class);
	
	@Autowired
	private EvidenciaDAO evidenciaDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.evidencia.CambiarEstatusEvidenciaService#cambiarEstatusEvidencia(mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO)
	 */
	@Override
	public Long cambiarEstatusEvidencia(EvidenciaDTO evidenciaDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CAMBIAR ESTATUS DE UNA EVIDENCIA ****/");
		
		if(evidenciaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if(evidenciaDTO.getEvidenciaId()==null||evidenciaDTO.getEstatus()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Evidencia evidencia = evidenciaDAO.read(evidenciaDTO.getEvidenciaId());
		evidencia.setEstatus(new Valor(evidenciaDTO.getEstatus().getIdCampo()));
		evidenciaDAO.update(evidencia);
		
		return evidencia.getEvidenciaId();
	}

}
