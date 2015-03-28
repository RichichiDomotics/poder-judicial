/**
 * Nombre del Programa : IngresarIntervinienteInvolucradoServiceImpl.java
 * Autor                            : LuisMG
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 14/10/2011
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.IntervinienteDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioAudienciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.IntervinienteDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.FuncionarioAudienciaId;
import mx.gob.segob.nsjp.model.Interviniente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.service.audiencia.IngresarIntervinienteInvolucradoService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.IntervinienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.IngresarFuncionarioService;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author LuisMG
 * 
 */
@Service
@Transactional
public class IngresarIntervinienteInvolucradoServiceImpl implements
		IngresarIntervinienteInvolucradoService {
	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(IngresarIntervinienteInvolucradoServiceImpl.class);
	@Autowired
	private IntervinienteDAO intervinienteDao;

	@Override
	public IntervinienteDTO ingresarIntervinienteInvolucrado(Long idAudiencia,
			Long idInvolucrado) throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA  CREAR UN INTERVINIENTE RELACIONADO A UNA AUDIENCIA E INVOLUCRADO ****/");

		/* Verificación de parámetros */
		if ((idAudiencia == null || idAudiencia <= 0)
				|| (idInvolucrado == null || idInvolucrado <= 0))
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		Interviniente interviniente = new Interviniente();
		interviniente.setAudiencia(new Audiencia(idAudiencia));
		interviniente.setInvolucrado(new Involucrado(idInvolucrado));
		IntervinienteDTO intervinienteDTO = new IntervinienteDTO();
		intervinienteDTO
				.setIntervinienteId(intervinienteDao.create(interviniente));
		logger.info("Fin -  ingresarIntervinienteInvolucrado(...)");
		return intervinienteDTO;

	}

}
