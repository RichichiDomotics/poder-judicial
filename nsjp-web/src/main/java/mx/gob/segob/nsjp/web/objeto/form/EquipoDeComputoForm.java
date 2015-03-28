/**
* Nombre del Programa		: EquipoDeComputoForm.java
* Autor                 	: AlejandroGA
* Compania                  : Ultrasist
* Proyecto                  : NSJP                    Fecha: 28 Apr 2011
* Marca de cambio        	: N/A
* Descripcion General    	: Forma para el objeto Equipo de computo
* Programa Dependiente  	:N/A
* Programa Subsecuente 		:N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion         :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                     :N/A
* Compania               	:N/A
* Proyecto                 	:N/A                                 Fecha: N/A
* Modificacion           	:N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.objeto.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Forma para el objeto Equipo de computo.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class EquipoDeComputoForm extends GenericForm{

	/**
	 * Serialize de la clase
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idEquipoComputo;
	private Boolean anular;
	public Long getIdEquipoComputo() {
		return idEquipoComputo;
	}

	public void setIdEquipoComputo(Long idEquipoComputo) {
		this.idEquipoComputo = idEquipoComputo;
	}
	
	//Campos para la insercion desde cadena de custodia
	private String fechaLevCadCus; 
	private String origenEvdCadCus;
	private String cadenaCustodia;
	//Fin Campos para la insercion desde cadena de custodia
	

	/**
	 * @return the fechaLevCadCus
	 */
	public String getFechaLevCadCus() {
		return fechaLevCadCus;
	}

	/**
	 * @param fechaLevCadCus the fechaLevCadCus to set
	 */
	public void setFechaLevCadCus(String fechaLevCadCus) {
		this.fechaLevCadCus = fechaLevCadCus;
	}

	/**
	 * @return the origenEvdCadCus
	 */
	public String getOrigenEvdCadCus() {
		return origenEvdCadCus;
	}

	/**
	 * @param origenEvdCadCus the origenEvdCadCus to set
	 */
	public void setOrigenEvdCadCus(String origenEvdCadCus) {
		this.origenEvdCadCus = origenEvdCadCus;
	}

	/**
	 * @return the cadenaCustodia
	 */
	public String getCadenaCustodia() {
		return cadenaCustodia;
	}

	/**
	 * @param cadenaCustodia the cadenaCustodia to set
	 */
	public void setCadenaCustodia(String cadenaCustodia) {
		this.cadenaCustodia = cadenaCustodia;
	}

	/**
	 * No. de serie del equipo de computo
	 */  
	private String gsNoSerieEquipoComputo;
	
	/**
	 * identificador del color del equipo de computo
	 */  
    private Long glColorEquipoComputoId;
    /**
	 * identificador de la marca del equipo de computo
	 */  
    private Long glMarcaEquipoComputoId;
    
    /**
	 * identificador del tipo de equipo de computo
	 */
    private Long glTipoEquipoComputoId;
    
    /**
	 * Modelo del equipo de computo
	 */  
    private String gsModeloEquipoComputo;
    
    /**
	 *condicion del equipo de computo
	 */
    
	private Long glCondicionFisicaEquipoComputoId;
	
	/**
	 * Breve descripcion del equipo de computo
	 */
	private String gsDescripcionEquipoComputo;

	
	/**
	 * Método de acceso al campo gsNoSerieEquipoComputo.
	 * @return El valor del campo gsNoSerieEquipoComputo
	 */
	public String getGsNoSerieEquipoComputo() {
		return gsNoSerieEquipoComputo;
	}

	/**
	 * Asigna el valor al campo gsNoSerieEquipoComputo.
	 * @param gsNoSerieEquipoComputo el valor gsNoSerieEquipoComputo a asignar
	 */
	public void setGsNoSerieEquipoComputo(String gsNoSerieEquipoComputo) {
		this.gsNoSerieEquipoComputo = gsNoSerieEquipoComputo;
	}

	/**
	 * Método de acceso al campo glColorEquipoComputoId.
	 * @return El valor del campo glColorEquipoComputoId
	 */
	public Long getGlColorEquipoComputoId() {
		return glColorEquipoComputoId;
	}

	/**
	 * Asigna el valor al campo glColorEquipoComputoId.
	 * @param glColorEquipoComputoId el valor glColorEquipoComputoId a asignar
	 */
	public void setGlColorEquipoComputoId(Long glColorEquipoComputoId) {
		this.glColorEquipoComputoId = glColorEquipoComputoId;
	}

	/**
	 * Método de acceso al campo glMarcaEquipoComputoId.
	 * @return El valor del campo glMarcaEquipoComputoId
	 */
	public Long getGlMarcaEquipoComputoId() {
		return glMarcaEquipoComputoId;
	}

	/**
	 * Asigna el valor al campo glMarcaEquipoComputoId.
	 * @param glMarcaEquipoComputoId el valor glMarcaEquipoComputoId a asignar
	 */
	public void setGlMarcaEquipoComputoId(Long glMarcaEquipoComputoId) {
		this.glMarcaEquipoComputoId = glMarcaEquipoComputoId;
	}

	/**
	 * Método de acceso al campo glTipoEquipoComputoId.
	 * @return El valor del campo glTipoEquipoComputoId
	 */
	public Long getGlTipoEquipoComputoId() {
		return glTipoEquipoComputoId;
	}

	/**
	 * Asigna el valor al campo glTipoEquipoComputoId.
	 * @param glTipoEquipoComputoId el valor glTipoEquipoComputoId a asignar
	 */
	public void setGlTipoEquipoComputoId(Long glTipoEquipoComputoId) {
		this.glTipoEquipoComputoId = glTipoEquipoComputoId;
	}

	/**
	 * Método de acceso al campo gsModeloEquipoComputo.
	 * @return El valor del campo gsModeloEquipoComputo
	 */
	public String getGsModeloEquipoComputo() {
		return gsModeloEquipoComputo;
	}

	/**
	 * Asigna el valor al campo gsModeloEquipoComputo.
	 * @param gsModeloEquipoComputo el valor gsModeloEquipoComputo a asignar
	 */
	public void setGsModeloEquipoComputo(String gsModeloEquipoComputo) {
		this.gsModeloEquipoComputo = gsModeloEquipoComputo;
	}

	/**
	 * Método de acceso al campo glCondicionFisicaEquipoComputoId.
	 * @return El valor del campo glCondicionFisicaEquipoComputoId
	 */
	public Long getGlCondicionFisicaEquipoComputoId() {
		return glCondicionFisicaEquipoComputoId;
	}

	/**
	 * Asigna el valor al campo glCondicionFisicaEquipoComputoId.
	 * @param glCondicionFisicaEquipoComputoId el valor glCondicionFisicaEquipoComputoId a asignar
	 */
	public void setGlCondicionFisicaEquipoComputoId(
			Long glCondicionFisicaEquipoComputoId) {
		this.glCondicionFisicaEquipoComputoId = glCondicionFisicaEquipoComputoId;
	}

	/**
	 * Método de acceso al campo gsDescripcionEquipoComputo.
	 * @return El valor del campo gsDescripcionEquipoComputo
	 */
	public String getGsDescripcionEquipoComputo() {
		return gsDescripcionEquipoComputo;
	}

	/**
	 * Asigna el valor al campo gsDescripcionEquipoComputo.
	 * @param gsDescripcionEquipoComputo el valor gsDescripcionEquipoComputo a asignar
	 */
	public void setGsDescripcionEquipoComputo(String gsDescripcionEquipoComputo) {
		this.gsDescripcionEquipoComputo = gsDescripcionEquipoComputo;
	}

	/**
	 * Método de acceso al campo serialversionuid.
	 * @return El valor del campo serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the anular
	 */
	public Boolean getAnular() {
		return anular;
	}

	/**
	 * @param anular the anular to set
	 */
	public void setAnular(Boolean anular) {
		this.anular = anular;
	}
	
}
