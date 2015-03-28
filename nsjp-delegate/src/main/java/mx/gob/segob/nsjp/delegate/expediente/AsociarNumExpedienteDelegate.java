/**
 * Nombre del Programa : AsociarNumExpedienteDelegate.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 28-jun-2011
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
package mx.gob.segob.nsjp.delegate.expediente;


import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Contrato del delegate para los metodos de comunicacion de AsociarNumExpediente
 * entre la vista y los servicios.
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface AsociarNumExpedienteDelegate {

    /**
     * Operación que realiza la funcionalidad de asociar el número de expediente
     * al expedeinte y al usuario firmado en el sistema
     * Recibe al expediente con el usuario firmado.
     * @param expedienteDto
     * @param usuarioDto
     * @throws NSJPNegocioException
     */
    void asociarNumExpediente(ExpedienteDTO expedienteDto, UsuarioDTO usuarioDto)
            throws NSJPNegocioException;

}
