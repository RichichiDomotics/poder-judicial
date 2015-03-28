/**
* Nombre del Programa 			: OtrosForm.java
* Autor                         : ArmandoCT
* Compania                    	: Ultrasist
* Proyecto                      : NSJP                    Fecha: 3 May 2012
* Marca de cambio        		: N/A
* Descripcion General    		: Forma usada para en jsp ingresar objeto Otros.
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
 * Forma usada para en jsp ingresar objeto otros.
 * @version 1.0
 * @author ArmandoCT
 *
 */
public class OtrosForm extends GenericForm{

	private Long idOtros;
	private Boolean anular;
	
	//Campos para la insercion desde cadena de custodia
	private String fechaLevCadCus; 
	private String origenEvdCadCus;
	private String cadenaCustodia;
	
	
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
	 * Nombre del objeto
	 */
	private String gcNombre;
	
	/***
	 * Metodo para regresar el id del objeto
	 * @return el valor del Id del objeto
	 */
	public Long getIdOtros() {
		return idOtros;
	}

	/***
	 * Metodo para asignar el valor del Id del objeto
	 * @param idOtros valor a ser asignado
	 */
	public void setIdOtros(Long idOtros) {
		this.idOtros = idOtros;
	}

	/***
	 * Metodo para obtener el nombre del Objeto 
	 * @return cadena del nombre
	 */
	public String getGcNombre() {
		return gcNombre;
	}

	/***
	 * Metodo para asignar el valor del nombre
	 * @param gcNombre valor a ser asignado
	 */
	public void setGcNombre(String gcNombre) {
		this.gcNombre = gcNombre;
	}

	/**
	 * Breve descripcion del objeto
	 */
	private String gcDescripcion;

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
