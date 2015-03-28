/**
 * 
 */
package mx.gob.segob.nsjp.service.implicado.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.quejaciudadana.ImplicadoDAO;
import mx.gob.segob.nsjp.dto.involucrado.ImplicadoDTO;
import mx.gob.segob.nsjp.model.Implicado;
import mx.gob.segob.nsjp.service.implicado.ConsultarImplicadoService;
import mx.gob.segob.nsjp.service.implicado.impl.transform.ImplicadoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rgama
 * 
 */
@Service
@Transactional
public class ConsultarImplicadoServiceImpl implements ConsultarImplicadoService {

    public final static Logger logger = Logger
            .getLogger(ConsultarImplicadoServiceImpl.class);
    @Autowired
    private ImplicadoDAO implicadoDAO;

    public ImplicadoDTO consultarImplicadoXId(ImplicadoDTO aoImplicado)throws NSJPNegocioException{
        if (logger.isDebugEnabled()) {
            logger.debug("/**** SERVICIO PARA CONSULTAR IMPLICADO POR ID ****/");
            logger.debug("/**** ID: " + aoImplicado.getImplicadoId() + "****/");
        }
        if (aoImplicado == null || aoImplicado.getImplicadoId() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Implicado implicado = implicadoDAO.consultarImplicadoPorId(new Implicado(aoImplicado.getImplicadoId()));
        return ImplicadoTransformer.transformarImplicado(implicado);
    }
}
