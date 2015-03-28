package mx.gob.segob.nsjp.dao.test.tarea.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.tarea.EventoCitaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.EventoCita;

public class EventoCitaDAOImplTest extends BaseTestPersistencia<EventoCitaDAO> {

	public void EventoCitaDAOImpl () throws NSJPNegocioException{
		Long idFuncionario = 1L;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		c.set(Calendar.MONTH, 6);
		c.set(Calendar.YEAR, 2011);
		Date fInicio = c.getTime();
		c.set(Calendar.DAY_OF_MONTH, 19);
		c.set(Calendar.MONTH, 6);
		c.set(Calendar.YEAR, 2011);
		Date fFinal = c.getTime();
		List<EventoCita> list = daoServcice.consultarEventosCitasPorFunciconario(idFuncionario, fInicio, fFinal);
		
		for(EventoCita cita : list){
			logger.info("["+cita.getDescripcionEvento()+" "+cita.getFechaInicioEvento()+"- "+cita.getFechaFinEvento()+"]");
		}
		
		
	}
	
}
