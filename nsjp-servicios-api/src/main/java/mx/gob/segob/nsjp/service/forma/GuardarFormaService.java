/**
* Nombre del Programa : GuardarFormaService.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/07/2011
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
package mx.gob.segob.nsjp.service.forma;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;

/**
 * Interfaz del servicio de negocio para almacenar o actualizar una forma
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface GuardarFormaService {
	/**
	 * Almacena los cambios hechos a una forma o crea una forma nueva en caso
	 * de no contar con el ID de la forma
	 * @param forma Datos de origen
	 * @return Identificador de la forma creada o actualizada
	 * @throws NSJPNegocioException
	 */
	Long guardarForma(FormaDTO forma) throws NSJPNegocioException;
	
}
