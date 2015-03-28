/**
 * Nombre del Programa : ConsultarNotificacionesXUsuarioService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 18-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.notificacion;

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface ConsultarNotificacionesXUsuarioService {

    /**
     * Consulta la siguiente informacion de las notificaciones asociadas a un
     * funcionario:
     * <ol>
     * <li> String idNotificacion = notificacion.getDocumentoId() + "";
     * <li> String numeroDecaso = notificacion.expedienteDTO.getCasoDTO().getNumeroGeneralCaso();;
     * <li> String numeroExpediente = notificacionDto.getNumeroExpedienteUsuarioNotif();
     * <li> String motivo = notificacion.getMotivo();
     * <li> Date fechaCreacion = notificacion.getFechaCreacion();
     * </ol>
     * @param funcionarioDto
     * @param estatusDto
     * @param pagina
     * @param numeroDeRegistros
     * @param campoOrden
     * @param direccionOrden
     * @return
     * @throws NSJPNegocioException
     */
    List<NotificacionDTO> consultarNotificacionesXUsuario(FuncionarioDTO funcionarioDto,
            ValorDTO estatusDto, int pagina, int numeroDeRegistros,
            String campoOrden, int direccionOrden) throws NSJPNegocioException;

    long consultarNumeroTotalDeNotificacionesXUsuario(FuncionarioDTO funcionarioDto,
            ValorDTO estatusDto);
    
    List<NotificacionDTO> consultarNotificacionesXFuncionario(
			FuncionarioDTO funcionarioDto, ValorDTO estatusDto)throws NSJPNegocioException;
}
