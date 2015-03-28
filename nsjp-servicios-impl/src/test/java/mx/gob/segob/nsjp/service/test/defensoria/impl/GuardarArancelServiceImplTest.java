/**
 * 
 */
package mx.gob.segob.nsjp.service.test.defensoria.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.defensoria.ArancelDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.defensoria.GuardarArancelService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class GuardarArancelServiceImplTest extends BaseTestServicios<GuardarArancelService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.defensoria.impl.GuardarArancelServiceImpl#guardarArancel(mx.gob.segob.nsjp.dto.defensoria.ArancelDTO)}.
	 */
	public void testGuardarArancel() {
		ArancelDTO dto=new ArancelDTO();
		
		dto.setArancel_id(null);
		dto.setFechaInicio(new Date());
		dto.setFechaFin(new Date());
		dto.setHoras((short) 15);
		dto.setMonto((double) 25000.66);
		dto.setFechaPago(new Date());
		
		ExpedienteDTO expDTO=new ExpedienteDTO();
//		expDTO.setNumeroExpediente("NSJYUCPG20113733333");
		expDTO.setNumeroExpedienteId(2L);
		dto.setClase_valDTO(new ValorDTO(1L));
		dto.setExpedienteDTO(expDTO);
		dto.setFuncionarioDTO(new FuncionarioDTO(1L));
		
		try {
			Long idArancel = service.guardarArancel(dto);
			assertNotNull(idArancel);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

