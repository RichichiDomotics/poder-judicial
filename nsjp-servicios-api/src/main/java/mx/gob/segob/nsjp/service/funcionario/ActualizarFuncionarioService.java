/**
* Nombre del Programa : ActualizarFuncionarioService.java
* Autor                            : jmartinez
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    
* Marca de cambio        : N/A
* Descripcion General    : Servicio para actualizar los datos de un funcionario
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
 * Servicio para actualizar un Funcionario.
 * @version 1.0
 * @author jmartinez
 *
 */
public interface ActualizarFuncionarioService {
	
	/**
	 * Servicio que permite actualizar un Funcionario
	 * @param defensorDTO información del Funcionario
	 * @return FuncionarioDTO información del Funcionario guardada
	 * @throws NSJPNegocioException
	 */
	public FuncionarioDTO modificarDefensor(FuncionarioDTO defensorDTO)
	throws NSJPNegocioException; 
	
}
