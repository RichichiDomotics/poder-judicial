/**
 * 
 */
package mx.gob.segob.nsjp.web.rol.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.modulo.ModuloDelegate;
import mx.gob.segob.nsjp.delegate.rol.RolDelegate;
import mx.gob.segob.nsjp.dto.usuario.ModuloDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

/**
 * @author LuisMG
 * 
 */
public class GuardarModulosRolAction extends GenericAction {

	private static final Logger log = Logger
			.getLogger(GuardarModulosRolAction.class);

	@Autowired
	public ModuloDelegate moduloDelegate;

	@Autowired
	public RolDelegate rolDelegate;

	public ActionForward findUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String idsMod = request.getParameter("idsMod");
		String idRol = request.getParameter("idRol");
		try {
			RolDTO rolDTO = rolDelegate.consultarRol(new RolDTO(Long
					.valueOf(idRol)));
			List<ModuloDTO> lstModulos = new ArrayList<ModuloDTO>();
			if (!idsMod.isEmpty()) {
				String[] modVec = idsMod.split(",");

				for (int i = 0; i < modVec.length; i++) {
					lstModulos.add(new ModuloDTO(Long.valueOf(modVec[i])));
				}
			}
			rolDTO.setModulos(lstModulos);
			log.debug("Ids Seleccionados: " + idsMod);
			request.setAttribute("guardar", 1);

			boolean resp = rolDelegate.actualizarModulosRol(rolDTO);

			converter.alias("resp", Boolean.class);
			String xml = converter.toXML(resp);
			log.info("Modulo:: " + xml);
			// mandamos la respuesta al cliente
			escribir(response, xml, null);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward consultaRolPapa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			String rolSelect = request.getParameter("rolSelect");
			RolDTO rol=new RolDTO();
			rol.setRolId(Long.parseLong(rolSelect));
			rol=rolDelegate.consultarRolMinimo(rol);
			boolean resp=false;
			if(rol!=null && rol.getRolPadre()==null){
				resp=true;
			}
			converter.alias("resp", Boolean.class);
			String xml = converter.toXML(resp);
			log.info("Modulo:: " + xml);
			// mandamos la respuesta al cliente
			escribir(response, xml, null);
		} catch (NSJPNegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward agregarSubRol(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			String nameSubRol = request.getParameter("nameSubRol");
			String decSubRol = request.getParameter("decSubRol");
			String rolSelect = request.getParameter("rolSelect");
			RolDTO rol=new RolDTO();
			rol.setRolId(Long.parseLong(rolSelect));
			rol=rolDelegate.consultarRolMinimo(rol);
			RolDTO rolDTO=new RolDTO();
			rolDTO.setDescripcionRol(decSubRol);
			rolDTO.setEsPrincipal(true);
			rolDTO.setNombreRol(nameSubRol);
			rolDTO.setRolPadre(rol);
			rolDTO.setJerarquiaOrganizacionalDTO(rol.getJerarquiaOrganizacionalDTO());
			rolDTO.setInstitucionPertenece(rol.getInstitucionPertenece());
			boolean resp = rolDelegate.crearRol(rolDTO);
			converter.alias("resp", Boolean.class);
			String xml = converter.toXML(resp);
			log.info("Modulo:: " + xml);
			// mandamos la respuesta al cliente
			escribir(response, xml, null);
		} catch (NSJPNegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
