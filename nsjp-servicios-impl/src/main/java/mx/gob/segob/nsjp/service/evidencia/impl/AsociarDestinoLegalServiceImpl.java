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
import mx.gob.segob.nsjp.service.evidencia.AsociarDestinoLegalService;

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
public class AsociarDestinoLegalServiceImpl implements
		AsociarDestinoLegalService {
	
	public final static Logger logger = 
		Logger.getLogger(AsociarDestinoLegalServiceImpl.class);
	@Autowired
	private EvidenciaDAO evidenciaDAO;

	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.evidencia.AsociarDestinoLegalService#asociarDestinoLegal(mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO, java.lang.Long)
	 */
	@Override
	public void asociarDestinoLegal(EvidenciaDTO evidenciaDTO, Long destinoLegal)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ASIGNAR UN TIPO DE DESTINO LEGAL A UNA EVIDENCIA ****/");
		
		if(evidenciaDTO==null||destinoLegal==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Evidencia evidencia = evidenciaDAO.read(evidenciaDTO.getEvidenciaId());
		evidencia.setDestinoLegal(new Valor(destinoLegal));
		
		evidenciaDAO.update(evidencia);
		
	}

}
