/**
    * Nombre del Programa : actualizarEstatusAlarmaServiceImplTest.java
 * Autor                            : rama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 02 Sep 2011
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
package mx.gob.segob.nsjp.service.test.alarma.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.alarmas.EstatusAlarmaAlerta;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.alarmas.AlertaDTO;
import mx.gob.segob.nsjp.service.alerta.ActualizarEstatusAlertaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria de actualizarEstatusAlarmaServiceImplTest.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class actualizarEstatusAlarmaServiceImplTest
        extends
            BaseTestServicios<ActualizarEstatusAlertaService> {
    
    public void testActualizarEstatusAlertaService() {   	
        try {        	
        	AlertaDTO loAlertaDTO = new AlertaDTO();
        	loAlertaDTO.setAlertaId(4L);
        	loAlertaDTO.setFechaAlerta(new Date());
            service.actualizaEstatusyFechaAlerta(loAlertaDTO, EstatusAlarmaAlerta.NO_EJECUTADA);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }    
}
