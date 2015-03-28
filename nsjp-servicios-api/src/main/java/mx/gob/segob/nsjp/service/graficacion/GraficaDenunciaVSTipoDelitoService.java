/**
* Nombre del Programa : GraficaDenunciaVSTipoDelitoService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para obtener la informacion de la Grafica DenunciaVSTipoDelito
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
 * Contrato del servicio para obtener la informacion de la Grafica DenunciaVSTipoDelito.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface GraficaDenunciaVSTipoDelitoService {
	
	/**
	 * Obtiene los total de expedientes por mes de acuerdo al rango de fechas.
	 * @author cesarAgustin
	 * @param fechaInicial
	 * @param fechaFin
	 * @return
	 */
	public List<Object[]> expedientesPorMes(Date fechaInicial, Date fechaFin);

	/**
	 * Obtiene los delitos de acuerdo al tipo y mes segun el rango de fechas
	 * @author cesarAgustin
	 * @param fechaInicial
	 * @param fechaFin
	 * @param tipo
	 * 			<li>true: grave<li>
	 * 			<li>false: false<li>
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<Object[]> delitosTipoPorMes(Date fechaInicial, Date fechaFin,
			boolean tipo);
	
}
