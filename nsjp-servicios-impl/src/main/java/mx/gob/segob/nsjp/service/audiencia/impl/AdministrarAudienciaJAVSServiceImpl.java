/**
* Nombre del Programa : AdministrarAudienciaJAVSServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 8 Nov 2011
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
package mx.gob.segob.nsjp.service.audiencia.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.constants.ConstantesGenerales;
import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.SalaJAVSDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioAudienciaDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.usuario.UsuarioDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.SalaJAVS;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.audiencia.AdministrarAudienciaJAVSService;
import mx.gob.segob.nsjp.service.audiencia.CalcularCargaTrabajoAudienciaService;
import mx.gob.segob.nsjp.service.eventocita.ConsultarEventosCitasPorUsuarioService;
import mx.gob.segob.nsjp.service.infra.JAVSClienteService;
import mx.gob.segob.nsjp.ws.cliente.agendaraudienciajavs.EstadoAudiencia;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Contrato de los servicios que permiten consumir el Cliente 
 * que se conecta a los WS de .net alojados en el servidor de JAVS. 
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Repository
public class AdministrarAudienciaJAVSServiceImpl implements
		AdministrarAudienciaJAVSService {

	/**
     * Logger de la clase.
     */
   private final static Logger logger = Logger
           .getLogger(AdministrarAudienciaJAVSServiceImpl.class);
   
   @Autowired
   private JAVSClienteService javsClienteService;
   @Autowired
   private AudienciaDAO audienciaDAO;   
   @Autowired
   private UsuarioDAO usuarioDAO;
   @Autowired
   private SalaJAVSDAO salaJAVSDAO;
   @Autowired
   CalcularCargaTrabajoAudienciaService calcularCargaTrabajoAudienciaService;
   @Autowired
   FuncionarioDAO funcionarioDAO;
   @Autowired
   private FuncionarioAudienciaDAO funcionarioAudienciaDAO;
   @Autowired
   private ConsultarEventosCitasPorUsuarioService consultarEventosCitasPorUsuarioService;
   
	
   
   
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.audiencia.AdministrarAudienciaJAVSService#agendarAudiencia(java.lang.Long)
	 */
	@Override
	public Long agendarAudiencia(Long audienciaId) throws NSJPNegocioException {
		logger.info("JAVSClienteService - Servicio para agendarAudiencia: "+ audienciaId);
		
		Boolean esSalaJavs = seDesarrollaraEnSalaJAVS(audienciaId);
		//Long idEvento=-1L;
		Long idEvento=(long) ConstantesGenerales.NO_ES_JAVS;
		if(esSalaJavs ){
			Usuario usuario = consultarUsuarioPorAudiencia(audienciaId);
		
			idEvento = javsClienteService.agendarAudiencia(audienciaId, usuario.getClaveUsuario());
		}
		return idEvento;
	}
	
	@Override
	public Long reagendarAudiencia(Long audienciaId) throws NSJPNegocioException {
		logger.info("JAVSClienteService - Servicio para agendarAudiencia: "+ audienciaId);
		
		Boolean esSalaJavs = seDesarrollaraEnSalaJAVS(audienciaId);
		Long idEvento=(long) ConstantesGenerales.NO_ES_JAVS;
		if(esSalaJavs ){
			Usuario usuario = consultarUsuarioPorAudiencia(audienciaId);
			
			idEvento = javsClienteService.estadoAudiencia(audienciaId, usuario.getClaveUsuario());
			
			if(idEvento == ConstantesGenerales.AUDIENCIA_NO_ACTIVA){
				idEvento = javsClienteService.eliminarAudiencia(audienciaId, usuario.getClaveUsuario());
				
				if(idEvento == ConstantesGenerales.EXITO_ELIMINACION || idEvento == ConstantesGenerales.NO_HAY_AUDIENCIAS){
					idEvento = javsClienteService.agendarAudiencia(audienciaId, usuario.getClaveUsuario());
				}
			}						
		}
		return idEvento;
	}
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.audiencia.AdministrarAudienciaJAVSService#agendarAudiencia(java.lang.Long)
	 */
	@Override
	public Boolean esJAVS(Long audienciaId) throws NSJPNegocioException {
		logger.info("JAVSClienteService - Servicio para agendarAudiencia: "+ audienciaId);
		
		Boolean esSalaJavs = seDesarrollaraEnSalaJAVS(audienciaId);
		return esSalaJavs;
	}
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.audiencia.AdministrarAudienciaJAVSService#estadoAudiencia(java.lang.Long)
	 */
	@Override
	public Long consultarEstadoAudiencia(Long audienciaId) throws NSJPNegocioException {
		logger.info("JAVSClienteService - Servicio para agendarAudiencia: "+ audienciaId);
		
		Boolean esSalaJavs = seDesarrollaraEnSalaJAVS(audienciaId);
		//Long idEvento=-1L;
		Long idEvento=(long) ConstantesGenerales.NO_ES_JAVS;
		if(esSalaJavs ){
			Usuario usuario = consultarUsuarioPorAudiencia(audienciaId);
		
			idEvento = javsClienteService.estadoAudiencia(audienciaId, usuario.getClaveUsuario());
		}
		return idEvento;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.audiencia.AdministrarAudienciaJAVSService#consultarAudiencia(java.lang.Long)
	 */
	@Override
	public Long consultarAudiencia(Long audienciaId)
			throws NSJPNegocioException {
		logger.info("JAVSClienteService - Servicio para consultarAudiencia: "+ audienciaId);
		
		Boolean esSalaJavs = seDesarrollaraEnSalaJAVS(audienciaId);
		Long idEvento = (long) ConstantesGenerales.NO_ES_JAVS;
		if(esSalaJavs ){
			Usuario usuario = consultarUsuarioPorAudiencia(audienciaId);
			
			idEvento = javsClienteService.consultarAudiencia(audienciaId, usuario.getClaveUsuario());
		}
		return idEvento;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.audiencia.AdministrarAudienciaJAVSService#eliminarAudiencia(java.lang.Long)
	 */
	@Override
	public Long eliminarAudiencia(Long audienciaId)
			throws NSJPNegocioException {
		
		logger.info("JAVSClienteService - Servicio para eliminarAudiencia: "+ audienciaId);
		
		Boolean esSalaJavs = seDesarrollaraEnSalaJAVS(audienciaId);
		Long idRespuesta = (long) ConstantesGenerales.NO_ES_JAVS;
		if(esSalaJavs ){
			Usuario usuario = consultarUsuarioPorAudiencia(audienciaId);
			idRespuesta = javsClienteService.eliminarAudiencia(audienciaId, usuario.getClaveUsuario());
		}
		
		return idRespuesta;
	}

	private Usuario consultarUsuarioPorAudiencia(Long audienciaId) throws NSJPNegocioException {

		//Se requiere el id del usuario,al que se tiene asociado la audiencia
		//a través del número de Expediente, Funcionario y finalmente usuario.
		Funcionario fun = audienciaDAO.obtenerFuncionarioDeNumExpedienteDeAudiencia(audienciaId);
		if(fun==null)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		Usuario usuario = usuarioDAO.consultarUsuarioPorClaveFuncionario(fun.getClaveFuncionario());
		
		return usuario;
	}
	
	private Boolean seDesarrollaraEnSalaJAVS(Long audienciaId) throws NSJPNegocioException {
		
		logger.info(" seDesarrollaraEnSalaJAVS: "+ audienciaId);
		Audiencia audiencia = audienciaDAO.consultarAudienciaById(audienciaId); 
		
		if(audiencia.getSalaAudiencia()!= null ){
			Long salaId = audiencia.getSalaAudiencia().getSalaAudienciaId();
			if( salaId!= null && salaId > 0){
				logger.info(" SalaID "+ salaId);
				//Verificar si existe una sala JAVS con el mismo ID de Sala Audiencia
				SalaJAVS salaJAVS = salaJAVSDAO.recuperarSalaJAVS(salaId);
				
				if(salaJAVS!= null){
					logger.info(" Si es una Sala JAVS "+ salaJAVS.getSalaJAVSId());
					return true;
				}
			}
		}
		return false;
	}
	
	public Long cancelarAudienciaSistema(Long audienciaId) throws NSJPNegocioException {
		logger.info("Cancelando la audiencia id: "+ audienciaId);

		Long resultado=(long) ConstantesGenerales.NO_ES_JAVS;
		Audiencia loAudiencia = audienciaDAO.read(audienciaId);
		
		//Si la audiencia ya fue cancelada se notifica al usuario
		
		if(loAudiencia.getEstatus() != null && loAudiencia.getEstatus().getValorId() != null){

							//Elimina EventoCitas en las agendas de los participantes
							eliminarEventos(funcionarioAudienciaDAO.consultarFuncionariosPorAudiencia(audienciaId),
									loAudiencia.getFechaAudiencia(), loAudiencia.getFechaAudiencia());
					
							loAudiencia = audienciaDAO.read(audienciaId);
							//Se cambia el estatus de la audiencia
							loAudiencia.setEstatus(new Valor(EstatusAudiencia.SOLICITADA.getValorId()));
					
							//Libera la sala para que se pueda volver a utilizar
							loAudiencia.setDuracionEstimada(0);
							loAudiencia.setSalaAudiencia(null);
							loAudiencia.setFechaAsignacionSala(null);
							loAudiencia.setFechaAudiencia(null);
							loAudiencia.setFechaHoraFin(null);
							audienciaDAO.update(loAudiencia);							
		}
		return resultado;				
	}
	
	public Long cancelarAudiencia(Long audienciaId) throws NSJPNegocioException {
		logger.info("Cancelando la audiencia id: "+ audienciaId);

		Long resultado=(long) ConstantesGenerales.NO_ES_JAVS;
		Long estadoJAVS=0L;
		Audiencia loAudiencia = audienciaDAO.read(audienciaId);
		boolean bandera=true;
		boolean esJAVS=false;
		
		//Si la audiencia ya fue cancelada se notifica al usuario
		
		if(loAudiencia.getEstatus() != null && loAudiencia.getEstatus().getValorId() != null){
			if(loAudiencia.getEstatus().getValorId().longValue()  == EstatusAudiencia.CANCELADA.getValorId().longValue()){
				throw new NSJPNegocioException(CodigoError.AUDIENCIA_CANCELADA);
			}else{
				//Se cancela la audiencia programada si y solo si el estatus de la audiencia es Solicitada o en Proceso

				if(loAudiencia.getEstatus().getValorId().longValue()  == EstatusAudiencia.SOLICITADA.getValorId().longValue() ||
						loAudiencia.getEstatus().getValorId().longValue()  == EstatusAudiencia.PROGRAMADA.getValorId().longValue()	||
						loAudiencia.getEstatus().getValorId().longValue()  == EstatusAudiencia.REPROGRAMADA.getValorId().longValue()
						
					){			
					
					esJAVS=seDesarrollaraEnSalaJAVS(audienciaId);
					if(esJAVS==true){
						estadoJAVS=consultarEstadoAudiencia(audienciaId);
						if(estadoJAVS != ConstantesGenerales.AUDIENCIA_NO_ACTIVA){
							bandera=false;
						} 
					}
					
					if(bandera==true){
						
						if(esJAVS==true){
							//Se manda a cancelar la audiencia JAVS mediante el Cliente de Javs
							if(audienciaId!=0L){
								resultado=eliminarAudiencia(audienciaId);
							}
							
							if(resultado==ConstantesGenerales.ERROR_ELIMINACION || resultado==ConstantesGenerales.ERROR_SERVICIO_ELIMINACION){
								bandera=false;
							}
						}
						else{
							resultado=(long) ConstantesGenerales.NO_ES_JAVS;							
						}

						if(bandera==true){
							//Elimina EventoCitas en las agendas de los participantes
							eliminarEventos(funcionarioAudienciaDAO.consultarFuncionariosPorAudiencia(audienciaId),
									loAudiencia.getFechaAudiencia(), loAudiencia.getFechaAudiencia());
					
							loAudiencia = audienciaDAO.read(audienciaId);
							//Se cambia el estatus de la audiencia
							loAudiencia.setEstatus(new Valor(EstatusAudiencia.CANCELADA.getValorId()));
					
							//Libera la sala para que se pueda volver a utilizar
							loAudiencia.setDuracionEstimada(0);
							loAudiencia.setSalaAudiencia(null);
							audienciaDAO.update(loAudiencia);							
						}
					}					
					else{
						resultado=estadoJAVS;
					}
					
				}else{
					throw new NSJPNegocioException(CodigoError.FAIL_ESTATUS_AUDIENCIA);
				}

			}
		}
		return resultado;
	}
	
	public boolean cancelarAudienciaJAVS(Long audienciaId) throws NSJPNegocioException {
		logger.info("Cancelando la audiencia id: "+ audienciaId);

		boolean esJAVS=seDesarrollaraEnSalaJAVS(audienciaId);
		if(esJAVS==true){
			eliminarAudiencia(audienciaId);			
		}
		return esJAVS;			
	}
	/**
	 * Metodo que permite eliminar de la agenda asociados a una lista de Jueces
	 */
	private void eliminarEventos(List<Funcionario> jueces, Date fechaInicial, Date fechaFinal) {
		try {
			
			for (Funcionario juez : jueces) {
				List<EventoCitaDTO> eventosJuez = consultarEventosCitasPorUsuarioService.
					consultarEventosCitasPorFuncionario(new FuncionarioDTO(juez.getClaveFuncionario()), fechaInicial, fechaFinal);
				//Elimina el evento(s) del Juez
				for (EventoCitaDTO envento : eventosJuez) {
					logger.info("SE ELIMINA EL EVENTO " + envento.getEventoCitaId());
					consultarEventosCitasPorUsuarioService.eliminarEventoCita(envento);
				}
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
	}
}
