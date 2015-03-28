package mx.gob.segob.nsjp.service.solicitud;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Nombre del Programa : SolicitarDefensor.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato del servicio para realizar la solicitud de un defensor
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

/**
 * Contrato del servicio para realizar la solicitud de un defensor.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface SolicitarDefensorService {

	/**
	 * 
	 * @param solicitudDefensorDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public SolicitudDefensorDTO solicitarDefensor(SolicitudDefensorDTO solicitudDefensorDTO) throws NSJPNegocioException;

	/**
	 * Consulta las solicitudes defensoria que fueron asignadas a un funcionario que es enviado como parametro.
	 * @author cesarAgustin	
	 * @param usuarioDTO
	 * @return Lista de solicitud asignadas al funcionario
	 * @throws NSJPNegocioException
	 */
	public List<SolicitudDefensorDTO> consultarSolDefensorAsignadas(FuncionarioDTO funcionarioDTO) throws NSJPNegocioException;

	/**
	 * Elimina la asignacion del funcionario enviado como parametro del expediente tambien enviado.
	 * @author cesarAgustin
	 * @param expedienteDTO
	 * @param funcionarioDTO
	 * @throws NSJPNegocioException
	 */
	public SolicitudDefensorDTO reasignarDefensorAExpediente(ExpedienteDTO expedienteDTO,
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException;
	
}
