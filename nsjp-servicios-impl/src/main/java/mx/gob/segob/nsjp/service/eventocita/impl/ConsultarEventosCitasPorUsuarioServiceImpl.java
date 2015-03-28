/**
 * Nombre del Programa : ConsultarEventosCitasPorUsuarioServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 27-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.eventocita.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.alarma.AlarmaDAO;
import mx.gob.segob.nsjp.dao.alerta.AlertaDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.tarea.EventoCitaDAO;
import mx.gob.segob.nsjp.dao.tarea.TareaDAO;
import mx.gob.segob.nsjp.dao.usuario.UsuarioDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Alarma;
import mx.gob.segob.nsjp.model.Alerta;
import mx.gob.segob.nsjp.model.EventoCita;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Tarea;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.eventocita.ConsultarEventosCitasPorUsuarioService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.tarea.ActualizarTareaFuncionarioService;
import mx.gob.segob.nsjp.service.tarea.impl.transform.EventoCitaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConsultarEventosCitasPorUsuarioServiceImpl implements
        ConsultarEventosCitasPorUsuarioService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(ConsultarEventosCitasPorUsuarioServiceImpl.class);


    @Autowired
    private EventoCitaDAO eventoCitaDao;

    @Autowired
    private UsuarioDAO usuarioDao;

	@Autowired
	private FuncionarioDAO funcionarioDAO;
	
	@Autowired
	private TareaDAO tareaDAO;
    
    @Autowired
    private ActualizarTareaFuncionarioService actualizarTareaFuncionarioService;
    
    @Autowired
    private AlarmaDAO alarmaDAO;
    
    @Autowired
    private AlertaDAO alertaDAO;
    
    
    @Override
    public List<EventoCitaDTO> consultarEventosCitasPorUsuario(UsuarioDTO usuarioDto)
            throws NSJPNegocioException {
        if (usuarioDto == null || usuarioDto.getIdUsuario() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        FuncionarioDTO funcionarioDto = usuarioDto.getFuncionario();
        Funcionario funcionario = null;
        if (funcionarioDto == null) {
            Usuario usuario = usuarioDao.read(usuarioDto.getIdUsuario());
            funcionario = usuario.getFuncionario();
        }else{
            funcionario = FuncionarioTransformer.transformarFuncionario(funcionarioDto);
        }
        List<EventoCita> eventosCita = eventoCitaDao.
                consultarEventosCitasPorFuncionario(funcionario);
        List<EventoCitaDTO> eventosCitaDto = Collections.EMPTY_LIST;
        if (!eventosCita.isEmpty()) {
            eventosCitaDto = new LinkedList<EventoCitaDTO>();
            for (EventoCita eventoCita : eventosCita) {
                EventoCitaDTO eventoCitaDto = EventoCitaTransformer.
                        transformarEventoCita(eventoCita);
                eventosCitaDto.add(eventoCitaDto);
            }
        }
        return eventosCitaDto;
    }

	
    @Override
	public List<EventoCitaDTO> consultarEventosCitasPorUsuario(
			UsuarioDTO usuarioDto, Date fechaInicio, Date fechaFin) throws NSJPNegocioException {
        if (usuarioDto == null || usuarioDto.getIdUsuario() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        FuncionarioDTO funcionarioDto = usuarioDto.getFuncionario();
        Funcionario funcionario = null;
        if (funcionarioDto == null) {
            Usuario usuario = usuarioDao.read(usuarioDto.getIdUsuario());
            funcionario = usuario.getFuncionario();
        }else{
            funcionario = FuncionarioTransformer.transformarFuncionario(funcionarioDto);
        }
        List<EventoCita> eventosCita = eventoCitaDao.
                consultarEventosCitasPorFunciconario(funcionario.getClaveFuncionario(),fechaInicio,fechaFin);
        List<EventoCitaDTO> eventosCitaDto = Collections.EMPTY_LIST;
        if (!eventosCita.isEmpty()) {
            eventosCitaDto = new LinkedList<EventoCitaDTO>();
            for (EventoCita eventoCita : eventosCita) {
                EventoCitaDTO eventoCitaDto = EventoCitaTransformer.
                        transformarEventoCita(eventoCita);
                eventosCitaDto.add(eventoCitaDto);
            }
        }
        return eventosCitaDto;
	}
	
    @Override
	public EventoCitaDTO consultarEventoCitaPorId(Long id)
			throws NSJPNegocioException {
		EventoCita cita = eventoCitaDao.read(id);
		return EventoCitaTransformer.transformarEventoCita(cita);
	}
	
    @Override
	public void actualizarEventoCita(EventoCitaDTO evento)
			throws NSJPNegocioException {

    	EventoCita cita = eventoCitaDao.read(evento.getEventoCitaId());
    	
    	if(!cita.getNombreEvento().equals(evento.getNombreEvento())){
    		cita.setNombreEvento(evento.getNombreEvento());
    	}
    	
    	if(!cita.getDescripcionEvento().equals(evento.getDescripcionEvento())){
    		cita.setDescripcionEvento(evento.getDescripcionEvento());
    	}
    	
    	if(!cita.getDireccion().equals(evento.getDireccion())){
    		cita.setDireccion(evento.getDireccion());
    	}
    	
    	if(!cita.getFechaInicioEvento().equals(evento.getFechaInicioEvento())){
    		cita.setFechaInicioEvento(evento.getFechaInicioEvento());
    	}
    	
    	if(!cita.getFechaFinEvento().equals(evento.getFechaFinEvento())){
    		cita.setFechaFinEvento(evento.getFechaFinEvento());
    	}
    	
    	Double duracion = 0.0;
    	if(cita.getTarea() != null){
    		duracion = (double) (cita.getTarea().getNtiempoReal() / 60);
    	}
    	eventoCitaDao.update(cita);
    	
    	Tarea	 tarea = cita.getTarea();
    	TareaDTO tareaN = evento.getTarea();
    	if(!tarea.getValor().getValorId().equals(tareaN.getValor().getIdCampo())){
    		tarea.setValor(new Valor(tareaN.getValor().getIdCampo()));
    	}
    	int minutos1 = (evento.getFechaInicioEvento().getHours() * 60) + evento.getFechaInicioEvento().getMinutes();
		int minutos2 = (evento.getFechaFinEvento().getHours() * 60) + evento.getFechaFinEvento().getMinutes();
		int minutos = minutos2 - minutos1;
		double mins = ((double)minutos)/60;
		if(duracion != mins){
			Funcionario funcionario = funcionarioDAO.read(cita.getAgendaFuncionario().getFuncionario().getClaveFuncionario());
			funcionario.setCargaTrabajo(funcionario.getCargaTrabajo() - duracion + mins);
			funcionarioDAO.saveOrUpdate(funcionario);
		}
		tarea.setNtiempoReal((short)minutos);
		tareaDAO.update(tarea);
		
		Alarma alarma = alarmaDAO.consultarAlarmasEvento(cita.getEventoCitaId().toString());
		
		for (Alerta alerta : alarma.getAlertas()) {
			Calendar fechaAlerta = Calendar.getInstance();
			fechaAlerta.setTime(cita.getFechaInicioEvento());
			fechaAlerta.add(Calendar.MINUTE, -30);
			alerta.setFechaAlerta(fechaAlerta.getTime());
			alertaDAO.update(alerta);
		}
		
		alarma.setFechaAlarma(cita.getFechaInicioEvento());
		alarma.setMotivo(cita.getDescripcionEvento());
		alarmaDAO.update(alarma);
	}

    @Override
	public void eliminarEventoCita(EventoCitaDTO evento)
			throws NSJPNegocioException {
		EventoCita cita = eventoCitaDao.read(evento.getEventoCitaId());
    	Double duracion = 0.0;
    	if(cita.getTarea() != null){
    		duracion = (double) (cita.getTarea().getNtiempoReal() / 60);
    	}
		if(duracion > 0){
			Funcionario funcionario = funcionarioDAO.read(cita.getAgendaFuncionario().getFuncionario().getClaveFuncionario());
			funcionario.setCargaTrabajo(funcionario.getCargaTrabajo() - duracion);
			funcionarioDAO.saveOrUpdate(funcionario);
		}
		Alarma alarma = alarmaDAO.consultarAlarmasEvento(cita.getEventoCitaId().toString());
		
		if(alarma != null){		
			logger.info("La alarma con id " + alarma.getAlarmaId() + " se eliminara");
			alarmaDAO.delete(alarma);
		}
		logger.info("El eventoCita con id " + cita.getEventoCitaId() + " se eliminara");
		eventoCitaDao.delete(cita);		
	}
    
    @SuppressWarnings("unchecked")
	@Override
	public List<EventoCitaDTO> consultarEventosCitasPorFuncionario(
			FuncionarioDTO funcionarioDto, Date fechaInicio, Date fechaFin) throws NSJPNegocioException {
        if (funcionarioDto == null || funcionarioDto.getClaveFuncionario() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        List<EventoCita> eventosCita = eventoCitaDao.
                consultarEventosCitasPorFunciconario(funcionarioDto.getClaveFuncionario(),fechaInicio,fechaFin);
        List<EventoCitaDTO> eventosCitaDto = Collections.EMPTY_LIST;
        if (!eventosCita.isEmpty()) {
            eventosCitaDto = new LinkedList<EventoCitaDTO>();
            for (EventoCita eventoCita : eventosCita) {
                EventoCitaDTO eventoCitaDto = EventoCitaTransformer.
                        transformarEventoCita(eventoCita);
                eventosCitaDto.add(eventoCitaDto);
            }
        }
        return eventosCitaDto;
	}
   
}
