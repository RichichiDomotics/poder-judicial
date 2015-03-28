package mx.gob.segob.nsjp.service.sesion.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.sesion.SesionDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.sesion.SesionDTO;
import mx.gob.segob.nsjp.model.Sesion;
import mx.gob.segob.nsjp.service.sesion.ConsultarSesionesPorIdNumeroExpedienteService;
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
public class ConsultarSesionesPorIdNumeroExpedienteServiceImpl implements ConsultarSesionesPorIdNumeroExpedienteService {
		
	public final static Logger logger = 
		Logger.getLogger(ConsultarSesionesPorIdNumeroExpedienteServiceImpl.class);
	
	@Autowired
	private SesionDAO sesionDAO;

	@Override
	public List<SesionDTO> consultarSesionesPorIdNumeroExpediente(ExpedienteDTO aoExpedienteDTO)
       throws NSJPNegocioException{
		
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para consultar Sesiones ****/");
		
		if (aoExpedienteDTO == null || aoExpedienteDTO.getNumeroExpedienteId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<SesionDTO> loSesiones = new ArrayList<SesionDTO>();
		
		List<Sesion> llSesionesBD = sesionDAO.consultarSesionesPorIdNumeroExpediente(aoExpedienteDTO.getNumeroExpedienteId());
		if(llSesionesBD != null && llSesionesBD.size() > 0)
			for (Sesion sesionBD : llSesionesBD) {
				loSesiones.add((SesionDTO)SesionTransformer.transformarSesion(sesionBD));				
			}
		return loSesiones;
	}

}
