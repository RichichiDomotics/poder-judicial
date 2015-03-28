/**
 * 
 */
package mx.gob.segob.nsjp.service.parametro;

import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.parametro.ParametroDAO;
import mx.gob.segob.nsjp.dto.ParametroDTO;
import mx.gob.segob.nsjp.model.Parametro;

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
public class ConsultarParametroServiceImpl implements ConsultarParametroService {

	public final static Logger logger = Logger
			.getLogger(ConsultarParametroServiceImpl.class);
	@Autowired
	private ParametroDAO parametroDAO;

	@Override
	public ParametroDTO consultarParametro(Parametros parametro)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR VARIABLES POR CLAVE NOMBRE ****/");

		if (parametro == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		Parametro result = parametroDAO.obtenerPorClave(parametro);
		ParametroDTO parDTO=new ParametroDTO(result.getParametroId(), result
				.getClave(), result.getValor(),
				result.getDescripcion(), result.getTipoValor());
		
		return parDTO;
	}

}
