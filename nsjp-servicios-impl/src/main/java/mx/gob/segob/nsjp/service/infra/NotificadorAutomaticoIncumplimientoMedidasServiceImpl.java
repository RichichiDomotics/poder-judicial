/**
 * Nombre del Programa : ReductorAutomaticoActividadesServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Cron para las notificaciones de incumplimientos
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
package mx.gob.segob.nsjp.service.infra;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.compromiso.NotificarIncumplimientoService;
import mx.gob.segob.nsjp.service.usuario.UsuarioService;
import mx.gob.segob.nsjp.service.visitante.VisitanteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * <i>Cron</i> para invocar la notificación de incumplimientos.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
public class NotificadorAutomaticoIncumplimientoMedidasServiceImpl {
    private final static Logger logger = Logger
            .getLogger(NotificadorAutomaticoIncumplimientoMedidasServiceImpl.class);
    @Autowired
    private NotificarIncumplimientoService notificadorService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VisitanteService visitanteService;
    
    // Se ejecuta todos los dias a las 22 Hrs
    @Scheduled(cron = "0 0 22 * * ?")
    public void ejecutarServicio() {
        try {
            logger.info("Inicia - ejecutarServicio(...)");
            this.notificadorService.generarNotificacionIncumplimientos(new Date());
            logger.info("Fin - ejecutarServicio(...)");
        } catch (NSJPNegocioException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage());
        }
    }
    
 // Se ejecuta todos los dias a las cada 15 minutos
    @Scheduled(cron = "0 */15 * * * ?")
    public void ejecutarServicioDesbloqueador() {
        try {
            logger.info("Inicia - ejecutarServicioDesbloqueador(...)");
            this.usuarioService.desbloquearUsuario();
            this.visitanteService.desbloquearVisitante();
            logger.info("Fin - ejecutarServicioDesbloqueador(...)");
        }catch (Exception e) {
//        } catch (NSJPNegocioException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage());
        }
    }

}
