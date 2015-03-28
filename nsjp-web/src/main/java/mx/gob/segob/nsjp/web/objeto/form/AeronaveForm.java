/**
* Nombre del Programa 		: AeronaveForm.java
* Autor                     : AlejandroGA
* Compania                  : Ultrasist
* Proyecto                  : NSJP                    Fecha: 29 Apr 2011
* Marca de cambio        	: N/A
* Descripcion General    	: Forma para el objeto Aeronave.
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
 * Forma para el objeto Aeronave.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class AeronaveForm extends GenericForm{

	private Long idAeronave;
	private Boolean anular;
	//Campos para la insercion desde cadena de custodia
	private String fechaLevCadCus; 
	private String origenEvdCadCus;
	private String cadenaCustodia;
	
	


	/**
	 * @return the idAeronave
	 */
	public Long getIdAeronave() {
		return idAeronave;
	}

	/**
	 * @param idAeronave the idAeronave to set
	 */
	public void setIdAeronave(Long idAeronave) {
		this.idAeronave = idAeronave;
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
	 * identificador del tipo de aeronave
	 */
	private Long glTipoAeronaveId;
	
	/**
	 * identificador de la marca de la aeronave
	 */
	private Long glMarcaAeronaveId;
	
	/**
	 * identificador de la sub marca de la aeronave
	 */
	private Long glSubMarcaAeronaveId;
	
	 /**
	 * Modelo de la aeronave
	 */
    private String gcModeloAeronave;
    
	/**
	 * identificador del pais de procedencia de la aeronave
	 */
    private Long glPaisProcedAeronaveId;
    
    /**
	 * identificador del color de la aeronave
	 */
    private Long glColorAeronaveId;
    
    
    /**
	 * Matricula de la aeronave
	 */
    private String gcMatriculaAeronave;
    
    /**
	 * No de serie de la aeronave
	 */
    private String gcNoSerieAeronave;
    
    /**
	 * No de motor de la aeronave
	 */
    private String gcNoMotorAeronave;
    
    /**
	 * Id de la condicion de la aeronave
	 */
    private Long glCondicionAeronaveId;
    
    /**
	 * Desripcion de la aeronave
	 */
    private String gcDescripcionAeronave;
    
    
    
	/**
	 * Método de acceso al campo serialversionuid.
	 * @return El valor del campo serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	/**
	 * Método de acceso al campo glTipoAeronaveId.
	 * @return El valor del campo glTipoAeronaveId
	 */
	public Long getGlTipoAeronaveId() {
		return glTipoAeronaveId;
	}
	
	/**
	 * Asigna el valor al campo glTipoAeronaveId.
	 * @param glTipoAeronaveId el valor glTipoAeronaveId a asignar
	 */
	public void setGlTipoAeronaveId(Long glTipoAeronaveId) {
		this.glTipoAeronaveId = glTipoAeronaveId;
	}
	
	/**
	 * Método de acceso al campo glMarcaAeronaveId.
	 * @return El valor del campo glMarcaAeronaveId
	 */
	public Long getGlMarcaAeronaveId() {
		return glMarcaAeronaveId;
	}
	
	/**
	 * Asigna el valor al campo glMarcaAeronaveId.
	 * @param glMarcaAeronaveId el valor glMarcaAeronaveId a asignar
	 */
	public void setGlMarcaAeronaveId(Long glMarcaAeronaveId) {
		this.glMarcaAeronaveId = glMarcaAeronaveId;
	}

	/**
	 * Método de acceso al campo glSubMarcaAeronaveId.
	 * @return El valor del campo glSubMarcaAeronaveId
	 */
	public Long getGlSubMarcaAeronaveId() {
		return glSubMarcaAeronaveId;
	}

	/**
	 * Asigna el valor al campo glSubMarcaAeronaveId.
	 * @param glSubMarcaAeronaveId el valor glSubMarcaAeronaveId a asignar
	 */
	public void setGlSubMarcaAeronaveId(Long glSubMarcaAeronaveId) {
		this.glSubMarcaAeronaveId = glSubMarcaAeronaveId;
	}

	/**
	 * Método de acceso al campo glPaisProcedAeronaveId.
	 * @return El valor del campo glPaisProcedAeronaveId
	 */
	public Long getGlPaisProcedAeronaveId() {
		return glPaisProcedAeronaveId;
	}

	/**
	 * Asigna el valor al campo glPaisProcedAeronaveId.
	 * @param glPaisProcedAeronaveId el valor glPaisProcedAeronaveId a asignar
	 */
	public void setGlPaisProcedAeronaveId(Long glPaisProcedAeronaveId) {
		this.glPaisProcedAeronaveId = glPaisProcedAeronaveId;
	}

	/**
	 * Método de acceso al campo glColorAeronaveId.
	 * @return El valor del campo glColorAeronaveId
	 */
	public Long getGlColorAeronaveId() {
		return glColorAeronaveId;
	}

	/**
	 * Asigna el valor al campo glColorAeronaveId.
	 * @param glColorAeronaveId el valor glColorAeronaveId a asignar
	 */
	public void setGlColorAeronaveId(Long glColorAeronaveId) {
		this.glColorAeronaveId = glColorAeronaveId;
	}

	/**
	 * Método de acceso al campo gcMatriculaAeronave.
	 * @return El valor del campo gcMatriculaAeronave
	 */
	public String getGcMatriculaAeronave() {
		return gcMatriculaAeronave;
	}

	/**
	 * Asigna el valor al campo gcMatriculaAeronave.
	 * @param gcMatriculaAeronave el valor gcMatriculaAeronave a asignar
	 */
	public void setGcMatriculaAeronave(String gcMatriculaAeronave) {
		this.gcMatriculaAeronave = gcMatriculaAeronave;
	}

	/**
	 * Método de acceso al campo gcNoSerieAeronave.
	 * @return El valor del campo gcNoSerieAeronave
	 */
	public String getGcNoSerieAeronave() {
		return gcNoSerieAeronave;
	}

	/**
	 * Asigna el valor al campo gcNoSerieAeronave.
	 * @param gcNoSerieAeronave el valor gcNoSerieAeronave a asignar
	 */
	public void setGcNoSerieAeronave(String gcNoSerieAeronave) {
		this.gcNoSerieAeronave = gcNoSerieAeronave;
	}

	/**
	 * Método de acceso al campo gcNoMotorAeronave.
	 * @return El valor del campo gcNoMotorAeronave
	 */
	public String getGcNoMotorAeronave() {
		return gcNoMotorAeronave;
	}

	/**
	 * Asigna el valor al campo gcNoMotorAeronave.
	 * @param gcNoMotorAeronave el valor gcNoMotorAeronave a asignar
	 */
	public void setGcNoMotorAeronave(String gcNoMotorAeronave) {
		this.gcNoMotorAeronave = gcNoMotorAeronave;
	}

	/**
	 * Método de acceso al campo gcModeloAeronave.
	 * @return El valor del campo gcModeloAeronave
	 */
	public String getGcModeloAeronave() {
		return gcModeloAeronave;
	}

	/**
	 * Asigna el valor al campo gcModeloAeronave.
	 * @param gcModeloAeronave el valor gcModeloAeronave a asignar
	 */
	public void setGcModeloAeronave(String gcModeloAeronave) {
		this.gcModeloAeronave = gcModeloAeronave;
	}

	/**
	 * Método de acceso al campo glCondicionAeronaveId.
	 * @return El valor del campo glCondicionAeronaveId
	 */
	public Long getGlCondicionAeronaveId() {
		return glCondicionAeronaveId;
	}

	/**
	 * Asigna el valor al campo glCondicionAeronaveId.
	 * @param glCondicionAeronaveId el valor glCondicionAeronaveId a asignar
	 */
	public void setGlCondicionAeronaveId(Long glCondicionAeronaveId) {
		this.glCondicionAeronaveId = glCondicionAeronaveId;
	}

	/**
	 * Método de acceso al campo gcDescripcionAeronave.
	 * @return El valor del campo gcDescripcionAeronave
	 */
	public String getGcDescripcionAeronave() {
		return gcDescripcionAeronave;
	}

	/**
	 * Asigna el valor al campo gcDescripcionAeronave.
	 * @param gcDescripcionAeronave el valor gcDescripcionAeronave a asignar
	 */
	public void setGcDescripcionAeronave(String gcDescripcionAeronave) {
		this.gcDescripcionAeronave = gcDescripcionAeronave;
	}
	
}
