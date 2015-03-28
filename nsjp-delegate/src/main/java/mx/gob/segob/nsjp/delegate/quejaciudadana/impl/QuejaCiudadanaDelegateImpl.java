/**
 * 
 */
package mx.gob.segob.nsjp.delegate.quejaciudadana.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.quejaciudadana.MotivoRechazo;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.quejaciudadana.QuejaCiudadanaDelegate;
import mx.gob.segob.nsjp.dto.quejaciudadana.QuejaCiudadanaDTO;
import mx.gob.segob.nsjp.service.quejaciudadana.AsignarMotivoRechazoService;
import mx.gob.segob.nsjp.service.quejaciudadana.ConsultarQuejaCiudadanaService;
import mx.gob.segob.nsjp.service.quejaciudadana.RegistrarQuejaCiudadanaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adrian
 *
 */
@Service("quejaCiudadanaDelegate")
public class QuejaCiudadanaDelegateImpl implements QuejaCiudadanaDelegate {
	
	@Autowired
	private RegistrarQuejaCiudadanaService registrarQuejaCiudadanaService;
	@Autowired
	private ConsultarQuejaCiudadanaService consultarQuejaCiudadanaService;
	@Autowired
	private AsignarMotivoRechazoService asignarMotivoRechazoService;
	

	@Override
	public Long registrarQuejaCiudadana(QuejaCiudadanaDTO quejaDTO)
			throws NSJPNegocioException {
		return registrarQuejaCiudadanaService.registrarQuejaCiudadana(quejaDTO);
	}

	@Override
	public List<QuejaCiudadanaDTO> consultarQuejasCiudadanas()
			throws NSJPNegocioException {
		return consultarQuejaCiudadanaService.consultarQuejasCiudadanas();
	}

	@Override
	public QuejaCiudadanaDTO consultarQuejaCiudadanaXId(Long idQueja)
			throws NSJPNegocioException {
		return consultarQuejaCiudadanaService.consultarQuejaCiudadanaXId(idQueja);
	}

	@Override
	public List<QuejaCiudadanaDTO> consultarQuejasPorEstatus(Long estatus)
			throws NSJPNegocioException {
		return consultarQuejaCiudadanaService.consultarQuejasPorEstatus(estatus);
	}

	@Override
	public void asignarMotivoRechazo(Long idQueja, MotivoRechazo motivoRechazo)
			throws NSJPNegocioException {
		asignarMotivoRechazoService.asignarMotivoRechazo(idQueja,motivoRechazo);
	}

}
