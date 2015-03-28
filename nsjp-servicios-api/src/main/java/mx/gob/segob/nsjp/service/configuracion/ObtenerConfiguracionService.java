/**
* Nombre del Programa : ObtenerConfiguracionService.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Interface para obtener la configuración general del sistema
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
package mx.gob.segob.nsjp.service.configuracion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfiguracionDTO;

/**
 * Interface para obtener la configuración general del sistema.
 * @version 1.0
 * @author vaguirre
 *
 */
public interface ObtenerConfiguracionService {
    /**
     * Recupera la configuración global
     * @return
     */
    ConfiguracionDTO obtgenerConfiguracionGlobal();

    /**
     * Servicio que permite consultar la institución actual
     * @return
     * @throws NSJPNegocioException
     */
	ConfInstitucionDTO consultarInstitucionActual()throws NSJPNegocioException;
}
