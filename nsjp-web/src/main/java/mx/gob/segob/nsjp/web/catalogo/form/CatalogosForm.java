package mx.gob.segob.nsjp.web.catalogo.form;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.web.base.form.GenericForm;

import org.apache.commons.collections.map.HashedMap;

public class CatalogosForm extends GenericForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idCatalogo;
	
	private CatalogoDTO catalogo = new CatalogoDTO();
	
	private List<CatalogoDTO> catalogos = new LinkedList<CatalogoDTO>();
	
	private List<String> estructuraCatalogo = new LinkedList<String>();

	/**
	 * Regresa el valor de la propiedad idCatalogo
	 * @return the idCatalogo
	 */
	public Long getIdCatalogo() {
		return idCatalogo;
	}

	/**
	 * Establece el valor de la propiedad idCatalogo
	 * @param idCatalogo valo idCatalogo a almacenar
	 */
	public void setIdCatalogo(Long idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	/**
	 * Establece el valor de la propiedad catalogo
	 * @param catalogo valo catalogo a almacenar
	 */
	public void setCatalogo(CatalogoDTO catalogo) {
		this.catalogo = catalogo;
	}
	

	/**
	 * Regresa el valor de la propiedad catalogo
	 * @return the catalogo
	 */
	public CatalogoDTO getCatalogo() {
		return catalogo;
	}
	

	/**
	 * Regresa el valor de la propiedad catalogos
	 * @return the catalogos
	 */
	public List<CatalogoDTO> getCatalogos() {
		return catalogos;
	}

	/**
	 * Establece el valor de la propiedad catalogos
	 * @param catalogos valo catalogos a almacenar
	 */
	public void setCatalogos(List<CatalogoDTO> catalogos) {
		this.catalogos = catalogos;
	}

	/**
	 * Regresa el valor de la propiedad estructuraCatalogo
	 * @return the estructuraCatalogo
	 */
	public List<String> getEstructuraCatalogo() {
		return estructuraCatalogo;
	}

	/**
	 * Establece el valor de la propiedad estructuraCatalogo
	 * @param estructuraCatalogo valo estructuraCatalogo a almacenar
	 */
	public void setEstructuraCatalogo(List<String> estructuraCatalogo) {
		this.estructuraCatalogo = estructuraCatalogo;
	}
}
