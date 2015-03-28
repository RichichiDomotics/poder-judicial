/**
 * 
 */
package mx.gob.segob.nsjp.delegate.modulo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.modulo.ModuloDelegate;
import mx.gob.segob.nsjp.dto.usuario.ModuloDTO;
import mx.gob.segob.nsjp.service.usuario.ModuloService;

/**
 * @author LuisMG
 *
 */
@Service
@Transactional
public class ModuloDelegateImpl implements ModuloDelegate {
	
	@Autowired
	private ModuloService moduloService;
	
	@Override
	public List<ModuloDTO> consultarModulos()  throws NSJPNegocioException{
		List<ModuloDTO> lstModulos=null;
		lstModulos= moduloService.consultarModulos();
		return lstModulos;
	}
	

	

}
