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
 * CatDelito entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CatDelito")
public class CatDelito implements java.io.Serializable {

    // Fields

	private Long catDelitoId;
    private String claveDelito;
    private String nombre;
    private Boolean esGrave;
    private Boolean esAccionPenPriv;
    private Set<CatDelitoActuacion> catDelitosActuaciones = new HashSet<CatDelitoActuacion>(0);
    private Long penaMinimaAnios;
    private Long penaMinimaMeses;
    private Long penaMinimaDias;
    private Long penaMaximaAnios;
    private Long penaMaximaMeses;
    private Long penaMaximaDias;
  //UIE
    private CatUIEspecializada unidadIEspecializada;

    
    
    // Constructors

    /** default constructor */
    public CatDelito() {
    }

    /** minimal constructor */
    public CatDelito(String cclaveDelito, String cnombre, Boolean besGrave) {
        this.claveDelito = cclaveDelito;
        this.nombre = cnombre;
        this.esGrave = besGrave;
    }
    
    
    /**Full constructor*/    
	public CatDelito(Long catDelitoId, String claveDelito, String nombre,
			Boolean esGrave, Boolean esAccionPenPriv,
			JerarquiaOrganizacional jerarquiaOrganizacional,
			Set<CatDelitoActuacion> catDelitosActuaciones,
			Long penaMinimaAnios, Long penaMinimaMeses, Long penaMinimaDias,
			Long penaMaximaAnios, Long penaMaximaMeses, Long penaMaximaDias,
			CatUIEspecializada unidadIEspecializada) {
		
		super();
		this.catDelitoId = catDelitoId;
		this.claveDelito = claveDelito;
		this.nombre = nombre;
		this.esGrave = esGrave;
		this.esAccionPenPriv = esAccionPenPriv;
		this.catDelitosActuaciones = catDelitosActuaciones;
		this.penaMinimaAnios = penaMinimaAnios;
		this.penaMinimaMeses = penaMinimaMeses;
		this.penaMinimaDias = penaMinimaDias;
		this.penaMaximaAnios = penaMaximaAnios;
		this.penaMaximaMeses = penaMaximaMeses;
		this.penaMaximaDias = penaMaximaDias;
		this.unidadIEspecializada = unidadIEspecializada;
	}
	
	public CatDelito(Long catDelitoId2) {
    	this.catDelitoId=catDelitoId2;
	}

	// Property accessors
    @Column(name = "cClaveDelito", unique = true, nullable = false, length = 10)
    public String getClaveDelito() {
        return this.claveDelito;
    }

    public void setClaveDelito(String cclaveDelito) {
        this.claveDelito = cclaveDelito;
    }

    @Column(name = "cNombre", nullable = false, length = 300)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String cnombre) {
        this.nombre = cnombre;
    }

    @Column(name = "bEsGrave", nullable = false, precision = 1, scale = 0)
    public Boolean getEsGrave() {
        return this.esGrave;
    }

    public void setEsGrave(Boolean besGrave) {
        this.esGrave = besGrave;
    }

    @Column(name = "bEsAccionPenPriv", nullable = false, precision = 1, scale = 0)
    public Boolean getEsAccionPenPriv() {
		return esAccionPenPriv;
	}

	public void setEsAccionPenPriv(Boolean esAccionPenPriv) {
		this.esAccionPenPriv = esAccionPenPriv;
	}


	/**
	 * Asigna el valor al campo catDelitoId.
	 * @param catDelitoId el valor catDelitoId a asignar
	 */
	public void setCatDelitoId(Long catDelitoId) {
		this.catDelitoId = catDelitoId;
	}

	/**
	 * Método de acceso al campo catDelitoId.
	 * @return El valor del campo catDelitoId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CatDelito_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCatDelitoId() {
		return catDelitoId;
	}

	/**
	 * Método de acceso al campo catDelitosActuaciones.
	 * @return El valor del campo catDelitosActuaciones
	 */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catDelito")
	public Set<CatDelitoActuacion> getCatDelitosActuaciones() {
		return catDelitosActuaciones;
	}

	/**
	 * Asigna el valor al campo catDelitosActuaciones.
	 * @param catDelitosActuaciones el valor catDelitosActuaciones a asignar
	 */
	public void setCatDelitosActuaciones(
			Set<CatDelitoActuacion> catDelitosActuaciones) {
		this.catDelitosActuaciones = catDelitosActuaciones;
	}

	/**
	 * @return the penaMinimaAnios
	 */
	@Column(name = "penaMinimaAnios", nullable = true)
	public Long getPenaMinimaAnios() {
		return penaMinimaAnios;
	}

	/**
	 * @param penaMinimaAnios the penaMinimaAnios to set
	 */
	public void setPenaMinimaAnios(Long penaMinimaAnios) {
		this.penaMinimaAnios = penaMinimaAnios;
	}

	/**
	 * @return the penaMinimaMeses
	 */
	@Column(name = "penaMinimaMeses", nullable = true)
	public Long getPenaMinimaMeses() {
		return penaMinimaMeses;
	}

	/**
	 * @param penaMinimaMeses the penaMinimaMeses to set
	 */
	public void setPenaMinimaMeses(Long penaMinimaMeses) {
		this.penaMinimaMeses = penaMinimaMeses;
	}

	/**
	 * @return the penaMinimaDias
	 */
	@Column(name = "penaMinimaDias", nullable = true)
	public Long getPenaMinimaDias() {
		return penaMinimaDias;
	}

	/**
	 * @param penaMinimaDias the penaMinimaDias to set
	 */
	public void setPenaMinimaDias(Long penaMinimaDias) {
		this.penaMinimaDias = penaMinimaDias;
	}

	/**
	 * @return the penaMaximaAnios
	 */
	@Column(name = "penaMaximaAnios", nullable = true)
	public Long getPenaMaximaAnios() {
		return penaMaximaAnios;
	}

	/**
	 * @param penaMaximaAnios the penaMaximaAnios to set
	 */
	public void setPenaMaximaAnios(Long penaMaximaAnios) {
		this.penaMaximaAnios = penaMaximaAnios;
	}

	/**
	 * @return the penaMaximaMeses
	 */
	@Column(name = "penaMaximaMeses", nullable = true)
	public Long getPenaMaximaMeses() {
		return penaMaximaMeses;
	}

	/**
	 * @param penaMaximaMeses the penaMaximaMeses to set
	 */
	public void setPenaMaximaMeses(Long penaMaximaMeses) {
		this.penaMaximaMeses = penaMaximaMeses;
	}

	/**
	 * @return the penaMaximaDias
	 */
	@Column(name = "penaMaximaDias", nullable = true)
	public Long getPenaMaximaDias() {
		return penaMaximaDias;
	}

	/**
	 * @param penaMaximaDias the penaMaximaDias to set
	 */
	public void setPenaMaximaDias(Long penaMaximaDias) {
		this.penaMaximaDias = penaMaximaDias;
	}

	/**
	 * Asigna el valor al campo unidadIEspecializada.
	 * @param unidadIEspecializada el valor unidadIEspecializada a asignar
	 */
	public void setUnidadIEspecializada(CatUIEspecializada unidadIEspecializada) {
		this.unidadIEspecializada = unidadIEspecializada;
	}

	/**
	 * Método de acceso al campo unidadIEspecializada.
	 * @return El valor del campo unidadIEspecializada
	 */
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catUIE_id", nullable = false)
	public CatUIEspecializada getUnidadIEspecializada() {
		return unidadIEspecializada;
	}

}