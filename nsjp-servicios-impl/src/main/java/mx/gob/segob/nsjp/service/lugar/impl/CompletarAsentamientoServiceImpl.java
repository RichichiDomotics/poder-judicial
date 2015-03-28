/**
 * 
 */
package mx.gob.segob.nsjp.service.lugar.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.domicilio.AsentamientoDAO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.model.Asentamiento;
import mx.gob.segob.nsjp.service.lugar.CompletarAsentamientoService;
import mx.gob.segob.nsjp.service.lugar.impl.transform.AsentamientoTransformer;

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
public class CompletarAsentamientoServiceImpl implements
		CompletarAsentamientoService {

	public final static Logger logger = 
		Logger.getLogger(CompletarAsentamientoServiceImpl.class);
	
	@Autowired
	private AsentamientoDAO asentamientoDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.lugar.CompletarAsentamientoService#completarAsentamientoXCodigoPostal(java.lang.String)
	 */
	@Override
	public List<AsentamientoDTO> completarAsentamientoXCodigoPostal(
			String codigoPostal) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA COMPLETAR UN ASENTAMIENTO POR CODIGO POSTAL****/");

		if(codigoPostal==null || codigoPostal.isEmpty()||codigoPostal.equals(""))
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Asentamiento> asentamientos = asentamientoDAO.consultarPorCP(codigoPostal);
		List<AsentamientoDTO> asentamientosDTO=new ArrayList<AsentamientoDTO>();
		for (Asentamiento asent : asentamientos) {
			asentamientosDTO.add(AsentamientoTransformer.transformarAsentamiento(asent));
		}

		return asentamientosDTO;
	}

	@Override
	public String obtenerCodigoPostalXIdAsentamiento(Long idAsentamiento) throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** obtenerCodigoPostalXIdAsentamiento ****/");

		if(idAsentamiento==null || idAsentamiento <= 0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Asentamiento asentamiento = asentamientoDAO.read(idAsentamiento);
		return (asentamiento != null && asentamiento.getCodigoPostal() != null? asentamiento.getCodigoPostal():"");
	}

	@Override
	public AsentamientoDTO obtenerAentamientoPrId(Long idAsentamiento)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA OBTENER UN ASENTAMIENTO POR ID****/");

		if(idAsentamiento==null || idAsentamiento.intValue()<=0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Asentamiento asent = asentamientoDAO.read(idAsentamiento);
		AsentamientoDTO asentDTO = AsentamientoTransformer.transformarAsentamiento(asent);		

		return asentDTO;
	}

}
