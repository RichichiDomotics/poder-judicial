/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.policiaministerial.SeguimientoLIDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO;
import mx.gob.segob.nsjp.model.SeguimientoLI;
import mx.gob.segob.nsjp.service.policiaministerial.ConsultarSeguimientoService;
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
public class ConsultarSeguimientoServiceImpl implements
		ConsultarSeguimientoService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarSeguimientoServiceImpl.class);
	
	@Autowired
	private SeguimientoLIDAO seguimientoDAO;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.policiaministerial.ConsultarSeguimientoService#consultarSeguimientoLI(mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO)
	 */
	@Override
	public SeguimientoLIDTO consultarSeguimientoLI(
			SeguimientoLIDTO seguimientoLIDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR UN SEGUIMIENTO ****/");
		
		if(seguimientoLIDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(seguimientoLIDTO.getSeguimientoLIId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		return SeguimientoLITransformer.transformarSeguimiento(seguimientoDAO.read(seguimientoLIDTO.getSeguimientoLIId()));
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.policiaministerial.ConsultarSeguimientoService#consultarSeguimientosLIXNumeroExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public List<SeguimientoLIDTO> consultarSeguimientosLIXExpedienteId(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LOS SEGUIMIENTOS DE UN NÚMERO DE EXPEDIENTE ****/");
		
		if(expedienteDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<SeguimientoLI> seguimientos=seguimientoDAO.consultarSeguimientosLIXExpedienteId(expedienteDTO.getExpedienteId());
		List<SeguimientoLIDTO> seguimientosDTO=new ArrayList<SeguimientoLIDTO>();
		for (SeguimientoLI seguimientoLI : seguimientos) {
			seguimientosDTO.add(SeguimientoLITransformer.transformarSeguimiento(seguimientoLI));
		}
		
		return seguimientosDTO;
	}

}
