/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author adrian
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Alerta")
public class Alerta implements Serializable {
	
	private Long alertaId;
	private Valor tipoAlerta;
	private String nombre;
	private Boolean esAplaza;
	private Short tiempo;
	private Valor unidadDeTiempo;
	private Date fechaAlerta;
	private Usuario usuario;
	private Valor estatusAlarmaAlerta;
	private Alarma alarma;
	

	/**
	 * @return the alertaId
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Alerta_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getAlertaId() {
		return alertaId;
	}
	
	/**
	 * @return the fechaAlerta
	 */
	@Column(name = "dFechaAlerta", nullable = false, length = 23)
	public Date getFechaAlerta() {
		return fechaAlerta;
	}
	/**
	 * @return the estatusAlarmaAlerta
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EstatusAlarmaAlerta_val", nullable = false)
	public Valor getEstatusAlarmaAlerta() {
		return estatusAlarmaAlerta;
	}
	/**
	 * @return the tipoAlerta
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoAlerta_val", nullable = false)
	public Valor getTipoAlerta() {
		return tipoAlerta;
	}
	/**
	 * @return the alarma
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Alarma_id", nullable = false)
	public Alarma getAlarma() {
		return alarma;
	}
	
	/**
	 * @param alertaId the alertaId to set
	 */
	public void setAlertaId(Long alertaId) {
		this.alertaId = alertaId;
	}
	/**
	 * @param fechaAlerta the fechaAlerta to set
	 */
	public void setFechaAlerta(Date fechaAlerta) {
		this.fechaAlerta = fechaAlerta;
	}
	/**
	 * @param estatusAlarmaAlerta the estatusAlarmaAlerta to set
	 */
	public void setEstatusAlarmaAlerta(Valor estatusAlarmaAlerta) {
		this.estatusAlarmaAlerta = estatusAlarmaAlerta;
	}
	/**
	 * @param tipoAlerta the tipoAlerta to set
	 */
	public void setTipoAlerta(Valor tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}
	/**
	 * @param alarma the alarma to set
	 */
	public void setAlarma(Alarma alarma) {
		this.alarma = alarma;
	}

	/** GAMA
	 * Método de acceso al campo nombre.
	 * @return El valor del campo nombre
	 */
	@Column(name = "cNombre", length = 100)
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
	@Column(name = "bEsAplaza", precision = 1, scale = 0)
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
	@Column(name = "iTiempo", precision = 4, scale = 0)
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
	 * Método de acceso al campo usuario.
	 * @return El valor del campo usuario
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_id")
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Asigna el valor al campo usuario.
	 * @param usuario el valor usuario a asignar
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Método de acceso al campo unidadDeTiempo.
	 * @return El valor del campo unidadDeTiempo
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UnidadDeTiempo_val", nullable = false)
	public Valor getUnidadDeTiempo() {
		return unidadDeTiempo;
	}

	/**
	 * Asigna el valor al campo unidadDeTiempo.
	 * @param unidadDeTiempo el valor unidadDeTiempo a asignar
	 */
	public void setUnidadDeTiempo(Valor unidadDeTiempo) {
		this.unidadDeTiempo = unidadDeTiempo;
	}

	
	
}
