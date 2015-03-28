/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.segob.nsjp.service.usuario;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioRolDTO;

/**
 *
 * @author sawbona
 */
public interface ConsultarUsuarioPorClaveService {

    UsuarioDTO consultarUsuarioPorClaveService(String claveUsuario)
            throws NSJPNegocioException;

    /**
	 * Consulta los roles asignados actualmente a un usuario en base a la clave del usuario
	 * @param claveUsuario Clave del usuario a consultar sus roles
	 * @return Lista de roles encontrados
	 * @throws NSJPNegocioException
	 */
	List<UsuarioRolDTO> consultarRolesDeUsuario(String claveUsuario) throws NSJPNegocioException;
}
