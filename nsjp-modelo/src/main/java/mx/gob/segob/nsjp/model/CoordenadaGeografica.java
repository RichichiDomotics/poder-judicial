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
 * CoordenadaGeografica entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CoordenadaGeografica" )
public class CoordenadaGeografica implements java.io.Serializable {

	// Fields

	private Long coordenadaGeograficaId;
	private Lugar lugar;
	private String latitud;
	private String longitud;

	// Constructors

	/** default constructor */
	public CoordenadaGeografica() {
	}

	/** minimal constructor */
	public CoordenadaGeografica(Long coordenadaGeograficaId, Lugar lugar) {
		this.coordenadaGeograficaId = coordenadaGeograficaId;
		this.lugar = lugar;
	}

	/** full constructor */
	public CoordenadaGeografica(Long coordenadaGeograficaId, Lugar lugar,
			String dcLatitud, String dcLongitud) {
		this.coordenadaGeograficaId = coordenadaGeograficaId;
		this.lugar = lugar;
		this.latitud = dcLatitud;
		this.longitud = dcLongitud;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CoordenadaGeografica_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getOordenadaGeograficaId() {
		return this.coordenadaGeograficaId;
	}

	public void setOordenadaGeograficaId(Long coordenadaGeograficaId) {
		this.coordenadaGeograficaId = coordenadaGeograficaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Lugar_id", nullable = false)
	public Lugar getLugar() {
		return this.lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	@Column(name = "cLatitud", length = 10)
	public String getLatitud() {
		return this.latitud;
	}

	public void setLatitud(String dcLatitud) {
		this.latitud = dcLatitud;
	}

	@Column(name = "cLongitud", length = 10)
	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String dcLongitud) {
		this.longitud = dcLongitud;
	}

}
