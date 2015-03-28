/**
 * Nombre del Programa : UsuarioDelegate.java
 * Autor                            : Jacob
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 21 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
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
package mx.gob.segob.nsjp.delegate.usuario.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.usuario.UsuarioDelegate;
import mx.gob.segob.nsjp.dto.elementomenu.ElementoMenuDTO;
import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioRolDTO;
import mx.gob.segob.nsjp.service.usuario.ConsultarFuncionService;
import mx.gob.segob.nsjp.service.usuario.ConsultarUsuarioPorClaveService;
import mx.gob.segob.nsjp.service.usuario.FuncionService;
import mx.gob.segob.nsjp.service.usuario.RolService;
import mx.gob.segob.nsjp.service.usuario.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del delegate para los metodos de comunicacion de Usuarios
 * entre la vista y los servicios.
 * 
 * @version 1.0
 * @author Jacob
 * 
 */
@Service("usuarioDelegate")
@Transactional
public class UsuarioDelegateImpl implements UsuarioDelegate {

	@Autowired
	private ConsultarUsuarioPorClaveService consultarUsuarioPorClaveService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ConsultarFuncionService consultarFuncionService;
	@Autowired
	private FuncionService funcionService;
	@Autowired
	private RolService rolService;

	@Override
	public UsuarioDTO consultarUsuarioPorClave(String claveUsuario)
			throws NSJPNegocioException {
		return consultarUsuarioPorClaveService
				.consultarUsuarioPorClaveService(claveUsuario);
	}

	public Long registrarUsuario(UsuarioDTO usuario, List<RolDTO> roles)
			throws NSJPNegocioException {
		return usuarioService.registrarUsuario(usuario, roles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.delegate.usuario.UsuarioDelegate#consultarRolesDeUsuario
	 * (java.lang.String)
	 */
	@Override
	public List<UsuarioRolDTO> consultarRolesDeUsuario(String claveUsuario)
			throws NSJPNegocioException {
		return consultarUsuarioPorClaveService
				.consultarRolesDeUsuario(claveUsuario);
	}

	public UsuarioDTO consultarUsuarioPorClaveFuncionario(Long claveFuncionario)
			throws NSJPNegocioException {
		return usuarioService
				.consultarUsuarioPorClaveFuncionario(claveFuncionario);
	}

	@Override
	public boolean actualizarUsuario(UsuarioDTO usuarioDTO, List<RolDTO> roles)
			throws NSJPNegocioException {
		return usuarioService.actualizarUsuario(usuarioDTO, roles);
	}

	@Override
	public List<FuncionDTO> consultarFuncionesByRol(RolDTO rolDTO)
			throws NSJPNegocioException {
		return consultarFuncionService.consultarFuncionesByRol(rolDTO);
	}

	@Override
	public void actualizarRol(RolDTO rolDTO) throws NSJPNegocioException {
		rolService.actualizarRol(rolDTO);
	}

	@Override
	public UsuarioDTO login(UsuarioDTO usr) throws NSJPNegocioException {
		return usuarioService.login(usr);
	}
	
	@Override
	public UsuarioDTO buscarUsuario(UsuarioDTO usr) throws NSJPNegocioException {
		return usuarioService.login(usr);
	}

	@Override
	public void logout(UsuarioDTO usr) throws NSJPNegocioException {
		usuarioService.logout(usr);
	}

	@Override
	public boolean cambiaContrasenia(UsuarioDTO usrOld, UsuarioDTO usrNew)
			throws NSJPNegocioException {
		
	//	List<UsuarioRolDTO> rolesUsuario = new ArrayList<UsuarioRolDTO>();
		boolean resp = false;
		usrOld=usuarioService.validaContrasenia(usrOld);
		if (usrOld!=null) {

			usrOld.setPassword(usrNew.getPassword());
			usrNew=usrOld;
			usuarioService.actualizarUsuarioBasico(usrNew);
			resp = true;
		}
		return resp;

	}

	@Override
	public String buscaParametroChat() throws NSJPNegocioException {
		return usuarioService.buscaParametroChat();
	}

	@Override
	public void agregaUsuariosChat() throws NSJPNegocioException {
		usuarioService.agregaUsuariosChat();
		
	}

	/**
     * Anula del sistema al usuario enviado como parametro, colocando la bandera de activo en falso. 
     * @author CesarAgustin
     * @param idUsuario
     * @return
     * @throws NSJPNegocioException
     */
	@Override
	public boolean anularUsuarioDeSistema(Long idUsuario)
			throws NSJPNegocioException {
		
		return usuarioService.anularUsuarioDeSistema(idUsuario);
	}
	/**
     * Busca si tiene una sesion activa el usuario. 
     * @author Jfernandez
     * @param idUsuario
     * @return
     * @throws NSJPNegocioException
     */
	@Override
	public boolean buscarUsuarioEnSesion(UsuarioDTO idUsuario)
			throws NSJPNegocioException {
		
		return usuarioService.buscarUsuarioEnSesion(idUsuario);
	}
	@Override
	public String buscarUsuarioEnSesionId(UsuarioDTO idUsuario)
			throws NSJPNegocioException {
		
		return usuarioService.buscarUsuarioEnSesionId(idUsuario);
	}

	@Override
	public UsuarioDTO obtenerUsuarioValidado(UsuarioDTO usuarioDTO)
			throws NSJPNegocioException {
		return usuarioService.obtenerUsuarioValidado(usuarioDTO);
	}
	
	@Override
	  public ElementoMenuDTO consultarMenuXUsuario (UsuarioDTO usuario) throws NSJPNegocioException {
		ElementoMenuDTO resp = null;
		
		
		return resp;
	}
	 
	@Override
		public UsuarioDTO validarSesionUsuario (UsuarioDTO usuarioDTO) throws NSJPNegocioException{
		return usuarioService.validarSesion(usuarioDTO);
	}
	
	@Override
	public boolean validarFuncionXUsuario (UsuarioDTO usrDTO, FuncionDTO fncDTO) throws NSJPNegocioException{
		boolean resp=false;
		usrDTO = usuarioService.consultarUsuarioXClaveUsuario(usrDTO);
		fncDTO = consultarFuncionService.consultarFuncionXNombre(fncDTO);
		if (usrDTO!=null && fncDTO!=null){
			resp = usuarioService.validarFuncionXUsuario(usrDTO, fncDTO);
		}
		return resp;
	}
	@Override
	public List<FuncionDTO> inventarioFunciones(FuncionDTO url,List<String> ext) throws NSJPNegocioException{
		return funcionService.inventariarFunciones(url, ext);
	}

}
