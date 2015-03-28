/**
 * 
 */
package mx.gob.segob.nsjp.service.test.policiaministerial.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.service.policiaministerial.CrearDocumentoLineaInvestigacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class CrearDocumentoLineaInvestigacionServiceImplTest extends
		BaseTestServicios<CrearDocumentoLineaInvestigacionService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.policiaministerial.impl.CrearDocumentoLineaInvestigacionServiceImpl#crearDocumentoLineaInvestigacion(mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO)}.
	 */
	public void testCrearDocumentoLineaInvestigacion() {
		try {
			DocumentoDTO doc=service.crearDocumentoLineaInvestigacion(new LineaInvestigacionDTO(99L),false);
			assertNotNull(doc);
			logger.info("ID: "+doc.getDocumentoId());
			logger.info("textP: "+doc.getTextoParcial());
			if(doc.getActividadDTO()!=null)
				logger.info("archiDig: "+doc.getActividadDTO().getActividadId());
			if(doc.getArchivoDigital()!=null){
				logger.info("ArchDig: "+doc.getArchivoDigital().getNombreArchivo()+doc.getArchivoDigital().getTipoArchivo());
				logger.info("TamCont: "+doc.getArchivoDigital().getContenido().length);
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
