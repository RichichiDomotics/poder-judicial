/**
 * Nombre del Programa : ConsultarEvidenciasResguardadasPorUsuarioServiceImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.test.evidencia.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciasResguardadasPorUsuarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ConsultarEvidenciasResguardadasPorUsuarioServiceImplTest
        extends
            BaseTestServicios<ConsultarEvidenciasResguardadasPorUsuarioService> {

    public void testConsultarEvidenciasResguardadasPorUsuario() {
        try {
            List<EvidenciaDTO> data = service
                    .consultarEvidenciasResguardadasPorUsuario(super
                            .getUsuario());
            for (EvidenciaDTO ev : data) {
                logger.debug("" + ev.getCadenaDeCustodia().getFolio() + " :: "
                        + ev.getCantEvidenciasResguardadas());
            }
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }
    
    public void testConsultarEvidenciasResguardadasPorUsuarioCadena() {
        try {
            List<EvidenciaDTO> data = service
                    .consultarEvidenciasResguardadasPorUsuario(super
                            .getUsuario(), new CadenaDeCustodiaDTO(1L));
            for (EvidenciaDTO ev : data) {
                logger.debug("" + ev.getCadenaDeCustodia().getFolio() + " :: "
                        + ev.getCantEvidenciasResguardadas());
            }
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }


}
