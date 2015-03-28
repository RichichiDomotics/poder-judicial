/**
 * 
 */
package mx.gob.segob.nsjp.service.programa.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.programa.ActoBuenaConductaDAO;
import mx.gob.segob.nsjp.dao.programa.AsignacionCentroDetencionDAO;
import mx.gob.segob.nsjp.dao.programa.AsignacionMedidaAlternaDAO;
import mx.gob.segob.nsjp.dao.programa.AsignacionProgramaDAO;
import mx.gob.segob.nsjp.dao.programa.CursoDAO;
import mx.gob.segob.nsjp.dao.programa.PeriodoAcumulacionPuntosDAO;
import mx.gob.segob.nsjp.dao.programa.ProgramaDAO;
import mx.gob.segob.nsjp.dao.programa.RemisionDAO;
import mx.gob.segob.nsjp.dao.programa.TrabajoDAO;
import mx.gob.segob.nsjp.dao.sentencia.SentenciaDAO;
import mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO;
import mx.gob.segob.nsjp.dto.programas.AsignacionCentroDetencionDTO;
import mx.gob.segob.nsjp.dto.programas.AsignacionMedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.programas.AsignacionProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.CursoDTO;
import mx.gob.segob.nsjp.dto.programas.PeriodoAcumulacionPuntosDTO;
import mx.gob.segob.nsjp.dto.programas.ProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.RemisionDTO;
import mx.gob.segob.nsjp.dto.programas.TrabajoDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.model.ActoBuenaConducta;
import mx.gob.segob.nsjp.model.AsignacionCentroDetencion;
import mx.gob.segob.nsjp.model.AsignacionMedidaAlterna;
import mx.gob.segob.nsjp.model.AsignacionPrograma;
import mx.gob.segob.nsjp.model.Curso;
import mx.gob.segob.nsjp.model.PeriodoAcumulacionPuntos;
import mx.gob.segob.nsjp.model.Programa;
import mx.gob.segob.nsjp.model.Remision;
import mx.gob.segob.nsjp.model.Sentencia;
import mx.gob.segob.nsjp.model.Trabajo;
import mx.gob.segob.nsjp.service.programa.AsignacionProgramaService;
import mx.gob.segob.nsjp.service.programa.impl.transform.AsignacionProgramaTransformer;
import mx.gob.segob.nsjp.service.sentencia.impl.transform.SentenciaTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AntonioBV
 *
 */
@Service
@Transactional
public class AsignacionProgramaServiceImpl implements AsignacionProgramaService {
	@Autowired
	private AsignacionCentroDetencionDAO asignacionCentroDetencionDAO;
	@Autowired
	private AsignacionMedidaAlternaDAO asignacionMedidaAlternaDAO;
	@Autowired
	private AsignacionProgramaDAO asignacionProgramaDAO;
	@Autowired
	private CursoDAO cursoDAO;
	@Autowired
	private ProgramaDAO programaDAO;
	@Autowired
	private RemisionDAO remisionDAO;
	@Autowired
	private SentenciaDAO sentenciaDAO;
	@Autowired
	private TrabajoDAO trabajoDAO;
	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired
	private ActoBuenaConductaDAO actoBuenaConductaDAO;
	@Autowired
	private PeriodoAcumulacionPuntosDAO periodoAcumulacionPuntosDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarAsignacionCentroDetencionPorId(mx.gob.segob.nsjp.dto.programas.AsignacionCentroDetencionDTO)
	 */
	@Override
	public AsignacionCentroDetencionDTO consultarAsignacionCentroDetencionPorId(
			AsignacionCentroDetencionDTO asignacionCentroDetencionDTO)
			throws NSJPNegocioException {
		AsignacionCentroDetencion asignacionCentroDetencion = AsignacionProgramaTransformer.transformar(asignacionCentroDetencionDTO);
		asignacionCentroDetencion = asignacionCentroDetencionDAO.consultarAsignacionCentroDetencionPorId(asignacionCentroDetencion); 		
		return AsignacionProgramaTransformer.transformar(asignacionCentroDetencion);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarAsignacionMedidaAlternaPorId(mx.gob.segob.nsjp.dto.programas.AsignacionMedidaAlternaDTO)
	 */
	@Override
	public AsignacionMedidaAlternaDTO consultarAsignacionMedidaAlternaPorId(
			AsignacionMedidaAlternaDTO asignacionMedidaAlternaDTO)
			throws NSJPNegocioException {
		AsignacionMedidaAlterna asignacionMedidaAlterna = AsignacionProgramaTransformer.transformar(asignacionMedidaAlternaDTO);
		asignacionMedidaAlterna = asignacionMedidaAlternaDAO.consultarAsignacionMedidaAlternaPorId(asignacionMedidaAlterna); 		
		return AsignacionProgramaTransformer.transformar(asignacionMedidaAlterna);
		
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarAsignacionProgramaPorId(mx.gob.segob.nsjp.dto.programas.AsignacionProgramaDTO)
	 */
	@Override
	public AsignacionProgramaDTO consultarAsignacionProgramaPorId(
			AsignacionProgramaDTO asignacionProgramaDTO)
			throws NSJPNegocioException {
		AsignacionPrograma asignacionPrograma = AsignacionProgramaTransformer.transformar(asignacionProgramaDTO);
		asignacionPrograma = asignacionProgramaDAO.consultarAsignacionProgramaPorId(asignacionPrograma); 		
		return AsignacionProgramaTransformer.transformar(asignacionPrograma);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarCursoPorId(mx.gob.segob.nsjp.dto.programas.CursoDTO)
	 */
	@Override
	public CursoDTO consultarCursoPorId(CursoDTO cursoDTO)
			throws NSJPNegocioException {
		Curso curso = AsignacionProgramaTransformer.transformar(cursoDTO, AsignacionProgramaTransformer.CON_ASIGNACIONES);
		curso = cursoDAO.consultarCursoPorId(curso); 		
		return AsignacionProgramaTransformer.transformar(curso, AsignacionProgramaTransformer.CON_ASIGNACIONES);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarProgramaPorId(mx.gob.segob.nsjp.dto.programas.ProgramaDTO)
	 */
	@Override
	public ProgramaDTO consultarProgramaPorId(ProgramaDTO programaDTO)
			throws NSJPNegocioException {
		Programa programa = AsignacionProgramaTransformer.transformar(programaDTO, AsignacionProgramaTransformer.CON_ASIGNACIONES);
		programa = programaDAO.consultarProgramaPorId(programa); 		
		return AsignacionProgramaTransformer.transformar(programa, AsignacionProgramaTransformer.CON_ASIGNACIONES);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarRemisionPorId(mx.gob.segob.nsjp.dto.programas.RemisionDTO)
	 */
	@Override
	public RemisionDTO consultarRemisionPorId(RemisionDTO remisionDTO)
			throws NSJPNegocioException {
		Remision remision = AsignacionProgramaTransformer.transformar(remisionDTO);
		remision = remisionDAO.consultarRemisionPorId(remision); 		
		return AsignacionProgramaTransformer.transformar(remision);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarSentenciaPorId(mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO)
	 */
	@Override
	public SentenciaDTO consultarSentenciaPorId(SentenciaDTO sentenciaDTO)
			throws NSJPNegocioException {
		Sentencia sentencia = SentenciaTransformer.transformar(sentenciaDTO);
		sentencia = sentenciaDAO.consultarSentenciaPorId(sentencia);
		
//		Integer idExpediente = sentencia.getNumeroExpediente().getNumeroExpedienteId().intValue();
//		Expediente expediente = sentencia.getNumeroExpediente().getExpediente();
//		sentencia.getNumeroExpediente().setExpediente(expediente);
//		Involucrado involucrado = involucradoDAO.consultarInvolucradosByExpediente(idExpediente.longValue()).get(0);
//		sentencia.setInvolucrado(involucrado);
		SentenciaDTO resultado = SentenciaTransformer.transformar(sentencia);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarTrabajoPorId(mx.gob.segob.nsjp.dto.programas.TrabajoDTO)
	 */
	@Override
	public TrabajoDTO consultarTrabajoPorId(TrabajoDTO trabajoDTO)
			throws NSJPNegocioException {
		Trabajo trabajo = AsignacionProgramaTransformer.transformar(trabajoDTO, AsignacionProgramaTransformer.CON_ASIGNACIONES);
		trabajo = trabajoDAO.consultarTrabajoPorId(trabajo); 		
		return AsignacionProgramaTransformer.transformar(trabajo, AsignacionProgramaTransformer.CON_ASIGNACIONES);
	}
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarSentencias(mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO)
	 */
	@Override
	public List<SentenciaDTO> consultarSentencias() throws NSJPNegocioException {
		List<Sentencia> lstSentencias = sentenciaDAO.consultarSentencias();
		List<SentenciaDTO> lstSentenciasDTO = new ArrayList<SentenciaDTO>();
		for(Sentencia sentencia :lstSentencias){
			SentenciaDTO sentenciaDTO = SentenciaTransformer.transformar(sentencia);
			lstSentenciasDTO.add(sentenciaDTO);
		}
		return lstSentenciasDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#registrarSentencia(mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO)
	 */
	@Override
	public void registrarSentencia(SentenciaDTO sentenciaDTO){
		Sentencia sentencia = SentenciaTransformer.transformar(sentenciaDTO);
		sentenciaDAO.merge(sentencia); 
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#crearPrograma(mx.gob.segob.nsjp.dto.programas.ProgramaDTO)
	 */
	@Override
	public ProgramaDTO crearPrograma(ProgramaDTO programaDTO) {
		Programa programa = AsignacionProgramaTransformer.transformar(programaDTO,false);
		Long programaId = programaDAO.create(programa); 
		programaDAO.flush();
		programaDTO.setProgramaId(programaId);
		return programaDTO;
	}
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#crearCurso(mx.gob.segob.nsjp.dto.programas.CursoDTO)
	 */
	@Override
	public CursoDTO crearCurso(CursoDTO cursoDTO) {
		Curso curso = AsignacionProgramaTransformer.transformar(cursoDTO,false);
		Long cursoId = cursoDAO.create(curso); 
		cursoDAO.flush();
		cursoDTO.setCursoId(cursoId);
		return cursoDTO;
	}
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#crearCursos(java.util.List)
	 */
	@Override
	public void crearCursos(List<CursoDTO> cursosDTO) {
		List<Curso> cursos = null;
		if (cursosDTO != null && !cursosDTO.isEmpty()){
			cursos = new ArrayList<Curso>();
			for (CursoDTO cursoDTO : cursosDTO){
				Curso curso = AsignacionProgramaTransformer.transformar(cursoDTO,false);
				cursos.add(curso);
			}
			cursoDAO.createAll(cursos);
		} 
	}
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#crearTrabajo(mx.gob.segob.nsjp.dto.programas.TrabajoDTO)
	 */
	@Override
	public TrabajoDTO crearTrabajo(TrabajoDTO trabajoDTO) {
		Trabajo trabajo = AsignacionProgramaTransformer.transformar(trabajoDTO,false);
		Long trabajoId = trabajoDAO.create(trabajo); 
		trabajoDAO.flush();
		trabajoDTO.setTrabajoId(trabajoId);
		return trabajoDTO;
	}
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#crearTrabajos(java.util.List)
	 */
	@Override
	public void crearTrabajos(List<TrabajoDTO> trabajosDTO) {
		List<Trabajo> trabajos = null;
		if (trabajosDTO != null && !trabajosDTO.isEmpty()){
			trabajos = new ArrayList<Trabajo>();
			for (TrabajoDTO trabajoDTO : trabajosDTO){
				Trabajo trabajo = AsignacionProgramaTransformer.transformar(trabajoDTO,false);
				trabajos.add(trabajo);
			}
			trabajoDAO.createAll(trabajos);
		} 
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarActoBuenaConductaPorId(mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO)
	 */
	@Override
	public ActoBuenaConductaDTO consultarActoBuenaConductaPorId(
			ActoBuenaConductaDTO actoBuenaConductaDTO) {
		ActoBuenaConducta actoBuenaConducta = AsignacionProgramaTransformer.transformar(actoBuenaConductaDTO, AsignacionProgramaTransformer.SIN_ASIGNACIONES);
		actoBuenaConducta = actoBuenaConductaDAO.consultarActoBuenaConductaPorId(actoBuenaConducta); 		
		return AsignacionProgramaTransformer.transformar(actoBuenaConducta, AsignacionProgramaTransformer.CON_ASIGNACIONES);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarActosBuenaConductaPorSentencia(mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO)
	 */
	@Override
	public List<ActoBuenaConductaDTO> consultarActosBuenaConductaPorSentencia(
			SentenciaDTO sentenciaDTO) throws NSJPNegocioException {
		Sentencia sentencia = SentenciaTransformer.transformar(sentenciaDTO);
		List<ActoBuenaConducta> actosBuenaConducta = actoBuenaConductaDAO.consultarActosBuenaConductaPorSentencia(sentencia);
		List<ActoBuenaConductaDTO> actosActoBuenaConductaDTO = null;
		if (actosBuenaConducta != null && !actosBuenaConducta.isEmpty()){
			actosActoBuenaConductaDTO = new ArrayList<ActoBuenaConductaDTO>();
			for (ActoBuenaConducta abc : actosBuenaConducta){
				actosActoBuenaConductaDTO.add(AsignacionProgramaTransformer.transformar(abc,AsignacionProgramaTransformer.CON_ASIGNACIONES));
			}
		}
		return actosActoBuenaConductaDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#crearActoBuenaConducta(mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO)
	 */
	@Override
	public ActoBuenaConductaDTO crearActoBuenaConducta(ActoBuenaConductaDTO actoBuenaConductaDTO) {
		ActoBuenaConducta actoBuenaConducta = AsignacionProgramaTransformer.transformar(actoBuenaConductaDTO, AsignacionProgramaTransformer.CON_ASIGNACIONES);
		Long actoBuenaConductaId = actoBuenaConductaDAO.create(actoBuenaConducta); 
		actoBuenaConductaDAO.flush();
		actoBuenaConductaDTO.setActoBuenaConductaId(actoBuenaConductaId);
		return actoBuenaConductaDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarPeriodoAcumulacionPuntosPorId(mx.gob.segob.nsjp.dto.programas.PeriodoAcumulacionPuntosDTO)
	 */
	@Override
	public PeriodoAcumulacionPuntosDTO consultarPeriodoAcumulacionPuntosPorId(
			PeriodoAcumulacionPuntosDTO periodoAcumulacionPuntosDTO) {
		PeriodoAcumulacionPuntos periodo = AsignacionProgramaTransformer.transformar(periodoAcumulacionPuntosDTO, AsignacionProgramaTransformer.SIN_ASIGNACIONES);
		periodo = periodoAcumulacionPuntosDAO.consultarPeriodoAcumulacionPuntosPorId(periodo); 		
		return AsignacionProgramaTransformer.transformar(periodo, AsignacionProgramaTransformer.CON_ASIGNACIONES);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarPeriodosAcumulacionPuntosPorSentencia(mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO)
	 */
	@Override
	public List<PeriodoAcumulacionPuntosDTO> consultarPeriodosAcumulacionPuntosPorSentencia(
			SentenciaDTO sentenciaDTO) throws NSJPNegocioException {
		Sentencia sentencia = null;
		if (sentenciaDTO != null){
			sentencia = new Sentencia();
			sentencia.setSentenciaId(sentenciaDTO.getSentenciaId());
		}
		List<PeriodoAcumulacionPuntos> periodos = periodoAcumulacionPuntosDAO.consultarPeriodosAcumulacionPuntosPorSentencia(sentencia);
		List<PeriodoAcumulacionPuntosDTO> periodosDTO = null;
		if (periodos != null && !periodos.isEmpty()){
			periodosDTO = new ArrayList<PeriodoAcumulacionPuntosDTO>();
			for (PeriodoAcumulacionPuntos per : periodos){
				periodosDTO.add(AsignacionProgramaTransformer.transformar(per,AsignacionProgramaTransformer.CON_ASIGNACIONES));
			}
		}
		return periodosDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#crearPeriodoAcumulacionPuntos(mx.gob.segob.nsjp.dto.programas.PeriodoAcumulacionPuntosDTO)
	 */
	@Override
	public PeriodoAcumulacionPuntosDTO crearPeriodoAcumulacionPuntos(
			PeriodoAcumulacionPuntosDTO periodoAcumulacionPuntosDTO) {
		PeriodoAcumulacionPuntos periodo = AsignacionProgramaTransformer.transformar(periodoAcumulacionPuntosDTO, AsignacionProgramaTransformer.CON_ASIGNACIONES);
		Long periodoAcumulacionPuntosId = periodoAcumulacionPuntosDAO.create(periodo); 
		periodoAcumulacionPuntosDAO.flush();
		periodoAcumulacionPuntosDTO.setPeriodoAcumulacionPuntosId(periodoAcumulacionPuntosId);
		return periodoAcumulacionPuntosDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#actualizarActoBuenaConducta(mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO)
	 */
	@Override
	public void actualizarActoBuenaConducta(ActoBuenaConductaDTO actoBuenaConductaDTO) {
		ActoBuenaConducta acto = AsignacionProgramaTransformer.transformar(actoBuenaConductaDTO, AsignacionProgramaTransformer.CON_ASIGNACIONES);
		actoBuenaConductaDAO.merge(acto);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#eliminarActoBuenaConducta(mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO)
	 */
	@Override
	public void eliminarActoBuenaConducta(
			ActoBuenaConductaDTO actoBuenaConductaDTO) {
		ActoBuenaConducta acto = AsignacionProgramaTransformer.transformar(actoBuenaConductaDTO, AsignacionProgramaTransformer.SIN_ASIGNACIONES);
		actoBuenaConductaDAO.delete(acto);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#consultarActosBuenaConductaSinAcumular(mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO)
	 */
	@Override
	public List<ActoBuenaConductaDTO> consultarActosBuenaConductaSinAcumular(
			SentenciaDTO sentenciaDTO) throws NSJPNegocioException {
		
		Sentencia sentencia = SentenciaTransformer.transformar(sentenciaDTO);
		List<ActoBuenaConducta> actosBuenaConducta = actoBuenaConductaDAO.consultarActosBuenaConductaSinAcumular(sentencia);
		List<ActoBuenaConductaDTO> actosBuenaConductaDTO = null;
		if (actosBuenaConducta != null && !actosBuenaConducta.isEmpty()){
			actosBuenaConductaDTO = new ArrayList<ActoBuenaConductaDTO>();
			for (ActoBuenaConducta abc : actosBuenaConducta){
				actosBuenaConductaDTO.add(AsignacionProgramaTransformer.transformar(abc,AsignacionProgramaTransformer.CON_ASIGNACIONES));
			}
		}
		return actosBuenaConductaDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaService#asignarCentroDetencionaSentencia(mx.gob.segob.nsjp.dto.programas.AsignacionCentroDetencionDTO)
	 */
	@Override
	public AsignacionCentroDetencionDTO asignarCentroDetencionaSentencia(AsignacionCentroDetencionDTO asignacionCentroDetencionDTO) throws NSJPNegocioException {
		try {				
			AsignacionCentroDetencion asignacionCentroDetencion = AsignacionProgramaTransformer.transformarSinSentencia(asignacionCentroDetencionDTO); 
			Long asignacionCentroDetencionId =  asignacionCentroDetencionDAO.create(asignacionCentroDetencion);
			asignacionCentroDetencionDTO.setAsignacionCentroDetencionId(asignacionCentroDetencionId);
		} catch (Exception e) {
			throw new NSJPNegocioException(CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO, e);
		}
		return asignacionCentroDetencionDTO;
	}	
	
}
