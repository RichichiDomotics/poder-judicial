/**
* Nombre del Programa : ProgramarAudienciaServiceImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Implementación del servicio de negocio para guardar la programación de una audiencia
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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.SalaTemporalDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioAudienciaDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.FuncionarioAudienciaId;
import mx.gob.segob.nsjp.model.SalaAudiencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.audiencia.AdministrarAudienciaJAVSService;
import mx.gob.segob.nsjp.service.audiencia.AsignarSalaTemporalService;
import mx.gob.segob.nsjp.service.audiencia.CalcularCargaTrabajoAudienciaService;
import mx.gob.segob.nsjp.service.audiencia.IngresarInvolucradoAudienciaService;
import mx.gob.segob.nsjp.service.audiencia.ProgramarAudienciaService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.EventoTransformer;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para guardar la programación de una audiencia
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
@Service
@Transactional
public class ProgramarAudienciaServiceImpl implements ProgramarAudienciaService {
    private static final Logger logger = Logger
    .getLogger(ProgramarAudienciaServiceImpl.class);
	@Autowired
	AudienciaDAO audienciaDAO;
	@Autowired
	SalaTemporalDAO salaTemporalDAO;
	@Autowired
	AsignarSalaTemporalService asignarSalaTemporalService;
	@Autowired
	FuncionarioDAO funcionarioDAO;
	@Autowired
	CalcularCargaTrabajoAudienciaService calcularCargaTrabajoAudienciaService;
	@Autowired
	GenerarFolioSolicitudService generarFolioService;
	@Autowired
	private IngresarInvolucradoAudienciaService audienciaIngresarInvolucradoAudienciaService;
	@Autowired
	AdministrarAudienciaJAVSService administrarAudienciaJAVSService;
	@Autowired
	FuncionarioAudienciaDAO funcionarioAudienciaDAO;
	
	
		
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.audiencia.ProgramarAudienciaService#guardarProgramacionAudiencia(mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO)
	 */
	@Override
	public void guardarProgramacionAudiencia(AudienciaDTO audienciaDTO) throws NSJPNegocioException {
		logger.info("guardarProgramacionAudiencia En el Servicio");
		Audiencia audiencia = audienciaDAO.read(audienciaDTO.getId());
	
			if(audiencia != null){
				
				//La udiencia ya ha sido programada por otro usuario
				if(audiencia.getFechaAsignacionSala() != null && audienciaDTO.getEsReprogramacionDeAudiencia() == false){
					throw new NSJPNegocioException(CodigoError.RANGO_FECHAS_CRUZADAS);
				}
		
				
				
				if(audiencia.getFechaAsignacionSala() != null && audienciaDTO.getEsReprogramacionDeAudiencia() == true){
					//Se cancela la audiencia y se procede a reprogramarla					
					administrarAudienciaJAVSService.cancelarAudiencia(audiencia.getAudienciaId());
					
					// Se eliminan lista de jueces asignados a la audiencia
					for (FuncionarioAudiencia loRelacion : audiencia.getFuncionarioAudiencias()) {
						loRelacion.setFuncionario(null);
						loRelacion.setAudiencia(null);
						funcionarioAudienciaDAO.delete(loRelacion);
					}
				}
				

				
				//Asignar la fecha de fin de la audiencia apartir de la fecha de inicio mas la duración estimada
				if( audienciaDTO.getFechaEvento()!=null && audienciaDTO.getDuracionEstimada()!=null){
					Date fechaFin = DateUtils.sumarMinutos(audienciaDTO.getFechaEvento(), audienciaDTO.getDuracionEstimada());
					audienciaDTO.setFechaHoraFin(fechaFin);
					
					if (!audienciaDTO.getSala().isTemporal()) {
						Boolean respConsulta = audienciaDAO.consultarAudienciasByFechaAudienciaYSala(
								audienciaDTO.getFechaEvento(), audienciaDTO
										.getFechaHoraFin(), audienciaDTO.getSala()
										.getSalaAudienciaId());
						if(respConsulta.equals(false)){
							throw new NSJPNegocioException(CodigoError.SALA_OCUPADA);
						}
					}
				}
				
				//actualizar datos de audiencia y cear/actualizar sala o sala temporal				
				if(audienciaDTO.getSala().isTemporal()){
					audiencia.setSalaAudiencia(null);
					asignarSalaTemporalService.asignarSalaTemporal(audienciaDTO);
				}else{
					if(audiencia.getSalaTemporal() != null){
						salaTemporalDAO.delete(audiencia.getSalaTemporal());
					}
					audiencia.setSalaTemporal(null);
					audiencia.setSalaAudiencia(new SalaAudiencia(audienciaDTO.getSala().getSalaAudienciaId()));
				}	
				
				//Se actualiza el estatus a Reprogramada
				if(audiencia.getFechaAsignacionSala() != null && audienciaDTO.getEsReprogramacionDeAudiencia() == true)
					audiencia.setEstatus(new Valor(EstatusAudiencia.REPROGRAMADA.getValorId()));
				else
					audiencia.setEstatus(new Valor(EstatusAudiencia.PROGRAMADA.getValorId()));

				
				
				EventoTransformer.tranformarAudienciaUpdateBasico(audiencia,audienciaDTO);
				
				
				//asignar los jueces a la audiencia
				Double cargaTrabajoAudiencia = calcularCargaTrabajoAudienciaService.calcularCargaTrabajoAudiencia(audienciaDTO);
				if(cargaTrabajoAudiencia==null || 
						Double.isInfinite(cargaTrabajoAudiencia)){
					cargaTrabajoAudiencia = 0.0;
				}
				FuncionarioAudiencia juezAudiencia = null;
				Funcionario juezBD = null;
				for(FuncionarioDTO juez:audienciaDTO.getFuncionarios()){
					juezAudiencia = new FuncionarioAudiencia();
					juezAudiencia.setId(new FuncionarioAudienciaId(audiencia.getAudienciaId(), juez.getClaveFuncionario()));
					if(!existeFuncionarioAudiencia(juez,audiencia.getFuncionarioAudiencias())){
						funcionarioAudienciaDAO.create(juezAudiencia);
						logger.info("Si agrega el Juez: " +  juezAudiencia);
					}
					
					juezBD = funcionarioDAO.read(juez.getClaveFuncionario());
					if(juezBD != null){
						juezBD.setCargaTrabajo((juezBD.getCargaTrabajo()!=null?juezBD.getCargaTrabajo().doubleValue():0.00)+
								cargaTrabajoAudiencia.doubleValue());
						juezBD.setEsPar(audiencia.getNumeroExpediente()!=null?audiencia.getNumeroExpediente().getEsPar():null);
						funcionarioDAO.saveOrUpdate(juezBD);
						logger.info("guardarProgramacionAudiencia var juezBD: "+ juezBD.getClaveFuncionario());
					}
					
				}

				audiencia.setFolioAudiencia(generarFolioService.generarFolioAudiencia());
				
				//Asociar involucrados a la Audiencia
				if( audienciaDTO.getInvolucrados()!= null &&  ! audienciaDTO.getInvolucrados().isEmpty()){
					for (InvolucradoDTO  involucradoDTO: audienciaDTO.getInvolucrados()) {
						if(involucradoDTO!=null && involucradoDTO.getElementoId()!= null)
							audienciaIngresarInvolucradoAudienciaService.asociarInvolucradoAAudiencia(involucradoDTO.getElementoId(), audienciaDTO.getId());		
					}
				}
				
				audienciaDAO.saveOrUpdate(audiencia);

				
			}
	}
	
	
	/**
	 * Quita los jueces del set de funcionariosAudiencia que no se encuentren en funcionarios
	 * @param funcionarioAudiencias
	 * @param funcionarios
	 */
	@SuppressWarnings("unused")
	private List<FuncionarioAudiencia> quitarJuecesEliminados(
			Set<FuncionarioAudiencia> funcionarioAudiencias,
			List<FuncionarioDTO> funcionarios) {
		List<FuncionarioAudiencia> aEliminar = new ArrayList<FuncionarioAudiencia>();
		boolean encontrado = false;
		for(FuncionarioAudiencia funcActual:funcionarioAudiencias){
			//buscar en la lista
			encontrado = false;
			for(FuncionarioDTO funDTO:funcionarios){
				if(funDTO.getClaveFuncionario().equals(funcActual.getId().getClaveFuncionario())){
					encontrado = true;
					break;
				}
			}
			if(!encontrado){
				aEliminar.add(funcActual);
			}
		}
		return aEliminar;
	}
	/**
	 * Verifica si existe el funcionario en la lista, para no insertarlo repetido
	 * @param juez
	 * @param funcionarioAudiencias
	 * @return
	 */
	private boolean existeFuncionarioAudiencia(FuncionarioDTO juez,
			Set<FuncionarioAudiencia> funcionarioAudiencias) {
		for(FuncionarioAudiencia funcionario:funcionarioAudiencias){
			if(funcionario.getId().getClaveFuncionario().equals(juez.getClaveFuncionario())){
				return true;
			}
		}
		return false;
	}
	@Override
	public Long crearAudienciaSiguiente(AudienciaDTO audienciaDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CREAR UNA AUDIENCIA VACÍA SIGUIENTE A UNA DADA ****/");
		
		if(audienciaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(audienciaDTO.getId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Audiencia audienciaEnCurso = audienciaDAO.read(audienciaDTO.getId());
		/*Compone la nueva audiencia*/
		Audiencia audienciaNueva=new Audiencia();
		audienciaNueva.setNumeroExpediente(audienciaEnCurso.getNumeroExpediente());
		audienciaNueva.setConsecutivo((short) (audienciaEnCurso.getConsecutivo()+1));
		audienciaNueva.setEstatus(new Valor(EstatusAudiencia.SOLICITADA.getValorId()));
		audienciaNueva.setTipo(audienciaEnCurso.getTipo());
		Long idAudiencia = audienciaDAO.create(audienciaNueva);
		
		return idAudiencia;
	}

}
