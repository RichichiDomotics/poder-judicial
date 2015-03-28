/**
 * Nombre del Programa : IngresarNumerarioServiceImplTest.java
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
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.NumerarioDTO;
import mx.gob.segob.nsjp.service.objeto.IngresarNumerarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class IngresarNumerarioServiceImplTest
    extends BaseTestServicios<IngresarNumerarioService> {

    public void testIngresarNumerarioService(){
        try {
            logger.info("Probando el servicio de: IngresarNumerarioService");
            assert service != null;
            NumerarioDTO numerarioDTO = new NumerarioDTO();
//            numerarioDTO.setElementoId(1179909L);
            numerarioDTO.setMoneda("pesos");
            numerarioDTO.setTipoObjeto(Objetos.NUMERARIO);
            numerarioDTO.setFechaDeposito(new Date());
            numerarioDTO.setCtaTesoreria("123QWE");
            numerarioDTO.setCantidad(2L);
            
            
            numerarioDTO.setExpedienteDTO(new ExpedienteDTO(2L));
    		CalidadDTO cali=new CalidadDTO();
            cali.setCalidades(Calidades.PRUEBA);
            cali.setValorIdCalidad(new ValorDTO(215L));
            cali.setDescripcionEstadoFisico("Descripcion calidad nuevo vehiculo");
    		numerarioDTO.setCalidadDTO(cali);
    		numerarioDTO.setValorByCondicionFisicaVal(new ValorDTO(434L));
            
            service.ingresarNumerario(numerarioDTO);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test IngresarNumerarioService");
        }
    }
   
}
