/**
 * Nombre del Programa : ConsultarIntervinientePorAudienciaServiceImpl.java
 * Autor                            : LuisMG
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 17/10/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.audiencia.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.IntervinienteDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.IntervinienteDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Interviniente;
import mx.gob.segob.nsjp.service.audiencia.ConsultarIntervinientePorAudienciaService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.AudienciaTransformer;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.IntervinienteTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author LuisMG
 * 
 */
@Service
@Transactional
public class ConsultarIntervinientePorAudienciaServiceImpl implements
		ConsultarIntervinientePorAudienciaService {

	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(ConsultarIntervinientePorAudienciaServiceImpl.class);
	@Autowired
	private IntervinienteDAO intervinienteDao;

	@Override
	public List<IntervinienteDTO> consultarIntervinientePorAudiencia(
			Long idAudiencia) throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA  CONSULTAR LOS INTERVINIENTES POR AUDIENCIA ****/");

		/* Verificación de parámetros */
		if ((idAudiencia == null || idAudiencia <= 0))
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		List<Interviniente> intervinientes = intervinienteDao
				.consultarIntervinientePorAudiencia(idAudiencia);
		List<IntervinienteDTO> intervinientesDTO = new ArrayList<IntervinienteDTO>();

		for (Interviniente interviniente : intervinientes) {
			intervinientesDTO.add(IntervinienteTransformer
					.transformarDTO(interviniente));
		}

		logger.info("Fin -  consultarIntervinientePorAudiencia(...)");
		return intervinientesDTO;

	}

}
