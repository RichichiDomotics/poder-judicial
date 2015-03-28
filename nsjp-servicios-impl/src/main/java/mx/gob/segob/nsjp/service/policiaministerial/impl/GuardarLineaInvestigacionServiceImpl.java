/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.policiaministerial.LineaInvestigacionDAO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.model.LineaInvestigacion;
import mx.gob.segob.nsjp.service.policiaministerial.GuardarLineaInvestigacionService;
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
public class GuardarLineaInvestigacionServiceImpl implements
		GuardarLineaInvestigacionService {
	
	public final static Logger logger = 
		Logger.getLogger(GuardarLineaInvestigacionServiceImpl.class);
	
	@Autowired
	private LineaInvestigacionDAO investigacionDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.policiaministerial.GuardarLineaInvestigacionService#guardarLineaInvestigacion(mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO)
	 */
	@Override
	public Long guardarLineaInvestigacion(LineaInvestigacionDTO investigacionDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR O ACTUALIZAR UNA LINEA DE INVESTIGACIÓN ****/");
		
		if(investigacionDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		
		/*Transformación*/
		LineaInvestigacion lineaInv=LineaInvestigacionTransformer.transformarInvestigacionDTO(investigacionDTO);
		
		/*Operación*/
		Long idLinea=lineaInv.getLineaInvestigacionId();
		if(lineaInv.getLineaInvestigacionId()==null|| lineaInv.getLineaInvestigacionId().equals(0)) {
			Integer maxConsecutivo = investigacionDAO.maxConsecutivoPorExp(lineaInv.getExpediente().getExpedienteId());
			
			if (maxConsecutivo!=null) {
				maxConsecutivo=maxConsecutivo+1;
				lineaInv.setConsecutivo(maxConsecutivo.intValue());
			} else {
				lineaInv.setConsecutivo(1);
			}
			
			idLinea=investigacionDAO.create(lineaInv);
		}			
		else
			investigacionDAO.update(lineaInv);
		
		return idLinea;
	}

	@Override
	public void actualizarLineaConImpreso(Long lineaInvestigacionId)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ACTUALIZAR QUE LINEA DE INVESTIGACIÓN FUE IMPRESA ****/");
		
		LineaInvestigacion linea = investigacionDAO.read(lineaInvestigacionId);
		linea.setEsImpreso(true);
		investigacionDAO.update(linea);
		
	}

}
