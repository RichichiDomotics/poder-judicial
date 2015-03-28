	/**
 * 
 */
package mx.gob.segob.nsjp.service.test.informepolicial;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.service.informepolicial.CargarInformeIPHService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class CargarInformeIPHServiceImplTest extends BaseTestServicios<CargarInformeIPHService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.informepolicial.impl.CargarInformeIPHServiceImpl#cargarInformeIPH(mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO)}.
	 */
	public void testCargarInformeIPH() {
		try {
			InformePolicialHomologadoDTO infIphDTO=new InformePolicialHomologadoDTO();
			infIphDTO.setFolioIPH(2011000095L);
			
			DocumentoDTO doc = service.cargarInformeIPH(infIphDTO);
			
			assertTrue("El servicio debe retornar el documento cargado", doc != null);
			logger.info("DocId: "+doc.getDocumentoId());
			logger.info("Texto Parcial: "+doc.getTextoParcial());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}
