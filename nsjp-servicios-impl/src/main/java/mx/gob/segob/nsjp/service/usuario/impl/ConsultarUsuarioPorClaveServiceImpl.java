/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.segob.nsjp.service.usuario.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.usuario.UsuarioDAO;
import mx.gob.segob.nsjp.dao.usuario.UsuarioRolDAO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioRolDTO;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.UsuarioRol;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;
import mx.gob.segob.nsjp.service.usuario.ConsultarUsuarioPorClaveService;
import mx.gob.segob.nsjp.service.usuario.impl.transformer.RolTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sawbona
 */
@Repository
public class ConsultarUsuarioPorClaveServiceImpl implements ConsultarUsuarioPorClaveService {

    private final static Logger logger = Logger.getLogger(ConsultarUsuarioPorClaveServiceImpl.class);
    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private UsuarioRolDAO usuarioRolDAO;
    @Override
    public UsuarioDTO consultarUsuarioPorClaveService(String claveUsuario) {
        Usuario usuario = usuarioDAO.consultarUsuarioPorClaveService(claveUsuario);
        
        return usuario != null ? UsuarioTransformer.transformarUsuario(usuario) : null;
    }
    
    /*
     * (non-Javadoc)
     * @see mx.gob.segob.nsjp.service.usuario.ConsultarUsuarioPorClaveService#consultarRolesDeUsuario(java.lang.String)
     */
	@Override
	public List<UsuarioRolDTO> consultarRolesDeUsuario(String claveUsuario)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/SERVICIO PARA CONSULTAR ROLES DE USUARIO/");
		
		List<UsuarioRolDTO> resultado = new ArrayList<UsuarioRolDTO>();
		for(UsuarioRol unRol:usuarioRolDAO.consultarRolesDeUsuario(claveUsuario)){
			resultado.add(RolTransformer.transformar(unRol));
			logger.debug("ClaveUsuario: " + claveUsuario);
			logger.debug("No Rol: " + unRol.getRol().getRolId());
		}
		
		return resultado;
	}
}
