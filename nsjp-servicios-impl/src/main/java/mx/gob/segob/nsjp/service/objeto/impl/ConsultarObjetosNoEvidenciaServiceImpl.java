/**
 * 
 */
package mx.gob.segob.nsjp.service.objeto.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.cadenadecustodia.CadenaDeCustodiaDAO;
import mx.gob.segob.nsjp.dao.objeto.ObjetoDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.service.objeto.ConsultarObjetosNoEvidenciaService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObjetoTransformer;

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
public class ConsultarObjetosNoEvidenciaServiceImpl implements
		ConsultarObjetosNoEvidenciaService {

	public final static Logger logger = 
		Logger.getLogger(ConsultarObjetosNoEvidenciaServiceImpl.class);

	@Autowired
	private CadenaDeCustodiaDAO custodiaDAO;
	@Autowired
	private ObjetoDAO objetoDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.objeto.ConsultarObjetosNoEvidenciaService#consultarObjetosNoEvidencia(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public List<ObjetoDTO> consultarObjetosNoEvidencia(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR OBJETOS QUE NO ESTAN GUARDADOS COMO EVIDENCIA ****/");
		
		/*Verificación de parámetros*/
		if(expedienteDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(expedienteDTO.getExpedienteId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Operación*/
		List<CadenaDeCustodia> cadenas = custodiaDAO.consultarCadenaCustodiaXExpediente(new Expediente(expedienteDTO.getExpedienteId()));
		List<Objeto> objetosEvidencia=new ArrayList<Objeto>();
		for (CadenaDeCustodia cad : cadenas) {
			for (Evidencia evi : cad.getEvidencias()) {
				objetosEvidencia.add(evi.getObjeto());
			}
		}
		
		List<Objeto> objetosExpediente = objetoDAO.consultarObjetosByExpediente(expedienteDTO.getExpedienteId());
		objetosExpediente.removeAll(objetosEvidencia);
		
		List<ObjetoDTO> objetosDTOs=new ArrayList<ObjetoDTO>();
		for (Objeto obj : objetosExpediente) {
			objetosDTOs.add(ObjetoTransformer.transformarObjeto(obj));
		}
		
		return objetosDTOs;
	}

}
