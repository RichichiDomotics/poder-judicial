/**
 * Nombre del Programa : ConsultarRelacionesXCategoriaServiceImplTest.java
 * Autor                            : Jacob Lobaco
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

import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.relacion.CatCategoriaRelacionDTO;
import mx.gob.segob.nsjp.dto.relacion.CatRelacionDTO;
import mx.gob.segob.nsjp.service.relacion.ConsultarCatRelacionesXCatCategoriaRelacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarRelacionesXCategoriaServiceImplTest
    extends BaseTestServicios<ConsultarCatRelacionesXCatCategoriaRelacionService> {

    public void testConsultarRelacionesXCategoriaService(){
        try {
            logger.info("Probando el servicio de: ConsultarRelacionesXCategoriaService");
            assert service != null;
            CatCategoriaRelacionDTO catCategoriaRelacionDTO = new CatCategoriaRelacionDTO();
//            catCategoriaRelacionDTO.setDesCategoriaRelacion("Persona - Persona");
            
            catCategoriaRelacionDTO.setCatCategoriaRelacionId(new Long (Relaciones.ORGANIZACION_INVOLUCRADA.ordinal()));
            
            List<CatRelacionDTO> catRelacionesDto = service.
                    consultarCatRelacionesXCatCategoriaRelacion(catCategoriaRelacionDTO);
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
