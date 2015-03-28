/**
 * Nombre del Programa : RegistrarRelacionService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 13-jul-2011
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
package mx.gob.segob.nsjp.service.relacion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface RegistrarRelacionService {

    /**
     * Crea un nuevo registro en la tabla relacion con los parametros que se le
     * pasan.
     * @param idCatRelacion El id del tipo de relacion que estamos creando.
     * @param idElementoSujeto El id del elemento sujeto.
     * @param idElementoComplemento El id del elemento complemento.
     * @throws NSJPNegocioException En caso que alguno de los parametros sea
     * null.
     */
    void registrarRelacion(Long idCatRelacion,
            Long idElementoSujeto, Long idElementoComplemento)
            throws NSJPNegocioException;

}
