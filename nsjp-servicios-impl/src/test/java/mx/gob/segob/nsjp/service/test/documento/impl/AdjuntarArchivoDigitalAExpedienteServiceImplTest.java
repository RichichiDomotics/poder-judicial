/**
 * 
 */
package mx.gob.segob.nsjp.service.test.documento.impl;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.documento.AdjuntarArchivoDigitalAExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class AdjuntarArchivoDigitalAExpedienteServiceImplTest extends
		BaseTestServicios<AdjuntarArchivoDigitalAExpedienteService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.documento.impl.AdjuntarArchivoDigitalAExpedienteServiceImpl#adjuntarArchivoDigitalAExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO, mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO, mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO)}.
	 */
/*	public void testAdjuntarArchivoDigitalAExpediente() {
		ArchivoDigitalDTO archDig=new ArchivoDigitalDTO();
		archDig.setNombreArchivo("Archivo Prueba");
		archDig.setTipoArchivo("PDF");
		String strCont="Este es un archivo cargado desde JUnit y el contenido es texto";
		archDig.setContenido(strCont.getBytes());
		try {
			Long idActividad = service.adjuntarArchivoDigitalAExpediente(new ExpedienteDTO(60L), archDig, new FuncionarioDTO(35L), Actividades.ADJUNTAR_ACUERDO_RESTAURATIVO);
			assertNotNull(idActividad);
			logger.info("Se guardo la actividad: "+idActividad);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
*/
}
