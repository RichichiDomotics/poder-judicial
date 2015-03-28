/**
 * 
 */
package mx.gob.segob.nsjp.service.test.defensoria.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.defensoria.ArancelDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.defensoria.ConsultarArancelService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarArancelServiceImplTest extends BaseTestServicios<ConsultarArancelService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.defensoria.impl.ConsultarArancelServiceImpl#consultarArancel(mx.gob.segob.nsjp.dto.defensoria.ArancelDTO)}.
	 */
	public void testConsultarArancel() {
		ArancelDTO dto=new ArancelDTO(6L);
		try {
			ArancelDTO arancel = service.consultarArancel(dto);
			assertNotNull(arancel);
			logger.info("Arancel: "+arancel);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.defensoria.impl.ConsultarArancelServiceImpl#consultarArancelesXNumeroExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)}.
	 */
	public void testConsultarArancelesXNumeroExpediente() {
		ExpedienteDTO dto = new ExpedienteDTO();
		dto.setNumeroExpediente("NSJYUCPG20113733333");
//		dto.setNumeroExpedienteId(2L);
		try {
			List<ArancelDTO> aranceles = service.consultarArancelesXNumeroExpediente(dto, null);
			assertNotNull(aranceles);
			logger.info("Existen "+aranceles.size()+ " aranceles");
			for (ArancelDTO ara : aranceles) {
				logger.info("Arancel: "+ara);
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.defensoria.impl.ConsultarArancelServiceImpl#consultarArancelesXFuncionario(mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO)}.
	 */
	public void testConsultarArancelesXFuncionario() {
		FuncionarioDTO dto=new FuncionarioDTO(1L);
		try {
			List<ArancelDTO> aranceles = service.consultarArancelesXFuncionario(dto);
			assertNotNull(aranceles);
			logger.info("Existen "+aranceles.size()+ " aranceles");
			for (ArancelDTO ara : aranceles) {
				logger.info("Arancel: "+ara);
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
