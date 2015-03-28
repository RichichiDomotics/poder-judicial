package mx.gob.segob.nsjp.service.solicitud.impl;

import java.util.Calendar;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDefensorDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.SolicitudDefensor;
import mx.gob.segob.nsjp.service.audiencia.ConsultarAudienciaService;
import mx.gob.segob.nsjp.service.infra.DefensoriaClienteService;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudDefensorEncargadoSalaService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudDefensorTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrarSolicitudDefensorEncargadoSalaServiceImpl implements
		RegistrarSolicitudDefensorEncargadoSalaService {

	@Autowired
	private ConsultarAudienciaService consultaAudienciaService;
	
	@Autowired
	private DefensoriaClienteService clienteService;
	
	@Autowired
	private SolicitudDefensorDAO solicitudDefensorDAO;
	
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	
	@Autowired
	private CasoDAO casoDAO;
	
	@Autowired
	private GenerarFolioSolicitudService generarFolioSolicitudService;
	
	@Override
	public void registrarSolicitudDefensorEncargadoSala(AudienciaDTO audiencia,
			Long imputadoId) throws NSJPNegocioException {
		
		audiencia = consultaAudienciaService.obtenerAudiencia(audiencia);
		InvolucradoDTO imputado = null;
		List<InvolucradoDTO> involucrados = audiencia.getInvolucrados();
		
		for(InvolucradoDTO involucrado : involucrados){
			if(involucrado.getElementoId().longValue() == imputadoId){
				imputado = involucrado;
			}
		}
		
		SolicitudDefensorDTO solicitud = new SolicitudDefensorDTO();
		Caso caso = casoDAO.consultarCasoPorExpediente(audiencia.getExpediente().getExpedienteId());
		
		solicitud.setNumeroCasoAsociado(caso.getNumeroGeneralCaso());
		NombreDemograficoDTO nombre = imputado.getNombresDemograficoDTO().get(0);
		for(NombreDemograficoDTO nd : imputado.getNombresDemograficoDTO()){
			if(nd.getEsVerdadero() != null && nd.getEsVerdadero()){
				nombre = nd;
			}
		}
		solicitud.setNombreDetenido(nombre.getNombre());
		solicitud.setApellidPaternoDetenido(nombre.getApellidoPaterno());
		solicitud.setApellidoMaternoDetenido(nombre.getApellidoMaterno());
		solicitud.setDetenido(nombre.getNombreCompleto());
		if(imputado.getEsDetenido() != null){
			solicitud.setEsDetenido(imputado.getEsDetenido());
		}else{
			solicitud.setEsDetenido(false);
		}
		solicitud.setInvolucradoDTO(imputado);
		solicitud.setDelitos(imputado.getDelitosCometidos());
		solicitud.setAudiencia(audiencia);
		solicitud.setFechaCreacion(Calendar.getInstance().getTime());
		solicitud.setTipoSolicitudDTO(new ValorDTO(TiposSolicitudes.DEFENSOR.getValorId()));
		solicitud.setTipoDocumentoDTO(new ValorDTO(TipoDocumento.SOLICITUD.getValorId()));
		solicitud.setNombreDocumento("Solicitud de Defensor");
		solicitud.setFormaDTO(new FormaDTO(Formas.SOLICITUD.getValorId()));
		solicitud.setExpedienteDTO(audiencia.getExpediente());
		solicitud.setEstatus(new ValorDTO(EstatusSolicitud.ABIERTA.getValorId()));
		solicitud.setFolioSolicitud(generarFolioSolicitudService.generarFolioSolicitud());
		solicitud.setFolioElementoDetenido(imputado.getFolioElemento());
		SolicitudDefensor sol = SolicitudDefensorTransformer.transformarSolicitudDefensoria(solicitud);
		sol.setNumeroExpediente(numeroExpedienteDAO.read(audiencia.getExpediente().getExpedienteId()));
		Long id = solicitudDefensorDAO.create(sol);
		solicitud.setDocumentoId(id);
		clienteService.enviarSolicitudDefensor(solicitud);
	}

}
