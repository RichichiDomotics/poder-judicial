/**
 * 
 */
package mx.gob.segob.nsjp.service.actividad.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.service.actividad.ConsultarActividadesXTipoAtencionExpedienteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AlejandroGA
 *
 */
@Service
@Transactional
public class ConsultarActividadesXTipoAtencionExpedienteServiceImpl implements ConsultarActividadesXTipoAtencionExpedienteService{
	
	@Autowired
	private ActividadDAO actividadDAO;
	 /**
     * Logger de la clase.
     */
   private final static Logger logger = Logger
           .getLogger(ConsultarActividadesXTipoAtencionExpedienteServiceImpl.class);

	@Override
	public Long consultarNumeroDeActividadesXTipoAtencion(Long expedienteId,
			Long tipoAtencionId) throws NSJPNegocioException{
		
		if(expedienteId == null || expedienteId.equals(0L)){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		if(tipoAtencionId == null || tipoAtencionId.equals(0L)){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		logger.info("BIENVENIDO AL SERVICIO: consultarNumeroDeActividadesXTipoAtencion");
		logger.info("VERIFICANDO PARAMETROS");
		logger.info("expedienteId"+expedienteId);
		logger.info("tipoAtencionId"+tipoAtencionId);
		
		Long numeroRegistros = actividadDAO.consultarNumeroActividadesPorTipoAtencionExpedienteId(expedienteId, tipoAtencionId);
		
		return numeroRegistros;
	}

}
