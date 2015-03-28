/**
* Nombre del Programa 			: ObraDeArteForm.java
* Autor                         : AlejandroGA
* Compania                    	: Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 May 2011
* Marca de cambio        		: N/A
* Descripcion General    		: Forma usada para en jsp ingresar obra de arte.
* Programa Dependiente  		: N/A
* Programa Subsecuente 			: N/A
* Cond. de ejecucion        	: N/A
* Dias de ejecucion          	: N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       	: N/A
* Compania               		: N/A
* Proyecto                 		: N/A                                 Fecha: N/A
* Modificacion           		: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.objeto.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Forma usada para en jsp ingresar obra de arte.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class ObraDeArteForm extends GenericForm{

	private Long idObraDeArte;
	private Boolean anular;
	//Campos para la insercion desde cadena de custodia
	private String fechaLevCadCus; 
	private String origenEvdCadCus;
	private String cadenaCustodia;
	
	/**
	 * @return the idObraDeArte
	 */
	public Long getIdObraDeArte() {
		return idObraDeArte;
	}

	/**
	 * @param idObraDeArte the idObraDeArte to set
	 */
	public void setIdObraDeArte(Long idObraDeArte) {
		this.idObraDeArte = idObraDeArte;
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
	 * Cantidad de la obra de arte
	 */
    private Long gsCantidadObraArte;
   
    /**
     * id del tipo de obra de arte
     */
    private Long glTipoObraArteId;

    /**
	 *condicion de la obra de arte
	 */
	private Long glCondicionFisica;
	
	/**
	 * Breve descripcion de la obra de arte
	 */
	private String gcDescripcion;

	/**
	 * Método de acceso al campo gsCantidadObraArte.
	 * @return El valor del campo gsCantidadObraArte
	 */
	public Long getGsCantidadObraArte() {
		return gsCantidadObraArte;
	}

	/**
	 * Asigna el valor al campo gsCantidadObraArte.
	 * @param gsCantidadObraArte el valor gsCantidadObraArte a asignar
	 */
	public void setGsCantidadObraArte(Long gsCantidadObraArte) {
		this.gsCantidadObraArte = gsCantidadObraArte;
	}


	/**
	 * Método de acceso al campo glTipoObraArteId.
	 * @return El valor del campo glTipoObraArteId
	 */
	public Long getGlTipoObraArteId() {
		return glTipoObraArteId;
	}

	/**
	 * Asigna el valor al campo glTipoObraArteId.
	 * @param glTipoObraArteId el valor glTipoObraArteId a asignar
	 */
	public void setGlTipoObraArteId(Long glTipoObraArteId) {
		this.glTipoObraArteId = glTipoObraArteId;
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

	/**
	 * Método de acceso al campo serialversionuid.
	 * @return El valor del campo serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
