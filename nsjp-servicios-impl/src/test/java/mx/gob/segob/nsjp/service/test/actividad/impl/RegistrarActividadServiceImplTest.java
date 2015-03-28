package mx.gob.segob.nsjp.service.test.actividad.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.actividad.RegistrarActividadService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class RegistrarActividadServiceImplTest extends BaseTestServicios<RegistrarActividadService> {

	public void testRegistrarActividad() {
		ExpedienteDTO expedienteDTO=new ExpedienteDTO(1L);
		FuncionarioDTO funcionarioDTO=new FuncionarioDTO(1L);
		Long tipoActividad=2287L;
		try {
			Long idActividad = service.registrarActividad(expedienteDTO, funcionarioDTO, tipoActividad);
			logger.info("La actividad es: "+idActividad);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}
