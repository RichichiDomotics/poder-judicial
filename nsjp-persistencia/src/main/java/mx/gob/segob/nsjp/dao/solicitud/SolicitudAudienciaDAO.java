/**
 * Nombre del Programa : SolicitudAudienciaDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del objeto de acceso a datos para la entidad SolicitudAudiencia.
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

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudAudiencia;

/**
 * Contrato del objeto de acceso a datos para la entidad SolicitudAudiencia.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface SolicitudAudienciaDAO
        extends
            GenericDao<SolicitudAudiencia, Long> {
    /**
     * 
     * @return
     */
    List<SolicitudAudiencia> consultarSolicitudesAudienciaPendientes(Long discriminanteId);
    /**
     * 
     * @return
     */
    List<Solicitud> consultarOtrasSolicitudesPendientes();
    /**
     * Consulta un listado de solicitudes de audiencia de cierto tipo y cierto estado
     * @param tipo Tipo a buscar
     * @param estado Estado a buscar
     * @return Solicitudes encontradas
     */
    List<SolicitudAudiencia> consultarSolicitudesAudienciaPorTipoyEstado(TiposSolicitudes tipo,EstatusSolicitud estado);
	SolicitudAudiencia consultarSolicitudesAudienciaPorAudiencia(
			Long idAudiencia);
	
    /**
     * Consulta un listado de solicitudes de audiencia con criterios
     * @param solicitud
     * @param lstIdEstatusSolicitud
     * @param lstIdTipoSolicitud
     * @param lstIdTipoAudiencia
     * @param tipoConsulta
     * @return
     * @throws NSJPNegocioException
     */

 
	List<SolicitudAudiencia> consultarSolicitudesAudienciaConCriterios (
			SolicitudAudiencia solicitudAudiencia,
			List<Long> lstIdEstatusSolicitud, List<Long> lstIdTipoSolicitud,
			List<Long> lstIdEstatusAudiencia, List<Long> lstIdTipoAudiencia,
			String tipoConsulta
			) throws NSJPNegocioException;
	
}
