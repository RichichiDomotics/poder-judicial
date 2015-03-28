/**
* Nombre del Programa : EliminarFormaService.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22/06/2011
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

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface EliminarFormaService {
	
	/**
	 * Operación que realiza la funcionalidad de eliminar la forma seleccionada.
	 * 
	 * @param idForma
	 * @return Devuelve un valor boleano, verdadero en caso de que haya eliminado la forma, falso en caso contrario.
	 * @throws NSJPNegocioException
	 */
	public void eliminarForma(Long idForma)throws NSJPNegocioException;

}
