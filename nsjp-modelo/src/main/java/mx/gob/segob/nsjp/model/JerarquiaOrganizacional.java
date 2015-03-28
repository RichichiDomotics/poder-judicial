package mx.gob.segob.nsjp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JerarquiaOrganizacional entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JerarquiaOrganizacional")
public class JerarquiaOrganizacional implements java.io.Serializable {

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 4553407227296063493L;
    private Long jerarquiaOrganizacionalId;
    private JerarquiaOrganizacional jerarquiaOrgResponsable;
    private String nombre;
    private String abreviatura;
    private Valor tipoJerarquia;
    private Domicilio domicilio;
    private Set<EtiquetaJerarquia> etiquetaJerarquias = new HashSet<EtiquetaJerarquia>(
            0);
    private Set<NumeroExpediente> numeroExpedientes = new HashSet<NumeroExpediente>(
            0);
    private Set<JerarquiaOrganizacional> jerarquiaOrgSubordinadas = new HashSet<JerarquiaOrganizacional>(
            0);

    //Relacion de Cruce con Tipo Solicitud
    private List<JerarquiaOrgTipoSolicitud> jerarquiaOrgTipoSolicitudes = new ArrayList<JerarquiaOrgTipoSolicitud>(); 
    
    // Constructors

    /** default constructor */
    public JerarquiaOrganizacional() {
    }

    /** minimal constructor */
    public JerarquiaOrganizacional(Long jerarquiaOrganizacionalId) {
        this.jerarquiaOrganizacionalId = jerarquiaOrganizacionalId;
    }

    /** minimal constructor */
    public JerarquiaOrganizacional(Long jerarquiaOrganizacionalId, String nom) {
        this.jerarquiaOrganizacionalId = jerarquiaOrganizacionalId;
        this.nombre = nom;
    }
    // Property accessors
    @Id
    @Column(name = "JerarquiaOrganizacional_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getJerarquiaOrganizacionalId() {
        return this.jerarquiaOrganizacionalId;
    }

    public void setJerarquiaOrganizacionalId(Long jerarquiaOrganizacionalId) {
        this.jerarquiaOrganizacionalId = jerarquiaOrganizacionalId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JerarquiaResponsable_id")
    public JerarquiaOrganizacional getJerarquiaOrgResponsable() {
        return this.jerarquiaOrgResponsable;
    }

    public void setJerarquiaOrgResponsable(
            JerarquiaOrganizacional jerarquiaOrganizacional) {
        this.jerarquiaOrgResponsable = jerarquiaOrganizacional;
    }

    @Column(name = "cAbreviatura", length = 10)
    public String getAbreviatura() {
        return this.abreviatura;
    }

    public void setAbreviatura(String cabreviatura) {
        this.abreviatura = cabreviatura;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jerarquiaOrganizacional")
    public Set<EtiquetaJerarquia> getEtiquetaJerarquias() {
        return this.etiquetaJerarquias;
    }

    public void setEtiquetaJerarquias(Set<EtiquetaJerarquia> etiquetaJerarquias) {
        this.etiquetaJerarquias = etiquetaJerarquias;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jerarquiaOrganizacional")
    public Set<NumeroExpediente> getNumeroExpedientes() {
        return this.numeroExpedientes;
    }

    public void setNumeroExpedientes(Set<NumeroExpediente> numeroExpedientes) {
        this.numeroExpedientes = numeroExpedientes;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jerarquiaOrgResponsable")
    public Set<JerarquiaOrganizacional> getJerarquiaOrgSubordinadas() {
        return this.jerarquiaOrgSubordinadas;
    }

    public void setJerarquiaOrgSubordinadas(
            Set<JerarquiaOrganizacional> jerarquiaOrganizacionals) {
        this.jerarquiaOrgSubordinadas = jerarquiaOrganizacionals;
    }

    /**
     * Método de acceso al campo nombre.
     * 
     * @return El valor del campo nombre
     */
    @Column(name = "cNombre", nullable = false, length = 50)
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el valor al campo nombre.
     * 
     * @param nombre
     *            el valor nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método de acceso al campo tipoJerarquia.
     * 
     * @return El valor del campo tipoJerarquia
     */
//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TipoJerarquia_val", nullable = false)
    public Valor getTipoJerarquia() {
        return tipoJerarquia;
    }

    /**
     * Asigna el valor al campo tipoJerarquia.
     * 
     * @param tipoJerarquia
     *            el valor tipoJerarquia a asignar
     */
    public void setTipoJerarquia(Valor tipoJerarquia) {
        this.tipoJerarquia = tipoJerarquia;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Domicilio_id")
    public Domicilio getDomicilio() {
        return this.domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jerarquiaOrganizacional")
	public List<JerarquiaOrgTipoSolicitud> getJerarquiaOrgTipoSolicitudes() {
		return jerarquiaOrgTipoSolicitudes;
	}

	public void setJerarquiaOrgTipoSolicitudes(
			List<JerarquiaOrgTipoSolicitud> jerarquiaOrgTipoSolicitudes) {
		this.jerarquiaOrgTipoSolicitudes = jerarquiaOrgTipoSolicitudes;
	}
}