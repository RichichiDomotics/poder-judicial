/**
 * Nombre del Programa : ObtenerFechaActualDelegateImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 23-jun-2011
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
package mx.gob.segob.nsjp.delegate.fecha.impl;

import java.util.Date;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.fecha.ObtenerFechaActualDelegate;
import mx.gob.segob.nsjp.service.fecha.ObtenerFechaActualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Service("obtenerFechaActualDelegate")
public class ObtenerFechaActualDelegateImpl implements ObtenerFechaActualDelegate {

    @Autowired
    private ObtenerFechaActualService obtenerFechaActualService;

    @Override
    public Date obtenerFechaActual() throws NSJPNegocioException {
        return obtenerFechaActualService.obtenerFechaActual();
    }
   
}
