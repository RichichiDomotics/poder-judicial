package mx.gob.segob.nsjp.service.familiar.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.sesion.FamiliarDAO;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;
import mx.gob.segob.nsjp.model.Familiar;
import mx.gob.segob.nsjp.service.familiar.ConsultarFamiliarPorIdService;
import mx.gob.segob.nsjp.service.sesion.impl.transform.FamiliarTransformer;

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
public class ConsultarFamiliarPorIdServiceImpl implements ConsultarFamiliarPorIdService {
		
	public final static Logger logger = 
		Logger.getLogger(ConsultarFamiliarPorIdServiceImpl.class);
	
	@Autowired
	private FamiliarDAO familiarDAO;

	@Override
    public FamiliarDTO consultarFamiliarPorId(FamiliarDTO aoFamiliarDTO)
    throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para consultar Familiar ****/");
		
		if (aoFamiliarDTO == null || aoFamiliarDTO.getFamiliarId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		FamiliarDTO loFamiliarBD = null;
		Familiar aoFamiliar = familiarDAO.consultarFamiliarPorId(
				FamiliarTransformer.transformarFamiliarDTO(aoFamiliarDTO));
		if(aoFamiliar != null)
			loFamiliarBD = FamiliarTransformer.transformarFamiliar(aoFamiliar);
		return loFamiliarBD;
	}

}
