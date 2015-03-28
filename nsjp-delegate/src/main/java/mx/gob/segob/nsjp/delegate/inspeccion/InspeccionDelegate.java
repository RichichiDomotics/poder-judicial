/**
 * Nombre del Programa : InspeccionDelegate.java
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
package mx.gob.segob.nsjp.delegate.inspeccion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.inspeccion.InspeccionDTO;
import mx.gob.segob.nsjp.dto.inspeccion.MultaSancionDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface InspeccionDelegate {
    /**
     * 
     * @param input
     * @return
     * @throws NSJPNegocioException
     */
    Long registrarMulta(MultaSancionDTO input) throws NSJPNegocioException;

    /**
     * 
     * @param funMultado
     *            obligatorio: <b>claveFuncionario</b>
     * @return
     * @throws NSJPNegocioException
     */

    List<MultaSancionDTO> consultarMultas(FuncionarioDTO funMultado)
            throws NSJPNegocioException;

    /**
     * Actualiza únicamente la descripción de la multa-sanción.
     * 
     * @param input
     *            requeridos: <b>multaSancionId</b> y <b>descripcion</b>
     * @throws NSJPNegocioException
     */
    void actualizarDescripcion(MultaSancionDTO input)
            throws NSJPNegocioException;

    /**
     * Cambia el estatus a concluida la inspección.
     * 
     * @param input
     *            input requerido: <b>multaSancionId</b>
     * @throws NSJPNegocioException
     */
    void saldarMulta(MultaSancionDTO input) throws NSJPNegocioException;

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
     * @param idMulta
     * @return
     * @throws NSJPNegocioException
     */
    MultaSancionDTO obtenerMulta(Long idMulta) throws NSJPNegocioException;
    
    /**
     * 
     * @param idInspeccion
     * @return
     * @throws NSJPNegocioException
     */
    InspeccionDTO obtenerInspeccion(Long idInspeccion) throws NSJPNegocioException;
}
