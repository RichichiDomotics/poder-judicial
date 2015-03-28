/**
 * 
 */
package mx.gob.segob.nsjp.service.quejaciudadana.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.quejaciudadana.QuejaCiudadanaDAO;
import mx.gob.segob.nsjp.dto.quejaciudadana.QuejaCiudadanaDTO;
import mx.gob.segob.nsjp.model.QuejaCiudadana;
import mx.gob.segob.nsjp.service.quejaciudadana.ConsultarQuejaCiudadanaService;
import mx.gob.segob.nsjp.service.quejaciudadana.impl.transformer.QuejaCiudadanaTransformer;

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
public class ConsultarQuejaCiudadanaServiceImpl implements
		ConsultarQuejaCiudadanaService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarQuejaCiudadanaServiceImpl.class);
	
	@Autowired
	private QuejaCiudadanaDAO quejaDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.quejaciudadana.ConsultarQuejaCiudadanaService#consultarQuejasCiudadanas()
	 */
	@Override
	public List<QuejaCiudadanaDTO> consultarQuejasCiudadanas()throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LAS QUEJAS CIUDADANAS ****/");
		
		List<Long> idsQuejas = quejaDAO.findAllId();
		List<QuejaCiudadanaDTO> quejasDTO=new ArrayList<QuejaCiudadanaDTO>();
		for (Long id : idsQuejas) {
			quejasDTO.add(QuejaCiudadanaTransformer.transformarQueja(quejaDAO.read(id)));
		}
		
		return quejasDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.quejaciudadana.ConsultarQuejaCiudadanaService#consultarQuejaCiudadanaXId(java.lang.Long)
	 */
	@Override
	public QuejaCiudadanaDTO consultarQuejaCiudadanaXId(Long idQueja)throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR EL DETALLE DE UNA QUEJA DADA ****/");
		
		if(idQueja==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		QuejaCiudadana queja = quejaDAO.read(idQueja);
		
		return QuejaCiudadanaTransformer.transformarQueja(queja);
	}

	@Override
	public List<QuejaCiudadanaDTO> consultarQuejasPorEstatus(Long estatus)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR QUEJAS CIUDADANAS SEGÚN UN ESTATUS ****/");
		
		if(estatus==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<QuejaCiudadana> quejas=quejaDAO.consultarQuejasPorEstatus(estatus);
		List<QuejaCiudadanaDTO> quejasDTO=new ArrayList<QuejaCiudadanaDTO>();
		for (QuejaCiudadana quej : quejas) {
			quejasDTO.add(QuejaCiudadanaTransformer.transformarQuejaSimple(quej));
		}
		
		return quejasDTO;
	}

}
