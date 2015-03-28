package mx.gob.segob.nsjp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



/**
 * Rol entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Rol", uniqueConstraints = @UniqueConstraint(columnNames = "cNombreRol"))
public class Rol implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8900024566340593763L;
	private Long rolId;
	private JerarquiaOrganizacional jerarquiaOrganizacional;
	private Rol rolPadre;
	private String nombreRol;
	private String descripcionRol;
	private Set<Proceso> procesos = new HashSet<Proceso>(0);
	private Set<UsuarioRol> usuarioRoles = new HashSet<UsuarioRol>(0);
	private Boolean esActivo;
	private ConfInstitucion institucionPertenece;
	private List<Modulo> modulos = new ArrayList<Modulo>();
	private Set<ConfActividadDocumento> confActividadDocumentos = new HashSet<ConfActividadDocumento>(0);
	private List<ElementoMenu> elementosMenu;

	// Constructors

	/** default constructor */
	public Rol() {
	}
	
	/** default constructor */
	public Rol(Long rolId) {
		this.rolId=rolId;
	}

	/** minimal constructor */
	public Rol(Long rolId, String cnombreRol, String cdescripcionRol) {
		this.rolId = rolId;
		this.nombreRol = cnombreRol;
		this.descripcionRol = cdescripcionRol;
	}

	/** full constructor */
	public Rol(Long rolId, String cnombreRol, String cdescripcionRol,
			Set<Proceso> procesos,Set<UsuarioRol> usuarioRols,JerarquiaOrganizacional jerarquiaOrganizacional) {
			this.rolId = rolId;
			this.nombreRol = cnombreRol;
			this.descripcionRol = cdescripcionRol;
		this.procesos = procesos;
		this.usuarioRoles = usuarioRols;
		this.jerarquiaOrganizacional = jerarquiaOrganizacional;
	}

	// Property accessors
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Rol_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getRolId() {
		return this.rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JerarquiaOrganizacional_Id")
	public JerarquiaOrganizacional getJerarquiaOrganizacional() {
		return this.jerarquiaOrganizacional;
	}

	public void setJerarquiaOrganizacional(
			JerarquiaOrganizacional jerarquiaOrganizacional) {
		this.jerarquiaOrganizacional = jerarquiaOrganizacional;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RolPadre_id")
	public Rol getRolPadre() {
		return this.rolPadre;
	}

	public void setRolPadre(Rol rolPadre) {
		this.rolPadre = rolPadre;
	}
	
	
	@Column(name = "cNombreRol", unique = true, nullable = false, length = 50)
	public String getNombreRol() {
		return this.nombreRol;
	}

	public void setNombreRol(String cnombreRol) {
		this.nombreRol = cnombreRol;
	}

	@Column(name = "cDescripcionRol", nullable = false, length = 200)
	public String getDescripcionRol() {
		return this.descripcionRol;
	}

	public void setDescripcionRol(String cdescripcionRol) {
		this.descripcionRol = cdescripcionRol;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ProcesoRol", joinColumns = { @JoinColumn(name = "Rol_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Proceso_id", nullable = false, updatable = false) })
	public Set<Proceso> getProcesos() {
		return this.procesos;
	}

	public void setProcesos(Set<Proceso> procesos) {
		this.procesos = procesos;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
	public Set<UsuarioRol> getUsuarioRoles() {
		return this.usuarioRoles;
	}

	public void setUsuarioRoles(Set<UsuarioRol> usuarioRols) {
		this.usuarioRoles = usuarioRols;
	}
	
	@Column(name = "bEsActivo", precision = 1, scale = 0)
    public Boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(Boolean esActivo) {
        if (esActivo == null)
            this.esActivo = true;
        else
            this.esActivo = esActivo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ConfInstitucion_id")
	public ConfInstitucion getInstitucionPertenece() {
		return institucionPertenece;
	}

	public void setInstitucionPertenece(ConfInstitucion institucionPertenece) {
		this.institucionPertenece = institucionPertenece;
	}

	/**
	 * @param arrayList the modulos to set
	 */
	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	/**
	 * @return the modulos
	 */
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "RolModulo",
    joinColumns = { @JoinColumn(name = "Rol_id", updatable = true) },
    inverseJoinColumns = { @JoinColumn(name = "Modulo_id", updatable = true) })
	public List<Modulo> getModulos() {
		return modulos;
	}
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "RolConfActividadDocumento",
	joinColumns = { @JoinColumn(name = "Rol_id", nullable = false, updatable = false) },
	inverseJoinColumns = { @JoinColumn(name = "ConfActividadDocumento_id", nullable = false, updatable = false) })
	public Set<ConfActividadDocumento> getConfActividadDocumentos() {
		return this.confActividadDocumentos;
	}

	public void setConfActividadDocumentos(
			Set<ConfActividadDocumento> confActividadDocumentos) {
		this.confActividadDocumentos = confActividadDocumentos;
	}

	/**
	 * @return the elementosMenu
	 */
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "RolElementoMenu",
    joinColumns = { @JoinColumn(name = "Rol_id", updatable = true) },
    inverseJoinColumns = { @JoinColumn(name = "ElementoMenu_id", updatable = true) })
	public List<ElementoMenu> getElementosMenu() {
		return elementosMenu;
	}

	/**
	 * @param elementosMenu the elementosMenu to set
	 */
	public void setElementosMenu(List<ElementoMenu> elementosMenu) {
		this.elementosMenu = elementosMenu;
	}
}