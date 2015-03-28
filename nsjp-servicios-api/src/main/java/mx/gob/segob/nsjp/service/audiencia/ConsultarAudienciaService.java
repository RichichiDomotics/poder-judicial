/**
 * Nombre del Programa : ConsultarAudienciaService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 15 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio para consultar Audiencias
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
package mx.gob.segob.nsjp.service.audiencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.FiltroAudienciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Contrato del servicio para consultar Audiencias.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface ConsultarAudienciaService {
    /**
     * Busca las audiencias en base a un filtro:
     * <ul>
     * <li>Rango</li>
     * <ul>
     * <li>fechaInicial</li>
     * <li>fechaFinal</li>
     * </ul>
     * <li>Día especifico</li>
     * <ul>
     * <li>fechaInicial</li>
     * <li>Día completo o sólo las posteriores a la fecha actual</li>
     * <ul>
     * <li>diaCompleto</li>
     * </ul>
     * </ul> <li>numeroExpedienteId</li> <li>tipoAudiencia</li>
     * <ul>
     * 
     * @param filtro
     * @return
     * @throws NSJPNegocioException
     */
    List<AudienciaDTO> buscarAudiencias(FiltroAudienciaDTO filtro)
            throws NSJPNegocioException;

    /**
     * Obtiene una audiencia en base al id
     * 
     * @param audIn
     * @return
     * @throws NSJPNegocioException
     */
    AudienciaDTO obtenerAudiencia(AudienciaDTO audIn)
            throws NSJPNegocioException;
    /**
     * Obtiene una audiencia en base al id de la solicitud
     * 
     * @param solAudIn
     * @return
     * @throws NSJPNegocioException
     */
    AudienciaDTO obtenerAudiencia(SolicitudAudienciaDTO solAudIn)
            throws NSJPNegocioException;

    /**
	 * Operación que realiza la funcionalidad de validar si la audiencia tiene registradas pruebas.
	 * @param audienciaDTO: el identificador de la audiencia.
	 * @return Devuelve un boleano, verdadero en caso de que la audiencia tenga pruebas asociadas, en caso contrario, regresa falso.
	 * @throws NSJPNegocioException
	 */
	Boolean validarExistenciaPruebas(AudienciaDTO audienciaDTO)throws NSJPNegocioException;

	List<AudienciaDTO> buscarAudienciasSinTranscripcionResolutivos()throws NSJPNegocioException;
	
	/**
	 * @author cesarAgustin
	 * Consulta las audiencia de un determinado tipo, y que la fecha de audiencia es de la fecha actual en adelante.
	 * @param aunAudienciaDTO
	 * 			-Fecha de audiencia <li>fechaEvento<li> 
	 * @param tipoAudiencia
	 * 			-Tipo de la audiencia a consltar
	 * @return Lista de audiencias recuperadas
	 * @throws NSJPNegocioException
	 */
	public List<AudienciaDTO> consultarAudienciasByTipoYFecha(
			AudienciaDTO audienciaDTO, TipoAudiencia tipoAudiencia,UsuarioDTO usuario) throws NSJPNegocioException;

	/**
	 * Obtiene una audiencia de acuerdo al numero de la audiencia recibido.
	 * @param audienciaDTO
	 * 			-Numero de la audiencia <li>consecutivo<li>
	 * @return Audiencia obtenida
	 * @throws NSJPNegocioException
	 */
	AudienciaDTO obtenerAudienciaByNumeroAudiencia(AudienciaDTO audienciaDTO) throws NSJPNegocioException;

	/**
	 * Obtiene las Audiencias asociadas a un numeroExpediente y que se encuentran en un estatus determinado.
	 * @author AlejandroGA
	 * @param expedienteDTO
	 * 		 	-Identificador del numeroExpediente del cual se requieren las Audiencias <li>numeroExpedienteId<li>
	 * @param estatusAudiencia[]
	 * 			-Estatus en el cual se requieren las Audiencias en la consulta
	 * @return Lista de audiencias en el estatus requerido y asociadas al numeroExpediente deseado.
	 * @throws NSJPNegocioException
	 */
	List<AudienciaDTO> consultarAudienciaByNumeroExpedienteYListaEstatus(
			ExpedienteDTO expedienteDTO, EstatusAudiencia[] estatusAudiencia) throws NSJPNegocioException;
	
	/**
	 * Obtiene las Audiencias asociadas a un numeroExpediente y que se encuentrn en un estatus determinado.
	 * @author cesarAgustin
	 * @param expedienteDTO
	 * 		 	-Identificador del numeroExpediente del cual se requieren las Audiencias <li>numeroExpedienteId<li>
	 * @param estatusAudiencia
	 * 			-Estatus en el cual se requieren las Audiencias en la consulta
	 * @return Lista de audiencias en el estatus requerido y asociadas al numeroExpediente deseado.
	 * @throws NSJPNegocioException
	 */
	List<AudienciaDTO> consultarAudienciaByNumeroExpedienteYEstatus(
			ExpedienteDTO expedienteDTO, EstatusAudiencia estatusAudiencia) throws NSJPNegocioException;
	
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
	
	List<AudienciaDTO> consultarAudienciasConSolicitudesPorTipoYEstado(TiposSolicitudes []tipos,EstatusSolicitud []estados,UsuarioDTO usuario) throws NSJPNegocioException;

	/**
	 * Consulta las audiencias de acuerdo a:
	 * @param tipoAudiencia
	 * @param estatusAudiencia
	 * @author cesarAgustin
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<AudienciaDTO> consultarAudienciasByTipoYEstatus(TipoAudiencia tipoAudiencia, 
						EstatusAudiencia estatusAudiencia) throws NSJPNegocioException;

	
	/**
	 * Solicita las audiencias programadas entre las fechas inico y fin de audiencia y del tipo seleccionado.
	 * @param audiencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<AudienciaDTO> consultarAudienciasByFechasyEstatus(
			AudienciaDTO audiencia) throws NSJPNegocioException;
	
	/**
	 * Este método es utilizado por el encargado de sala al abrir el visor de audiencia, prepara los
	 * elementos mínimos necesarios para tener persistida en BD la siguiente audiencia y poder empezar
	 * a colocar elementos como involucrados, funcionarios, objetos en la siguiente audiencia
	 * Este método consulta las audiencias que no están programadas para el número de causa de la
	 * audiencia enviada como parámetro, si encuentra al menos una audiencia retorna sus datos, si
	 * no encuentra audiencias entonces crea una con los datos mínimos necesarios y copia los siguientes
	 * elementos de la audiencia actual:
	 * Involucrados
	 * Objetos
	 * 
	 * @param audienciaActualId Audiencia Actual en base a la cual se creará la nueva audiencia
	 * en caso de no encontrarla, de esta audiencia actual se consultará el número de causa para buscar
	 * la audiencia no programada
	 * @return
	 * @throws NSJPNegocioException
	 */
	AudienciaDTO consultarORegistrarSiguienteAudiencia(Long audienciaActualId) throws NSJPNegocioException;
}
