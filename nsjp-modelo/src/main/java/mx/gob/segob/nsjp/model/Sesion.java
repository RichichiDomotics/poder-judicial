package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "Sesion")
@Inheritance(strategy = InheritanceType.JOINED)
public class Sesion implements java.io.Serializable{
    private Long sesionId;
	private java.util.Date fechaSesion;
	private Boolean esPresente;
	private Short numeroSesion;
	private Valor tipoSesion;
	private NumeroExpediente numeroExpediente;

	public Sesion() {
	}


	public Sesion(Long sesionId, Date fechaSesion, Boolean esPresente,
			Short numeroSesion, Valor tipoSesion,
			NumeroExpediente numeroExpediente) {
		super();
		this.sesionId = sesionId;
		this.fechaSesion = fechaSesion;
		this.esPresente = esPresente;
		this.numeroSesion = numeroSesion;
		this.tipoSesion = tipoSesion;
		this.numeroExpediente = numeroExpediente;
	}


	public void setFechaSesion(java.util.Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	}

    @Column(name = "dFechaSesion", nullable = false, length = 23)
	public java.util.Date getFechaSesion() {
		return fechaSesion;
	}

    

	/**
	 * Método de acceso al campo esPresente.
	 * @return El valor del campo esPresente
	 */
    @Column(name = "bEsPresente", precision = 1, scale = 0)
	public Boolean getEsPresente() {
		return esPresente;
	}

	/**
	 * Asigna el valor al campo esPresente.
	 * @param esPresente el valor esPresente a asignar
	 */
	public void setEsPresente(Boolean esPresente) {
		this.esPresente = esPresente;
	}

	public void setNumeroSesion(Short numeroSesion) {
		this.numeroSesion = numeroSesion;
	}
	
    @Column(name = "iNumeroSesion", precision = 4, scale = 0)	
	public Short getNumeroSesion() {
		return numeroSesion;
	}

	public void setTipoSesion(Valor tipoSesion) {
		this.tipoSesion = tipoSesion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoSesion_val", nullable = false)
	public Valor getTipoSesion() {
		return tipoSesion;
	}

	/**
	 * Método de acceso al campo numeroExpediente.
	 * @return El valor del campo numeroExpediente
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumeroExpediente_id", nullable = true)
	public NumeroExpediente getNumeroExpediente() {
		return numeroExpediente;
	}

	/**
	 * Asigna el valor al campo numeroExpediente.
	 * @param numeroExpediente el valor numeroExpediente a asignar
	 */
	public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	/**
	 * Método de acceso al campo sesionId.
	 * @return El valor del campo sesionId
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Sesion_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getSesionId() {
		return sesionId;
	}

	/**
	 * Asigna el valor al campo sesionId.
	 * @param sesionId el valor sesionId a asignar
	 */
	public void setSesionId(Long sesionId) {
		this.sesionId = sesionId;
	}	
	
}
