package mx.gob.segob.nsjp.dao.test.turnolaboral;

import java.util.List;

import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.dao.turnolaboral.TurnoLaboralDAO;
import mx.gob.segob.nsjp.model.TurnoLaboral;

public class TurnoLaboralDAOTest extends BaseTestPersistencia<TurnoLaboralDAO>{
	@SuppressWarnings("unused")
	public void ConsultarCatalogoTurnoLaboral()
	{
		List<TurnoLaboral> list = daoServcice.ConsultarCatalogoTurnoLaboral();
		for(TurnoLaboral tl:list)
		{
			logger.debug("------CONSULTA CATALOGO DE TURNO LABORAL-----");
			logger.debug("ID: " + tl.getTurnoLaboralId());
			logger.debug("Nombre de turno: " + tl.getNombreTurno());
			logger.debug("Hora Inicio turno: " + tl.getHoraInicioTurno());
			logger.debug("Hora fin turno: " + tl.getHoraFinTurno());
			logger.debug("---------------------------------------------");
		}
	}
}
