/**
 * Nombre del Programa : ConsultarRelacionesXCategoriaServiceImplTest.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 12-jul-2011
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.relacion.CatCategoriaRelacionDTO;
import mx.gob.segob.nsjp.dto.relacion.CatRelacionDTO;
import mx.gob.segob.nsjp.service.relacion.ConsultarParentescosService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author rgama
 */
public class ConsultarParentescosServiceImplTest
    extends BaseTestServicios<ConsultarParentescosService> {

    public void testConsultarParentescosService(){
        try {
            logger.info("Probando el servicio de: ConsultarRelacionesXCategoriaService");
            assert service != null;
            CatCategoriaRelacionDTO catCategoriaRelacionDTO = new CatCategoriaRelacionDTO();
            catCategoriaRelacionDTO.setDesCategoriaRelacion("Persona - Persona");
            List<CatRelacionDTO> catRelacionesDto = service.consultarParentescos();
            assertNotNull("catRelacionesDto", catRelacionesDto);
            if (logger.isDebugEnabled()) {
                logger.debug("catRelacionesDto.size() = " + catRelacionesDto.size());
                for (CatRelacionDTO catRelacionDTO : catRelacionesDto) {
                    logger.debug(catRelacionDTO);
                    logger.debug("---------------------------------------------\n\n");
                }
            }
            
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarRelacionesXCategoriaService");
        }
    }
   
}
