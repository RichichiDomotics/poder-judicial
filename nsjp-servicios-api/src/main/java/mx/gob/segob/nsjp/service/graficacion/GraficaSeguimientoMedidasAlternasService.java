/**
* Nombre del Programa : GraficaSeguimientoMedidasAlternasService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 1 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para obtener la informacion requerida para la grafica
* 							seguimiento de medidas alternas
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
package mx.gob.segob.nsjp.service.graficacion;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.medida.TipoMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Contrato del servicio para obtener la informacion requerida para la grafica
 * seguimiento de medidas alternas.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface GraficaSeguimientoMedidasAlternasService {

	/**
	 * Obtiene las medidas alternas de un determinado tipo, registrados en un rango de fechas.
	 * @author cesarAgustin
	 * @param fechaInicio
	 * @param fechaFin
	 * @param prisiónPreventiva
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long obtenerMedAltPorFechasYTipoNedida(Date fechaInicio, Date fechaFin,
			TipoMedida tipoMedida) throws NSJPNegocioException;
}
