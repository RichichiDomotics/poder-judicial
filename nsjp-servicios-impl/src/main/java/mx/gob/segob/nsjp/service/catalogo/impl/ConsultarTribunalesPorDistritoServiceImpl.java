/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.catalogo.TipoDiscriminante;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.catalogo.CatDiscriminateDAO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteWSDTO;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.service.catalogo.ConsultarTribunalesPorDistritoService;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatDiscriminanteTransformer;
import mx.gob.segob.nsjp.service.infra.impl.transform.consultarcatalogo.CatDiscriminanteWSDTOTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rgama
 *
 */
@Service("consultarTribunalesPorDistritoService")
@Transactional
public class ConsultarTribunalesPorDistritoServiceImpl implements
 	ConsultarTribunalesPorDistritoService {
	
	public static final Logger logger = Logger.getLogger(ConsultarTribunalesPorDistritoServiceImpl.class);
	
	@Autowired 
	private CatDiscriminateDAO discriminateDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.catalogo.ConsultarDiscriminanteService#consultarDiscriminantesXDistrito(java.lang.Long)
	 */
	@Override
	public List<CatDiscriminanteWSDTO> consultarTribunalesPorDistrito(Long distritoID)throws NSJPNegocioException{

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR TRIBUNALES POR DISTRITO****/");
		
		if(distritoID==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);		

		List<CatDiscriminanteWSDTO> dicriminantesWSDTO = new ArrayList<CatDiscriminanteWSDTO>();
		List<CatDiscriminante> discriminantes = discriminateDAO.consultarDiscriminantesXDistrito(distritoID,TipoDiscriminante.TRIBUNAL.ordinal());
		List<CatDiscriminanteDTO> discriminatesDTO=new ArrayList<CatDiscriminanteDTO>();
		for (CatDiscriminante discr : discriminantes) {
			CatDiscriminanteDTO dto = CatDiscriminanteTransformer.transformarCatDiscriminante(discr);  
			discriminatesDTO.add(dto);
			dicriminantesWSDTO.add(CatDiscriminanteWSDTOTransformer.transformarCatDiscriminante(dto));
		}
		
		return dicriminantesWSDTO;
	}
	
	@Override
	public List<CatDiscriminanteWSDTO> consultarAgenciasPorDistrito(Long idAgencia)throws NSJPNegocioException{

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR AGENCIAS POR DISTRITO****/");
		
		if(idAgencia==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);		

		List<CatDiscriminanteWSDTO> dicriminantesWSDTO = new ArrayList<CatDiscriminanteWSDTO>();
		List<CatDiscriminante> discriminantes = discriminateDAO.consultarDiscriminantesXDistrito(idAgencia,TipoDiscriminante.AGENCIA.ordinal());
		List<CatDiscriminanteDTO> discriminatesDTO=new ArrayList<CatDiscriminanteDTO>();
		for (CatDiscriminante discr : discriminantes) {
			CatDiscriminanteDTO dto = CatDiscriminanteTransformer.transformarCatDiscriminante(discr);  
			discriminatesDTO.add(dto);
			dicriminantesWSDTO.add(CatDiscriminanteWSDTOTransformer.transformarCatDiscriminante(dto));
}
		
		return dicriminantesWSDTO;
	}
}
