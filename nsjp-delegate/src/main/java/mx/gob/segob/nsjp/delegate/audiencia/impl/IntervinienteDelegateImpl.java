/**
 * Nombre del Programa : IntervinienteDelegateImpl.java
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
package mx.gob.segob.nsjp.delegate.audiencia.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.audiencia.IntervinienteDelegate;
import mx.gob.segob.nsjp.dto.audiencia.IntervinienteDTO;
import mx.gob.segob.nsjp.service.audiencia.ActualizarIntervininenteService;
import mx.gob.segob.nsjp.service.audiencia.ConsultarIntervinientePorAudienciaService;
import mx.gob.segob.nsjp.service.audiencia.IngresarIntervinienteFuncionarioService;
import mx.gob.segob.nsjp.service.audiencia.IngresarIntervinienteInvolucradoService;
import mx.gob.segob.nsjp.service.audiencia.IngresarInvolucradoAudienciaService;
import mx.gob.segob.nsjp.service.funcionario.ActualizarFuncionarioService;

/**
 * Implementación del delegate de Interviniente.
 * 
 * @version 1.0
 * @author LuisMG
 * 
 */
@Service
public class IntervinienteDelegateImpl implements IntervinienteDelegate {

	@Autowired
	private IngresarIntervinienteInvolucradoService ingresarIntervinienteInvolucradoService;
	@Autowired
	private IngresarIntervinienteFuncionarioService ingresarIntervinienteFuncionarioService;
	@Autowired
	private ConsultarIntervinientePorAudienciaService consultarIntervinientePorAudienciaService;
	@Autowired
    private ActualizarIntervininenteService actualizarIntervinienteService;
	

	/**
	 * De acuerdo a los IdAudiencia y idInvolucrado, se crea un interviniente
	 * con los datos en default es decir en null
	 * 
	 * @param idAudiencia
	 * @param idInvolucrado
	 * @return
	 * @throws NSJPNegocioException
	 */
	@Override
	public IntervinienteDTO asociarIntervinienteInvolucrado(Long idAudiencia,
			Long idInvolucrado) throws NSJPNegocioException {
		return ingresarIntervinienteInvolucradoService
				.ingresarIntervinienteInvolucrado(idAudiencia, idInvolucrado);
	}

	/**
	 * De acuerdo a los IdAudiencia y idFuncionario, se crea un interviniente
	 * con los datos en default es decir en null
	 * 
	 * @param idAudiencia
	 * @param idFuncionario
	 * @return
	 * @throws NSJPNegocioException
	 */
	@Override
	public IntervinienteDTO asociarIntervinienteFuncionario(Long idAudiencia,
			Long idFuncionario) throws NSJPNegocioException {
		return ingresarIntervinienteFuncionarioService
		.ingresarIntervinienteFuncionario(idAudiencia, idFuncionario);
	}

	/**
	 * Regresa una lista de intervinientes, con base en una audiencia dada en
	 * caso de no encontrar intervinientes, regresará una lista vacía
	 * 
	 * @param idAudiencia
	 * @return
	 * @throws NSJPNegocioException
	 */

	
	@Override
	public List<IntervinienteDTO> consultarIntervinientesPorAudiencia(
			Long idAudiencia) throws NSJPNegocioException {
		return consultarIntervinientePorAudienciaService
		.consultarIntervinientePorAudiencia(idAudiencia);
		
	}

	/**
	 * En este servicio se va a actualizar los valores del interviniente:
	 * esAceptado, esPresente.. etc.
	 * 
	 * @return
	 */
	@Override
	public IntervinienteDTO actualizarInterviniente(
			IntervinienteDTO interviniente) throws NSJPNegocioException {
		return actualizarIntervinienteService.actualizarInterviniente (interviniente);
	}

}
