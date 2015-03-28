/**
 * 
 */
package mx.gob.segob.nsjp.service.test.alerta.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.alarmas.AlertaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.alerta.ConsultarAlertasService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarAlertasServiceImplTest extends BaseTestServicios<ConsultarAlertasService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.alarma.impl.ConsultarAlarmaServiceImpl#consultarAlarmasXFuncionario(java.lang.Long, java.lang.Long)}.
	 */
	public void testConsultarAlertasXFuncionario() {
		try {
			UsuarioDTO loUsuario = new UsuarioDTO(3L);
			loUsuario.setFuncionario(new FuncionarioDTO(3L));
 			List<AlertaDTO> alarmas = service.consultarAlertasXUsuario(loUsuario, EstatusAlarmaAlerta.NO_EJECUTADA);
			assertNotNull(alarmas);
			
			
				for (AlertaDTO ale : alarmas) {
					logger.info("**-----------------------");
					logger.info("**Id Alerta: "+ale.getAlertaId());
					logger.info("**fecha: "+ale.getFechaAlerta());
					String strEstatusAlerta=(ale.getEstatusAlarmaAlerta()==null)?"No trae Estatus":ale.getEstatusAlarmaAlerta().getValor();
					logger.info("**estatusAlerta: "+strEstatusAlerta);
					String strTipoAlerta=(ale.getTipoAlerta()==null)?"No trae Tipo":ale.getTipoAlerta().getValor();
					logger.info("**tipoAlerta: "+strTipoAlerta);
					logger.info("**Nombre: "+ale.getNombre());
					logger.info("**EsPlaza: "+ale.getEsAplaza());
					logger.info("**Tiempo: "+ale.getTiempo());
					logger.info("**Unidad de tiempo id: "+ale.getUnidadDeTiempo().getIdCampo());
					logger.info("**Unidad de tiempo : "+ale.getUnidadDeTiempo().getValor());
					logger.info("**Usuario : "+ale.getUsuario().getIdUsuario());
					
				}
				logger.info("Existen "+alarmas.size()+" alarmas");

			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
