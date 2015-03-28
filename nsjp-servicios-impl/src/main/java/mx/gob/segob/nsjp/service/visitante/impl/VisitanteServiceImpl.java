/**
 * 
 */
package mx.gob.segob.nsjp.service.visitante.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.visitante.VisitanteDAO;
import mx.gob.segob.nsjp.dto.visitante.VisitanteDTO;
import mx.gob.segob.nsjp.model.Visitante;
import mx.gob.segob.nsjp.service.visitante.VisitanteService;
import mx.gob.segob.nsjp.service.visitante.impl.transform.VisitanteTransformer;

/**
 * @author LuisMG
 * 
 */
@Service
@Transactional
public class VisitanteServiceImpl implements VisitanteService {
	@Autowired
	private VisitanteDAO visitanteDAO;
	private static final Logger logger = Logger
			.getLogger(VisitanteServiceImpl.class);

	/**
	 * Si no existe un visitante lo registra, si ya existe lo actualiza el
	 * registro existente y en caso de algún error envía false
	 * 
	 * @param visitante
	 * @return
	 * @throws NSJPNegocioException
	 */
	@Override
	public boolean registrarVisitante(VisitanteDTO visitanteDTO)
			throws NSJPNegocioException {
		boolean resp = false;
		Visitante visitante = new Visitante();
		if (visitanteDTO.getcIP() != null) {
			visitante=visitanteDAO.consultarVisitantePorIP(visitanteDTO.getcIP());
			if (visitante == null)
				resp = visitanteDAO.registrarVisitante(VisitanteTransformer
						.transformarDTO(visitanteDTO));
			else{
				visitante.setiIntentos(visitante.getiIntentos()+1);
				resp = visitanteDAO.actualizarVisitantePorIP(visitante);
			}
				
		}

		return resp;
	}

	/**
	 * Elimina una visita dada una IP y regresa True en caso de existir el
	 * registro o false en caso de que no exista el registro
	 * 
	 * @param visitante
	 * @return
	 * @throws NSJPNegocioException
	 */
	@Override
	public boolean eliminarVisitantePorIP(VisitanteDTO visitanteDTO)
			throws NSJPNegocioException {
		boolean resp = false;
		
		if (visitanteDTO!=null){
			if (visitanteDTO.getcIP()!=null){
				if(visitanteDAO.consultarVisitantePorIP(visitanteDTO.getcIP())!=null)
					resp=visitanteDAO.borrarVisitantePorIP(VisitanteTransformer.transformarDTO(visitanteDTO));
				
			}
		}
		return resp;
	}

	/**
	 * Consulta un visitante con base en su dirección IP y regresa Null en caso
	 * de que el visitante no se encuentre registrado
	 * 
	 * @param visitante
	 * @return
	 * @throws NSJPNegocioException
	 */
	@Override
	public VisitanteDTO consultarVisitantePorIP(VisitanteDTO visitanteDTO)
			throws NSJPNegocioException {
		VisitanteDTO resp = null;
		if (visitanteDTO.getcIP()!=null){
			logger.info("IP a consultar:" + visitanteDTO.getcIP());
			resp= VisitanteTransformer.transformar(visitanteDAO.consultarVisitantePorIP(visitanteDTO.getcIP()));
			logger.info("Los Intentos fueron:" + visitanteDTO.getiIntentos());
			
			
		}
		return resp;
	}

	 /**
     * Método encargado de reiniciar la cuenta de intentos de una dirección IP registrada a cero
     * @throws NSJPNegocioException
     */
	@Override
    public void desbloquearVisitante()throws NSJPNegocioException{
		try {
			visitanteDAO.actualizarIPVisitante();
		} catch (Exception e) {
			throw new NSJPNegocioException(
					CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO);
		}
		
		
		
	}

}
