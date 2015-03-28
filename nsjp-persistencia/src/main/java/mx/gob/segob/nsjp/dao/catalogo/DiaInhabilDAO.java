package mx.gob.segob.nsjp.dao.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.DiaInhabil;

public interface DiaInhabilDAO extends GenericDao<DiaInhabil, Long> {
	
	public List<DiaInhabil> consultarDiasInhabiles() throws NSJPNegocioException;

	public List<DiaInhabil> consultarDiasInhabilesPorMes(Short mes) throws NSJPNegocioException;
}
