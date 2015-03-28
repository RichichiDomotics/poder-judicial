package mx.gob.segob.nsjp.service.sesion.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.sesion.EntrevistaComplementariaDAO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.model.EntrevistaComplementaria;
import mx.gob.segob.nsjp.service.sesion.GuardarEntrevistaComplementariaService;
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
public class GuardarEntrevistaComplementariaServiceImpl implements GuardarEntrevistaComplementariaService {
		
	public final static Logger logger = 
		Logger.getLogger(GuardarEntrevistaComplementariaServiceImpl.class);
	
	@Autowired
	private EntrevistaComplementariaDAO entrevistaComplementariaDAO;

	@Override
	public EntrevistaComplementariaDTO guardarEntrevistaComplementaria(EntrevistaComplementariaDTO aoEntrevistaComplementariaDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para GUARDAR O ACTUALIZAR EntrevistaComplementaria ****/");
		
		if (aoEntrevistaComplementariaDTO == null || aoEntrevistaComplementariaDTO.getNumeroExpediente() == null
				|| aoEntrevistaComplementariaDTO.getNumeroExpediente().getNumeroExpedienteId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
				
		/*Transformación*/
		EntrevistaComplementaria aoEntrevistaComplementaria = (EntrevistaComplementaria)SesionTransformer.transformarSesionDTO(aoEntrevistaComplementariaDTO);
		
		/*Operación*/
		Long idObjeto= aoEntrevistaComplementaria.getSesionId();
		EntrevistaComplementariaDTO loObjetoBD = new EntrevistaComplementariaDTO();
		loObjetoBD.setSesionId(idObjeto);
		loObjetoBD.setNumeroSesion(aoEntrevistaComplementariaDTO.getNumeroSesion());
		if(idObjeto==null|| idObjeto.equals(0))
			idObjeto = entrevistaComplementariaDAO.create(aoEntrevistaComplementaria);
		else
			entrevistaComplementariaDAO.update(aoEntrevistaComplementaria);
		
		
		return loObjetoBD;
	}

}
