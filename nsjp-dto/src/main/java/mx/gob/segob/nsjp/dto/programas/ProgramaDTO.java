package mx.gob.segob.nsjp.dto.programas;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

public class ProgramaDTO extends GenericDTO {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7545854098661830525L;
	private Long programaId;
	private CatProgramaDTO catProgramaDTO;
	private Date dfechaIngreso;
	private Date dfechaTermino;
	private List<CursoDTO> cursos;
	private List<TrabajoDTO> trabajos;
	private List<AsignacionProgramaDTO> asignacionProgramas ;
	/**
	 * @return the programaId
	 */
	public Long getProgramaId() {
		return programaId;
	}
	/**
	 * @param programaId the programaId to set
	 */
	public void setProgramaId(Long programaId) {
		this.programaId = programaId;
	}
	/**
	 * @return the catProgramaDTO
	 */
	public CatProgramaDTO getCatProgramaDTO() {
		return catProgramaDTO;
	}
	/**
	 * @param catProgramaDTO the catProgramaDTO to set
	 */
	public void setCatProgramaDTO(CatProgramaDTO catProgramaDTO) {
		this.catProgramaDTO = catProgramaDTO;
	}
	/**
	 * @return the dfechaIngreso
	 */
	public Date getDfechaIngreso() {
		return dfechaIngreso;
	}
	/**
	 * @param dfechaIngreso the dfechaIngreso to set
	 */
	public void setDfechaIngreso(Date dfechaIngreso) {
		this.dfechaIngreso = dfechaIngreso;
	}
	/**
	 * @return the dfechaTermino
	 */
	public Date getDfechaTermino() {
		return dfechaTermino;
	}
	/**
	 * @param dfechaTermino the dfechaTermino to set
	 */
	public void setDfechaTermino(Date dfechaTermino) {
		this.dfechaTermino = dfechaTermino;
	}
	/**
	 * @return the cursos
	 */
	public List<CursoDTO> getCursos() {
		return cursos;
	}
	/**
	 * @param cursos the cursos to set
	 */
	public void setCursos(List<CursoDTO> cursos) {
		this.cursos = cursos;
	}
	/**
	 * @return the trabajos
	 */
	public List<TrabajoDTO> getTrabajos() {
		return trabajos;
	}
	/**
	 * @param trabajos the trabajos to set
	 */
	public void setTrabajos(List<TrabajoDTO> trabajos) {
		this.trabajos = trabajos;
	}
	/**
	 * @return the asignacionProgramas
	 */
	public List<AsignacionProgramaDTO> getAsignacionProgramas() {
		return asignacionProgramas;
	}
	/**
	 * @param asignacionProgramas the asignacionProgramas to set
	 */
	public void setAsignacionProgramas(
			List<AsignacionProgramaDTO> asignacionProgramas) {
		this.asignacionProgramas = asignacionProgramas;
	}

	/**
	 * M&eacute;todo utilitario que calcula el total de puntos 
	 * obtenidos del programa que provienen de cursos.
	 * @return totalPuntos el n&uacute;mero de puntos obtenidos de cursos
	 */
	public Long getPuntosObtenidosCursos(){
		long totalPuntos = 0;
		if (this.cursos != null && !this.cursos.isEmpty()){
			for (CursoDTO curso : this.cursos){
				totalPuntos += curso.getIpuntosObtenidos();
			}
		}
		return totalPuntos;
	}
	
	/**
	 * M&eacute;todo utilitario que calcula el total de puntos 
	 * obtenidos del programa que provienen de trabajos.
	 * @return totalPuntos el n&uacute;mero de puntos obtenidos de trabajos.
	 */
	public Long getPuntosObtenidosTrabajos(){
		long totalPuntos = 0;
		if (this.trabajos != null && !this.trabajos.isEmpty()){
			for (TrabajoDTO trabajo : this.trabajos){
				totalPuntos += trabajo.getIpuntosObtenidos();
			}
		}
		return totalPuntos;
	}
	
	/**
	 * M&eacute;todo utilitario que calcula el total de puntos 
	 * obtenidos del programa que provienen de trabajos y cursos.
	 * @return totalPuntos el n&uacute;mero de puntos obtenidos de trabajos y cursos.
	 */
	public Long getPuntosObtenidosPrograma(){
		return getPuntosObtenidosCursos() + getPuntosObtenidosTrabajos();
	}
}