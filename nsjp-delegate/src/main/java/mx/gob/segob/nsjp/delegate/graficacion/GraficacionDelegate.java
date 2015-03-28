/**
* Nombre del Programa : GraficacionDelegate.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos para el acceso a los servicios de Graficacion
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
package mx.gob.segob.nsjp.delegate.graficacion;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.medida.TipoMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Contrato de metodos para el acceso a los servicios de Graficacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface GraficacionDelegate {

	/**
	 * Obtiene el numero de casos de individuos remitidos.
	 * @author cesarAgustin
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<Object[]> obtenerCasosRemitidos(Date fechaInicio, Date fechaFin) throws NSJPNegocioException;

	/**
	 * Obtiene las medidas cautelares de un determinado tipo, registrados en un rango de fechas.
	 * @author cesarAgustin
	 * @param fechaInicio
	 * @param fechaFin
	 * @param prisiónPreventiva
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long obtenerMedCauPorFechasYTipoNedida(Date fechaInicio,
			Date fechaFin, TipoMedida tipoMedida) throws NSJPNegocioException;

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
