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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * SalaAudiencia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SalaAudiencia")
public class SalaAudiencia implements java.io.Serializable {

    // Fields

    private Long salaAudienciaId;
    private String nombreSala;
    private String domicilioSala;
    private String ubicacionSala;
    private Set<Audiencia> audiencias = new HashSet<Audiencia>(0);

    private Boolean esActivo;
    private Funcionario encargado = null;
	
	private CatDiscriminante catDiscriminante = null;
	private SalaJAVS salaJAVS = null;

    // Constructors

    /** default constructor */
    public SalaAudiencia() {
    }

    /** minimal constructor */
    public SalaAudiencia(Long salaAudienciaId) {
        this.salaAudienciaId = salaAudienciaId;
        
    }
    /** minimal constructor */
    public SalaAudiencia(Long salaAudienciaId, String cnombreSala) {
        this.salaAudienciaId = salaAudienciaId;
        this.nombreSala = cnombreSala;
    }

    /** full constructor */
    public SalaAudiencia(Long salaAudienciaId, String cnombreSala,
            String cdomicilioSala, String cubicacionSala,
            Set<Audiencia> audiencias) {
        this.salaAudienciaId = salaAudienciaId;
        this.nombreSala = cnombreSala;
        this.domicilioSala = cdomicilioSala;
        this.ubicacionSala = cubicacionSala;
        this.audiencias = audiencias;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SalaAudiencia_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getSalaAudienciaId() {
        return this.salaAudienciaId;
    }

    public void setSalaAudienciaId(Long salaAudienciaId) {
        this.salaAudienciaId = salaAudienciaId;
    }

    @Column(name = "cNombreSala", nullable = false, length = 100)
    public String getNombreSala() {
        return this.nombreSala;
    }

    public void setNombreSala(String cnombreSala) {
        this.nombreSala = cnombreSala;
    }

    @Column(name = "cDomicilioSala", nullable = false, length = 150)
    public String getDomicilioSala() {
        return this.domicilioSala;
    }

    public void setDomicilioSala(String cdomicilioSala) {
        this.domicilioSala = cdomicilioSala;
    }

    @Column(name = "cUbicacionSala", nullable = false, length = 100)
    public String getUbicacionSala() {
        return this.ubicacionSala;
    }

    public void setUbicacionSala(String cubicacionSala) {
        this.ubicacionSala = cubicacionSala;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "salaAudiencia")
    public Set<Audiencia> getAudiencias() {
        return this.audiencias;
    }

    public void setAudiencias(Set<Audiencia> audiencias) {
        this.audiencias = audiencias;
    }

    /**
     * Método de acceso al campo esActivo.
     * 
     * @return El valor del campo esActivo
     */
    @Column(name = "bEsActivo", precision = 1, scale = 0)
    public Boolean getEsActivo() {
        return esActivo;
    }

    /**
     * Asigna el valor al campo esActivo.
     * 
     * @param esActivo
     *            el valor esActivo a asignar
     */
    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }

    /**
     * Método de acceso al campo encargado.
     * 
     * @return El valor del campo encargado
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionario", nullable = true)
    public Funcionario getEncargado() {
        return encargado;
    }

    /**
     * Asigna el valor al campo encargado.
     * 
     * @param encargado
     *            el valor encargado a asignar
     */
    public void setEncargado(Funcionario encargado) {
        this.encargado = encargado;
    }
    
    
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "catDiscriminante_id", nullable = false, precision = 18, scale = 0)
	
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catDiscriminante_id", nullable = false)
    public CatDiscriminante getCatDiscriminante() {
		return catDiscriminante;
	}

	/**
	 * @param discriminante the catDiscriminanteId to set
	 */
	public void setCatDiscriminante(CatDiscriminante catDiscriminante) {
		this.catDiscriminante = catDiscriminante;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "salaAudiencia")
	public SalaJAVS getSalaJAVS() {
		return salaJAVS;
	}

	public void setSalaJAVS(SalaJAVS salaJAVS) {
		this.salaJAVS = salaJAVS;
	}
}