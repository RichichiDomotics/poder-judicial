/**
 * 
 */
package mx.gob.segob.nsjp.delegate.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.catalogo.TipoDiscriminante;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.catalogo.DistritoDelegate;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;
import mx.gob.segob.nsjp.service.catalogo.CatalogoService;
import mx.gob.segob.nsjp.service.catalogo.ConsultarDiscriminanteService;
import mx.gob.segob.nsjp.service.catalogo.ConsultarDistritoService;
import mx.gob.segob.nsjp.service.catalogo.RegistrarDiscriminanteService;
import mx.gob.segob.nsjp.service.catalogo.RegistrarDistritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AlineGS
 *
 */
@Service("distritoDelegate")
public class DistritoDelegateImpl implements DistritoDelegate {
	
	//SERVICIOS
	
	@Autowired
	private ConsultarDistritoService consultarDistritoService;
	@Autowired
	private ConsultarDiscriminanteService consultarDiscriminanteService;
	@Autowired
	private RegistrarDistritoService registrarDistritoService;
	@Autowired
	private RegistrarDiscriminanteService registrarDiscriminanteService;
	@Autowired
	private CatalogoService catalogoService;
	
	

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.catalogo.DistritoDelegate#registrarDistrito(mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO)
	 */
	@Override
	public Long registrarDistrito(CatDistritoDTO distritoDTO)
			throws NSJPNegocioException {
		return registrarDistritoService.registrarDistrito(distritoDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.catalogo.DistritoDelegate#consultarDistritos()
	 */
	@Override
	public List<CatDistritoDTO> consultarDistritos()
			throws NSJPNegocioException {
		return consultarDistritoService.consultarDistritos();
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.catalogo.DistritoDelegate#consultarDistritoXId(java.lang.Long)
	 */
	@Override
	public CatDistritoDTO consultarDistritoXId(Long distritoID)
			throws NSJPNegocioException {
		return consultarDistritoService.consultarDistritoXId(distritoID);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.catalogo.DistritoDelegate#registrarDiscriminante(mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO)
	 */
	@Override
	public Long registrarDiscriminante(CatDiscriminanteDTO DiscriminanteDTO)
			throws NSJPNegocioException {
		return registrarDiscriminanteService.registrarDiscriminante(DiscriminanteDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.catalogo.DistritoDelegate#consultarDiscriminantesXDistrito()
	 */
	@Override
	public List<CatDiscriminanteDTO> consultarDiscriminantesXDistrito(Long distritoID, TipoDiscriminante tipo)
			throws NSJPNegocioException {
		return consultarDiscriminanteService.consultarDiscriminantesXDistrito(distritoID, tipo);
	}
	
	@Override
	public List<CatDiscriminanteDTO> consultarDiscriminantesXDistritoYTipoInstitucion(
			Long distritoID) throws NSJPNegocioException {
		return consultarDiscriminanteService.consultarDiscriminantesXDistritoYTipoInstitucion(distritoID);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.catalogo.DistritoDelegate#consultarDiscriminanteXId(java.lang.Long)
	 */
	@Override
	public CatDiscriminanteDTO consultarDiscriminanteXId(Long discriminanteID)
			throws NSJPNegocioException {
		return consultarDiscriminanteService.consultarDiscriminanteXId(discriminanteID);
	}

	@Override
	public List<CatDiscriminanteDTO> consultarDiscriminantes(TipoDiscriminante tipo)
			throws NSJPNegocioException {
		return consultarDiscriminanteService.consultarDiscriminantes(tipo);
	}

	@Override
	public Long registrarDistritoConFantasma(CatDistritoDTO distritoDTO)
			throws NSJPNegocioException {
		return registrarDistritoService.registrarDistritoConFantasma(distritoDTO);
	}
	
	public Long eliminarDistrito(CatDistritoDTO distritoDTO) throws NSJPNegocioException {
		return catalogoService.eliminarDistrito(distritoDTO.getCatDistritoId());
	}
	
}
