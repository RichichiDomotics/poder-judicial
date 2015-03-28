/**
 * Nombre del Programa : ConsultarElementosXActividadServiceImplTest.java
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
package mx.gob.segob.nsjp.service.test.calidad.impl;

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.service.elemento.ConsultarCatElementoService;
import mx.gob.segob.nsjp.service.elemento.ConsultarElementosXActividadService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarElementosXActividadServiceImplTest
    extends BaseTestServicios<ConsultarElementosXActividadService> {

    @Autowired
    private ConsultarCatElementoService consultarCatElementoService;

    public void testConsultarElementosXActividadService(){
        try {
            logger.info("Probando el servicio de: ConsultarElementosXActividadService");
            assert service != null;
            List<ElementoDTO> calidadesDto = service.consultarElementosXActividad(4L);
            assertNotNull("calidadesDto", calidadesDto);
            if (logger.isDebugEnabled()) {
                logger.debug("calidadesDto.size() = " + calidadesDto.size());
            }
            for (ElementoDTO elementoDto : calidadesDto) {
                if (logger.isDebugEnabled()) {
                    assertNotNull("elementoDto.getValorIdElemento()", elementoDto.getValorIdElemento());
                    logger.debug("elementoDto.getValorIdElemento() = " + elementoDto.getValorIdElemento());
                    logger.debug("elementoDto.getValorIdElemento().getValor() = " + elementoDto.getValorIdElemento().getValor());
                    logger.debug("elementoDto = " + elementoDto);
//                    logger.debug(consultarCatElementoService.consultarCatElemento(elementoDto.getElementoId()));
                    logger.debug(".............................................\n\n");
                }
            }
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarElementosXActividadService");
        }
    }
}
