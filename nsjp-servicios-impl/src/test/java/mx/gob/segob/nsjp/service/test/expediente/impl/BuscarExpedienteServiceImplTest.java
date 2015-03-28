/**
 * Nombre del Programa : BuscarExpedienteServiceImplTest.java
 * Autor                            : cesar
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 Apr 2011
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
package mx.gob.segob.nsjp.service.test.expediente.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.expediente.DatosGeneralesExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.DatosGeneralesExpedienteUAVDDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.FiltroExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author cesar
 * 
 */
public class BuscarExpedienteServiceImplTest extends
		BaseTestServicios<BuscarExpedienteService> {

	public void testBuscarExpedientePorEvidencia() {
		FiltroExpedienteDTO filtros = new FiltroExpedienteDTO();
		List<String> palabrasClave = new ArrayList<String>();

//		Id Usuario  7
//		claveUsuario= agentemp
//		Area = 10
		
		
		filtros.setNombreEvidencia(Objetos.VEHICULO.getNombreEntity());
		palabrasClave.add("vehiculo");
//		palabrasClave.add("veloz");
//		palabrasClave.add("60321975");
//		palabrasClave.add("papito");
//		palabrasClave.add("arena");
		filtros.setPalabrasClave(palabrasClave);

		List<ExpedienteDTO> respuesta = new ArrayList<ExpedienteDTO>();
		try {
			respuesta = service.buscarExpedientes(filtros);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}

		assertFalse("Lista respuesta ", respuesta.isEmpty());
		logger.info("Expedientes : " + respuesta.size());

	}

	public void _testBuscarExpedientePorAlias() {
		FiltroExpedienteDTO filtros = new FiltroExpedienteDTO();
		filtros.setAlias("chompi");
		filtros.setTipoBusqueda(1L);

		List<InvolucradoDTO> involocrados = new ArrayList<InvolucradoDTO>();
		try {
			involocrados = service.buscarExpedientesPorFiltros(filtros);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}

		assertFalse("Lista respuesta ", involocrados.isEmpty());

	}

	public void _testBuscarExpedientePorNombre() {
		FiltroExpedienteDTO filtros = new FiltroExpedienteDTO();
		filtros.setNombre("v");
		filtros.setApellidos("agus");

		List<InvolucradoDTO> involocrados = new ArrayList<InvolucradoDTO>();
		try {
			involocrados = service.buscarExpedientesPorFiltros(filtros);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}

		assertFalse("Lista respuesta ", involocrados.isEmpty());
		for (InvolucradoDTO involucradoDTO : involocrados) {
			logger.info("Involucrado " + involucradoDTO.getElementoId());
		}
	}

	public void testBuscarExpediente() {
		FiltroExpedienteDTO filtrosBusqueda = new FiltroExpedienteDTO();

		filtrosBusqueda.setNumeroExpediente("33");
//		UsuarioDTO usuarioDTO = new UsuarioDTO();
//		usuarioDTO.setAreaActual(new AreaDTO(Areas.COORDINACION_ATENCION_TEMPRANA_PG));
//		filtrosBusqueda.setUsuario(usuarioDTO);
		try {
			List<ExpedienteDTO> respuesta = service
					.buscarExpedientes(filtrosBusqueda);
			logger.info("Expedientes : " + respuesta.size());

			assertFalse("La lista de expedientes no debe estar vacia ",
					respuesta.isEmpty());
			for (ExpedienteDTO expedienteDTO : respuesta) {
				logger.info(" Involucrados"
						+ expedienteDTO.getInvolucradosDTO());
				List<InvolucradoDTO> linvolucradoDTO = expedienteDTO
						.getInvolucradosDTO();
				for (InvolucradoDTO involucradoDTO : linvolucradoDTO) {
					logger.info(" Involucrado "
							+ involucradoDTO.getElementoId());
				}
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void testBuscarExpedienteById() {
		try {
			ExpedienteDTO expDTO = new ExpedienteDTO();

			expDTO.setNumeroExpedienteId(826L);
//			expDTO.setHechoSolicitado(true);
			// expDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PENAL));
			
			ExpedienteDTO respuesta = service.obtenerExpediente(expDTO);

			assertTrue("El expediente debe tener un numero ",
					respuesta.getNumeroExpedienteId() > 0);
			logger.info("getNumeroExpedienteId "
					+ respuesta.getNumeroExpedienteId());
			logger.info("Expediente id " + respuesta.getExpedienteId());
			logger.info("Expediente Area " + respuesta.getArea());
			logger.info("CASO" + respuesta.getCasoDTO());
			logger.info("ELEMENTOS:" + respuesta.getElementosDTO());
			logger.info("No. expediente " + respuesta.getNumeroExpediente());
			logger.info("Involucrados " + respuesta.getInvolucradosDTO().size());
			logger.info("Involucrados por calidad "
					+ respuesta.getInvolucradoByCalidad(Calidades.DENUNCIANTE)
							.size());
			logger.info("Actividad Actual " + respuesta.getActividadActual());
			logger.info("Delitos " + respuesta.getDelitos());
			logger.info("Delito principal " + respuesta.getDelitoPrincipal());
			logger.info("Hecho " + respuesta.getHechoDTO());
			if (respuesta.getHechoDTO() != null) {
				HechoDTO hechoDTO = respuesta.getHechoDTO();
				logger.info("HechoId:" + hechoDTO.getHechoId());
				logger.info("DescNarrativa:" + hechoDTO.getDescNarrativa());
				if( hechoDTO.getTiempo()!= null){
					logger.info("Tiempo Hecho TiempoId : " + respuesta.getHechoDTO().getTiempo().getTiempoId());
					logger.info("Tiempo Hecho Descripcion: " + respuesta.getHechoDTO().getTiempo().getDescripcion());
					logger.info("Tiempo Hecho Inicio: " + respuesta.getHechoDTO().getTiempo().getFechaInicio());
					logger.info("Tiempo Hecho FechaFin: " + respuesta.getHechoDTO().getTiempo().getFechaFin());
					logger.info("Tiempo Hecho TipoRegistro: " + respuesta.getHechoDTO().getTiempo().getTipoRegistro());
				}
				if (hechoDTO.getLugar() != null){
					LugarDTO lugarDTO = hechoDTO.getLugar();	
					logger.info("Lugar Hecho Lugar : "+ lugarDTO);
					logger.info("Lugar Hecho Lugar Latitud: "+ lugarDTO.getLatitud());
					logger.info("Lugar Hecho Lugar Longitud: "+ lugarDTO.getLongitud());
					
					if(lugarDTO instanceof DomicilioDTO){
						DomicilioDTO domicilioDTO = (DomicilioDTO) lugarDTO;
						logger.info("DomicilioDTO : "+ domicilioDTO);
						
					}
				}
			}
			logger.info("Tiempo getObjetosDTO : " + respuesta.getObjetosDTO());

			logger.info("Documentos: " + respuesta.getDocumentosDTO().size());
			for (DocumentoDTO loDocumentoDTO : respuesta.getDocumentosDTO()) {
				logger.info("--------- ID Documento: "
						+ loDocumentoDTO.getDocumentoId() + " -------------");
				logger.info("Nombre Documento: "
						+ loDocumentoDTO.getNombreDocumento());
				logger.info("Tipo Documento: "
						+ loDocumentoDTO.getTipoDocumentoDTO().getValor());
				logger.info("Archivo Digital: "
						+ loDocumentoDTO.getArchivoDigital());
				if (loDocumentoDTO.getArchivoDigital() != null) {
					ArchivoDigitalDTO archivoDigitalDTO = loDocumentoDTO
							.getArchivoDigital();
					logger.info("Archivo Digital: "
							+ archivoDigitalDTO.getNombreArchivo());
					logger.info("Tipo Archivo: "
							+ archivoDigitalDTO.getTipoArchivo());
					logger.info("Size: "
							+ archivoDigitalDTO.getContenido().length);
				}
				logger.info("-------------------------------");

			}
			List<InvolucradoDTO> lInvolucradoDTO = respuesta
					.getInvolucradosDTO();
			for (InvolucradoDTO involucradoDTO : lInvolucradoDTO) {
				logger.info("-------------INVOLUCRADO------------------");
				logger.info("** ID:" + involucradoDTO.getElementoId());
				logger.info("Calidad:** "
						+ (involucradoDTO.getCalidadDTO() != null ? involucradoDTO
								.getCalidadDTO().getValorIdCalidad().getValor()
								: null) + "**  - "
						+ involucradoDTO.getCalidadDTO());
				logger.info(" TIPO PERSONA: **"
						+ (involucradoDTO.getTipoPersona().equals(0L) ? "Moral"
								: "Fisica") + "**");

				// DATOS GENRALES
				logger.info(" Domicilio:" + involucradoDTO.getDomicilio());
				// MORAL
				if (involucradoDTO.getTipoPersona().equals(0L)) {
					logger.info("** ORGNANIZACION:"
							+ involucradoDTO.getOrganizacionDTO());
					if (involucradoDTO.getOrganizacionDTO() != null) {
						OrganizacionDTO organizacionDTO = involucradoDTO
								.getOrganizacionDTO();
						logger.info("Nombre:"
								+ organizacionDTO.getNombreOrganizacion());
						logger.info("Domicilio:"
								+ organizacionDTO.getDomicilioDTO());
						logger.info("Nombre:"
								+ organizacionDTO.getNombreOrganizacion());

						logger.info("Representante Legal:"
								+ organizacionDTO.getRepresentanteLegal());
					}
				}
				// FISICA
				else {
					logger.info("** FISICA:"
							+ involucradoDTO.getNombresDemograficoDTO());

				}
			}
			logger.info(" Es Replicado:" + respuesta.getEsReplicado());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void testConsultarExpedientesPorCasoOK() {
		try {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO
					.setAreaActual(new AreaDTO(Areas.COORDINACION_ATENCION_TEMPRANA_PG));

			CasoDTO cdto = new CasoDTO();
			cdto.setCasoId(1L);
			cdto.setUsuario(usuarioDTO);
			List<ExpedienteDTO> respuesta = service
					.consultarExpedientesPorCaso(cdto);
			logger.info("Expedientes : " + respuesta.size());

			assertFalse("La lista de expedientes no debe estar vacia ",
					respuesta.isEmpty());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void testObtenerExpedientePorNumeroExpediente() {
		try {
			ExpedienteDTO resp = this.service
					.obtenerExpedientePorNumeroExpediente("NSJYUCPG20114433337");
			logger.info("Expedientes : " + resp.getNumeroExpedienteId());
			logger.info("Expedientes : " + resp.getExpedienteId());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void testConsultarExpedienteActividadAreaAnio() {
		FiltroExpedienteDTO filtrosBusqueda = new FiltroExpedienteDTO();

		filtrosBusqueda.setAnio(2011L);
		filtrosBusqueda
				.setIdActividad(Actividades.CANALIZAR_UNIDAD_INVESTIGACION
						.getValorId());
		filtrosBusqueda.setIdArea(new Long(Areas.COORDINACION_ATENCION_TEMPRANA_PG
				.ordinal()));

		try {
			List<ExpedienteDTO> respuesta = service
					.consultarExpedienteActividadAreaAnio(filtrosBusqueda);

			for (ExpedienteDTO expedienteDTO : respuesta) {
				logger.info("/**** Expediente ID "
						+ expedienteDTO.getExpedienteId()
						+ " Numero Expediente : "
						+ expedienteDTO.getNumeroExpediente() + " Estatus : "
						+ expedienteDTO.getEstatus() + " Caso : "
						+ expedienteDTO.getCasoDTO() + " Fecha Apertura : "
						+ expedienteDTO.getFechaApertura());
				if (expedienteDTO.getActividadActual() != null) {
					logger.info("/**** Actividad ID "
							+ expedienteDTO.getActividadActual().getNombre()
							+ " Fecha: "
							+ expedienteDTO.getActividadActual()
									.getFechaCreacion());
				}

				List<InvolucradoDTO> invsRes = expedienteDTO
						.getInvolucradoByCalidad(Calidades.DENUNCIANTE);
				for (InvolucradoDTO involucradoDTO : invsRes) {
					logger.info("/**** Involucrado : "
							+ involucradoDTO.getNombreCompleto());
				}
			}

			assertNotNull("La lista no puede estar vacia", respuesta);
			logger.info("/**** Expedientes recuperados : " + respuesta.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Permite consultar los datos generales de un Expediente dado un Numero de
	 * expediente
	 * 
	 * @author ricardog
	 */
	public void testBuscarDatosGeneralesExpedienteById() {
		try {
			ExpedienteDTO expDTO = new ExpedienteDTO();
			expDTO.setNumeroExpedienteId(183L);
			DatosGeneralesExpedienteDTO respuesta = service
					.obtenerDatosGeneralesExpediente(expDTO);
			logger.info("Fecha de apertura: " + respuesta.getFechaApertura());

			logger.info("Id Expediente: " + respuesta.getExpedienteId());
			logger.info("Estatus del Numero del expediente: "
					+ respuesta.getEstatusNumeroExpediente());
			logger.info("Tipo de Denuncia: " + respuesta.getOrigenExpediente());

			logger.info("TotalVehiculos: " + respuesta.getTotalVehiculos());
			logger.info("TotalEquiposComputo:"
					+ respuesta.getTotalEquiposComputo());
			logger.info("TotalEquiposTelefonicos:"
					+ respuesta.getTotalEquiposTelefonicos());
			logger.info("TotalArmas:" + respuesta.getTotalArmas());
			logger.info("TotalExplosivos:" + respuesta.getTotalExplosivos());
			logger.info("TotalSustancias:" + respuesta.getTotalSustancias());
			logger.info("TotalAnimales:" + respuesta.getTotalAnimales());
			logger.info("TotalAeronaves:" + respuesta.getTotalAeronaves());
			logger.info("TotalEmbarcaciones:"
					+ respuesta.getTotalEmbarcaciones());
			logger.info("TotalInmuelbes:" + respuesta.getTotalInmuelbes());
			logger.info("TotalNumerarios:" + respuesta.getTotalNumerarios());
			logger.info("TotalVegetales:" + respuesta.getTotalVegetales());
			logger.info("TotalDocumentosOficiales:"
					+ respuesta.getTotalDocumentosOficiales());
			logger.info("TotalJoyas:" + respuesta.getTotalJoyas());
			logger.info("TotalObrasDeArte:" + respuesta.getTotalObrasDeArte());
			logger.info("TotalOtrosObjestos:"
					+ respuesta.getTotalOtrosObjestos());

			logger.info("Denunciantes:" + respuesta.getTotalDenunciantes());
			logger.info("Victimas:" + respuesta.getTotalVictimas());
			logger.info("Probables responsables:"
					+ respuesta.getTotalProbablesResposables());
			logger.info("Testigos:" + respuesta.getTotalTestigos());
			logger.info("Traductores:" + respuesta.getTotalTraductores());
			logger.info("Quien detuvo:" + respuesta.getQuienDetuvo());
			logger.info("DOCUMENTOS: " + respuesta.getTotalDocumentosDelExpediente());
			logger.info("Origen del documento: " + respuesta.getOrigenExpediente());
			logger.info("Origen del Numero de Expediente: " + respuesta.getOrigenNumeroExpediente());

			
			assertTrue("El expediente debe tener un numero ",
					respuesta.getExpedienteId() > 0);

		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void testBuscarExpedienteConEventosHistorico() {

		try {
			List<ExpedienteDTO> expedientes = service
					.consultarExpedientesConEventosHistorico(1L,
							new UsuarioDTO());
			assertNotNull(expedientes);
			logger.debug("Expediente encontrados para el caso 1 :"
					+ expedientes.size());
			for (ExpedienteDTO expediente : expedientes) {
				logger.debug("Expediente (" + expediente.getExpedienteId()
						+ "): " + expediente.getNumeroExpediente());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			logger.error(e);
			assertNull(e);

		}
	}

	public void testObtenerNumeroExpedienteByNumExp() {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();

		expedienteDTO.setNumeroExpediente("NSJYUCPROC201133331");
		UsuarioDTO usuario = new UsuarioDTO();
		FuncionarioDTO funcionario = new FuncionarioDTO();
		CatDiscriminanteDTO catDis = new CatDiscriminanteDTO();
		catDis.setCatDiscriminanteId(26L);
		
		funcionario.setDiscriminante(catDis);
		usuario.setFuncionario(funcionario);
		

		try {
			expedienteDTO = service
					.obtenerNumeroExpedienteByNumExp(expedienteDTO,usuario);
			assertTrue("ExpedienteDTO no puede ser nulo : ",
					expedienteDTO.getNumeroExpedienteId() > 0);
			logger.info("ExpedienteDTO no puede ser nulo : "
					+ expedienteDTO.getNumeroExpedienteId());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}

	}

	public void testConsultarNumeroExpedienteHistorico() {
		try {
			UsuarioDTO usuario = new UsuarioDTO();
			FuncionarioDTO funcionario = new FuncionarioDTO();
			CatDiscriminanteDTO catDiscri = new CatDiscriminanteDTO();
			catDiscri.setCatDiscriminanteId(26L);
			
			funcionario.setDiscriminante(catDiscri);
			usuario.setFuncionario(funcionario);
			
			List<ExpedienteDTO> respuesta = service
					.consultarNumeroExpedienteHistorico(usuario);
			assertTrue("La lista debe tener minimo un registro: ",
					respuesta.size() > 0);
			for (ExpedienteDTO expedienteDTO : respuesta) {
				logger.info("-------------------------");
				logger.info("Causa ID : "
						+ expedienteDTO.getNumeroExpedienteId());
				for (ExpedienteDTO carpeta : expedienteDTO.getNumExpHijos()) {
					logger.info("  **Carpeta ID : "
							+ carpeta.getNumeroExpedienteId());
				}
				logger.info("-------------------------");
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}

	public void testConsultarExpedientesUsuarioArea() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		AreaDTO areaActual = new AreaDTO();
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO(9L);
		
//		areaActual.setAreaId(Areas.ATENCION_TEMPRANA_PG_NO_PENAL.parseLong());
		areaActual.setAreaId(15L);
		//usuarioDTO.setIdUsuario(1L);
		usuarioDTO.setAreaActual(areaActual);
		usuarioDTO.setFuncionario(funcionarioDTO);

		try {
			List<ExpedienteDTO> respuesta = service
					.consultarExpedientesUsuarioArea(usuarioDTO);
			assertTrue("La lista debe tener minimo un Expediente",
					respuesta.size() > 0);
			logger.info("La lista debe tener minimo un Expediente"
					+ respuesta.size());
			for (ExpedienteDTO expedienteDTO : respuesta) {
				logger.info("-------------------------");
				logger.info("Expediente: "+ expedienteDTO);
				logger.info("NumeroExpeidnte ID : "
						+ expedienteDTO.getNumeroExpedienteId());
				logger.info("NumeroExpeidnte : "
						+ expedienteDTO.getNumeroExpediente());
				logger.info("Estatus : " + expedienteDTO.getEstatus());
				logger.info("Etapa : " + expedienteDTO.getEtapa());
				logger.info("Delitos : " + expedienteDTO.getDelitos());
				logger.info("DelitoPrincipal : " + expedienteDTO.getDelitoPrincipal());
				logger.info("Caso : " + expedienteDTO.getCasoDTO());
				logger.info("-------------------------");
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}

	public void testConsultarExpedientePorAreaEstatusRemitente_Estatus() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario(1L);
		usuarioDTO.setAreaActual(new AreaDTO(1L));
		try {
			List<ExpedienteDTO> expedientes = service
					.consultarExpedientePorAreaEstatusRemitente(usuarioDTO,
							null, EstatusExpediente.CERRADO.getValorId());
			logger.info("Existen " + expedientes.size() + " expedientes");
			for (ExpedienteDTO nexp : expedientes) {
				logger.info("-------------------------------");
				logger.info("ID: " + nexp.getNumeroExpedienteId());
				logger.info("Num: " + nexp.getNumeroExpediente());
				logger.info("Fecha Can (Apertura): " + nexp.getFechaApertura());
				logger.info("Involucrados: " + nexp.getInvolucradosDTO().size());
				if (nexp.getInvolucradosDTO().size() > 0) {
					logger.info("*Inv1: "
							+ nexp.getInvolucradosDTO().get(0)
									.getNombreCompleto());
					logger.info("*Inv1 calidad: "
							+ nexp.getInvolucradosDTO().get(0).getCalidadDTO()
									.getValorIdCalidad().getValor());
				}
				String strDelito = (nexp.getDelitoPrincipal() != null) ? nexp
						.getDelitoPrincipal().getCatDelitoDTO().getNombre()
						: "Sin principal";
				logger.info("Delito Princ: " + strDelito);
				logger.info("Origen (area anterior): " + "TODO");
				logger.info("Estado: " + nexp.getEstatus().getValor());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue("FALLO", false);
		}
	}

	public void testConsultarExpedientePorAreaEstatusRemitente_Remitente() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario(1L);
		usuarioDTO.setAreaActual(new AreaDTO(1L));
		AreaDTO area = new AreaDTO(2L);
		try {
			List<ExpedienteDTO> expedientes = service
					.consultarExpedientePorAreaEstatusRemitente(usuarioDTO,
							area, null);
			logger.info("Existen " + expedientes.size() + " expedientes");
			for (ExpedienteDTO nexp : expedientes) {
				logger.info("-------------------------------");
				logger.info("ID: " + nexp.getNumeroExpedienteId());
				logger.info("Num: " + nexp.getNumeroExpediente());
				logger.info("Fecha Can (Apertura): " + nexp.getFechaApertura());
				logger.info("Involucrados: " + nexp.getInvolucradosDTO().size());
				if (nexp.getInvolucradosDTO().size() > 0) {
					logger.info("*Inv1: "
							+ nexp.getInvolucradosDTO().get(0)
									.getNombreCompleto());
					logger.info("*Inv1 calidad: "
							+ nexp.getInvolucradosDTO().get(0).getCalidadDTO()
									.getValorIdCalidad().getValor());
				}
				String strDelito = (nexp.getDelitoPrincipal() != null) ? nexp
						.getDelitoPrincipal().getCatDelitoDTO().getNombre()
						: "Sin principal";
				logger.info("Delito Princ: " + strDelito);
				logger.info("Origen (area anterior): " + "TODO");
				logger.info("Estado: " + nexp.getEstatus().getValor());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue("FALLO", false);
		}
	}

	public void testBuscarRemisionesConIPH() {
		try {
			List<ExpedienteDTO> respuesta = service
			.buscarRemisionesConIPH(EstatusExpediente.PENDIENTE_REVISION_COMO_IPH);
//			List<ExpedienteDTO> respuesta = service
//					.consultarNumeroExpedienteByTipoYEstatus(TipoExpediente.CARPETA_DE_EJECUCION, EstatusExpediente.ABIERTO);
			assertTrue("La lista debe tener minimo un registro : ",
					respuesta.size() > 0);
			logger.info("La lista debe tener minimo un registro : "
					+ respuesta.size());
			for (ExpedienteDTO expedienteDTO : respuesta) {
				logger.info(" Exp_:"+expedienteDTO.getExpedienteId());
				logger.info(" Exp_:"+expedienteDTO);
				logger.info(" Exp_DElito Principal:"+expedienteDTO.getDelitoPrincipal());
				logger.info(" Exp_Delitos:"+expedienteDTO.getDelitos());
				logger.info(" Exp_Involucrados:"+expedienteDTO.getInvolucradosDTO());
				List<InvolucradoDTO> involucradosDTO = expedienteDTO.getInvolucradosDTO();
				for (InvolucradoDTO involucradoDTO : involucradosDTO) {
					logger.info(" Inv_:"+involucradoDTO.getNombreCompleto());
					logger.info(" Inv_:"+involucradoDTO.getNombresDemograficoDTO());
				}
				logger.info(" Exp_Origen:"+expedienteDTO.getOrigen());
				logger.info(" Exp_Caso:"+expedienteDTO.getCasoDTO());
				logger.info(" Exp_:"+expedienteDTO.getEstatus());
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}

	}
	
	public void testConsultarCarpetasEjecucionPorCausa() {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(36L);

		try {
			List<ExpedienteDTO> respuesta = service
					.consultarCarpetasEjecucionPorCausa(expedienteDTO);
			assertTrue("La lista debe tener minimo un registro : ",
					respuesta.size() > 0);
			logger.info("La lista debe tener minimo un registro : "
					+ respuesta.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}

	}

	/**
	 * Prueba del uso del método de buscar expedientes por filtro desde poder
	 * judicial donde se buscan las causas por fecha
	 */
	public void testBuscarExpedientePJPorFiltro() {

		try {
			List<ExpedienteDTO> resultado = service
					.consultarExpedientesPorFiltro(new Date(), null, null,
							null, null);
			logger.debug("Tamaño del resultado:" + resultado.size());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			fail();
		}

	}
	
	
	public void testConsultarExpedientesPorUsuarioAreaEstatus() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		AreaDTO areaActual = new AreaDTO();
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO(8L);
		
//		areaActual.setAreaId(Areas.ATENCION_TEMPRANA_PG_NO_PENAL.parseLong());
		areaActual.setAreaId(10L);
		usuarioDTO.setAreaActual(areaActual);
		usuarioDTO.setFuncionario(funcionarioDTO);

		Long estatus = null; //EstatusExpediente.CERRADO.getValorId();

		try {
			List<ExpedienteDTO> respuesta = service
					.consultarExpedientesPorUsuarioAreaEstatus(usuarioDTO, estatus);
			assertTrue("La lista debe tener minimo un Expediente",
					respuesta.size() > 0);
			logger.info("La lista debe tener minimo un Expediente"
					+ respuesta.size());
			for (ExpedienteDTO expedienteDTO : respuesta) {
				logger.info("-------------------------");
				logger.info("Expediente: "+ expedienteDTO);
				logger.info("NumeroExpeidnte ID : "
						+ expedienteDTO.getNumeroExpedienteId());
				logger.info("NumeroExpeidnte : "
						+ expedienteDTO.getNumeroExpediente());
				logger.info("Estatus : " + expedienteDTO.getEstatus());
				logger.info("Etapa : " + expedienteDTO.getEtapa());
				logger.info("Delitos : " + expedienteDTO.getDelitos());
				logger.info("DelitoPrincipal : " + expedienteDTO.getDelitoPrincipal());
				logger.info("Caso : " + expedienteDTO.getCasoDTO());
				logger.info("-------------------------");
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testConsultarNumExpPorFuncionario() {
		try {
			ExpedienteDTO respuesa =service.consultarNumExpPorFuncionarioYNumExp(new Long(3), new Long(1));
			assertNotNull(respuesa);
			
			logger.info("-------------------------");
			logger.info("NumeroExpediente ID :: "+respuesa.getNumeroExpedienteId());
			logger.info("NumeroExpediente :: "+respuesa.getNumeroExpediente());
			logger.info("Permiso :: "+respuesa.isEsEscritura());
			logger.info("Propietario :: "+respuesa.isEsPropietario());
			logger.info("-------------------------");
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testConsultarExpedienteRelacionadoAArea(){
		
		String numeroExpediente = "NSJYUCPG2011543333D";
		Long areaId = 10L;
		try{
			ExpedienteDTO  expedienteDTO = service.consultarExpedienteRelacionadoAArea(numeroExpediente, areaId);
			assertNotNull(expedienteDTO);
			
			logger.info("-------------------------");
			logger.info("Expediente ID :: "+expedienteDTO.getExpedienteId());
			logger.info("NumeroExpediente ID :: "+expedienteDTO.getNumeroExpedienteId());
			logger.info("NumeroExpediente :: "+expedienteDTO.getNumeroExpediente());
			logger.info("-------------------------");
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * Permite consultar los datos generales de un Expediente de UAVD
	 * 
	 * @author ricardog
	 */
	public void obtenerResumenDeExpedienteUAVD() {
		try {

			DatosGeneralesExpedienteUAVDDTO respuesta = service
					.obtenerResumenDeExpedienteUAVD(new SolicitudDTO(643L));			
			logger.info("Tipo Solicitud: " + respuesta.getTipoSolicitud());
			logger.info("Nombre De La Victima: " + respuesta.getNombreDeLaVictima());
			logger.info("Delito: " + respuesta.getDelito());
			logger.info("Amp Solicitante: " + respuesta.getAmpSolicitante());
			logger.info("Area solicitante: " + respuesta.getAreaSolicitante());
			logger.info("Fecha de creacion del exp: " + respuesta.getFechaDeCreacionDelExpediente());
			logger.info("Estatus del expediente: " + respuesta.getEstatusDelExpediente());			
			
			assertTrue("El expediente debe tener un numero ",
					respuesta.getExpedienteId() > 0);

		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	
	public void testConsultarExpedientesConPermisoFuncionario(){
		
		Long claveFuncionario = 97L;
		
		 try {
			List<ExpedienteDTO> expedientesConPermisos = service.consultarExpedientescConPermisoFuncionario(claveFuncionario);
			assertFalse("La lista no puede ser vacia", expedientesConPermisos.isEmpty());
			for (ExpedienteDTO expedienteDTO : expedientesConPermisos) {
				logger.info("Expediente: "+ expedienteDTO.getExpedienteId());
				logger.info("Expediente: "+ expedienteDTO.getNumeroExpediente());
				logger.info("Expediente: "+ expedienteDTO.getDiscriminante());
				logger.info("Expediente: "+ expedienteDTO.getDiscriminante());
			}
			logger.info("Total: "+ expedientesConPermisos.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
		 
		
	}
	
	public void testbuscarExpedientesPorNumExpDiscriminanteArea(){
		 try {
			 
			 FiltroExpedienteDTO filtrosBusquedaExpediente = new FiltroExpedienteDTO();
			 UsuarioDTO usuariofir = new UsuarioDTO();
			 usuariofir.setAreaActual(new AreaDTO(27L));
			 
			 CatDiscriminanteDTO catDis = new CatDiscriminanteDTO();
			 catDis.setCatDiscriminanteId(2L); 
			 
			 FuncionarioDTO funcionario = new FuncionarioDTO();
			 funcionario.setDiscriminante(catDis);
			 usuariofir.setFuncionario(funcionario);
			 
			 ConfInstitucionDTO conIns = new ConfInstitucionDTO();
			 conIns.setConfInstitucionId(3L);
			 usuariofir.setInstitucion(conIns);
			 
			 String numeroExpediente = "NSJYUCPJ0100220123333";
			 
			 filtrosBusquedaExpediente.setNumeroExpediente(numeroExpediente);
			 filtrosBusquedaExpediente.setUsuario(usuariofir);
			 
			List<ExpedienteDTO> expedientesConPermisos = service.buscarExpedientesPorNumExpDiscriminanteArea(filtrosBusquedaExpediente);
			
			for (ExpedienteDTO expedienteDTO : expedientesConPermisos) {
				logger.info("Expediente: "+ expedienteDTO.getExpedienteId());
				logger.info("Expediente: "+ expedienteDTO.getNumeroExpediente());
				logger.info("Expediente: "+ expedienteDTO.getNumeroExpedienteId());
				logger.info("Expediente: "+ expedienteDTO.getCasoDTO().getNumeroGeneralCaso());
			}
			logger.info("Total: "+ expedientesConPermisos.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
		 
		
	}

}
