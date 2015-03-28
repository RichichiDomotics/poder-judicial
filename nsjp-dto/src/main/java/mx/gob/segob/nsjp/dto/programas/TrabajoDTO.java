package mx.gob.segob.nsjp.dto.programas;


import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

public class TrabajoDTO extends GenericDTO {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4605567645929198867L;
	private Long trabajoId;
	private CatTrabajoDTO catTrabajoDTO;
	private ProgramaDTO programaDTO;
	private Date dfechaIngreso;
	private Date dfechaTermino;
	private Long ipuntosObtenidos;
	private Boolean bcompletado;
	/**
	 * @return the trabajoId
	 */
	public Long getTrabajoId() {
		return trabajoId;
	}
	/**
	 * @param trabajoId the trabajoId to set
	 */
	public void setTrabajoId(Long trabajoId) {
		this.trabajoId = trabajoId;
	}
	/**
	 * @return the catTrabajoDTO
	 */
	public CatTrabajoDTO getCatTrabajoDTO() {
		return catTrabajoDTO;
	}
	/**
	 * @param catTrabajoDTO the catTrabajoDTO to set
	 */
	public void setCatTrabajoDTO(CatTrabajoDTO catTrabajoDTO) {
		this.catTrabajoDTO = catTrabajoDTO;
	}
	/**
	 * @return the programaDTO
	 */
	public ProgramaDTO getProgramaDTO() {
		return programaDTO;
	}
	/**
	 * @param programaDTO the programaDTO to set
	 */
	public void setProgramaDTO(ProgramaDTO programaDTO) {
		this.programaDTO = programaDTO;
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
	 * @return the ipuntosObtenidos
	 */
	public Long getIpuntosObtenidos() {
		return ipuntosObtenidos;
	}
	/**
	 * @param ipuntosObtenidos the ipuntosObtenidos to set
	 */
	public void setIpuntosObtenidos(Long ipuntosObtenidos) {
		this.ipuntosObtenidos = ipuntosObtenidos;
	}
	/**
	 * @return the bcompletado
	 */
	public Boolean getBcompletado() {
		return bcompletado;
	}
	/**
	 * @param bcompletado the bcompletado to set
	 */
	public void setBcompletado(Boolean bcompletado) {
		this.bcompletado = bcompletado;
	}

}