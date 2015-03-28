/**
 * Nombre del Programa : IngresarTelefonoServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 06-jul-2011
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
package mx.gob.segob.nsjp.service.test.objeto.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.TelefoniaDTO;
import mx.gob.segob.nsjp.service.objeto.IngresarTelefonoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class IngresarTelefonoServiceImplTest
    extends BaseTestServicios<IngresarTelefonoService> {

    public void testIngresarTelefonoService(){
        try {
            logger.info("Probando el servicio de: IngresarTelefonoService");
            assert service != null;
            TelefoniaDTO telefoniaDTO = new TelefoniaDTO();
            telefoniaDTO.setElementoId(1021L);
            telefoniaDTO.setExpedienteDTO(new ExpedienteDTO(577L));
            telefoniaDTO.setModelo("modelo cosme 123");
            service.ingresarTelefono(telefoniaDTO);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test IngresarTelefonoService");
        }
    }
   
}
