/**
* Nombre del Programa : IngresarMedidaService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/08/2011
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
package mx.gob.segob.nsjp.service.medida;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;

/**
 * Contrato del service para ingresar Medidas
 * Es necesario saber el tipo de medida que se debera de introducir: Medida Cautelar o Medida Alterna
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface IngresarMedidaService {
	/**
	 * Servicio que permite ingresar una medida de acuedo al tipo de medida.
	 * La distincion se hace por instancia de la MedidaDTO, ya sea MedidaAlternaDTO o MedidaCautelarDTO
	 * Actualmente se tiene implementado unicamente para MedidaAlternaDTO 
	 * 
	 * @param medidaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long ingresarMedida(MedidaDTO medidaDTO) throws NSJPNegocioException;

}
