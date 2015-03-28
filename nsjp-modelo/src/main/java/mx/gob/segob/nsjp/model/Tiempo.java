package mx.gob.segob.nsjp.model;

import java.sql.Timestamp;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tiempo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Tiempo")
public class Tiempo implements java.io.Serializable {

    // Fields

    private Long tiempoId;
    private Valor tipoRegistro;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private Set<Hecho> hechos = new HashSet<Hecho>(0);

    // Constructors

    /** default constructor */
    public Tiempo() {
    }

    /** minimal constructor */
    public Tiempo(Long tiempoId, Valor valor) {
        this.tiempoId = tiempoId;
        this.tipoRegistro = valor;
    }

    /** full constructor */
    public Tiempo(Long tiempoId, Valor valor, String cdescripcion,
            Timestamp dfechaInicio, Timestamp dfechaFin, Set<Hecho> hechos) {
        this.tiempoId = tiempoId;
        this.tipoRegistro = valor;
        this.descripcion = cdescripcion;
        this.fechaInicio = dfechaInicio;
        this.fechaFin = dfechaFin;
        this.hechos = hechos;
    }

    public Tiempo(Long tiempoId) {
		super();
		this.tiempoId= tiempoId;
	}

	// Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Tiempo_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getTiempoId() {
        return this.tiempoId;
    }

    public void setTiempoId(Long tiempoId) {
        this.tiempoId = tiempoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoRegistro_val", nullable = false)
    public Valor getTipoRegistro() {
        return this.tipoRegistro;
    }

    public void setTipoRegistro(Valor valor) {
        this.tipoRegistro = valor;
    }

    @Column(name = "cDescripcion", length = 300)
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String cdescripcion) {
        this.descripcion = cdescripcion;
    }

    @Column(name = "dFechaInicio", length = 23)
    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date dfechaInicio) {
        this.fechaInicio = dfechaInicio;
    }

    @Column(name = "dFechaFin", length = 23)
    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date dfechaFin) {
        this.fechaFin = dfechaFin;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tiempo")
    public Set<Hecho> getHechos() {
        return this.hechos;
    }

    public void setHechos(Set<Hecho> hechos) {
        this.hechos = hechos;
    }

}