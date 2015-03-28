/**
* Nombre del Programa : ReducirCargaDeTrabajoService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para reducir la carga de trabajo de los Funcionarios
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
package mx.gob.segob.nsjp.service.tarea;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Contrato del servicio para reducir la carga de trabajo de los Funcionarios.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ReducirCargaDeTrabajoService {

	/**
	 * Realiza la reduccion de carga diaria para cada uno de los defensores.
	 * @throws NSJPNegocioException
	 */
	public void reducirCargaDeTrabajo () throws NSJPNegocioException;
}
