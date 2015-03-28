/**
* Nombre del Programa : SolicitudPericialDAO.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 03 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos para la entidad SolicitudPericial
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
package mx.gob.segob.nsjp.dao.solicitud;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudPericial;

/**
 * Contrato de metodos de acceso a datos para la entidad SolicitudPericial.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface SolicitudPericialDAO extends
		GenericDao<SolicitudPericial, Long> {
	/**
	 * Metodo que permite consultar las solicitudes periciales en base al tipo y al estatus
	 * @param tipoSolicitud: Asesoria o Dictamen
	 * @param estatus
	 * @return SolicitudPericial
	 */
	public List<SolicitudPericial> consultarSolicitudesPericiales(Long tipoSolicitud, Long estatus, String puestoSolicitante);
	/**
     * Consulta una solicitud de servicio pericial en base al estatus de la solicitud
     * y el tipo de solicitud de pericial para cierto rol de destinatario
     * @param estado Estado a consultar
     * @param tipo tipo de solicitud a consultar
     * @param puesto - Puesto a filtrar en el destinatario
     * @return Lista de solicitudes de servicio encontradas
     * @throws NSJPNegocioException 
     */
    public List<SolicitudPericial> consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(TiposSolicitudes tipo,
    		EstatusSolicitud estado,Puestos puesto)
    throws NSJPNegocioException;
    
    /**
     * Consulta una solicitud de servicio pericial en base al estatus de la solicitud
     * y el tipo de solicitud de pericial para cierto rol de destinatario
     * @param estado Estado a consultar
     * @param tipo tipo de solicitud a consultar
     * @param puesto - Puesto a filtrar en el destinatario
     * @return Lista de solicitudes de servicio encontradas
     * @throws NSJPNegocioException 
     */
    public List<SolicitudPericial> consultarSolicitudesPericialesPorTipoEstatusAreaDestinatario(TiposSolicitudes tipo,
    		EstatusSolicitud estado,Long area)
    throws NSJPNegocioException;
    
	/**
	 * Metodo que permite consultar las solicitudes periciales de un usuario en base al tipo y al estatus
	 * @param tipoSol
	 * @param estatusSol
	 * @param idUsuario
	 * @return
	 */
	public List<Solicitud> consultarSolicitudesPericialPorTipoEstatusYUsuario(
			Long tipoSol, Long estatusSol, Long idUsuario);
	
	/**
	 * Consulta una o mas solicitudes cuyo destinatario sea el funcionario enviado como parámetro y filtradas
	 * por el estatus deseado
	 * @param destinatario Destinatario de las solicitudes a consultar
	 * @param estatus Estado de la solicitud
	 * @return Listado de solicitudes encontradas
	 * @throws NSJPNegocioException
	 */
     List<SolicitudPericial> consultarSolicitudesPericialesPorEstatusYDestinatario(EstatusSolicitud estatus, 
    		Funcionario destinatario)
    throws NSJPNegocioException;
     /**
      * Consulta el último dictamen generado para la solicitud pericial, si no se tiene ningún dictamen
      * creado entonces se retorna nulo
      * @param solicitudPericialId Solicitud pericial para la cuál se busca el dictamen
      * @return ID del dictamen localizado, null si no se localiza
      * @throws NSJPNegocioException
      */
     Long consultarDictamenIdDeSolicitudPericial(Long solicitudPericialId) throws NSJPNegocioException;
     
     List<SolicitudPericial> actualizarStatusSolicitudPericial(SolicitudPericial solicitudPericial);
     
     
     /**
      * Consulta una lista de solicitudes de pericial enviadas por cierto perfil y relacionadas a cierto
      * numero de Causa/Expediente y con ciertos est atus
      * @param puesto Puesto del solicitante buscado
      * @param estados Estados buscados en la solicitud
      * @param numeroExpedienteId Id del número de expediente
      * @author Emigdio Hernández
      * @return Lista de solicitudes de pericial encontradas
      */
     List<SolicitudPericial> consultarSolicitudesPericialesPorPuestoSolicitanteEstatusYNumeroExpediente(Puestos puesto,
     		EstatusSolicitud[] estados,Long numeroExpedienteId);
    
}
