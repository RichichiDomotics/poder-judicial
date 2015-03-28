/**
 * 
 */
package mx.gob.segob.nsjp.service.defensoria.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.defensoria.ArancelDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.defensoria.ArancelDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Arancel;
import mx.gob.segob.nsjp.service.defensoria.ConsultarArancelService;
import mx.gob.segob.nsjp.service.defensoria.impl.transformer.ArancelTransformer;

/**
 * @author adrian
 *
 */
@Service
@Transactional
public class ConsultarArancelServiceImpl implements ConsultarArancelService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarArancelServiceImpl.class);
	
	@Autowired
	private ArancelDAO arancelDAO;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.defensoria.ConsultarArancelService#consultarArancel(mx.gob.segob.nsjp.dto.defensoria.ArancelDTO)
	 */
	@Override
	public ArancelDTO consultarArancel(ArancelDTO arancelDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR UN ARANCEL POR ID ****/");
		
		if(arancelDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(arancelDTO.getArancel_id()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		return ArancelTransformer.transformarArancelDTO(arancelDAO.read(arancelDTO.getArancel_id()));
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.defensoria.ConsultarArancelService#consultarArancelesXNumeroExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public List<ArancelDTO> consultarArancelesXNumeroExpediente(
			ExpedienteDTO expedienteDTO, Long claveFuncionario) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR ARANCELES POR NUMERO DE EXPEDIENTE ****/");
		
		if(expedienteDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Obtiene el NumeroExpediente ID si no lo tiene*/
		if(expedienteDTO.getNumeroExpedienteId()==null){
				throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		}
		
		List<Arancel> aranceles=arancelDAO.consultarArancelesXNumeroExpediente(expedienteDTO.getNumeroExpedienteId(), claveFuncionario);
		List<ArancelDTO> arancelesDTO=new ArrayList<ArancelDTO>();
		for (Arancel ara : aranceles) {
			arancelesDTO.add(ArancelTransformer.transformarArancelDTO(ara));
		}
		
		return arancelesDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.defensoria.ConsultarArancelService#consultarArancelesXFuncionario(mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO)
	 */
	@Override
	public List<ArancelDTO> consultarArancelesXFuncionario(
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR ARANCELES POR FUNCIONARIO ****/");
		
		if(funcionarioDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(funcionarioDTO.getClaveFuncionario()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Arancel> aranceles=arancelDAO.consultarArancelesXFuncionario(funcionarioDTO.getClaveFuncionario());
		List<ArancelDTO> arancelesDTO=new ArrayList<ArancelDTO>();
		for (Arancel ara : aranceles) {
			arancelesDTO.add(ArancelTransformer.transformarArancelDTO(ara));
		}
		
		return arancelesDTO;
	}

}
