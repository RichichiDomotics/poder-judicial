/**
 * Nombre del Programa : RegistrarRelacionServiceImplTestUno.java
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
package mx.gob.segob.nsjp.service.test.relacion.impl;

import mx.gob.segob.nsjp.service.relacion.RegistrarRelacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class RegistrarRelacionServiceImplTestUno
    extends BaseTestServicios<RegistrarRelacionService> {

    public void testRegistrarRelacionService(){
//        try {
            logger.info("Probando el servicio de: RegistrarRelacionService");
            assert service != null;
            assertNotNull("service", service);

//        } catch (NSJPNegocioException ex) {
//            if (logger.isDebugEnabled()) {
//                logger.debug(ex);
//            }
//            fail("Ocurrio una excepcion al ejecutar el test RegistrarRelacionService");
//        }
    }
   
}
