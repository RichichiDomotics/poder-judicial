/**
 * Nombre del Programa  : RegistrarVacacionesIncapacidadServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Registra un periodo de vacaciones o incapacidad para 
 * 						  un funcionario
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.tarea.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.eventocita.EstatusEventoCita;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.tarea.TipoEvento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.tarea.AgendaFuncionarioDAO;
import mx.gob.segob.nsjp.dao.tarea.EventoCitaDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.AgendaFuncionario;
import mx.gob.segob.nsjp.model.EventoCita;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.tarea.RegistrarVacacionesIncapacidadService;
import mx.gob.segob.nsjp.service.tarea.impl.transform.EventoCitaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrarVacacionesIncapacidadServiceImpl implements
		RegistrarVacacionesIncapacidadService {

    private final static Logger logger = Logger.getLogger(RegistrarVacacionesIncapacidadServiceImpl.class);
	
	@Autowired
	private EventoCitaDAO eventoCitaDAO;
	@Autowired
	private AgendaFuncionarioDAO agendaFuncionarioDAO;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private FuncionarioDAO funcionarioDAO;
	
	@Override
	public void registrarVacacionesIncapacidad(FuncionarioDTO funcionario,
			EventoCitaDTO periodoDTO, UsuarioDTO usuario)
			throws NSJPNegocioException {

		if((funcionario.getClaveFuncionario() == null) || (periodoDTO.getFechaInicioEvento() == null) 
		|| (periodoDTO.getFechaFinEvento() == null) || (periodoDTO.getTipoEvento() == null)){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		Funcionario defensor = funcionarioDAO.read(funcionario.getClaveFuncionario());
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(periodoDTO.getFechaInicioEvento());
			cal.set(Calendar.HOUR_OF_DAY, 0); 
			cal.set(Calendar.MINUTE, 0); 
			cal.set(Calendar.SECOND, 0);
		Date fechaInicio = cal.getTime();
			cal.set(Calendar.MINUTE, 1);
		periodoDTO.setFechaInicioEvento(cal.getTime());
		
		cal.setTime(periodoDTO.getFechaFinEvento());
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59); 
			cal.set(Calendar.SECOND, 59);
		Date fechaFinal = cal.getTime();
			cal.set(Calendar.SECOND, 0);
		periodoDTO.setFechaFinEvento(cal.getTime());

		
		List<EventoCita> lista = eventoCitaDAO.consultarEventosCitasPorFunciconario(defensor.getClaveFuncionario(), 
											fechaInicio, fechaFinal);
		
		if(!lista.isEmpty()){
			if(periodoDTO.getTipoEvento().getIdCampo() == TipoEvento.VACACIONES.getValorId()){
				throw new NSJPNegocioException(CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO);
			}else if(periodoDTO.getTipoEvento().getIdCampo() == TipoEvento.INCAPACIDAD.getValorId()){
				for(EventoCita cita : lista){
					if(cita.getTipoEvento().getValorId() == TipoEvento.TAREA.getValorId() 
					|| cita.getTipoEvento().getValorId() == TipoEvento.AUDIENCIA.getValorId()){
						eventoCitaDAO.delete(cita);
					}
				}
				List<NumeroExpediente> expedientes = numeroExpedienteDAO.buscarNumeroExpedienteAbieroPorIdFuncionario(defensor.getClaveFuncionario());
				Funcionario coord = new Funcionario(usuario.getFuncionario().getClaveFuncionario());
				for (NumeroExpediente expediente : expedientes) {
					expediente.setFuncionario(coord);
					numeroExpedienteDAO.saveOrUpdate(expediente);
				}
			}
		}
		
		EventoCita evento = EventoCitaTransformer.transformarEventoCita(periodoDTO);
		AgendaFuncionario agenda = agendaFuncionarioDAO.obtenerAgendaFuncionario(defensor.getClaveFuncionario());
		if(agenda == null){
			agenda = new AgendaFuncionario();
			agenda.setFuncionario(defensor);
			Calendar c = Calendar.getInstance();
			c.set(c.get(Calendar.YEAR), 0, 1);
			agenda.setDinicioAgenda(c.getTime());
			c.set(c.get(Calendar.YEAR)+1, 11, 31);
			agenda.setDfinAgenda(c.getTime());
			agenda.setAgendaFuncionarioId(agendaFuncionarioDAO.create(agenda));
		}
		
		evento.setAgendaFuncionario(agenda);
		
		cal.setTime(evento.getFechaInicioEvento());
		Calendar fin = Calendar.getInstance();
		fin.setTime(evento.getFechaFinEvento());
		fin.add(Calendar.DAY_OF_MONTH, 1);
		double carga = 0.0;
		EventoCita nuevoEvento = null;
		String loggear ="";
		while(cal.before(fin)){
			nuevoEvento = new EventoCita();
			nuevoEvento.setAgendaFuncionario(evento.getAgendaFuncionario()); 
			nuevoEvento.setDescripcionEvento("Vacaciones");
			nuevoEvento.setEstatus(new Valor(EstatusEventoCita.NO_ATENDIDO.getValorId()));
			nuevoEvento.setDireccion("N/A");
			nuevoEvento.setTieneAlarma(evento.getTieneAlarma());
			nuevoEvento.setNombreEvento(evento.getNombreEvento());
			nuevoEvento.setTipoEvento(evento.getTipoEvento());
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 1);
				cal.set(Calendar.SECOND, 0);
			nuevoEvento.setFechaInicioEvento(cal.getTime());
				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 0);
			nuevoEvento.setFechaFinEvento(cal.getTime());
			carga+=8.0;
			nuevoEvento.setEventoCitaId(eventoCitaDAO.create(nuevoEvento));
			loggear += evento.toString()+"\n";
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		logger.debug(loggear);
		logger.debug("Carga de trabajo: ["+defensor.getCargaTrabajo()+" + "+carga+"]");
		defensor.setCargaTrabajo(defensor.getCargaTrabajo() + carga);
		logger.debug("Carga de trabajo: ["+defensor.getCargaTrabajo()+"]");
	}

}
