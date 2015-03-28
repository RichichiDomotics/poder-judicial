package mx.gob.segob.nsjp.service.sesion.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.sesion.EntrevistaComplementariaDAO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.model.EntrevistaComplementaria;
import mx.gob.segob.nsjp.service.sesion.ConsultarEntrevistaComplementariaPorIdService;
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
public class ConsultarEntrevistaComplementariaPorIdServiceImpl implements ConsultarEntrevistaComplementariaPorIdService {
		
	public final static Logger logger = 
		Logger.getLogger(ConsultarEntrevistaComplementariaPorIdServiceImpl.class);
	
	@Autowired
	private EntrevistaComplementariaDAO entrevistaComplementariaDAO;

	@Override
    public EntrevistaComplementariaDTO consultarEntrevistaComplementariaPorId(EntrevistaComplementariaDTO aoEntrevistaComplementariaDTO)
    throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para consultar EntrevistaComplementaria ****/");
		
		if (aoEntrevistaComplementariaDTO == null || aoEntrevistaComplementariaDTO.getSesionId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		EntrevistaComplementariaDTO loEntrevistaComplementariaBD = null;
		EntrevistaComplementaria aoEntrevistaComplementaria = entrevistaComplementariaDAO.consultarEntrevistaComplementariaPorId(
				(EntrevistaComplementaria)SesionTransformer.transformarSesionDTO(aoEntrevistaComplementariaDTO));
		if(aoEntrevistaComplementaria != null)
			loEntrevistaComplementariaBD = (EntrevistaComplementariaDTO)SesionTransformer.transformarSesion(aoEntrevistaComplementaria);
		return loEntrevistaComplementariaBD;
	}

}
