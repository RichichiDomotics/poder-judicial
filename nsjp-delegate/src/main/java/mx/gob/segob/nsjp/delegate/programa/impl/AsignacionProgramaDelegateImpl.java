/**
 * 
 */
package mx.gob.segob.nsjp.delegate.programa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate;
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
import mx.gob.segob.nsjp.service.programa.AsignacionProgramaService;

/**
 * @author AntonioBV
 *
 */
@Service
@Transactional
public class AsignacionProgramaDelegateImpl implements
		AsignacionProgramaDelegate {

	@Autowired
	AsignacionProgramaService asignacionProgramaService;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#consultarAsignacionCentroDetencionPorId(mx.gob.segob.nsjp.dto.programas.AsignacionCentroDetencionDTO)
	 */
	@Override
	public AsignacionCentroDetencionDTO consultarAsignacionCentroDetencionPorId(
			AsignacionCentroDetencionDTO asignacionCentroDetencionDTO)
			throws NSJPNegocioException {
		return asignacionProgramaService.consultarAsignacionCentroDetencionPorId(asignacionCentroDetencionDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#consultarAsignacionMedidaAlternaPorId(mx.gob.segob.nsjp.dto.programas.AsignacionMedidaAlternaDTO)
	 */
	@Override
	public AsignacionMedidaAlternaDTO consultarAsignacionMedidaAlternaPorId(
			AsignacionMedidaAlternaDTO asignacionMedidaAlternaDTO)
			throws NSJPNegocioException {
		return asignacionProgramaService.consultarAsignacionMedidaAlternaPorId(asignacionMedidaAlternaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#consultarAsignacionProgramaPorId(mx.gob.segob.nsjp.dto.programas.AsignacionProgramaDTO)
	 */
	@Override
	public AsignacionProgramaDTO consultarAsignacionProgramaPorId(
			AsignacionProgramaDTO asignacionProgramaDTO)
			throws NSJPNegocioException {
		return asignacionProgramaService.consultarAsignacionProgramaPorId(asignacionProgramaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#consultarCursoPorId(mx.gob.segob.nsjp.dto.programas.CursoDTO)
	 */
	@Override
	public CursoDTO consultarCursoPorId(CursoDTO cursoDTO)
			throws NSJPNegocioException {
		return asignacionProgramaService.consultarCursoPorId(cursoDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#consultarProgramaPorId(mx.gob.segob.nsjp.dto.programas.ProgramaDTO)
	 */
	@Override
	public ProgramaDTO consultarProgramaPorId(ProgramaDTO programaDTO)
			throws NSJPNegocioException {
		return asignacionProgramaService.consultarProgramaPorId(programaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#consultarRemisionPorId(mx.gob.segob.nsjp.dto.programas.RemisionDTO)
	 */
	@Override
	public RemisionDTO consultarRemisionPorId(RemisionDTO remisionDTO)
			throws NSJPNegocioException {
		return asignacionProgramaService.consultarRemisionPorId(remisionDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#consultarSentenciaPorId(mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO)
	 */
	@Override
	public SentenciaDTO consultarSentenciaPorId(SentenciaDTO sentenciaDTO)
			throws NSJPNegocioException {
		return asignacionProgramaService.consultarSentenciaPorId(sentenciaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#consultarTrabajoPorId(mx.gob.segob.nsjp.dto.programas.TrabajoDTO)
	 */
	@Override
	public TrabajoDTO consultarTrabajoPorId(TrabajoDTO trabajoDTO)
			throws NSJPNegocioException {
		return asignacionProgramaService.consultarTrabajoPorId(trabajoDTO);
	}
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.programa.AsignacionProgramaDelegate#consultarSentencias(mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO)
	 */
	@Override
	public List<SentenciaDTO> consultarSentencias() throws NSJPNegocioException {
		return asignacionProgramaService.consultarSentencias();
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#registrarSentencia(mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO)
	 */
	@Override
	public void registrarSentencia(SentenciaDTO sentenciaDTO) {
		asignacionProgramaService.registrarSentencia(sentenciaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#crearPrograma(mx.gob.segob.nsjp.dto.programas.ProgramaDTO)
	 */
	@Override
	public ProgramaDTO crearPrograma(ProgramaDTO programaDTO) {
		return asignacionProgramaService.crearPrograma(programaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#crearCurso(mx.gob.segob.nsjp.dto.programas.CursoDTO)
	 */
	@Override
	public CursoDTO crearCurso(CursoDTO cursoDTO) {
		return asignacionProgramaService.crearCurso(cursoDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#crearCursos(java.util.List)
	 */
	@Override
	public void crearCursos(List<CursoDTO> cursosDTO) {
		asignacionProgramaService.crearCursos(cursosDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#crearTrabajo(mx.gob.segob.nsjp.dto.programas.TrabajoDTO)
	 */
	@Override
	public TrabajoDTO crearTrabajo(TrabajoDTO trabajoDTO) {
		return asignacionProgramaService.crearTrabajo(trabajoDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#crearTrabajos(java.util.List)
	 */
	@Override
	public void crearTrabajos(List<TrabajoDTO> trabajosDTO) {
		asignacionProgramaService.crearTrabajos(trabajosDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#consultarActoBuenaConductaPorId(mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO)
	 */
	@Override
	public ActoBuenaConductaDTO consultarActoBuenaConductaPorId(
			ActoBuenaConductaDTO actoBuenaConductaDTO) {
		return asignacionProgramaService.consultarActoBuenaConductaPorId(actoBuenaConductaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#consultarActosBuenaConductaPorSentencia(mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO)
	 */
	@Override
	public List<ActoBuenaConductaDTO> consultarActosBuenaConductaPorSentencia(
			SentenciaDTO sentenciaDTO) throws NSJPNegocioException {
		return asignacionProgramaService.consultarActosBuenaConductaPorSentencia(sentenciaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#crearActoBuenaConducta(mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO)
	 */
	@Override
	public ActoBuenaConductaDTO crearActoBuenaConducta(
			ActoBuenaConductaDTO actoBuenaConductaDTO) {
		return asignacionProgramaService.crearActoBuenaConducta(actoBuenaConductaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#consultarPeriodoAcumulacionPuntosPorId(mx.gob.segob.nsjp.dto.programas.PeriodoAcumulacionPuntosDTO)
	 */
	@Override
	public PeriodoAcumulacionPuntosDTO consultarPeriodoAcumulacionPuntosPorId(
			PeriodoAcumulacionPuntosDTO periodoAcumulacionPuntosDTO) {
		return asignacionProgramaService.consultarPeriodoAcumulacionPuntosPorId(periodoAcumulacionPuntosDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#consultarPeriodosAcumulacionPuntosPorSentencia(mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO)
	 */
	@Override
	public List<PeriodoAcumulacionPuntosDTO> consultarPeriodosAcumulacionPuntosPorSentencia(
			SentenciaDTO sentenciaDTO) throws NSJPNegocioException {
		return asignacionProgramaService.consultarPeriodosAcumulacionPuntosPorSentencia(sentenciaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#crearPeriodoAcumulacionPuntos(mx.gob.segob.nsjp.dto.programas.PeriodoAcumulacionPuntosDTO)
	 */
	@Override
	public PeriodoAcumulacionPuntosDTO crearPeriodoAcumulacionPuntos(
			PeriodoAcumulacionPuntosDTO periodoAcumulacionPuntosDTO) {
		return asignacionProgramaService.crearPeriodoAcumulacionPuntos(periodoAcumulacionPuntosDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#actualizarActoBuenaConducta(mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO)
	 */
	@Override
	public void actualizarActoBuenaConducta(ActoBuenaConductaDTO actoBuenaConductaDTO) {
		asignacionProgramaService.actualizarActoBuenaConducta(actoBuenaConductaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#eliminarActoBuenaConducta(mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO)
	 */
	@Override
	public void eliminarActoBuenaConducta(ActoBuenaConductaDTO actoBuenaConductaDTO) {
		asignacionProgramaService.eliminarActoBuenaConducta(actoBuenaConductaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.programa.AsignacionProgramaDelegate#consultarActosBuenaConductaSinAcumular(mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO)
	 */
	@Override
	public List<ActoBuenaConductaDTO> consultarActosBuenaConductaSinAcumular(SentenciaDTO sentenciaDTO) throws NSJPNegocioException {
		return asignacionProgramaService.consultarActosBuenaConductaSinAcumular(sentenciaDTO);
	}

	@Override
	public AsignacionCentroDetencionDTO asignarCentroDetencionaSentencia(AsignacionCentroDetencionDTO asignacionCentroDetencionDTO)
			throws NSJPNegocioException {
		return asignacionProgramaService.asignarCentroDetencionaSentencia(asignacionCentroDetencionDTO);
	}
	
	

}
