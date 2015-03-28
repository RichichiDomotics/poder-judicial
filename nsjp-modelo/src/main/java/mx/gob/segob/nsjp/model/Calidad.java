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
 * Calidad entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Calidad" )
public class Calidad implements java.io.Serializable {

	// Fields

	private Long calidadId;
	private Valor tipoCalidad;
	private String descripcionEstadoFisico;
	private Set<Elemento> elementos = new HashSet<Elemento>(0);

	// Constructors

	/** default constructor */
	public Calidad() {
	}

	/** minimal constructor */
	public Calidad(Long calidadId) {
		this.calidadId = calidadId;
	}
	
	/** minimal constructor */
	public Calidad(Long calidadId, Valor valor) {
		this.calidadId = calidadId;
		this.tipoCalidad = valor;
	}

	/** full constructor */
	public Calidad(Long calidadId, Valor valor,
			String descripcionEstadoFisico, Set<Elemento> elementos) {
		this.calidadId = calidadId;
		this.tipoCalidad = valor;
		this.descripcionEstadoFisico = descripcionEstadoFisico;
		this.elementos = elementos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Calidad_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCalidadId() {
		return this.calidadId;
	}

	public void setCalidadId(Long calidadId) {
		this.calidadId = calidadId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoCalidad_val", nullable = false)
	public Valor getTipoCalidad() {
		return this.tipoCalidad;
	}

	public void setTipoCalidad(Valor valor) {
		this.tipoCalidad = valor;
	}

	@Column(name = "cDescripcionEstadoFisico", length = 60)
	public String getDescripcionEstadoFisico() {
		return this.descripcionEstadoFisico;
	}

	public void setDescripcionEstadoFisico(String descripcionEstadoFisico) {
		this.descripcionEstadoFisico = descripcionEstadoFisico;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "calidad")
	public Set<Elemento> getElementos() {
		return this.elementos;
	}

	public void setElementos(Set<Elemento> elementos) {
		this.elementos = elementos;
	}

}
