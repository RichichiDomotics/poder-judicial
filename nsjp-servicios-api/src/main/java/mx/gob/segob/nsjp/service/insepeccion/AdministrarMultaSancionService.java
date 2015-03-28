/**
 * Nombre del Programa : AdministrarMultaSancionService.java
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
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.inspeccion.MultaSancionDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface AdministrarMultaSancionService {
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
     * 
     * @param idMulta
     * @return
     * @throws NSJPNegocioException
     */
    MultaSancionDTO obtenerMulta(Long idMulta) throws NSJPNegocioException;
}
