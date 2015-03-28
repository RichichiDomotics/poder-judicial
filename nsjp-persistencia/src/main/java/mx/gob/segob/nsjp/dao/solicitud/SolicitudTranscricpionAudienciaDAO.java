package mx.gob.segob.nsjp.dao.solicitud;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.SolicitudTranscripcionAudiencia;

public interface SolicitudTranscricpionAudienciaDAO extends GenericDao<SolicitudTranscripcionAudiencia, Long>{

	List<SolicitudTranscripcionAudiencia> consultarSolicitudesTrascripcionAudienciaPendientes();
	List<SolicitudTranscripcionAudiencia> consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(Long idAudiencia, Long tipoId,Long estatusId);
	List<SolicitudTranscripcionAudiencia> consultarSolicitudMaster(Long idAudiencia, Long idTipo);
	/**
	 * Consulta las solicitudes de transcripción de audiencia que estén en cierto estatus y que sean de cierto tipo
	 * @param tipoId Tipo de la solicitud de transcripción (transcripción o A/V)
	 * @param estatusId Estatus de la solicitud de transcripción
	 * @param fechaIni  Fecha inicial, de creación de la solicitud.
	 * @param fechaFin  Fecha final, de creación del a solicitud.
	 * @author Emigdio - GBP
	 * @return Lista de solicitudes
	 */
	List<SolicitudTranscripcionAudiencia> consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(Long tipoId,Long estatusId,Long dif,Long discriminanteId,Date fechaIni, Date fechaFin);

	
}
