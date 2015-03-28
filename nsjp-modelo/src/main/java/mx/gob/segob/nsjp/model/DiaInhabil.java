package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DiaInhabil entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DiaInhabil")
public class DiaInhabil implements java.io.Serializable {

	// Fields

	private Long diaInhabilId;
	private String descripcion;
	private Short dia;
	private Short mes;

	// Constructors

	/** default constructor */
	public DiaInhabil() {
	}

	/** full constructor */
	public DiaInhabil(Long diaInhabilId, String cdescripcion, Short ndia,
			Short nmes) {
		this.diaInhabilId = diaInhabilId;
		this.descripcion = cdescripcion;
		this.dia = ndia;
		this.mes = nmes;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DiaInhabil_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getDiaInhabilId() {
		return this.diaInhabilId;
	}

	public void setDiaInhabilId(Long diaInhabilId) {
		this.diaInhabilId = diaInhabilId;
	}

	@Column(name = "cDescripcion", nullable = false, length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String cdescripcion) {
		this.descripcion = cdescripcion;
	}

	@Column(name = "nDia", nullable = false, precision = 4, scale = 0)
	public Short getDia() {
		return this.dia;
	}

	public void setDia(Short ndia) {
		this.dia = ndia;
	}

	@Column(name = "nMes", nullable = false, precision = 4, scale = 0)
	public Short getMes() {
		return this.mes;
	}

	public void setMes(Short nmes) {
		this.mes = nmes;
	}

}