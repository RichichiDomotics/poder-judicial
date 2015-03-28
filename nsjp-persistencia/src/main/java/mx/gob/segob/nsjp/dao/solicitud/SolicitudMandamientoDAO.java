package mx.gob.segob.nsjp.dao.solicitud;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.SolicitudMandamiento;

public interface SolicitudMandamientoDAO extends GenericDao<SolicitudMandamiento, Long> {

	public List<SolicitudMandamiento> consultarSolicitudesMandamientoBy(EstatusSolicitud estado) throws NSJPNegocioException;
	
}
