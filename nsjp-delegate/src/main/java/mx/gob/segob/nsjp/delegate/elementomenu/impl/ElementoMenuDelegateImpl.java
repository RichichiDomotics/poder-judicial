/**
 * 
 */
package mx.gob.segob.nsjp.delegate.elementomenu.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.elementomenu.ElementoMenuDelegate;
import mx.gob.segob.nsjp.dto.elementomenu.ElementoMenuDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.service.elementomenu.ElementoMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LuisMG
 *
 */
@Service
@Transactional
public class ElementoMenuDelegateImpl implements ElementoMenuDelegate {
	@Autowired
	private ElementoMenuService elementoMenuService;
	
	@Override
	public List<ElementoMenuDTO> consultarElementosMenuXRol (RolDTO rolDTO) throws NSJPNegocioException{
		return elementoMenuService.consultarElementosMenuXRol(rolDTO);
	}
	
	@Override
	public ElementoMenuDTO consultarElementoMenu (ElementoMenuDTO eMDTO) throws NSJPNegocioException{
		return elementoMenuService.consultarElementoMenu(eMDTO);
	}
	
}
