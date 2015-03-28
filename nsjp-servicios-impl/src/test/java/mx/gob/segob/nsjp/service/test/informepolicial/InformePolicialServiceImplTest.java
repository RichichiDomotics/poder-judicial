package mx.gob.segob.nsjp.service.test.informepolicial;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatFaltaAdministrativaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.informepolicial.DelitoIphDTO;
import mx.gob.segob.nsjp.dto.informepolicial.FaltaAdministrativaIphDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.OperativoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.TurnoLaboralIphDTO;
import mx.gob.segob.nsjp.dto.turnolaboral.TurnoLaboralDTO;
import mx.gob.segob.nsjp.service.informepolicial.InformePolicialHomologadoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.services.dtos.print.ExpedientePrint;

public class InformePolicialServiceImplTest extends
		BaseTestServicios<InformePolicialHomologadoService> {

	public void testobtenerFolioIPH() throws NSJPNegocioException {
		InformePolicialHomologadoDTO Folio = new InformePolicialHomologadoDTO();
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setUsuario(super.getUsuario());
		Folio = service.ObtenerFolioIPH(expedienteDTO);
		logger.info("Respuesta : " + Folio.getFolioIPH().toString());
		logger.info("ExpedienteID: " + Folio.getExpediente().getExpedienteId());
	}

	public void testIngresarDatosGenerales() throws NSJPNegocioException {
		InformePolicialHomologadoDTO informe = new InformePolicialHomologadoDTO();
		informe = service.consultarInformePorFolio(2011000005L);
		informe.setInformePolicialHomologadoId(null);
		informe.setNumEcoTransporte("PATRULLA-ECO6666");
		informe.setAsunto("Este es el asunto: Prueba");
		
		
//		// -----FORMAR DTO DE DOMICILIO(LUGAR-----
//		DomicilioDTO domicilio = new DomicilioDTO();
//		domicilio.setCalle("Calzada de tlalpan");
//		domicilio.setNumeroExterior("3000");
//		domicilio.setNumeroInterior("54");
//		domicilio.setReferencias("Sistema radiopolis");
//		domicilio.setEntreCalle1("Calle 99");
//		domicilio.setEntreCalle2("Calle 100");
//		domicilio.setAlias("Tlalpan");
//		domicilio.setEdificio("");
//
//		AsentamientoDTO asentamiento = new AsentamientoDTO();
//		asentamiento.setAsentamientoId((long) 1);
//		domicilio.setAsentamientoDTO(asentamiento);
//
//		EntidadFederativaDTO eFederativa = new EntidadFederativaDTO();
//		eFederativa.setEntidadFederativaId((long) 9);
//		domicilio.setEntidadDTO(eFederativa);
//
//		MunicipioDTO municipio = new MunicipioDTO();
//		municipio.setMunicipioId((long) 1);
//		domicilio.setMunicipioDTO(municipio);
//
//		CiudadDTO ciudad = new CiudadDTO();
//		ciudad.setCiudadId(1L);
//		domicilio.setCiudadDTO(ciudad);
//
//		ValorDTO tipoCalle = new ValorDTO();
//		tipoCalle.setIdCampo(72L);
//		domicilio.setValorCalleId(tipoCalle);
//
//		domicilio.setLatitud("006600");
//		domicilio.setLongitud("006606");
//
//		/* Datos adicionales de domicilio */
//		CalidadDTO calidad = new CalidadDTO();
//		calidad.setCalidades(Calidades.DOMICILIO);
//		domicilio.setExpedienteDTO(informe.getExpediente());
//		domicilio.setFechaCreacionElemento(new Date());
//		domicilio.setCalidadDTO(calidad);
//		// -----FORMAR DTO DE HECHO-----
//		HechoDTO hecho = new HechoDTO();
//		hecho.setDescNarrativa("Ira parejita, iba cruzando el puente cuando procedía  voltear a mi lado derecho, en eso me percato de que un sujeto sospechoso estaba haciendo cosas sospechosas y entonces comence a sospechar de el y mande la Clave 8 a mi comandante");
//		/* Asignar domicilio a un hecho */
//		hecho.setDomicilio(domicilio);
//		hecho.setExpediente(informe.getExpediente());
//
//		TiempoDTO tiempo = new TiempoDTO();
//		ValorDTO tipoRegistro = new ValorDTO();
//		tipoRegistro.setIdCampo(TipoTiempo.ESPECIFICAMENTE_EN.getValorId());
//		tiempo.setTipoRegistro(tipoRegistro);
//		tiempo.setDescripcion("Ira parejita, iba cruzando el puente cuando procedía  voltear a mi lado derecho, en eso me percato de que un sujeto sospechoso estaba haciendo cosas sospechosas y entonces comence a sospechar de el y mande la Clave 8 a mi comandante");
//		tiempo.setFechaInicio(DateUtils.obtener("10/08/2011", "10:42"));
//		hecho.setTiempo(tiempo);

		// ----FORMAR DTO DE FUNCIONARIOS (DESTINATARIO/ELABORA)----
		FuncionarioDTO destinatario = new FuncionarioDTO();
		destinatario.setNumeroEmpleado("2345");

//		ValorDTO tipoParticipacion = new ValorDTO();
//		tipoParticipacion.setIdCampo(1891L);
//		informe.setTipoParticipacion(tipoParticipacion);
//		informe.setHecho(hecho);
//		OperativoDTO operativo = new OperativoDTO();
		OperativoDTO operativo = null;
//		operativo.setNombre("Operativo");
//		operativo.setNombreComte("Comandante");
//		operativo.setNombreComteAgrupto("Agrupamiento");
		
		informe.setFuncionarioDestinatario(destinatario);
		informe.setObjetivosGenerales("Esta es la descripción de las observaciones capturadas");
		
		informe.setTurnoLaboralIphs(darTurnoIPH(informe.getInformePolicialHomologadoId()));
		informe.setDelitoIph(darDelitosIPH(informe.getInformePolicialHomologadoId()));
//		informe.setFaltaIph(darFaltasIPH(informe.getInformePolicialHomologadoId()));

		service.ingresarDatosGenerales(informe, operativo);
	}

	private List<TurnoLaboralIphDTO> darTurnoIPH(
			Long idIPH) {
		List<TurnoLaboralIphDTO> turnos=new ArrayList<TurnoLaboralIphDTO>();
		
		
		TurnoLaboralIphDTO turno=new TurnoLaboralIphDTO();
		turno.setId(null);
		TurnoLaboralDTO turLab=new TurnoLaboralDTO();
		turLab.setTurnoLaboralId(3L);
		turno.setTurnoLaboral(turLab);
		
		turnos.add(turno);
		
		return turnos;
	}

	private List<FaltaAdministrativaIphDTO> darFaltasIPH(
			Long idIPH) {
		List<FaltaAdministrativaIphDTO> faltas=new ArrayList<FaltaAdministrativaIphDTO>();
		
		
		FaltaAdministrativaIphDTO fal=new FaltaAdministrativaIphDTO();
		fal.setId(null);
		CatFaltaAdministrativaDTO catFal=new CatFaltaAdministrativaDTO();
		catFal.setCatFaltaAdministrativaId(3L);
		fal.setCatFaltaAdministrativa(catFal);
		
		faltas.add(fal);
		
		return faltas;
	}

	private List<DelitoIphDTO> darDelitosIPH(Long idIPH) {
		List<DelitoIphDTO> delitos=new ArrayList<DelitoIphDTO>();
		DelitoIphDTO del =new DelitoIphDTO();
		del.setId(null);
		del.setCatDelito(new CatDelitoDTO(3L));
		delitos.add(del);
		
		return delitos;
	}

	public void testConsultarInformes() {
		try {
			List<InformePolicialHomologadoDTO> informes = service
					.consultarInformes(false);
			assertNotNull(informes);
			logger.info("Existen " + informes.size() + " informes");
			for (InformePolicialHomologadoDTO inf : informes) {
				logger.info("-------------------------------");
				logger.info("Id: " + inf.getInformePolicialHomologadoId());
				logger.info("Folio: " + inf.getFolioIPH());
				if(inf.getFaltaIph()!=null)
					logger.info("Trae faltas administrativas ("+ inf.getFaltaIph().size() + ")");
				if(inf.getDelitoIph()!=null)
					logger.info("Trae delitos ("+inf.getDelitoIph().size()+")");
				if(inf.getInvolucradoIphs()!=null)
					logger.info("Trae incolucrados ("+inf.getInvolucradoIphs().size()+")");
				logger.info("Fecha Informe: " + inf.getFechaCaptura());
			}

		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testConsultarInformePorFolio(){
		try {
			InformePolicialHomologadoDTO informe = service.consultarInformePorFolio(2011000013L);
			assertNotNull(informe);
			logger.info("ID: "+informe.getInformePolicialHomologadoId());
			logger.info("Folio: "+informe.getFolioIPH());
			logger.info("ExpID: "+informe.getExpediente());
			logger.info("Observaciones: "+informe.getObjetivosGenerales());
			logger.info("Turno: "+informe.getTurnoLaboralIphs().get(0));
			logger.info("Delito: "+informe.getDelitoIph().get(0));
			logger.info("Falta: "+(informe.getFaltaIph()!=null && !informe.getFaltaIph().isEmpty()? informe.getFaltaIph().get(0): informe.getFaltaIph()));
			if(informe.getExpediente()!= null){
				ExpedientePrint.imprimirDatosExpediente(informe.getExpediente());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
