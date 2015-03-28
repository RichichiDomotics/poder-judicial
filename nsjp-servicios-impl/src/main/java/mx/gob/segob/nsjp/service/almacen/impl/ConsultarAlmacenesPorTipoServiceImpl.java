/**
 * Nombre del Programa : ConsultarAlmacenesPorTipoServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 29-jul-2011
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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.almacen.AlmacenDAO;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.service.almacen.ConsultarAlmacenesPorTipoService;
import mx.gob.segob.nsjp.service.caso.impl.transform.CasoTransformer;
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
public class ConsultarAlmacenesPorTipoServiceImpl implements ConsultarAlmacenesPorTipoService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(ConsultarAlmacenesPorTipoServiceImpl.class);

    @Autowired
    private AlmacenDAO almacenDao;

    @Override
    public List<AlmacenDTO> consultarAlmacenesPorTipo(Long idTipoAlmacen,
            Boolean estatus, CasoDTO casoDto) throws NSJPNegocioException {
        if (estatus == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Caso caso = null;
        if (casoDto != null) {
            caso = CasoTransformer.transformarCasoBasico(casoDto);
        }
        List<Almacen> almacenes = almacenDao.consultarAlmacenesPorTipo(idTipoAlmacen, estatus, caso);
        List<AlmacenDTO> almacenesDto = Collections.EMPTY_LIST;
        if (!almacenes.isEmpty()) {
            almacenesDto = new LinkedList<AlmacenDTO>();
            for (Almacen almacen : almacenes) {
                AlmacenDTO almacenDto = AlmacenTransformer.transformarAlmacen(almacen);
                almacenesDto.add(almacenDto);
            }
        }
        return almacenesDto;
    }
   
}
