package mx.gob.segob.nsjp.dto;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

public class LeyCodigoDTO extends GenericDTO {

	private Long leyCodigoId;
	private ValorDTO tipoNorma;
	private String nombre;
	private String descripcion;
	private String url;
	
	public Long getLeyCodigoId() {
		return leyCodigoId;
	}
	
	public void setLeyCodigoId(Long leyCodigoId) {
		this.leyCodigoId = leyCodigoId;
	}
	
	public ValorDTO getTipoNorma() {
		return tipoNorma;
	}
	
	public void setTipoNorma(ValorDTO tipoNorma) {
		this.tipoNorma = tipoNorma;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
