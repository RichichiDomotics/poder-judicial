/**
 * Nombre del Programa : ConsultarEventosServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 1 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.test.audiencia.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.BandejaNotificador;
import mx.gob.segob.nsjp.comun.enums.audiencia.Eventos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.EventoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.audiencia.ConsultarEventosService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ConsultarEventosServiceImplTest
        extends
            BaseTestServicios<ConsultarEventosService> {
    public void testConsultarEventosNuevo() {
        EventoDTO in = new EventoDTO();
        in.setBandeja(BandejaNotificador.NUEVO);
//        in.setTipoEvento(Eventos.AUDENCIA);
        try {
            List<EventoDTO> data = service.consultarEventos(in);
            assertNotNull("Evento no puede ser nulo", data);
            logger.info(" Total:"+ data.size());
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }
    public void testConsultarEventosSeguimiento() {
        EventoDTO in = new EventoDTO();
        in.setBandeja(BandejaNotificador.SEGUIMIENTO);
        in.setTipoEvento(Eventos.AUDENCIA);
        try {
            List<EventoDTO> data = service.consultarEventos(in);
            assertNotNull("Evento no puede ser nulo", data);
            assertFalse("Debe existir al menos un evento", data.isEmpty());
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

    public void testObtenerEventoAudiencia() {
        logger.info("Inicia  - testObtenerEventoAudiencia()");
        EventoDTO in = new EventoDTO();
        in.setId(208L);
        in.setTipoEvento(Eventos.AUDENCIA);
        try {
            EventoDTO ev = service.obtenerEvento(in);
            logger.info("Fin  - testObtenerEventoAudiencia()");
            assertNotNull("Evento no puede ser nulo", ev);
            logger.debug("evento :: " + ev);
            assertFalse("Los involucrados no pueden ser nulos", ev
                    .getInvolucrados().isEmpty());

            for (InvolucradoDTO invo : ev.getInvolucrados()) {
                logger.debug("invo :: " + invo);
                assertNotNull("Las notificaciones no puede ser nulo",
                        invo.getNotificaciones());
                logger.debug("invo.notificaciones :: " + invo.getNotificaciones());
            }
            
            for(FuncionarioDTO fun : ev.getFuncionarios()){
            	logger.debug("invo :: " + fun);
                assertNotNull("Las notificaciones no puede ser nulo",
                		fun.getNotificaciones());
                logger.debug("invo.notificaciones :: " + fun.getNotificaciones());
            }

        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }

    }
}
