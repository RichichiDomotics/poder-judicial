/**
* Nombre del Programa : IngresarPeritoService.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    
* Marca de cambio        : N/A
* Descripcion General    : Servicio para designar un abogado defensor
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
package mx.gob.segob.nsjp.service.funcionario;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Servicio para registrar un Perito.
 * @version 1.0
 * @author rgama
 *
 */
public interface IngresarFuncionarioService {

	/**
	 * Servicio que permite registrar un Funcionario
	 * @param PeritoDTO información del Funcionario
	 * @throws NSJPNegocioException
	 */
	public FuncionarioDTO ingresarFuncionario(FuncionarioDTO peritoDTO)
	throws NSJPNegocioException; 
}
