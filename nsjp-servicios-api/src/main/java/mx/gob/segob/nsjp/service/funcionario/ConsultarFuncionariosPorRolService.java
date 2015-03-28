/**
* Nombre del Programa : ConsultarFuncionarioPorPuestoService.java
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;

/**
 * Servicio para consultar un Funcionario por puesto.
 * @version 1.0
 * @author rgama
 *
 */
public interface ConsultarFuncionariosPorRolService {

	/**
	 * Servicio que permite consultar una lista de funcionarios por su puesto
	 * Este metodo sera usado para obtener la informacion de:
	 * -Abogado defensor.
	 * -Coordinador de defensores.
	 * -Coordinador de fiscales.
	 * -Coordinador de servicios periciales.
	 * -Fiscal general.
	 * -Fiscal.
	 * -Juez.
	 * -Magistrado.
	 * @param idPuesto: Los identificadores se pueden obtener del enum Puestos
	 * @throws NSJPNegocioException
	 */
	public List<FuncionarioDTO> consultarFuncionariosPorRol(Long idPuesto)
	throws NSJPNegocioException;


	/**
	 * Obtiene un listado de funcionarios que pertenecen al areada identificcada por <code>area</code>
	 * tienen un puesto identificado por <code>puesto</code>
	 * @param area identificador del área de la cual se obtendrán los funcionarios
	 * @param puesto identificador del puesto que tienen los funcionaros
	 * @return lista de funcionarios que satisfacen la búsqueda
	 * @throws NSJPNegocioException
	 */
	public List<FuncionarioDTO> consultarFuncionariosPorAreayPuesto(Long area,
			Long puesto) throws NSJPNegocioException;
	
	/**
	 * Consultar el listado de funcionarios de acuerdo al rol.
	 * Requiere como parametro el Id del Rol, extraido de la enumeración de Roles.
	 * @param idRol
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<FuncionarioDTO> consultarFuncionariosPorRolMultiRol(Long idRol)
			throws NSJPNegocioException;
	

	 /**
     * Obtiene la lista de los funcionarios subordinados, del funcionario que realiza la consulta
     * @param funcionarioDTO
     * 			Clave del funcionario que realiza la consulta <li>claveFuncionario<li>
     * @return Lista de funcionarios subordinados
     * @throws NSJPNegocioException
     */
	public List<FuncionarioDTO> consultarFuncionariosSubordinados(
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException;

	 /**
     * Obtiene el defensor asignado al expediente enviado como parametro    
     * @param expedienteDTO
     * @return El funcionario asignado al expediente requerido
     * @throws NSJPNegocioException
     */
	public FuncionarioDTO obtenerDefensorAsignadoAExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException;

	/**
	 * 
	 * @return
	 */
	public List<FuncionarioDTO> obtenerFuncionarosConUsuario()throws NSJPNegocioException;

	 /**
     * Obtiene la informacion del funcionario superior, del funcionario que es enviado como parametro
     * @author cesarAgustin
     * @param funcionarioDTO
     * 			<li>claveFuncionario<li> Identificador del funcionario 
     * @return Funcionario superior obtenido
     * @throws NSJPNegocioException
     */
	public FuncionarioDTO obtenerFuncionarioSuperior(
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException;

	/**
	 * Obtiene el funcionario de acuerdo al nombre completo.
	 * @author cesarAgustin
	 * @param nombreCompleto
	 * @return
	 * @throws NSJPNegocioException
	 */
	public FuncionarioDTO obtenerFuncionarioPorNombreCompleto(
			String nombreCompleto);	
	
	/**
	 * Consulta los funcionarios por el discriminante, que representa a nivel de negocio 
	 * las Agencias y/o Tribunales.
	 * El rol es opcional si se pasa nulo.
	 * 
	 * @param catDiscriminanteId
	 * @param idRol
	 * @param idUIE (opcional)
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<FuncionarioDTO> consultarFuncionariosPorDicriminanteYRol(Long catDiscriminanteId, Long idRol, Long idUIE)throws NSJPNegocioException;
	
}
