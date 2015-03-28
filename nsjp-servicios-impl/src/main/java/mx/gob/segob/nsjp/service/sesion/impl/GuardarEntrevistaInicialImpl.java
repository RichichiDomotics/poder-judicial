package mx.gob.segob.nsjp.service.sesion.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.sesion.EntrevistaInicialDAO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;
import mx.gob.segob.nsjp.model.EntrevistaInicial;
import mx.gob.segob.nsjp.service.sesion.GuardarEntrevistaInicialService;
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
public class GuardarEntrevistaInicialImpl implements GuardarEntrevistaInicialService {
		
	public final static Logger logger = 
		Logger.getLogger(GuardarEntrevistaInicialImpl.class);
	
	@Autowired
	private EntrevistaInicialDAO entrevistaInicialDAO;

	@Override
	public EntrevistaInicialDTO guardarEntrevistaInicial(EntrevistaInicialDTO aoEntrevistaInicialDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para GUARDAR O ACTUALIZAR EntrevistaInicial ****/");
		
		if (aoEntrevistaInicialDTO == null || aoEntrevistaInicialDTO.getNumeroExpediente() == null
				|| aoEntrevistaInicialDTO.getNumeroExpediente().getNumeroExpedienteId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
				
		/*Transformación*/
		EntrevistaInicial aoEntrevistaInicial = (EntrevistaInicial)SesionTransformer.transformarSesionDTO(aoEntrevistaInicialDTO);
		
		/*Operación*/
		Long idObjeto= aoEntrevistaInicial.getSesionId();
		
		EntrevistaInicialDTO loObjetoBD = new EntrevistaInicialDTO();
		loObjetoBD.setSesionId(idObjeto);
		loObjetoBD.setNumeroSesion(aoEntrevistaInicialDTO.getNumeroSesion());
		
		if(idObjeto==null|| idObjeto.equals(0))
			idObjeto = entrevistaInicialDAO.create(aoEntrevistaInicial);
		else
			entrevistaInicialDAO.update(aoEntrevistaInicial);
		
		
		return loObjetoBD;
	}

}
