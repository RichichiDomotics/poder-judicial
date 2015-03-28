/**
 * Nombre del Programa : AdministrarSancionService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 Oct 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.insepeccion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.inspeccion.InspeccionDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface AdministrarInspeccionService {
    /**
     * Consulta inspecciones por funcionario y expediente. <br>
     * El expediente es opcional.
     * 
     * @param funInspeccionado
     *            DTO obligatorio, y dentro de el <b>claveFuncionario</b>.
     * @param expInspeccionado
     *            DTO opcional, al mandarlo el obligatorio es
     *            <b>numeroExpedienteId</b>.
     * @return
     * @throws NSJPNegocioException
     */
    List<InspeccionDTO> consultarInspecciones(FuncionarioDTO funInspeccionado,
            ExpedienteDTO expInspeccionado) throws NSJPNegocioException;
    /**
     * Registra la inspección.
     * 
     * @param input
     *            <code>funcionarioRegistra</code> es le funcionario de sesión.
     * @return
     * @throws NSJPNegocioException
     */
    Long registrarInspeccion(InspeccionDTO input) throws NSJPNegocioException;
    /**
     * Actualiza únicamente la descripción de la inspección.
     * 
     * @param input
     *            requeridos: <b>inspeccionId</b> y <b>descripcion</b>
     * @throws NSJPNegocioException
     */
    void actualizarDescripcion(InspeccionDTO input) throws NSJPNegocioException;

    /**
     * Cambia el estatus a concluida la inspección.
     * 
     * @param input
     *            input requerido: <b>inspeccionId</b>
     * @throws NSJPNegocioException
     */
    void concluirInspeccion(InspeccionDTO input) throws NSJPNegocioException;
    /**
     * 
     * @param idInspeccion
     * @return
     * @throws NSJPNegocioException
     */
    InspeccionDTO obtenerInspeccion(Long idInspeccion) throws NSJPNegocioException;
}
