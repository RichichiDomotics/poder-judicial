/**
 * 
 */
package mx.gob.segob.nsjp.service.alarma.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.alarma.AlarmaDAO;
import mx.gob.segob.nsjp.dto.alarmas.AlarmaDTO;
import mx.gob.segob.nsjp.model.Alarma;
import mx.gob.segob.nsjp.service.alarma.ConsultarAlarmaService;
import mx.gob.segob.nsjp.service.alarma.impl.transform.AlarmaTransformer;

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
public class ConsultarAlarmaServiceImpl implements ConsultarAlarmaService {

	public final static Logger logger = 
		Logger.getLogger(ConsultarAlarmaServiceImpl.class);
	@Autowired
	private AlarmaDAO alarmaDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.alarma.ConsultarAlarmaService#consultarAlarmasXFuncionario(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<AlarmaDTO> consultarAlarmasXFuncionario(Long claveFuncionario,
			Long estatusAlarma) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR ALARMAS POR FUNCIONARIO ****/");
		
		if(claveFuncionario==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Alarma> alarmas= alarmaDAO.consultarAlarmasXFuncionario(claveFuncionario, estatusAlarma);
		List<AlarmaDTO> alarmasDTO=new ArrayList<AlarmaDTO>();
		for (Alarma ala : alarmas) {
			alarmasDTO.add(AlarmaTransformer.transformarAlarma(ala));
		}
		
		return alarmasDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.alarma.ConsultarAlarmaService#consultarAlarma(java.lang.Long)
	 */
	@Override
	public AlarmaDTO consultarAlarma(Long idAlarma) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR UNA ALARMA DETERMINADA ****/");
		
		if(idAlarma==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		Alarma alarma = alarmaDAO.read(idAlarma);
		
		return AlarmaTransformer.transformarAlarma(alarma);
	}

}
