/**
 * Nombre del Programa : UsuarioDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 29 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de transferencia que representa a un usuario del sistema
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dto.usuario;

import java.util.Set;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;

/**
 * Objeto de transferencia que representa a un usuario del sistema
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class UsuarioDTO extends GenericDTO {

	/**
     * 
     */
	private static final long serialVersionUID = -6063549607645809908L;
	/**
	 * Identificado de BD
	 */
	private Long idUsuario;
	/**
	 * Clave del usuario (cuenta o login).
	 */
	private String claveUsuario;
	/**
	 * Password.
	 */
	private String password;
	/**
     * 
     */
	private FuncionarioDTO funcionario;

	private AreaDTO areaActual;

	private ConfInstitucionDTO institucion;
	private Integer iSesion;
	private Integer iIntentos;
	private String cIP;
	private String idSesion;
	private Boolean datosIncorrectos;
	private String rolActivo;
	
	/**
	 * Constructor default.
	 */
	public UsuarioDTO() {
		super();
	}
	@Deprecated
	public UsuarioRolDTO getRolPrincipal() {
		if (this.usuarioRoles == null || this.usuarioRoles.isEmpty()) {
			return null;
		}
		return this.usuarioRoles.iterator().next();
	}
	
	public UsuarioRolDTO getRolACtivo(){
		if(this.rolActivo!=null && !this.rolActivo.equals("")){
			for (UsuarioRolDTO usuarioRolDTO : this.usuarioRoles) {
				if(usuarioRolDTO.getRol().getNombreRol().equals(this.rolActivo)){
					return usuarioRolDTO;
				}
			}
		}
		return null;
	}

	public UsuarioDTO(Long idUsuario) {
		super();
		this.idUsuario = idUsuario;
	}

	/**
	 * Cosntructor mímino.
	 * 
	 * @param cveUsr
	 * @param pwd
	 */
	public UsuarioDTO(String cveUsr, String pwd, String ip) {
		super();
		this.claveUsuario = cveUsr;
		this.password = pwd;
		this.cIP=ip;
	}
	
	/**
	 * Segundo Cosntructor mímino.
	 * 
	 * @param cveUsr
	 * @param pwd
	 */
	public UsuarioDTO(String cveUsr, String pwd, String ip,String idSesion) {
		super();
		this.claveUsuario = cveUsr;
		this.password = pwd;
		this.cIP=ip;
		this.idSesion=idSesion;
	}

	/**
	 * Regresa el valor de la propiedad usuarioRoles
	 * 
	 * @return the usuarioRoles
	 */
	public Set<UsuarioRolDTO> getUsuarioRoles() {
		return usuarioRoles;
	}

	/**
	 * Establece el valor de la propiedad usuarioRoles
	 * 
	 * @param usuarioRoles
	 *            valo usuarioRoles a almacenar
	 */
	public void setUsuarioRoles(Set<UsuarioRolDTO> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

	boolean coordinador;

	private Set<UsuarioRolDTO> usuarioRoles = null;

	/**
	 * Método de acceso al campo udUsuario.
	 * 
	 * @return El valor del campo udUsuario
	 */
	public Long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Area al que pertence el usuario (funcionario.departamento.area)
	 * 
	 * @deprecated usar AreaDTO getAreaActual()
	 * @return
	 */
	public AreaDTO getArea() {
		if (funcionario != null && funcionario.getDepartamento() != null) {
			return funcionario.getDepartamento().getArea();
		}
		return null;
	}

	/**
	 * Asigna el valor al campo udUsuario.
	 * 
	 * @param udUsuario
	 *            el valor udUsuario a asignar
	 */
	public void setIdUsuario(Long udUsuario) {
		this.idUsuario = udUsuario;
	}

	/**
	 * Método de acceso al campo claveUsuario.
	 * 
	 * @return El valor del campo claveUsuario
	 */
	public String getClaveUsuario() {
		return claveUsuario;
	}

	/**
	 * Asigna el valor al campo claveUsuario.
	 * 
	 * @param claveUsuario
	 *            el valor claveUsuario a asignar
	 */
	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	/**
	 * Método de acceso al campo funcionario.
	 * 
	 * @return El valor del campo funcionario
	 */
	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	/**
	 * Asigna el valor al campo funcionario.
	 * 
	 * @param funcionario
	 *            el valor funcionario a asignar
	 */
	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * Método de acceso al campo areaActual.
	 * 
	 * @return El valor del campo areaActual
	 */
	public AreaDTO getAreaActual() {
		return areaActual;
	}

	/**
	 * Asigna el valor al campo areaActual.
	 * 
	 * @param areaActual
	 *            el valor areaActual a asignar
	 */
	public void setAreaActual(AreaDTO areaActual) {
		this.areaActual = areaActual;
	}

	/**
	 * Método de acceso al campo coordinador.
	 * 
	 * @return El valor del campo coordinador
	 */
	public boolean isCoordinador() {
		return coordinador;
	}

	/**
	 * Asigna el valor al campo coordinador.
	 * 
	 * @param coordinador
	 *            el valor coordinador a asignar
	 */
	public void setCoordinador(boolean coordinador) {
		this.coordinador = coordinador;
	}

	/**
	 * Método de acceso al campo institucion.
	 * 
	 * @return El valor del campo institucion
	 */
	public ConfInstitucionDTO getInstitucion() {
		return institucion;
	}

	/**
	 * Asigna el valor al campo institucion.
	 * 
	 * @param institucion
	 *            el valor institucion a asignar
	 */
	public void setInstitucion(ConfInstitucionDTO institucion) {
		this.institucion = institucion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the iSesion
	 */
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
	 * @return
	 */
	public String getcIP() {
		return cIP;
	}

	/**
	 * 
	 * @param cIP
	 */
	public void setcIP(String cIP) {
		this.cIP = cIP;
	}

	/**
	 * @return the idSesion
	 */
	public String getIdSesion() {
		return idSesion;
	}

	/**
	 * @param idSesion the idSesion to set
	 */
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}
	/**
	 * @return the rolActivo
	 */
	public String getRolActivo() {
		return rolActivo;
	}

	/**
	 * @param rolActivo the rolActivo to set
	 */
	public void setRolActivo(String rolActivo) {
		this.rolActivo = rolActivo;
	}
	


	/**
	 * @return the datosIncorrectos
	 */
	public Boolean getDatosIncorrectos() {
		return datosIncorrectos;
	}

	/**
	 * @param datosIncorrectos the datosIncorrectos to set
	 */
	public void setDatosIncorrectos(Boolean datosIncorrectos) {
		this.datosIncorrectos = datosIncorrectos;
	}
	
	
	
}
