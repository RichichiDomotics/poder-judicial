/**
 * Nombre del Programa : ValidarRelacionXActividadService.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 12-jul-2011
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
public interface ValidarRelacionXActividadService {

    /**
     * Verifica si existe un registro en la tabla Relacion que corresponda
     * con los parametros {@code idElementoSujeto, idElementoComplemento y
     * idCatRelacion}. 
     * @param idCatRelacion El tipo de relacion que se quiere verificar.
     * @param idElementoSujeto El id del elemento sujeto del que estamos
     * verificando en la tabla relacion.
     * @param idElementoComplemento El id del elemento complemento que estamos
     * verificando en la tabla relacion.
     * @return {@code true} en caso que exista un registro en la tabla relacion
     * que tenga alguna de las actividades asociadas que corresponda con el
     * {@code idActividad} que se le pasa como parametro. {@code false} en caso
     * que no exista un registro en la tabla relacion o que si existe, ninguna
     * de sus actividades relacionadas corresponda con el {@code idActividad}
     * pasado como parametro.
     * @throws NSJPNegocioException Si alguno de los parametros es null.
     */
    boolean validarRelacion(Long idCatRelacion, Long idElementoUno, Long idElementoDos)
            throws NSJPNegocioException;

}
