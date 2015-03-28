/**
 * 
 */
package mx.gob.segob.nsjp.web.persona.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.configuracion.ConfiguracionDelegate;
import mx.gob.segob.nsjp.delegate.parametro.ParametroDelegate;
import mx.gob.segob.nsjp.delegate.usuario.UsuarioDelegate;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.login.action.LoginAction;

/**
 * @author LuisMG
 * 
 */
public class CambiarContraseniaIndividuoAction extends GenericAction {
	/**
	 * Logger.
	 */
	private static final Logger log = Logger.getLogger(LoginAction.class);
	@Autowired
	private UsuarioDelegate usuarioDelegate;
	@Autowired
	private ConfiguracionDelegate configDelegate;
	@Autowired
	private ParametroDelegate parametroDelegate;

	public ActionForward findUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// DynaActionForm forma = (DynaActionForm) form;

		// String username = (String) forma.get("username");
		// String oldPassword = (String) forma.get("oldPassword");
		// String newPassword = (String) forma.get("newPassword");

		String username = request.getParameter("username");

		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		UsuarioDTO oldUserDTO = new UsuarioDTO();
		UsuarioDTO newUserDTO = new UsuarioDTO();
		if (username != null && oldPassword != null && newPassword != null) {
			oldUserDTO.setClaveUsuario(username);
			oldUserDTO.setPassword(oldPassword);
			newUserDTO.setClaveUsuario(username);
			newUserDTO.setPassword(newPassword);
			log.debug("---------- Servicio para el cambio de contraseña del usuario ----------");
			log.debug("Usuario id: " + username);
			log.debug("Constraseña Antigua: " + oldPassword);
			log.debug("Contraseña Nueva: " + newPassword);
			log.debug("---------- Servicio para el cambio de contraseña del usuario ----------");
			try {
				if (usuarioDelegate.cambiaContrasenia(oldUserDTO, newUserDTO)) {
					String xml = "<error><bandera>0</bandera></error>";
					xml += "<exito><bandera>1</bandera></exito>";
					log.info(xml);
					escribirRespuesta(response, xml);
					return mapping.findForward("success");
				} else {
					String xml = "<error><bandera>0</bandera></error>";
					xml += "<exito><bandera>0</bandera></exito>";
					log.info(xml);
					escribirRespuesta(response, xml);
					return mapping.findForward("fail");
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				String xml = "<error><bandera>1</bandera></error>";
				log.info(xml);
				escribirRespuesta(response, xml);
				return mapping.findForward("error");
			}
		} else {
			String xml = "<error><bandera>2</bandera></error>";
			log.info(xml);
			escribirRespuesta(response, xml);
			return mapping.findForward("error2");
		}

	}
}
