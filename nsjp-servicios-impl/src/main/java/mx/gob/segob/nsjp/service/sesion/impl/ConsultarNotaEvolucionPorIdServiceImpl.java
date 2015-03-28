package mx.gob.segob.nsjp.service.sesion.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.sesion.NotaEvolucionDAO;
import mx.gob.segob.nsjp.dto.sesion.NotaEvolucionDTO;
import mx.gob.segob.nsjp.model.NotaEvolucion;
import mx.gob.segob.nsjp.service.sesion.ConsultarNotaEvolucionPorIdService;
import mx.gob.segob.nsjp.service.sesion.impl.transform.SesionTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rgama
 *
 */
@Service
@Transactional
public class ConsultarNotaEvolucionPorIdServiceImpl implements ConsultarNotaEvolucionPorIdService {
		
	public final static Logger logger = 
		Logger.getLogger(ConsultarNotaEvolucionPorIdServiceImpl.class);
	
	@Autowired
	private NotaEvolucionDAO notaEvolucionDAO;

	@Override
    public NotaEvolucionDTO consultarNotaEvolucionPorId(NotaEvolucionDTO aoNotaEvolucionDTO)
    throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para consultar NotaEvolucion ****/");
		
		if (aoNotaEvolucionDTO == null || aoNotaEvolucionDTO.getSesionId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		NotaEvolucionDTO loNotaEvolucionBD = null;
		NotaEvolucion aoNotaEvolucion = notaEvolucionDAO.consultarNotaEvolucionPorId(
				(NotaEvolucion)SesionTransformer.transformarSesionDTO(aoNotaEvolucionDTO));
		if(aoNotaEvolucion != null)
			loNotaEvolucionBD = (NotaEvolucionDTO)SesionTransformer.transformarSesion(aoNotaEvolucion);
		return loNotaEvolucionBD;
	}

}
