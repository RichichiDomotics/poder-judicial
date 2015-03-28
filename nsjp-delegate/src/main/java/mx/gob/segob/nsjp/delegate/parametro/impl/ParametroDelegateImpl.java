/**
 * 
 */
package mx.gob.segob.nsjp.delegate.parametro.impl;

import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.parametro.ParametroDelegate;
import mx.gob.segob.nsjp.dto.ParametroDTO;
import mx.gob.segob.nsjp.service.parametro.ConsultarParametroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adrian
 *
 */
@Service
public class ParametroDelegateImpl implements ParametroDelegate {

	@Autowired
	private ConsultarParametroService consultarParametroService;
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.parametro.ParametroDelegate#consultarParametro(mx.gob.segob.nsjp.dto.ParametroDTO)
	 */
	@Override
	public ParametroDTO consultarParametro(Parametros parametro)
			throws NSJPNegocioException {
		return consultarParametroService.consultarParametro(parametro);
	}

}
