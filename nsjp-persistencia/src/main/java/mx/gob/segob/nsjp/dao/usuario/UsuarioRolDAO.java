package mx.gob.segob.nsjp.dao.usuario;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Rol;
import mx.gob.segob.nsjp.model.UsuarioRol;
import mx.gob.segob.nsjp.model.UsuarioRolId;

public interface UsuarioRolDAO extends GenericDao<UsuarioRol, UsuarioRolId> {

	
	/**
	 * Consulta los roles asignados actualmente a un usuario en base a la clave del usuario
	 * @param claveUsuario Clave del usuario a consultar sus roles
	 * @return Lista de roles encontrados
	 * @throws NSJPNegocioException
	 */
	List<UsuarioRol> consultarRolesDeUsuario(String claveUsuario) throws NSJPNegocioException;
}
