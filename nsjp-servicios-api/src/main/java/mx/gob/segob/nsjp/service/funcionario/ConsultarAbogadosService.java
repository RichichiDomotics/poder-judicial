/**
* Nombre del Programa : ConsultarAbogadosService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para los metodos del servicio de busqueda de abogados defensores
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

import mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Contrato para los metodos del servicio de busqueda de abogados defensores.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ConsultarAbogadosService {

	/**
	 * Obtiene los abogados defensores que se encuentran activos.
	 * @return
	 * @deprecated
	 * @throws NSJPNegocioException
	 */
	List<FuncionarioDTO> consultarDefensoresActivos() throws NSJPNegocioException;
	
	/**
	 * Obtiene los abogados defensores por tipo de defensoria
	 * @param tipodefensoria
	 * @return List <DefensorDTO>
	 * @throws NSJPNegocioException
	 */
	List <FuncionarioDTO> consultarDefensoresActivosPorTipoDefensa(TipoDefensoria tipodefensoria
					)throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de obtener del conjunto de defensores de un 
	 * tipo de Defensa, aquellos que tiene los valores de carga de trabajo más baja o menor.
	 * 
	 * Para obtener un subconjunto fue necesario hacer lo siguiente:
	 *  1.-Ordenar la lista de funcionarios haciendo uso de la interfaz Comparable y clase Collections
	 *  2.-Obtener el promedio de carga de trabajo de los funcionarios
	 *  3.-Descartar aquellos funcionarios que tengan una carga de trabajo mayor al promedio.
	 * 
	 * Recibe el conjunto de defensores del tipo de Defensa.
	 * 
	 * @param ldefensoresDTO  lista de defensores
	 * @return Devuelve un listado o subconjunto de Defensores.
	 * @throws NSJPNegocioException
	 */
	List<FuncionarioDTO> obtenerDefensoresConCargaMenor(List<FuncionarioDTO> ldefensoresDTO) throws NSJPNegocioException;

	/**
	 * Operación que realiza la funcionalidad de seleccionar de un conjunto de elementos, uno aleatoriamente.
	 * 
	 * Recibe el listado o conjunto de elementos
	 * 
	 * Devuelve un elemento de ese listado seleccionado aleatoriamente.
	 * 
	 * @param lista
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Object asignarAleatoriamenteElemento(Object[] lista) throws NSJPNegocioException;
}
