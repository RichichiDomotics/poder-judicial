package mx.gob.segob.nsjp.service.test.audiencia.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.service.audiencia.CalcularCargaTrabajoAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import org.apache.log4j.Logger;

public class CalcularCargaTrabajoAudienciaServiceTest extends
		BaseTestServicios<CalcularCargaTrabajoAudienciaService> {

	public final static Logger logger = Logger.getLogger(CalcularCargaTrabajoAudienciaServiceTest.class);
	
	public void testCalcularCargaTrabajoAudiencia() throws NSJPNegocioException{
		AudienciaDTO audiencia = new AudienciaDTO();
		audiencia.setId(184L);
		Double carga = service.calcularCargaTrabajoAudiencia(audiencia);
		
		logger.info("Carga de Trabajo para audiencia ["+audiencia.getId()+"] "+carga);
	}
}
