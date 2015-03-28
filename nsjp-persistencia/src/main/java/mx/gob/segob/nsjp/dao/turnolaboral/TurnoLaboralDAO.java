package mx.gob.segob.nsjp.dao.turnolaboral;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.TurnoLaboral;

public interface TurnoLaboralDAO extends GenericDao<TurnoLaboral,Long>{
	public List<TurnoLaboral> ConsultarCatalogoTurnoLaboral();
}
