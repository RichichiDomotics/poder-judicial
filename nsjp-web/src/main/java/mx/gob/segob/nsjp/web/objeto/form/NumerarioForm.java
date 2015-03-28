/**
* Nombre del Programa 		: NumerarioForm.java
* Autor                     : AlejandroGA
* Compania                  : Ultrasist
* Proyecto                  : NSJP                    Fecha: 5 May 2011
* Marca de cambio        	: N/A
* Descripcion General    	: Forma usada para ingresar numerario jsp.
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
 * Forma usada para ingresar numerario jsp.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class NumerarioForm extends GenericForm{

	private Long idNumerario;
	private Boolean anular;
	//Campos para la insercion desde cadena de custodia
	private String fechaLevCadCus; 
	private String origenEvdCadCus;
	private String cadenaCustodia;
	
	/**
	 * @return the idNumerario
	 */
	public Long getIdNumerario() {
		return idNumerario;
	}

	/**
	 * @param idNumerario the idNumerario to set
	 */
	public void setIdNumerario(Long idNumerario) {
		this.idNumerario = idNumerario;
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
	 * Serialize de la calse
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *cantidad del numerario
	 */
	private Long glCantidad;
	
	/**
	 *moneda del numerario
	 */
	private String gcMoneda;
	
	/**
	 *cuenata en la que se deposira el numerario
	 */
	private String gcCtaTesoreria;
	
	/**
	 *fecha de deposito del numerario
	 */
    private String gcFechaDeposito;
    
	/**
	 *hora de deposito del  numerario
	 */
    private String gcHoraDeposito;
    
	/**
	 *condicion del numerario
	 */
	private Long glCondicionFisica;
	
	/**
	 * Breve descripcion del numerario
	 */
	private String gcDescripcion;

	/**
	 * Método de acceso al campo glCantidad.
	 * @return El valor del campo glCantidad
	 */
	public Long getGlCantidad() {
		return glCantidad;
	}

	/**
	 * Asigna el valor al campo glCantidad.
	 * @param glCantidad el valor glCantidad a asignar
	 */
	public void setGlCantidad(Long glCantidad) {
		this.glCantidad = glCantidad;
	}

	/**
	 * Método de acceso al campo gcMoneda.
	 * @return El valor del campo gcMoneda
	 */
	public String getGcMoneda() {
		return gcMoneda;
	}

	/**
	 * Asigna el valor al campo gcMoneda.
	 * @param gcMoneda el valor gcMoneda a asignar
	 */
	public void setGcMoneda(String gcMoneda) {
		this.gcMoneda = gcMoneda;
	}

	/**
	 * Método de acceso al campo gcCtaTesoreria.
	 * @return El valor del campo gcCtaTesoreria
	 */
	public String getGcCtaTesoreria() {
		return gcCtaTesoreria;
	}

	/**
	 * Asigna el valor al campo gcCtaTesoreria.
	 * @param gcCtaTesoreria el valor gcCtaTesoreria a asignar
	 */
	public void setGcCtaTesoreria(String gcCtaTesoreria) {
		this.gcCtaTesoreria = gcCtaTesoreria;
	}

	/**
	 * Método de acceso al campo gcFechaDeposito.
	 * @return El valor del campo gcFechaDeposito
	 */
	public String getGcFechaDeposito() {
		return gcFechaDeposito;
	}

	/**
	 * Asigna el valor al campo gcFechaDeposito.
	 * @param gcFechaDeposito el valor gcFechaDeposito a asignar
	 */
	public void setGcFechaDeposito(String gcFechaDeposito) {
		this.gcFechaDeposito = gcFechaDeposito;
	}

	/**
	 * Método de acceso al campo gcHoraDeposito.
	 * @return El valor del campo gcHoraDeposito
	 */
	public String getGcHoraDeposito() {
		return gcHoraDeposito;
	}

	/**
	 * Asigna el valor al campo gcHoraDeposito.
	 * @param gcHoraDeposito el valor gcHoraDeposito a asignar
	 */
	public void setGcHoraDeposito(String gcHoraDeposito) {
		this.gcHoraDeposito = gcHoraDeposito;
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
