/**
 * Nombre del Programa : ValidarRelacionXActividadServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 12-jul-2011
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
package mx.gob.segob.nsjp.service.relacion.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.service.relacion.ValidarRelacionXActividadService;
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
public class ValidarRelacionXActividadServiceImpl
        implements ValidarRelacionXActividadService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(ValidarRelacionXActividadServiceImpl.class);

    @Autowired
    private RelacionDAO relacionDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validarRelacion(Long idCatRelacion, Long idElementoUno, Long idElementoDos)
            throws NSJPNegocioException {
        if (idCatRelacion == null || idElementoUno == null || idElementoDos == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        return relacionDao.existeRelacion(idCatRelacion, idElementoUno, idElementoDos);
    }
   
}
