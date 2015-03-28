/**
 * 
 */
package mx.gob.segob.nsjp.service.test.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.CartaCumplimientoDTO;
import mx.gob.segob.nsjp.service.documento.ConsultarControversiasResueltasService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarControversiasResueltasServiceImplTest extends
		BaseTestServicios<ConsultarControversiasResueltasService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.documento.impl.ConsultarControversiasResueltasServiceImpl#consultarControversiasResueltas(java.lang.Long)}.
	 */
	public void testConsultarControversiasResueltas() {
		try {
			List<CartaCumplimientoDTO> documentos = service.consultarControversiasResueltas(TipoDocumento.CARTA_DE_CUMPLIMIENTO_DE_ACUERDO.getValorId());
			assertNotNull(documentos);
			logger.info("Existen "+documentos.size()+ " documentos");
			for (CartaCumplimientoDTO doc : documentos) {
				logger.info("------------------------------");
//				logger.info("Actividad: "+doc.getActividadDTO().getActividadId());
//				logger.info("Expediente: "+doc.getExpedienteDTO().getExpedienteId());
				logger.info("Número de caso: "+doc.getExpedienteDTO().getCasoDTO().getCasoId()+" ("+doc.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso()+")");
				logger.info("Identificador de la controversia resuelta: "+doc.getDocumentoId());
				logger.info("Nombre completo del fiscal que llevó a cabo la controversia: "+doc.getResponsableDocumento().getNombreCompleto());
				logger.info("Nombre del documento: "+doc.getNombreDocumento());
				logger.info("Bandera si ya ha sido leída la controversia: "+doc.getEsLeido());
//				logger.info("Fecha de envío de la misma(creacion doc): "+doc.getFechaCreacion());
				logger.info("Fecha de envío de la misma(creacion act): "+doc.getActividadDTO().getFechaCreacion());
				logger.info("Archivo digital: "+doc.getArchivoDigital().getArchivoDigitalId());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
