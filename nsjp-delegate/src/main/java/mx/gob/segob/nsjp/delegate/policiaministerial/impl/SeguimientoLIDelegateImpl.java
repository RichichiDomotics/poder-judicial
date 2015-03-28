/**
 * 
 */
package mx.gob.segob.nsjp.delegate.policiaministerial.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.policiaministerial.SeguimientoLIDelegate;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO;
import mx.gob.segob.nsjp.service.policiaministerial.ConsultarSeguimientoService;
import mx.gob.segob.nsjp.service.policiaministerial.GuardarSeguimientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adrian
 *
 */
@Service("seguimientoLI")
public class SeguimientoLIDelegateImpl implements SeguimientoLIDelegate {
	
	@Autowired
	private GuardarSeguimientoService guardarSeguimientoService;
	@Autowired
	private ConsultarSeguimientoService consultarSeguimientoService;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.policiaministerial.SeguimientoLIDelegate#guardarSeguimientoLI(mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO)
	 */
	@Override
	public Long guardarSeguimientoLI(SeguimientoLIDTO seguimientoLIDTO)
			throws NSJPNegocioException {
		return guardarSeguimientoService.guardarSeguimientoLI(seguimientoLIDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.policiaministerial.SeguimientoLIDelegate#consultarSeguimientoLI(mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO)
	 */
	@Override
	public SeguimientoLIDTO consultarSeguimientoLI(
			SeguimientoLIDTO seguimientoLIDTO) throws NSJPNegocioException {
		return consultarSeguimientoService.consultarSeguimientoLI(seguimientoLIDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.policiaministerial.SeguimientoLIDelegate#consultarSeguimientosLIXNumeroExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public List<SeguimientoLIDTO> consultarSeguimientosLIXExpedienteId(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		return consultarSeguimientoService.consultarSeguimientosLIXExpedienteId(expedienteDTO);
	}

}
