/**
* Nombre del Programa : ConsultarTipoSolicitudAudienciaService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20/10/2011
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
package mx.gob.segob.nsjp.service.solicitud;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;

/**
 * Contrato de servicio para consultar los Tipos de Solicitudes de Audiencia.
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface ConsultarTipoSolicitudAudienciaService {

	/**
	 * Servicio que se encarga de consultar la lista de solicitudes siguientes, de acuerdo a 
	 * la audiencia actual y a la clasificación de grupos que se define en la misma clase.
	 * 
	 * @param audienciaActual
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<CatalogoDTO> consultarTipoSolicitudAudienciaSiguientes(TipoAudiencia audienciaActual ) throws NSJPNegocioException ;
	
	
}
