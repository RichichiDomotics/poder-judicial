package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Perfil entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Perfil" )
public class Perfil implements java.io.Serializable {

	// Fields

	private Long perfiId;
	private Involucrado involucrado;
	private String filias;
	private String fobias;
	private String adicciones;
	private String preferenciaSexual;
	private String paranoias;
	private String ansiedades;
	private String depresiones;
	private String perfilPsicologico;

	// Constructors

	/** default constructor */
	public Perfil() {
	}

	/** minimal constructor */
	public Perfil(Long perfiId, Involucrado involucrado) {
		this.perfiId = perfiId;
		this.involucrado = involucrado;
	}

	/** full constructor */
	public Perfil(Long perfiId, Involucrado involucrado, String filias,
			String fobias, String adicciones, String preferenciaSexual,
			String paranoias, String ansiedades, String depresiones,
			String perfilPsicologico) {
		this.perfiId = perfiId;
		this.involucrado = involucrado;
		this.filias = filias;
		this.fobias = fobias;
		this.adicciones = adicciones;
		this.preferenciaSexual = preferenciaSexual;
		this.paranoias = paranoias;
		this.ansiedades = ansiedades;
		this.depresiones = depresiones;
		this.perfilPsicologico = perfilPsicologico;
	}

	// Property accessors
	@Id
	@Column(name = "Perfil_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getPerfilId() {
		return this.perfiId;
	}

	public void setPerfilId(Long perfiId) {
		this.perfiId = perfiId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Involucrado_id", nullable = false)
	public Involucrado getInvolucrado() {
		return this.involucrado;
	}

	public void setInvolucrado(Involucrado involucrado) {
		this.involucrado = involucrado;
	}

	@Column(name = "cFilias", length = 300)
	public String getFilias() {
		return this.filias;
	}

	public void setFilias(String filias) {
		this.filias = filias;
	}

	@Column(name = "cFobias", length = 300)
	public String getFobias() {
		return this.fobias;
	}

	public void setFobias(String fobias) {
		this.fobias = fobias;
	}

	@Column(name = "cAdicciones", length = 300)
	public String getAdicciones() {
		return this.adicciones;
	}

	public void setAdicciones(String adicciones) {
		this.adicciones = adicciones;
	}

	@Column(name = "cPreferenciaSexual", length = 300)
	public String getPreferenciaSexual() {
		return this.preferenciaSexual;
	}

	public void setPreferenciaSexual(String preferenciaSexual) {
		this.preferenciaSexual = preferenciaSexual;
	}

	@Column(name = "cParanoias", length = 300)
	public String getParanoias() {
		return this.paranoias;
	}

	public void setParanoias(String paranoias) {
		this.paranoias = paranoias;
	}

	@Column(name = "cAnsiedades", length = 300)
	public String getAnsiedades() {
		return this.ansiedades;
	}

	public void setAnsiedades(String ansiedades) {
		this.ansiedades = ansiedades;
	}

	@Column(name = "cDepresiones", length = 300)
	public String getDepresiones() {
		return this.depresiones;
	}

	public void setDepresiones(String depresiones) {
		this.depresiones = depresiones;
	}

	@Column(name = "cPerfilPsicologico", length = 300)
	public String getPerfilPsicologico() {
		return this.perfilPsicologico;
	}

	public void setPerfilPsicologico(String perfilPsicologico) {
		this.perfilPsicologico = perfilPsicologico;
	}

}
