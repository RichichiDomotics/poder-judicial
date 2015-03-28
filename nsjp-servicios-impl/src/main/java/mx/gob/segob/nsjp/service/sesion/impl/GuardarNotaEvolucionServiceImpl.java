package mx.gob.segob.nsjp.service.sesion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.sesion.NotaEvolucionDAO;
import mx.gob.segob.nsjp.dao.sesion.SesionDAO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.NotaEvolucionDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NotaEvolucion;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.sesion.GuardarNotaEvolucionService;
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
public class GuardarNotaEvolucionServiceImpl implements GuardarNotaEvolucionService {
    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDAO;  	
	
	public final static Logger logger = 
		Logger.getLogger(GuardarNotaEvolucionServiceImpl.class);
	
	@Autowired
	private NotaEvolucionDAO NotaEvolucionDAO;
	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired
	private SesionDAO sesionDAO;

	@Override
	public NotaEvolucionDTO guardarNotaEvolucion(NotaEvolucionDTO aoNotaEvolucionDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para GUARDAR O ACTUALIZAR NotaEvolucion ****/");
		
		if (aoNotaEvolucionDTO == null || aoNotaEvolucionDTO.getNumeroExpediente() == null
				|| aoNotaEvolucionDTO.getNumeroExpediente().getNumeroExpedienteId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        NumeroExpediente numeroExpediente = new NumeroExpediente();

				
		/*Transformación*/
		NotaEvolucion aoNotaEvolucion = (NotaEvolucion)SesionTransformer.transformarSesionDTO(aoNotaEvolucionDTO);
		
		/*Operación*/
		Long idObjeto= aoNotaEvolucion.getSesionId();
		NotaEvolucionDTO loObjetoBD = new NotaEvolucionDTO();
		loObjetoBD.setSesionId(idObjeto);
		Short numeroDeSesion = sesionDAO.obtenerSiguinteNumeroDeSesionPorNumeroExpediente(aoNotaEvolucionDTO.getNumeroExpediente().getNumeroExpedienteId()).shortValue();
		loObjetoBD.setNumeroSesion(aoNotaEvolucionDTO.getNumeroSesion());
		logger.debug("/**** El nuevo numero de sesion es:::::::::: ****/" + numeroDeSesion);

		if(idObjeto==null|| idObjeto.equals(0)){
			loObjetoBD.setNumeroSesion(numeroDeSesion);
			aoNotaEvolucion.setNumeroSesion(numeroDeSesion);
			Expediente expediente = new Expediente();			
			numeroExpediente = numeroExpedienteDAO.read( aoNotaEvolucionDTO.getNumeroExpediente().getNumeroExpedienteId());
			expediente = numeroExpediente.getExpediente();
			List<Involucrado> victimas = involucradoDAO.obtenerInvByIdExpAndCalidad(expediente.getExpedienteId(),
		    	Calidades.VICTIMA_PERSONA.getValorId(), null);
			if(victimas != null && victimas.size() > 0)
				aoNotaEvolucion.setVictima(victimas.get(0));
			idObjeto = NotaEvolucionDAO.create(aoNotaEvolucion);
			loObjetoBD.setSesionId(idObjeto);
		}			
		else
			NotaEvolucionDAO.update(aoNotaEvolucion);
		
		
		return loObjetoBD;
	}

}
