/**
 * Nombre del Programa : ValidarCadenaCustodiaEnAlmacenServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 03-ago-2011
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
package mx.gob.segob.nsjp.service.almacen.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.almacen.AlmacenDAO;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.service.almacen.ValidarCadenaCustodiaEnAlmacenService;
import mx.gob.segob.nsjp.service.cadenadecustodia.impl.transform.CadenaDeCustodiaTransformer;
import mx.gob.segob.nsjp.service.objeto.impl.transform.AlmacenTransformer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ValidarCadenaCustodiaEnAlmacenServiceImpl
        implements ValidarCadenaCustodiaEnAlmacenService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(ValidarCadenaCustodiaEnAlmacenServiceImpl.class);

    @Autowired
    private AlmacenDAO almacenDAO;

    @Override
    public boolean validarCadenaCustodiaEnAlmacen(CadenaDeCustodiaDTO cadenaDeCustodiaDto,
            AlmacenDTO almacenDto) throws NSJPNegocioException {
        if (cadenaDeCustodiaDto == null || almacenDto == null ||
                cadenaDeCustodiaDto.getFolio() == null ||
                almacenDto.getIdentificadorAlmacen() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        CadenaDeCustodia cadenaDeCustodia =
            CadenaDeCustodiaTransformer.transformarCadenaDeCustodia(cadenaDeCustodiaDto);
        Almacen almacen = AlmacenTransformer.transformarAlmacen(almacenDto);
        return almacenDAO.validarCadenaCustodiaEnAlmacen(cadenaDeCustodia, almacen);
    }
   
}
