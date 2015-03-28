/**
* Nombre del Programa : AvisoDetencionDelegate.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Delegate para los Avisos de Detencion
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
package mx.gob.segob.nsjp.delegate.avisodetencion;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;


/**
 * Contrato Delegate para los Avisos de Detencion
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface AvisoDetencionDelegate {

	
	/**
	 * Obtiene los avisos de detencion por estatus
	 * @return List<NotificacionDTO>
	 * @throws NSJPNegocioException
	 */
	
	List<AvisoDetencionDTO> obtenerAvisosDetencionPorEstatus(
			EstatusNotificacion estatusnotificacion) throws NSJPNegocioException;
	/**
	 * Consulta los avisos de detencion de acuerdo a la varible de consulta y al estatus enviado como parametro.
	 * @author cesarAgustin
	 * @param estatusNotificacion
	 * @return Avisos de detencion obtenidos en la consulta
	 * @throws NSJPNegocioException
	 */
	List<AvisoDetencionDTO> consultarAvisosDetencionHistoricoByEstatus(EstatusNotificacion estatusNotificacion) throws NSJPNegocioException;
	
	/**
	 * Obtiene el detalle de un aviso de detencion de acuero al identificador enviado como parametro.
	 * @author cesarAgustin
	 * @param avisoDetencionDTO
	 * 			<li>documentoId<li> identificador del aviso a obtener
	 * @return El aviso detencion.
	 * @throws NSJPNegocioException
	 */
	AvisoDetencionDTO obtenerAvisoDetencion(AvisoDetencionDTO avisoDetencionDTO) throws NSJPNegocioException;
	
	/**
	 * 
	 * @param avisoDetencionDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	AvisoDetencionDTO atenderAvisoDetencion (AvisoDetencionDTO avisoDetencionDTO) throws NSJPNegocioException;
	
    /**
     * Registra en BD el aviso de detención y lo envía por WS a defensoria.
     * 
     * @param input
     *            <ul>
     *            <li>involucradoDTO.elementoId</li>
     *            <li>involucradoDTO.nombresDemograficoDTO</li>
     *            <li>involucradoDTO.expedienteDTO.casoDTO.numeroGeneralCaso</li>
     *            <li>usuario.areaActual</li>
     *            <li>fechaInicioDetencion</li>
     *            <li>fechaRecepcionDetencion</li>
     *            <li></li>
     *            </ul>
     * @throws NSJPNegocioException
     */
    void enviarAvisoDetencion(DetencionDTO input,Long idAgencia, String claveAgencia,Long idFuncionarioSolicitante) throws NSJPNegocioException;

}
