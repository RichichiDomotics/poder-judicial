package mx.gob.segob.nsjp.dto.funcionario;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

public class BitacoraDefensorDTO extends GenericDTO {

    private Long bitacoraDefensorId;
    private FuncionarioDTO funcionario;
    private ExpedienteDTO expediente;
    private Boolean lecturaDerechos;
    private Boolean bEsAceptado;
    private Boolean esJudicializacion;
    private Date fechaJudicializacion;
    private Boolean esLiberacion;
    private Date fechaLiberacion;
    private String observaciones;
    
    /**
	 * Regresa el valor de la propiedad bitacoraDefensorId
	 * @return the bitacoraDefensorId
	 */
	public Long getBitacoraDefensorId() {
		return bitacoraDefensorId;
	}

	/**
	 * Establece el valor de la propiedad bitacoraDefensorId
	 * @param bitacoraDefensorId valo bitacoraDefensorId a almacenar
	 */
	public void setBitacoraDefensorId(Long bitacoraDefensorId) {
		this.bitacoraDefensorId = bitacoraDefensorId;
	}

	/**
	 * Regresa el valor de la propiedad funcionario
	 * @return the funcionario
	 */
	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	/**
	 * Establece el valor de la propiedad funcionario
	 * @param funcionario valo funcionario a almacenar
	 */
	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * Regresa el valor de la propiedad expediente
	 * @return the expediente
	 */
	public ExpedienteDTO getExpediente() {
		return expediente;
	}

	/**
	 * Establece el valor de la propiedad expediente
	 * @param expediente valo expediente a almacenar
	 */
	public void setExpediente(ExpedienteDTO expediente) {
		this.expediente = expediente;
	}

	/**
	 * Regresa el valor de la propiedad lecturaDerechos
	 * @return the lecturaDerechos
	 */
	public Boolean getLecturaDerechos() {
		return lecturaDerechos;
	}

	/**
	 * Establece el valor de la propiedad lecturaDerechos
	 * @param lecturaDerechos valo lecturaDerechos a almacenar
	 */
	public void setLecturaDerechos(Boolean lecturaDerechos) {
		this.lecturaDerechos = lecturaDerechos;
	}

	/**
	 * Regresa el valor de la propiedad esAceptacion
	 * @return the esAceptacion
	 */
	public Boolean getEsAceptado() {
		return bEsAceptado;
	}

	/**
	 * Establece el valor de la propiedad esAceptacion
	 * @param esAceptacion valo esAceptacion a almacenar
	 */
	public void setEsAceptado(Boolean esAceptacion) {
		this.bEsAceptado = esAceptacion;
	}

	/**
	 * Regresa el valor de la propiedad esJudicializacion
	 * @return the esJudicializacion
	 */
	public Boolean getEsJudicializacion() {
		return esJudicializacion;
	}

	/**
	 * Establece el valor de la propiedad esJudicializacion
	 * @param esJudicializacion valo esJudicializacion a almacenar
	 */
	public void setEsJudicializacion(Boolean esJudicializacion) {
		this.esJudicializacion = esJudicializacion;
	}

	/**
	 * Regresa el valor de la propiedad fechaJudicializacion
	 * @return the fechaJudicializacion
	 */
	public Date getFechaJudicializacion() {
		return fechaJudicializacion;
	}

	/**
	 * Establece el valor de la propiedad fechaJudicializacion
	 * @param fechaJudicializacion valo fechaJudicializacion a almacenar
	 */
	public void setFechaJudicializacion(Date fechaJudicializacion) {
		this.fechaJudicializacion = fechaJudicializacion;
	}

	/**
	 * Regresa el valor de la propiedad esLiberacion
	 * @return the esLiberacion
	 */
	public Boolean getEsLiberacion() {
		return esLiberacion;
	}

	/**
	 * Establece el valor de la propiedad esLiberacion
	 * @param esLiberacion valo esLiberacion a almacenar
	 */
	public void setEsLiberacion(Boolean esLiberacion) {
		this.esLiberacion = esLiberacion;
	}

	/**
	 * Regresa el valor de la propiedad fechaLiberacion
	 * @return the fechaLiberacion
	 */
	public Date getFechaLiberacion() {
		return fechaLiberacion;
	}

	/**
	 * Establece el valor de la propiedad fechaLiberacion
	 * @param fechaLiberacion valo fechaLiberacion a almacenar
	 */
	public void setFechaLiberacion(Date fechaLiberacion) {
		this.fechaLiberacion = fechaLiberacion;
	}

	/**
	 * Regresa el valor de la propiedad observaciones
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Establece el valor de la propiedad observaciones
	 * @param observaciones valo observaciones a almacenar
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public String toString(){
    	StringBuffer strB = new StringBuffer ();
    	strB.append("[")
    		.append(bitacoraDefensorId+": ");
    	return strB.toString();
    }
}
