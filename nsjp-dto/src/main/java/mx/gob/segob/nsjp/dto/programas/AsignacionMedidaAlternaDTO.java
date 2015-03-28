package mx.gob.segob.nsjp.dto.programas;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;

public class AsignacionMedidaAlternaDTO extends GenericDTO {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1321547690070068032L;
	private Long asignacionMedidaAlternaId;
	private SentenciaDTO sentenciaDTO;
	private MedidaAlternaDTO medidaAlternaDTO;
	private Date dfechaInicio;
	private Date dfechaFin;
	private Boolean bcumplida;
	/**
	 * @return the asignacionMedidaAlternaId
	 */
	public Long getAsignacionMedidaAlternaId() {
		return asignacionMedidaAlternaId;
	}
	/**
	 * @param asignacionMedidaAlternaId the asignacionMedidaAlternaId to set
	 */
	public void setAsignacionMedidaAlternaId(Long asignacionMedidaAlternaId) {
		this.asignacionMedidaAlternaId = asignacionMedidaAlternaId;
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
	 * @return the medidaAlternaDTO
	 */
	public MedidaAlternaDTO getMedidaAlternaDTO() {
		return medidaAlternaDTO;
	}
	/**
	 * @param medidaAlternaDTO the medidaAlternaDTO to set
	 */
	public void setMedidaAlternaDTO(MedidaAlternaDTO medidaAlternaDTO) {
		this.medidaAlternaDTO = medidaAlternaDTO;
	}
	/**
	 * @return the dfechaInicio
	 */
	public Date getDfechaInicio() {
		return dfechaInicio;
	}
	/**
	 * @param dfechaInicio the dfechaInicio to set
	 */
	public void setDfechaInicio(Date dfechaInicio) {
		this.dfechaInicio = dfechaInicio;
	}
	/**
	 * @return the dfechaFin
	 */
	public Date getDfechaFin() {
		return dfechaFin;
	}
	/**
	 * @param dfechaFin the dfechaFin to set
	 */
	public void setDfechaFin(Date dfechaFin) {
		this.dfechaFin = dfechaFin;
	}
	/**
	 * @return the bcumplida
	 */
	public Boolean getBcumplida() {
		return bcumplida;
	}
	/**
	 * @param bcumplida the bcumplida to set
	 */
	public void setBcumplida(Boolean bcumplida) {
		this.bcumplida = bcumplida;
	}

}