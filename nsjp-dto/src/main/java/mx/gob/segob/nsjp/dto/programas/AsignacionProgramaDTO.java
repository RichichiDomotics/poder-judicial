package mx.gob.segob.nsjp.dto.programas;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;

public class AsignacionProgramaDTO extends GenericDTO {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1918229360225887756L;	
	private Long asignacionProgramaId;
	private SentenciaDTO sentenciaDTO;
	private ProgramaDTO programaDTO;
	private Boolean baceptado;
	/**
	 * @return the asignacionProgramaId
	 */
	public Long getAsignacionProgramaId() {
		return asignacionProgramaId;
	}
	/**
	 * @param asignacionProgramaId the asignacionProgramaId to set
	 */
	public void setAsignacionProgramaId(Long asignacionProgramaId) {
		this.asignacionProgramaId = asignacionProgramaId;
	}
	/**
	 * @return the sentenciaDTO
	 */
	public SentenciaDTO getSentenciaDTO() {
		return sentenciaDTO;
	}
	/**
	 * @param sentenciaDTO the sentenciaDTO to set
	 */
	public void setSentenciaDTO(SentenciaDTO sentenciaDTO) {
		this.sentenciaDTO = sentenciaDTO;
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
	 * @return the baceptado
	 */
	public Boolean getBaceptado() {
		return baceptado;
	}
	/**
	 * @param baceptado the baceptado to set
	 */
	public void setBaceptado(Boolean baceptado) {
		this.baceptado = baceptado;
	}

}