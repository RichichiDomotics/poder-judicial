/**
 * 
 */
package mx.gob.segob.nsjp.service.documento.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.NotificacionDAO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.ActualizarNotificacionService;

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
public class ActualizarNotificacionServiceImpl implements
		ActualizarNotificacionService {
	
	public final static Logger logger = 
		Logger.getLogger(ActualizarNotificacionServiceImpl.class);
	
	@Autowired
	private NotificacionDAO notificDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.ActualizarNotificacionService#actualizarEstatusNotificacion(mx.gob.segob.nsjp.dto.documento.NotificacionDTO, java.lang.Long)
	 */
	@Override
	public NotificacionDTO actualizarEstatusNotificacion(
			NotificacionDTO notificacionDTO, Long estatusNotificacion)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR PLANTILLAS POR TIPO DE DOCUMENTO ****/");
		
		/*Verificación de parámetros*/
		if(notificacionDTO==null||estatusNotificacion==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(notificacionDTO.getDocumentoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		/*Operación*/
		Notificacion notif = notificDAO.read(notificacionDTO.getDocumentoId());
		notif.setEstatus(new Valor(estatusNotificacion));
		
		notificDAO.update(notif);
		
		return notificacionDTO;
	}

}
