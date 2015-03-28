package mx.gob.segob.nsjp.service.test.audiencia.impl;

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.service.audiencia.RegistrarResolutivoAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class RegistrarResolutivoAudienciaServiceImplTest extends
		BaseTestServicios<RegistrarResolutivoAudienciaService> {
	
	public void testRegistrarResolutivoAudiencia() throws NSJPNegocioException, InterruptedException{
		
		AudienciaDTO audiencia = new AudienciaDTO();
		ResolutivoDTO resolutivo = new ResolutivoDTO();
		
		audiencia.setId(2L);
		
		Calendar time = Calendar.getInstance();
		
		for(int i = 0; i < 10; i++){
			resolutivo = new ResolutivoDTO();
			resolutivo.setAudiencia(audiencia);
			resolutivo.setDetalle("resolutivo de resolucion "+i);
			resolutivo.setTemporizador(time.getTime());
			service.registrarResolutivoAudiencia(resolutivo);
			time = Calendar.getInstance();
		}
	}
}
