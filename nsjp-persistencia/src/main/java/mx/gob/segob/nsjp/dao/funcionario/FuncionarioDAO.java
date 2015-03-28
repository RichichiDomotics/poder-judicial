/**
* Nombre del Programa : FuncionarioDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para los metodos de acceso a datos de la entidad Funcionario
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
package mx.gob.segob.nsjp.dao.funcionario;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.funcionario.TipoEspecialidad;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoDTO;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;

/**
 * Contrato para los metodos de acceso a datos de la entidad Funcionario.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface FuncionarioDAO extends GenericDao<Funcionario, Long> {

    Funcionario consultarFuncionarioXExpediente(String expediente);

	/**
	 * Metodo que permite consultar a una lista de funcionarios por su puesto
	 * Este metodo sera usado para obtener la informacion de:
	 * -Abogado defensor.
	 * -Coordinador de defensores.
	 * -Coordinador de fiscales.
	 * -Coordinador de servicios periciales.
	 * -Fiscal general.
	 * -Fiscal.
	 * -Juez.
	 * -Magistrado.
	 * @param idPuesto
	 * @return
	 */
	public List<Funcionario> consultarFuncionariosPorRol(Long idPuesto);
	
	/**
	 * Servicio que consulta los Funcionarios por ROl, para la versión multi Rol.
	 * 
	 * @param idRol
	 * @return
	 */
	List<Funcionario> consultarFuncionariosPorRolMultiRol(Long idRol);
	
	/**
	 * Consulta los jueces disponibles para una cierta fecha y que estén desocupados durante el 
	 * toda la duración estimada de la audiencia.
	 * 
	 * @param fecha fecha y hora de la audiencia
	 * @param duracionEstimada Duración estimada en minutos	
	 * @param especialidad Especialidad del funcionario a filtrat
	 * @param puesto Puesto del funcionario a filtrar
	 * @param paridadJuezRequerida
	 * @param funcionario Toma el 
	 * @return Lista de jueces disponibles para la audiencia a programar
	 */
	List<Funcionario> consultarFuncionariosPorEspecialidadYPuestoDisponiblesParaFechaYHoraAudiencia
	(Date fecha,Integer duracionEstimada,TipoEspecialidad especialidad,Puestos puesto, Boolean paridadJuezRequerida, Funcionario funcionario) throws NSJPNegocioException;

	/**
	 * Elimina una lista de funcionarios audiencia
	 * @param funcs
	 */
	void deleteFuncionarioAudiencia(List<FuncionarioAudiencia> funcs);
	
	   /**
     * Metodo que permite filtrar por el nombre, institucion y especialidad del Perito
     * @param filtro
     * @return
     */
    public List<Funcionario> consultarFuncionarioXFiltro(Funcionario filtro);
    
    /**
     * Metodo que permite consultar los defensores de acuerdo a un tipo de Defensa
     * @param idTipoDefensa Recibe el tipo de defensa que se va a consultar.
     * @return Devuelve un listado de defensores que ejercen ese tipo de Defensa.
     * @author ricardo
     */
    public List<Funcionario> consultarDefensorPorTipoDefensa(Long idTipoDefensa);
    
    
    /**
     * Metodo que permite consultar los defensores
     * @return Devuelve un listado de defensores
     * @author ricardo
     */
    public List<Funcionario> consultarDefensores();

    /**
     * Devuelve un listado de PERITOS que coinciden con el nombre, apellido
     * paterno y apellido materno que se le pasan dentro del objeto
     * @{code criterios}
     * @return Una lista que contiene los peritos que coinciden con el nombre
     * del perito que se le pasa como parametro o la lista vacia en caso de
     * no encontrar coincidencias.
     * @param criterios
     * @return
     */
    List<Funcionario> consultarPeritosPorNombre(Funcionario criterios);

    /**
     * Devuelve un listado de PERITOS que coinciden con el nombre, apellido
     * paterno y apellido materno que se le pasan dentro del objeto
     * @{code criterios}
     * @return Una lista que contiene los peritos que coinciden con el nombre
     * del perito que se le pasa como parametro o la lista vacia en caso de
     * no encontrar coincidencias.
     * @param criterios
     * @return
     */

	public String obtenerDiscriminanteFuncionario(String idFuncionario);

    Funcionario obtenerFuncionarioPorNombreCompleto(String nombreCompleto);
    
    /**
     * Operación que realiza la funcionalidad de asociar el Perito
     * seleccionado con el número de expediente
     * Recibe el perito y el número de expediente
     * @throws NSJPNegocioException
     */
    void asociarPeritoExpediente(Funcionario perito, Expediente Expediente);

    /**
     * Consula el funcionario asociado a una evidencia.
     * @param evidencia La evidencia de la que se busca el funcionario.
     * @return EL funcionario asociado a una evidencia o {@code null} en caso
     * que la evidencia no exista o que no tenga un funcionario asociado.
     */
    Funcionario consultarFuncionarioPorEvidencia(Evidencia evidencia);

    /**
     * Operación que realiza la funcionalidad de consultar el personal o los funcionarios adscritos a un departamento de la Institución.
     * @param idDepartamento
     * @return 
     */
	List<Funcionario> consultarFuncionarioPorDepartamento(Long idDepartamento);


	List<Funcionario> consultarFuncionariosPorAreaYPuesto(Long area, Long puesto);
	
	/**
	 * Obtiene la informacion del defensor por un Id
	 * @param id_Defensor
	 * @return Defensor
	 */
	Funcionario obtenerInformacionDefensorPorId(Long id_Defensor);

	/**
	 * Obtiene los funcionarios subordinados de otro funcionarios.
	 * @param claveFuncionario
	 * @return
	 */
	List<Funcionario> consultarFuncionariosSubordinados(Long claveFuncionario, Long areaId, Long jerarquia);
	
	/**
	 * 
	 * @param expedienteId
	 * @return
	 */
	Funcionario obtenerDefensorAsignadoAExpediente(Long expedienteId);
	/**
	 * Conulta los funcionarios que tienen un usuario asociado para loggearse en el sistema
	 * @return
	 */
	List<Funcionario> obtenerFuncionariosConUsuario()throws NSJPNegocioException;

    Funcionario consultaFuncionarioPorNombreInstitucionPuesto(Funcionario funcionario);

    /**
     * Consulta la disponibilidad de un funcionario para la fecha señalada por time
     * @param claveFuncionario
     * @param time
     * @param limite
     * @return
     */
	boolean existeDisponibilidad(Long claveFuncionario, Date time, Long limite);

	/**
	 * Obtiene el funcionario por su número de empleado
	 * @param numeroEmpleado
	 * @return
	 */
	Funcionario obtenerFuncionarioXNumeroEmpleado(String numeroEmpleado);
	
	Almacen obtenerAlmacenXFuncionario(Long idFuncionario);
	
	Long countFuncionarios();

	List<Funcionario> consultarFuncionariosXAgencia(Long idAgencia);

	List<JerarquiaOrganizacional> consultarAreasXAgencia(Long idAgencia);
	
	/**
	 * Consulta los funcionarios por el discriminante, que representa a nivel de negocio 
	 * las Agencias y/o Tribunales. 
	 * @param catDiscriminanteId
	 * @return
	 */
	List<Funcionario> consultarFuncionariosPorDiscriminante(Long catDiscriminanteId,Long idUIE);
	
	/**
	 * Permite consultar Funcionarios por Numero Expediente y area del numero de expediente
	 * @param numeroExpediente
	 * @param area
	 * @return Funcionario
	 */
	public Funcionario consultarFuncionarioXNumeroExpYTipo (String numeroExpediente, Long area);
	
	/**
	 * M&eacute;todo utilizado para llevar a cabo la consulta de los funcionarios en base a un 
	 * criterio din&aacute;mico.
	 * @param criterio - El criterio sobre el cual se va a llevar a cabo la consulta.
	 * @return List<FuncionarioDTO> - Lista de funcionarios que cumplen con el criterio 
	 * 		   de b&uacute;squeda. 
	 * @throws NSJPNegocioException
	 */
	public List<Funcionario> consultarFuncionariosXCriterio(CriterioConsultaFuncionarioExternoDTO 
			criterioConsultaFuncionarioExternoDTO) throws NSJPNegocioException;
	
}
