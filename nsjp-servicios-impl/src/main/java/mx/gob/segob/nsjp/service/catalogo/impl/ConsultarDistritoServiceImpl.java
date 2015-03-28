/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.catalogo.CatDistritoDAO;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;
import mx.gob.segob.nsjp.model.CatDistrito;
import mx.gob.segob.nsjp.service.catalogo.ConsultarDistritoService;
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
public class ConsultarDistritoServiceImpl implements ConsultarDistritoService {
	
	public static final Logger logger = Logger.getLogger(ConsultarDistritoServiceImpl.class);

	@Autowired
	private CatDistritoDAO distritoDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.catalogo.ConsultarDistritoService#consultarDistritos()
	 */
	@Override
	public List<CatDistritoDTO> consultarDistritos()
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR TODOS LOS DISTRITOS****/");
		
		List<CatDistrito> distritos = distritoDAO.findAll(null, true);
		List<CatDistritoDTO> distritosDTO=new ArrayList<CatDistritoDTO>();
		for (CatDistrito dist : distritos) {
			distritosDTO.add(CatDistritoTransformer.transformarDistritoSimple(dist));
		}
		return distritosDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.catalogo.ConsultarDistritoService#consultarDistritoXId(java.lang.Long)
	 */
	@Override
	public CatDistritoDTO consultarDistritoXId(Long distritoID)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR UN DISTRITO****/");
		
		if(distritoID==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		CatDistrito distrito = distritoDAO.read(distritoID);
		
		return CatDistritoTransformer.transformarDistritoCompleto(distrito);
	}

}
