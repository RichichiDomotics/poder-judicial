package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * PuntoCarretero entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PuntoCarretero" )
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "PuntoCarretero_id")
public class PuntoCarretero extends Lugar {

	// Fields
	
	private Valor valor;
	private String numCarretera;
	private String nombreCarretera;
	private Short kilometro;
	private String nombreParaje;
	private String nombreTramo;
	private String poblacionesVecinas;

	// Constructors

	/** default constructor */
	public PuntoCarretero() {
	}

	/** minimal constructor */
	public PuntoCarretero(Valor valor) {		
		this.valor = valor;		
	}

	/** full constructor */
	public PuntoCarretero(Valor valor,
			String numCarretera, String nombreCarretera, Short dcKlometro,
			String nombreParaje, String nombreTramo,
			String poblacionesVecinas) {		
		this.valor = valor;		
		this.numCarretera = numCarretera;
		this.nombreCarretera = nombreCarretera;
		this.kilometro = dcKlometro;
		this.nombreParaje = nombreParaje;
		this.nombreTramo = nombreTramo;
		this.poblacionesVecinas = poblacionesVecinas;
	}	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoCarreteraVal", nullable = false)
	public Valor getValor() {
		return this.valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}	

	@Column(name = "cNumCarretera", length = 10)
	public String getNumCarretera() {
		return this.numCarretera;
	}

	public void setNumCarretera(String numCarretera) {
		this.numCarretera = numCarretera;
	}

	@Column(name = "cNombreCarretera", length = 50)
	public String getNombreCarretera() {
		return this.nombreCarretera;
	}

	public void setNombreCarretera(String nombreCarretera) {
		this.nombreCarretera = nombreCarretera;
	}

	@Column(name = "dcKilometro", precision = 4, scale = 0)
	public Short getKilometro() {
		return this.kilometro;
	}

	public void setKilometro(Short dcKlometro) {
		this.kilometro = dcKlometro;
	}

	@Column(name = "cNombreParaje", length = 50)
	public String getNombreParaje() {
		return this.nombreParaje;
	}

	public void setNombreParaje(String nombreParaje) {
		this.nombreParaje = nombreParaje;
	}

	@Column(name = "cNombreTramo", length = 50)
	public String getNombreTramo() {
		return this.nombreTramo;
	}

	public void setNombreTramo(String nombreTramo) {
		this.nombreTramo = nombreTramo;
	}

	@Column(name = "cPoblacionesVecinas", length = 60)
	public String getPoblacionesVecinas() {
		return this.poblacionesVecinas;
	}

	public void setPoblacionesVecinas(String poblacionesVecinas) {
		this.poblacionesVecinas = poblacionesVecinas;
	}

}
