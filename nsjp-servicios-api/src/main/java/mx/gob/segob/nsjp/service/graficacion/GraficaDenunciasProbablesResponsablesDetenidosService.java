/**
* Nombre del Programa : GraficaDenunciasProbablesResponsablesDetenidosService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para obtener la informacion requerida para la grafica Denuncias-Probables Responsables-Detenidos
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
 * Contrato del servicio para obtener la informacion requerida para la grafica Denuncias-Probables Responsables-Detenidos.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface GraficaDenunciasProbablesResponsablesDetenidosService {

	/**
	 * Obtiene el numero de expedinetes que cumplem con la condicion detencion requerida y que se encuentren en 
	 * un rango de fechas
	 * @author cesarAgustin
	 * @param fechaInicio
	 * @param fechaFin
	 * @param condicion
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<Object[]> obtenerExpedientesPorCondicionDetencionInvYMes (Date fechaInicio, Date fechaFin, Boolean condicion) throws NSJPNegocioException; 
	
}
