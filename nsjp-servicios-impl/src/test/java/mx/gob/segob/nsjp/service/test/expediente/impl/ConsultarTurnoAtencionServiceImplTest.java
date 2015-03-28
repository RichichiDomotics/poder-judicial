/**
* Nombre del Programa : GenerarTurnoServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria del servicio para consultar los turnos atendidos por un usuario
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

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.expediente.ConsultarTurnoAtencionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria del servicio para consultar los turnos atendidos por un usuario.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class ConsultarTurnoAtencionServiceImplTest extends
		BaseTestServicios<ConsultarTurnoAtencionService> {

	public void testConsultarTurnoAtencion () {
		
		//adminat = 2
		Long claveFuncionario = 2L;
		
		//atpenal = 3
//		Long claveFuncionario = 3L;
		Long discriminante = 1L;
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO(claveFuncionario);
		CatDiscriminanteDTO discriminanteDTO = new CatDiscriminanteDTO();
		discriminanteDTO.setCatDiscriminanteId(discriminante);
		funcionarioDTO.setDiscriminante(discriminanteDTO);
		
		UsuarioDTO usuarioDto = new UsuarioDTO();
		//atpenal
		usuarioDto.setAreaActual(new AreaDTO(new Long(Areas.ATENCION_TEMPRANA_PG_NO_PENAL.ordinal())));
		
		//adminat
//		usuarioDto.setAreaActual(new AreaDTO(new Long(Areas.ATENCION_TEMPRANA_PG_PENAL.ordinal())));
		
		usuarioDto.setFuncionario(funcionarioDTO);
		
//		UsuarioDTO usuarioDto = new UsuarioDTO();
//		AreaDTO areaDTO = new AreaDTO();
//		areaDTO.setAreaId(new Long(Areas.COORDINACION_ATENCION_TEMPRANA_PG.ordinal()));
//		usuarioDto.setClaveUsuario("");
//		usuarioDto.setIdUsuario(59L);
////		usuarioDto.setPassword("usuario");
//		usuarioDto.setAreaActual(areaDTO);
		
		try {
			List<TurnoDTO> resTurnos = service.consultarTurnosAtendidosPorUsuario(usuarioDto,true);
			assertTrue("La lista debe tener al menos un turno", resTurnos.size()>0);
			for (TurnoDTO turnoDTO : resTurnos) {
				logger.info("/**** TURNO ID : " + turnoDTO.getTurnoId() + "  Expediente ID: " + turnoDTO.getExpediente().getExpedienteId() +
						"  Numero Expediente ID: " + turnoDTO.getExpediente().getNumeroExpedienteId() );
				for (InvolucradoDTO inv: turnoDTO.getExpediente().getInvolucradosDTO()) {
					List<NombreDemograficoDTO> listNomDTO = inv.getNombresDemograficoDTO();
					logger.info("Involucrado ID: " + inv.getElementoId() + "****/");
					for (NombreDemograficoDTO nombreDemograficoDTO : listNomDTO) {
						logger.info("Nombre ID: " + nombreDemograficoDTO.getNombreDemograficoId());
					} 
				}
				
				if(turnoDTO.getExpediente()!=null ){
					logger.info(" Expediente:"+ turnoDTO.getExpediente().getCasoDTO());
					logger.info(" Expediente:"+ turnoDTO.getExpediente().getCasoDTO().getNumeroGeneralCaso());
					logger.info(" Expediente:"+ turnoDTO.getExpediente().getNumeroExpedienteId());
					logger.info(" Expediente:"+ turnoDTO.getExpediente().getNumeroExpediente());
					logger.info(" Expediente:"+ turnoDTO.getExpediente().getStrFechaApertura());
					logger.info("Este es el expediente con calidad de denunciante"+turnoDTO.getExpediente().getInvolucradoByCalidad(Calidades.DENUNCIANTE));
					logger.info("invol tamaño"+turnoDTO.getExpediente().getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
					logger.info("invol tamaño de"+turnoDTO.getExpediente().getInvolucradosDTO().size());
				}
				if (turnoDTO.getExpediente().getDelitoPrincipal()!=null)
					logger.info("Delito :" + turnoDTO.getExpediente().getDelitoPrincipal());//.getNombreDelito());
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}			
	}
	
	public void testObtenerTurnoParaAtencion () {
		TurnoDTO turnoDTO = new TurnoDTO();
		TurnoDTO turnoResDTO = new TurnoDTO();
		
		turnoDTO.setTipoTurno(TipoTurno.PENAL);
		//turnoDTO.setEsUrgente(true);
		
		try {
			turnoResDTO = service.obtenerTurnoParaAtencion(turnoDTO);
			assertFalse("El numero siguiente no puede ser nulo ", turnoResDTO.getNumeroTurno().isEmpty());
			logger.info("Turno id : " + turnoResDTO.getTurnoId() + " Numero : " + turnoResDTO.getNumeroTurno());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	public void testObtenerTurnosPorUsuario () {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario(97L);
		usuarioDTO.setAreaActual(new AreaDTO(Areas.COORDINACION_ATENCION_TEMPRANA_PG));
		try {
			List<TurnoDTO> respuesta = service.consultarTurnosAtendidosPorUsuario(usuarioDTO,false);
			assertNotNull(respuesta);
			for (TurnoDTO turnoDTO : respuesta) {
				logger.info("Turno : " + turnoDTO.toString());
				if (turnoDTO.getExpediente()!=null) {
					logger.info("Expediente " + turnoDTO.getExpediente().toString());
					logger.info("Involucrados " + turnoDTO.getExpediente().getInvolucradoByCalidad(Calidades.DENUNCIANTE).toString());
					if (turnoDTO.getExpediente().getDelitoPrincipal()!=null)
						logger.info("Delito principal " + turnoDTO.getExpediente().getDelitoPrincipal().toString());
					
					if ( turnoDTO.getExpediente().getInvolucradosDTO()!= null ){
						List<InvolucradoDTO> lInvolucradoDTO = turnoDTO.getExpediente().getInvolucradosDTO();
						for (InvolucradoDTO involucradoDTO : lInvolucradoDTO) {
							logger.info("-------------INVOLUCRADO------------------");
							logger.info("** ID:" + involucradoDTO.getElementoId());
							logger.info("** ORGNANIZACION:" + involucradoDTO.getOrganizacionDTO());
							if (involucradoDTO.getTipoPersona().equals(0L) && involucradoDTO.getOrganizacionDTO()!= null){
								OrganizacionDTO organizacionDTO = involucradoDTO.getOrganizacionDTO();
								logger.info("** Nombre:" + organizacionDTO.getNombreOrganizacion());
								logger.info("** Nombre:" + organizacionDTO.getStrFechaCreacion());
								
							}
						}
					}
					
				}
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	
	public void testObtenerTurnosPorFiltro(){
		TurnoDTO turnoFiltro = new TurnoDTO();
//		turnoFiltro.setTurnoId(1L);
//		turnoFiltro.setExpediente(new ExpedienteDTO(1L));
//		turnoFiltro.setUsuario(new UsuarioDTO(3L));
//		turnoFiltro.setNumeroTurno("1");
		turnoFiltro.setTipoTurno(TipoTurno.JUDICIAL);
//		turnoFiltro.setEsUrgente(true);
//		turnoFiltro.setFechaAtencion(new Date());
//		turnoFiltro.setEstado(EstatusTurno.ATENDIDO);
		
		List<TurnoDTO> turnos;
		try {
			turnos = service.obtenerTurnosPorFiltro(turnoFiltro);
		
			logger.info("Turnos recuperados:" + turnos.size());
			for (TurnoDTO turno : turnos) {
				logger.info(" *******Turno:"+ turno.getTurnoId());
				logger.info(" Turno:"+ turno.getExpediente());
				logger.info(" Turno:"+ turno.getUsuario());
				logger.info(" Turno:"+ turno.getNumeroTurno());
				logger.info(" Turno:"+ turno.getExpediente());
				if(turno.getExpediente()!=null ){
					logger.info(" Expediente:"+ turno.getExpediente().getNumeroExpediente());
					logger.info(" Expediente:"+ turno.getExpediente().getNumeroExpedienteId());
					logger.info(" Expediente:"+ turno.getExpediente().getCasoDTO());
				}
					
			}
			logger.info("Turnos recuperados:" + turnos.size());
		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}
	 
	}
	
	
	public void testConsultarTurnosConPermisosFuncionario(){
		List<TurnoDTO> turnos;
		try {
			Long claveFuncionario = 97L;
			Long discriminante = 1L;
			
			FuncionarioDTO funcionarioDTO = new FuncionarioDTO(claveFuncionario);
			CatDiscriminanteDTO discriminanteDTO = new CatDiscriminanteDTO();
			discriminanteDTO.setCatDiscriminanteId(discriminante);
			funcionarioDTO.setDiscriminante(discriminanteDTO);
			
			UsuarioDTO usuarioDto = new UsuarioDTO();
			usuarioDto.setFuncionario(funcionarioDTO);
			
			turnos = service.consultarTurnosConPermisosFuncionario(usuarioDto);
		
			logger.info("Turnos recuperados:" + turnos.size());
			for (TurnoDTO turno : turnos) {
				logger.info(" *******Turno:"+ turno.getTurnoId());
				logger.info(" Turno:"+ turno.getExpediente());
				logger.info(" Turno:"+ turno.getUsuario());
				logger.info(" Turno:"+ turno.getNumeroTurno());
				logger.info(" Turno:"+ turno.getExpediente());
				if(turno.getExpediente()!=null ){
					logger.info(" Expediente:"+ turno.getExpediente().getCasoDTO());
					logger.info(" Expediente:"+ turno.getExpediente().getCasoDTO().getNumeroGeneralCaso());
					logger.info(" Expediente:"+ turno.getExpediente().getNumeroExpedienteId());
					logger.info(" Expediente:"+ turno.getExpediente().getNumeroExpediente());
					logger.info(" Expediente:"+ turno.getExpediente().getStrFechaApertura());
					logger.info("Este es el expediente con calidad de denunciante"+turno.getExpediente().getInvolucradoByCalidad(Calidades.DENUNCIANTE));
					logger.info("invol tamaño"+turno.getExpediente().getInvolucradoByCalidad(Calidades.DENUNCIANTE).size());
					logger.info("invol tamaño de"+turno.getExpediente().getInvolucradosDTO().size());
					logger.info("Origen:"+turno.getExpediente().getOrigen());
					logger.info("getEstatusExpedientePadre:"+turno.getExpediente().getEstatusExpedientePadre());
				}
					
			}
			logger.info("Turnos recuperados:" + turnos.size());
		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}
		
	}
}
