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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * CadenaDeCustodia entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CadenaDeCustodia")
public class CadenaDeCustodia implements java.io.Serializable {

    // Fields
    private Long cadenaDeCustodiaId;
    private Date fechaIntercambio;
    private String observacion;
    private String quienEntrega;
	private String quienRecibe;
    private String quienEmbala;
    private String quienTransporta;
    private String institucionEmbalaje;
    private String institucionTraslado;
    private String folio;
    private Date fechaLevantamiento;
    private Expediente expediente;

    private Set<Evidencia> evidencias = new HashSet<Evidencia>(0);
    // Constructors
    /** default constructor */
    public CadenaDeCustodia() {
    }
    public CadenaDeCustodia(Long id) {
        this.cadenaDeCustodiaId = id;
    }

    public CadenaDeCustodia(Long cadenaDeCustodiaId, String folio) {
		super();
		this.cadenaDeCustodiaId = cadenaDeCustodiaId;
		this.folio = folio;
	}
	/**
	 * Método de acceso al campo quienEntrega.
	 * @return El valor del campo quienEntrega
	 */
    @Column(name = "cQuienEntrega", length = 100)
	public String getQuienEntrega() {
		return quienEntrega;
	}

	/**
	 * Asigna el valor al campo quienEntrega.
	 * @param quienEntrega el valor quienEntrega a asignar
	 */
	public void setQuienEntrega(String quienEntrega) {
		this.quienEntrega = quienEntrega;
	}

	/**
	 * Método de acceso al campo quienRecibe.
	 * @return El valor del campo quienRecibe
	 */
	@Column(name = "cQuienRecibe", length = 100)
	public String getQuienRecibe() {
		return quienRecibe;
	}

	/**
	 * Asigna el valor al campo quienRecibe.
	 * @param quienRecibe el valor quienRecibe a asignar
	 */
	public void setQuienRecibe(String quienRecibe) {
		this.quienRecibe = quienRecibe;
	}

	/**
	 * Método de acceso al campo quienEmbala.
	 * @return El valor del campo quienEmbala
	 */
	@Column(name = "cQuienEmbala", length = 100)
	public String getQuienEmbala() {
		return quienEmbala;
	}

	/**
	 * Asigna el valor al campo quienEmbala.
	 * @param quienEmbala el valor quienEmbala a asignar
	 */
	public void setQuienEmbala(String quienEmbala) {
		this.quienEmbala = quienEmbala;
	}

	/**
	 * Método de acceso al campo quienTransporta.
	 * @return El valor del campo quienTransporta
	 */
	@Column(name = "cQuienTransporta", length = 100)
	public String getQuienTransporta() {
		return quienTransporta;
	}

	/**
	 * Asigna el valor al campo quienTransporta.
	 * @param quienTransporta el valor quienTransporta a asignar
	 */
	public void setQuienTransporta(String quienTransporta) {
		this.quienTransporta = quienTransporta;
	}

	@Column(name = "cInstitucionEmbalaje", length = 150)
	public String getInstitucionEmbalaje() {
		return institucionEmbalaje;
	}
	public void setInstitucionEmbalaje(String institucionEmbalaje) {
		this.institucionEmbalaje = institucionEmbalaje;
	}
	
	@Column(name = "cInstitucionTraslado", length = 150)
	public String getInstitucionTraslado() {
		return institucionTraslado;
	}
	public void setInstitucionTraslado(String institucionTraslado) {
		this.institucionTraslado = institucionTraslado;
	}
	// Property accessors
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CadenaDeCustodia_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getCadenaDeCustodiaId() {
        return this.cadenaDeCustodiaId;
    }

    public void setCadenaDeCustodiaId(Long cadenaDeCustodiaId) {
        this.cadenaDeCustodiaId = cadenaDeCustodiaId;
    }

    @Column(name = "dFechaIntercambio", length = 23)
    public Date getFechaIntercambio() {
        return this.fechaIntercambio;
    }

    public void setFechaIntercambio(Date dfechaIntercambio) {
        this.fechaIntercambio = dfechaIntercambio;
    }

    @Column(name = "cObservacion", length = 300)
    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String cobservacion) {
        this.observacion = cobservacion;
    }

//    @Column(name = "iQuienEntrega", precision = 8, scale = 0)
//    public Integer getQuienEntrega() {
//        return this.quienEntrega;
//    }
//
//    public void setQuienEntrega(Integer iquienEntrega) {
//        this.quienEntrega = iquienEntrega;
//    }
//
//    @Column(name = "iQuienRecibe", precision = 8, scale = 0)
//    public Integer getQuienRecibe() {
//        return this.quienRecibe;
//    }
//
//    public void setQuienRecibe(Integer iquienRecibe) {
//        this.quienRecibe = iquienRecibe;
//    }

    @Column(name = "dFechaLevantamiento", length = 23)
    public Date getFechaLevantamiento() {
        return this.fechaLevantamiento;
    }

    public void setFechaLevantamiento(Date dfechaLevantamiento) {
        this.fechaLevantamiento = dfechaLevantamiento;
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cadenaDeCustodia")
    public Set<Evidencia> getEvidencias() {
        return this.evidencias;
    }

    public void setEvidencias(Set<Evidencia> evidencias) {
        this.evidencias = evidencias;
    }

    @Column(name = "cFolio", length=25)
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Expediente_id", nullable = false)
    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
}
