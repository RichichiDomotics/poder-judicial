/**
 * Nombre del Programa : IngresarEquipoDeComputoServiceImplTest.java
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
import mx.gob.segob.nsjp.dto.objeto.EquipoComputoDTO;
import mx.gob.segob.nsjp.service.objeto.IngresarEquipoDeComputoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class IngresarEquipoDeComputoServiceImplTest
    extends BaseTestServicios<IngresarEquipoDeComputoService> {

    public void testIngresarEquipoDeComputoService(){
        try {
            logger.info("Probando el servicio de: IngresarEquipoDeComputoService");
            assert service != null;
            EquipoComputoDTO equipoComputoDto = new EquipoComputoDTO();
            equipoComputoDto.setElementoId(1020L);
            equipoComputoDto.setModelo("cosme-pro");
            equipoComputoDto.setDescripcion("descripcion cosme");
            service.ingresarEquipoComputo(equipoComputoDto);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test IngresarEquipoDeComputoService");
        }
    }
   
}
