package mx.gob.segob.nsjp.dao.solicitud;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.SolicitudAdjuntos;
import mx.gob.segob.nsjp.model.SolicitudAdjuntosId;

public interface SolicitudAdjuntosDAO extends GenericDao<SolicitudAdjuntos, SolicitudAdjuntosId> {
	public List<SolicitudAdjuntos> consultarAdjuntosXSolicitud(Long IdSolicitud);
	
}
