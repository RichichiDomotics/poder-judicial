package mx.gob.segob.nsjp.service.leycodigo;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.LeyCodigoDTO;

public interface LeyCodigoService {

	public List<LeyCodigoDTO> obtenerNormasCategoria(Long id) throws NSJPNegocioException;
	
	public ByteArrayOutputStream leerLeyCodigo(Long id) throws NSJPNegocioException;
}
