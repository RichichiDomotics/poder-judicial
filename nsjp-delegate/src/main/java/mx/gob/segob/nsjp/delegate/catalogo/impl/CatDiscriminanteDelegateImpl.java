/**
 * 
 */
package mx.gob.segob.nsjp.delegate.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.catalogo.CatDiscriminanteDelegate;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.service.catalogo.AdministrarCatalogoService;
import mx.gob.segob.nsjp.service.catalogo.CatalogoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rgama
 *
 */
@Service("catDiscriminanteDelegate")
public class CatDiscriminanteDelegateImpl implements CatDiscriminanteDelegate {
	
	
	@Autowired
	private AdministrarCatalogoService administrarCatalogoService;
	
	@Autowired
	private CatalogoService catalogoService;

	@Override
	public List<CatDiscriminanteDTO> consultarTribunalesPorDistrito(
			Long distritoId, Instituciones target) throws NSJPNegocioException {
		return administrarCatalogoService.consultarTribunalesPorDistrito(distritoId, target);
	}
	
	@Override
	public List<CatDiscriminanteDTO> consultarAgenciasPorDistrito(
			Long distritoId, Instituciones target) throws NSJPNegocioException {
		return administrarCatalogoService.consultarAgenciasPorDistrito(distritoId, target);
	}
	
	public Long eliminarAgencia(CatDiscriminanteDTO discriminanteDTO) throws NSJPNegocioException {
	   	return catalogoService.eliminarAgencia(discriminanteDTO.getCatDiscriminanteId());
	}
	
}
