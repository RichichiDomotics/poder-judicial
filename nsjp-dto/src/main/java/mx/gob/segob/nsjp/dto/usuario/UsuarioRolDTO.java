package mx.gob.segob.nsjp.dto.usuario;

import java.util.Comparator;
import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

public class UsuarioRolDTO extends GenericDTO {

	private RolDTO rol;
	private UsuarioDTO usuario;
	private Date fechaInicio;
	private Date fechaFin;
	private Boolean esPrincipal;
	
	/**
	 * Regresa el valor de la propiedad rol
	 * @return the rol
	 */
	public RolDTO getRol() {
		return rol;
	}
	
	/**
	 * Establece el valor de la propiedad rol
	 * @param rol valo rol a almacenar
	 */
	public void setRol(RolDTO rol) {
		this.rol = rol;
	}

	/**
	 * Regresa el valor de la propiedad usuario
	 * @return the usuario
	 */
	public UsuarioDTO getUsuario() {
		return usuario;
	}

	/**
	 * Establece el valor de la propiedad usuario
	 * @param usuario valo usuario a almacenar
	 */
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	/**
	 * Regresa el valor de la propiedad fechaInicio
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Establece el valor de la propiedad fechaInicio
	 * @param fechaInicio valo fechaInicio a almacenar
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Regresa el valor de la propiedad fechaFin
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * Establece el valor de la propiedad fechaFin
	 * @param fechaFin valo fechaFin a almacenar
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the esPrincipal
	 */
	public Boolean getEsPrincipal() {
		return esPrincipal;
	}

	/**
	 * @param esPrincipal the esPrincipal to set
	 */
	public void setEsPrincipal(Boolean esPrincipal) {
		this.esPrincipal = esPrincipal;
	}
	@SuppressWarnings("rawtypes")
	public static final Comparator COMPARA_ROL = new Comparator() {
		public int compare(Object o1, Object o2) {
			if (o1 == o2 || !(o1 instanceof UsuarioRolDTO) || !(o2 instanceof UsuarioRolDTO)) {
				return 0;
			} else {
				UsuarioRolDTO urDTO1 = (UsuarioRolDTO) o1;
				UsuarioRolDTO urDTO2 = (UsuarioRolDTO) o2;
				if(urDTO2.getRol()!=null && urDTO1.getRol()!=null ){
					return urDTO1.getRol().getNombreRol().compareTo(urDTO2.getRol().getNombreRol());
				}
				return 0;
			}
		}
	};
	
}
