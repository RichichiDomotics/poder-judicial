/**
 * Nombre del Programa : RegistrarSolicitudDefensorAreaExternaServiceImpl.java
 * Autor                            : Emigdio
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30/06/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.solicitud.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.caso.EstatusCaso;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.involucrado.PersonalidadJuridica;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.SalaAudienciaDAO;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDefensorDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoWSDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorWSDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.CatDelito;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.SalaAudiencia;
import mx.gob.segob.nsjp.model.SolicitudDefensor;
import mx.gob.segob.nsjp.model.SolicitudDefensorDelito;
import mx.gob.segob.nsjp.model.SolicitudDefensorDelitoId;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.audiencia.AsignarSalaTemporalService;
import mx.gob.segob.nsjp.service.expediente.AdministrarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedientePorCasoImputadoService;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudDefensorAreaExternaService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para el registro de una solicitud de
 * defensor desde una institución externa
 * 
 * @version 1.0
 * @author Emigdio Hernández
 * 
 */
@Service("registrarSolicitudDefensorAreaExternaService")
@Transactional
public class RegistrarSolicitudDefensorAreaExternaServiceImpl implements
		RegistrarSolicitudDefensorAreaExternaService {

	@Autowired
	AsignarSalaTemporalService asignarSalaTemporalService;

	@Autowired
	AdministrarNumeroExpedienteService administrarNumeroExpedienteService;
	@Autowired
	AudienciaDAO audienciaDAO;
	@Autowired
	SalaAudienciaDAO salaAudienciaDAO;
	@Autowired
	SolicitudDefensorDAO solicitudDefensorDAO;
	@Autowired
	DelitoDAO delitoDAO;
	@Autowired
	private BuscarExpedientePorCasoImputadoService buscarExpedientePorCasoImputadoService;
	@Autowired
	private CasoDAO casoDAO;
	@Autowired
	private ExpedienteDAO expedienteDAO;
    @Autowired
    private GenerarFolioSolicitudService generarFolioSolicitudService;
	@Autowired
	private IngresarIndividuoService ingresarIndividuoService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.service.solicitud.
	 * RegistrarSolicitudDefensorAreaExternaService
	 * #registrarSolicitudDefensor(mx
	 * .gob.segob.nsjp.dto.solicitud.SolicitudDefensorWSDTO)
	 */
	@Override
	public SolicitudDefensorWSDTO registrarSolicitudDefensor(
			SolicitudDefensorWSDTO solicitud) throws NSJPNegocioException {

		if (solicitud == null) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		if (solicitud.getAudiencia() == null) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}

		// Asignar expediente
		ExpedienteDTO exp = administrarNumeroExpedienteService
				.buscarOCrearExpedienteDeInstitucionExterna(solicitud
						.getNumeroCasoAsociado());
		
		
		
		Audiencia audiencia = this.audienciaDAO.obtnerAudienciaByFolio(solicitud.getAudiencia().getFolioAudiencia());
        if (audiencia == null) {
            // Registrar Audiencia
            audiencia = new Audiencia();
            audiencia.setTipo(new Valor(solicitud.getAudiencia()
                    .getTipoAudienciaId()));
            audiencia.setFechaAsignacionSala(solicitud.getAudiencia()
                    .getFechaAsignacionSala());
            audiencia.setDuracionEstimada(solicitud.getAudiencia()
                    .getDuracionEstimada());
            audiencia.setEstatus(new Valor(solicitud.getAudiencia()
                    .getEstatusAudienciaId()));
            audiencia.setFechaAudiencia(solicitud.getAudiencia()
                    .getFechaHoraAudiencia());
            audiencia.setNumeroExpediente(exp != null
                    && exp.getNumeroExpedienteId() != null
                    ? new NumeroExpediente(exp.getNumeroExpedienteId())
                    : null);
            audiencia.setConsecutivo((short) 1);
            audiencia.setFolioAudiencia(solicitud.getAudiencia()
                    .getFolioAudiencia());
            audiencia.setEstatus(new Valor(EstatusAudiencia.PROGRAMADA
                    .getValorId()));
            // guardar audiencia

            audiencia.setAudienciaId(audienciaDAO.create(audiencia));
        }
		AudienciaDTO audDTO = new AudienciaDTO();
		audDTO.setId(audiencia.getAudienciaId());
		audDTO.setSala(new SalaAudienciaDTO());
		audDTO.setFechaEvento(solicitud.getAudiencia().getFechaHoraAudiencia());
		audDTO.setDuracionEstimada(solicitud.getAudiencia()
				.getDuracionEstimada());
		// Registrar sala o sala temporal
		if (solicitud.getAudiencia().isSalaTemporal()) {

			audDTO.getSala().setTemporal(true);
			audDTO.getSala().setDomicilioSala(
					solicitud.getAudiencia().getDomicilioSala());
			audDTO.getSala().setUbicacionSala(
					solicitud.getAudiencia().getUbicacionSala());
			audDTO.getSala().setMotivo(solicitud.getAudiencia().getMotivo());

			asignarSalaTemporalService.asignarSalaTemporal(audDTO);

		} else {
			// Buscar sala por nombre y descripcion , si no se encuentra crear
			// nueva
			SalaAudiencia sala = buscarCrearSalaAudiencia(solicitud);
			if (sala == null) {
				sala = crearSalaAudiencia(solicitud);
			}
			audiencia.setSalaAudiencia(sala);
		}
		// registrar solicitud defensor
		
		InvolucradoDTO imputado = new InvolucradoDTO();
		imputado.setFolioElemento(solicitud.getFolioElemento()); 

		NombreDemograficoDTO ndDTO = new NombreDemograficoDTO();
		ndDTO.setNombre(solicitud.getNombreImputado());
		ndDTO.setApellidoPaterno(solicitud.getApellidoPaternoImputado());
		ndDTO.setApellidoMaterno(solicitud.getApellidoMaternoImputado());
		imputado.getNombresDemograficoDTO().add(ndDTO);
		CalidadDTO calidad = new CalidadDTO();
		calidad.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
		imputado.setCalidadDTO(calidad);
		imputado.setEsDetenido(solicitud.getEsDetenido());
		
		ExpedienteDTO expedienteDTO = buscarExpedientePorCasoImputadoService.buscarExpedientePorCasoImputado(solicitud.getNumeroCasoAsociado(), imputado);
		if(expedienteDTO == null){
			Caso caso = casoDAO.obtenerCasoByNumeroGeneral(solicitud.getNumeroCasoAsociado());
			Expediente expediente = new Expediente();
			if(caso != null){
				expediente.setCaso(caso);
			}
			expediente.setFechaCreacion(Calendar.getInstance().getTime());
			expedienteDTO = new ExpedienteDTO();
			expediente.setPertenceConfInst(new ConfInstitucion(Instituciones.DEF.getValorId()));
			expediente.setEstatus(new Valor(EstatusExpediente.ABIERTO.getValorId()));
			expedienteDTO.setExpedienteId(expedienteDAO.create(expediente));
		}
		imputado.setExpedienteDTO(exp);
        // por defecto es fisica   
        imputado.setTipoPersona(new Long(PersonalidadJuridica.FISICA.ordinal()));
		imputado.setElementoId(ingresarIndividuoService.ingresarIndividuoInterInstitucion(imputado, false));

		
		SolicitudDefensor solicitudDefensor = new SolicitudDefensor();
		solicitudDefensor.setConfInstitucion(new ConfInstitucion(solicitud.getConfInstitucionId()));
		solicitudDefensor.setFolioElementoDetenido(solicitud.getFolioElemento());
		solicitudDefensor.setAudiencia(audiencia);
		solicitudDefensor.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
		solicitudDefensor.setFechaCreacion(new Date());
		solicitudDefensor.setFechaLimite(solicitud.getFechaLimite());
		solicitudDefensor.setNumeroCasoAsociado(solicitud.getNumeroCasoAsociado());
		solicitudDefensor.setNumeroExpediente(audiencia.getNumeroExpediente());
		solicitudDefensor.setNombreSolicitante(solicitud.getNombreSolicitante());
		solicitudDefensor.setSolicitanteExterno(solicitud.getSolicitanteExternoId());
		solicitudDefensor.setConfInstitucion(new ConfInstitucion(Instituciones.PJ.getValorId()));
		solicitudDefensor.setDetenido(solicitud.getNombreImputado()+" "+solicitud.getApellidoPaternoImputado()+" "+solicitud.getApellidoMaternoImputado());
		solicitudDefensor.setEsDetenido(solicitud.getEsDetenido());
		solicitudDefensor.setForma(new Forma(1L));
		solicitudDefensor.setNombreDocumento("Solicitud de Defensor para "+imputado.getFolioElemento());
		solicitudDefensor.setTipoDocumento(new Valor(1L));
		solicitudDefensor.setTipoSolicitud(new Valor(TiposSolicitudes.DEFENSOR.getValorId()));
		solicitudDefensor.setFolioSolicitud(solicitud.getFolioSolicitud());
		solicitudDefensor.setDocumentoId(solicitudDefensorDAO.create(solicitudDefensor));
		// registrarDelitos de la solicitud defensor
		SolicitudDefensorDelito solDelitoDB = null;
		if (solicitud.getDelitos()!=null) {
    		for (DelitoWSDTO delito : solicitud.getDelitos()) {
    			solDelitoDB = new SolicitudDefensorDelito();
    			Delito del = new Delito();
    			CatDelito catDel = new CatDelito(delito.getIdCatDelito());
    			del.setCatDelito(catDel);
    			del.setEsPrincipal(delito.isEsPrincipal());
    			del.setEsProbable(delito.isEsProbable());
    			del.setDelitoId(delitoDAO.create(del));
    			solDelitoDB.setDelito(del);
    			solDelitoDB.setId(new SolicitudDefensorDelitoId(del.getDelitoId(), solicitudDefensor.getDocumentoId()));
    			solDelitoDB.setSolicitudDefensor(solicitudDefensor);
    			solicitudDefensor.getSolicitudDefensorDelitos().add(solDelitoDB);
    		}
		}
		solicitudDefensorDAO.saveOrUpdate(solicitudDefensor);
		solicitud.setSolicitudId(solicitudDefensor.getDocumentoId());
		return solicitud;
	}

	/**
	 * Crea una nueva sala de audiencia basada en los datos de la solicitud de
	 * defensor
	 * 
	 * @param solicitud
	 *            Datos de origen
	 * @return Sala creada
	 */
	private SalaAudiencia crearSalaAudiencia(SolicitudDefensorWSDTO solicitud) {
		SalaAudiencia sala = new SalaAudiencia();
		sala.setNombreSala(solicitud.getAudiencia().getNombreSala());
		sala.setDomicilioSala(solicitud.getAudiencia().getDomicilioSala());
		sala.setUbicacionSala(solicitud.getAudiencia().getUbicacionSala());
		sala.setEsActivo(true);
		salaAudienciaDAO.create(sala);
		return sala;
	}

	/**
	 * Busca una sala de audiencia por nombre y descripción, si no se encuentra
	 * se crea sala nueva
	 * 
	 * @param solicitud
	 * @return
	 */
	private SalaAudiencia buscarCrearSalaAudiencia(
			SolicitudDefensorWSDTO solicitud) {
		SalaAudiencia filtro = new SalaAudiencia();
		filtro.setNombreSala(solicitud.getAudiencia().getNombreSala());
		filtro.setDomicilioSala(solicitud.getAudiencia().getDomicilioSala());
		filtro.setUbicacionSala(solicitud.getAudiencia().getUbicacionSala());

		List<SalaAudiencia> salas = salaAudienciaDAO
				.consultarSalasPorFiltro(filtro);
		return salas != null && !salas.isEmpty() ? salas.get(0) : null;
	}

}
