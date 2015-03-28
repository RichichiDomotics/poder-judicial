/**
 * 
 */
package mx.gob.segob.nsjp.service.test.evidencia.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.service.evidencia.ConsultarDocumentosDEvidenciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarDocumentosDEvidenciaServiceImplTest extends
		BaseTestServicios<ConsultarDocumentosDEvidenciaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.evidencia.impl.ConsultarDocumentosDEvidenciaServiceImpl#consultarDocumentosXEslabonesDEvidencia(mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO)}.
	 */
	public void testConsultarDocumentosXEslabonesDEvidencia() {
		try {
			List<DocumentoDTO> documentos = service.consultarDocumentosXEslabonesDEvidencia(new EvidenciaDTO(1L));
			assertNotNull(documentos);
	    	logger.info("Existen "+documentos.size()+" documentos");
	    	for (DocumentoDTO doc : documentos) {
	    		logger.info("-----------------------------");
	    		logger.info("ID: "+doc.getDocumentoId());
	    		logger.info("Tipo: "+doc.getTipoDocumentoDTO().getValor());
	    		logger.info("Nombre: "+doc.getNombreDocumento());
	    		logger.info("Fecha: "+doc.getFechaCreacion());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
