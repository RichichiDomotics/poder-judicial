/**
 * Nombre del Programa : EnviarReplicaCasoServiceImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 22 Aug 2011
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
package mx.gob.segob.nsjp.service.test.caso.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.caso.EnviarReplicaCasoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class EnviarReplicaCasoServiceImplTest
        extends
            BaseTestServicios<EnviarReplicaCasoService> {

    
    
    public void testEnviarReplicaCaso() {
        ExpedienteDTO dtoParam = new ExpedienteDTO(842L);
        try {
            super.service.enviarReplicaCaso(dtoParam);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }
    
    public void testActualizarExpedienteReplicado() {
        Long idExpediente = 842L;
        try {
            super.service.actualizarExpedienteReplicado(idExpediente);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

}
