/**
 * Nombre del Programa : ValidarCadenaCustodiaEnAlmacenServiceImplTest.java
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
package mx.gob.segob.nsjp.service.test.almacen.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.service.almacen.ValidarCadenaCustodiaEnAlmacenService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ValidarCadenaCustodiaEnAlmacenServiceImplTest
    extends BaseTestServicios<ValidarCadenaCustodiaEnAlmacenService> {

    public void testValidarCadenaCustodiaEnAlmacenService(){
        try {
            logger.info("Probando el servicio de: ValidarCadenaCustodiaEnAlmacenService");
            assert service != null;
            CadenaDeCustodiaDTO cadenaDeCustodiaDTO = new CadenaDeCustodiaDTO();
            cadenaDeCustodiaDTO.setFolio("CadenaDeCustodia_0012");
            AlmacenDTO almacenDTO = new AlmacenDTO();
            almacenDTO.setIdentificadorAlmacen(1L);
            service.validarCadenaCustodiaEnAlmacen(cadenaDeCustodiaDTO, almacenDTO);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ValidarCadenaCustodiaEnAlmacenService");
        }
    }
   
}
