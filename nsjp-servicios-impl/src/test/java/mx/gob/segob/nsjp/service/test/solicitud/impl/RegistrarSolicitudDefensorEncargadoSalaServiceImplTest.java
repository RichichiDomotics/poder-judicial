/**
* Nombre del Programa : RegistrarSolicitudDefensorEncargadoSalaServiceImplTest.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 1 Sep 2011
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
package mx.gob.segob.nsjp.service.test.solicitud.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudDefensorEncargadoSalaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public class RegistrarSolicitudDefensorEncargadoSalaServiceImplTest
        extends
            BaseTestServicios<RegistrarSolicitudDefensorEncargadoSalaService> {

    public void testEnviar() {
        try {
            AudienciaDTO aud = new AudienciaDTO();
            aud.setId(207L);
            service.registrarSolicitudDefensorEncargadoSala(aud, 868L);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }
    
}
