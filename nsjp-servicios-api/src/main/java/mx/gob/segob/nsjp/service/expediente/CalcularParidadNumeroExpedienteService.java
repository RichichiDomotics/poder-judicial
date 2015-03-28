/**
* Nombre del Programa : CalcularParidadNumeroExpedienteService.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/07/2011
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
package mx.gob.segob.nsjp.service.expediente;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Interfaz del servicio de negocio para realizar la consulta y/o el cálculo
 * del número de paridad de un expediente.
 * 
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface CalcularParidadNumeroExpedienteService {
	/**
	 * Consulta el valor de paridad de un número de expediente,
	 * Si la paridad no existe entonces se consulta el último número de expediente que tenga
	 * paridad asignada y se asigna la paridad contraria, guardando el expediente.
	 * Si no se encuentra un número de expediente con paridad asignada se asigna una paridad
	 * inicial al número de expediente acutal de PAR
	 * @param numeroExpedienteId
	 * @return True si la paridad es Par, false en otro caso, null si no existe el número de expediente
	 */
	Boolean consultarParidadDeNumeroExpediente(Long numeroExpedienteId) throws NSJPNegocioException;

}
