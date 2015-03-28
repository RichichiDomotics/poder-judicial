package mx.gob.segob.nsjp.dto.usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.dto.ConfActividadDocumentoDTO;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;

public class RolDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8528739317757467468L;
	private Long rolId;
	private String nombreRol;
	private String descripcionRol;
	private ConfInstitucionDTO institucionPertenece;
	private Set<UsuarioRolDTO> usuarioRoles = new HashSet<UsuarioRolDTO>(0);
	private List<FuncionDTO> funciones;
	private List<ModuloDTO> modulos;
	private Boolean esPrincipal;
	private String nombreRolPadre;
	private JerarquiaOrganizacionalDTO jerarquiaOrganizacionalDTO;
	private RolDTO rolPadre; 
	private List<ConfActividadDocumentoDTO> confActividadDocumentoDTO;
	
	/**
	 * Constructor por Default
	 */
	public RolDTO(){
		
	}
	/**
	 * Constructor Mínimo
	 * @param rolId
	 */
	public RolDTO(Long rolId){
		this.rolId=rolId;
	}
	
	/**
	 * Regresa el valor de la propiedad rolId
	 * @return the rolId
	 */
	public Long getRolId() {
		return rolId;
	}
	
	
	
	/**
	 * Establece el valor de la propiedad rolId
	 * @param rolId valo rolId a almacenar
	 */
	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}
	
	/**
	 * Regresa el valor de la propiedad nombreRol
	 * @return the nombreRol
	 */
	public String getNombreRol() {
		return nombreRol;
	}
	
	/**
	 * Establece el valor de la propiedad nombreRol
	 * @param nombreRol valo nombreRol a almacenar
	 */
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	
	/**
	 * Regresa el valor de la propiedad descripcionRol
	 * @return the descripcionRol
	 */
	public String getDescripcionRol() {
		return descripcionRol;
	}
	
	/**
	 * Establece el valor de la propiedad descripcionRol
	 * @param descripcionRol valo descripcionRol a almacenar
	 */
	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}
	
	/**
	 * Regresa el valor de la propiedad usuarioRoles
	 * @return the usuarioRoles
	 */
	public Set<UsuarioRolDTO> getUsuarioRoles() {
		return usuarioRoles;
	}
	
	/**
	 * Establece el valor de la propiedad usuarioRoles
	 * @param usuarioRoles valo usuarioRoles a almacenar
	 */
	public void setUsuarioRoles(Set<UsuarioRolDTO> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

	/**
	 * Método de acceso al campo funciones.
	 * @return El valor del campo funciones
	 */
	public List<FuncionDTO> getFunciones() {
		return funciones;
	}

	/**
	 * Asigna el valor al campo funciones.
	 * @param funciones el valor funciones a asignar
	 */
	public void setFunciones(List<FuncionDTO> funciones) {
		this.funciones = funciones;
	}
	/**
	 * @return the modulos
	 */
	public List<ModuloDTO> getModulos() {
		return modulos;
	}
	/**
	 * @param modulos the modulos to set
	 */
	public void setModulos(List<ModuloDTO> modulos) {
		this.modulos = modulos;
	}
	/**
	 * @return the institucionPertenece
	 */
	public ConfInstitucionDTO getInstitucionPertenece() {
		return institucionPertenece;
	}
	/**
	 * @param institucionPertenece the institucionPertenece to set
	 */
	public void setInstitucionPertenece(ConfInstitucionDTO institucionPertenece) {
		this.institucionPertenece = institucionPertenece;
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

	/**
	 * @return the jerarquiaOrganizacionalDTO
	 */
	public JerarquiaOrganizacionalDTO getJerarquiaOrganizacionalDTO() {
		return jerarquiaOrganizacionalDTO;
	}

	/**
	 * @param jerarquiaOrganizacionalDTO the jerarquiaOrganizacionalDTO to set
	 */
	public void setJerarquiaOrganizacionalDTO(
			JerarquiaOrganizacionalDTO jerarquiaOrganizacionalDTO) {
		this.jerarquiaOrganizacionalDTO = jerarquiaOrganizacionalDTO;
	}	
	/**
	 * @return the rolPadre
	 */
	public RolDTO getRolPadre() {
		return rolPadre;
	}
	/**
	 * @param rolPadre the rolPadre to set
	 */
	public void setRolPadre(RolDTO rolPadre) {
		this.rolPadre = rolPadre;
	}
	public List<ConfActividadDocumentoDTO> getConfActividadDocumentoDTO() {
		return confActividadDocumentoDTO;
	}
	public void setConfActividadDocumentoDTO(
			List<ConfActividadDocumentoDTO> confActividadDocumentoDTO) {
		this.confActividadDocumentoDTO = confActividadDocumentoDTO;
	}
	/**
	 * @return the nombreRolPadre
	 */
	public String getNombreRolPadre() {
		return nombreRolPadre;
	}
	/**
	 * @param nombreRolPadre the nombreRolPadre to set
	 */
	public void setNombreRolPadre(String nombreRolPadre) {
		this.nombreRolPadre = nombreRolPadre;
	}
	
	
}
