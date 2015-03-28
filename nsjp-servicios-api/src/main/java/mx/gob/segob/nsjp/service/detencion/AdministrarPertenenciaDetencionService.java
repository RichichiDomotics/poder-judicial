/**
 * Nombre del Programa : AdministrarPertenenciaDetencionService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 11 Oct 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Interface para la administración de pertenencias en la detención
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
package mx.gob.segob.nsjp.service.detencion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO;

/**
 * Interface para la administración de pertenencias en la detención.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface AdministrarPertenenciaDetencionService {
    /**
     * 
     * @param input
     * @return
     */
    Long registrarPertenecia(PertenenciaDTO input) throws NSJPNegocioException;
    /**
     * 
     * @param idDetencion
     * @return
     * @throws NSJPNegocioException
     */
    
    List<PertenenciaDTO> consultarPertenenciaByDetencion(Long idDetencion) throws NSJPNegocioException;
    
    /**
     * M&eacute;todo utilizado para llevar a cabo la actualizaci&oacute;n de una 
     * pertenencia previamente registrada en la base de datos.
     * @param pertenenciaDTO - La pertenencia a actualizar.
     */
    public void actualizarPertenencia(PertenenciaDTO pertenenciaDTO);
    
    /**
     * M&eacute;todo utilizado para llevar a cabo la eliminaci&oacute;n f&iacute;sica
     * de una pertenencia previamente registrada en la base de datos.
     * @param pertenenciaDTO - La pertenencia a eliminar.
     */
    public void eliminarPertenencia(PertenenciaDTO pertenenciaDTO);
    
    /**
     * M&eacute;todo que lleva a cabo la consulta de una pertenencia a trav&eacute;s 
     * de su identificador.
     * @param pertenenciaDTO - DTO con el identificador de la pertenencia a consultar.
     * @return pertenenciaDTO - DTO con todos los datos de la base de datos.
     */
    public PertenenciaDTO consultarPertenenciaPorId(PertenenciaDTO pertenenciaDTO);

}
