/**
 * 
 */
package mx.gob.segob.nsjp.delegate.rol;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.rol.FiltroRolesDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;

/**
 * @author LuisMG
 *
 */
public interface RolDelegate {
	public List<RolDTO> consultarRoles (FiltroRolesDTO fRolesDTO) throws NSJPNegocioException;
	public RolDTO consultarRolCompleto (RolDTO rol) throws NSJPNegocioException;
	public RolDTO consultarRol (RolDTO rol) throws NSJPNegocioException;
	public RolDTO consultarRolMinimo (RolDTO rol) throws NSJPNegocioException;
	public boolean actualizarModulosRol (RolDTO rolDTO) throws NSJPNegocioException;
	public boolean crearRol (RolDTO rolDTO) throws NSJPNegocioException;
	
}
