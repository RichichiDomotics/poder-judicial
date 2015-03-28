/**
 * Nombre del Programa  : UsuarioServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 27 Jul 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Servicio que administra la información de Usuarios
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.usuario.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mx.gob.segob.nsjp.comun.constants.KeygenencripDTO;
import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.EncriptadorUtil;
import mx.gob.segob.nsjp.dao.chat.BlowFish;
import mx.gob.segob.nsjp.dao.chat.OfGroupUserDAO;
import mx.gob.segob.nsjp.dao.chat.OfPropertyDAO;
import mx.gob.segob.nsjp.dao.chat.OfUserDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.institucion.ConfInstitucionDAO;
import mx.gob.segob.nsjp.dao.parametro.ParametroDAO;
import mx.gob.segob.nsjp.dao.usuario.FuncionDAO;
import mx.gob.segob.nsjp.dao.usuario.UsuarioDAO;
import mx.gob.segob.nsjp.dao.usuario.UsuarioRolDAO;
import mx.gob.segob.nsjp.dto.elementomenu.ElementoMenuDTO;
import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Funcion;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.OfGroupUser;
import mx.gob.segob.nsjp.model.OfGroupUserId;
import mx.gob.segob.nsjp.model.OfProperty;
import mx.gob.segob.nsjp.model.OfUser;
import mx.gob.segob.nsjp.model.Parametro;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.UsuarioRol;
import mx.gob.segob.nsjp.model.UsuarioRolId;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.ConfInstitucionTransformer;
import mx.gob.segob.nsjp.service.usuario.UsuarioService;
import mx.gob.segob.nsjp.service.usuario.impl.transformer.FuncionTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio que administra la información de Usuarios
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private UsuarioRolDAO usuarioRolDAO;
	@Autowired
	private ConfInstitucionDAO intitucionDao;
	@Autowired
	private OfUserDAO ofUserDAO;
	@Autowired
	private OfGroupUserDAO ofGroupUserDAO;
	@Autowired
	private OfPropertyDAO ofPropertyDAO;
	@Autowired
	private ParametroDAO parametroDAO;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private FuncionDAO funcionDAO;

	private static final Logger logger = Logger
			.getLogger(UsuarioServiceImpl.class);

	@Override
	public Long registrarUsuario(UsuarioDTO usuarioDTO, List<RolDTO> roles)
			throws NSJPNegocioException {
		if (usuarioDTO == null && (roles != null && !roles.isEmpty())) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		Usuario usuario = UsuarioTransformer.transformarDTO(usuarioDTO);
		Long id = 0L;

		// Servicio que permite guardar un usuario
		// Long id =usuarioDAO.registrarUsuario(usuario).getUsuarioId();
		KeygenencripDTO keygenencripDTO = EncriptadorUtil.encriptador(usuario
				.getPasword());
		usuario.setEncriptPasword(keygenencripDTO.getEncrypted());
		usuario.setcLlave(keygenencripDTO.getRaw());
		usuario.setiSesion(0);
		usuario.setiIntentos(0);
		usuario.setEsActivo(Boolean.TRUE);

		Usuario usuarioConsulta = usuarioDAO
				.consultarUsuarioPorClaveService(usuario.getClaveUsuario());

		if (usuarioConsulta == null)
			id = usuarioDAO.create(usuario);

		if (id != 0) {
			OfUser ofUser = new OfUser();
			ofUser.setCreationDate("0");
			ofUser.setModificationDate("0");
			ofUser.setEmail("test@ultrasist.com.mx");
			ofUser.setUsername(usuario.getClaveUsuario());
			// ofUser.setPlainPassword(usuario.getPasword());
			OfProperty ofProperty = ofPropertyDAO.findById("passwordKey");

			if (ofProperty != null) {
				BlowFish blowFish = new BlowFish(ofProperty.getPropValue());
				String encryptedPassword = blowFish.encryptString(usuario
						.getPasword());
				ofUser.setEncryptedPassword(encryptedPassword);
				ofUser.setName(usuario.getFuncionario().getNombreCompleto());
				String idOfUser = ofUserDAO.create(ofUser);
				OfGroupUser ofGroupUser = new OfGroupUser();
				OfGroupUserId ofGroupUserId = new OfGroupUserId();
				ofGroupUserId.setAdministrator(0);
				ofGroupUserId.setUsername(idOfUser);
				ConfInstitucion confInstitucion = intitucionDao
						.consultarIntitucionActual();
				String nombreIstitucion = confInstitucion.getNombreInst();
				ofGroupUserId.setGroupName(nombreIstitucion);
				ofGroupUser.setId(ofGroupUserId);
				OfGroupUserId idgruop = ofGroupUserDAO.create(ofGroupUser);
				// OfGroupUserId idgruop2=ofGroupUserDAO.create(ofGroupUser);
			}

			usuarioDTO.setIdUsuario(id);
			for (RolDTO rol : roles) {
				asociarRolAUsuario(usuarioDTO, rol);
			}
		}
		return id;
	}

	@Override
	public boolean actualizarUsuario(UsuarioDTO usuarioDTO, List<RolDTO> roles)
			throws NSJPNegocioException {
		boolean permisible = false;

		if (usuarioDTO == null || roles == null || roles.isEmpty()
				|| usuarioDTO.getClaveUsuario() == null
				|| usuarioDTO.getClaveUsuario().trim().isEmpty()
				|| usuarioDTO.getFuncionario() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		// LYO
		// Manteniendo unicidad: 1 - funcionario <--> 1 - Usuario
		Usuario usuarioConsulta = usuarioDAO
				.consultarUsuarioPorClaveService(usuarioDTO.getClaveUsuario());

		if (usuarioConsulta == null)
			permisible = true;
		else {
			if (usuarioConsulta.getFuncionario() != null
					&& usuarioConsulta.getFuncionario().getClaveFuncionario() != null) {
				if (usuarioConsulta
						.getFuncionario()
						.getClaveFuncionario()
						.equals(usuarioDTO.getFuncionario()
								.getClaveFuncionario())) {
					permisible = true;
				}
			}
		}

		if (permisible == true) {

			Usuario usuario = usuarioDAO.read(usuarioDTO.getIdUsuario());

			if (usuario == null || usuario.getUsuarioId() == null)
				throw new NSJPNegocioException(
						CodigoError.INFORMACION_PARAMETROS_ERRONEA);

			if (usuarioDTO.getPassword() != null
					&& !usuarioDTO.getPassword().trim().isEmpty())
				usuario.setPasword(usuarioDTO.getPassword());

			if (usuarioDTO.getClaveUsuario() != null
					&& !usuarioDTO.getClaveUsuario().trim().isEmpty())
				usuario.setClaveUsuario(usuarioDTO.getClaveUsuario());

			// LA (Lección aprendida)si se obtenía los UsuariosRoles por la
			// entidad
			// consultada = usuario.getUsuarioRoles();
			// me marcaba un error al eliminar las relaciones de cruce. Porque
			// se
			// tenía en sesión
			// al usuario y obviamente con su relación.

			List<UsuarioRol> rolesUsuario = usuarioRolDAO
					.consultarRolesDeUsuario(usuario.getClaveUsuario()); //

			// obtener los id de la entidad compuesta.. para ser eliminados

			UsuarioRolId[] ids = new UsuarioRolId[rolesUsuario.size()];

			for (int cont = 0; cont < rolesUsuario.size(); cont++) {
				UsuarioRolId delId = rolesUsuario.get(cont).getId();
				ids[cont] = delId;
				logger.info("ID " + cont + " :" + ids[cont].getRolId());
			}

			logger.info("Antes de borrar los roles");
			usuarioRolDAO.deleteAll(ids);
			logger.info("Después de borrar los roles");
			KeygenencripDTO keygenencripDTO = EncriptadorUtil
					.encriptador(usuarioDTO.getPassword());
			usuario.setcLlave(keygenencripDTO.getRaw());
			usuario.setEncriptPasword(keygenencripDTO.getEncrypted());
			usuario.setiSesion(0);
			usuario.setiIntentos(0);

			// Actualizar los nuevos datos del usuario
			logger.info("Antes de actualizar el usuario y ya se encriptó");
			usuarioDAO.saveOrUpdate(usuario);
			logger.info("Después de actualizar el usuario y ya se encriptó");
			logger.info("Actualización realizada con éxito");

			// Permite actualizar la contrasenia de un usuaria
			// usuarioDAO.actualizarPasswordUsuario(usuario);

			// Asociar los nuevos roles
			for (RolDTO rol : roles) {
				asociarRolAUsuario(usuarioDTO, rol);
			}
		}
		return permisible;
	}

	public void asociarRolAUsuario(UsuarioDTO usario, RolDTO rol)
			throws NSJPNegocioException {
		UsuarioRol usuarioRol = new UsuarioRol();
		UsuarioRolId id = new UsuarioRolId();
		id.setRolId(rol.getRolId());
		id.setUsuarioId(usario.getIdUsuario());
		usuarioRol.setId(id);
		usuarioRol.setFechaInicio(Calendar.getInstance().getTime());
		usuarioRol.setFechaFin(Calendar.getInstance().getTime());
		usuarioRol.setEsPrincipal(rol.getEsPrincipal());
		usuarioRolDAO.create(usuarioRol);
	}

	@Override
	public UsuarioDTO consultarUsuarioPorClaveFuncionario(Long claveFuncionario)
			throws NSJPNegocioException {
		UsuarioDTO usuario = new UsuarioDTO();
		Usuario user = usuarioDAO
				.consultarUsuarioPorClaveFuncionario(claveFuncionario);
		usuario = UsuarioTransformer.transformarUsuario(user);
		return usuario;
	}

	@Override
	public UsuarioDTO login(UsuarioDTO usr) throws NSJPNegocioException {
		final UsuarioDTO response = validarUsuario(usr);
		if (response != null) {
			ConfInstitucion intActul = this.intitucionDao
					.consultarInsitucionActual();
			response.setInstitucion(ConfInstitucionTransformer
					.transformarInstitucion(intActul));
		}
		return response;
	}

	/**
	 * usuarioDTO es el usuario que se quiere validar
	 */
	@Override
	public UsuarioDTO buscaUsuario(UsuarioDTO usuarioDTO)
			throws NSJPNegocioException {

		Usuario usuario2 = usuarioDAO
				.consultarUsuarioPorClaveService(usuarioDTO.getClaveUsuario());
		KeygenencripDTO keygenencripDTO = new KeygenencripDTO(
				usuario2.getcLlave(), usuario2.getEncriptPasword());
		if (EncriptadorUtil.comparador(usuarioDTO.getPassword(),
				keygenencripDTO)) {

			return UsuarioTransformer.transformarUsuario(usuario2);
		}
		return null;
	}

	/**
	 * usuarioDTO es el usuario que se quiere validar
	 */
	@Override
	public UsuarioDTO validarUsuario(UsuarioDTO usuarioDTO)
			throws NSJPNegocioException {
		UsuarioDTO respDto = null;
		Usuario resp = null;
		// Usuario a validar
		Usuario usuario1 = new Usuario();
		// Usuario que se consultrará
		Usuario usuario2 = new Usuario();
		// Se valida que no venga nulo el usuario
		if (usuarioDTO != null) {
			// Se hace la transformación del DTO al Entity

			usuario1 = UsuarioTransformer.transformarDTO(usuarioDTO);
			// Se consulta la clave de usuario en la BD para poder hacer la
			// comparación
			usuario2 = usuarioDAO.consultarUsuarioPorClaveService(usuario1
					.getClaveUsuario());

			// Existe el usuario
			if (usuario2 != null) {
				// Valida si no hay una sesión previa en el sistema registrada
				// Grava el id de la sesion activa del server
				logger.info("######IDSESION:" + usuarioDTO.getIdSesion());
				usuario2.setIdSesionServer(usuarioDTO.getIdSesion());
				usuarioDAO.update(usuario2);

				respDto = UsuarioTransformer.transformarUsuario(usuario2);
				// usuario2=
				// usuarioDAO.consultarUsuarioPorClaveFuncionario(Long.valueOf(usuario1.getClaveUsuario()));
				// Se construye el DTO que contendrá la llave con la que se
				// cifró y contraseña CIFRADA
				KeygenencripDTO keygenencripDTO = new KeygenencripDTO(
						usuario2.getcLlave(), usuario2.getEncriptPasword());
				// El método compara con una contraseña NO CIFRADA y un DTO
				// que
				// contiene lo que se explicó en el paso anteior
				// Usuario y Contraseña Correcta
				if (EncriptadorUtil.comparador(usuario1.getPasword(),
						keygenencripDTO)) {
					// Manejo de Sesión donde 0= Sin sesión 1= Sesión
					// iniciada
					// 2= Sesión duplicada
					if (usuario2.getiSesion() < 2) {
						if (usuario2.getiSesion() < 1) {
							usuario2.setiSesion(1);
							usuario2.setcIp(usuario1.getcIp());

						} else {
							// TODO Cambiar la parte de las IP's
							// Si la sesión está en 1 y la ip del usuario es
							// diferente que la anterior registrada
							// Se bloquea la cuenta, en caso contrario no se
							// hace nada con la cuenta usuario
							if (!usuario2.getcIp().equals(usuario1.getcIp()))
								usuario2.setiSesion(usuario2.getiSesion() + 1);

						}
						usuario2.setiIntentos(0);
						respDto = UsuarioTransformer
								.transformarUsuario(usuario2);

					}

				}
				// Usuario correcto, contraseña no correcta
				else {
					// No hay sesión iniciada
					if (usuario2.getiSesion() <= 0) {
						if (usuario2.getiIntentos() < 2) {
							logger.info("luigitel: los intentos son:"
									+ usuario2.getiIntentos());
							usuario2.setiIntentos(respDto.getiIntentos() + 1);
							usuario2.setiSesion(-1);
							respDto = UsuarioTransformer
									.transformarUsuario(usuario2);
						}
						// Excede el número de intentos y se bloquea la
						// cuenta
						else {
							usuario2.setiSesion(2);
							usuario2.setiIntentos(0);
							respDto = UsuarioTransformer
									.transformarUsuario(usuario2);
						}
					}
					// Existe un usuario en sesión
					if (respDto.getiSesion() == 1) {
						respDto.setiSesion(-1);
					}

				}

			}
		}
		return respDto;
	}

	@Override
	public void logout(UsuarioDTO usr) throws NSJPNegocioException {
		if (usr == null || usr.getIdUsuario() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		logger.info("Logout :" + usr.getIdUsuario());
		logger.info("Logout 1");
		Usuario usuario = usuarioDAO.read(usr.getIdUsuario());
		logger.info("Logout 2");
		usuario.setiSesion(0);
		usuario.setIdSesionServer("");
		logger.info("Logout 3");
		usuarioDAO.saveOrUpdate(usuario);
		logger.info("Logout 4");
	}

	public void desbloquearUsuario() throws NSJPNegocioException {
		try {
			usuarioDAO.actualizarSesionUsuario();
		} catch (Exception e) {
			throw new NSJPNegocioException(
					CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO);
		}
	}

	@Override
	public UsuarioDTO validaContrasenia(UsuarioDTO usuarioDTO)
			throws NSJPNegocioException {
		UsuarioDTO resp = null;
		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		logger.info("------------ Servicio Validador de Contraseña----------------");
		if (usuarioDTO != null) {
			usuario1 = UsuarioTransformer.transformarDTO(usuarioDTO);
			// Se consulta la clave de usuario en la BD para poder hacer la
			// comparación
			usuario2 = usuarioDAO.consultarUsuarioPorClaveService(usuario1
					.getClaveUsuario());
			if (usuario2 != null) {
				KeygenencripDTO keygenencripDTO = new KeygenencripDTO(
						usuario2.getcLlave(), usuario2.getEncriptPasword());
				if (EncriptadorUtil.comparador(usuario1.getPasword(),
						keygenencripDTO)) {
					resp = UsuarioTransformer.transformarUsuario(usuario2);
				}
			}
		}
		if (resp != null)
			logger.info("------------ Servicio Validador de Contraseña Aprobado----------------"
					+ usuarioDTO.getIdUsuario());
		else
			logger.info("------------ Servicio Validador de Contraseña Rechazado----------------");
		return resp;
	}

	/**
	 * Operación que recibe un usuario con contraseña sin encriptar y regresa un
	 * objeto usuario con contraseña encriptada
	 * 
	 * @param usuario
	 * @return
	 */
	Usuario encriptaUsuario(Usuario usuario) {
		Usuario resp = new Usuario();
		resp = usuario;
		KeygenencripDTO keygenencripDTO = EncriptadorUtil.encriptador(resp
				.getPasword());
		resp.setcLlave(keygenencripDTO.getRaw());
		resp.setEncriptPasword(keygenencripDTO.getEncrypted());
		return resp;
	}

	@Override
	public void actualizarUsuarioBasico(UsuarioDTO usuarioDTO)
			throws NSJPNegocioException {
		if (usuarioDTO == null || usuarioDTO.getClaveUsuario() == null
				|| usuarioDTO.getClaveUsuario().trim().isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		logger.info("usuarioDTO.ID:" + usuarioDTO.getIdUsuario());
		Usuario usuario = usuarioDAO.read(usuarioDTO.getIdUsuario());
		if (usuario == null || usuario.getUsuarioId() == null)
			throw new NSJPNegocioException(
					CodigoError.INFORMACION_PARAMETROS_ERRONEA);

		if (usuarioDTO.getPassword() != null
				&& !usuarioDTO.getPassword().trim().isEmpty())
			usuario.setPasword(usuarioDTO.getPassword());
		if (usuarioDTO.getClaveUsuario() != null
				&& !usuarioDTO.getClaveUsuario().trim().isEmpty())
			usuario.setClaveUsuario(usuarioDTO.getClaveUsuario());

		// LA (Lección aprendida)si se obtenía los UsuariosRoles por la entidad
		// consultada = usuario.getUsuarioRoles();
		// me marcaba un error al eliminar las relaciones de cruce. Porque se
		// tenía en sesión
		// al usuario y obviamente con su relación.

		// colocar codigo aki
		OfUser ofUser = ofUserDAO.read(usuario.getClaveUsuario());
		OfProperty ofProperty = ofPropertyDAO.findById("passwordKey");
		if (ofProperty != null) {
			BlowFish blowFish = new BlowFish(ofProperty.getPropValue());
			String encryptedPassword = blowFish.encryptString(usuarioDTO
					.getPassword());
			ofUser.setEncryptedPassword(encryptedPassword);
			ofUserDAO.saveOrUpdate(ofUser);
		}
		KeygenencripDTO keygenencripDTO = EncriptadorUtil
				.encriptador(usuarioDTO.getPassword());
		usuario.setcLlave(keygenencripDTO.getRaw());
		usuario.setEncriptPasword(keygenencripDTO.getEncrypted());
		usuario.setiSesion(0);
		usuario.setiIntentos(0);
		// Actualizar los nuevos datos del usuario
		logger.info("Antes de actualizar el usuario y ya se encriptó");
		usuarioDAO.saveOrUpdate(usuario);
		logger.info("Después de actualizar el usuario y ya se encriptó");
		logger.info("Actualización realizada con éxito");
		// Permite actualizar la contrasenia de un usuaria
		// usuarioDAO.actualizarPasswordUsuario(usuario);

	}

	@Override
	public void agregaUsuariosChat() throws NSJPNegocioException {
		try {
			logger.info("------------ Servicio agregaUsuariosChat Inicio----------------");
			List<Usuario> lisUsuarios = usuarioDAO
					.findAll("claveUsuario", true);
			logger.info("------------ Servicio agregaUsuariosChat Inicio----------------1.1");
			OfProperty ofProperty = ofPropertyDAO.findById("passwordKey");
			if (ofProperty != null) {
				logger.info("------------ Servicio agregaUsuariosChat Inicio----------------1.2");
				BlowFish blowFish = new BlowFish(ofProperty.getPropValue());
				logger.info("------------ Servicio agregaUsuariosChat Inicio----------------1");
				for (Usuario usuario : lisUsuarios) {
					try {
						KeygenencripDTO keygenencripDTO = new KeygenencripDTO();
						keygenencripDTO.setEncrypted(usuario
								.getEncriptPasword());
						keygenencripDTO.setRaw(usuario.getcLlave());
						String passwordDesencriptado = EncriptadorUtil
								.desencripta(keygenencripDTO);
						String encryptedPassword = blowFish
								.encryptString(passwordDesencriptado);
						logger.info("------------ Servicio agregaUsuariosChat Inicio----------------2");
						OfUser ofUser = null;
						try {
							ofUser = ofUserDAO.read(usuario.getClaveUsuario());
							logger.info("------------ Servicio agregaUsuariosChat Inicio----------------3");
							if (ofUser == null) {
								ofUser = new OfUser();
								ofUser.setUsername(usuario.getClaveUsuario());
								ofUser.setEmail("segob@segob.com");
								ofUser.setName(usuario.getFuncionario()
										.getNombreCompleto());
								ofUser.setCreationDate("0");
								ofUser.setModificationDate("0");
							}

						} catch (Exception e) {
							ofUser = new OfUser();
							ofUser.setUsername(usuario.getClaveUsuario());
							ofUser.setEmail("segob@segob.com");
							ofUser.setName(usuario.getFuncionario()
									.getNombreCompleto());
							ofUser.setCreationDate("0");
							ofUser.setModificationDate("0");
						}
						logger.info("------------ Servicio agregaUsuariosChat Inicio----------------4");
						ofUser.setEncryptedPassword(encryptedPassword);
						ofUserDAO.saveOrUpdate(ofUser);
						OfGroupUser ofGroupUser = new OfGroupUser();
						OfGroupUserId ofGroupUserId = new OfGroupUserId();
						ofGroupUserId.setAdministrator(0);
						ofGroupUserId.setUsername(ofUser.getUsername());
						ConfInstitucion confInstitucion = intitucionDao
								.consultarIntitucionActual();
						String nombreIstitucion = confInstitucion
								.getNombreInst();
						ofGroupUserId.setGroupName(nombreIstitucion);
						ofGroupUser.setId(ofGroupUserId);
						ofGroupUserDAO.saveOrUpdate(ofGroupUser);
						logger.info("------------ Servicio agregaUsuariosChat Inicio----------------5");
					} catch (Exception e) {
						logger.info("------------ Servicio agregaUsuariosChat Error----------------"
								+ e.getStackTrace());
						logger.info("------------Usurio no modificado----------------"
								+ usuario.getClaveUsuario());
					}
				}
				Parametro parametro = parametroDAO
						.obtenerPorClave(Parametros.USUARIOS_CHAT);
				if (parametro == null) {
					parametro = new Parametro();
				}
				parametro.setClave("USUARIOS_CHAT");
				parametro
						.setDescripcion("Bandera que ocualta Funcion de Agregar usuarios ya creados al chat");
				parametro.setTipoValor("0");
				parametro.setValor("1");
				parametroDAO.saveOrUpdate(parametro);
				logger.info("------------ Servicio agregaUsuariosChat fin----------------");
			}
		} catch (Exception e) {
			logger.info("------------ Servicio agregaUsuariosChat Error----------------"
					+ e.getCause());
		}
	}

	public String buscaParametroChat() throws NSJPNegocioException {
		try {
			Parametro parametro = parametroDAO
					.obtenerPorClave(Parametros.USUARIOS_CHAT);
			return parametro.getValor();
		} catch (Exception e) {
			logger.info("------------ Servicio buscaParametroChat Error----------------");
			return "0";
		}
	}

	@Override
	public ElementoMenuDTO consultarMenuXUsuario(UsuarioDTO usuario)
			throws NSJPNegocioException {
		ElementoMenuDTO resp = null;

		return resp;
	}

	/**
	 * Anula del sistema al usuario enviado como parametro, colocando la bandera
	 * de activo en falso.
	 * 
	 * @author CesarAgustin
	 * @param idUsuario
	 * @return
	 * @throws NSJPNegocioException
	 */
	@Override
	public boolean anularUsuarioDeSistema(Long idUsuario) {

		Usuario usuario = new Usuario();
		usuario = usuarioDAO.read(idUsuario);

		List<NumeroExpediente> lstNumExps = new ArrayList<NumeroExpediente>();

		lstNumExps = numeroExpedienteDAO.obtenerNumExpPorFuncionarioYEstatus(
				usuario.getFuncionario().getClaveFuncionario(),
				EstatusExpediente.ABIERTO.getValorId());

		if (lstNumExps.isEmpty()) {
			usuario.setEsActivo(Boolean.FALSE);
			usuarioDAO.update(usuario);

			return true;
		}

		return false;
	}

	/**
	 * Busca si tiene una sesion activa el usuario
	 * 
	 * @author Jfernandez
	 * @param idUsuario
	 * @return
	 * @throws NSJPNegocioException
	 */
	@Override
	public boolean buscarUsuarioEnSesion(UsuarioDTO usuarioDTO) {

		Usuario usuario = new Usuario();
		usuario = usuarioDAO.read(usuarioDTO.getIdUsuario());
		if (usuario.getIdSesionServer() == null
				|| usuario.getIdSesionServer().equals("")) {
			return true;
		}

		return false;
	}

	@Override
	public String buscarUsuarioEnSesionId(UsuarioDTO usuarioDTO) {

		Usuario usuario = new Usuario();
		usuario = usuarioDAO.read(usuarioDTO.getIdUsuario());
		if (usuario != null) {
			return usuario.getIdSesionServer();
		}

		return "";
	}

	@Override
	public UsuarioDTO obtenerUsuarioValidado(UsuarioDTO usuarioDTO)
			throws NSJPNegocioException {
		UsuarioDTO respDto = null;

		// Se valida lso parametros
		if (usuarioDTO == null || usuarioDTO.getClaveUsuario() == null
				|| usuarioDTO.getPassword() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		// Se consulta la clave de usuario en la BD para poder hacer la
		// comparación
		Usuario usuario = usuarioDAO.consultarUsuarioPorClaveService(usuarioDTO
				.getClaveUsuario());

		// Existe el usuario
		if (usuario != null) {
			// Se construye el DTO que contendrá la llave con la que se
			// cifró y contraseña CIFRADA
			KeygenencripDTO keygenencripDTO = new KeygenencripDTO(
					usuario.getcLlave(), usuario.getEncriptPasword());
			// El método compara con una contraseña NO CIFRADA y un DTO que
			// contiene lo que se explicó en el paso anteior
			// Usuario y Contraseña Correcta
			if (EncriptadorUtil.comparador(usuarioDTO.getPassword(),
					keygenencripDTO)) {
				respDto = UsuarioTransformer.transformarUsuario(usuario);

				ConfInstitucion intActul = this.intitucionDao
						.consultarInsitucionActual();
				respDto.setInstitucion(ConfInstitucionTransformer
						.transformarInstitucion(intActul));
			}
		}
		return respDto;
	}

	@Override
	public UsuarioDTO validarSesion(UsuarioDTO usuarioDTO)
			throws NSJPNegocioException {
		UsuarioDTO resp = null;
		if (usuarioDTO != null) {
			Usuario usuario = usuarioDAO
					.consultarUsuarioPorClaveService(usuarioDTO
							.getClaveUsuario());
			if (usuario != null) {
				if (usuario.getIdSesionServer() != null
						&& !usuario.getIdSesionServer().isEmpty()) {
					resp = UsuarioTransformer.transformarUsuario(usuario);
				}
			}
		}

		return resp;
	}

	@Override
	public boolean validarFuncionXUsuario(UsuarioDTO usrDTO, FuncionDTO fncDTO)
			throws NSJPNegocioException {
		boolean resp = false;
		if (usrDTO != null && fncDTO != null) {
			List<Funcion> lstFunciones = funcionDAO.validarFuncionXUsuario(
					UsuarioTransformer.transformarDTO(usrDTO),
					FuncionTransformer.transformarFuncionDTO(fncDTO));
			resp = !lstFunciones.isEmpty();
		}
		return resp;
	}

	@Override
	public UsuarioDTO consultarUsuarioXClaveUsuario(UsuarioDTO usrDTO) {
		Usuario usuario = usuarioDAO.consultarUsuarioPorClaveService(usrDTO
				.getClaveUsuario());
		return UsuarioTransformer.transformarUsuario(usuario);
	}

}
