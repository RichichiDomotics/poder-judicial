/**
* Nombre del Programa : SolicitudDefensorDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos para la entidad SolicitudDefensor
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

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.SolicitudDefensor;

/**
 * Contrato de metodos de acceso a datos para la entidad SolicitudDefensor.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface SolicitudDefensorDAO extends
		GenericDao<SolicitudDefensor, Long> {
	
	/**
	 * Generar el ultimo numero para el folio de una solicitud
	 * @return Long
	 */
    Long obtenerUltimoNumero();
    
    
    /**
     * Obtener la lista de las solicitudesd de defensoria con estatus no atendida
     * @return List<SolicitudDefensor>
     */
    List<SolicitudDefensor> obtenerSolicitudesDefensoriaPorEstatus(
    					Long esatus,Instituciones institucion);

    /**
	 * Consulta las solicitudes defensoria que fueron asignadas a un funcionario que es enviado como parametro.
	 * @author cesarAgustin	
	 * @param usuarioDTO
	 * @return Lista de solicitud asignadas al funcionario
	 * @throws NSJPNegocioException
	 */
	List<SolicitudDefensor> consultarSolDefensorAsignadas(Long idUsuario);

	/**
	 * 
	 * @param time
	 * @param estatusSolicitud
	 * @return
	 */
	List<SolicitudDefensor> consultarSolicitudesDefensoriaByHistoricoYEstatus(
			Date fechaHistorico, Long estatusSolicitud);

	/**
	 * 
	 * @param expedienteId
	 * @param claveFuncionario
	 * @return
	 */
	SolicitudDefensor obtenerSolDfensorByExpedienteYFuncionario(
			Long expedienteId, Long claveFuncionario);

	/**
	 * Obtiene una solicitud de defensor asociada a Expediente por numero de expediente id
	 * @param numeroExpedienteId
	 * @return
	 */
	SolicitudDefensor consultarSolicituDefensorPorNumeroExpedienteId(
			Long numeroExpedienteId);


    /**
     * Obtener la lista de las solicitudesd de defensoria con estatus no atendida
     * @return List<SolicitudDefensor>
     */
    List<SolicitudDefensor> obtenerSolicitudesAsesoriaDefensoriaPorEstatus(
    					Long esatus,Instituciones institucion)throws NSJPNegocioException;


	SolicitudDefensor obtenerSolicitudByIdyYipo(Long documentoId, Long idCampo);


}
