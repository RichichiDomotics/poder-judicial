/**
 * 
 */
package mx.gob.segob.nsjp.service.actividad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ActividadTransformer;

/**
 * @author MelitonBC
 *
 */
@Service
@Transactional
public class ConsultarActividadServiceImpl implements ConsultarActividadService {
	private final static Logger logger = Logger
    .getLogger(ConsultarActividadServiceImpl.class);
	
	@Autowired
	private ActividadDAO actividadDAO;
	
	@Override
	public ActividadDTO obtenerActPorExpTipoAct(Long expedienteId, Actividades actividad) throws NSJPNegocioException {		
		if(expedienteId == null || expedienteId.equals("") || actividad == null || actividad.equals("")){			
			logger.info(":::::Parametros insuficientes para ConsultarActividadServiceImpl :::::");
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		Actividad actividadADAO = this.actividadDAO.consultarActividadPorExpedienteIdTipoActividad(expedienteId, actividad.getValorId());		
		return ActividadTransformer.transformarActividad(actividadADAO);		
	}

}
