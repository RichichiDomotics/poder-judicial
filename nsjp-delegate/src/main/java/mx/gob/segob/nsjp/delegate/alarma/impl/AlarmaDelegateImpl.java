/**
 * 
 */
package mx.gob.segob.nsjp.delegate.alarma.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.alarma.AlarmaDelegate;
import mx.gob.segob.nsjp.dto.alarmas.AlarmaDTO;
import mx.gob.segob.nsjp.dto.alarmas.AlertaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.alarma.ConsultarAlarmaService;
import mx.gob.segob.nsjp.service.alarma.RegistrarAlarmaService;
import mx.gob.segob.nsjp.service.alerta.ActualizarEstatusAlertaService;
import mx.gob.segob.nsjp.service.alerta.ConsultarAlertasService;

/**
 * @author adrian
 *
 */
@Service
public class AlarmaDelegateImpl implements AlarmaDelegate {

	@Autowired
	private RegistrarAlarmaService registrarAlarmaService;
	@Autowired
	private ConsultarAlarmaService consultarAlarmaService;
	@Autowired
	private ConsultarAlertasService consultarAlertasService;
	
	@Autowired
	private ActualizarEstatusAlertaService actualizarEstatusAlertaService;
	


	@Override
	public Long registrarAlarma(AlarmaDTO alarmaDTO)
			throws NSJPNegocioException {
		return registrarAlarmaService.registrarAlarma(alarmaDTO);
	}

	@Override
	public List<AlarmaDTO> consultarAlarmasXFuncionario(Long claveFuncionario,
			Long estatusAlarma) throws NSJPNegocioException {
		return consultarAlarmaService.consultarAlarmasXFuncionario(claveFuncionario, estatusAlarma);
	}

	@Override
	public AlarmaDTO consultarAlarma(Long idAlarma) throws NSJPNegocioException {
		return consultarAlarmaService.consultarAlarma(idAlarma);
	}
	
	
	/**
	 * Operación que permite consultar las alertas para un usuario y opcionalmente se puede filtrar por estatus
	 * @param usuario.usuarioId: Permite consultar las Alertas de un usuario en particular
	 * @param estatusAlerta: Representa el estatus del la Alerta
	 * @return
	 * @throws NSJPNegocioException
	 */	
	public List<AlertaDTO> consultarAlertasXUsuario(UsuarioDTO usuario, EstatusAlarmaAlerta estatusAlerta)throws NSJPNegocioException{
		return consultarAlertasService.consultarAlertasXUsuario(usuario, estatusAlerta);
	}
	
    /**
     * Actualiza el Estatus y/o fecha Alerta 
     * @param alerta.alertaId: Representa el id de la Alerta a actualizar
     * @param alerta.fechaAlerta: Representa la fecha de la Alerta a actualizar
     * @param estatusAlerta: Representa el nuevo estatus
     * @throws NSJPNegocioException
     */
    public void actualizaEstatusyFechaAlerta(AlertaDTO alarma,
    		EstatusAlarmaAlerta estatusAlerta) throws NSJPNegocioException {
    	actualizarEstatusAlertaService.actualizaEstatusyFechaAlerta(alarma, estatusAlerta);
    }

}
