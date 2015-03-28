/**
 * Nombre del Programa : RecibirSolicitudCiudadanaDefensoriaServImplTest.java
 * Autor                            : cesar
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 17 May 2011
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
package mx.gob.segob.nsjp.service.test.solicitud.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.OrigenExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteRestDefensoriaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.solicitud.RecibirSolicitudCiudadanaDefensoriaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author cesar
 * 
 */
public class RecibirSolicitudCiudadanaDefensoriaServImplTest extends
		BaseTestServicios<RecibirSolicitudCiudadanaDefensoriaService> {

	public void testActualizaEstatusSolicitudDefensoria(){
		SolicitudDefensorDTO solDefDTO = new SolicitudDefensorDTO(39L);
		
		solDefDTO.setFuncionario(new FuncionarioDTO(1L));
		try{
			this.service.actualizaEstatusSolicitudDefensoria(solDefDTO);
			
		} catch(NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	public void testAsginarNumeroSolicitud(){
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		//Opcional: si no se asigna el NumeroExpedienteId entonces se asignara uno de forma automatica 
		expedienteDTO.setNumeroExpedienteId(1L);
		UsuarioDTO loUsuario = new UsuarioDTO();
		loUsuario.setIdUsuario(1L);		
		expedienteDTO.setUsuario(loUsuario);
		loUsuario.setFuncionario(new FuncionarioDTO(1L));
		SolicitudDefensorDTO solDefDTO = new SolicitudDefensorDTO();
		try{
			solDefDTO=this.service.generarAcuseAtencion(expedienteDTO);
			assertTrue("El id generado no puede ser menor a cero ",solDefDTO.getFolioDocumento()!=null);
		}	
		catch (NSJPNegocioException e) {
				logger.error(e.getMessage());
			}	
	}
	
	public void testGuardarSolicitanteSolicitudDefensoria(){
		ExpedienteDTO expDTO = new ExpedienteDTO();
		expDTO.setNumeroExpedienteId(1L);
		InvolucradoDTO imputado = new InvolucradoDTO();
		imputado.setExpedienteDTO(expDTO);
		
		try{
			InvolucradoDTO respuesta =  this.service.guardarDefendidoSolicitudDefensoria(imputado, 1L, null);
			assertTrue("El id generado no puede ser menor a cero ", respuesta.getElementoId()>=0);
			
		}catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}


		
	}
	
	//Permite guardar el motivo y a la fecha limite
	public void testGuardarMotivoSolicitudDefensoria(){
			
		SolicitudDefensorDTO sloDefDto = new SolicitudDefensorDTO(149L);
		sloDefDto.setMotivo("NUEVO MOTIVO");
		sloDefDto.setFechaLimite(new Date());
		try{
		this.service.guardarMotivoSolicitudDefensoria(sloDefDto);
		}catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}		
	}
	
	public void testActualizaImputadoSolicitudDefensoria(){
		InvolucradoDTO invDTO = new InvolucradoDTO();

		try{
			this.service.actualizaImputadoSolicitudDefensoria(invDTO);
			}catch (NSJPNegocioException e) {
				logger.error(e.getMessage());
			}

		
	}
	
	
	public void testSolicitudDefensoriaCiudadano () {
		InvolucradoDTO solicitante = new InvolucradoDTO();
		InvolucradoDTO imputado = new InvolucradoDTO();
		SolicitudDefensorDTO solicitudDefendorDTO = new SolicitudDefensorDTO();
		ExpedienteRestDefensoriaDTO expedienteDTO = new ExpedienteRestDefensoriaDTO();
		CalidadDTO calidadSol = new CalidadDTO();
		CalidadDTO calidadImp = new CalidadDTO();
		
		List<NombreDemograficoDTO> listNombSol = new ArrayList<NombreDemograficoDTO>();
		NombreDemograficoDTO nombreSol = new NombreDemograficoDTO();
		
		nombreSol.setNombre("Solicitante");
		nombreSol.setApellidoPaterno("AP Sol");
		nombreSol.setApellidoMaterno("AM Sol");		
		listNombSol.add(nombreSol);
		solicitante.setEsVivo(true);		
		solicitante.setNombresDemograficoDTO(listNombSol);
		
		List<NombreDemograficoDTO> listNombImp = new ArrayList<NombreDemograficoDTO>();
		NombreDemograficoDTO nombreImp = new NombreDemograficoDTO();		
		nombreImp.setNombre("Imputado");
		nombreImp.setApellidoPaterno("AP Imp");
		nombreImp.setApellidoMaterno("AM Imp");
		listNombImp.add(nombreImp);
		
		imputado.setEsVivo(true);
		imputado.setNombresDemograficoDTO(listNombImp);
		imputado.setFechaCreacionElemento(new Date());
		calidadSol.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
		calidadImp.setCalidades(Calidades.SOLICITANTE);
		imputado.setCalidadDTO(calidadImp);
		solicitante.setCalidadDTO(calidadSol);
		solicitante.setFechaCreacionElemento(new Date());
		ExpedienteDTO exp = new ExpedienteDTO();
		exp.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_DEFENSORIA));
		solicitudDefendorDTO.setEstatus(new ValorDTO(EstatusSolicitud.ABIERTA.getValorId()));
		solicitudDefendorDTO.setTipoSolicitudDTO(new ValorDTO(1L));
		
		expedienteDTO.setDireccionDondeSolicitanDefensor("Direccion prueba");
		expedienteDTO.setFechaDeCita(new Date());
		solicitudDefendorDTO.setExpedienteDTO(expedienteDTO);
		solicitudDefendorDTO.setRelacionConImputado(Relaciones.AMIGO);
		/*
		try {
			SolicitudDefensorDTO respuesta = service.guardarSolicitudDefensoria(imputado, solicitante, solicitudDefendorDTO);
			assertTrue("El id generado no puede ser menor a cero ", respuesta.getDocumentoId()>=0);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(),e);
			fail(e.getMessage());
		}
		*/
	}
	
	//Pruebas by Gama
	public void testGuardarSolicitante(){
		ExpedienteDTO expDTO = new ExpedienteDTO();
		expDTO.setExpedienteId(1L);		
		InvolucradoDTO loSolicitante = new InvolucradoDTO();
		loSolicitante.setExpedienteDTO(expDTO);
		List<NombreDemograficoDTO> nombresDemograficoDTO = new ArrayList<NombreDemograficoDTO>();
		
		NombreDemograficoDTO nd = new NombreDemograficoDTO();
		nd.setNombre("Ricardo");
		nd.setApellidoMaterno("Garcia");
		nd.setApellidoPaterno("Garcia");
		nd.setSexo("M");
		nombresDemograficoDTO.add(nd);
		loSolicitante.setNombresDemograficoDTO(nombresDemograficoDTO);
		
		//ES NECESARIO AGREGAR 
		loSolicitante.setIdSolicitudDefensor(149L);
		
		try{
			InvolucradoDTO respuesta =  this.service.guardarSolicitanteSolicitudDefensoria(loSolicitante);
			assertTrue("El id generado no puede ser menor a cero ", respuesta.getElementoId()>=0);
			
		}catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	
	public void testGuardarImputadoDefensoria_expedienteNuevo(){
		
		//Prueba que genera un nuevo numero de expediente 
		//Registra nuevos delitos asociados al expediente
		//Registra nuevod delitos persona
		//Registra delitos asociados al CatDelito 1 y 4
		SolicitudDefensorDTO loSolicitud = null;
		ExpedienteDTO expedienteDTO =new ExpedienteDTO();
		expedienteDTO.setOrigen(new ValorDTO(OrigenExpediente.DENUNCIA.getValorId()));
		UsuarioDTO loUsuario = new UsuarioDTO();
		loUsuario.setIdUsuario(1L);		
		expedienteDTO.setUsuario(loUsuario);
		loUsuario.setFuncionario(new FuncionarioDTO(1L));
		try {
			loSolicitud = this.service.generarAcuseAtencion(expedienteDTO);
		} catch (NSJPNegocioException e1) {
			e1.printStackTrace();
		}
		ExpedienteDTO expDTO = loSolicitud.getExpedienteDTO();		
		

		InvolucradoDTO imputado = generaDatosImputado();
		imputado.setExpedienteDTO(expDTO);		
		try{			
			InvolucradoDTO respuesta =  this.service.guardarDefendidoSolicitudDefensoria(imputado,null,null);
			assertTrue("El id generado no puede ser menor a cero ", respuesta.getElementoId()>=0);
			
		}catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testGuardarImputadoDefensoria_expedienteExistente(){
		//Indica que el expediente no es nuevo
		ExpedienteDTO expDTO = new ExpedienteDTO();
		expDTO.setExpedienteId(649L);
		InvolucradoDTO imputado = generaDatosImputado();
		imputado.setExpedienteDTO(expDTO);
		
		//Se registrarn los delitos del imputado
		List<DelitoDTO> delitosCometidos = new ArrayList<DelitoDTO>();
		DelitoDTO delito1 = new DelitoDTO();
		delito1.setDelitoId(57L);		
		DelitoDTO delito2 = new DelitoDTO();
		delito2.setDelitoId(58L);
		delitosCometidos.add(delito1);
		delitosCometidos.add(delito2);		
		imputado.setDelitosCometidos(delitosCometidos);
		
		try{			
			InvolucradoDTO respuesta =  this.service.guardarDefendidoSolicitudDefensoria(imputado, null, null);
			assertTrue("El id generado no puede ser menor a cero ", respuesta.getElementoId()>=0);
			
		}catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public InvolucradoDTO generaDatosImputado(){		
		InvolucradoDTO imputado = new InvolucradoDTO();		
		//Se registra el nombre completo del imputado
		List<NombreDemograficoDTO> nombresDemograficoDTO = new ArrayList<NombreDemograficoDTO>();
		NombreDemograficoDTO demografico =  new NombreDemograficoDTO();
		demografico.setNombre("NombreImputado");
		demografico.setApellidoMaterno("ApeMatImputado");
		demografico.setApellidoPaterno("ApePatImputado");
		nombresDemograficoDTO.add(demografico);		
		imputado.setNombresDemograficoDTO(nombresDemograficoDTO );

		//Se registrarn los delitos del imputado
		List<DelitoDTO> delitosCometidos = new ArrayList<DelitoDTO>();
		DelitoDTO delito1 = new DelitoDTO();
		delito1.setDelitoId(1L);		
		DelitoDTO delito2 = new DelitoDTO();
		delito2.setDelitoId(4L);
		delitosCometidos.add(delito1);
		delitosCometidos.add(delito2);		
		imputado.setDelitosCometidos(delitosCometidos);
		
		//Se registran los datos de las detenciones del imputado
		List<DetencionDTO> detenciones = new ArrayList<DetencionDTO>();
		DetencionDTO detencion1 = new DetencionDTO();
		detencion1.setDetencionId(1L);
		detencion1.setFechaInicioDetencion(new Date());
		detencion1.setFechaFinDetencion(new Date());
		detencion1.setMotivoDetencion("Nada mas..");
		detencion1.setObservaciones("Observaciones de la detencion");
		detencion1.setFuncionarioByFuncionarioDetiene(new FuncionarioDTO(1L));
		DomicilioDTO lugarDetencionDTO = new DomicilioDTO();
		lugarDetencionDTO.setElementoId(7L);
		detencion1.setLugarDetencionDTO(lugarDetencionDTO);
		
		
		DetencionDTO detencion2 = new DetencionDTO();
		detencion2.setDetencionId(2L);
		detencion2.setFechaInicioDetencion(new Date());
		detencion2.setFechaFinDetencion(new Date());
		detencion2.setMotivoDetencion("Nada mas..");
		detencion2.setObservaciones("Observaciones de la detencion");
		detencion2.setLugarDetencionDTO(lugarDetencionDTO);
		detencion2.setFuncionarioByFuncionarioDetiene(new FuncionarioDTO(1L));

		
		detenciones.add(detencion1);
		detenciones.add(detencion2);
		imputado.setDetenciones(detenciones);
		imputado.setEsDetenido(true);
		return imputado;
	}

	
}
