package mx.gob.segob.nsjp.service.test.agenda;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.Periodicidad;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.service.agenda.CalendarizacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class CalendarizacionServiceImplTest extends BaseTestServicios<CalendarizacionService>{
	public void testCalendarizacion() throws NSJPNegocioException, Exception
	{
		Date dInicio = new Date();
		Date dFin = new Date();		
		DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
		dInicio = dfm.parse("2011-08-29");
		dFin = dfm.parse("2012-08-29");
		Calendar fechaInicio = Calendar.getInstance();
		Calendar fechaFin = Calendar.getInstance();
		
		fechaInicio.clear();
		fechaFin.clear();
		fechaInicio.setTime(dInicio);
		fechaFin.setTime(dFin);
		
		logger.debug(fechaInicio.getTime());
		logger.debug(fechaFin.getTime());
		List<FechaCompromisoDTO> calendario = service.generarCalendarizacion(fechaInicio, fechaFin, Periodicidad.getByValor(2468L),324L,12L);
		
		logger.debug("Dias de asistencia: " + calendario.size());		
	}
	
	public void testConsultarCalendarizacionPorMedidaTest() throws NSJPNegocioException
	{
		List<FechaCompromisoDTO> fechas = service.consultarCalendarizacionPorMedidaId(324L);
		logger.debug("Fechas encontradas: " + fechas.size());
		for(FechaCompromisoDTO row:fechas)
		{
			logger.debug("Fecha compromiso: " + row.getFechaCompromiso());
		}
	}
	
	public void testConsultarCalendarizacionPorMedidaReducidoTest() throws NSJPNegocioException
	{
		List<FechaCompromisoDTO> fechas = service.consultarCalendarizacionPorMedidaIdReducido(6L);
		logger.debug("Fechas encontradas: " + fechas.size());
		for(FechaCompromisoDTO row:fechas)
		{
			logger.debug("Fecha compromiso: " + row.getFechaCompromiso());
			logger.debug("	Tipo de medida: " + 
					(row.getCompromisoPeriodico() != null &&
							row.getCompromisoPeriodico().getMedida() != null &&
							row.getCompromisoPeriodico().getMedida().getValorTipoMedida() != null? row.getCompromisoPeriodico().getMedida().getValorTipoMedida(): "-"));
		}
	}
	
	public void testActualizarFechaCompromiso() throws NSJPNegocioException
	{
		FechaCompromisoDTO fecha = new FechaCompromisoDTO();
		fecha.setFechaCompromisoId(35L);
		fecha.setFechaCumplimiento(new Date());
		
		service.actualizarFechaCumplimiento(fecha);
	}
	
	
}
