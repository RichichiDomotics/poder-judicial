/**
* Nombre del Programa : ConsultarFuncionarioExternoService.java
* Autor                            : EdgarTE
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10 Apr 2012
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
package mx.gob.segob.nsjp.service.funcionario;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoWSDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioWSDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public interface ConsultarFuncionarioExternoService {
	
	/**
	 * M&eacute;todo utilizado para llevar a cabo la consulta de los funcionarios en base a un 
	 * criterio din&aacute;mico.
	 * @param criterio - El criterio sobre el cual se va a llevar a cabo la consulta.
	 * @return List<FuncionarioDTO> - Lista de funcionarios que cumplen con el criterio 
	 * 		   de b&uacute;squeda. 
	 * @throws NSJPNegocioException
	 */
	public List<FuncionarioWSDTO> consultarFuncionariosXCriterio(CriterioConsultaFuncionarioExternoWSDTO 
			criterioConsultaFuncionarioExternoWSDTO) throws NSJPNegocioException;

}
