/**
* Nombre del Programa 		: HechoForm.java
* Autor                     : ArmandoCT
* Compania                  : Ultrasist
* Proyecto                  : NSJP                    Fecha: 14 Junio 2011
* Marca de cambio        	: N/A
* Descripcion General    	: Forma para el objeto Hecho.
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
package mx.gob.segob.nsjp.web.cadenadecustodia.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Forma para el objeto Hecho.
 * @version 1.0
 * @author ArmandoCT
 *
 */
public class CadenaCustodiaForm extends GenericForm{

	/**
	 * Serialize de la clase
	 */
	private static final long serialVersionUID = 1L;
	
	private String numeroExpediente;

	private String cadenaDeCustodiaId;
    private String fechaIntercambio;
    private String horaIntercambio;
	private String horaLevantamiento;
    private String observacion;
    private String quienEntrega;
	private String quienRecibe;
    private String quienEmbala;
    private String quienTransporta;
    private String fechaLevantamiento;
    private String folio;
    private String institutionTraslada;
    private String institutionEmbala;
    
    /**
	 * @return the institutionTraslada
	 */
	public String getInstitutionTraslada() {
		return institutionTraslada;
	}
	/**
	 * @param institutionTraslada the institutionTraslada to set
	 */
	public void setInstitutionTraslada(String institutionTraslada) {
		this.institutionTraslada = institutionTraslada;
	}
	/**
	 * @return the institutionEmbala
	 */
	public String getInstitutionEmbala() {
		return institutionEmbala;
	}
	/**
	 * @param institutionEmbala the institutionEmbala to set
	 */
	public void setInstitutionEmbala(String institutionEmbala) {
		this.institutionEmbala = institutionEmbala;
	}
	/**
	 * @return the horaIntercambio
	 */
	public String getHoraIntercambio() {
		return horaIntercambio;
	}
	/**
	 * @param horaIntercambio the horaIntercambio to set
	 */
	public void setHoraIntercambio(String horaIntercambio) {
		this.horaIntercambio = horaIntercambio;
	}
	/**
	 * @return the horaLevantamiento
	 */
	public String getHoraLevantamiento() {
		return horaLevantamiento;
	}
	/**
	 * @param horaLevantamiento the horaLevantamiento to set
	 */
	public void setHoraLevantamiento(String horaLevantamiento) {
		this.horaLevantamiento = horaLevantamiento;
	}
	/**
	 * @return the numeroExpediente
	 */
	public String getNumeroExpediente() {
		return numeroExpediente;
	}
	/**
	 * @param numeroExpediente the numeroExpediente to set
	 */
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	/**
	 * @return the cadenaDeCustodiaId
	 */
	public String getCadenaDeCustodiaId() {
		return cadenaDeCustodiaId;
	}
	/**
	 * @param cadenaDeCustodiaId the cadenaDeCustodiaId to set
	 */
	public void setCadenaDeCustodiaId(String cadenaDeCustodiaId) {
		this.cadenaDeCustodiaId = cadenaDeCustodiaId;
	}
	/**
	 * @return the fechaIntercambio
	 */
	public String getFechaIntercambio() {
		return fechaIntercambio;
	}
	/**
	 * @param fechaIntercambio the fechaIntercambio to set
	 */
	public void setFechaIntercambio(String fechaIntercambio) {
		this.fechaIntercambio = fechaIntercambio;
	}
	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	/**
	 * @return the quienEntrega
	 */
	public String getQuienEntrega() {
		return quienEntrega;
	}
	/**
	 * @param quienEntrega the quienEntrega to set
	 */
	public void setQuienEntrega(String quienEntrega) {
		this.quienEntrega = quienEntrega;
	}
	/**
	 * @return the quienRecibe
	 */
	public String getQuienRecibe() {
		return quienRecibe;
	}
	/**
	 * @param quienRecibe the quienRecibe to set
	 */
	public void setQuienRecibe(String quienRecibe) {
		this.quienRecibe = quienRecibe;
	}
	/**
	 * @return the quienEmbala
	 */
	public String getQuienEmbala() {
		return quienEmbala;
	}
	/**
	 * @param quienEmbala the quienEmbala to set
	 */
	public void setQuienEmbala(String quienEmbala) {
		this.quienEmbala = quienEmbala;
	}
	/**
	 * @return the quienTransporta
	 */
	public String getQuienTransporta() {
		return quienTransporta;
	}
	/**
	 * @param quienTransporta the quienTransporta to set
	 */
	public void setQuienTransporta(String quienTransporta) {
		this.quienTransporta = quienTransporta;
	}
	/**
	 * @return the fechaLevantamiento
	 */
	public String getFechaLevantamiento() {
		return fechaLevantamiento;
	}
	/**
	 * @param fechaLevantamiento the fechaLevantamiento to set
	 */
	public void setFechaLevantamiento(String fechaLevantamiento) {
		this.fechaLevantamiento = fechaLevantamiento;
	}
	/**
	 * @return the folio
	 */
	public String getFolio() {
		return folio;
	}
	/**
	 * @param folio the folio to set
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	}
