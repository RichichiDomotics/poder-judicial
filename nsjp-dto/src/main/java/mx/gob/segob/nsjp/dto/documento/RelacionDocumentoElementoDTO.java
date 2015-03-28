package mx.gob.segob.nsjp.dto.documento;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.relacion.CatRelacionDTO;


public class RelacionDocumentoElementoDTO extends GenericDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long relacionDocumentoElementoId;
	private DocumentoDTO documento;
	private ElementoDTO elemento;	
	private CatRelacionDTO catRelacion;
	
	
	
	public RelacionDocumentoElementoDTO() {
		super();
	}
	
	public RelacionDocumentoElementoDTO(Long relacionDocumentoElementoId,
			DocumentoDTO documento, ElementoDTO elemento,
			CatRelacionDTO catRelacion) {
		super();
		this.relacionDocumentoElementoId = relacionDocumentoElementoId;
		this.documento = documento;
		this.elemento = elemento;
		this.catRelacion = catRelacion;
	}
	public Long getRelacionDocumentoElementoId() {
		return relacionDocumentoElementoId;
	}
	public void setRelacionDocumentoElementoId(Long relacionDocumentoElementoId) {
		this.relacionDocumentoElementoId = relacionDocumentoElementoId;
	}
	public DocumentoDTO getDocumento() {
		return documento;
	}
	public void setDocumento(DocumentoDTO documento) {
		this.documento = documento;
	}
	public ElementoDTO getElemento() {
		return elemento;
	}
	public void setElemento(ElementoDTO elemento) {
		this.elemento = elemento;
	}
	public CatRelacionDTO getCatRelacion() {
		return catRelacion;
	}
	public void setCatRelacion(CatRelacionDTO catRelacion) {
		this.catRelacion = catRelacion;
	}
	
	
}
