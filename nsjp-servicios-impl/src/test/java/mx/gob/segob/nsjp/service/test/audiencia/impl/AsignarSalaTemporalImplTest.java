/**
 * Nombre del Programa : AsignarSalaTemporalImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Jun 2011
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

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.service.audiencia.AsignarSalaTemporalService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class AsignarSalaTemporalImplTest
        extends
            BaseTestServicios<AsignarSalaTemporalService> {

    public void testAsignarSalaTemporal(){
        try {
            
            final SalaAudienciaDTO sa = new SalaAudienciaDTO();
            sa.setMotivo("porque estoy probando de nuevo");
            sa.setDomicilioSala("revo 1181");
            sa.setUbicacionSala("piso 8");
            AudienciaDTO au = new AudienciaDTO();
            au.setId(7L);
            au.setSala(sa);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, 6);
            cal.set(Calendar.DATE, 29);
            cal.set(Calendar.HOUR_OF_DAY, 16);
            cal.set(Calendar.MINUTE, 30);
            au.setFechaEvento(cal.getTime());
            au.setDuracionEstimada(90);
            service.asignarSalaTemporal(au);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
            logger.error(e);
        }
    }
    
}
