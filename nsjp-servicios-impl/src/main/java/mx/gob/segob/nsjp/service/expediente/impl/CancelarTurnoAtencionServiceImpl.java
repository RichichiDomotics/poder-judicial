/**
* Nombre del Programa : CancelarTurnoAtencionServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar la cancelacion de un turno de atencion
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
package mx.gob.segob.nsjp.service.expediente.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusTurno;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.TurnoDAO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Turno;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.CancelarTurnoAtencionService;

/**
 * Implementacion del servicio para realizar la cancelacion de un turno de atencion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class CancelarTurnoAtencionServiceImpl implements
		CancelarTurnoAtencionService {

	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(CancelarTurnoAtencionServiceImpl.class);

	@Autowired
	private TurnoDAO turnoDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.expediente.CancelarTurnoAtencionService#cancelarTurnoAtencion(mx.gob.segob.nsjp.dto.expediente.TurnoDTO)
	 */
	@Override
	public void cancelarTurnoAtencion(TurnoDTO turnoDTO, UsuarioDTO usuarioDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REALIZAR LA CANCELACION DE UN TURNO DE ATENCION ****/");
		
		if (turnoDTO==null || turnoDTO.getTurnoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if (usuarioDTO==null || usuarioDTO.getIdUsuario()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Turno turno = turnoDAO.read(turnoDTO.getTurnoId());
		turno.setEstatus(new Valor(EstatusTurno.CANCELADO.getValorId()));
		turno.setUsuario(new Usuario(usuarioDTO.getIdUsuario()));
		
		turnoDAO.update(turno);
		if (logger.isDebugEnabled())
			logger.debug("/**** EL SERVICIO REALIZO LA CANCELACION DEL TURNO DE ATENCION ****/");
		
	}

}
