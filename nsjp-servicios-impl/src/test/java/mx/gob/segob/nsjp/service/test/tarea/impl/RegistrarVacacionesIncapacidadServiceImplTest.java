package mx.gob.segob.nsjp.service.test.tarea.impl;

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.enums.tarea.TipoEvento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.tarea.RegistrarVacacionesIncapacidadService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class RegistrarVacacionesIncapacidadServiceImplTest extends
		BaseTestServicios<RegistrarVacacionesIncapacidadService> {

	public void testRegistrarVacacionesIncapacidad() throws NSJPNegocioException{
		FuncionarioDTO funcionario = new FuncionarioDTO();
		funcionario.setClaveFuncionario(1L);
		EventoCitaDTO periodo = new EventoCitaDTO();
		Calendar c = Calendar.getInstance();
		c.set(2011, 8, 1);
		periodo.setFechaInicioEvento(c.getTime());
		c.set(2011, 8, 10);
		periodo.setFechaFinEvento(c.getTime());
		ValorDTO tipoEvento = new ValorDTO(TipoEvento.VACACIONES.getValorId(), TipoEvento.VACACIONES.name());
		periodo.setTipoEvento(tipoEvento);
		periodo.setNombreEvento(TipoEvento.VACACIONES.name());
		UsuarioDTO usuario = new UsuarioDTO();
		service.registrarVacacionesIncapacidad(funcionario, periodo, usuario);
	}
}
