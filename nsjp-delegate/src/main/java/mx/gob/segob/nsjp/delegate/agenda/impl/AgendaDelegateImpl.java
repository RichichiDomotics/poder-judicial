package mx.gob.segob.nsjp.delegate.agenda.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.enums.documento.Periodicidad;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.agenda.AgendaDelegate;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.service.agenda.CalendarizacionService;

/**
 * Implementación del delegate para la Agenda.
 * 
 * @version 1.0
 * @author mgallardo
 * 
 */

@Service("agendaDelegate")
public class AgendaDelegateImpl implements AgendaDelegate {

	@Autowired
	CalendarizacionService calendarizacionService;
	@Override
	public List<FechaCompromisoDTO> generarCalendarizacion(Calendar fechaInicio,
			Calendar fechaFin, Periodicidad periodo,Long idMedida, Long idUsuarioLoggeado)
			throws NSJPNegocioException {
		
		return calendarizacionService.generarCalendarizacion(fechaInicio, fechaFin, periodo,idMedida, idUsuarioLoggeado);
	}
	@Override
	public List<FechaCompromisoDTO> consultarCalendarizacionPorMedidaId(Long idMedida)
			throws NSJPNegocioException {
		
		return calendarizacionService.consultarCalendarizacionPorMedidaId(idMedida);
	}
	@Override
	public List<FechaCompromisoDTO> consultarCalendarizacionPorMedidaIdReducido(
			Long idMedida) throws NSJPNegocioException {
		return calendarizacionService.consultarCalendarizacionPorMedidaIdReducido(idMedida);
	}
	@Override
	public void actualizarFechaCumplimiento(FechaCompromisoDTO fechaCompromiso)
			throws NSJPNegocioException {
		calendarizacionService.actualizarFechaCumplimiento(fechaCompromiso);		
	}
	
	
	


}
