package mx.gob.segob.nsjp.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UsuarioRol entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "UsuarioRol")
public class UsuarioRol implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4771959900061282220L;
	private UsuarioRolId id;
	private Rol rol;
	private Usuario usuario;
	private Date fechaInicio;
	private Date fechaFin;
	private Boolean esPrincipal;

	// Constructors

	/** default constructor */
	public UsuarioRol() {
	}


	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "usuarioId", column = @Column(name = "Usuario_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "rolId", column = @Column(name = "Rol_id", nullable = false, precision = 18, scale = 0)) })
	public UsuarioRolId getId() {
		return this.id;
	}

	public void setId(UsuarioRolId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Rol_id", nullable = false, insertable = false, updatable = false)
	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Usuario_id", nullable = false, insertable = false, updatable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "dFechaInicio", nullable = false, length = 23)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date dfechaInicio) {
		this.fechaInicio = dfechaInicio;
	}

	@Column(name = "dFechaFin", nullable = true, length = 23)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date dfechaFin) {
		this.fechaFin = dfechaFin;
	}

	@Column(name = "esPrincipal", nullable = true)
	public Boolean getEsPrincipal() {
		return esPrincipal;
	}


	public void setEsPrincipal(Boolean esPrincipal) {
		this.esPrincipal = esPrincipal;
	}
	
	

}