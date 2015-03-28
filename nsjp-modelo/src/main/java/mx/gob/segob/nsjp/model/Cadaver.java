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
 * Cadaver entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Cadaver" )
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Cadaver_id")
public class Cadaver extends Objeto {

	// Fields

	private Short edadAproximada;
	private String sexo;
	private Double estaturaAproximada;
	private String esCompleto;

	// Constructors

	/** default constructor */
	public Cadaver() {
	}




	@Column(name = "iEdadAproximada", precision = 4, scale = 0)
	public Short getEdadAproximada() {
		return this.edadAproximada;
	}

	public void setEdadAproximada(Short iedadAproximada) {
		this.edadAproximada = iedadAproximada;
	}

	@Column(name = "cSexo", length = 1)
	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String csexo) {
		this.sexo = csexo;
	}

	@Column(name = "dcEstaturaAproximada", precision = 5)
	public Double getEstaturaAproximada() {
		return this.estaturaAproximada;
	}

	public void setEstaturaAproximada(Double dcEstaturaAproximada) {
		this.estaturaAproximada = dcEstaturaAproximada;
	}

	@Column(name = "cEsCompleto", length = 10)
	public String getEsCompleto() {
		return this.esCompleto;
	}

	public void setEsCompleto(String cesCompleto) {
		this.esCompleto = cesCompleto;
	}

}