/**
 * Nombre del Programa : ActualizarFechaCompromisoService.java
 * Autor                            : Rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 09-09-2011
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
package mx.gob.segob.nsjp.service.test.convenio.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.service.convenio.ActualizarFechaCompromisoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Rgama
 */
public class ActualizarFechaCompromisoServiceImplTest
    extends BaseTestServicios<ActualizarFechaCompromisoService> {

    public void ActualizarFechaCompromisoService(){
        try {
            logger.info("Probando el servicio de: ActualizarFechaCompromisoService");
            Long iDFechaCompromiso = 2439L;
            
            assert service != null;
            FechaCompromisoDTO loFechaCompromiso = new FechaCompromisoDTO();
            loFechaCompromiso.setFechaCompromisoId(iDFechaCompromiso);
            loFechaCompromiso.setObservaciones("Nuevas observacines");
            loFechaCompromiso.setMonto(99999D);
            loFechaCompromiso.setFechaCumplimiento(new Date());
            loFechaCompromiso.setFechaCompromiso(new Date());
            service.actualizarFechaCompromido(loFechaCompromiso);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ActualizarFechaCompromisoService");
        }
    }
   
}
