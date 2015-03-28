/**
 * Nombre del Programa : ValidarExisteDelitoGraveServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 04-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.delito.impl;

import java.util.List;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.service.delito.ValidarExisteDelitoGraveService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ValidarExisteDelitoGraveServiceImpl implements ValidarExisteDelitoGraveService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(ValidarExisteDelitoGraveServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validarExisteDelitoGrave(List<DelitoDTO> delitos)
            throws NSJPNegocioException {
        if(delitos == null){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        boolean res = false;
        for (DelitoDTO delitoDTO : delitos) {
            if(delitoDTO.getEsPrincipal()){
                res = true;
                break;
            }
        }
        return res;
    }

}
