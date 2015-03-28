/**
 * Nombre del Programa : actualizarEstatusSolicitudServiceImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 29 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Prueba unitaria de ConsultarSolicitudServiceImpl
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

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.solicitud.ActualizarEstatusSolicitudService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria de actualizarEstatusSolicitudServiceImplTest.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class actualizarEstatusSolicitudServiceImplTest
        extends
            BaseTestServicios<ActualizarEstatusSolicitudService> {
    
    public void testActualizarEstatusSolicitud() {
    	String folioSolicitud = "CD/199900001";   	
    	
        try {
        	
            service.actualizaEstatusSolicitud(folioSolicitud, EstatusSolicitud.CERRADA);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }    
}
