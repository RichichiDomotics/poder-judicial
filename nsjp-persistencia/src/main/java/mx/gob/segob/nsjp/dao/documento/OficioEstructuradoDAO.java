/**
 * Nombre del Programa : OficioEstructuradoDAO.java
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
import mx.gob.segob.nsjp.model.OficioEstructurado;

/**
 * Objeto de Acceso a Datos para OficioEstructurado.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public interface OficioEstructuradoDAO extends GenericDao<OficioEstructurado, Long> {

	OficioEstructurado consultarOficioXDocumento(Long documentoId);

}
