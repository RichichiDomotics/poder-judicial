/**
* Nombre del Programa 		: EmbarcacionForm.java
* Autor                     : AlejandroGA
* Compania                  : Ultrasist
* Proyecto                  : NSJP                    Fecha: 29 Apr 2011
* Marca de cambio        	: N/A
* Descripcion General    	: Forma para el objeto Embarcacion
* Programa Dependiente  	: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion        : N/A
* Dias de ejecucion         : N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                     : N/A
* Compania               	: N/A
* Proyecto                 	: N/A                                 Fecha: N/A
* Modificacion           	: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.objeto.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Forma para el objeto Embarcacion.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class EmbarcacionForm extends GenericForm{

	
	private Long idEmbarcacion;
	private Boolean anular;
	
	//Campos para la insercion desde cadena de custodia
	private String fechaLevCadCus; 
	private String origenEvdCadCus;
	private String cadenaCustodia;
	
	/**
	 * @return the idEmbarcacion
	 */
	public Long getIdEmbarcacion() {
		return idEmbarcacion;
	}

	/**
	 * @param idEmbarcacion the idEmbarcacion to set
	 */
	public void setIdEmbarcacion(Long idEmbarcacion) {
		this.idEmbarcacion = idEmbarcacion;
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
	 * identificador del tipo de embarcacion
	 */
	private Long glTipoEmbarcacionId;
	
	/**
	 * identificador de la marca de la embarcacion 
	 */  
	private Long glMarcaEmbarcacionId;


	/**
	 *identificador de la sub marca de la embarcacion
	 */
	private Long glSubMarcaEmbarcacionId;
	 
	/**
	 *identificador del color de la embarcacion
	 */
	private Long glColorEmbarcacionId;
	 
	/**
	 *identificador del pais de la embarcacion
	 */
	private Long glPaisEmbarcacionId;
	 
	/**
	 * nombre de la embarcacion
	 */
	private String gcNombreEmbarcacion;
	 
	/**
	 * numero de motor de la embarcacion
	 */
	private String gcNoMotorEmbarcacion;

	/**
	 * numero de serie de la embarcacion
	 */
	private String gcNoSerieEmbarcacion;
   
	/**
	 * matricula de la embarcacion
	 */
	private String gcMatriculaEmbarcacion;
   
	/**
	 *condicion de la embarcacion
	 */
	private Long glCondicionFisica;
	
	/**
	 * Breve descripcion de la embarcacion
	 */
	private String gcDescripcion;

  	
	/**
	 * Método de acceso al campo glTipoEmbarcacionId.
	 * @return El valor del campo glTipoEmbarcacionId
	 */
	public Long getGlTipoEmbarcacionId() {
		return glTipoEmbarcacionId;
	}

	/**
	 * Asigna el valor al campo glTipoEmbarcacionId.
	 * @param glTipoEmbarcacionId el valor glTipoEmbarcacionId a asignar
	 */
	public void setGlTipoEmbarcacionId(Long glTipoEmbarcacionId) {
		this.glTipoEmbarcacionId = glTipoEmbarcacionId;
	}

	/**
	 * Método de acceso al campo glMarcaEmbarcacionId.
	 * @return El valor del campo glMarcaEmbarcacionId
	 */
	public Long getGlMarcaEmbarcacionId() {
		return glMarcaEmbarcacionId;
	}

	/**
	 * Asigna el valor al campo glMarcaEmbarcacionId.
	 * @param glMarcaEmbarcacionId el valor glMarcaEmbarcacionId a asignar
	 */
	public void setGlMarcaEmbarcacionId(Long glMarcaEmbarcacionId) {
		this.glMarcaEmbarcacionId = glMarcaEmbarcacionId;
	}

	/**
	 * Método de acceso al campo serialversionuid.
	 * @return El valor del campo serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Método de acceso al campo glSubMarcaEmbarcacionId.
	 * @return El valor del campo glSubMarcaEmbarcacionId
	 */
	public Long getGlSubMarcaEmbarcacionId() {
		return glSubMarcaEmbarcacionId;
	}

	/**
	 * Asigna el valor al campo glSubMarcaEmbarcacionId.
	 * @param glSubMarcaEmbarcacionId el valor glSubMarcaEmbarcacionId a asignar
	 */
	public void setGlSubMarcaEmbarcacionId(Long glSubMarcaEmbarcacionId) {
		this.glSubMarcaEmbarcacionId = glSubMarcaEmbarcacionId;
	}

	/**
	 * Método de acceso al campo glColorEmbarcacionId.
	 * @return El valor del campo glColorEmbarcacionId
	 */
	public Long getGlColorEmbarcacionId() {
		return glColorEmbarcacionId;
	}

	/**
	 * Asigna el valor al campo glColorEmbarcacionId.
	 * @param glColorEmbarcacionId el valor glColorEmbarcacionId a asignar
	 */
	public void setGlColorEmbarcacionId(Long glColorEmbarcacionId) {
		this.glColorEmbarcacionId = glColorEmbarcacionId;
	}

	/**
	 * Método de acceso al campo glPaisEmbarcacionId.
	 * @return El valor del campo glPaisEmbarcacionId
	 */
	public Long getGlPaisEmbarcacionId() {
		return glPaisEmbarcacionId;
	}

	/**
	 * Asigna el valor al campo glPaisEmbarcacionId.
	 * @param glPaisEmbarcacionId el valor glPaisEmbarcacionId a asignar
	 */
	public void setGlPaisEmbarcacionId(Long glPaisEmbarcacionId) {
		this.glPaisEmbarcacionId = glPaisEmbarcacionId;
	}

	/**
	 * Método de acceso al campo gcNombreEmbarcacion.
	 * @return El valor del campo gcNombreEmbarcacion
	 */
	public String getGcNombreEmbarcacion() {
		return gcNombreEmbarcacion;
	}

	/**
	 * Asigna el valor al campo gcNombreEmbarcacion.
	 * @param gcNombreEmbarcacion el valor gcNombreEmbarcacion a asignar
	 */
	public void setGcNombreEmbarcacion(String gcNombreEmbarcacion) {
		this.gcNombreEmbarcacion = gcNombreEmbarcacion;
	}

	/**
	 * Método de acceso al campo gcNoMotorEmbarcacion.
	 * @return El valor del campo gcNoMotorEmbarcacion
	 */
	public String getGcNoMotorEmbarcacion() {
		return gcNoMotorEmbarcacion;
	}

	/**
	 * Asigna el valor al campo gcNoMotorEmbarcacion.
	 * @param gcNoMotorEmbarcacion el valor gcNoMotorEmbarcacion a asignar
	 */
	public void setGcNoMotorEmbarcacion(String gcNoMotorEmbarcacion) {
		this.gcNoMotorEmbarcacion = gcNoMotorEmbarcacion;
	}

	/**
	 * Método de acceso al campo gcNoSerieEmbarcacion.
	 * @return El valor del campo gcNoSerieEmbarcacion
	 */
	public String getGcNoSerieEmbarcacion() {
		return gcNoSerieEmbarcacion;
	}

	/**
	 * Asigna el valor al campo gcNoSerieEmbarcacion.
	 * @param gcNoSerieEmbarcacion el valor gcNoSerieEmbarcacion a asignar
	 */
	public void setGcNoSerieEmbarcacion(String gcNoSerieEmbarcacion) {
		this.gcNoSerieEmbarcacion = gcNoSerieEmbarcacion;
	}

	/**
	 * Método de acceso al campo gcMatriculaEmbarcacion.
	 * @return El valor del campo gcMatriculaEmbarcacion
	 */
	public String getGcMatriculaEmbarcacion() {
		return gcMatriculaEmbarcacion;
	}

	/**
	 * Asigna el valor al campo gcMatriculaEmbarcacion.
	 * @param gcMatriculaEmbarcacion el valor gcMatriculaEmbarcacion a asignar
	 */
	public void setGcMatriculaEmbarcacion(String gcMatriculaEmbarcacion) {
		this.gcMatriculaEmbarcacion = gcMatriculaEmbarcacion;
	}

	/**
	 * Método de acceso al campo glCondicionFisica.
	 * @return El valor del campo glCondicionFisica
	 */
	public Long getGlCondicionFisica() {
		return glCondicionFisica;
	}

	/**
	 * Asigna el valor al campo glCondicionFisica.
	 * @param glCondicionFisica el valor glCondicionFisica a asignar
	 */
	public void setGlCondicionFisica(Long glCondicionFisica) {
		this.glCondicionFisica = glCondicionFisica;
	}

	/**
	 * Método de acceso al campo gcDescripcion.
	 * @return El valor del campo gcDescripcion
	 */
	public String getGcDescripcion() {
		return gcDescripcion;
	}

	/**
	 * Asigna el valor al campo gcDescripcion.
	 * @param gcDescripcion el valor gcDescripcion a asignar
	 */
	public void setGcDescripcion(String gcDescripcion) {
		this.gcDescripcion = gcDescripcion;
	}


}
