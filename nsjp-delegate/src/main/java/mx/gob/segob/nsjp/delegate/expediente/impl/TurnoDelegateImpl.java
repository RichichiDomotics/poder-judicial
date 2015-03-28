/**
* Nombre del Programa : TurnoDelegateImpl.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementación del Delegate para las acciones con el turno
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.delegate.expediente.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.expediente.TurnoDelegate;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.expediente.ActualizarTurnoAtencionService;
import mx.gob.segob.nsjp.service.expediente.CancelarTurnoAtencionService;
import mx.gob.segob.nsjp.service.expediente.ConsultarTurnoAtencionService;
import mx.gob.segob.nsjp.service.expediente.GenerarTurnoAtencionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del Delegate para las acciones con el turno.
 * @version 1.0
 * @author vaguirre
 *
 */
@Service
public class TurnoDelegateImpl implements TurnoDelegate {
    @Autowired
    private GenerarTurnoAtencionService generarTurnoService;
    
    @Autowired
    private ConsultarTurnoAtencionService consultarTurnoService;
    
    @Autowired
    private CancelarTurnoAtencionService cancelarTurnoAtencionService;
    
    @Autowired ActualizarTurnoAtencionService actulizarTurnoAtencionService; 
    
    @Override
    public TurnoDTO generarTurnoAtencion(TurnoDTO defTurno)
            throws NSJPNegocioException {
        return this.generarTurnoService.generarTurnoAtencion(defTurno);
    }

	@Override
	public List<TurnoDTO> consultarTurnosAtendidosPorUsuario(UsuarioDTO usuarioDTO, boolean turnosDelDia)
			throws NSJPNegocioException {
		return this.consultarTurnoService.consultarTurnosAtendidosPorUsuario(usuarioDTO,turnosDelDia);

	}
	
	@Override
	public List<TurnoDTO> consultarTurnosConPermisosFuncionario(UsuarioDTO usuarioDto) throws NSJPNegocioException{
		return consultarTurnoService.consultarTurnosConPermisosFuncionario(usuarioDto);
	}

	@Override
	public TurnoDTO obtenerTurnoParaAtencion(TurnoDTO turnoDTO) throws NSJPNegocioException {

		return consultarTurnoService.obtenerTurnoParaAtencion(turnoDTO);

	}

	@Override
	public void cancelarTurnoParaAtencion(TurnoDTO turnoDTO, UsuarioDTO usuarioDTO)
			throws NSJPNegocioException {
		this.cancelarTurnoAtencionService.cancelarTurnoAtencion(turnoDTO, usuarioDTO);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.expediente.TurnoDelegate#obtenerTurnosPendientesPorTipo(mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno)
	 */
	@Override
	public List<TurnoDTO> obtenerTurnosPendientesPorTipo(TipoTurno tipo) {
		return consultarTurnoService.obtenerTurnosPendientesPorTipo(tipo);
	}

    @Override
	public List<TurnoDTO> obtenerTurnosPorFiltro(TurnoDTO turnoFiltro) throws NSJPNegocioException{
		return consultarTurnoService.obtenerTurnosPorFiltro(turnoFiltro);
	}

    @Override
    public TurnoDTO actualizarTurno(TurnoDTO turnoDTO) throws  NSJPNegocioException{
    	return actulizarTurnoAtencionService.actualizarTurno(turnoDTO);
    }
    
    @Override
    @Transactional
    public TurnoDTO generarConsultarTurnoAtencion(TurnoDTO defTurno) throws NSJPNegocioException{
    	TurnoDTO turnoDTO = generarTurnoService.generarTurnoAtencion(defTurno);
    	return (consultarTurnoService.obtenerTurnoParaAtencion(turnoDTO));
    }
}
