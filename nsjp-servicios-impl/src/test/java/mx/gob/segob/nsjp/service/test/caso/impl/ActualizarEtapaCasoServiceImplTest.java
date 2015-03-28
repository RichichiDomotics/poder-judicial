/**
 * Nombre del Programa : ActualizarEtapaCasoServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 02-ago-2011
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
package mx.gob.segob.nsjp.service.test.caso.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.service.caso.ActualizarEtapaCasoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ActualizarEtapaCasoServiceImplTest
    extends BaseTestServicios<ActualizarEtapaCasoService> {

    public void testActualizarEtapaCasoService(){
        try {
            logger.info("Probando el servicio de: ActualizarEtapaCasoService");
            assert service != null;
//            service.actualizarEtapaCaso(new CasoDTO(1L), new ValorDTO(EstatusCaso.INVESTIGACION.get));
            service.actualizarEtapaCaso(new CasoDTO(1L), new ValorDTO(327L));
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ActualizarEtapaCasoService");
        }
    }
   
}
