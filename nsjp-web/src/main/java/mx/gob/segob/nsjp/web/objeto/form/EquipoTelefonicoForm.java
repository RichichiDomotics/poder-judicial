/**
* Nombre del Programa : EquipoTelefonicoForm.java
* Autor                            : AlejandroGA
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 4 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.objeto.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class EquipoTelefonicoForm extends GenericForm{

	private Long idEquipoTelefonico;
	private Boolean anular;
	public Long getIdEquipoTelefonico() {
		return idEquipoTelefonico;
	}

	public void setIdEquipoTelefonico(Long idEquipoTelefonico) {
		this.idEquipoTelefonico = idEquipoTelefonico;
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
	 * Serialize de la clase
	 */
	private static final long serialVersionUID = 1L;

    /**
	 * identificador de la marca del equipo telefonico
	 */  
    private Long glMarcaEquipoTelefId;
    
    /**
	 * identificador del tipo de equipo de equipo telefonico
	 */
    private Long glTipoEquipoTelefId;
    
    /**
	 * Modelo del equipo telefonico
	 */  
    private String gsModeloEquipoTelef;
    
    /**
	 * Modelo del equipo telefonico
	 */  
    private Long  gsCantidadEquipoTelef;
    
    
    /**
	 *condicion del equipo telefonico
	 */
    
	private Long glCondicionFisicaEquipoTelefId;
	
	/**
	 * Breve descripcion del equipo telefonico
	 */
	private String gsDescripcionEquipoTelef;

	
	/**
	 * Método de acceso al campo glMarcaEquipoTelefId.
	 * @return El valor del campo glMarcaEquipoTelefId
	 */
	public Long getGlMarcaEquipoTelefId() {
		return glMarcaEquipoTelefId;
	}

	/**
	 * Asigna el valor al campo glMarcaEquipoTelefId.
	 * @param glMarcaEquipoTelefId el valor glMarcaEquipoTelefId a asignar
	 */
	public void setGlMarcaEquipoTelefId(Long glMarcaEquipoTelefId) {
		this.glMarcaEquipoTelefId = glMarcaEquipoTelefId;
	}

	/**
	 * Método de acceso al campo glTipoEquipoTelefId.
	 * @return El valor del campo glTipoEquipoTelefId
	 */
	public Long getGlTipoEquipoTelefId() {
		return glTipoEquipoTelefId;
	}

	/**
	 * Asigna el valor al campo glTipoEquipoTelefId.
	 * @param glTipoEquipoTelefId el valor glTipoEquipoTelefId a asignar
	 */
	public void setGlTipoEquipoTelefId(Long glTipoEquipoTelefId) {
		this.glTipoEquipoTelefId = glTipoEquipoTelefId;
	}

	/**
	 * Método de acceso al campo gsModeloEquipoTelef.
	 * @return El valor del campo gsModeloEquipoTelef
	 */
	public String getGsModeloEquipoTelef() {
		return gsModeloEquipoTelef;
	}

	/**
	 * Asigna el valor al campo gsModeloEquipoTelef.
	 * @param gsModeloEquipoTelef el valor gsModeloEquipoTelef a asignar
	 */
	public void setGsModeloEquipoTelef(String gsModeloEquipoTelef) {
		this.gsModeloEquipoTelef = gsModeloEquipoTelef;
	}

	/**
	 * Método de acceso al campo glCantidadEquipoTelef.
	 * @return El valor del campo glCantidadEquipoTelef
	 */
	public Long getGsCantidadEquipoTelef() {
		return gsCantidadEquipoTelef;
	}

	/**
	 * Asigna el valor al campo glCantidadEquipoTelef.
	 * @param glCantidadEquipoTelef el valor glCantidadEquipoTelef a asignar
	 */
	public void setGsCantidadEquipoTelef(Long gsCantidadEquipoTelef) {
		this.gsCantidadEquipoTelef = gsCantidadEquipoTelef;
	}

	/**
	 * Método de acceso al campo glCondicionFisicaEquipoTelefId.
	 * @return El valor del campo glCondicionFisicaEquipoTelefId
	 */
	public Long getGlCondicionFisicaEquipoTelefId() {
		return glCondicionFisicaEquipoTelefId;
	}

	/**
	 * Asigna el valor al campo glCondicionFisicaEquipoTelefId.
	 * @param glCondicionFisicaEquipoTelefId el valor glCondicionFisicaEquipoTelefId a asignar
	 */
	public void setGlCondicionFisicaEquipoTelefId(
			Long glCondicionFisicaEquipoTelefId) {
		this.glCondicionFisicaEquipoTelefId = glCondicionFisicaEquipoTelefId;
	}

	/**
	 * Método de acceso al campo gsDescripcionEquipoTelef.
	 * @return El valor del campo gsDescripcionEquipoTelef
	 */
	public String getGsDescripcionEquipoTelef() {
		return gsDescripcionEquipoTelef;
	}

	/**
	 * Asigna el valor al campo gsDescripcionEquipoTelef.
	 * @param gsDescripcionEquipoTelef el valor gsDescripcionEquipoTelef a asignar
	 */
	public void setGsDescripcionEquipoTelef(String gsDescripcionEquipoTelef) {
		this.gsDescripcionEquipoTelef = gsDescripcionEquipoTelef;
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
