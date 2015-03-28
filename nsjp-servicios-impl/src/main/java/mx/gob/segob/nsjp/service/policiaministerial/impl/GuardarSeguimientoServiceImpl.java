/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.policiaministerial.SeguimientoLIDAO;
import mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO;
import mx.gob.segob.nsjp.model.SeguimientoLI;
import mx.gob.segob.nsjp.service.policiaministerial.GuardarSeguimientoService;
import mx.gob.segob.nsjp.service.policiaministerial.impl.transformer.SeguimientoLITransformer;

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
public class GuardarSeguimientoServiceImpl implements GuardarSeguimientoService {
	
	public final static Logger logger = 
		Logger.getLogger(GuardarSeguimientoServiceImpl.class);
	
	@Autowired
	private SeguimientoLIDAO seguimientoDAO;


	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.policiaministerial.GuardarSeguimientoService#guardarSeguimientoLI(mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO)
	 */
	@Override
	public Long guardarSeguimientoLI(SeguimientoLIDTO seguimientoLIDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR O ACTUALIZAR UN SEGUIMIENTO ****/");
		
		if(seguimientoLIDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(seguimientoLIDTO.getExpedienteDTO()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
				
		/*Transformación*/
		SeguimientoLI seguimiento = SeguimientoLITransformer.transformarSeguimientoDTO(seguimientoLIDTO);
		
		/*Operación*/
		Long idSeguimiento=seguimiento.getSeguimientoLIId();
		if(seguimiento.getSeguimientoLIId()==null|| seguimiento.getSeguimientoLIId().equals(0))
			idSeguimiento=seguimientoDAO.create(seguimiento);
		else
			seguimientoDAO.update(seguimiento);
		
		return idSeguimiento;
	}

}
