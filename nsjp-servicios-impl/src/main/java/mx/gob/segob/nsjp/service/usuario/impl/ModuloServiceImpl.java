/**
 * 
 */
package mx.gob.segob.nsjp.service.usuario.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.usuario.ModuloDAO;
import mx.gob.segob.nsjp.dto.usuario.ModuloDTO;
import mx.gob.segob.nsjp.model.Modulo;
import mx.gob.segob.nsjp.service.usuario.ModuloService;
import mx.gob.segob.nsjp.service.usuario.impl.transformer.ModuloTransformer;

/**
 * @author LuisMG
 *
 */
@Service
@Transactional
public class ModuloServiceImpl implements ModuloService {

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.usuario.ModuloService#consultarModulos()
	 */
	@Autowired
	private ModuloDAO moduloDAO;
	
	@Override
	public List<ModuloDTO> consultarModulos() throws NSJPNegocioException {
		// TODO Auto-generated method stub
		List<ModuloDTO> modulosDTO=null;
		List<Modulo> modulos =	moduloDAO.findAll("", true);
		if (modulos!=null){
			modulosDTO=new ArrayList<ModuloDTO>();
			for (int i=0;i<modulos.size();i++){
				modulosDTO.add(ModuloTransformer.transformar(modulos.get(i)));
			}
		}
		return modulosDTO;
	}

}
