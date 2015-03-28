/**
 * Nombre del Programa : RegistrarRelacionServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 13-jul-2011
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
import mx.gob.segob.nsjp.comun.enums.relacion.TipoRelacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.model.CatRelacion;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.service.relacion.RegistrarRelacionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class RegistrarRelacionServiceImpl implements RegistrarRelacionService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(RegistrarRelacionServiceImpl.class);

    @Autowired
    private RelacionDAO relacionDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void registrarRelacion(Long idCatRelacion,
            Long idElementoSujeto, Long idElementoComplemento)
            throws NSJPNegocioException {
        if (idCatRelacion == null ||
                idElementoSujeto == null || idElementoComplemento == null) {
                throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Relacion relacion = new Relacion();
        CatRelacion catRelacion = new CatRelacion();
        catRelacion.setCatRelacionId(idCatRelacion);
        relacion.setCatRelacion(catRelacion);
        relacion.setElementoByComplementoId(new Elemento(idElementoComplemento));
        relacion.setElementoBySujetoId(new Elemento(idElementoSujeto));
        relacion.setTipoRelacion(TipoRelacion.EXPLICITA.getValorId().shortValue());
        relacion.setEsActivo(true);
        relacionDao.create(relacion);
    }
   
}
