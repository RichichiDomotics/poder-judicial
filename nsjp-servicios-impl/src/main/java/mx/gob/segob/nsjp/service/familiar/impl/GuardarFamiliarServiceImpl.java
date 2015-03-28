package mx.gob.segob.nsjp.service.familiar.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.sesion.FamiliarDAO;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;
import mx.gob.segob.nsjp.model.Familiar;
import mx.gob.segob.nsjp.service.familiar.GuardarFamiliarService;
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
public class GuardarFamiliarServiceImpl implements GuardarFamiliarService {
		
	public final static Logger logger = 
		Logger.getLogger(GuardarFamiliarServiceImpl.class);
	
	@Autowired
	private FamiliarDAO FamiliarDAO;

	@Override
	public FamiliarDTO guardarFamiliar(FamiliarDTO aoFamiliarDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para GUARDAR O ACTUALIZAR Familiar ****/");
		
		if (aoFamiliarDTO == null || aoFamiliarDTO.getEntrevistaComplementaria() == null
				|| aoFamiliarDTO.getEntrevistaComplementaria().getSesionId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
				
		/*Transformación*/
		Familiar aoFamiliar = FamiliarTransformer.transformarFamiliarDTO(aoFamiliarDTO);
		
		/*Operación*/
		Long idObjeto= aoFamiliar.getFamiliarId();
		if(idObjeto==null|| idObjeto.equals(0))
			idObjeto = FamiliarDAO.create(aoFamiliar);
		else
			FamiliarDAO.update(aoFamiliar);		
		
		return new FamiliarDTO(idObjeto);
	}

}
