/**
 * Nombre del Programa  : RegistroBitacoraDAO
 * Autor                : Cuauhtemoc Paredes Serrano
 * Compania             : Ultrasist
 * Proyecto             : NSJP                    Fecha: 13/10/2011
 * Marca de cambio      : N/A
 * Descripcion General  : Registra los cambios del expediente en la bitacora.
 * Programa Dependiente :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion   :N/A
 * Dias de ejecucion    :N/A                      Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                       Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dao.bitacora;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.RegistroBitacora;

public interface RegistroBitacoraDAO extends GenericDao<RegistroBitacora, Long> {

    /**
     * Obtiene el último folio asignado a un registro de bitacora
     * 
     * @return
     * @throws NSJPNegocioException
     */
    public Short obtenerUltimoFolioBitacora(RegistroBitacora bitacora)
            throws NSJPNegocioException;
    /**
     * 
     * @param numeroExpedienteId
     * @return
     */
    List<RegistroBitacora> consultarByNumeroExpedienteId(Long numeroExpedienteId);

}
