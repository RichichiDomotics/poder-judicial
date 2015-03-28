/**
 * Nombre del Programa : ConsultarRelacionesXCategoriaServiceImpl.java
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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.relacion.CatRelacionDAO;
import mx.gob.segob.nsjp.dto.relacion.CatCategoriaRelacionDTO;
import mx.gob.segob.nsjp.dto.relacion.CatRelacionDTO;
import mx.gob.segob.nsjp.model.CatCategoriaRelacion;
import mx.gob.segob.nsjp.model.CatRelacion;
import mx.gob.segob.nsjp.service.relacion.ConsultarCatRelacionesXCatCategoriaRelacionService;
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
public class ConsultarCatRelacionesXCatCategoriaRelacionServiceImpl
        implements ConsultarCatRelacionesXCatCategoriaRelacionService {

    /**
     * Logger de la clase.
     */
    private final static Logger logger = Logger.
            getLogger(ConsultarCatRelacionesXCatCategoriaRelacionServiceImpl.class);

    @Autowired
    private CatRelacionDAO catRelacionDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CatRelacionDTO> consultarCatRelacionesXCatCategoriaRelacion(
            CatCategoriaRelacionDTO catCategoriaRelacionDTO) throws NSJPNegocioException {
        if (catCategoriaRelacionDTO == null
                || catCategoriaRelacionDTO.getCatCategoriaRelacionId() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        CatCategoriaRelacion catCategoriaRelacion = new CatCategoriaRelacion();
        catCategoriaRelacion.setCatCategoriaRelacionId(
                catCategoriaRelacionDTO.getCatCategoriaRelacionId());
        List<CatRelacion> catRelaciones = catRelacionDao.
                consultarCatRelacionesXCatCategoriaRelacion(catCategoriaRelacion);
        List<CatRelacionDTO> catRelacionesDto = Collections.EMPTY_LIST;
        if (!catRelaciones.isEmpty()) {
            catRelacionesDto = new LinkedList<CatRelacionDTO>();
            for (CatRelacion catRelacion : catRelaciones) {
                CatRelacionDTO catRelacionDto =
                        CatRelacionTransformer.transformarCatRelacion(catRelacion);
                catRelacionesDto.add(catRelacionDto);
            }
        }
        return catRelacionesDto;
    }
}
