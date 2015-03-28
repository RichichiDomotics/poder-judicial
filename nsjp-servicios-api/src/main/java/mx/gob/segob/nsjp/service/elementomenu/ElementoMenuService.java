/**
 * 
 */
package mx.gob.segob.nsjp.service.elementomenu;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.elementomenu.ElementoMenuDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;

/**
 * @author LuisMG
 *
 */
public interface ElementoMenuService {
	/**
	 * Dado un Rol se regresa el menú asociado hasta ese rol 
	 * @param rolDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	
	public List<ElementoMenuDTO> consultarElementosMenuXRol (RolDTO rolDTO) throws NSJPNegocioException;
	/**
	 * Dado un id de elementoMenu válido se regresa el árbol de asociaciones de dicho elementoMenu
	 * @param eMDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public ElementoMenuDTO consultarElementoMenu (ElementoMenuDTO eMDTO) throws NSJPNegocioException;
	
		
}
