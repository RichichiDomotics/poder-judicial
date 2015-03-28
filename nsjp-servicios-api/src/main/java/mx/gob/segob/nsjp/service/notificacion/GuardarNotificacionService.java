/**
 * 
 */
package mx.gob.segob.nsjp.service.notificacion;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * @author adrian
 *
 */
public interface GuardarNotificacionService {

	/**
	 * Operación que realiza la funcionalidad de guardar una notifcación.
	 * @param notificacionDTO: objeto a guardar. Hereda de Documento
	 * @param audienciaDTO: idAudiencia
	 * @param involucradoDTO: idInvolucradoDestinatario
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarNotificacion(NotificacionDTO notificacionDTO, AudienciaDTO audienciaDTO, InvolucradoDTO involucradoDTO)throws NSJPNegocioException;

	Long guardarNotificacion(NotificacionDTO notificacionDTO,
			AudienciaDTO audienciaDTO, FuncionarioDTO funcionario) throws NSJPNegocioException;
	/**
	 * Método para enviar la notificación una vez generada previamente
	 * @param idNotificacion
	 * @param idAudiencia
	 * @param nombreCompletoFuncionario
	 * @param institucion
	 * @throws NSJPNegocioException
	 */
	void enviarNotificacion(Long idNotificacion, Long idAudiencia, 
            String nombreCompletoFuncionario, Instituciones institucion) throws NSJPNegocioException ;


	/**
	 * 
	 * Servicio que permite guardar (enviar) la notificación a la misma institución.
	 * 
	 * @param expedienteDTO
	 * @param documentoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarYEnviarNotificacionAMismaInstitucion(ExpedienteDTO expedienteDTO, DocumentoDTO documentoDTO) throws NSJPNegocioException ;
}
