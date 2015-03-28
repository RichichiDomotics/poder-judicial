/**
 * Nombre del Programa : ConsultarResolutivosAudienciaServiceImplTest.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 29/06/2011
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
package mx.gob.segob.nsjp.service.test.audiencia.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoViewDTO;
import mx.gob.segob.nsjp.service.audiencia.ConsultarResolutivosAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
public class ConsultarResolutivosAudienciaServiceImplTest extends
		BaseTestServicios<ConsultarResolutivosAudienciaService> {

	/**
	 * Test method for
	 * {@link mx.gob.segob.nsjp.service.audiencia.impl.ConsultarResolutivosAudienciaServiceImpl#consultarResolutivosAudiencia(java.lang.Long)}
	 * .
	 */
	public void testConsultarResolutivosAudiencia() {
		Long idAudiencia = 14L;
		try {
			List<ResolutivoDTO> resolutivos = service
					.consultarResolutivosAudiencia(idAudiencia);
			logger.info("Existen " + resolutivos.size()
					+ " resolutivos para la audiencia "
					+ resolutivos.get(0).getAudiencia().getId());
			for (ResolutivoDTO res : resolutivos) {
				logger.info("----------------------------------------------");
				logger.info("Id Resolutivo: " + res.getResolutivoId());
				logger.info("Detalle: " + res.getDetalle());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue("FALLO: consultarResolutivosAudiencia", false);
		}
	}

	public void testConsultarResolutivosByAudienciaId()
			throws NSJPNegocioException {
		Long idAudiencia = 191L;
		List<ResolutivoViewDTO> lista = service
				.consultarResolutivosByAudienciaId(idAudiencia);
		for (ResolutivoViewDTO resolutivo : lista) {
			logger.info("[" + resolutivo.getResolutivoId() + " - "
					+ resolutivo.getTemporizador() + " - "
					+ resolutivo.getDetalle() + "]");
		}
	}

	public void testConsultarResolutivosByAudienciaIdWithDocumento()
			throws NSJPNegocioException {
		Long idAudiencia = 189L;
		List<ResolutivoViewDTO> lista = service
				.consultarResolutivosByAudienciaIdWithDocumento(idAudiencia);
		if (lista.size() > 0) {
			logger.info("Lista NO Vacía");
			for (ResolutivoViewDTO resolutivo : lista) {
				logger.info("[" + resolutivo.getResolutivoId() + " - "
						+ resolutivo.getTemporizador() + " - "
						+ resolutivo.getDetalle() + "]");
			}
		} else {
			logger.info("Lista Vacía");
		}

	}

	public void testConsultarResolutivosByAudienciaIdWithDocumentoAndArchDigital()
			throws NSJPNegocioException {
		Long idAudiencia = 189L;
		List<ResolutivoViewDTO> lista = service
				.consultarResolutivosByAudienciaIdWithDocumentoAndArchDigital(idAudiencia);
		if (lista.size() > 0) {
			logger.info("Lista NO Vacía");
			for (ResolutivoViewDTO resolutivo : lista) {
				logger.info("[" + resolutivo.getResolutivoId() + " - "
						+ resolutivo.getTemporizador() + " - "
						+ resolutivo.getDetalle() + "]");
			}
		} else {
			logger.info("Lista Vacía");
		}

	}

}
