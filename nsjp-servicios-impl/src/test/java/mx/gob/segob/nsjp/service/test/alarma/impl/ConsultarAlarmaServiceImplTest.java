/**
 * 
 */
package mx.gob.segob.nsjp.service.test.alarma.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.alarmas.AlarmaDTO;
import mx.gob.segob.nsjp.dto.alarmas.AlertaDTO;
import mx.gob.segob.nsjp.service.alarma.ConsultarAlarmaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarAlarmaServiceImplTest extends BaseTestServicios<ConsultarAlarmaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.alarma.impl.ConsultarAlarmaServiceImpl#consultarAlarmasXFuncionario(java.lang.Long, java.lang.Long)}.
	 */
	public void testConsultarAlarmasXFuncionario() {
		try {
			List<AlarmaDTO> alarmas = service.consultarAlarmasXFuncionario(3L, EstatusAlarmaAlerta.NO_EJECUTADA.getValorId());
			assertNotNull(alarmas);
			logger.info("Existen "+alarmas.size()+" alarmas");
			for (AlarmaDTO ala : alarmas) {
				logger.info("------------------------------");
				logger.info("Id Alarma: "+ala.getAlarmaId());
				logger.info("fecha: "+ala.getFechaAlarma());
				logger.info("motivo: "+ala.getMotivo());
				logger.info("folio: "+ala.getFolioEvento());
				logger.info("datos: "+ala.getDatosAsociados());
				String strEstatusAlarma=(ala.getEstatusAlarmaAlerta()==null)?"No trae Estatus":ala.getEstatusAlarmaAlerta().getValor();
				logger.info("estatusAlarma: "+strEstatusAlarma);
				String strTipo=(ala.getTipoEventoAlarma()==null)?"No trae Tipo":ala.getTipoEventoAlarma().getValor();
				logger.info("tipoEvento: "+strTipo);
				for (AlertaDTO ale : ala.getAlertas()) {
					logger.info("**-----------------------");
					logger.info("**Id Alerta: "+ale.getAlertaId());
					logger.info("**fecha: "+ale.getFechaAlerta());
					String strEstatusAlerta=(ale.getEstatusAlarmaAlerta()==null)?"No trae Estatus":ale.getEstatusAlarmaAlerta().getValor();
					logger.info("**estatusAlerta: "+strEstatusAlerta);
					String strTipoAlerta=(ale.getTipoAlerta()==null)?"No trae Tipo":ale.getTipoAlerta().getValor();
					logger.info("**tipoAlerta: "+strTipoAlerta);
				}
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.alarma.impl.ConsultarAlarmaServiceImpl#consultarAlarma(java.lang.Long)}.
	 */
	public void testConsultarAlarma() {
		try {
			AlarmaDTO ala = service.consultarAlarma(3L);
			assertNotNull(ala);
				logger.info("------------------------------");
				logger.info("Id Alarma: "+ala.getAlarmaId());
				logger.info("fecha: "+ala.getFechaAlarma());
				logger.info("motivo: "+ala.getMotivo());
				logger.info("folio: "+ala.getFolioEvento());
				logger.info("datos: "+ala.getDatosAsociados());
				String strEstatusAlarma=(ala.getEstatusAlarmaAlerta()==null)?"No trae Estatus":ala.getEstatusAlarmaAlerta().getValor();
				logger.info("estatusAlarma: "+strEstatusAlarma);
				String strTipo=(ala.getTipoEventoAlarma()==null)?"No trae Tipo":ala.getTipoEventoAlarma().getValor();
				logger.info("tipoEvento: "+strTipo);
				for (AlertaDTO ale : ala.getAlertas()) {
					logger.info("**-----------------------");
					logger.info("**Id Alerta: "+ale.getAlertaId());
					logger.info("**fecha: "+ale.getFechaAlerta());
					String strEstatusAlerta=(ale.getEstatusAlarmaAlerta()==null)?"No trae Estatus":ale.getEstatusAlarmaAlerta().getValor();
					logger.info("**estatusAlerta: "+strEstatusAlerta);
					String strTipoAlerta=(ale.getTipoAlerta()==null)?"No trae Tipo":ale.getTipoAlerta().getValor();
					logger.info("**tipoAlerta: "+strTipoAlerta);
				}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
