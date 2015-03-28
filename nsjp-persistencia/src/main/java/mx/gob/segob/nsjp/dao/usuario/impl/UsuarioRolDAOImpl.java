package mx.gob.segob.nsjp.dao.usuario.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.usuario.UsuarioRolDAO;
import mx.gob.segob.nsjp.model.UsuarioRol;
import mx.gob.segob.nsjp.model.UsuarioRolId;

import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRolDAOImpl extends GenericDaoHibernateImpl<UsuarioRol, UsuarioRolId> implements UsuarioRolDAO {
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dao.usuario.UsuarioRolDAO#consultarRolesDeUsuario(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioRol> consultarRolesDeUsuario(String claveUsuario)
			throws NSJPNegocioException {
		return getHibernateTemplate().
		find("Select ur from UsuarioRol ur where ur.usuario.claveUsuario = ? and ur.usuario.esActivo = ? ",claveUsuario, true);
	}

}
