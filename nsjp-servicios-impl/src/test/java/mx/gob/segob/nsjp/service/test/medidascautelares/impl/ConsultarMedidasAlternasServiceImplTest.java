/**
* Nombre del Programa : ConsultarMedidasAlternasServiceImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Sep 2011
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
package mx.gob.segob.nsjp.service.test.medidascautelares.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.service.medidasalternas.ConsultarMedidasAlternasService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author cesar
 *
 */
public class ConsultarMedidasAlternasServiceImplTest extends
		BaseTestServicios<ConsultarMedidasAlternasService> {

	public void testConsultarMedidasAlternasPorEstatus () {
		
		try {
			List<MedidaAlternaDTO> respuesta = service.consultarMedidasAlternasPorEstatus(EstatusMedida.EN_PROCESO);
			for (MedidaAlternaDTO loMedidaAlterna : respuesta) {
				logger.info("--------------------------------------------");
				logger.info("NUS: " + "-");
				logger.info("Nombre del sentenciado: " + (loMedidaAlterna.getInvolucrado() != null ? loMedidaAlterna.getInvolucrado().getNombreCompleto(): "-"));
				logger.info("Expediente SSP: " + (loMedidaAlterna.getExpedienteDTO() != null && loMedidaAlterna.getExpedienteDTO().getNumeroExpediente() != null ?
						loMedidaAlterna.getExpedienteDTO().getNumeroExpediente(): "-"));
				logger.info("Numero Caso: " + (loMedidaAlterna.getNumeroCaso() != null ? loMedidaAlterna.getNumeroCaso(): "-"));
				logger.info("Causa: " + (loMedidaAlterna.getNumeroCausa() != null ? loMedidaAlterna.getNumeroCausa(): "-"));
				logger.info("Carpeta de ejecucion: " + (loMedidaAlterna.getNumeroCarpetaEjecucion() != null ? loMedidaAlterna.getNumeroCarpetaEjecucion(): "-"));
			}
			assertTrue("La lista debe regresar al menos un registro :: ",respuesta.size()>0);
			logger.info("La lista debe regresar al menos un registro :: "+respuesta.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	public void testConsultarMedidasAlternasPorNumeroExpediente () {
		 
		try {
			ExpedienteDTO expediente = new ExpedienteDTO();
			expediente.setNumeroExpedienteId(2L);
			List<MedidaAlternaDTO> respuesta = service.consultarMedidasAlternasPorNumeroExpediente(expediente);
			for (MedidaAlternaDTO loMedidaAlterna : respuesta) {
				logger.info("--------------------------------------------");
				logger.info("Nombre del imputado: " + (loMedidaAlterna.getInvolucrado() != null ? loMedidaAlterna.getInvolucrado().getNombreCompleto(): "-"));
				logger.info("Expediente SSP: " + (loMedidaAlterna.getExpedienteDTO() != null && loMedidaAlterna.getExpedienteDTO().getNumeroExpediente() != null ?
					loMedidaAlterna.getExpedienteDTO().getNumeroExpediente(): "-"));
				logger.info("Numero Caso: " + (loMedidaAlterna.getNumeroCaso() != null ? loMedidaAlterna.getNumeroCaso(): "-"));
				logger.info("Causa: " + (loMedidaAlterna.getNumeroCausa() != null ? loMedidaAlterna.getNumeroCausa(): "-"));

			}
			assertTrue("La lista debe regresar al menos un registro :: ",respuesta.size()>0);
			logger.info("La lista debe regresar al menos un registro :: "+respuesta.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
}
