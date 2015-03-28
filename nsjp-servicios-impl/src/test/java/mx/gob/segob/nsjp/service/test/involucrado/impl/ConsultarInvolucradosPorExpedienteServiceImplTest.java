/**
* Nombre del Programa : ConsultarInvolucradosPorExpedienteServiceImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13/02/2012
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
package mx.gob.segob.nsjp.service.test.involucrado.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradosPorIdExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author rgama
 *
 */
public class ConsultarInvolucradosPorExpedienteServiceImplTest extends
		BaseTestServicios<ConsultarInvolucradosPorIdExpedienteService> {

	public void testConsultarInvolucradosPorExpediente() {
				
		try {
			List<InvolucradoDTO> involucrados = service.consultarInvolucradosPorExpediente(63L);
			
			for (InvolucradoDTO dto : involucrados) {
				logger.info("Involucrado ID: "+dto.getElementoId());
				logger.info("Nombre: "+dto.getNombreCompleto());
				logger.info("Calidad: "+dto.getCalidadDTO().getValorIdCalidad().getValor());
			}
			logger.info("\n\r Existen "+involucrados.size()+ " involucrados");

			
		} catch (NSJPNegocioException e) {
			assertTrue("Fallo: testConsultarInvolucradosPorExpediente", false);
			e.printStackTrace();
		}
	}

}
