package mx.gob.segob.nsjp.dto.programas;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;


public class CursoDTO extends GenericDTO {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7439990682775313643L;
	private Long cursoId;
	private CatCursoDTO catCursoDTO;
	private ProgramaDTO programaDTO;
	private Date dfechaIngreso;
	private Date dfechaTermino;
	private Long ipuntosObtenidos;
	private Boolean bcompletado;

	public Long getCursoId() {
		return this.cursoId;
	}

	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
	}

	public CatCursoDTO getCatCursoDTO() {
		return this.catCursoDTO;
	}

	public void setCatCursoDTO(CatCursoDTO catCursoDTO) {
		this.catCursoDTO = catCursoDTO;
	}

	public ProgramaDTO getProgramaDTO() {
		return this.programaDTO;
	}

	public void setProgramaDTO(ProgramaDTO programaDTO) {
		this.programaDTO = programaDTO;
	}

	public Date getDfechaIngreso() {
		return this.dfechaIngreso;
	}

	public void setDfechaIngreso(Date dfechaIngreso) {
		this.dfechaIngreso = dfechaIngreso;
	}

	public Date getDfechaTermino() {
		return this.dfechaTermino;
	}

	public void setDfechaTermino(Date dfechaTermino) {
		this.dfechaTermino = dfechaTermino;
	}

	public Long getIpuntosObtenidos() {
		return this.ipuntosObtenidos;
	}

	public void setIpuntosObtenidos(Long ipuntosObtenidos) {
		this.ipuntosObtenidos = ipuntosObtenidos;
	}

	public Boolean getBcompletado() {
		return this.bcompletado;
	}

	public void setBcompletado(Boolean bcompletado) {
		this.bcompletado = bcompletado;
	}

}