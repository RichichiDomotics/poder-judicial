package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Delito entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Delito")
public class Delito implements java.io.Serializable {

    // Fields

    private Long delitoId;
    private Expediente expediente;
    private CatDelito catDelito;
    private Boolean esProbable;
    private Boolean esPrincipal;


	private Set<SolicitudDefensorDelito> solicitudDefensorDelitos = new HashSet<SolicitudDefensorDelito>(
            0);
    private Set<AvisoDetencionDelito> avisoDetencionDelitos = new HashSet<AvisoDetencionDelito>(
            0);

    // Constructors

    /** default constructor */
    public Delito() {
    }
    
    public Delito(Long delitoId) {
    	this.delitoId = delitoId;
    }

    public Delito(Long delitoId, Expediente expediente, CatDelito catDelito, 
			Boolean esProbable, Boolean esPrincipal) {
		super();
		this.delitoId = delitoId;
		this.expediente = expediente;
		this.esProbable = esProbable;
		this.esPrincipal = esPrincipal;
		this.catDelito=catDelito;
	}


	// Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Delito_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getDelitoId() {
        return this.delitoId;
    }

    public void setDelitoId(Long deitoId) {
        this.delitoId = deitoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Expediente_id", nullable = true)
    public Expediente getExpediente() {
        return this.expediente;
    }

    public void setExpediente(Expediente exp) {
        this.expediente = exp;
    }

    /**
     * Metodo de acceso al campo esProbable.
     * 
     * @return El valor del campo esProbable
     */

    @Column(name = "bEsProbable", nullable = false, precision = 1, scale = 0)
    public Boolean getEsProbable() {
        return esProbable;
    }

    /**
     * Asigna el valor al campo esProbable.
     * 
     * @param esProbable
     *            el valor esProbable a asignar
     */
    public void setEsProbable(Boolean esProbable) {
        this.esProbable = esProbable;
    }

    @Column(name = "bEsPrincipal", precision = 1, scale = 0)
    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "delito")
    public Set<SolicitudDefensorDelito> getSolicitudDefensorDelitos() {
        return this.solicitudDefensorDelitos;
    }

    public void setSolicitudDefensorDelitos(
            Set<SolicitudDefensorDelito> solicitudDefensorDelitos) {
        this.solicitudDefensorDelitos = solicitudDefensorDelitos;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "delito")
    public Set<AvisoDetencionDelito> getAvisoDetencionDelitos() {
        return this.avisoDetencionDelitos;
    }

    public void setAvisoDetencionDelitos(
            Set<AvisoDetencionDelito> avisoDetencionDelitos) {
        this.avisoDetencionDelitos = avisoDetencionDelitos;
    }


	/**
	 * Asigna el valor al campo catDelito.
	 * @param catDelito el valor catDelito a asignar
	 */
	public void setCatDelito(CatDelito catDelito) {
		this.catDelito = catDelito;
	}


	/**
	 * Metodo de acceso al campo catDelito.
	 * @return El valor del campo catDelito
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CatDelito_id", nullable = false)
	public CatDelito getCatDelito() {
		return catDelito;
	}
}
