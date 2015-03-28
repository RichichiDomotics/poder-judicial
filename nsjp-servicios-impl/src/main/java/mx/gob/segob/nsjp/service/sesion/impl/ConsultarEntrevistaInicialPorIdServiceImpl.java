package mx.gob.segob.nsjp.service.sesion.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.sesion.EntrevistaInicialDAO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;
import mx.gob.segob.nsjp.model.EntrevistaInicial;
import mx.gob.segob.nsjp.service.sesion.ConsultarEntrevistaInicialPorIdService;
import mx.gob.segob.nsjp.service.sesion.impl.transform.SesionTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rgama
 *
 */
@Service
@Transactional
public class ConsultarEntrevistaInicialPorIdServiceImpl implements ConsultarEntrevistaInicialPorIdService {
		
	public final static Logger logger = 
		Logger.getLogger(ConsultarEntrevistaInicialPorIdServiceImpl.class);
	
	@Autowired
	private EntrevistaInicialDAO entrevistaInicialDAO;

	@Override
    public EntrevistaInicialDTO consultarEntrevistaInicialPorId(EntrevistaInicialDTO aoEntrevistaInicialDTO)
    throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para consultar EntrevistaInicial ****/");
		
		if (aoEntrevistaInicialDTO == null || aoEntrevistaInicialDTO.getSesionId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		EntrevistaInicialDTO loEntrevistaInicialBD = null;
		EntrevistaInicial aoEntrevistaInicial = entrevistaInicialDAO.consultarEntrevistaInicialPorId(
				(EntrevistaInicial)SesionTransformer.transformarSesionDTO(aoEntrevistaInicialDTO));
		if(aoEntrevistaInicial != null)
			loEntrevistaInicialBD = (EntrevistaInicialDTO)SesionTransformer.transformarSesion(aoEntrevistaInicial);
		return loEntrevistaInicialBD;
	}

}
