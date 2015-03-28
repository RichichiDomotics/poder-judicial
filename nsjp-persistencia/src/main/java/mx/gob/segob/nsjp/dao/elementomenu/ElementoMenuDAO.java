/**
 * 
 */
package mx.gob.segob.nsjp.dao.elementomenu;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.ElementoMenu;
import mx.gob.segob.nsjp.model.Rol;

/**
 * @author LuisMG
 *
 */
public interface ElementoMenuDAO extends GenericDao<ElementoMenu, Long> {
	
	public ElementoMenu consultarElementoMenu (ElementoMenu elementoMenu);
	
	public List<ElementoMenu> consultarElementosMenuXRol (Rol rol);
	
	public boolean eliminarElementoMenu (ElementoMenu elementoMenu);
	
	public Long insertarSimpleElementoMenu (ElementoMenu elementoMenu);

}
