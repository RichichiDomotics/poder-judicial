/**
 * Nombre del Programa : AcuseReciboDAO.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de Acceso a Datos para la notificacion.
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
package mx.gob.segob.nsjp.dao.documento;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.AcuseRecibo;

/**
 * Objeto de Acceso a Datos para la notificacion.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public interface AcuseReciboDAO extends GenericDao<AcuseRecibo, Long> {
    /**
     * Metodo que realiza la funcionalidad de consultar el último número de
     * acuse de atención registrado.
     * 
     * @return Devuelve un número de acuse, cero si no hay registros
     */
    public Long consultarUltimoAcuse();

}
