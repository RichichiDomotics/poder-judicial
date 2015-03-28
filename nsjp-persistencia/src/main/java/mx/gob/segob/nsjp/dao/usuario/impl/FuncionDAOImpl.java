/**
 * Nombre del Programa : FuncionDAOImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 1 Aug 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion de metodos de acceso a datos para la entidad Funcion
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
package mx.gob.segob.nsjp.dao.usuario.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.usuario.FuncionDAO;
import mx.gob.segob.nsjp.model.ElementoMenu;
import mx.gob.segob.nsjp.model.Funcion;
import mx.gob.segob.nsjp.model.Rol;
import mx.gob.segob.nsjp.model.Usuario;

/**
 * Implementacion de metodos de acceso a datos para la entidad Funcion.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Repository
public class FuncionDAOImpl extends GenericDaoHibernateImpl<Funcion, Long>
		implements FuncionDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Funcion> consultarFunciones() {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Funcion ");

		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Funcion> consultarFuncionesByRol(Long rolId) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("FROM Funcion f LEFT JOIN f.rolFunciones rf ")
				.append("WHERE rf.rol=").append(rolId);
		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Funcion> consultarFuncionesXUsuario(Usuario usuario) {
		StringBuffer queryString = new StringBuffer();
		queryString
				.append(" FROM Funcion f WHERE f.funcionId IN ( ")
				.append(" SELECT f.funcionId FROM Funcion f left join f.rolFunciones rf WHERE rf.rol.rolId IN("
						+ " SELECT r.rolId FROM Rol r left join r.usuarioRoles ur WHERE ur.usuario.usuarioId =")
				.append(usuario.getUsuarioId()).append(" )) ");

		Query query = super.getSession().createQuery(queryString.toString());
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Funcion> validarFuncionXUsuario(Usuario usr, Funcion fnc) {
		StringBuffer queryString = new StringBuffer();
		queryString.append(" FROM Funcion f Where f.funcionId = ")
		.append(fnc.getFuncionId())
		.append(" and f.funcionId IN (")
		.append(" SELECT f.funcionId FROM Funcion f left join f.modulos m WHERE m.moduloId IN ( ")
		.append(" SELECT m.moduloId FROM Modulo m left join m.roles r WHERE r.rolId IN (")
		.append(" SELECT r.rolId FROM Rol r left join r.usuarioRoles ur WHERE ur.usuario IN (")
		.append(" SELECT u.usuarioId FROM Usuario u WHERE u.usuarioId = ")
		.append(usr.getUsuarioId())
		.append(" )))) ");

		Query query = super.getSession().createQuery(queryString.toString());
		List<Funcion> aux = query.list();
		if (aux != null) {
			System.out.println("Tamaño de elementos: " + aux.size());
		}
		return aux;
	}

	@Override
	public Funcion consultarFuncionXNombre(Funcion fnc) {
		Funcion resp = null;
		StringBuffer queryString = new StringBuffer();
		queryString.append(" FROM Funcion f WHERE f.nombreFuncion = '")
				.append(fnc.getNombreFuncion()).append("'");
		Query query = super.getSession().createQuery(queryString.toString());
		if (!query.list().isEmpty()) {
			resp = (Funcion) query.list().get(0);
		}

		return resp;
	}

	@Override
	public boolean instertarFunciones(List<Funcion> lstFunciones) {
		boolean resp = false;
		if (lstFunciones != null) {
			super.createAll(lstFunciones);
			resp = true;
		}
		return resp;
	}

}
