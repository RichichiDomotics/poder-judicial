package mx.gob.segob.nsjp.service.agenda;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.documento.Periodicidad;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.institucion.JerarquiaOrganizacionalDAO;
import mx.gob.segob.nsjp.dao.medida.CompromisoPeriodicoDAO;
import mx.gob.segob.nsjp.dao.medida.FechaCompromisoDAO;
import mx.gob.segob.nsjp.dao.medida.MedidaDAO;
import mx.gob.segob.nsjp.dao.usuario.UsuarioDAO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.model.CompromisoPeriodico;
import mx.gob.segob.nsjp.model.FechaCompromiso;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.Medida;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.service.expediente.impl.transform.FechaCompromisoTransformer;

@Service
@Transactional
public class CalendarizacionServiceImpl implements CalendarizacionService {

	@Autowired
	UsuarioDAO usuarioDAO;
	@Autowired
	MedidaDAO medidaDAO;
	@Autowired
	CompromisoPeriodicoDAO compromisoDAO;
	@Autowired
	JerarquiaOrganizacionalDAO jerarquiaDAO;
	@Autowired
	FechaCompromisoDAO fechaCompromisoDAO;
	
	public final static Logger logger = 
		Logger.getLogger(CalendarizacionServiceImpl.class);
	
	@Override
	public List<FechaCompromisoDTO> generarCalendarizacion(Calendar fechaInicio, Calendar fechaFin,
			Periodicidad periodo, Long idMedida, Long idUsuarioLoggeado) throws NSJPNegocioException {
		
		logger.debug("Fecha Inicio: " + fechaInicio.getTime());
		logger.debug("Fecha Fin: " + fechaFin.getTime());
		
		List<FechaCompromisoDTO> fechaCompromisoDTO = new ArrayList<FechaCompromisoDTO>();
		List<Date> listaPeriodos = new ArrayList<Date>();
		listaPeriodos.add(fechaInicio.getTime());
		
		int count = 0;		
		while((fechaInicio.getTime().compareTo(fechaFin.getTime()))<0)
		{
					
			switch(periodo)
			{
				case DIARIO:
					fechaInicio.add(Calendar.DAY_OF_WEEK, 1);	
					
					break;
				case CADA_TERCER_DIA:
					fechaInicio.add(Calendar.DAY_OF_WEEK, 3);
					
					break;
				case SEMANAL:
					fechaInicio.add(Calendar.DAY_OF_WEEK, 7);
					
					break;
				case QUINCENAL:
					fechaInicio.add(Calendar.DAY_OF_WEEK, 15);
					
					break;
				case MENSUAL:
					fechaInicio.add(Calendar.MONTH, 1);
					
					break;
					
			}
			
			if ((fechaInicio.getTime().compareTo(fechaFin.getTime()))<0) {
				if(fechaInicio.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && fechaInicio.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
				{
					listaPeriodos.add(fechaInicio.getTime());
					logger.debug("------------Fecha calendarizada-------: " + listaPeriodos.get(count));
					count++;				
				}
				else
				{
					while(fechaInicio.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY || fechaInicio.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
					{
						fechaInicio.add(Calendar.DAY_OF_WEEK, 1);					
					}
					listaPeriodos.add(fechaInicio.getTime());
					logger.debug("------------Fecha calendarizada-------: " + listaPeriodos.get(count));
					count++;
				}
			}						
															
		}
		
		//Guardar la calendarización en la base de datos
		//Generar campos para la entidad CompromisoPeriodico
		Medida medida = new Medida();
		Usuario usr = new Usuario();
		CompromisoPeriodico cPeriodico = new CompromisoPeriodico();
		
		medida = medidaDAO.read(idMedida);
		usr = usuarioDAO.read(idUsuarioLoggeado);
		JerarquiaOrganizacional jerarquia = new JerarquiaOrganizacional();		
		jerarquia = jerarquiaDAO.read(usr.getFuncionario().getArea().getJerarquiaOrganizacionalId());
		
		cPeriodico.setLugarPresentacion(jerarquia);
		cPeriodico.setMedida(medida);
		cPeriodico.setMonto(0D);
		Long cPeriodicoId = compromisoDAO.create(cPeriodico);		
		cPeriodico.setCompromisoPeriodicoId(cPeriodicoId);
		
		//Generar campos para la entidad FechaCompromiso		
		for(Date row:listaPeriodos)
		{
			Long idFechaCompromiso;
			FechaCompromiso fechaCompromiso = new FechaCompromiso();
			fechaCompromiso.setCompromisoPeriodico(cPeriodico);
			fechaCompromiso.setFechaCompromiso(row);
			idFechaCompromiso = fechaCompromisoDAO.create(fechaCompromiso);
			fechaCompromiso.setFechaCompromisoId(idFechaCompromiso);
			fechaCompromisoDTO.add(FechaCompromisoTransformer.transformarFechaCompromiso(fechaCompromiso));			
		}

		return fechaCompromisoDTO;
	}

	@Override
	public List<FechaCompromisoDTO> consultarCalendarizacionPorMedidaId(
			Long idMedida) throws NSJPNegocioException {		
		List<FechaCompromisoDTO> fechasCompromiso = new ArrayList<FechaCompromisoDTO>(); 
			//FechaCompromisoTransformer.transformarFechaCompromiso(fechaCompromisoDAO.read(idMedida));
		List<FechaCompromiso> fechaCompromiso = fechaCompromisoDAO.consultarFechaCompromisoPorMedidaId(idMedida); 
		
		if(fechaCompromiso.size()>0)
		{
			for(FechaCompromiso row:fechaCompromiso)
			{
				FechaCompromisoDTO fc = new FechaCompromisoDTO();
				fc = FechaCompromisoTransformer.transformarFechaCompromiso(row);
				fechasCompromiso.add(fc);
			}
		}
		
		return fechasCompromiso;
	}

	@Override
	public List<FechaCompromisoDTO> consultarCalendarizacionPorMedidaIdReducido(
			Long idMedida) throws NSJPNegocioException {
		
		List<FechaCompromisoDTO> fechasCompromiso = new ArrayList<FechaCompromisoDTO>(); 
			//FechaCompromisoTransformer.transformarFechaCompromiso(fechaCompromisoDAO.read(idMedida));
		List<FechaCompromiso> fechaCompromiso = fechaCompromisoDAO.consultarCalendarizacionPorMedidaIdReducido(idMedida); 
		
		if(fechaCompromiso.size()>0)
		{
			for(FechaCompromiso row:fechaCompromiso)
			{
				FechaCompromisoDTO fc = new FechaCompromisoDTO();
				fc = FechaCompromisoTransformer.transformarFechaCompromisoMaestro(row);
				fechasCompromiso.add(fc);
			}
		}
		
		return fechasCompromiso;
	}

	@Override
	public void actualizarFechaCumplimiento(FechaCompromisoDTO fechaCompromiso)
			throws NSJPNegocioException {
		
		if(fechaCompromiso.getFechaCumplimiento()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if(fechaCompromiso.getFechaCompromisoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		
		FechaCompromiso fCompromiso = new FechaCompromiso();
		fCompromiso = fechaCompromisoDAO.read(fechaCompromiso.getFechaCompromisoId());
		fCompromiso.setFechaCumplimiento(fechaCompromiso.getFechaCumplimiento());
				
		fechaCompromisoDAO.update(fCompromiso);
	}

}
