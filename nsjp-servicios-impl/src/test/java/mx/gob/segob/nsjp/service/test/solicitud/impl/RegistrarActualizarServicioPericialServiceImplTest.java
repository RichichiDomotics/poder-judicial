/**
* Nombre del Programa : RegistrarActualizarServicioPericialServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22/06/2011
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

import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.service.solicitud.RegistrarActualizarServicioPericialService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el 
 * CU Solicitar Servicio Pericial.
 * CU Solicitar Investigacion Pericial
 * 
 * Para dada una: 
 * -Solicitud Pericial a Registrar
 * -Solicitud Pericial a actualizar.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class RegistrarActualizarServicioPericialServiceImplTest extends BaseTestServicios<RegistrarActualizarServicioPericialService>{

	
	public void testGuardarCambiosSolicitudPericialNueva(){
		//SolicitudPericialDTO solicitudPericialDTO = generarSolicitudPericialDTONueva();
		//Con expediente
		SolicitudPericialDTO solicitudPericialDTO = generarSolicitudPericialDTONuevaNumeroExpediente(47L);
		solicitudPericialDTO.getExpedienteDTO().setNumeroExpedienteId(3L);
		solicitudPericialDTO.setDestinatario(new FuncionarioDTO(2L));
		try {
			solicitudPericialDTO.setPeritosDesignados(new ArrayList<FuncionarioDTO>());
			solicitudPericialDTO.getPeritosDesignados().add(new FuncionarioDTO(1L));
			solicitudPericialDTO.getPeritosDesignados().add(new FuncionarioDTO(2L));
			SolicitudPericialDTO respuesta = service.registrarActulizarSolicitudPericial(solicitudPericialDTO);
			logger.info(" Respuesta: "+ respuesta);
			//assertTrue("El id de la respuesta no puede ser menor a 0 : ", respuesta.getDocumentoId()>=0);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());			
		}
	}
	
	/**
	 * Prueba unitaria para el 
	 * CU Solicitar Servicio Pericial.
	 * Solicitud Pericial a actualizar 
	 * 
	 * @version 1.0
	 * @author GustavoBP
	 *
	 */
	public void testguardarCambiosSolicitudPericialActualizada(){
		//Cambiar el ID de la solicitud
		//SolicitudPericialDTO solicitudPericialDTO = generarSolicitudPericialDTOActualizada(34L);
		//Con expediente
		SolicitudPericialDTO solicitudPericialDTO = generarSolicitudPericialDTOActualizadaNumeroExpediente(1L , 34L);
		try {
			SolicitudPericialDTO respuesta = service.registrarActulizarSolicitudPericial(solicitudPericialDTO);
			logger.info(" Respuesta: "+ respuesta);
			//assertTrue("El id de la respuesta no puede ser menor a 0 : ", respuesta.getDocumentoId()>=0);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());			
		}
	}
	
	
	private SolicitudPericialDTO generarSolicitudPericialDTONueva(){
		//Nueva solicitud
		SolicitudPericialDTO solicitudPericialDTO = new SolicitudPericialDTO();
		//La fecha de Creacion es asignada en el servicio
		
		//Parametros requeridos del CU - Solicitante		
		//Nombre del Servidor Publico -- Nombre del Funcionario
		solicitudPericialDTO.setNombreSolicitante("Juan");		
		
		//Area Administrativa
		solicitudPericialDTO.setAreaOrigen(1L);
		//Fecha de Solicitud --> Fecha de de creacion
		solicitudPericialDTO.setFechaCreacion(new Date());
		
		//Parametros requeridos del CU - Para quien solicita
		//Tipo de Servicio --> Tipo de Solicitud
		solicitudPericialDTO.setTipoSolicitudDTO(new ValorDTO(TiposSolicitudes.ASESORIA.getValorId())); 
		//Especialidad
		solicitudPericialDTO.setEspecialidad(new ValorDTO(1728L));//Antropología Forense
		//Fecha Limite
		solicitudPericialDTO.setFechaLimite( new Date());
		
		//Parametros requeridos del CU - Motivo
		solicitudPericialDTO.setMotivo(" Motivo de la solicitud");
		
		//Parametros requeridos del CU - Avisar a Funcionario
		//Puesto del Funcionario
		FuncionarioDTO usuarioSolicitanteDTO =  new FuncionarioDTO(1L);
		//HARD CODE Atributos harcodeados para poder registrar/actualizar una solicitud pericial - Revisar el catalogo de puestos (Enumeraciones asociados Puestos y Solicitudes)
		usuarioSolicitanteDTO.setPuesto( new ValorDTO(Puestos.ABOGADO_DEFENSOR.getValorId(),"ABOGADO DEFENSOR"));
		solicitudPericialDTO.setUsuarioSolicitante(usuarioSolicitanteDTO);
		//EsEntregarNotificacionFisica
		solicitudPericialDTO.setEsEntregaNotificacionFisica(true);
		solicitudPericialDTO.setEstatus(new ValorDTO(EstatusSolicitud.ABIERTA.getValorId()));
		return solicitudPericialDTO;
	}
	
	
	private SolicitudPericialDTO generarSolicitudPericialDTOActualizada(Long idSolicitudPericialDTO){
		//Actualizar la solicitud  se asigna el idDocumento
		SolicitudPericialDTO solicitudPericialDTO = new SolicitudPericialDTO(idSolicitudPericialDTO);
		
		solicitudPericialDTO.setFechaModificacion(new Date());

		//Parametros requeridos del CU		
		//Nombre del Servidor Publico -- Nombre del Funcionario
		solicitudPericialDTO.setNombreSolicitante("Pedro");		
		//Area Administrativa
		solicitudPericialDTO.setAreaOrigen(2L);
		//Fecha de Solicitud --> Fecha de de creacion
		solicitudPericialDTO.setFechaModificacion(new Date());

		
		//Parametros requeridos del CU - Para quien solicita
		//Tipo de Servicio --> Tipo de Solicitud
		solicitudPericialDTO.setTipoSolicitudDTO(new ValorDTO(TiposSolicitudes.DICTAMEN.getValorId())); 
		//Especialidad
		solicitudPericialDTO.setEspecialidad(new ValorDTO(1728L));//Antropología Forense
		//Fecha Limite
		solicitudPericialDTO.setFechaLimite(new Date());

		//Parametros requeridos del CU - Motivo
		solicitudPericialDTO.setMotivo(" Motivo de la solicitud");
		
		//Parametros requeridos del CU - Avisar a Funcionario
		//Puesto del Funcionario
		FuncionarioDTO usuarioSolicitanteDTO =  new FuncionarioDTO(1L);
		//HARD CODE Atributos harcodeados para poder registrar/actualizar una solicitud pericial - Revisar el catalogo de puestos (Enumeraciones asociados Puestos y Solicitudes)
		usuarioSolicitanteDTO.setPuesto( new ValorDTO(Puestos.ABOGADO_DEFENSOR.getValorId(), "ABOGADO DEFENSOR"));
		solicitudPericialDTO.setUsuarioSolicitante(usuarioSolicitanteDTO);
		//EsEntregarNotificacionFisica
		solicitudPericialDTO.setEsEntregaNotificacionFisica(false);
		
		
		return solicitudPericialDTO;
	}

	private SolicitudPericialDTO generarSolicitudPericialDTONuevaNumeroExpediente( Long idExpedienteDTO){
		ExpedienteDTO expedienteDTO = new ExpedienteDTO(idExpedienteDTO);
		SolicitudPericialDTO solicitudPericialDTO = generarSolicitudPericialDTONueva();
		
		solicitudPericialDTO.setExpedienteDTO(expedienteDTO);
		return solicitudPericialDTO;
	}

	private SolicitudPericialDTO generarSolicitudPericialDTOActualizadaNumeroExpediente( Long idExpedienteDTO, Long idSolicitudPericialDTO){
		ExpedienteDTO expedienteDTO = new ExpedienteDTO(idExpedienteDTO);
		SolicitudPericialDTO solicitudPericialDTO = generarSolicitudPericialDTOActualizada(idSolicitudPericialDTO);
		
		solicitudPericialDTO.setExpedienteDTO(expedienteDTO);
		return solicitudPericialDTO;
	}

}
