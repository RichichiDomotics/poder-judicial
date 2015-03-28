/**
* Nombre del Programa : ConsultarFuncionService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 1 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar las consultar de Funcion
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
package mx.gob.segob.nsjp.service.usuario;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;

/**
 * Contrato del servicio para realizar las consultar de Funcion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ConsultarFuncionService {

	/**
	 * Consultar las funciones de los roles del sistema
	 * @author cesarAgustin
	 * @return Lista de funciones
	 * @throws NSJPNegocioException
	 */
	List<FuncionDTO> consultarFunciones() throws NSJPNegocioException;

	/**
	 * Consulta las funciones relacionadas al rol enviado como parametro
	 * @author cesarAgustin
	 * @param rol
	 * 		<li>rolId<li>
	 * @return Lista de funciones
	 * @throws NSJPNegocioException
	 */
	public List<FuncionDTO> consultarFuncionesByRol(RolDTO rolDTO) throws NSJPNegocioException;
	
	public FuncionDTO consultarFuncionXNombre (FuncionDTO fncDTO) throws NSJPNegocioException;
}
