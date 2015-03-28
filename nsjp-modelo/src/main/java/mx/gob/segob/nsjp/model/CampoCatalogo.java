package mx.gob.segob.nsjp.model;

import java.util.HashSet;
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
 * CampoCatalogo entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CampoCatalogo" )
public class CampoCatalogo implements java.io.Serializable {

	// Fields

	private Long campoCataogoId;
	private Catalogo catalogo;
	private String nombreCampo;
	private Short tipoDato;
	private Boolean esRecursivo;
	private Boolean esLlave;
	private Set<Valor> valors = new HashSet<Valor>(0);

	// Constructors

	/** default constructor */
	public CampoCatalogo() {
	}
    /** default constructor */
    public CampoCatalogo(Long idCam) {
    this.campoCataogoId = idCam;
    }

	/** minimal constructor */
	public CampoCatalogo(Long campoCataogoId, Catalogo catalogo,
			String nombreCampo, Short tipoDato, Boolean esRecursivo) {
		this.campoCataogoId = campoCataogoId;
		this.catalogo = catalogo;
		this.nombreCampo = nombreCampo;
		this.tipoDato = tipoDato;
		this.esRecursivo = esRecursivo;
	}

	/** full constructor */
	public CampoCatalogo(Long campoCataogoId, Catalogo catalogo,
			String nombreCampo, Short tipoDato, Boolean esRecursivo,
			Set<Valor> valors) {
		this.campoCataogoId = campoCataogoId;
		this.catalogo = catalogo;
		this.nombreCampo = nombreCampo;
		this.tipoDato = tipoDato;
		this.esRecursivo = esRecursivo;
		this.valors = valors;
	}

	// Property accessors
	@Id
	@Column(name = "CampoCatalogo_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getAmpoCatalogoId() {
		return this.campoCataogoId;
	}

	public void setAmpoCatalogoId(Long campoCataogoId) {
		this.campoCataogoId = campoCataogoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Catalogo_id", nullable = false)
	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	@Column(name = "cNombreCampo", nullable = false, length = 50)
	public String getNombreCampo() {
		return this.nombreCampo;
	}

	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	@Column(name = "iTipoDato", nullable = false, precision = 4, scale = 0)
	public Short getTipoDato() {
		return this.tipoDato;
	}

	public void setTipoDato(Short tipoDato) {
		this.tipoDato = tipoDato;
	}

	@Column(name = "bEsRecursivo", nullable = false, precision = 1, scale = 0)
	public Boolean getEsRecursivo() {
		return this.esRecursivo;
	}

	public void setEsRecursivo(Boolean esRecursivo) {
		this.esRecursivo = esRecursivo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "campoCatalogo")
	public Set<Valor> getValors() {
		return this.valors;
	}

	public void setValors(Set<Valor> valors) {
		this.valors = valors;
	}

    /**
     * Método de acceso al campo esLlave.
     * @return El valor del campo esLlave
     */
	@Column(name = "bEsLlave", nullable = false, precision = 1, scale = 0)
    public Boolean getEsLlave() {
        return esLlave;
    }

    /**
     * Asigna el valor al campo esLlave.
     * @param esLlave el valor esLlave a asignar
     */
    public void setEsLlave(Boolean esLlave) {
        this.esLlave = esLlave;
    }

}
