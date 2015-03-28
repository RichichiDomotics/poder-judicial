/**
 * 
 */
package mx.gob.segob.nsjp.dto.alarmas;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * @author adrian
 * 
 */
public class AlertaDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1479831802687383847L;

	private Long alertaId;
	private Date fechaAlerta;

	private ValorDTO estatusAlarmaAlerta;
	private ValorDTO tipoAlerta;
	private AlarmaDTO alarma;
	
	private String nombre;
	private Boolean esAplaza;
	private Short tiempo;
	private ValorDTO unidadDeTiempo;
	private UsuarioDTO usuario;
	
	/**
	 * @return the fechaAlerta
	 */
	public Date getFechaAlerta() {
		return fechaAlerta;
	}
	/**
	 * @param fechaAlerta the fechaAlerta to set
	 */
	public void setFechaAlerta(Date fechaAlerta) {
		this.fechaAlerta = fechaAlerta;
	}
	/**
	 * @return the estatusAlarmaAlerta
	 */
	public ValorDTO getEstatusAlarmaAlerta() {
		return estatusAlarmaAlerta;
	}
	/**
	 * @param estatusAlarmaAlerta the estatusAlarmaAlerta to set
	 */
	public void setEstatusAlarmaAlerta(ValorDTO estatusAlarmaAlerta) {
		this.estatusAlarmaAlerta = estatusAlarmaAlerta;
	}
	/**
	 * @return the tipoAlerta
	 */
	public ValorDTO getTipoAlerta() {
		return tipoAlerta;
	}
	/**
	 * @param tipoAlerta the tipoAlerta to set
	 */
	public void setTipoAlerta(ValorDTO tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}
	/**
	 * @param alertaId the alertaId to set
	 */
	public void setAlertaId(Long alertaId) {
		this.alertaId = alertaId;
	}
	/**
	 * @return the alertaId
	 */
	public Long getAlertaId() {
		return alertaId;
	}
	/**
	 * @param alarma the alarma to set
	 */
	public void setAlarma(AlarmaDTO alarma) {
		this.alarma = alarma;
	}
	/**
	 * @return the alarma
	 */
	public AlarmaDTO getAlarma() {
		return alarma;
	}
	/**
	 * Método de acceso al campo nombre.
	 * @return El valor del campo nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Asigna el valor al campo nombre.
	 * @param nombre el valor nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Método de acceso al campo esAplaza.
	 * @return El valor del campo esAplaza
	 */
	public Boolean getEsAplaza() {
		return esAplaza;
	}
	/**
	 * Asigna el valor al campo esAplaza.
	 * @param esAplaza el valor esAplaza a asignar
	 */
	public void setEsAplaza(Boolean esAplaza) {
		this.esAplaza = esAplaza;
	}
	/**
	 * Método de acceso al campo tiempo.
	 * @return El valor del campo tiempo
	 */
	public Short getTiempo() {
		return tiempo;
	}
	/**
	 * Asigna el valor al campo tiempo.
	 * @param tiempo el valor tiempo a asignar
	 */
	public void setTiempo(Short tiempo) {
		this.tiempo = tiempo;
	}
	/**
	 * Método de acceso al campo unidadDeTiempo.
	 * @return El valor del campo unidadDeTiempo
	 */
	public ValorDTO getUnidadDeTiempo() {
		return unidadDeTiempo;
	}
	/**
	 * Asigna el valor al campo unidadDeTiempo.
	 * @param unidadDeTiempo el valor unidadDeTiempo a asignar
	 */
	public void setUnidadDeTiempo(ValorDTO unidadDeTiempo) {
		this.unidadDeTiempo = unidadDeTiempo;
	}
	/**
	 * Método de acceso al campo usuario.
	 * @return El valor del campo usuario
	 */
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	/**
	 * Asigna el valor al campo usuario.
	 * @param usuario el valor usuario a asignar
	 */
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}	

}
