/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.documento.CartaCumplimientoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CartaCumplimiento;

/**
 * @author adrian
 *
 */
public class CartaCumplimientoDAOImplTest extends BaseTestPersistencia<CartaCumplimientoDAO> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.dao.documento.impl.CartaCumplimientoDAOImpl#consultarControversiasResueltas()}.
	 */
	public void testConsultarControversiasResueltas() {
		List<CartaCumplimiento> controversias = daoServcice.consultarControversiasResueltas();
		assertNotNull(controversias);
		logger.info("Existen "+controversias.size()+ " documentos");
		for (CartaCumplimiento doc : controversias) {
			logger.info("------------------------------");
//			logger.info("Número de caso: "+doc.getActividad().getExpediente().getCaso().getCasoId()+" ("+doc.getActividad().getExpediente().getCaso().getNumeroGeneralCaso()+")");
			logger.info("Identificador de la controversia resuelta: "+doc.getDocumentoId());
			logger.info("Nombre completo del fiscal que llevó a cabo la controversia: "+doc.getResponsableDocumento().getNombreCompleto());
			logger.info("Nombre del documento: "+doc.getNombreDocumento());
			logger.info("Bandera si ya ha sido leída la controversia: "+doc.getEsEnviado());
			logger.info("Fecha de envío de la misma(creacion doc): "+doc.getFechaCreacion());
//			logger.info("Fecha de envío de la misma(creacion act): "+doc.getActividad().getFechaCreacion());
			logger.info("Archivo digital: "+doc.getArchivoDigital().getArchivoDigitalId());
			logger.info("Leido: "+doc.getEsLeido());
			
		}
	}

}
