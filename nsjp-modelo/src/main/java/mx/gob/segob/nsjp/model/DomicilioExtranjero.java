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
 * DomicilioExtranjero entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DomicilioExtranjero" )
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="DomicilioExtranjero_id")
public class DomicilioExtranjero extends Domicilio{

	// Fields

	private Valor valor;
	private String codigoPostal;
	private String asentamientoExt;
	private String municipioStr;
	private String ciudadStr;
	private String estadoStr;
	private String pais;

	// Constructors

	/** default constructor */
	public DomicilioExtranjero() {
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Pais_val")
	public Valor getValor() {
		return this.valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}

	@Column(name = "cCodigoPostal", length = 10)
	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Column(name = "cAsentamientoExt", length = 50)
	public String getAsentamientoExt() {
		return this.asentamientoExt;
	}

	public void setAsentamientoExt(String asentamientoExt) {
		this.asentamientoExt = asentamientoExt;
	}

	@Column(name = "cMunicipio", length = 50)
	public String getMunicipioStr() {
		return this.municipioStr;
	}

	public void setMunicipioStr(String municipio) {
		this.municipioStr = municipio;
	}

	@Column(name = "cCiudad", length = 50)
	public String getCiudadStr() {
		return this.ciudadStr;
	}

	public void setCiudadStr(String ciudad) {
		this.ciudadStr = ciudad;
	}

	@Column(name = "cEstado", length = 50)
	public String getEstadoStr() {
		return this.estadoStr;
	}

	public void setEstadoStr(String estado) {
		this.estadoStr = estado;
	}

	@Column(name = "cPais", length = 50)
	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
