package mx.gob.segob.nsjp.service.test.objeto.impl;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.objeto.RegistrarObjetoEnAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class RegistrarObjetoEnAudienciaServiceImplTest extends BaseTestServicios<RegistrarObjetoEnAudienciaService> {
	
	public void testRegistrarObjetoEnAudiencia() throws NSJPNegocioException{
		long audienciaId = 17L;
		long institucion = Instituciones.DEF.getValorId();
		String descripcion = "Cuchillo sin filo";
		String noCustodia = "AUPV43332";
		Long noPrueba = 2L;
		Long condicionFisica = 431L;  
		
		service.registrarObjetoEnAudiencia(audienciaId, institucion, descripcion, condicionFisica, noCustodia, noPrueba);
	}

}
