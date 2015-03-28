package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Proceso entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Proceso")
public class Proceso implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4575367485925506213L;
	private Long id;
	private String cnombreProceso;
	private String cdescripcionProceso;
	private Set<Subproceso> subprocesos = new HashSet<Subproceso>(0);
	private Set<Rol> roles = new HashSet<Rol>(0);

	// Constructors

	/** default constructor */
	public Proceso() {
	}

	/** minimal constructor */
	public Proceso(Long id, String cnombreProceso,
			String cdescripcionProceso) {
		this.id = id;
		this.cnombreProceso = cnombreProceso;
		this.cdescripcionProceso = cdescripcionProceso;
	}

	/** full constructor */
	public Proceso(Long id, String cnombreProceso,
			String cdescripcionProceso, Set<Subproceso> subprocesos,
			Set<Rol> roles) {
		this.id = id;
		this.cnombreProceso = cnombreProceso;
		this.cdescripcionProceso = cdescripcionProceso;
		this.subprocesos = subprocesos;
		this.roles = roles;
	}

	// Property accessors
	@Id
	@Column(name = "Proceso_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cNombreProceso", unique = true, nullable = false, length = 50)
	public String getCnombreProceso() {
		return this.cnombreProceso;
	}

	public void setCnombreProceso(String cnombreProceso) {
		this.cnombreProceso = cnombreProceso;
	}

	@Column(name = "cDescripcionProceso", nullable = false, length = 200)
	public String getCdescripcionProceso() {
		return this.cdescripcionProceso;
	}

	public void setCdescripcionProceso(String cdescripcionProceso) {
		this.cdescripcionProceso = cdescripcionProceso;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proceso")
	public Set<Subproceso> getSubprocesos() {
		return this.subprocesos;
	}

	public void setSubprocesos(Set<Subproceso> subprocesos) {
		this.subprocesos = subprocesos;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "ProcesoRol", 
				joinColumns = { @JoinColumn(name = "Proceso_id", nullable = false, updatable = false) }, 
				inverseJoinColumns = { @JoinColumn(name = "Rol_id", nullable = false, updatable = false) }
	)	
	public Set<Rol> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

}