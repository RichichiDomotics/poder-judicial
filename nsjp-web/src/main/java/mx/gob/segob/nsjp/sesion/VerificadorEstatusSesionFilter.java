/**
 * 
 */
package mx.gob.segob.nsjp.sesion;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.context.AppContext;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

/**
 * Filtro encargado de verificar el estatus de la sesion del usuario para
 * redireccionarlo a la página de cambio de contrasenia.
 * 
 * @author IgnacioFO
 * 
 */
public class VerificadorEstatusSesionFilter implements Filter {

	private String urlLogin;
	private String cargaLogin;
	private String urlLogout;
	private String urlRedirec;
	private static final Log log = LogFactory
			.getLog(VerificadorEstatusSesionFilter.class);

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.urlLogin = config.getInitParameter("urlLogin");
		this.cargaLogin = config.getInitParameter("cargaLogin");
		this.urlLogout = config.getInitParameter("urlLogout");
		this.urlRedirec = config.getInitParameter("urlRedirec");
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		if (servletRequest instanceof HttpServletRequest
				&& servletResponse instanceof HttpServletResponse) {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			String url = request.getServletPath();
			log.debug("Paht: " + request.getContextPath());
			if (url.equals(urlLogin) || url.equals(cargaLogin)
					|| url.equals(urlLogout) || url.equals("/" + urlRedirec)) {
				chain.doFilter(servletRequest, servletResponse);
			} else if (url.endsWith(".do") || url.endsWith(".jsp")
					|| url.endsWith(".js") || url.endsWith(".css")
					|| url.endsWith(".jrxml") || url.endsWith(".jasper")
					|| url.endsWith(".gif") || url.endsWith("back_login.png")
					|| url.endsWith("back_huella.png")
					|| url.endsWith("pleca_bottom.png")
					|| url.endsWith("pleca_top_log.png")) {
				manejarSesionExistente(servletRequest, servletResponse, chain,
						request, response);
			} else {
				chain.doFilter(servletRequest, servletResponse);
			}
		} else {
			throw new RuntimeException(
					"No fue posible obtener el http servlet request y response");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * Método que maneja el caso de que la sesion es existente.
	 * 
	 * @param servletRequest
	 * @param servletResponse
	 * @param chain
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void manejarSesionExistente(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ApplicationContext appContext = AppContext.getApplicationContext();

//		LoginAction eventDao = (LoginAction) appContext.getBean("/autorizar");

		HttpServletRequest request1 = (HttpServletRequest) servletRequest;
		String url = request1.getServletPath();
		int inicioCut = url.lastIndexOf("/");
		String bloqueosNoRestringidos = url.substring(inicioCut + 1)
				.toUpperCase();
		log.info("Url sin /" + url);
		UsuarioDTO usuarioDTO = (UsuarioDTO) request.getSession().getAttribute(
				GenericAction.KEY_SESSION_USUARIO_FIRMADO);
		log.info("Url sin /:" + bloqueosNoRestringidos);
		BloqueosNoRestringidos noRestringidos = BloqueosNoRestringidos
				.getByValor(bloqueosNoRestringidos);
		if (usuarioDTO != null && usuarioDTO.getIdUsuario() != null) {
			// Inicia parte de autorizador de navegación - LOMG
			//if (url.endsWith(".do") || url.endsWith(".jsp")) {
			//	if (eventDao.autorizarNavegacion(new FuncionDTO(url),
			//			usuarioDTO)) {
			//		chain.doFilter(servletRequest, servletResponse);
			//	} else {
			//		response.sendRedirect(request1.getContextPath() + "/"
			//				+ urlRedirec);
			//	}
			//	chain.doFilter(servletRequest, servletResponse);
			//} else {
				chain.doFilter(servletRequest, servletResponse);
		//	}
			// Fin parte de autorizador de navegación - LOMG
		} else if (noRestringidos != null) {
			log.info("LA peticion es correcta no existe un usuario de sesion pero es parte del login.jsp");
			chain.doFilter(servletRequest, servletResponse);
		} else {
			log.info("No fue posible obtener el usuario de sesion");
			response.sendRedirect(request1.getContextPath() + "/" + urlRedirec);
		}
	}
}
