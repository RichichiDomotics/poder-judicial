/**
 * Nombre del Programa : ReductorAutomaticoActividadesServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Jul 2011
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
package mx.gob.segob.nsjp.service.infra;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.tarea.ReducirCargaDeTrabajoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
public class ReductorAutomaticoCargaServiceImpl {
    private final static Logger logger = Logger
            .getLogger(ReductorAutomaticoCargaServiceImpl.class);

    @Autowired
    private ReducirCargaDeTrabajoService servicio;
    // Se ejecuta todos los dias a las 23 Hrs
    @Scheduled(cron = "0 0 23 * * ?")
    public void ejecutarServicio() {
        try {
            logger.info("Inicia - ejecutarServicio(...)");
            servicio.reducirCargaDeTrabajo();
            logger.info("Fin - ejecutarServicio(...)");
        } catch (NSJPNegocioException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage());
        }
    }

}
