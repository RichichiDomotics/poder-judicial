/**
 * 
 */
package mx.gob.segob.nsjp.service.alarma.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.alarma.AlarmaDAO;
import mx.gob.segob.nsjp.dao.alerta.AlertaDAO;
import mx.gob.segob.nsjp.dto.alarmas.AlarmaDTO;
import mx.gob.segob.nsjp.model.Alarma;
import mx.gob.segob.nsjp.model.Alerta;
import mx.gob.segob.nsjp.service.alarma.RegistrarAlarmaService;
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
public class RegistrarAlarmaServiceImpl implements RegistrarAlarmaService {

	public final static Logger logger = Logger
			.getLogger(RegistrarAlarmaServiceImpl.class);
	
	@Autowired
	private AlarmaDAO alarmaDAO;
	@Autowired
	private AlertaDAO alertaDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.alarma.RegistrarAlarmaService#registrarAlarma
	 * (mx.gob.segob.nsjp.dto.alarmas.AlarmaDTO)
	 */
	@Override
	public Long registrarAlarma(AlarmaDTO alarmaDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA GUARDAR O ACTUALIZAR UNA ALARMA ****/");

		if (alarmaDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(alarmaDTO.getFechaAlarma()==null||alarmaDTO.getMotivo()==null||alarmaDTO.getDatosAsociados()==null||alarmaDTO.getFuncionario()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		Long idAlarma=alarmaDTO.getAlarmaId();
		Alarma alarma=AlarmaTransformer.transformarAlarmaDTO(alarmaDTO);
		List<Alerta> alertas = alarma.getAlertas();
		alarma.setAlertas(null);
		/*ALARMA*/
		if(alarma.getAlarmaId()==null)
			idAlarma=alarmaDAO.create(alarma);
		else
			alarmaDAO.update(alarma);
		/*ALERTAS*/
		if(alertas!=null){
			if(alertas.size()>0){
				for (Alerta ale : alertas) {
					ale.setAlarma(new Alarma(idAlarma));
					if(ale.getAlertaId()==null)
						alertaDAO.create(ale);
					else
						alertaDAO.update(ale);
				}
			}
		}
		
		return idAlarma;
	}

}
