/**
 * Nombre del Programa : ConsultarEventosCitasPorUsuarioServiceImplTest.java
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
package mx.gob.segob.nsjp.service.test.eventocita.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.eventocita.ConsultarEventosCitasPorUsuarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarEventosCitasPorUsuarioServiceImplTest
    extends BaseTestServicios<ConsultarEventosCitasPorUsuarioService> {

    public void testConsultarEventosCitasPorUsuarioService(){
        try {
            logger.info("Probando el servicio de: ConsultarEventosCitasPorUsuarioService");
            assert service != null;
            UsuarioDTO usuarioDto = new UsuarioDTO();
            usuarioDto.setIdUsuario(16L);
            List<EventoCitaDTO> listaEventos = service.consultarEventosCitasPorUsuario(usuarioDto);
            assertNotNull("listaEventos", listaEventos);
            for (EventoCitaDTO eventoCitaDTO : listaEventos) {
				logger.info("Evento de Cita:"+ eventoCitaDTO);
				logger.info("Evento de Cita:"+ eventoCitaDTO.getEventoCitaId());
				
			}
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarEventosCitasPorUsuarioService");
        }
    }
    
//    public List<EventoCitaDTO> consultarEventosCitasPorUsuario(
//			UsuarioDTO usuarioDto, Date fechaInicio, Date fechaFin) throws NSJPNegocioException {
    @SuppressWarnings("deprecation")
	public void testConsultarEventosCitasPorUsuario(){
        try {
            logger.info("Probando el servicio de: ConsultarEventosCitasPorUsuarioService");
            assert service != null;
            UsuarioDTO usuarioDto = new UsuarioDTO();
            usuarioDto.setIdUsuario(17L);
            
            //Obtener la fecha actual del sistema.
            Calendar c = Calendar.getInstance();
            //Colocar en cero las horas, minutos y segundos.
            DateUtils.setHoraMinutoSegundoCero(c);
            //Obtener como Date 
            Date fechaInicial= c.getTime();

            //Sumar un dia
            DateUtils.sumarDias(c, 1);
          //Obtener como Date
            Date fechaFinal = c.getTime();
            
            logger.info("Fecha Inicio:"+  fechaInicial);
            logger.info("Fecha Fin:"+  fechaFinal);
//			Date fechaFin= DateUtils.obtener("28/10/2011");
			
			List<EventoCitaDTO> listaEventos = service.consultarEventosCitasPorUsuario(usuarioDto, fechaInicial, fechaFinal);
            assertNotNull("listaEventos", listaEventos);
            logger.info(" listaEventos"+ listaEventos);
            for (EventoCitaDTO eventoCitaDTO : listaEventos) {
				logger.info("Evento de Cita:"+ eventoCitaDTO);
				logger.info("Evento de Cita:"+ eventoCitaDTO.getStrFechaInicioEvento());
				logger.info("Evento de Cita:"+ eventoCitaDTO.getStrFechaFinEvento());
				logger.info("Evento de Cita:"+ eventoCitaDTO.getHoraInicioEvento());
				logger.info("Evento de Cita:"+ eventoCitaDTO.getHoraFinEvento());
			}
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarEventosCitasPorUsuarioService");
        }
    }
}

