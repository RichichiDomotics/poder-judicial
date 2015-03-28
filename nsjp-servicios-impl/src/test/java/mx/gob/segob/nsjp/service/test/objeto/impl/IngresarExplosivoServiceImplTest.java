/**
 * Nombre del Programa : IngresarExplosivoServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 07-jul-2011
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

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ExplosivoDTO;
import mx.gob.segob.nsjp.service.objeto.IngresarExplosivoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class IngresarExplosivoServiceImplTest
    extends BaseTestServicios<IngresarExplosivoService> {

    public void testIngresarExplosivoService(){
        try {
            logger.info("Probando el servicio de: IngresarExplosivoService");
            assert service != null;
            ExplosivoDTO actualizable = new ExplosivoDTO();
            //Para actualizar es necesario el Id del Elemento
            Long idElemento = 1933L;
            actualizable.setElementoId(idElemento);
            
            actualizable.setDescripcion("Descripcion Explosivo" + new Date());
            actualizable.setUnidadMedida(new ValorDTO(1396L));
            //actualizable.setCantidad((short)99);
            actualizable.setExpedienteDTO(new ExpedienteDTO(119L));
            CalidadDTO calidadDTO = new CalidadDTO();
            calidadDTO.setCalidades(Calidades.PRUEBA);
            actualizable.setCalidadDTO(calidadDTO);
            actualizable.setValorByCondicionFisicaVal(new ValorDTO(1L));
            service.ingresarExplosivo(actualizable);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test IngresarExplosivoService");
        }
    }
   
}
