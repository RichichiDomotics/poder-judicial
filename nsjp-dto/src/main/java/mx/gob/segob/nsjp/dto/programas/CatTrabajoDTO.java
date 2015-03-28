package mx.gob.segob.nsjp.dto.programas;

import mx.gob.segob.nsjp.dto.base.GenericDTO;



/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CatTrabajoDTO extends GenericDTO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7083564365756766895L;
	private Long catTrabajoId;
	private CatTipoTrabajoExternoDTO catTipoTrabajoExterno;
	private String cnombre;
	private String cdescripcion;
	private Boolean besExterno;
	private String cnumeroConvenio;
	private Long ipuntos;
	private Boolean bActivo;
	/**
	 * Método de acceso al campo catTrabajoId.
	 * @return El valor del campo catTrabajoId
	 */
	public Long getCatTrabajoId() {
		return catTrabajoId;
	}
	/**
	 * Asigna el valor al campo catTrabajoId.
	 * @param catTrabajoId el valor catTrabajoId a asignar
	 */
	public void setCatTrabajoId(Long catTrabajoId) {
		this.catTrabajoId = catTrabajoId;
	}
	/**
	 * Método de acceso al campo catTipoTrabajoExterno.
	 * @return El valor del campo catTipoTrabajoExterno
	 */
	public CatTipoTrabajoExternoDTO getCatTipoTrabajoExterno() {
		return catTipoTrabajoExterno;
	}
	/**
	 * Asigna el valor al campo catTipoTrabajoExterno.
	 * @param catTipoTrabajoExterno el valor catTipoTrabajoExterno a asignar
	 */
	public void setCatTipoTrabajoExterno(
			CatTipoTrabajoExternoDTO catTipoTrabajoExterno) {
		this.catTipoTrabajoExterno = catTipoTrabajoExterno;
	}
	/**
	 * Método de acceso al campo cdescripcion.
	 * @return El valor del campo cdescripcion
	 */
	public String getCdescripcion() {
		return cdescripcion;
	}
	/**
	 * Asigna el valor al campo cdescripcion.
	 * @param cdescripcion el valor cdescripcion a asignar
	 */
	public void setCdescripcion(String cdescripcion) {
		this.cdescripcion = cdescripcion;
	}
	/**
	 * Método de acceso al campo besExterno.
	 * @return El valor del campo besExterno
	 */
	public Boolean getBesExterno() {
		return besExterno;
	}
	/**
	 * Asigna el valor al campo besExterno.
	 * @param besExterno el valor besExterno a asignar
	 */
	public void setBesExterno(Boolean besExterno) {
		this.besExterno = besExterno;
	}
	/**
	 * Método de acceso al campo cnumeroConvenio.
	 * @return El valor del campo cnumeroConvenio
	 */
	public String getCnumeroConvenio() {
		return cnumeroConvenio;
	}
	/**
	 * Asigna el valor al campo cnumeroConvenio.
	 * @param cnumeroConvenio el valor cnumeroConvenio a asignar
	 */
	public void setCnumeroConvenio(String cnumeroConvenio) {
		this.cnumeroConvenio = cnumeroConvenio;
	}
	/**
	 * Método de acceso al campo ipuntos.
	 * @return El valor del campo ipuntos
	 */
	public Long getIpuntos() {
		return ipuntos;
	}
	/**
	 * Asigna el valor al campo ipuntos.
	 * @param ipuntos el valor ipuntos a asignar
	 */
	public void setIpuntos(Long ipuntos) {
		this.ipuntos = ipuntos;
	}
	/**
	 * Método de acceso al campo cnombre.
	 * @return El valor del campo cnombre
	 */
	public String getCnombre() {
		return cnombre;
	}
	/**
	 * Asigna el valor al campo cnombre.
	 * @param cnombre el valor cnombre a asignar
	 */
	public void setCnombre(String cnombre) {
		this.cnombre = cnombre;
	}
	/**
	 * @return the bActivo
	 */
	public Boolean getbActivo() {
		return bActivo;
	}
	/**
	 * @param bActivo the bActivo to set
	 */
	public void setbActivo(Boolean bActivo) {
		this.bActivo = bActivo;
	}
	
}