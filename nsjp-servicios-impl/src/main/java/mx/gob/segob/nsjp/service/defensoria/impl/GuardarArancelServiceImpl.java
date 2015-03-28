/**
 * 
 */
package mx.gob.segob.nsjp.service.defensoria.impl;

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.defensoria.ArancelDAO;
import mx.gob.segob.nsjp.dto.defensoria.ArancelDTO;
import mx.gob.segob.nsjp.model.Arancel;
import mx.gob.segob.nsjp.service.defensoria.GuardarArancelService;
import mx.gob.segob.nsjp.service.defensoria.impl.transformer.ArancelTransformer;

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
public class GuardarArancelServiceImpl implements GuardarArancelService {
	
	public final static Logger logger = 
		Logger.getLogger(GuardarArancelServiceImpl.class);
	
	@Autowired
	private ArancelDAO arancelDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.defensoria.GuardarArancelService#guardarArancel(mx.gob.segob.nsjp.dto.defensoria.ArancelDTO)
	 */
	@Override
	public Long guardarArancel(ArancelDTO arancelDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR O ACTUALIZAR UN ARANCEL ****/");
		
		if(arancelDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(arancelDTO.getFechaInicio()==null || arancelDTO.getFechaFin()==null 
			 || arancelDTO.getHoras()==null || arancelDTO.getMonto()==null
			 || arancelDTO.getClase_valDTO()==null || arancelDTO.getClase_valDTO().getIdCampo()==null 
			 || arancelDTO.getExpedienteDTO()==null || arancelDTO.getFuncionarioDTO()==null  
			 || arancelDTO.getExpedienteDTO().getNumeroExpedienteId()==null ){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		Arancel arancel=ArancelTransformer.transformarArancelDTO(arancelDTO);
		
		Long idArancel=arancel.getArancel_id();
		if(arancel.getArancel_id()==null|| arancel.getArancel_id().longValue() == 0){
			idArancel=arancelDAO.create(arancel);
		}else{
			arancelDAO.update(arancel);
		}
		return idArancel;
	}

	
	@Override
	public void pagarArancel(ArancelDTO arancelDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR O ACTUALIZAR UN ARANCEL ****/");
		
		if(arancelDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(arancelDTO.getArancel_id()==null || arancelDTO.getArancel_id().longValue() == 0){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		Arancel arancel=arancelDAO.read(arancelDTO.getArancel_id());
		arancel.setFechaPago(Calendar.getInstance().getTime());
		arancelDAO.update(arancel);	
	}

}
