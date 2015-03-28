/**
 * 
 */
package mx.gob.segob.nsjp.service.quejaciudadana.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.quejaciudadana.EstatusQueja;
import mx.gob.segob.nsjp.comun.enums.quejaciudadana.MotivoRechazo;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.quejaciudadana.QuejaCiudadanaDAO;
import mx.gob.segob.nsjp.model.QuejaCiudadana;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.quejaciudadana.AsignarMotivoRechazoService;

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
public class AsignarMotivoRechazoServiceImpl implements
		AsignarMotivoRechazoService {
	
	public final static Logger logger = 
		Logger.getLogger(AsignarMotivoRechazoServiceImpl.class);
	
	@Autowired
	private QuejaCiudadanaDAO quejaDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.quejaciudadana.AsignarMotivoRechazoService#asignarMotivoRechazo(java.lang.Long, mx.gob.segob.nsjp.comun.enums.quejaciudadana.MotivoRechazo)
	 */
	@Override
	public void asignarMotivoRechazo(Long idQueja, MotivoRechazo motivoRechazo)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ASIGNAR UN MOTIVO RECHAZO Y CAMBIAR ESTATUS DE UNA QUEJA****/");
		
		if(idQueja==null||motivoRechazo==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		QuejaCiudadana queja = quejaDAO.read(idQueja);
		queja.setMotivoRechazo(new Valor(motivoRechazo.getValorId()));
		queja.setEstatusQueja(new Valor(EstatusQueja.TERMINADA.getValorId()));
		
		quejaDAO.update(queja);

	}

}
