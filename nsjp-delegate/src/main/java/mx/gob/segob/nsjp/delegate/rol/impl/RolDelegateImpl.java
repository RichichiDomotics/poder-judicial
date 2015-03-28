/**
 * 
 */
package mx.gob.segob.nsjp.delegate.rol.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.rol.RolDelegate;
import mx.gob.segob.nsjp.dto.rol.FiltroRolesDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.service.usuario.RolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LuisMG
 *
 */
@Service
@Transactional
public class RolDelegateImpl implements RolDelegate {

	@Autowired
	private RolService rolService;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.rol.RolDelegate#consultarRoles()
	 */
	@Override
	public List<RolDTO> consultarRoles(FiltroRolesDTO fRolesDTO) throws NSJPNegocioException {
		// TODO Auto-generated method stub
		return rolService.consultarRoles(fRolesDTO);
	}
	
	@Override
	public RolDTO consultarRolCompleto (RolDTO rol) throws NSJPNegocioException{
		return rolService.consultarRolCompleto(rol);
	}
	
	@Override
	public RolDTO consultarRol (RolDTO rol) throws NSJPNegocioException{
		return rolService.consultarRol(rol);
	}
	
	@Override
	public RolDTO consultarRolMinimo (RolDTO rol) throws NSJPNegocioException{
		return rolService.consultarRolMinimo(rol);
	}
	@Override
	public boolean actualizarModulosRol (RolDTO rolDTO) throws NSJPNegocioException{
		boolean resp=false;
		resp= rolService.actualizaModulosRol(rolDTO);
		
		return resp;
		
		
		
	}

	@Override
	public boolean crearRol(RolDTO rolDTO) throws NSJPNegocioException {
		
		return rolService.crearRol(rolDTO);
	}
	

}
