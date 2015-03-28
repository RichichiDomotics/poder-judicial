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
 * LeyCodigo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LeyCodigo")
public class LeyCodigo implements java.io.Serializable {

	// Fields

	private Long leyCodigoId;
	private Valor tipoNorma;
	private String nombre;
	private String descripcion;
	private String url;

	// Constructors

	/** default constructor */
	public LeyCodigo() {
	}

	/** full constructor */
	public LeyCodigo(Long leyCodigoId, Valor valor, String cnombre,
			String cdescripcion, String curl) {
		this.leyCodigoId = leyCodigoId;
		this.tipoNorma = valor;
		this.nombre = cnombre;
		this.descripcion = cdescripcion;
		this.url = curl;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "LeyCodigo_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getLeyCodigoId() {
		return this.leyCodigoId;
	}

	public void setLeyCodigoId(Long leyCodigoId) {
		this.leyCodigoId = leyCodigoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoNorma_val", nullable = false)
	public Valor getTipoNorma() {
		return this.tipoNorma;
	}

	public void setTipoNorma(Valor valor) {
		this.tipoNorma = valor;
	}

	@Column(name = "cNombre", nullable = false, length = 150)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String cnombre) {
		this.nombre = cnombre;
	}

	@Column(name = "cDescripcion", nullable = false, length = 150)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String cdescripcion) {
		this.descripcion = cdescripcion;
	}

	@Column(name = "cURL", nullable = false, length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String curl) {
		this.url = curl;
	}

}