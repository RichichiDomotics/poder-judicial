package mx.gob.segob.nsjp.dao.leycodigo;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.LeyCodigo;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.Valor;

public interface LeyCodigoDAO extends GenericDao<LeyCodigo, Long>{

	public List<Valor> obtenerCatalogoNormas() throws NSJPNegocioException;
	
	public List<LeyCodigo> obtenerNormasCategoria(Long id) throws NSJPNegocioException;
	
}
