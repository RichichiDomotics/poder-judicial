/**
 * Nombre del Programa : ObtenerFechaActualServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 23-jun-2011
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
package mx.gob.segob.nsjp.service.test.fecha.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.fecha.ObtenerFechaActualService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ObtenerFechaActualServiceImplTest
    extends BaseTestServicios<ObtenerFechaActualService> {

    public void testObtenerFechaActual(){
        try {
            logger.info("testObtenerFechaActual-------------------------");
            assert service != null;
            assertNotNull("service.obtenerFechaActual()", service.obtenerFechaActual());
        } catch (NSJPNegocioException ex) {
            fail("No se pudo obtener la fecha actual del sistema.");
        }
    }
   
}
