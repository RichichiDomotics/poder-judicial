/**
 * Nombre del Programa : ConsultarAlmacenesPorTipoServiceImplTest.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 15-Feb-2012
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
package mx.gob.segob.nsjp.service.test.amparo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.amparo.AmparoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author rgama
 */
public class ConsultarAlmacenesPorTipoServiceImplTest
    extends BaseTestServicios<AmparoService> {

    public void testConsultarInvolucradosXAmparo(){
        try {
            logger.info("Probando el servicio de: AmparoService");
            assert service != null;
            Long idComplemento = 2059L;
            List<InvolucradoDTO> involucradosAsociadosAlAmparo = service.consultarInvolucradosXAmparo(idComplemento);
            assertNotNull("involucradosAsociadosAlAmparo", involucradosAsociadosAlAmparo);
            for (InvolucradoDTO dto : involucradosAsociadosAlAmparo) {
            	logger.info("Involucrado ID: "+dto.getElementoId());
				logger.info("Nombre: "+dto.getNombreCompleto());
				logger.info("Calidad: "+dto.getCalidadDTO().getValorIdCalidad().getValor());
            	
				
			}
            
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarAlmacenesPorTipoService");
        }
    }
   
}
