/**
 * Nombre del Programa : FuncionarioDelegate.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato de metodos para el acceso a los servicios de Funcionario
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
package mx.gob.segob.nsjp.delegate.funcionario;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.BitacoraDefensorDTO;
import mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
/**
 * Contrato de metodos para el acceso a los servicios de Funcionario.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface FuncionarioDelegate {

	
    /**
     * Consulta el Funcionario por nï¿½mero de empleado
     * @param FuncionarioDTO el nï¿½mero de empleado del funcionario
     * @return El Funcionario asociado al nï¿½mero de empleado solicitado     * 
     * @throws NSJPNegocioException En caso que alguno de {@code evidenciaDto} o
     * {@code evidenciaDto.evidenciaId} sea {@code null}.
     */
	
    List<FuncionarioDTO> consultarFuncionarioXNumeroEmpleado(FuncionarioDTO evidenciaDto)
    throws NSJPNegocioException;
	
    /**
     * Consulta el Perito (Funcionario) asociado a la evidencia.
     * @param evidenciaDto La evidencia de la que se busca su funcionario.
     * @return El Perito (Funcionario) asociado a la evidencia o {@code null} en
     * caso que la evidencia buscada no exista o que no tenga un Perito
     * (Funcionario) asociado.
     * @throws NSJPNegocioException En caso que alguno de {@code evidenciaDto} o
     * {@code evidenciaDto.evidenciaId} sea {@code null}.
     */
    FuncionarioDTO consultarPeritoPorEvidencia(EvidenciaDTO evidenciaDto)
            throws NSJPNegocioException;

    /**
     * Devuelve un listado de PERITOS que coinciden con el nombre, apellido
     * paterno y apellido materno que se le pasan dentro del objeto
     * @{code criterios}
     * @return Una lista que contiene los peritos que coinciden con el nombre
     * del perito que se le pasa como parametro o la lista vacia en caso de
     * no encontrar coincidencias.
     * @throws NSJPNegocioException
     */
    List<FuncionarioDTO> consultarPeritosPorNombre(FuncionarioDTO criterios)
            throws NSJPNegocioException;

    /**
     * Consulta los defensores disponibles en la defensoria publica
     * @return Defensores encontrados
     */
    public List<FuncionarioDTO> consultarDefensoresActivos() throws NSJPNegocioException;

    /**
     * Servicio para obtener los defensores por tipo de defensoria
     * @param tipodefensoria
     * @return List<FuncionarioDTO>
     * @throws NSJPNegocioException
     */
    List<FuncionarioDTO> consultarDefensoresActivosPorTipoDefensa(
            TipoDefensoria tipodefensoria) throws NSJPNegocioException;

    /**
     * Servicio para obtener la informacion referente al defensor
     * @param defensor
     * @return FuncionarioDTO
     * @throws NSJPNegocioException
     */
    FuncionarioDTO obtenerInformacionDefensor(FuncionarioDTO defensor) throws NSJPNegocioException;

    /**
     * Servicio que designa a un funcionario defensor a una solicitud de defensoria
     * @param solicitudDefensorDTO
     * @throws NSJPNegocioException
     */
    public void designarAbogadoDefensor(SolicitudDefensorDTO solicitudDefensorDTO) throws NSJPNegocioException;

    /**
     * Consulta el funcionario asociado a un numero de expediente.
     * @param expediente El numero de expediente al que esta asociado el
     * funcionario que estamos buscando.
     * @return El funcionario asociado al expediente o @{code null} en caso de
     * no existir el expediente.
     * @throws NSJPNegocioException
     */
    FuncionarioDTO consultarFuncionarioXExpediente(String expediente)
            throws NSJPNegocioException;

    /**
     * Operaciï¿½n que realiza la funcionalidad de asociar el Perito seleccionado
     * con el nï¿½mero de expediente
     * Recibe el perito y el nï¿½mero de expediente
     * @param peritoDto
     * @param expediente
     * @throws NSJPNegocioException
     */
    public void asociarPeritoExpediente(
            FuncionarioDTO peritoDto, ExpedienteDTO numeroExpediente)
            throws NSJPNegocioException;

    /**
     *
     * @param solicitudDefensorDTO
     * @throws NSJPNegocioException
     */
    public void designarAbogadoDefensor(SolicitudDefensorDTO solicitudDefensorDTO, ExpedienteDTO expedienteDTO) throws NSJPNegocioException;

//	/**
//	 * Operaciï¿½n que realiza la funcionalidad de consultar los jueces disponibles en el sistema, que cumplen con los siguientes criterios:
//	 * - Estado activo para la fecha de la audiencia
//	 * - Libre para la fecha-hora de la audiencia y para el tiempo estimado de la audiencia
//	 * - Paridad del nï¿½mero de causa (aplica solo para designar juez automï¿½ticamente)
//	 * 
//	 * @param fechaAudiencia
//	 * @param horaAudiencia
//	 * @param tiempoEstimado
//	 * @param paridad (aplica solo para designar juez automï¿½ticamente
//	 * @return Devuelve el listado de jueces, en caso contrario NULL.
//	 * @throws NSJPNegocioException
//	 */
//	public List<FuncionarioDTO> consultarJuecesDisponibles(Date fechaAudiencia, 
//			String horaAudiencia, String tiempoEstimado, String paridad)throws NSJPNegocioException;
    /**
     * Metodo que permite consultar los defensores
     * @return Devuelve un listado de defensores
     * @throws NSJPNegocioException
     * @author ricardo
     */
    public List<FuncionarioDTO> consultarDefensores() throws NSJPNegocioException;

    /**
     * Metodo que permite consultar los defensores de acuerdo a un tipo de Defensa
     * @param idTipoDefensa Recibe el tipo de defensa que se va a consultar.
     * @return Devuelve un listado de defensores que ejercen ese tipo de Defensa.
     * @author ricardo
     */
    public List<FuncionarioDTO> consultarDefensorPorTipoDefensa(Long idTipoDefensa) throws NSJPNegocioException;

    /**
     * Registra un periodo de vacaciones o incapacidad para el funcionario definido por <code>funcionario</code>
     * @param funcionario Representa al funcionario al cual se le va a asociar el periodo 
     * 		 			  de vacacioines o incapacidad, es obligatorio que el atributo <code>claveFuncionario</code>
     * 					  contenga un valor diferente de <code>null</code>.
     * @param periodo Objeto de tipo EventoCitaDTO que describe el periodo de vacaciones o incapadiad que
     * 				  serï¿½ registrado para el funcionario, los aributos obligatorios son 
     * 				  <code>fechaInicioEvento</code> Fecha en que inicia el periï¿½do
     * 				  <code>fechaFinEvento</code> Fecha en que termina el periï¿½do
     * 				  <code>tipoEvento</code> Tipo de Evento que registra el periï¿½do Vacaiones o Incapacidad
     *  			  <code>nombreEvento</code> Nombre del periï¿½do
     * @return identifador conn el que se registro el periodo en la BDD
     * @throws NSJPNegocioException
     */
    public void registrarVacacionesIncapacidad(FuncionarioDTO funcionario, EventoCitaDTO periodo, UsuarioDTO usuario)throws NSJPNegocioException;
    
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
     * @return FuncionarioDTO con el nombre completo y el puesto
     * @throws NSJPNegocioException
     */
    public List<FuncionarioDTO> consultarFuncionariosPorRol(Long idPuesto)
            throws NSJPNegocioException;
    
    /**
	 * Operaciï¿½n que realiza la funcionalidad de obtener del conjunto de defensores de un 
	 * tipo de Defensa, aquellos que tiene los valores de carga de trabajo mï¿½s baja o menor.
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
	 * Operaciï¿½n que realiza la funcionalidad de seleccionar de un conjunto de elementos, uno aleatoriamente.
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

	/**
	 * Obtiene un listado de funcionarios que pertenecen al areada identificcada por <code>area</code>
	 * tienen un puesto identificado por <code>puesto</code>
	 * @param area identificador del ï¿½rea de la cual se obtendrï¿½n los funcionarios
	 * @param puesto identificador del puesto que tienen los funcionaros
	 * @return lista de funcionarios que satisfacen la bï¿½squeda
	 * @throws NSJPNegocioException
	 */
	public List<FuncionarioDTO> consultarFuncionariosPorAreayPuesto(Long area, Long puesto) throws NSJPNegocioException;
	
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
	 * Operaciï¿½n que realiza la funcionalidad de consultar el personal o los funcionarios adscritos a un departamento de la Instituciï¿½n.
	 * @param idDepartamento
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<FuncionarioDTO> consultarFuncionarioPorDepartamento(Long idDepartamento)throws NSJPNegocioException;
	
	/**
	 * Almacena la bitacora de un expediente que esta manejando un defensor. 
	 * @param bitacora Bitacora con la informaciï¿½n que se va a almacenar
	 * @return identificador con el que se almaceno la bitacora.
	 * @throws NSJPNegocioException
	 */
	public void guardarBitacoraDefensor(BitacoraDefensorDTO bitacora)throws NSJPNegocioException;
	
    /**
     * Metodo que permite guardar la informacion de un Funcionario
     * @param peritoDTO: Informacion del Perito
     * @return El identificador con el que fue guardado el Funcionario 
     * @throws NSJPNegocioException
     */
    public FuncionarioDTO ingresarFuncionario(FuncionarioDTO peritoDTO)
    throws NSJPNegocioException;
    
    /**
     * Metodo que permite modificar la informaciï¿½n del Defensor
     * @param defensorDTO: Informacion del Defensor
     * @return Datos que se guardaron del Defensor 
     * @throws NSJPNegocioException
     */
    public FuncionarioDTO modificarDefensor(FuncionarioDTO defensorDTO)
    throws NSJPNegocioException;
    
    /**
     * Obtiene la lista de los funcionarios subordinados, del funcionario que realiza la consulta
     * @param funcionarioDTO
     * 			Clave del funcionario que realiza la consulta <li>claveFuncionario<li>
     * @return Lista de funcionarios subordinados
     * @throws NSJPNegocioException
     */
    public List<FuncionarioDTO> consultarFuncionariosSubordinados(FuncionarioDTO funcionarioDTO) throws NSJPNegocioException;
    
    /**
     * Obtiene el defensor asignado al expediente enviado como parametro    
     * @param expedienteDTO
     * @return El funcionario asignado al expediente requerido
     * @throws NSJPNegocioException
     */
    public FuncionarioDTO obtenerDefensorAsignadoAExpediente(ExpedienteDTO expedienteDTO) throws NSJPNegocioException;

    /**
     * 
     * @return
     * @throws NSJPNegocioException
     */
    public List<FuncionarioDTO> obtenerFuncionariosConUsuario() throws NSJPNegocioException;
    
    /**
     * Obtiene la informacion del funcionario superior, del funcionario que es enviado como parametro
     * @author cesarAgustin
     * @param funcionarioDTO
     * 			<li>claveFuncionario<li> Identificador del funcionario 
     * @return Funcionario superior obtenido
     * @throws NSJPNegocioException
     */
    public FuncionarioDTO obtenerFuncionarioSuperior(FuncionarioDTO funcionarioDTO) throws NSJPNegocioException;
    
    FuncionarioDTO consultaFuncionarioPorNombreInstitucionYPuesto(FuncionarioDTO funcionarioDto) throws NSJPNegocioException;
    
    /**
	 * Servicio que consultar los funcionarios asociados a un departamento y los
	 * asociados al ï¿½rea, a la que pertenece el departamento.
	 * 
	 * @param idDepartamento
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<FuncionarioDTO> consultarFuncionarioPorDepartamentoYArea(Long idDepartamento)
		throws NSJPNegocioException ;
	
	/**
	 * Obtiene el funcionario de acuerdo al nombre completo.
	 * @author cesarAgustin
	 * @param nombreCompleto
	 * @return
	 * @throws NSJPNegocioException
	 */
	FuncionarioDTO obtenerFuncionarioPorNombreCompleto(String nombreCompleto) throws NSJPNegocioException;
	
	/**
	 * 
	 * @param claveFuncionario
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<ExpedienteDTO> consultarExpedientesDelFuncionario(UsuarioDTO usuario) throws NSJPNegocioException;
	
	/**
	 * 
	 * @param claveFuncionario
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<ExpedienteDTO> consultarExpedientescConPermisoFuncionario(Long claveFuncionario) throws NSJPNegocioException;
	
	/**
	 * Servicio que permite consultar a los funcionarios de una agencia en particular
	 * @param idAgencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<FuncionarioDTO> consultarFuncionariosXAgencia(Long idAgencia)throws NSJPNegocioException;
	
	/**
	 * Servicio que permite consultar que ï¿½reas existen para una agencia
	 * @param idAgencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<JerarquiaOrganizacionalDTO> consultarAreasXAgencia(Long idAgencia)throws NSJPNegocioException;
	
	/**
	 * Consulta los funcionarios por el discriminante, que representa a nivel de negocio 
	 * las Agencias y/o Tribunales.
	 * El rol es opcional si se pasa nulo.
	 * 
	 * @param catDiscriminanteId
	 * @param idRol
	 * @param idUIE (Opcional)
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<FuncionarioDTO> consultarFuncionariosPorDicriminanteYRol(Long catDiscriminanteId, Long idRol, Long idUIE)throws NSJPNegocioException;
	
	 /**
     * Se conecta a la intituciï¿½n indicada por <code>target</code> para consultar
     * Funcionarios por id del catDiscriminante
     * @param catDiscriminanteId
     * @param target
     * @return
     * @throws NSJPNegocioException
     */
    public List<FuncionarioDTO> consultarFuncionariosXTribunal(
      		 Long catDiscriminanteId,Instituciones target) throws NSJPNegocioException;
    
    /**
	 * Servicio que es utilizado para ingresar la foto de un funcionario
	 * @param funcionario
	 * @param adjunto
	 * @throws NSJPNegocioException
	 */
	void adjuntarArchivoAFuncionario(Long idfuncionario,
			ArchivoDigitalDTO adjunto) throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo utilizado para llevar a cabo la consulta de los funcionarios en base a un 
	 * criterio din&aacute;mico.
	 * @param criterio - El criterio sobre el cual se va a llevar a cabo la consulta.
	 * @return List<FuncionarioDTO> - Lista de funcionarios que cumplen con el criterio 
	 * 		   de b&uacute;squeda. 
	 * @throws NSJPNegocioException
	 */
	public List<FuncionarioDTO> consultarFuncionariosXCriterio(CriterioConsultaFuncionarioExternoDTO 
			criterioConsultaFuncionarioExternoDTO) throws NSJPNegocioException;
	
    /**
	 * M&eacute;todo utilizado para llevar a cabo la consulta de los funcionarios pertenencientes 
	 * a una instituci&oacute;n en espec&iacute;fico en base a un criterio establecido.
	 * @param criterioConsultaFuncionarioExternoDTO - El criterio sobre el cual se va a llevar a 
	 * 												  cabo la consulta.
	 * @param target - La instituciÃ³n sobre la cual se va a invocar el servicio.
	 * @return List<FuncionarioDTO> - Lista de funcionarios que cumplen con el criterio 
	 * 		   						  de b&uacute;squeda. 
	 * @throws NSJPNegocioException
	 */
	public List<FuncionarioDTO> consultarFuncionariosExternosXCriterio(CriterioConsultaFuncionarioExternoDTO 
			criterioConsultaFuncionarioExternoDTO, Instituciones target) throws NSJPNegocioException;
}
