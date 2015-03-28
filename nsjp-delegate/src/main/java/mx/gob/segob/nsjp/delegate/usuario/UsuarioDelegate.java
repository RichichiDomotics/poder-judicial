/**
 * Nombre del Programa : UsuarioDelegate.java
 * Autor                            : Jacob
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 21 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del delegate para los metodos de
 * comunicacion de los usuarios con la vista y la implementacion.
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
package mx.gob.segob.nsjp.delegate.usuario;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.elementomenu.ElementoMenuDTO;
import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioRolDTO;

/**
 * Contrato del delegate para los metodos de comunicacion de los usuarios con la
 * vista y la implementacion.
 * 
 * @version 1.0
 * @author Jacob
 * 
 */
public interface UsuarioDelegate {

    /**
     * Operación que realiza la funcionalidad de consultar los datos del usuario
     * de acuerdo a la clave capturada. Los datos que consulta son: - Nombre
     * completo del usuario - Institución - Puesto
     */
    UsuarioDTO consultarUsuarioPorClave(String claveUsuario)
            throws NSJPNegocioException;

    /**
     * Operación que valida que el usuario exista en el sistema y con
     * passcoincida.
     * 
     * @param usr
     *            Requeridos:
     *            <ul>
     *            <li>claveUsuario</li>
     *            <li>palabraSecreta</li>
     *            </ul>
     * @return Un <code>UsuarioDTO</code> para cuando el uuario existe y su
     *         palabra secreta coincide, <code>null</code> en caso contrario.
     * @throws NSJPNegocioException
     */
    UsuarioDTO login(UsuarioDTO usr) throws NSJPNegocioException;

    /**
     * Registra un nuevo usuario y le asocia los roles especificados.
     * 
     * @param usuario
     * @param roles
     * @return
     * @throws NSJPNegocioException
     */
    public Long registrarUsuario(UsuarioDTO usuario, List<RolDTO> roles)
            throws NSJPNegocioException;

    /**
     * Consulta los roles asignados actualmente a un usuario en base a la clave
     * del usuario
     * 
     * @param claveUsuario
     *            Clave del usuario a consultar sus roles
     * @return Lista de roles encontrados
     * @throws NSJPNegocioException
     */
    List<UsuarioRolDTO> consultarRolesDeUsuario(String claveUsuario)
            throws NSJPNegocioException;

    /**
     * 
     * @param claveFuncionario
     * @return
     * @throws NSJPNegocioException
     */
    public UsuarioDTO consultarUsuarioPorClaveFuncionario(Long claveFuncionario)
            throws NSJPNegocioException;

    public boolean actualizarUsuario(UsuarioDTO usuarioDTO, List<RolDTO> roles)
            throws NSJPNegocioException;

    /**
     * Consulta las funciones relacionadas al rol enviado como parametro
     * 
     * @author cesarAgustin
     * @param rol
     *            <li>rolId<li>
     * @return Lista de funciones
     * @throws NSJPNegocioException
     */
    public List<FuncionDTO> consultarFuncionesByRol(RolDTO rolDTO)
            throws NSJPNegocioException;

    /**
     * Realiza la actualizacion de un rol
     * 
     * @author cesarAgustin
     * @param rolDTO
     * @throws NSJPNegocioException
     */
    public void actualizarRol(RolDTO rolDTO) throws NSJPNegocioException;
    
    
    /**
     * Operación que al cerrar la secion modifica el atributo de sesion para poder
     * autenticarse despues
     * @param usr
     *            Requeridos:
     *            <ul>
     *            <li>claveUsuario</li>
     *            <li>idUsuario</li>
     *            </ul>
     * @throws NSJPNegocioException
     */
    void logout(UsuarioDTO usr) throws NSJPNegocioException;
    /**
     * Operación que cambiará la contrasenia de un usuario dado un usuario y contraseña correcta
     * @param usrOld
     * @param usrNew
     * @return
     * @throws NSJPNegocioException
     */
    public boolean cambiaContrasenia(UsuarioDTO usrOld, UsuarioDTO usrNew) throws NSJPNegocioException;
    
    public String buscaParametroChat()throws NSJPNegocioException;
    public void agregaUsuariosChat()throws NSJPNegocioException ;
    public ElementoMenuDTO consultarMenuXUsuario (UsuarioDTO usuario) throws NSJPNegocioException ;
    public boolean anularUsuarioDeSistema(Long idUsuario) throws NSJPNegocioException;
    boolean buscarUsuarioEnSesion(UsuarioDTO usuarioDTO) throws NSJPNegocioException;
    String buscarUsuarioEnSesionId(UsuarioDTO idUsuario)throws NSJPNegocioException;
    
    /**
	 * Apartir de la clave de usuario y passsword, se valida la autentificación.
	 * y se obtiene el Usuario con su funcionario.
	 * No se activa la sesión, ni se registra la información de acceso.
	 * Se requiere:
	 * 		ClaveUsuario
	 * 		Password
	 * @param usuarioDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public UsuarioDTO obtenerUsuarioValidado(UsuarioDTO usuarioDTO)
			throws NSJPNegocioException; 
	
	public UsuarioDTO buscarUsuario(UsuarioDTO usr) throws NSJPNegocioException;
	
	public UsuarioDTO validarSesionUsuario (UsuarioDTO usuarioDTO) throws NSJPNegocioException;
	
	public boolean validarFuncionXUsuario (UsuarioDTO usrDTO, FuncionDTO fncDTO) throws NSJPNegocioException;
	
	public List<FuncionDTO> inventarioFunciones(FuncionDTO url,List<String> ext) throws NSJPNegocioException;
	
}
