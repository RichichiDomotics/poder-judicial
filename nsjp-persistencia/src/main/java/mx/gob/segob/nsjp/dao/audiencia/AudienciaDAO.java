/**
 * Nombre del Programa : AudienciaDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 31 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato para el acceso a datos de la entidad Audencia
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
package mx.gob.segob.nsjp.dao.audiencia;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.BandejaNotificador;
import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Resolutivo;

/**
 * Contrato para el acceso a datos de la entidad Audencia.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface AudienciaDAO extends GenericDao<Audiencia, Long> {
    /**
     * 
     * @param tipoBandeja
     * @return
     */
    List<Audiencia> consultarAudienciasPendientesNotificacion(
            BandejaNotificador tipoBandeja);

    /**
     * 
     * @param idExpediente
     * @return
     */
    List<Audiencia> consultarAudienciasporExpediente(Long idExpediente);

    /**
     * 
     * @param usrId
     * @param fecIni
     * @param fecFin
     * @param diaCompleto
     * @return
     */
    List<Audiencia> buscarAudiencias(Long usrId, Long expNumeroId, Date fecIni,
            Date fecFin, boolean diaCompleto, Long tipoAudiencia, boolean isCausa,Long discriminanteId, Long idDistrito);
    /**
     * 
     * @param solId
     * @return
     */
    Long obtenerIdAudienciaByIdSolicitud(Long solId);

	Audiencia consultarAudienciaById(Long id);

	/**
	 * Operación que realiza la funcionalidad de consultar las Audiencias por un estatus de la Audiencia
	 * 
	 * @param estatus
	 * @return Devuelve un listado de Audiencias que cumplen con el criterio.
	 */
	List<Audiencia> consultarAudienciasPorEstatus(Long estatus);

	/**
	 * Operación que realiza la funcionalidad de consultar los resolutivos de una audiencia.
	 * 
	 * @param Recibe el identificador de la Audiencia 
	 * @return Devuelve un listado de textos que corresponden a los resolutivos.
	 */
	List<Resolutivo> consultarResolutivosAudiencia(Long idAudiencia);

	List<Audiencia> buscarAudienciasSinResolutivos();

	/**
	 * 
	 * @param fechaEvento
	 * @param valorId
	 * @return
	 */
	List<Audiencia> consultarAudienciasByTipoYFecha(Date fechaEvento,
			Long valorId,Long discriminanteId);

	/**
	 * 
	 * @param consecutivo
	 * @return
	 */
	Audiencia obtenerAudienciaByNumeroAudiencia(Short consecutivo);

	/**
	 * 
	 * @param numeroExpediente
	 * @return
	 */
	List<Audiencia> buscarAudienciaByCausa(String numeroExpediente,Long discriminanteId);

	/**
	 * Obtiene las Audiencias asociadas a un numeroExpediente y que se encuentrn en un estatus determinado.
	 * @param numeroExpedienteId
	 * @param valorId
	 * @return
	 */
	List<Audiencia> consultarAudienciaByNumeroExpedienteYEstatus(
			Long numeroExpedienteId, Long estatusAudiencia);
	
	/**
	 * Obtiene las Audiencias asociadas a un numeroExpediente y que se encuentrn en un estatus determinado.
	 * @param numeroExpedienteId
	 * @param valorId
	 * @return
	 */
	List<Audiencia> consultarAudienciaByNumeroExpedienteYListaEstatus(
			Long numeroExpedienteId, EstatusAudiencia[] estatusAudiencia);

	/**
	 * 
	 * @param estatusAudienciaId
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	List<Audiencia> consultarAudienciasByFechasYEstatus(
			Long estatusAudienciaId, Date fechaInicio, Date fechaFin);

	/**
	 * Consulta una lista de audiencias que tengan al menos una solicitud asociada que este en algunos de los estatus
	 * enviados como parámetro y que sea de alguno de los tipos enviados como parámetro.
	 * Las solicitudes asociadas son los objetos del tipo
	 * SolicitudTranscripcion, en este objeto se guardan las solicitudes de transcripción y las solicitudes
	 * de audio y video
	 * @param tipos Tipos de la solicitudes asociadas a las audiencias buscadas
	 * @param estados Estados de las solicitudes asociadas a las audiencias buscadas
	 * @author Emigdio Hernández
	 * @return Lista de audiencias que cumplen con las condiciones descritas
	 */
	
	List<Audiencia> consultarAudienciasConSolicitudesPorTipoYEstado(TiposSolicitudes []tipos,EstatusSolicitud []estados,Long discriminanteId);
	
	/**
	 * Consulta las audiencias de acuerdo a:
	 * @param tipoAudiencia
	 * @param estatusAudiencia
	 * @author cesarAgustin
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<Audiencia> consultarAudienciasByTipoYEstatus(Long tipoAudiencia,
			Long estatusAudiencia);

	/**
	 * Obtiene las audienciad de un tipo determindo y de acuerdo a un rango de fechas de apertura del numero
	 * expediente relacionado
	 * @author cesarAgustin	 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	List<Object[]> obtenerAudienciasJudicializadasPorMes (Date fechaInicial, Date fechaFinal); 
	/**
	 * Obtiene el último folio generado para una audiencia
	 * @return Ultimo folio generado
	 */
	String obtenerUltimoFolioAudiencia();

	/**
	 * 
	 * @param folioAudiecia
	 * @return
	 */
	Audiencia obtnerAudienciaByFolio(String folioAudiecia);

	/**
	 * Servicio que se encarga de obtener el Funcionario que, mediante el número
	 * de expediente, esta relacionado con la Audiencia
	 * @param audienciaId
	 * @return
	 */
	Funcionario obtenerFuncionarioDeNumExpedienteDeAudiencia(Long audienciaId);
	
	/**
	 * Metodo permite consultar las audiencias por fecha y sala
	 * @param fechaAudiencia
	 * @param SalaId
	 * @return
	 */
	Boolean consultarAudienciasByFechaAudienciaYSala(Date fechaInicioAudiencia,Date fechaFinAudiencia,Long SalaId);
	
}
