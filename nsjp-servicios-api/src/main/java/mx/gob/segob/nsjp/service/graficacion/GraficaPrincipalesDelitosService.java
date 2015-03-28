/**
* Nombre del Programa : GraficaPrincipalesDelitosService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para obtener la informacion requerida para la Grafica
 * 							delitos principales
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
 * Contrato del servicio para obtener la informacion requerida para la Grafica.
 * delitos principales.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface GraficaPrincipalesDelitosService {

	/**
	 *  Obtiene el numero de registros de un delito existentes por mes dentro de un rango de fechas.
	 * @author cesarAgustin
	 * @param fechaInicio
	 * @param fechaFin
	 * @param catDelito
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<Object[]> obtenerDelitoPorMes (Date fechaInicio, Date fechaFin,
			Long catDelito) throws NSJPNegocioException; 
}
