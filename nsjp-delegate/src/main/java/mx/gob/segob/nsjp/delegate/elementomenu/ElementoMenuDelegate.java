/**
 * 
 */
package mx.gob.segob.nsjp.delegate.elementomenu;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.elementomenu.ElementoMenuDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;


/**
 * @author LuisMG
 *
 */
public interface ElementoMenuDelegate {
	/**
	 * M�todo que regresa TODO el men� asignado a un rol dado
	 * 
	 * @param rolDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<ElementoMenuDTO> consultarElementosMenuXRol (RolDTO rolDTO) throws NSJPNegocioException;
	/**
	 * M�todo que regresa toda la estructura de un Nodo Elemento Menu dado
	 * @param eMDTO
	 * @return
	 * @throws NSJPNegocioException
	 */

	public ElementoMenuDTO consultarElementoMenu (ElementoMenuDTO eMDTO) throws NSJPNegocioException;
	
}
