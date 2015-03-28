/**
 * 
 */
package mx.gob.segob.nsjp.service.almacen.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.almacen.EncargadoAlmacenDAO;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.EncargadoAlmacen;
import mx.gob.segob.nsjp.model.EncargadoAlmacenId;
import mx.gob.segob.nsjp.service.almacen.EncargadoAlmacenService;

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
public class EncargadoAlmacenServiceImpl implements EncargadoAlmacenService {
	
	public final static Logger logger = 
		Logger.getLogger(EncargadoAlmacenServiceImpl.class);
	
	@Autowired
	private EncargadoAlmacenDAO encargadoAlmacenDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.almacen.EncargadoAlmacenService#asignarEncargadoDAlmacen(mx.gob.segob.nsjp.dto.almacen.AlmacenDTO, mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO)
	 */
	@Override
	public Long asignarEncargadoDAlmacen(AlmacenDTO almacenDTO,
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ASIGNAR ENCARGADO DE ALMACEN ****/");
		
		if(almacenDTO==null||funcionarioDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(almacenDTO.getIdentificadorAlmacen()==null||funcionarioDTO.getClaveFuncionario()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Long idEncargado=null;
		EncargadoAlmacen newInstance=new EncargadoAlmacen();
		EncargadoAlmacenId encargadoAlmacenId=new EncargadoAlmacenId(almacenDTO.getIdentificadorAlmacen(), funcionarioDTO.getClaveFuncionario());
		newInstance.setEncargadoAlmacenId(encargadoAlmacenId);
		EncargadoAlmacenId idResp = encargadoAlmacenDAO.create(newInstance);
		if(idResp!=null)
			return idResp.getFuncionarioId();
		
		return idEncargado;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.almacen.EncargadoAlmacenService#removerEncargadoDAlmacen(mx.gob.segob.nsjp.dto.almacen.AlmacenDTO, mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO)
	 */
	@Override
	public Long removerEncargadoDAlmacen(AlmacenDTO almacenDTO,
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REMOVER ENCARGADO DE ALMACEN ****/");
		
		if(almacenDTO==null||funcionarioDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(almacenDTO.getIdentificadorAlmacen()==null||funcionarioDTO.getClaveFuncionario()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Long idEncargado=null;
		EncargadoAlmacen persistentObject=new EncargadoAlmacen();
		EncargadoAlmacenId encargadoAlmacenId=new EncargadoAlmacenId(almacenDTO.getIdentificadorAlmacen(), funcionarioDTO.getClaveFuncionario());
		persistentObject.setEncargadoAlmacenId(encargadoAlmacenId);
		encargadoAlmacenDAO.delete(persistentObject);
		
		idEncargado=funcionarioDTO.getClaveFuncionario();
		
		return idEncargado;
	}

}
