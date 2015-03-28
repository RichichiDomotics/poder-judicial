package mx.gob.segob.nsjp.model;

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
 * Turno entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Turno")
public class Turno implements java.io.Serializable {

    // Fields

    private Long turnoId;

    private Expediente expediente;
    private Usuario usuario;

    private String numeroTurno;
    private String tipoTurno;
    private Boolean esUrgente;
    private String fichaAtencion;

    
    private Valor estatus;    
    
    private java.util.Date fechaAtencion;
    private String nombreCiudadano;
    private String apellidoPaternoCiudadano;
    private String apellidoMaternoCiudadano;

    private CatDiscriminante discriminante;
    // Constructors

    /** default constructor */
    public Turno() {
    }

    /** minimal constructor */
    public Turno(Long turnoId) {
        this.turnoId = turnoId;
    }

    /** full constructor */
    public Turno(Long turnoId, String numeroTurno, String tipoTurno,
            Boolean esUrgente, String fichaAtencion) {
        this.turnoId = turnoId;
        this.numeroTurno = numeroTurno;
        this.tipoTurno = tipoTurno;
        this.esUrgente = esUrgente;
        this.fichaAtencion = fichaAtencion;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Turno_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getTurnoId() {
        return this.turnoId;
    }

    public void setTurnoId(Long turnoId) {
        this.turnoId = turnoId;
    }

    @Column(name = "cNumeroTurno", length = 30)
    public String getNumeroTurno() {
        return this.numeroTurno;
    }

    public void setNumeroTurno(String numeroTurno) {
        this.numeroTurno = numeroTurno;
    }

    @Column(name = "cTipoTurno", length = 20)
    public String getTipoTurno() {
        return this.tipoTurno;
    }

    public void setTipoTurno(String tipoTurno) {
        this.tipoTurno = tipoTurno;
    }

    @Column(name = "bEsUrgente", precision = 1, scale = 0)
    public Boolean getEsUrgente() {
        return this.esUrgente;
    }

    public void setEsUrgente(Boolean esUrgente) {
        this.esUrgente = esUrgente;
    }

    @Column(name = "cFichaAtencion", length = 15)
    public String getFichaAtencion() {
        return this.fichaAtencion;
    }

    public void setFichaAtencion(String fichaAtencion) {
        this.fichaAtencion = fichaAtencion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Expediente_id")
    public Expediente getExpediente() {
        return this.expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_id")
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Column(name = "dFechaAtencion", length = 23, nullable = true)
    public java.util.Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(java.util.Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    /**
     * Método de acceso al campo estatus.
     * @return El valor del campo estatus
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Estatus_val")
    public Valor getEstatus() {
        return estatus;
    }

    /**
     * Asigna el valor al campo estatus.
     * @param estatus el valor estatus a asignar
     */
    public void setEstatus(Valor estatus) {
        this.estatus = estatus;
    }

    /**
     * Método de acceso al campo nombreCiudadano.
     * @return El valor del campo nombreCiudadano
     */
    @Column(name = "cNombreCiudadano", length = 50)
    public String getNombreCiudadano() {
        return nombreCiudadano;
    }

    /**
     * Asigna el valor al campo nombreCiudadano.
     * @param nombreCiudadano el valor nombreCiudadano a asignar
     */
    public void setNombreCiudadano(String nombreCiudadano) {
        this.nombreCiudadano = nombreCiudadano;
    }

    /**
     * Método de acceso al campo apellidoPaternoCiudadano.
     * @return El valor del campo apellidoPaternoCiudadano
     */
    @Column(name = "cApellidoPaternoCiudadano", length = 50)
    public String getApellidoPaternoCiudadano() {
        return apellidoPaternoCiudadano;
    }

    /**
     * Asigna el valor al campo apellidoPaternoCiudadano.
     * @param apellidoPaternoCiudadano el valor apellidoPaternoCiudadano a asignar
     */
    public void setApellidoPaternoCiudadano(String apellidoPaternoCiudadano) {
        this.apellidoPaternoCiudadano = apellidoPaternoCiudadano;
    }

    /**
     * Método de acceso al campo apellidoMaternoCiudadano.
     * @return El valor del campo apellidoMaternoCiudadano
     */
    @Column(name = "cApellidoMaternoCiudadano", length = 50)
    public String getApellidoMaternoCiudadano() {
        return apellidoMaternoCiudadano;
    }

    /**
     * Asigna el valor al campo apellidoMaternoCiudadano.
     * @param apellidoMaternoCiudadano el valor apellidoMaternoCiudadano a asignar
     */
    public void setApellidoMaternoCiudadano(String apellidoMaternoCiudadano) {
        this.apellidoMaternoCiudadano = apellidoMaternoCiudadano;
    }

	/**
	 * @return the discriminante
	 */
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catDiscriminante_id")
	public CatDiscriminante getDiscriminante() {
		return discriminante;
	}

	/**
	 * @param discriminante the discriminante to set
	 */
	public void setDiscriminante(CatDiscriminante discriminante) {
		this.discriminante = discriminante;
	}

    
		
}
