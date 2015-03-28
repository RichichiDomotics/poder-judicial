/**
 * Nombre del Programa : ConsultarElementosXActividadServiceImpl.java
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
package mx.gob.segob.nsjp.service.elemento.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.ElementoDAO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.service.elemento.ConsultarElementosXActividadService;
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
public class ConsultarElementosXActividadServiceImpl
        implements ConsultarElementosXActividadService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(ConsultarElementosXActividadServiceImpl.class);

    @Autowired
    private ElementoDAO elementoDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ElementoDTO> consultarElementosXActividad(Long idActividad)
            throws NSJPNegocioException {
        if (idActividad == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        List<Elemento> elementos = elementoDao.consultarElementoXActividad(idActividad);
        List<ElementoDTO> elementosDto = Collections.EMPTY_LIST;
        if (!elementos.isEmpty()) {
            elementosDto = new LinkedList<ElementoDTO>();
            for (Elemento elemento : elementos) {
                ElementoDTO elementoDto = ElementoTransformer.transformarElemento(elemento);
                elementosDto.add(elementoDto);
            }
        }
        return elementosDto;
    }
}
