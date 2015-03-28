/**
* Nombre del Programa : GraficaCasosRemitidosService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para obtener informacion requerida para grafica casos remitidos
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
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Contrato del servicio para obtener informacion requerida para grafica casos remitidos.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface GraficaCasosRemitidosService {

	/**
	 * Obtiene el numero de remitidos por mes, dentro de un rango de fechas
	 * @author cesarAgustin
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<Object[]> obtenerCasosRemitidosPorMes (Date fechaInicio, Date fechaFin) throws NSJPNegocioException;
	
	
}
