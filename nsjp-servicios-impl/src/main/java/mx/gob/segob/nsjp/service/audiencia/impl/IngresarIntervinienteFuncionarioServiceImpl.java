/**
* Nombre del Programa : IngresarIntervinienteFuncionarioServiceImpl.java
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.IntervinienteDAO;
import mx.gob.segob.nsjp.dto.audiencia.IntervinienteDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Interviniente;

import mx.gob.segob.nsjp.service.audiencia.IngresarIntervinienteFuncionarioService;

import mx.gob.segob.nsjp.service.audiencia.impl.transform.IntervinienteTransformer;


/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author LuisMG
 *
 */
@Service
@Transactional
public class IngresarIntervinienteFuncionarioServiceImpl implements IngresarIntervinienteFuncionarioService {
	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(IngresarIntervinienteFuncionarioServiceImpl.class);
	@Autowired
	private IntervinienteDAO intervinienteDao;
	private AudienciaDAO audienciaDao;

	@Override
	public IntervinienteDTO ingresarIntervinienteFuncionario(Long idAudiencia,
			Long idFuncionario) throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA  CREAR UN INTERVINIENTE RELACIONADO A UNA AUDIENCIA Y FUNCIONARIO ****/");

		/* Verificación de parámetros */
		if ((idAudiencia == null || idAudiencia <= 0)
				|| (idFuncionario == null || idFuncionario <= 0))
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		Interviniente interviniente = new Interviniente();
		interviniente.setAudiencia(new Audiencia(idAudiencia));
		interviniente.setFuncionario(new Funcionario(idFuncionario));
		
		
		IntervinienteDTO resp = new IntervinienteDTO();
		
		
				resp.setIntervinienteId(intervinienteDao.create(interviniente));
		logger.info("Fin -  ingresarIntervinienteFuncionario(...)");
		interviniente = intervinienteDao.read(interviniente.getIntervinienteId());
		return resp;

	}

	

	

}
