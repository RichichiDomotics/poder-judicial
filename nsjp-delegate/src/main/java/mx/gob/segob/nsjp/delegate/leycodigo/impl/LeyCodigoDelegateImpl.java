package mx.gob.segob.nsjp.delegate.leycodigo.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.leycodigo.LeyCodigoDelegate;
import mx.gob.segob.nsjp.dto.LeyCodigoDTO;
import mx.gob.segob.nsjp.service.leycodigo.LeyCodigoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeyCodigoDelegateImpl implements LeyCodigoDelegate {

	@Autowired
	private LeyCodigoService leyCodigoService;
	

	@Override
	public List<LeyCodigoDTO> obtenerNormasCategoria(Long id)
			throws NSJPNegocioException {
		return leyCodigoService.obtenerNormasCategoria(id);
	}


	@Override
	public ByteArrayOutputStream leerLeyCodigo(Long id) throws NSJPNegocioException {
		return leyCodigoService.leerLeyCodigo(id);
	}

}
