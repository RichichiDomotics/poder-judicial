package mx.gob.segob.nsjp.service.solicitud.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.service.solicitud.ActualizarStatusSolicitudEvidenciaService;
import mx.gob.segob.nsjp.service.solicitud.DesignarPeritoASolicitudService;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudTransformer;

@Service
@Transactional
public class DesignarPeritoASolicitudServiceImpl implements
		DesignarPeritoASolicitudService {
	@Autowired
	SolicitudDAO solicitudDAO;
	@Autowired
	FuncionarioDAO funcionarioDAO;
	@Autowired
	RegistrarSolicitudService registrarSolicitudService;
	@Autowired
	ActualizarStatusSolicitudEvidenciaService actualizaStatusService;
	
	@Override
	public List<SolicitudDTO> designarPeritoASolicitud(Long idSolicitud,
			List<FuncionarioDTO> peritos) throws NSJPNegocioException {
		
		List<SolicitudDTO> respuesta = new ArrayList<SolicitudDTO>();
		SolicitudDTO solicitudActual = SolicitudTransformer.solicitudTransformer(solicitudDAO.read(idSolicitud));				
		
		solicitudActual.setDocumentoId(null);
		for(FuncionarioDTO reg:peritos)
		{
			SolicitudDTO solicitudNueva = solicitudActual;
			solicitudNueva.setFechaCreacion(new Date());
			solicitudNueva.setDestinatario(reg);
			solicitudNueva.setEstatus(new ValorDTO(EstatusSolicitud.ABIERTA.getValorId()));
			solicitudNueva.setUsuarioSolicitante(solicitudActual.getUsuarioSolicitante());
			registrarSolicitudService.registrarSolicitud(solicitudNueva);
			respuesta.add(solicitudNueva);
		}
		SolicitudDTO actualizaSolicitud = new SolicitudDTO();
		actualizaSolicitud.setDocumentoId(idSolicitud);
		actualizaSolicitud.setEstatus(new ValorDTO(EstatusSolicitud.CERRADA.getValorId()));
		actualizaStatusService.actualizarStatusSolicitudEvidencia(actualizaSolicitud);
		return respuesta;
	}

}
