/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.catalogo.CatDiscriminateDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatDistritoDAO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.CatDistrito;
import mx.gob.segob.nsjp.service.catalogo.RegistrarDiscriminanteService;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatDiscriminanteTransformer;

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
public class RegistrarDiscriminanteServiceImpl implements
		RegistrarDiscriminanteService {
	
	public static final Logger logger = Logger.getLogger(RegistrarDiscriminanteServiceImpl.class);
	
	@Autowired 
	private CatDiscriminateDAO discriminanteDAO;
	@Autowired 
	private CatDistritoDAO catDistritoDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.catalogo.RegistrarDiscriminanteService#registrarDiscriminante(mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO)
	 */
	@Override
	public Long registrarDiscriminante(CatDiscriminanteDTO discriminanteDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGISTRAR/MODIFICAR UN DISCRIMINANTE ****/");
		
		if(discriminanteDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if(discriminanteDTO.getClave()==null||discriminanteDTO.getNombre()==null||
				discriminanteDTO.getTipo()==null||discriminanteDTO.getDistrito()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if(discriminanteDTO.getDistrito()==null || discriminanteDTO.getDistrito().getCatDistritoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		Long idDiscrim= 0L;
		if(discriminanteDTO.getCatDiscriminanteId()==null || discriminanteDTO.getCatDiscriminanteId() < 0){
			CatDiscriminante discriminante=CatDiscriminanteTransformer.transformarCatDiscriminanteDTO(discriminanteDTO);
			idDiscrim=discriminanteDAO.create(discriminante);
		}
		else{
			CatDiscriminante discriminanteDB = discriminanteDAO.read(discriminanteDTO.getCatDiscriminanteId());
			
			//Se setean los cambios en el dis de BD
			discriminanteDB = CatDiscriminanteTransformer.transformarCatDiscriminanteUpdate (discriminanteDB, discriminanteDTO);
			
			if(discriminanteDTO.getDistrito()!= null && discriminanteDTO.getDistrito().getCatDistritoId()!= null){
				CatDistrito distrito = catDistritoDAO.read(discriminanteDTO.getDistrito().getCatDistritoId());
				discriminanteDB.setDistrito(distrito);
			}
			discriminanteDAO.update(discriminanteDB);
			idDiscrim=discriminanteDB.getCatDiscriminanteId();
		}
		return idDiscrim;
	}

}
