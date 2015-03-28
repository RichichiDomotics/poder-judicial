package mx.gob.segob.nsjp.dao.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CatFaltaAdministrativa;

public interface CatFaltaAdministrativaDAO extends GenericDao<CatFaltaAdministrativa,Long>{
	List<CatFaltaAdministrativa> consultarCatalogoFaltaAdministrativa();
}
