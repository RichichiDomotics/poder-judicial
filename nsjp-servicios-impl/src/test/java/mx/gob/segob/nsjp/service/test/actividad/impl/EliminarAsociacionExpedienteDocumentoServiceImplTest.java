/**
 * 
 */
package mx.gob.segob.nsjp.service.test.actividad.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.service.actividad.EliminarAsociacionExpedienteDocumentoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class EliminarAsociacionExpedienteDocumentoServiceImplTest extends
		BaseTestServicios<EliminarAsociacionExpedienteDocumentoService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.actividad.impl.EliminarAsociacionExpedienteDocumentoServiceImpl#eliminarAsocArchivoAExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO, mx.gob.segob.nsjp.dto.documento.DocumentoDTO)}.
	 */
	public void testEliminarAsocArchivoAExpediente() {
		ExpedienteDTO expedienteDTO=new ExpedienteDTO();
		expedienteDTO.setNumeroExpediente("NSJYUCPROC201133333");
		expedienteDTO.setArea(new AreaDTO(1L));
		DocumentoDTO documentoDTO=new DocumentoDTO(35L);
		try {
			service.eliminarAsocArchivoAExpediente(expedienteDTO, documentoDTO);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue("FAllo", false);
		}
	}

}
