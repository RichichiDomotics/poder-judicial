/**
 * Nombre del Programa : ConsultarEventosCitasPorUsuarioService.java
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
package mx.gob.segob.nsjp.service.eventocita;

import java.util.Date;
import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarEventosCitasPorUsuarioService {

    /**
     * 
     * @param usuarioDto
     * @return
     * @throws NSJPNegocioException
     */
    public List<EventoCitaDTO> consultarEventosCitasPorUsuario(UsuarioDTO usuarioDto)
            throws NSJPNegocioException;

	
    public List<EventoCitaDTO> consultarEventosCitasPorUsuario(
			UsuarioDTO usuario, Date fechaInicio, Date fechaFin) throws NSJPNegocioException;


	public EventoCitaDTO consultarEventoCitaPorId(Long id) throws NSJPNegocioException;


	public void actualizarEventoCita(EventoCitaDTO evento) throws NSJPNegocioException;


	public void eliminarEventoCita(EventoCitaDTO evento)throws NSJPNegocioException;
	
	public List<EventoCitaDTO> consultarEventosCitasPorFuncionario(FuncionarioDTO funcionarioDto, Date fechaInicio, Date fechaFin) 
		throws NSJPNegocioException;
}
