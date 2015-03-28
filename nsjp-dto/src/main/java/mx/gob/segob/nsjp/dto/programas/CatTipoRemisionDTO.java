package mx.gob.segob.nsjp.dto.programas;

import mx.gob.segob.nsjp.dto.base.GenericDTO;


public class CatTipoRemisionDTO extends GenericDTO {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4093398751256664376L;
	private Long catTipoRemisionId;
	private String cnombre;
	private String cdescripcion;
	private Long iporcentajeCumplido;

	public Long getCatTipoRemisionId() {
		return this.catTipoRemisionId;
	}

	public void setCatTipoRemisionId(Long catTipoRemisionId) {
		this.catTipoRemisionId = catTipoRemisionId;
	}

	public String getCnombre() {
		return this.cnombre;
	}

	public void setCnombre(String cnombre) {
		this.cnombre = cnombre;
	}

	public String getCdescripcion() {
		return this.cdescripcion;
	}

	public void setCdescripcion(String cdescripcion) {
		this.cdescripcion = cdescripcion;
	}

	public Long getIporcentajeCumplido() {
		return this.iporcentajeCumplido;
	}

	public void setIporcentajeCumplido(Long iporcentajeCumplido) {
		this.iporcentajeCumplido = iporcentajeCumplido;
	}

}