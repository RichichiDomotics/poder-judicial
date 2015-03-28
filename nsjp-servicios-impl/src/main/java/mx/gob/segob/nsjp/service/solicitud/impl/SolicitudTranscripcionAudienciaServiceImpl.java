package mx.gob.segob.nsjp.service.solicitud.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudTranscricpionAudienciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudTranscripcionAudiencia;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.EventoTransformer;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatalogoTransformer;
import mx.gob.segob.nsjp.service.documento.ConsultarArchivosDigitalesService;
import mx.gob.segob.nsjp.service.infra.PJClienteService;
import mx.gob.segob.nsjp.service.solicitud.SolicitudTranscripcionAudienciaService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.ConfInstitucionTransformer;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudTranscripcionAudienciaTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SolicitudTranscripcionAudienciaServiceImpl implements
		SolicitudTranscripcionAudienciaService {

	@Autowired
	SolicitudTranscricpionAudienciaDAO solicitudTranscripcionAudienciaDAO;
	@Autowired
	ConsultarArchivosDigitalesService consultarArchivosDigitalesService;
	@Autowired
	PJClienteService pjClienteService;
	@Override
	public AudienciaDTO consultarAudienciaDeSolicitudTranscripcion(
			SolicitudDTO solicitud) throws NSJPNegocioException {
		SolicitudTranscripcionAudiencia sta = null;
		sta = solicitudTranscripcionAudienciaDAO.read(solicitud.getDocumentoId());
		AudienciaDTO audiencia = null;
		if(sta != null && sta.getAudiencia() != null){
			audiencia = EventoTransformer.transformarAudienciaBasico(sta.getAudiencia());
		}else{
			audiencia = new AudienciaDTO();
			ValorDTO v = new ValorDTO();
			v.setIdCampo(TipoAudiencia.JUICIO_ORAL.getValorId());
			audiencia.setId(2L);
			audiencia.setTipoAudiencia(v);
			audiencia.setFechaEvento(Calendar.getInstance().getTime());
		}
		return audiencia;
	}

	@Override
	public List<SolicitudTranscripcionAudienciaDTO> consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(
			Long idAudiencia, Long tipoId, Long estatusId)
			throws NSJPNegocioException {
		
		List<SolicitudTranscripcionAudienciaDTO> solicitudesDTO = new ArrayList<SolicitudTranscripcionAudienciaDTO>();
		List<SolicitudTranscripcionAudiencia> solicitudes =  solicitudTranscripcionAudienciaDAO.consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(idAudiencia, tipoId, estatusId);
		if(solicitudes.size()>0)
		{
			for(SolicitudTranscripcionAudiencia row:solicitudes)
			{
				solicitudesDTO.add(SolicitudTranscripcionAudienciaTransformer.transformarSolicitudTranscripcionAudiencia(row));
			}
		}
		return solicitudesDTO;
	}
	
	@Override
	public List<SolicitudTranscripcionAudienciaDTO> consultarSolicitudMaster(Long idAudiencia, Long idTipo) throws NSJPNegocioException
	{
		
		List<SolicitudTranscripcionAudienciaDTO> solicitudesDTO = new ArrayList<SolicitudTranscripcionAudienciaDTO>();
		List<SolicitudTranscripcionAudiencia> solicitudes =  solicitudTranscripcionAudienciaDAO.consultarSolicitudMaster(idAudiencia, idTipo);
		
		if(solicitudes.size()>0)
		{
			for(SolicitudTranscripcionAudiencia row:solicitudes)
			{
				solicitudesDTO.add(SolicitudTranscripcionAudienciaTransformer.transformarSolicitudTranscripcionAudiencia(row));
			}
		}
		return solicitudesDTO;
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.solicitud.SolicitudTranscripcionAudienciaService#consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<SolicitudTranscripcionAudienciaDTO> consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(
			Long tipoId, Long estatusId,UsuarioDTO usuario, Date fechaIni, Date fechaFin) {

		List<SolicitudTranscripcionAudienciaDTO> solicitudesDTO = new ArrayList<SolicitudTranscripcionAudienciaDTO>();
		
		/*
		* Usado para obtener el discriminante Id
		*/
		  long discriminanteId = 0L; 
				

		if (usuario != null
				&& usuario.getFuncionario() != null
				&& usuario.getFuncionario().getDiscriminante() != null
				&& usuario.getFuncionario().getDiscriminante().getCatDiscriminanteId() != null) {

			discriminanteId = usuario.getFuncionario().getDiscriminante()
					.getCatDiscriminanteId();
		}
		
		List<SolicitudTranscripcionAudiencia> solicitudes =  solicitudTranscripcionAudienciaDAO.
		consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(tipoId, estatusId,null,discriminanteId,fechaIni, fechaFin);
		for(SolicitudTranscripcionAudiencia row:solicitudes)
		{
			solicitudesDTO.add(SolicitudTranscripcionAudienciaTransformer.transformarSolicitudTranscripcionAudiencia(row));
		}
		
		return solicitudesDTO;
	}

	@Override
	public SolicitudTranscripcionAudienciaDTO consultarDetalleSolicitudTranscripcion(
			Long solicitudId) {
		return SolicitudTranscripcionAudienciaTransformer.
		transformarSolicitudTranscripcionAudiencia(solicitudTranscripcionAudienciaDAO.read(solicitudId));
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.solicitud.SolicitudTranscripcionAudienciaService#enviarActualizacionSolicitudTranscripcionAudiencia(java.lang.Long)
	 */
	@Override
	public void enviarActualizacionSolicitudTranscripcionAudiencia(
			Long solicitudId) throws NSJPNegocioException{
		Solicitud solBD = solicitudTranscripcionAudienciaDAO.read(solicitudId);
		SolicitudDTO solicitud = new SolicitudDTO(solicitudId);
		solicitud.setInstitucion(ConfInstitucionTransformer.transformarInstitucion(solBD.getConfInstitucion()));
		solicitud.setArchivoDigital(ArchivoDigitalTransformer.transformarArchivoDigital(solBD.getArchivoDigital()));
		solicitud.setFolioSolicitud(solBD.getFolioSolicitud());
		solicitud.setFolioDocumento(solBD.getFolioDocumento());
		pjClienteService.envarActualizacionSeguimientoSolicitudTranscripcion
		(solicitud, solicitud.getArchivoDigital(),CatalogoTransformer.transformValor(solBD.getEstatus()));
		
	}

}
