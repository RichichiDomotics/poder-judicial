/**
 * Nombre del Programa : ActualizarIntervininenteServiceImpl.java
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.IntervinienteDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.audiencia.IntervinienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Interviniente;
import mx.gob.segob.nsjp.service.audiencia.ActualizarIntervininenteService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.IntervinienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author LuisMG
 * 
 */
@Service
@Transactional
public class ActualizarIntervininenteServiceImpl implements
		ActualizarIntervininenteService {
	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(ActualizarIntervininenteServiceImpl.class);
	@Autowired
	private IntervinienteDAO intervininenteDao;

	@Override
	public IntervinienteDTO actualizarInterviniente(
			IntervinienteDTO intervinienteDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled()) {
			logger.debug("/SERVICIO PARA MODIFICAR UN INTERVININENTE/");
			logger.debug("intervininenteDTO :: " + intervinienteDTO);
		}
		Interviniente interviniente = intervininenteDao.read(intervinienteDTO.getIntervinienteId());
		
		interviniente.setEsAceptado(intervinienteDTO.isEsAceptado());
		interviniente.setEsParteFiscalia(intervinienteDTO.isEsParteFiscalia());
		interviniente.setEsPresente(intervinienteDTO.isEsPresente());
		interviniente.setEsSuperVeniente(intervinienteDTO.isEsSuperVeniente());
		interviniente.setEsTitular(intervinienteDTO.isEsTitular());
		intervininenteDao.update(interviniente);

		return intervinienteDTO;
		
	}

	

}
