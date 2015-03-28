/**
 * Nombre del Programa : ConsultarPeritoPorEvidenciaServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 01-jul-2011
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
package mx.gob.segob.nsjp.service.test.funcionario.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.funcionario.ConsultarPeritoPorEvidenciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarPeritoPorEvidenciaServiceImplTest
    extends BaseTestServicios<ConsultarPeritoPorEvidenciaService> {

    public void testConsultarPeritoPorEvidenciaService(){
        try {
            logger.info("Probando el servicio de: ConsultarPeritoPorEvidenciaService");
            assert service != null;
            EvidenciaDTO evidenciaDto = new EvidenciaDTO();
            evidenciaDto.setEvidenciaId(1L);
            FuncionarioDTO perito = service.consultarPeritoPorEvidencia(evidenciaDto);
            assertNotNull("perito", perito);
            if (logger.isDebugEnabled()) {
                logger.debug("perito = " + perito);
            }
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarPeritoPorEvidenciaService");
        }
    }
   
}
