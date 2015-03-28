/**
 * Nombre del Programa : ConsultarEvidenciaPorIdObjetoService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 30-jun-2011
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
package mx.gob.segob.nsjp.service.test.evidencia.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciaPorIdObjetoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarEvidenciaPorIdObjetoServiceImplTest
        extends BaseTestServicios<ConsultarEvidenciaPorIdObjetoService> {

    public void testConsultarEvidenciaPorIdObjetoService(){
    	try {
			EvidenciaDTO evidencia = service.consultarEvidenciaPorIdObjeto(36L, true);
			if(evidencia != null && evidencia.getCadenaDeCustodia() != null && evidencia.getCadenaDeCustodia().getFolio() != null)
				logger.debug("Folio: " + evidencia.getCadenaDeCustodia().getFolio());				
			else
				logger.debug("Folio: " + "-");
			logger.debug(evidencia);
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertNotNull(null);
		}
    	
    }
  
}
