package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Usuario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Usuario", uniqueConstraints = {
		@UniqueConstraint(columnNames = "iClaveFuncionario"),
		@UniqueConstraint(columnNames = "cClaveUsuario") })
public class Usuario implements java.io.Serializable {

	// Fields

	private Long usuarioId;
	private Funcionario funcionario;
	private String claveUsuario;
	private Set<Turno> turnos = new HashSet<Turno>(0);
	private Set<UsuarioRol> usuarioRoles = new HashSet<UsuarioRol>(0);
	private String pasword;
	private byte[] encriptPasword;
	private byte[] cLlave;
	private Integer iSesion;
	private Integer iIntentos;
	private String cIp;
	private Boolean esActivo;
	private String idSesionServer;

	// Constructors

	/** default constructor */
	public Usuario() {
	}

	/** minimal constructor */
	public Usuario(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	/** minimal constructor */
	public Usuario(Long usuarioId, Funcionario funcionario,
			String cclaveUsuario, String cpalabraSecreta) {
		this.usuarioId = usuarioId;
		this.funcionario = funcionario;
		this.claveUsuario = cclaveUsuario;
	}

	/** full constructor */
	public Usuario(Long usuarioId, Funcionario funcionario,
			String cclaveUsuario, String cpalabraSecreta, Set<Turno> turnos) {
		this.usuarioId = usuarioId;
		this.funcionario = funcionario;
		this.claveUsuario = cclaveUsuario;
		this.turnos = turnos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Usuario_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iClaveFuncionario", unique = true, nullable = false)
	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Column(name = "cClaveUsuario", unique = true, nullable = false, length = 10)
	public String getClaveUsuario() {
		return this.claveUsuario;
	}

	public void setClaveUsuario(String cclaveUsuario) {
		this.claveUsuario = cclaveUsuario;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Turno> getTurnos() {
		return this.turnos;
	}

	public void setTurnos(Set<Turno> turnos) {
		this.turnos = turnos;
	}

	/**
	 * Método de acceso al campo usuarioRoles.
	 * 
	 * @return El valor del campo usuarioRoles
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<UsuarioRol> getUsuarioRoles() {
		return usuarioRoles;
	}

	/**
	 * Asigna el valor al campo usuarioRoles.
	 * 
	 * @param usuarioRoles
	 *            el valor usuarioRoles a asignar
	 */
	public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

	@Transient
	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	/**
	 * @return the cLlave
	 */
	@Column(name = "cllave")
	@Basic
	public byte[] getcLlave() {
		return cLlave;
	}

	/**
	 * @return the encriptPasword
	 */
	@Column(name = "password")
	@Basic
	public byte[] getEncriptPasword() {
		return encriptPasword;
	}

	/**
	 * @param encriptPasword
	 *            the encriptPasword to set
	 */
	public void setEncriptPasword(byte[] encriptPasword) {
		this.encriptPasword = encriptPasword;
	}

	/**
	 * @param cLlave
	 *            the cLlave to set
	 */
	public void setcLlave(byte[] cLlave) {
		this.cLlave = cLlave;
	}

	/**
	 * @return the iSesion
	 */
	@Column(name = "iSesion")
	public Integer getiSesion() {
		return iSesion;
	}

	/**
	 * @param iSesion
	 *            the iSesion to set
	 */
	public void setiSesion(Integer iSesion) {
		this.iSesion = iSesion;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "iIntentos")
	public Integer getiIntentos() {
		return iIntentos;
	}

	/**
	 * 
	 * @param iIntentos
	 */
	public void setiIntentos(Integer iIntentos) {
		this.iIntentos = iIntentos;
	}

	/**
	 * 
	 * @return cIP the IP of the user
	 */
	@Column(name = "cIP")
	public String getcIp() {
		return cIp;
	}

	/**
	 * 
	 * @param cIp
	 */
	public void setcIp(String cIp) {
		this.cIp = cIp;
	}

	/**
	 * @return the esActivo
	 */
	@Column(name = "bEsActivo", precision = 1, scale = 0)
	public Boolean getEsActivo() {
		return esActivo;
	}

	/**
	 * @param esActivo the esActivo to set
	 */
	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}
	/**
	 * @return the idSesionServer
	 */
	@Column(name = "idSesionServer", length = 50)
	public String getIdSesionServer() {
		return this.idSesionServer;
	}
	/**
	 * @param idSesionServer the idSesionServer to set
	 */
	public void setIdSesionServer(String idSesionServer) {
		this.idSesionServer = idSesionServer;
	}

}