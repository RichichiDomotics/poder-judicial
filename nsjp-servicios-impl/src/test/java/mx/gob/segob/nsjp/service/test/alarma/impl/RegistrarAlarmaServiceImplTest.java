/**
 * 
 */
package mx.gob.segob.nsjp.service.test.alarma.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.comun.enums.alarmas.TipoEventoAlarma;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.alarmas.AlarmaDTO;
import mx.gob.segob.nsjp.dto.alarmas.AlertaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.alarma.RegistrarAlarmaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class RegistrarAlarmaServiceImplTest extends BaseTestServicios<RegistrarAlarmaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.alarma.impl.RegistrarAlarmaServiceImpl#registrarAlarma(mx.gob.segob.nsjp.dto.alarmas.AlarmaDTO)}.
	 */
	public void testRegistrarAlarma() {
		AlarmaDTO dto=new AlarmaDTO();
//		dto.setAlarmaId(1L);
		dto.setFechaAlarma(new Date());
		dto.setMotivo("Motivo de prueba");
		dto.setFolioEvento("Folio");
		dto.setDatosAsociados("Datos asociados de Prueba");
		dto.setFuncionario(new FuncionarioDTO(3L));
		dto.setTipoEventoAlarma(new ValorDTO(TipoEventoAlarma.SEGUIMIENTO_A_SOLICITUD.getValorId()));
		dto.setEstatusAlarmaAlerta(new ValorDTO(EstatusAlarmaAlerta.NO_EJECUTADA.getValorId()));
		
		
		List<AlertaDTO> alertas=new ArrayList<AlertaDTO>();
		AlertaDTO alerta=new AlertaDTO();
		alerta.setFechaAlerta(new Date());
		alerta.setTipoAlerta(new ValorDTO(1L));
		alerta.setEstatusAlarmaAlerta(new ValorDTO(EstatusAlarmaAlerta.NO_EJECUTADA.getValorId()));
		alertas.add(alerta);
		
		dto.setAlertas(alertas);
		
		try {
			Object idAlarma = service.registrarAlarma(dto);
			assertNotNull(idAlarma);
			logger.info("Registrado el objeto: "+idAlarma);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
