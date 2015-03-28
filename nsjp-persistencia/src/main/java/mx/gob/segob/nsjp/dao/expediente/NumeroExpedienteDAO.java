/**
 * Nombre del Programa : NumeroExpedienteDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Interface para el DAO del numero del expediente
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
package mx.gob.segob.nsjp.dao.expediente;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Usuario;

/**
 * Interface para el DAO del numero del expediente.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface NumeroExpedienteDAO
        extends
            GenericDao<NumeroExpediente, Long> {
    
    /**
     * 
     * @param expedienteId
     * @param areaId
     * @return
     */
    NumeroExpediente obtenerNumeroExpediente(Long expedienteId, Long areaId);
    /**
     * 
     * @param expedienteId
     * @param areaId
     * @return
     */
    List<NumeroExpediente> consultarNumeroExpedientes(Long expedienteId, Long areaId);
    
    /**
     * Consulta Numeros de expediente por ExpedienteId, Area y Discriminante 
     * donde solo el ExpedienteId es requerido
     * 
     * @param expedienteId
     * @param areaId
     * @param discriminante
     * @return
     */
    List<NumeroExpediente> consultarNumeroExpedientesXIdExpAreaDiscriminante(Long expedienteId, Long areaId, Long discriminante);
    
    /**
     * Obtiene un objeto de la tabla NumeroExpediente dado el atributo de la
     * tabla cNumeroExpediente que esta mapeado al campo del objeto
     * NumeroExpediente.numeroExpediente.
     * @param numeroExpediente La cadena por la que se buscara en el campo
     * cNumeroExpediente. Esta cadena consta de:
     * 3 caracteres configurables
     * 3 caracteres de estado
     * 3 caracteres de institucion
     * 3 caracteres de distrito
     * 3 caracteres de area
     * 4 caracteres de anho
     * 6 caracteres de folio (o sufijo)
     *
     * Total: 25 caracteres con lo que se presume que esta cadena es unica
     * en la tabla NumeroExpediente
     * 
     * @return El objeto asociado al numeroExpediente o null en caso de no
     * existir dicho numero.
     */
    NumeroExpediente obtenerNumeroExpediente(String numeroExpediente,Long discriminanteId);

    /**
     * Dado un expediente y un usuario, el sistema consulta el NumeroExpediente
     * asociado al {@code expediente.numeroExpediente}, posteriormente toma el
     * funcionario asociado a {@code usuario.funcionario} y asocia el
     * NumeroExpediente con el funcionario y el Expediente con el
     * NumeroExpediente.
     * @param expediente
     * @param usuario
     */
    public void asociarNumExpediente(Expediente expediente, Usuario usuario);

    /**
     * Operación que realiza la funcionalidad de consultar los número de TOCA relacionados a un número de causa
     * @param expedienteId
     * @return
     */
	public List<NumeroExpediente> consultarTOCAPorCausa(Long causaPadre);
	/**
	 * Consulta la paridad del número de expediente con el ID mas alto y que la 
	 * paridad sea diferente de nulo, en caso de no encontrar resultado se retorna nulo
	 * @return Paridad del numero de expediente encontrado, nulo en caso de no encontrar paridad
	 * asignada
	 */
	Boolean consultarUltimaParidadAsignadaDeNumeroExpediente() throws NSJPNegocioException;
	
	/**
     * Consulta los expedientes que tienen al menos un evento (audiencia o recurso) con fecha de evento
     * entre la fecha actual y el periodo hacia atrás definido por el parámetro del sistmea de tiempo de 
     * consulta histórica y asociados a cierto id de caso
     * @param usuario Usuario que consulta
     * @return Lista de expediente con al menos un evento para el periodo histórico
     * 
     */
    List<NumeroExpediente> consultarExpedientesConEventosHistorico(Long casoId,Long usuarioId);

   
    /**
     * 
     * @param fechaInicio
     * @return
     */
    List<NumeroExpediente> consultarCausasHistorico (Calendar fechaInicio,Long discriminanteId) throws NSJPNegocioException;
 
    /**
     * 
     * @param idNumeroExpediente
     * @return
     * @throws NSJPNegocioException
     */
    List<NumeroExpediente> consultarCarpetasEjecucionPorCausa (Long idNumeroExpediente) throws NSJPNegocioException;
    
    /**
     * 
     * @param tipoExpediente
     * @param estatusExpediente
     * @return
     * @throws NSJPNegocioException
     */
    List<NumeroExpediente> consultarNumeroExpedienteByEstatus (TipoExpediente tipoExpediente, EstatusExpediente estatusExpediente) throws NSJPNegocioException;

    /**
     * 
     * @param idUsuario
     * @param areaId
     * @return
     */
	List<NumeroExpediente> consultarByUsuarioArea(Long idUsuario, Long areaId, Long estatusExpediente,Long agenciaId);

	/**
	 * Consulta los expedientes asociados a un funcionario.
	 * @param claveFuncionario
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<NumeroExpediente> buscarNumeroExpedienteAbieroPorIdFuncionario(
			Long claveFuncionario)throws NSJPNegocioException;

    /**
     * Consulta números de expediente relacionados a un Caso de una solicitud
     * @return Numeros de expediente encontrados
     */
	List<NumeroExpediente> consultarNumeroExpedientePorCasoDeSolicitud(Long solicitudId);

	/**
	 * 
	 * @return
	 */
	List<NumeroExpediente> consultarHistoricoCausasExpediente(Date fechaHistorico);
	/**
	 * Consulta numeros de expediente de cierto tipo (TOCA, CAUSA o CARPETA) en base a los filstros enviados como parametro
	 * si un filtro es nulo entonces no se considera en la cosulta
	 * @param fechaInicio Límite inferior para la fecha de apertura
	 * @param fechaFin Límite superior para la fecha de apertura
	 * @param usuario Usuario al cuál debe de pertenecer el expediente
	 * @param tipo Tipo de expediente
	 * @param numeroExpedientePadreId Si existe el parámetro se consultan los número de expedientes
	 * cuya causa padre sea la enviada en este parámetro
	 * @return Lista con los números de expediente encontrados
	 */
	List<NumeroExpediente> consultarNumeroExpedientePorFiltro(Date fechaInicio,Date fechaFin,Funcionario usuario,
			TipoExpediente tipo,Long numeroExpedientePadreId);

	/**
	 * Consulta el numero de expediente que corresponde a un expediente y no tenga numero expediente padre
	 * @param expedienteId
	 * @return
	 */
	NumeroExpediente obtenerNumeroExpedienteXExpediente(Long expedienteId);
	   /**
     * Consulta el numero de expediente que corresponde a un expediente y no tenga numero expediente padre
     * @param expedienteId
     * @return
     */
    List<NumeroExpediente> consultarNumeroExpedientesXExpediente(Long expedienteId);
    /**
     * Consulta números de expediente asociados a cierto caso
     * @param caso
     * @return
     */
	List<NumeroExpediente> consultarNumeroExpedientePorNumeroCaso(String caso);

	/**
	 * Obtiene el numero de expediente de un tipo determinado asociado a caso
	 * @param casoId
	 * @return
	 */
	NumeroExpediente obtenerCarpetaEjecucionByCaso(String numeroCaso, Long tipoNumExpediente);

	/**
	 * Obtiene el numero expediente causa de un expedinete
	 * @param expedienteId
	 * @return
	 */
	NumeroExpediente obtenerCausaByExpediente(Long expedienteId);

	/**
	 * 
	 * @param valorId
	 * @param valorId2
	 * @return
	 */
	List<NumeroExpediente> consultarNumeroExpedienteByTipoYEstatus(
			Long tipoExp, Long estatusExp,Long discriminanteId);

	/**
	 * 
	 * @param numeroExpedienteId
	 * @return
	 */
	List<NumeroExpediente> consultarnumExpedienteHijos(Long numeroExpedienteId);
	
	NumeroExpediente consultarNumeroExpedienteXExpedienteId(Long expedienteId);
	
	/**
	 * Obtiene el total por mes de los numeros de expediente que se encuentren en un estatus
	 * determinado y dentro de un rango de fechas.
	 * @author cesarAgustin
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param estatusExpediente
	 * @return
	 */
	List<Object[]> obtenerNumExpPorEstatusYMes(Date fechaInicial, Date fechaFinal, EstatusExpediente estatusExpediente);
	
	/**
	 * Obtiene la carpeta de ejecucion de un involucrado si es que la tiene.
	 * @param idInvolucrado
	 * @return
	 */
	NumeroExpediente obtenerCarpetaEjecucionPorInvolucrado (Long idInvolucrado);

	/**
	 * Busca un numero de expediente que tenga como probable responsable principal 
	 * al involucrado identificado por <code>folioImputado</code>
	 * @param numeroCaso
	 * @param folioImputado
	 * @param valorId
	 * @return
	 */
	NumeroExpediente buscarNumeroExpedientePorCasoFolioImputado(String numeroCaso,
			String folioImputado, Long claveFuncionario);
	
	/**
     * Busca un numero de expediente que tenga como probable responsable principal 
     * al involucrado identificado por <code>folioImputado</code>. <b>Aplica para defensoria únicamente</b>
     * @param numeroCaso
     * @param folioImputado
     * @param valorId
     * @return
     */
    NumeroExpediente obtenerExpedienteDefensaPorCasoFolioImputado(String numeroCaso,
            String folioImputado, Long claveFuncionario);
    
    /**
     * 
     * @param claveFuncionario
     * @return
     */
    NumeroExpediente consultarNumExpPorFuncionarioYNumExp (Long claveFuncionario, Long numExpId);
    
    /**
     * 
     * @param claveFuncionario
     * @param numExpId
     * @return
     */
    List<NumeroExpediente> consultarNumExpPorFuncionario (Long claveFuncionario);

    /**
     * 
     * @param claveFuncionario
     * @return
     */
	List<NumeroExpediente> consultarExpedientesDelFuncionario(
			Long claveFuncionario, Long agenciaId);
	
	/**
	 * Consulta en base a los filstros enviados como parametro
	 * si la fecha inicial o fecha final  es nulo entonces no se considera en la cosulta
	 * Los demas parametros son requeridos
	 * 
	 * @param fechaInicio Límite inferior para la fecha de apertura
	 * @param fechaFin Límite superior para la fecha de apertura
	 * @param usuario Usuario al cuál debe de pertenecer el expediente
	 * @param discriminanteId al cual pertenece el funcionario
	 * 
	 * @return los expediente dto 
	 */
	List<NumeroExpediente> consultarExpedientesPorFiltroST(Date fechaInicio,Date fechaFin,Long Area,Long estatusExpediente,Long discriminanteId);
	
	/**
	 * 
	 * @param idFuncionario
	 * @param estus
	 * @return
	 */
	List<NumeroExpediente> obtenerNumExpPorFuncionarioYEstatus(Long idFuncionario, Long estus);
}
