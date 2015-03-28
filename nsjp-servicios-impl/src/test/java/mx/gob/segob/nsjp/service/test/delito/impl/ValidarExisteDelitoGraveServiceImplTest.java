/**
 * Nombre del Programa : ValidarExisteDelitoGraveServiceImplTest.java
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
package mx.gob.segob.nsjp.service.test.delito.impl;

import java.util.LinkedList;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.service.delito.ValidarExisteDelitoGraveService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ValidarExisteDelitoGraveServiceImplTest
    extends BaseTestServicios<ValidarExisteDelitoGraveService> {

    public void testValidarExisteDelitoGraveService(){
        try {
            logger.info("Probando el servicio de: ValidarExisteDelitoGraveService");
            assert service != null;
            service.validarExisteDelitoGrave(new LinkedList<DelitoDTO>());
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ValidarExisteDelitoGraveService");
        }
    }
   
}
