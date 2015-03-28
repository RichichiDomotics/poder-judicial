/**
 * Nombre del Programa : ConfiguracionDelegateImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 14 Oct 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Delegate para lo relacionado la configuración del sistema
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
package mx.gob.segob.nsjp.delegate.configuracion.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.configuracion.ConfiguracionDelegate;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO;
import mx.gob.segob.nsjp.service.configuracion.ObtenerConfiguracionService;

/**
 * Delegate para lo relacionado la configuración del sistema.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
public class ConfiguracionDelegateImpl implements ConfiguracionDelegate {
    @Autowired
    ObtenerConfiguracionService conusltaService;

    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.delegate.configuracion.ConfiguracionDelegate#
     * obtgenerConfiguracionGlobal()
     */
    @Override
    public ConfiguracionDTO obtgenerConfiguracionGlobal() {
        return conusltaService.obtgenerConfiguracionGlobal();
    }

	@Override
	public ConfInstitucionDTO consultarInstitucionActual()
			throws NSJPNegocioException {
		return conusltaService.consultarInstitucionActual();
	}

}
