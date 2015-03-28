/**
 * Nombre del Programa : AgendaAlarmaDelegateImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 27-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.delegate.eventocita.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.eventocita.EventoCitaDelegate;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.eventocita.ConsultarEventosCitasPorUsuarioService;
import mx.gob.segob.nsjp.service.tarea.ActualizarTareaFuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Service("agendaAlarmaDelegate")
public class EventoCitaDelegateImpl implements EventoCitaDelegate {

    @Autowired
    private ConsultarEventosCitasPorUsuarioService consultarEventosCitasPorUsuarioService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EventoCitaDTO> consultarEventosCitasPorUsuario(UsuarioDTO usuarioDto)
            throws NSJPNegocioException {
        return consultarEventosCitasPorUsuarioService.consultarEventosCitasPorUsuario(usuarioDto);
    }
	
    @Override
	public List<EventoCitaDTO> consultarEventosCitasPorUsuario(
			UsuarioDTO usuario, Date fechaInicio, Date fechaFin) throws NSJPNegocioException {
    	return consultarEventosCitasPorUsuarioService.consultarEventosCitasPorUsuario(usuario, fechaInicio, fechaFin);
	}

    @Override
	public EventoCitaDTO consultarEventoCitaPorId(Long id) throws NSJPNegocioException{
		return consultarEventosCitasPorUsuarioService.consultarEventoCitaPorId(id);
	}

	
    @Override
	public void actualizarEventoCita(EventoCitaDTO evento)
			throws NSJPNegocioException {
    	consultarEventosCitasPorUsuarioService.actualizarEventoCita(evento);	
	}

	
    @Override
	public void eliminarEventoCita(EventoCitaDTO evento)
			throws NSJPNegocioException {
    	consultarEventosCitasPorUsuarioService.eliminarEventoCita(evento);
		
	}

}
