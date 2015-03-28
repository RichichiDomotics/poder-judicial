package mx.gob.segob.nsjp.service.solicitud;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;

public interface DesignarPeritoASolicitudService {

	public List<SolicitudDTO> designarPeritoASolicitud(Long idSolicitud, List<FuncionarioDTO> peritos) throws NSJPNegocioException;
	
}
