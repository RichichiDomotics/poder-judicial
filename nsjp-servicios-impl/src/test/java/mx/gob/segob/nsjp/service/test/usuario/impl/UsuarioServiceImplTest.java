/**
 * Nombre del Programa : UsuarioServiceImplTest.java
 * Autor                            : GustavoBP
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24/10/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.test.usuario.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.service.usuario.UsuarioService;

/**
 * Pruebas unitaroas de los servisios para adminsitrar usuario de la aplicación.
 * 
 * @version 1.0
 * @author GustavoBP
 * 
 */
public class UsuarioServiceImplTest extends BaseTestServicios<UsuarioService> {

	public void testActualizarUsuario() {

		Long idUsuario = 3L;
		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		usuarioDTO.setPassword("PalaM");
		usuarioDTO.setClaveUsuario("atpenal");

		List<RolDTO> roles = new ArrayList<RolDTO>();

		RolDTO rol1 = new RolDTO();
		rol1.setRolId(6L);
		roles.add(rol1);
		try {
			service.actualizarUsuario(usuarioDTO, roles);

		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}

	}

	public void testActualizarUsuarioBasico() {

		Long idUsuario = 3L;
		UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
		usuarioDTO.setPassword("Pa55w0rd");
		usuarioDTO.setClaveUsuario("atpenal");
		try {
			service.actualizarUsuarioBasico(usuarioDTO);

		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}

	}

	public void testRegistrarUsuario() {

		UsuarioDTO usuarioDTO = new UsuarioDTO();

		usuarioDTO.setClaveUsuario("NuevoAdmin");
		FuncionarioDTO funcionario = new FuncionarioDTO();
		funcionario.setClaveFuncionario(50L);
		usuarioDTO.setFuncionario(funcionario);
		usuarioDTO.setPassword("password");
		List<RolDTO> roles = new ArrayList<RolDTO>();
		RolDTO rol1 = new RolDTO();
		rol1.setRolId(36L);
		roles.add(rol1);
		try {
			service.registrarUsuario(usuarioDTO, roles);

		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			logger.debug(e.getMessage(), e);
		}

	}

	public void testValidarUsuario() {

		UsuarioDTO usuarioDTO = new UsuarioDTO("adminat", "Pa55w0rd",
				"127.0.0.2");

		try {
			usuarioDTO = service.validarUsuario(usuarioDTO);
			if (usuarioDTO != null) {
				if (usuarioDTO.getIdSesion() != null
						&& !usuarioDTO.getIdSesion().isEmpty()) {
					logger.info("La sesión del usuario: "
							+ usuarioDTO.getClaveUsuario() + " es: "
							+ service.validarSesion(usuarioDTO));
				} else {
					if (usuarioDTO.getiSesion() == -1) {
						logger.info("¡ALERTA! Intruso ¡ALERTA "
								+ usuarioDTO.getiIntentos());
					}
					if (usuarioDTO.getiSesion() == 1) {
						logger.info("Usuario y contraseña correctos ¡BIENVENIDO!");
					}
					if (usuarioDTO.getiSesion() == 2) {
						logger.info("Usuario bloqueado");
					}
					logger.info("usuario:" + usuarioDTO.getFuncionario());
					if (usuarioDTO.getFuncionario() != null) {
						logger.info("usuario:"
								+ usuarioDTO.getFuncionario()
										.getNombreCompleto());
					}
				}
			} else {
				logger.info("¡ALERTA! Intruso ¡ALERTA!");
			}

		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			logger.debug(e.getMessage(), e);
		}
	}

	public void testValidaDesbloqueo() {
		try {
			service.desbloquearUsuario();
			assertTrue(true);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			logger.debug(e.getMessage(), e);
			assertTrue(false);
		}
	}

	public void testCambiaContrasenia() {
		try {
			UsuarioDTO usrDTOOld = new UsuarioDTO("atpenal", "Pa55w0rd",
					"127.0.0.1");

			List<RolDTO> roles = new ArrayList<RolDTO>();
			Long idUsuario = 3L;
			UsuarioDTO usrDTONew = new UsuarioDTO(idUsuario);
			usrDTONew.setClaveUsuario("atpenal");
			usrDTONew.setPassword("&123abc#");
			RolDTO rol1 = new RolDTO();
			rol1.setRolId(6L);

			roles.add(rol1);
			if (service.validaContrasenia(usrDTOOld) != null) {
				service.actualizarUsuario(usrDTONew, roles);
				logger.info("Contrasenia modificada con exito");
			} else {
				logger.info("****** ERROR LA CONTRASEÑA NO COINCIDE ******");
			}

			assertTrue(true);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			logger.debug(e.getMessage(), e);
			assertTrue(false);
		}
	}

	public void testObtenerUsuarioValidado() {

		UsuarioDTO usuarioDTO = new UsuarioDTO("atpenal12", "Pa55w0rd", null);

		try {
			usuarioDTO = service.obtenerUsuarioValidado(usuarioDTO);
			if (usuarioDTO != null) {
				logger.info("usuario:" + usuarioDTO.getFuncionario());
				if (usuarioDTO.getFuncionario() != null) {
					logger.info("usuario:"
							+ usuarioDTO.getFuncionario().getNombreCompleto());
					logger.info("usuario:" + usuarioDTO.getInstitucion());
					logger.info("usuario:"
							+ usuarioDTO.getFuncionario().getInstitucion());
					logger.info("usuario:"
							+ usuarioDTO.getFuncionario().getInstitucion()
									.getNombreInst());
				}
			} else {
				logger.info("¡ALERTA! Intruso ¡ALERTA!");
			}

		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			logger.debug(e.getMessage(), e);
		}
	}

	public void testValidarSesion() {
		UsuarioDTO usuarioDTO = new UsuarioDTO("facilitador", "Pa55w0rd", null);
		try {
			usuarioDTO = service.validarSesion(usuarioDTO);
			if (usuarioDTO != null) {
				logger.info("La sesión del usuario: "
						+ usuarioDTO.getClaveUsuario() + " es: "
						+ usuarioDTO.getIdSesion());
			} else {
				logger.info("usuario sin sesión inciada");
			}

		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			logger.debug(e.getMessage(), e);
		}
	}

	public void testValidarFuncionXUsuario() {
		UsuarioDTO usuarioDTO = new UsuarioDTO(103L);
		FuncionDTO funcionDTO = new FuncionDTO(10L);
		try {
			if (service.validarFuncionXUsuario(usuarioDTO, funcionDTO)) {
				logger.info("Función Facultada");
			} else {
				logger.info("¡Función NO Facultada!");
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

	public void testConsultarUsuarioXClaveUsuario() {
		UsuarioDTO usuarioDTO = new UsuarioDTO("luiso2", "Pa55w0rd", null);
		try {
			usuarioDTO = service.consultarUsuarioXClaveUsuario(usuarioDTO);
			if (usuarioDTO != null) {
				logger.info("Id Usuario: " + usuarioDTO.getIdUsuario());
			}else{
				logger.info("Usuario no encontrado");
			}
				
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}

	}
}
