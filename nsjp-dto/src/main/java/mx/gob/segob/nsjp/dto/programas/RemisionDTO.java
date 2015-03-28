package mx.gob.segob.nsjp.dto.programas;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;

public class RemisionDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -535042547836630927L;
	// Fields

	private Long remisionId;
	private CatTipoRemisionDTO catTipoRemisionDTO;
	private SentenciaDTO sentencia;
	private Long imontoDanioReparado;
	private Long idiasAcreditados;

	public Long getRemisionId() {
		return this.remisionId;
	}

	public void setRemisionId(Long remisionId) {
		this.remisionId = remisionId;
	}

	public CatTipoRemisionDTO getCatTipoRemisionDTO() {
		return this.catTipoRemisionDTO;
	}

	public void setCatTipoRemisionDTO(CatTipoRemisionDTO catTipoRemisionDTO) {
		this.catTipoRemisionDTO = catTipoRemisionDTO;
	}

	public SentenciaDTO getSentencia() {
		return this.sentencia;
	}

	public void setSentencia(SentenciaDTO sentencia) {
		this.sentencia = sentencia;
	}

	public Long getImontoDanioReparado() {
		return this.imontoDanioReparado;
	}

	public void setImontoDanioReparado(Long imontoDanioReparado) {
		this.imontoDanioReparado = imontoDanioReparado;
	}

	public Long getIdiasAcreditados() {
		return this.idiasAcreditados;
	}

	public void setIdiasAcreditados(Long idiasAcreditados) {
		this.idiasAcreditados = idiasAcreditados;
	}

}