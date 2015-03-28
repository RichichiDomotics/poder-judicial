/**
 * Nombre del Programa : ConsultarAlmacenesPorTipoServiceImplTest.java
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
package mx.gob.segob.nsjp.service.test.almacen.impl;

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.service.almacen.ConsultarAlmacenesPorTipoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarAlmacenesPorTipoServiceImplTest
    extends BaseTestServicios<ConsultarAlmacenesPorTipoService> {

    public void testConsultarAlmacenesPorTipoService(){
        try {
            logger.info("Probando el servicio de: ConsultarAlmacenesPorTipoService");
            assert service != null;
            List<AlmacenDTO> consultarAlmacenesPorTipo = service.
                    consultarAlmacenesPorTipo(1L, Boolean.TRUE, new CasoDTO(1L));
            assertNotNull("consultarAlmacenesPorTipo", consultarAlmacenesPorTipo);
            consultarAlmacenesPorTipo = service.
                    consultarAlmacenesPorTipo(1L, Boolean.TRUE, null);
            assertNotNull("consultarAlmacenesPorTipo", consultarAlmacenesPorTipo);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarAlmacenesPorTipoService");
        }
    }
   
}
