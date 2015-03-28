/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.catalogo.CatDiscriminateDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatDistritoDAO;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.CatDistrito;
import mx.gob.segob.nsjp.service.catalogo.RegistrarDistritoService;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatDistritoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AlineGS
 * 
 */
@Service
@Transactional
public class RegistrarDistritoServiceImpl implements RegistrarDistritoService {

	public static final Logger logger = Logger.getLogger(RegistrarDistritoServiceImpl.class);

	@Autowired
	private CatDistritoDAO distritoDAO;
	@Autowired 
	private CatDiscriminateDAO discriminanteDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.catalogo.RegistrarDistritoService#registrarDistrito
	 * (mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO)
	 */
	@Override
	public Long registrarDistrito(CatDistritoDTO distritoDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGISTRAR UN DISTRITO****/");
		
		if(distritoDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if(distritoDTO.getClaveDistrito()==null||distritoDTO.getNombreDist()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		CatDistrito distrito=CatDistritoTransformer.transformarDistritoDTO(distritoDTO);
		Long idDistrito=distrito.getCatDistritoId();
		if(idDistrito==null)
			idDistrito=distritoDAO.create(distrito);
		else
			distritoDAO.update(distrito);
		
		return idDistrito;
	}

	@Override
	public Long registrarDistritoConFantasma(CatDistritoDTO distritoDTO)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGISTRAR UN DISTRITO CON DISCRIMINANTE FANTASMA****/");
		
		if(distritoDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if(distritoDTO.getClaveDistrito()==null||distritoDTO.getNombreDist()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		CatDistrito distrito=CatDistritoTransformer.transformarDistritoDTO(distritoDTO);
		Long idDistrito =distrito.getCatDistritoId();
		if(idDistrito==null){
			idDistrito =distritoDAO.create(distrito);
			CatDiscriminante discriminante =CatDistritoTransformer.transformarDistritoDTOFantasma(distritoDTO,idDistrito);
			discriminanteDAO.create(discriminante);			
		}else{
			List<CatDiscriminante> discriminantes = discriminanteDAO.consultarDiscriminantesXDistrito(idDistrito, null);
			if(discriminantes!=null && !discriminantes.isEmpty()){
				CatDiscriminante discriminante =discriminantes.get(0);
				discriminante.setClave(distrito.getClaveDistrito());
				discriminante.setNombre(distrito.getNombreDist());
				distritoDAO.update(distrito);
				discriminanteDAO.update(discriminante);
			}
		}
		
		

		return idDistrito;
	}

}
