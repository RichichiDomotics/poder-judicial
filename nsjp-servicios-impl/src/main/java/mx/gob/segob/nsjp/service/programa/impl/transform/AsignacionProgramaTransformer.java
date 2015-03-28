/**
 * Nombre del Programa : ProgramaTransformer.java
 * Autor                            : AntonioBV
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 27/01/2012
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
package mx.gob.segob.nsjp.service.programa.impl.transform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO;
import mx.gob.segob.nsjp.dto.programas.AsignacionCentroDetencionDTO;
import mx.gob.segob.nsjp.dto.programas.AsignacionMedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.programas.AsignacionProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoRemisionDTO;
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
import mx.gob.segob.nsjp.model.CatTipoRemision;
import mx.gob.segob.nsjp.model.Curso;
import mx.gob.segob.nsjp.model.PeriodoAcumulacionPuntos;
import mx.gob.segob.nsjp.model.Programa;
import mx.gob.segob.nsjp.model.Remision;
import mx.gob.segob.nsjp.model.Sentencia;
import mx.gob.segob.nsjp.model.Trabajo;
import mx.gob.segob.nsjp.service.detencion.transform.CentroDetencionTransformer;
import mx.gob.segob.nsjp.service.medida.impl.transform.MedidaAlternaTransformer;
import mx.gob.segob.nsjp.service.sentencia.impl.transform.SentenciaTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author AntonioBV
 * 
 */
public class AsignacionProgramaTransformer {

	public final static Boolean CON_ASIGNACIONES = Boolean.TRUE;
	public final static Boolean SIN_ASIGNACIONES = Boolean.FALSE;
	
	public static CatTipoRemisionDTO transformar(CatTipoRemision catTipoRemision) {
		CatTipoRemisionDTO catTipoRemisionDTO = new CatTipoRemisionDTO();
		catTipoRemisionDTO.setCatTipoRemisionId(catTipoRemision.getCatTipoRemisionId());
		catTipoRemisionDTO.setCnombre(catTipoRemision.getCnombre());
		catTipoRemisionDTO.setIporcentajeCumplido(catTipoRemision.getIporcentajeCumplido());
		catTipoRemisionDTO.setCdescripcion(catTipoRemision.getCdescripcion());
		return catTipoRemisionDTO;
	}

	public static CatTipoRemision transformar(
			CatTipoRemisionDTO catTipoRemisionDTO) {
		CatTipoRemision catTipoRemision = new CatTipoRemision();
		catTipoRemision.setCatTipoRemisionId(catTipoRemisionDTO
				.getCatTipoRemisionId());
		catTipoRemision.setCdescripcion(catTipoRemisionDTO.getCdescripcion());
		catTipoRemision.setCnombre(catTipoRemisionDTO.getCnombre());
		catTipoRemision.setIporcentajeCumplido(catTipoRemisionDTO
				.getIporcentajeCumplido());
		return catTipoRemision;
	}

	public static RemisionDTO transformar(Remision remision) {
		RemisionDTO remisionDTO = new RemisionDTO();
		remisionDTO.setRemisionId(remision.getRemisionId());
		remisionDTO.setIdiasAcreditados(remision.getIdiasAcreditados());
		remisionDTO.setImontoDanioReparado(remision.getImontoDanioReparado());

		remisionDTO.setCatTipoRemisionDTO(transformar(remision
				.getCatTipoRemision()));
		if (remision.getSentencia() != null) {
			remisionDTO.setSentencia(SentenciaTransformer.transformar(remision
					.getSentencia()));
		}
		return remisionDTO;
	}

	public static Remision transformar(RemisionDTO remisionDTO) {
		Remision remision = new Remision();
		remision.setRemisionId(remisionDTO.getRemisionId());
		remision.setIdiasAcreditados(remisionDTO.getIdiasAcreditados());
		remision.setImontoDanioReparado(remisionDTO.getImontoDanioReparado());

		remision.setCatTipoRemision(transformar(remisionDTO
				.getCatTipoRemisionDTO()));
		if (remisionDTO.getSentencia() != null) {
			remision.setSentencia(SentenciaTransformer.transformar(remisionDTO
					.getSentencia()));
		}
		return remision;
	}

	public static RemisionDTO transformarSinSentencia(Remision remision) {
		Sentencia sentencia = remision.getSentencia();
		SentenciaDTO sentenciaDTO = null;
		if (sentencia != null){
			sentenciaDTO = new SentenciaDTO();
			sentenciaDTO.setSentenciaId(sentencia.getSentenciaId());
			remision.setSentencia(null);			
		}
		
		RemisionDTO remisionDTO = transformar(remision);
		remisionDTO.setSentencia(sentenciaDTO);
		return remisionDTO;
	}

	public static Remision transformarSinSentencia(RemisionDTO remisionDTO) {
		SentenciaDTO sentenciaDTO = remisionDTO.getSentencia();
		Sentencia sentencia = null;
		if (sentenciaDTO != null){
			sentencia = new Sentencia();
			sentencia.setSentenciaId(sentenciaDTO.getSentenciaId());
			remisionDTO.setSentencia(null);			
		}
		
		Remision remision = transformar(remisionDTO);
		remision.setSentencia(sentencia);
		return remision;
	}
	
	public static ActoBuenaConductaDTO transformarSinSentencia(ActoBuenaConducta actoBuenaConducta) {
		Sentencia sentencia = actoBuenaConducta.getSentencia();
		SentenciaDTO sentenciaDTO = null;
		if (sentencia != null){
			sentenciaDTO = new SentenciaDTO();
			sentenciaDTO.setSentenciaId(sentencia.getSentenciaId());
			actoBuenaConducta.setSentencia(null);			
		}
		
		ActoBuenaConductaDTO actoBuenaConductaDTO = transformar(actoBuenaConducta, CON_ASIGNACIONES);
		actoBuenaConductaDTO.setSentenciaDTO(sentenciaDTO);
		return actoBuenaConductaDTO;
	}

	public static ActoBuenaConducta transformarSinSentencia(ActoBuenaConductaDTO actoBuenaConductaDTO) {
		SentenciaDTO sentenciaDTO = actoBuenaConductaDTO.getSentenciaDTO();
		Sentencia sentencia = null;
		if (sentenciaDTO != null){
			sentencia = new Sentencia();
			sentencia.setSentenciaId(sentenciaDTO.getSentenciaId());
			actoBuenaConductaDTO.setSentenciaDTO(null);
		}
		ActoBuenaConducta actoBuenaConducta = transformar(actoBuenaConductaDTO, CON_ASIGNACIONES);
		actoBuenaConducta.setSentencia(sentencia);
		return actoBuenaConducta;
	}
	
	
	public static TrabajoDTO transformar(Trabajo trabajo, boolean conPrograma) {
		TrabajoDTO trabajoDTO = new TrabajoDTO();
		trabajoDTO.setTrabajoId(trabajo.getTrabajoId());
		trabajoDTO.setBcompletado(trabajo.getBcompletado());
		trabajoDTO.setCatTrabajoDTO(ProgramaTransformer.transformar(trabajo
				.getCatTrabajo()));
		trabajoDTO.setDfechaIngreso(trabajo.getDfechaIngreso());
		trabajoDTO.setDfechaTermino(trabajo.getDfechaTermino());
		trabajoDTO.setIpuntosObtenidos(trabajo.getIpuntosObtenidos());
		if (conPrograma) {
			trabajoDTO.setProgramaDTO(transformar(trabajo.getPrograma(), CON_ASIGNACIONES));
		}
		return trabajoDTO;
	}

	public static Trabajo transformar(TrabajoDTO trabajoDTO, boolean conPrograma) {
		Trabajo trabajo = new Trabajo();
		trabajo.setTrabajoId(trabajoDTO.getTrabajoId());
		trabajo.setBcompletado(trabajoDTO.getBcompletado());
		trabajo.setCatTrabajo(ProgramaTransformer.transformar(trabajoDTO
				.getCatTrabajoDTO()));
		trabajo.setDfechaIngreso(trabajoDTO.getDfechaIngreso());
		trabajo.setDfechaTermino(trabajoDTO.getDfechaTermino());
		trabajo.setIpuntosObtenidos(trabajoDTO.getIpuntosObtenidos());
		if(conPrograma) {
			trabajo.setPrograma(transformar(trabajoDTO.getProgramaDTO(), CON_ASIGNACIONES));
		}else{
			trabajo.setPrograma(transformar(trabajoDTO.getProgramaDTO(), SIN_ASIGNACIONES));
		}
		return trabajo;
	}

	public static CursoDTO transformar(Curso curso, boolean conPrograma) {
		CursoDTO cursoDTO = new CursoDTO();
		cursoDTO.setCursoId(curso.getCursoId());
		cursoDTO.setBcompletado(curso.getBcompletado());
		cursoDTO.setCatCursoDTO(ProgramaTransformer.transformar(curso
				.getCatCurso()));
		cursoDTO.setDfechaIngreso(curso.getDfechaIngreso());
		cursoDTO.setDfechaTermino(curso.getDfechaTermino());
		cursoDTO.setIpuntosObtenidos(curso.getIpuntosObtenidos());
		if(conPrograma) {
			cursoDTO.setProgramaDTO(transformar(curso.getPrograma(), CON_ASIGNACIONES));
		}
		return cursoDTO;
	}

	public static Curso transformar(CursoDTO cursoDTO, boolean conPrograma) {
		Curso curso = new Curso();
		curso.setCursoId(cursoDTO.getCursoId());
		curso.setBcompletado(cursoDTO.getBcompletado());
		curso.setCatCurso(ProgramaTransformer.transformar(cursoDTO
				.getCatCursoDTO()));
		curso.setDfechaIngreso(cursoDTO.getDfechaIngreso());
		curso.setDfechaTermino(cursoDTO.getDfechaTermino());
		curso.setIpuntosObtenidos(cursoDTO.getIpuntosObtenidos());
		if(conPrograma) {
			curso.setPrograma(transformar(cursoDTO.getProgramaDTO(), CON_ASIGNACIONES));
		}else{
			curso.setPrograma(transformar(cursoDTO.getProgramaDTO(), SIN_ASIGNACIONES));
		}
		return curso;
	}

	public static ProgramaDTO transformar(Programa programa, boolean conAsignaciones) {
		ProgramaDTO programaDTO = new ProgramaDTO();
		programaDTO.setCatProgramaDTO(ProgramaTransformer.transformar(programa
				.getCatPrograma()));
		programaDTO.setDfechaIngreso(programa.getDfechaIngreso());
		programaDTO.setDfechaTermino(programa.getDfechaTermino());
		programaDTO.setProgramaId(programa.getProgramaId());

		List<AsignacionProgramaDTO> lstAsignacionProgramaDTO = new ArrayList<AsignacionProgramaDTO>();
		if(conAsignaciones) {
			if (programa.getAsignacionProgramas() != null) {
				for (AsignacionPrograma asignacionPrograma : programa
						.getAsignacionProgramas()) {
					AsignacionProgramaDTO asignacionProgramaDTO = AsignacionProgramaTransformer
							.transformar(asignacionPrograma);
					lstAsignacionProgramaDTO.add(asignacionProgramaDTO);
				}
			}
		}

		List<CursoDTO> lstCursoDTO = new ArrayList<CursoDTO>();	
		if (programa.getCursos() != null) {
			for (Curso curso : programa.getCursos()) {
				CursoDTO cursoDTO = AsignacionProgramaTransformer
						.transformar(curso, SIN_ASIGNACIONES);
				lstCursoDTO.add(cursoDTO);
			}
		}
		List<TrabajoDTO> lstTrabajoDTO = new ArrayList<TrabajoDTO>();
		if (programa.getTrabajos() != null) {
			for (Trabajo trabajo : programa.getTrabajos()) {
				TrabajoDTO trabajoDTO = AsignacionProgramaTransformer
						.transformar(trabajo, SIN_ASIGNACIONES);
				lstTrabajoDTO.add(trabajoDTO);
			}
		}
		programaDTO.setAsignacionProgramas(lstAsignacionProgramaDTO);
		programaDTO.setCursos(lstCursoDTO);
		programaDTO.setTrabajos(lstTrabajoDTO);
		return programaDTO;
	}

	public static Programa transformar(ProgramaDTO programaDTO, boolean conAsignaciones) {
		Programa programa = new Programa();
		programa.setCatPrograma(ProgramaTransformer.transformar(programaDTO
				.getCatProgramaDTO()));
		programa.setDfechaIngreso(programaDTO.getDfechaIngreso());
		programa.setDfechaTermino(programaDTO.getDfechaTermino());
		programa.setProgramaId(programaDTO.getProgramaId());

		List<AsignacionPrograma> lstAsignacionPrograma = new ArrayList<AsignacionPrograma>();
		if(conAsignaciones) {
			if (programaDTO.getAsignacionProgramas() != null) {
				for (AsignacionProgramaDTO asignacionProgramaDTO : programaDTO
						.getAsignacionProgramas()) {
					AsignacionPrograma asignacionPrograma = transformar(asignacionProgramaDTO);
					lstAsignacionPrograma.add(asignacionPrograma);
				}
			}
		}
		Set<AsignacionPrograma> asignacionProgramas = new HashSet<AsignacionPrograma>(
				lstAsignacionPrograma);

		List<Curso> lstCurso = new ArrayList<Curso>();
		if(conAsignaciones) {		
			if (programaDTO.getCursos() != null) {
				for (CursoDTO cursoDTO : programaDTO.getCursos()) {
					Curso curso = transformar(cursoDTO, SIN_ASIGNACIONES);
					lstCurso.add(curso);
				}
			}
		}
		Set<Curso> cursos = new HashSet<Curso>(lstCurso);

		List<Trabajo> lstTrabajo = new ArrayList<Trabajo>();
		if(conAsignaciones) {
			if (programaDTO.getTrabajos() != null) {
				for (TrabajoDTO trabajoDTO : programaDTO.getTrabajos()) {
					Trabajo trabajo = transformar(trabajoDTO, SIN_ASIGNACIONES);
					lstTrabajo.add(trabajo);
				}
			}
		}
		Set<Trabajo> trabajos = new HashSet<Trabajo>(lstTrabajo);

		programa.setAsignacionProgramas(asignacionProgramas);
		programa.setCursos(cursos);
		programa.setTrabajos(trabajos);
		return programa;
	}

	public static AsignacionProgramaDTO transformar(
			AsignacionPrograma asignacionPrograma) {
		AsignacionProgramaDTO asignacionProgramaDTO = new AsignacionProgramaDTO();
		asignacionProgramaDTO.setAsignacionProgramaId(asignacionPrograma
				.getAsignacionProgramaId());
		asignacionProgramaDTO.setBaceptado(asignacionPrograma.getBaceptado());
		if(asignacionPrograma.getPrograma() != null){
			asignacionProgramaDTO.setProgramaDTO(transformar(asignacionPrograma
				.getPrograma(), SIN_ASIGNACIONES));
		}
		if (asignacionPrograma.getSentencia() != null) {
			asignacionProgramaDTO.setSentenciaDTO(SentenciaTransformer
					.transformar(asignacionPrograma.getSentencia()));
		}
		return asignacionProgramaDTO;
	}

	public static AsignacionPrograma transformar(
			AsignacionProgramaDTO asignacionProgramaDTO) {
		AsignacionPrograma asignacionPrograma = new AsignacionPrograma();
		asignacionPrograma.setAsignacionProgramaId(asignacionProgramaDTO
				.getAsignacionProgramaId());
		asignacionPrograma.setBaceptado(asignacionProgramaDTO.getBaceptado());
		if(asignacionProgramaDTO.getProgramaDTO() != null){
			asignacionPrograma.setPrograma(transformar(asignacionProgramaDTO
					.getProgramaDTO(), SIN_ASIGNACIONES));
		}
		if (asignacionProgramaDTO.getSentenciaDTO() != null) {
			asignacionPrograma.setSentencia(SentenciaTransformer
					.transformar(asignacionProgramaDTO.getSentenciaDTO()));
		}
		return asignacionPrograma;
	}

	public static AsignacionProgramaDTO transformarSinSentencia(
			AsignacionPrograma asignacionPrograma) {
		
		Sentencia sentencia = asignacionPrograma.getSentencia();
		SentenciaDTO sentenciaDTO = null;
		if (sentencia != null ){
			sentenciaDTO = new SentenciaDTO();
			sentenciaDTO.setSentenciaId(sentencia.getSentenciaId());
			asignacionPrograma.setSentencia(null);			
		}
		AsignacionProgramaDTO asignacionProgramaDTO = transformar(asignacionPrograma);
		asignacionProgramaDTO.setSentenciaDTO(sentenciaDTO);
		return asignacionProgramaDTO;
	}

	public static AsignacionPrograma transformarSinSentencia(
			AsignacionProgramaDTO asignacionProgramaDTO) {
		SentenciaDTO sentenciaDTO = asignacionProgramaDTO.getSentenciaDTO();
		
		Sentencia sentencia = null;
		if (sentenciaDTO != null ){
			sentencia = new Sentencia();
			sentencia.setSentenciaId(sentenciaDTO.getSentenciaId());
			asignacionProgramaDTO.setSentenciaDTO(null);			
		}
		
		AsignacionPrograma asignacionPrograma = transformar(asignacionProgramaDTO);
		asignacionPrograma.setSentencia(sentencia);
		return asignacionPrograma;
	}

	public static AsignacionProgramaDTO transformarSinPrograma(
			AsignacionPrograma asignacionPrograma) {
		Programa programa = asignacionPrograma.getPrograma();
		
		ProgramaDTO programaDTO = null;
		if (programa != null ){
			programaDTO = new ProgramaDTO();
			programaDTO.setProgramaId(programa.getProgramaId());
			asignacionPrograma.setPrograma(null);			
		}
		
		AsignacionProgramaDTO asignacionProgramaDTO = transformar(asignacionPrograma);
		asignacionProgramaDTO.setProgramaDTO(programaDTO);
		return asignacionProgramaDTO;
	}

	public static AsignacionPrograma transformarSinPrograma(
			AsignacionProgramaDTO asignacionProgramaDTO) {
		ProgramaDTO programaDTO = asignacionProgramaDTO.getProgramaDTO();
		
		Programa programa = null;
		if (programaDTO != null){
			programa = new Programa();
			programa.setProgramaId(programaDTO.getProgramaId());
			asignacionProgramaDTO.setProgramaDTO(programaDTO);			
		}
		
		AsignacionPrograma asignacionPrograma = transformar(asignacionProgramaDTO);
		asignacionPrograma.setPrograma(programa);
		return asignacionPrograma;
	}

	
	
	public static AsignacionMedidaAlternaDTO transformar(
			AsignacionMedidaAlterna asignacionMedidaAlterna) {
		AsignacionMedidaAlternaDTO asignacionMedidaAlternaDTO = new AsignacionMedidaAlternaDTO();
		asignacionMedidaAlternaDTO
				.setAsignacionMedidaAlternaId(asignacionMedidaAlterna
						.getAsignacionMedidaAlternaId());
		asignacionMedidaAlternaDTO.setBcumplida(asignacionMedidaAlterna
				.getBcumplida());
		asignacionMedidaAlternaDTO.setDfechaInicio(asignacionMedidaAlterna
				.getDfechaInicio());
		asignacionMedidaAlternaDTO.setDfechaFin(asignacionMedidaAlterna
				.getDfechaFin());
		asignacionMedidaAlternaDTO.setMedidaAlternaDTO(MedidaAlternaTransformer
				.transformarMedidaAlterna(asignacionMedidaAlterna
						.getMedidaAlterna()));
		if (asignacionMedidaAlterna.getSentencia() != null) {
			asignacionMedidaAlternaDTO.setSentenciaDTO(SentenciaTransformer
					.transformar(asignacionMedidaAlterna.getSentencia()));
		}
		return asignacionMedidaAlternaDTO;
	}

	public static AsignacionMedidaAlterna transformar(
			AsignacionMedidaAlternaDTO asignacionMedidaAlternaDTO) {
		AsignacionMedidaAlterna asignacionMedidaAlterna = new AsignacionMedidaAlterna();
		asignacionMedidaAlterna
				.setAsignacionMedidaAlternaId(asignacionMedidaAlternaDTO
						.getAsignacionMedidaAlternaId());
		asignacionMedidaAlterna.setBcumplida(asignacionMedidaAlternaDTO
				.getBcumplida());
		asignacionMedidaAlterna.setDfechaInicio(asignacionMedidaAlternaDTO
				.getDfechaInicio());
		asignacionMedidaAlterna.setDfechaFin(asignacionMedidaAlternaDTO
				.getDfechaFin());
		asignacionMedidaAlterna.setMedidaAlterna(MedidaAlternaTransformer
				.transformarMedidaAlterna(asignacionMedidaAlternaDTO
						.getMedidaAlternaDTO()));
		if (asignacionMedidaAlternaDTO.getSentenciaDTO() != null) {
			asignacionMedidaAlterna.setSentencia(SentenciaTransformer
					.transformar(asignacionMedidaAlternaDTO.getSentenciaDTO()));
		}
		return asignacionMedidaAlterna;
	}

	public static AsignacionMedidaAlternaDTO transformarSinSentencia(
			AsignacionMedidaAlterna asignacionMedidaAlterna) {
		
		Sentencia sentencia = asignacionMedidaAlterna.getSentencia();
		
		SentenciaDTO sentenciaDTO = null;
		if (sentencia != null ){
			sentenciaDTO = new SentenciaDTO();
			sentenciaDTO.setSentenciaId(sentencia.getSentenciaId());
			asignacionMedidaAlterna.setSentencia(null);			
		}
		
		AsignacionMedidaAlternaDTO asignacionMedidaAlternaDTO = transformar(asignacionMedidaAlterna);
		asignacionMedidaAlternaDTO.setSentenciaDTO(sentenciaDTO);
		return asignacionMedidaAlternaDTO;
	}

	public static AsignacionMedidaAlterna transformarSinSentencia(
			AsignacionMedidaAlternaDTO asignacionMedidaAlternaDTO) {
		SentenciaDTO sentenciaDTO = asignacionMedidaAlternaDTO.getSentenciaDTO();
		
		Sentencia sentencia = null;
		if (sentenciaDTO != null){
			sentencia = new Sentencia();
			sentencia.setSentenciaId(sentenciaDTO.getSentenciaId());
			asignacionMedidaAlternaDTO.setSentenciaDTO(null);			
		}
		
		AsignacionMedidaAlterna asignacionMedidaAlterna = transformar(asignacionMedidaAlternaDTO);
		asignacionMedidaAlterna.setSentencia(sentencia);
		return asignacionMedidaAlterna;
	}
	
	public static AsignacionCentroDetencionDTO transformar(
			 AsignacionCentroDetencion asignacionCentroDetencion) {
		AsignacionCentroDetencionDTO asignacionCentroDetencionDTO = new AsignacionCentroDetencionDTO();
		asignacionCentroDetencionDTO
				.setAsignacionCentroDetencionId(asignacionCentroDetencion
						.getAsignacionCentroDetencionId());
		asignacionCentroDetencionDTO.setBarraigado(asignacionCentroDetencion
				.getBarraigado());
		asignacionCentroDetencionDTO
				.setCentroDetencionDTO(CentroDetencionTransformer
						.transformar(asignacionCentroDetencion
								.getCentroDetencion()));
		asignacionCentroDetencionDTO.setCmotivo(asignacionCentroDetencion
				.getCmotivo());
		asignacionCentroDetencionDTO.setDfechaIngreso(asignacionCentroDetencion
				.getDfechaIngreso());
		asignacionCentroDetencionDTO.setDfechaSalida(asignacionCentroDetencion
				.getDfechaSalida());
		if (asignacionCentroDetencion.getSentencia() != null) {
			asignacionCentroDetencionDTO.setSentenciaDTO(SentenciaTransformer
					.transformar(asignacionCentroDetencion.getSentencia()));
		}
		return asignacionCentroDetencionDTO;
	}

	public static AsignacionCentroDetencion transformar(
			AsignacionCentroDetencionDTO asignacionCentroDetencionDTO) {
		AsignacionCentroDetencion asignacionCentroDetencion = new AsignacionCentroDetencion();
		asignacionCentroDetencion
				.setAsignacionCentroDetencionId(asignacionCentroDetencionDTO
						.getAsignacionCentroDetencionId());
		asignacionCentroDetencion.setBarraigado(asignacionCentroDetencionDTO
				.getBarraigado());
		asignacionCentroDetencion.setCentroDetencion(CentroDetencionTransformer
				.transformar(asignacionCentroDetencionDTO
						.getCentroDetencionDTO()));
		asignacionCentroDetencion.setCmotivo(asignacionCentroDetencionDTO
				.getCmotivo());
		asignacionCentroDetencion.setDfechaIngreso(asignacionCentroDetencionDTO
				.getDfechaIngreso());
		asignacionCentroDetencion.setDfechaSalida(asignacionCentroDetencionDTO
				.getDfechaSalida());
		if (asignacionCentroDetencionDTO.getSentenciaDTO() != null) {
			asignacionCentroDetencion
					.setSentencia(SentenciaTransformer
							.transformar(asignacionCentroDetencionDTO
									.getSentenciaDTO()));
		}
		return asignacionCentroDetencion;
	}

	public static AsignacionCentroDetencionDTO transformarSinSentencia(
			AsignacionCentroDetencion asignacionCentroDetencion) {

		Sentencia sentencia = asignacionCentroDetencion.getSentencia();
		
		SentenciaDTO sentenciaDTO = null;
		if (sentencia != null){
			sentenciaDTO = new SentenciaDTO();
			sentenciaDTO.setSentenciaId(sentencia.getSentenciaId());
			asignacionCentroDetencion.setSentencia(null);			
		}

		AsignacionCentroDetencionDTO asignacionCentroDetencionDTO = transformar(asignacionCentroDetencion); 
		asignacionCentroDetencionDTO.setSentenciaDTO(sentenciaDTO);
		return asignacionCentroDetencionDTO;
	}

	public static AsignacionCentroDetencion transformarSinSentencia(
			AsignacionCentroDetencionDTO asignacionCentroDetencionDTO) {
		
		SentenciaDTO sentenciaDTO = asignacionCentroDetencionDTO.getSentenciaDTO();
		Sentencia sentencia =  null; 
		if (sentenciaDTO != null){
			sentencia = new Sentencia();
			sentencia.setSentenciaId(sentenciaDTO.getSentenciaId());
			asignacionCentroDetencionDTO.setSentenciaDTO(null);
		}

		AsignacionCentroDetencion asignacionCentroDetencion = transformar(asignacionCentroDetencionDTO);
		asignacionCentroDetencion.setSentencia(sentencia);
		return asignacionCentroDetencion;
	}
	
	public static PeriodoAcumulacionPuntosDTO transformar(PeriodoAcumulacionPuntos periodoAcumulacionPuntos, Boolean conAsignaciones) {
		PeriodoAcumulacionPuntosDTO periodoAcumulacionPuntosDTO = new PeriodoAcumulacionPuntosDTO();
		periodoAcumulacionPuntosDTO.setPeriodoAcumulacionPuntosId(periodoAcumulacionPuntos.getPeriodoAcumulacionPuntosId());
		periodoAcumulacionPuntosDTO.setDfechaTermino(periodoAcumulacionPuntos.getDfechaTermino());
		periodoAcumulacionPuntosDTO.setDfechaInicio(periodoAcumulacionPuntos.getDfechaInicio());
		periodoAcumulacionPuntosDTO.setcNombrePeriodo(periodoAcumulacionPuntos.getcNombrePeriodo());
		if (conAsignaciones){
			Set<ActoBuenaConducta> actosBuenaConducta = periodoAcumulacionPuntos.getActosBuenaConducta(); 
			if (actosBuenaConducta != null && !actosBuenaConducta.isEmpty()){
				List<ActoBuenaConductaDTO> actosBuenaConductaDTO = new ArrayList<ActoBuenaConductaDTO>();
				for (ActoBuenaConducta actoBuenaConducta : actosBuenaConducta){
					actosBuenaConductaDTO.add(transformar(actoBuenaConducta,SIN_ASIGNACIONES));
				}
				periodoAcumulacionPuntosDTO.setActosBuenaConducta(actosBuenaConductaDTO);
			}
		}
		return periodoAcumulacionPuntosDTO;
	}

	public static PeriodoAcumulacionPuntos transformar(PeriodoAcumulacionPuntosDTO periodoAcumulacionPuntosDTO, Boolean conAsignaciones) {
		PeriodoAcumulacionPuntos periodoAcumulacionPuntos = new PeriodoAcumulacionPuntos();
		periodoAcumulacionPuntos.setPeriodoAcumulacionPuntosId(periodoAcumulacionPuntosDTO.getPeriodoAcumulacionPuntosId());
		periodoAcumulacionPuntos.setDfechaTermino(periodoAcumulacionPuntosDTO.getDfechaTermino());
		periodoAcumulacionPuntos.setDfechaInicio(periodoAcumulacionPuntosDTO.getDfechaInicio());
		periodoAcumulacionPuntos.setcNombrePeriodo(periodoAcumulacionPuntosDTO.getcNombrePeriodo());
		if (conAsignaciones){
			List<ActoBuenaConductaDTO> actosBuenaConductaDTO = periodoAcumulacionPuntosDTO.getActosBuenaConducta(); 
			if (actosBuenaConductaDTO != null && !actosBuenaConductaDTO.isEmpty()){
				Set<ActoBuenaConducta> actosBuenaConducta = new HashSet<ActoBuenaConducta>();
				for (ActoBuenaConductaDTO actoBuenaConductaDTO : actosBuenaConductaDTO){
					actosBuenaConducta.add(transformar(actoBuenaConductaDTO,SIN_ASIGNACIONES));
				}
				periodoAcumulacionPuntos.setActosBuenaConducta(actosBuenaConducta);
			}
		}
		return periodoAcumulacionPuntos;
	}
	
	public static ActoBuenaConductaDTO transformar(ActoBuenaConducta actoBuenaConducta, Boolean conAsignaciones){
		ActoBuenaConductaDTO actoBuenaConductaDTO = new ActoBuenaConductaDTO();
		actoBuenaConductaDTO.setActoBuenaConductaId(actoBuenaConducta.getActoBuenaConductaId());
		actoBuenaConductaDTO.setCdescripcion(actoBuenaConducta.getCdescripcion());
		actoBuenaConductaDTO.setCnombre(actoBuenaConducta.getCnombre());
		actoBuenaConductaDTO.setIpuntosOtorgados(actoBuenaConducta.getIpuntosOtorgados());
		actoBuenaConductaDTO.setSentenciaDTO(SentenciaTransformer.transformar(actoBuenaConducta.getSentencia()));
		if (conAsignaciones && actoBuenaConducta.getPeriodoAcumulacionPuntos() != null ){
			actoBuenaConductaDTO.setPeriodoAcumulacionPuntos(transformar(actoBuenaConducta.getPeriodoAcumulacionPuntos(),SIN_ASIGNACIONES));
		}
		return actoBuenaConductaDTO;
	}
	
	public static ActoBuenaConducta transformar(ActoBuenaConductaDTO actoBuenaConductaDTO, Boolean conAsignaciones){
		ActoBuenaConducta actoBuenaConducta = new ActoBuenaConducta();
		actoBuenaConducta.setActoBuenaConductaId(actoBuenaConductaDTO.getActoBuenaConductaId());
		actoBuenaConducta.setCdescripcion(actoBuenaConductaDTO.getCdescripcion());
		actoBuenaConducta.setCnombre(actoBuenaConductaDTO.getCnombre());
		actoBuenaConducta.setIpuntosOtorgados(actoBuenaConductaDTO.getIpuntosOtorgados());
		if (actoBuenaConductaDTO.getSentenciaDTO() != null 
				&& actoBuenaConductaDTO.getSentenciaDTO().getSentenciaId() != null && actoBuenaConductaDTO.getSentenciaDTO().getSentenciaId() > 0L){
			Sentencia sentencia = new Sentencia();
			sentencia.setSentenciaId(actoBuenaConductaDTO.getSentenciaDTO().getSentenciaId());
			actoBuenaConducta.setSentencia(sentencia);
		}
		//actoBuenaConducta.setSentencia(SentenciaTransformer.transformar(actoBuenaConductaDTO.getSentenciaDTO()));
		if (conAsignaciones && actoBuenaConductaDTO.getPeriodoAcumulacionPuntos() != null){
			actoBuenaConducta.setPeriodoAcumulacionPuntos(transformar(actoBuenaConductaDTO.getPeriodoAcumulacionPuntos(),SIN_ASIGNACIONES));
		}
		return actoBuenaConducta;
	}
}
