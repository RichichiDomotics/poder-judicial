/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.policiaministerial.LineaInvestigacionDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.model.LineaInvestigacion;
import mx.gob.segob.nsjp.service.policiaministerial.ConsultarLineaInvestigacionService;
import mx.gob.segob.nsjp.service.policiaministerial.impl.transformer.LineaInvestigacionTransformer;

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
public class ConsultarLineaInvestigacionServiceImpl implements
		ConsultarLineaInvestigacionService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarLineaInvestigacionServiceImpl.class);
	
	@Autowired
	private LineaInvestigacionDAO lineaInvestigacionDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.policiaministerial.ConsultarLineaInvestigacionService#consultarLineaInvestigacion(mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO)
	 */
	@Override
	public LineaInvestigacionDTO consultarLineaInvestigacion(
			LineaInvestigacionDTO investigacionDTO)throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR UNA LINEA DE INVESTIGACIÓN ****/");
		
		if(investigacionDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(investigacionDTO.getLineaInvestigacionId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		LineaInvestigacion lineaInv=lineaInvestigacionDAO.read(investigacionDTO.getLineaInvestigacionId());
				
		return LineaInvestigacionTransformer.transformarInvestigacion(lineaInv);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.policiaministerial.ConsultarLineaInvestigacionService#consultarLineasInvestigacionXSeguimiento(mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO)
	 */
	@Override
	public List<LineaInvestigacionDTO> consultarLineasInvestigacionXExpedienteId(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LAS LINEAS DE INVESTIGACION DE UN SEGUIMIENTO ****/");
		
		if(expedienteDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(expedienteDTO.getExpedienteId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<LineaInvestigacion> lineas= lineaInvestigacionDAO.consultarLineasInvestigacionXExpedienteId(expedienteDTO.getExpedienteId());
		List<LineaInvestigacionDTO> lineasDTO=new ArrayList<LineaInvestigacionDTO>();
		for (LineaInvestigacion linv : lineas) {
			lineasDTO.add(LineaInvestigacionTransformer.transformarInvestigacion(linv));
		}
		
		return lineasDTO;
	}

}
