package mx.gob.segob.nsjp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Forma entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Forma")
public class Forma implements java.io.Serializable {

    // Fields

    private Long formaId;
    private Valor tipoForma;
    private String descForma;
    private Set<Documento> documentos = new HashSet<Documento>(0);
    private String nombre;
    private String cuerpo;
    private Valor tipoDocumento;
    private Date fechaCreacion;
    

    // Constructors

    /** default constructor */
    public Forma() {
    }

    /** minimal constructor */
    public Forma(Long formaId) {
        this.formaId = formaId;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Forma_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getFormaId() {
        return this.formaId;
    }

    public void setFormaId(Long formaId) {
        this.formaId = formaId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoForma_val")
    public Valor getTipoForma() {
        return this.tipoForma;
    }

    public void setTipoForma(Valor valor) {
        this.tipoForma = valor;
    }

    @Column(name = "cDescForma")
    public String getDescForma() {
        return this.descForma;
    }

    public void setDescForma(String descForma) {
        this.descForma = descForma;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "forma")
    public Set<Documento> getDocumentos() {
        return this.documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }
    
    @Column(name = "cNombre", length = 50)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    @Column(name = "cCuerpo")
	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public void setTipoDocumento(Valor tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "TipoDocumento_val")
	@Transient
	public Valor getTipoDocumento() {
		return tipoDocumento;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

//	@Column(name = "dFechaCreacion")
	@Transient
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
    
    

}