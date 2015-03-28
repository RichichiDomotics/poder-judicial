package mx.gob.segob.nsjp.service.solicitud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudPericialDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudPericial;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.solicitud.ActualizarEstatusSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.AutorizarSolicitudEvidenciaService;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AutorizarSolicitudEvidenciaServiceImpl implements
		AutorizarSolicitudEvidenciaService {

	@Autowired 
	SolicitudDAO solicitudDAO;
	SolicitudPericialDAO solicitudPericialDAO;
	
	@Autowired
	ActualizarEstatusSolicitudService actualizarService;
	
	@Override
	public Long autorizarSolicitudEvidencia(SolicitudPericialDTO evidencia)
			throws NSJPNegocioException {
		SolicitudPericial s = new SolicitudPericial();
		SolicitudPericial sPadre = new SolicitudPericial();
		s = (SolicitudPericial) solicitudDAO.read(evidencia.getDocumentoId());
		sPadre = s.getSolicitudPadre();
		
		s.setEstatus(new Valor(EstatusSolicitud.CERRADA.getValorId()));
		sPadre.setEstatus(new Valor(EstatusSolicitud.CERRADA.getValorId()));
		
		//DATOS OBTENIDOS DEL SERVICIO
		s.setObservaciones(evidencia.getObservaciones());
		s.setFechaInicioPrestamo(evidencia.getFechaInicioPrestamo());
		s.setFechaFinPrestamo(evidencia.getFechaFinPrestamo());
		sPadre.setObservaciones(evidencia.getObservaciones());
		sPadre.setFechaInicioPrestamo(evidencia.getFechaInicioPrestamo());
		sPadre.setFechaInicioPrestamo(evidencia.getFechaInicioPrestamo());
		//s.setDocIdentificacion();
		//s.setFolioDoc();
		//s.setNombreAutorizado();
		
		solicitudDAO.saveOrUpdate(s);
		solicitudDAO.saveOrUpdate(sPadre);
		
		
		return 1L;
	}

}
