/**
* Nombre del Programa : RecibirSolicitudCiudadanaDefensoria.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para el servicio Recibir una solicitud de defensoria por un ciudadano
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
package mx.gob.segob.nsjp.service.solicitud;

import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;

/**
 * Contrato para el servicio Recibir una solicitud de defensoria por un ciudadano.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface RecibirSolicitudCiudadanaDefensoriaService {
	
	/**
	 * Actualiza la etapa del expediente asociado a una solicitud de defensoria
	 * @param solicitudDefensorDTO
	 * @throws NSJPNegocioException
	 */
	
	void actualizarEtapaExpedienteSolicitudDefensoria(
			SolicitudDefensorDTO solicitudDefensorDTO,EtapasExpediente etapas )throws NSJPNegocioException;
	
	/**
	 * Actualiza el estatus de la solocitudd e defensoria para turnarla al coordinador
	 * @param solicitudDefensorDTO
	 * @throws NSJPNegocioException
	 */
	
	void actualizaEstatusSolicitudDefensoria(SolicitudDefensorDTO solicitudDefensorDTO)throws NSJPNegocioException;

	/**
	 * 
	 * @param expedienteDTO
	 * @return SolicitudDefensorDTO
	 * @throws NSJPNegocioException
	 */
	SolicitudDefensorDTO generarAcuseAtencion(ExpedienteDTO expedienteDTO)throws NSJPNegocioException;
	
	/**
	 * 
	 * @param solicitudDefensorDTO
	 * @return SolicitudDefensorDTO
	 * @throws NSJPNegocioException
	 */
	void guardarMotivoSolicitudDefensoria(SolicitudDefensorDTO solicitudDefensorDTO) throws NSJPNegocioException;
	
	/**
	 * 
	 * @param solicitante
	 * @return InvolucradoDTO
	 * @throws NSJPNegocioException
	 */
	InvolucradoDTO guardarSolicitanteSolicitudDefensoria(InvolucradoDTO solicitante
			 )throws NSJPNegocioException;
	
	/**
	 * 
	 * @param imputado
	 * @return InvolucradoDTO
	 * @throws NSJPNegocioException
	 */
	InvolucradoDTO guardarDefendidoSolicitudDefensoria(InvolucradoDTO imputado, Long tipoArea, EtapasExpediente etapa) throws NSJPNegocioException;
		
	/**
	 * 
	 * @param imputado
	 * @throws NSJPNegocioException
	 */
	void actualizaImputadoSolicitudDefensoria(InvolucradoDTO imputado) throws NSJPNegocioException;

}
