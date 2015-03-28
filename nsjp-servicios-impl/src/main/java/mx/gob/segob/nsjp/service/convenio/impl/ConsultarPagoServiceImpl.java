/**
 * 
 */
package mx.gob.segob.nsjp.service.convenio.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.medida.FechaCompromisoDAO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.model.FechaCompromiso;
import mx.gob.segob.nsjp.service.convenio.ConsultarPagoService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.FechaCompromisoTransformer;

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
public class ConsultarPagoServiceImpl implements ConsultarPagoService {

    public final static Logger logger = Logger
            .getLogger(ConsultarPagoServiceImpl.class);
    @Autowired
    private FechaCompromisoDAO fechaCompromisoDAO;

    public FechaCompromisoDTO consultarPagoXId(FechaCompromisoDTO aoFechaCompromiso)throws NSJPNegocioException{
        if (logger.isDebugEnabled()) {
            logger.debug("/**** SERVICIO PARA CONSULTAR PAGO POR ID ****/");
            logger.debug("/**** ID: " + aoFechaCompromiso.getFechaCompromisoId() + "****/");
        }
        if (aoFechaCompromiso == null || aoFechaCompromiso.getFechaCompromisoId() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        FechaCompromiso aoPagoBd = fechaCompromisoDAO.consultarPagoPorId(new FechaCompromiso(aoFechaCompromiso.getFechaCompromisoId()));
        return FechaCompromisoTransformer.transformarFechaCompromiso(aoPagoBd);
    }

}
