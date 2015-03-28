package mx.gob.segob.nsjp.dto.cadenadecustoria;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteWSDTO;

public class CadenaDeCustodiaWSDTO extends GenericWSDTO {

	private Long cadenaDeCustodiaId;
    private Date fechaIntercambio;
    private String observacion;
    private String quienEntrega;
	private String quienRecibe;
    private String quienEmbala;
    private String quienTransporta;
    private Date fechaLevantamiento;
    private String folio;
   
    private ExpedienteWSDTO expediente;

	/**
	 * Regresa el valor de la propiedad cadenaDeCustodiaId
	 * @return the cadenaDeCustodiaId
	 */
	public Long getCadenaDeCustodiaId() {
		return cadenaDeCustodiaId;
	}

	/**
	 * Establece el valor de la propiedad cadenaDeCustodiaId
	 * @param cadenaDeCustodiaId valo cadenaDeCustodiaId a almacenar
	 */
	public void setCadenaDeCustodiaId(Long cadenaDeCustodiaId) {
		this.cadenaDeCustodiaId = cadenaDeCustodiaId;
	}

	/**
	 * Regresa el valor de la propiedad fechaIntercambio
	 * @return the fechaIntercambio
	 */
	public Date getFechaIntercambio() {
		return fechaIntercambio;
	}

	/**
	 * Establece el valor de la propiedad fechaIntercambio
	 * @param fechaIntercambio valo fechaIntercambio a almacenar
	 */
	public void setFechaIntercambio(Date fechaIntercambio) {
		this.fechaIntercambio = fechaIntercambio;
	}

	/**
	 * Regresa el valor de la propiedad observacion
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * Establece el valor de la propiedad observacion
	 * @param observacion valo observacion a almacenar
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * Regresa el valor de la propiedad quienEntrega
	 * @return the quienEntrega
	 */
	public String getQuienEntrega() {
		return quienEntrega;
	}

	/**
	 * Establece el valor de la propiedad quienEntrega
	 * @param quienEntrega valo quienEntrega a almacenar
	 */
	public void setQuienEntrega(String quienEntrega) {
		this.quienEntrega = quienEntrega;
	}

	/**
	 * Regresa el valor de la propiedad quienRecibe
	 * @return the quienRecibe
	 */
	public String getQuienRecibe() {
		return quienRecibe;
	}

	/**
	 * Establece el valor de la propiedad quienRecibe
	 * @param quienRecibe valo quienRecibe a almacenar
	 */
	public void setQuienRecibe(String quienRecibe) {
		this.quienRecibe = quienRecibe;
	}

	/**
	 * Regresa el valor de la propiedad quienEmbala
	 * @return the quienEmbala
	 */
	public String getQuienEmbala() {
		return quienEmbala;
	}

	/**
	 * Establece el valor de la propiedad quienEmbala
	 * @param quienEmbala valo quienEmbala a almacenar
	 */
	public void setQuienEmbala(String quienEmbala) {
		this.quienEmbala = quienEmbala;
	}

	/**
	 * Regresa el valor de la propiedad quienTransporta
	 * @return the quienTransporta
	 */
	public String getQuienTransporta() {
		return quienTransporta;
	}

	/**
	 * Establece el valor de la propiedad quienTransporta
	 * @param quienTransporta valo quienTransporta a almacenar
	 */
	public void setQuienTransporta(String quienTransporta) {
		this.quienTransporta = quienTransporta;
	}

	/**
	 * Regresa el valor de la propiedad fechaLevantamiento
	 * @return the fechaLevantamiento
	 */
	public Date getFechaLevantamiento() {
		return fechaLevantamiento;
	}

	/**
	 * Establece el valor de la propiedad fechaLevantamiento
	 * @param fechaLevantamiento valo fechaLevantamiento a almacenar
	 */
	public void setFechaLevantamiento(Date fechaLevantamiento) {
		this.fechaLevantamiento = fechaLevantamiento;
	}

	/**
	 * Regresa el valor de la propiedad folio
	 * @return the folio
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * Establece el valor de la propiedad folio
	 * @param folio valo folio a almacenar
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}

	/**
	 * Regresa el valor de la propiedad expediente
	 * @return the expediente
	 */
	public ExpedienteWSDTO getExpediente() {
		return expediente;
	}

	/**
	 * Establece el valor de la propiedad expediente
	 * @param expediente valo expediente a almacenar
	 */
	public void setExpediente(ExpedienteWSDTO expediente) {
		this.expediente = expediente;
	}

    
    
}
