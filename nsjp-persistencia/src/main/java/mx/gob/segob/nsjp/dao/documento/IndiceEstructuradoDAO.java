/**
 * Nombre del Programa : IndiceEstructuradoDAO.java
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

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.IndiceEstructurado;

/**
 * Objeto de Acceso a Datos para la IndiceEstructurado.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public interface IndiceEstructuradoDAO extends GenericDao<IndiceEstructurado, Long> {
	/**
	 * Metodo que regresa una lista de IndiceEstructurado
	 * @param tipoOficioId: permite filtar por el tipo de Oficio
	 * @return List<IndiceEstructurado>
	 */
	public List<IndiceEstructurado> consultarIndicesEstructuradosPorTipoOficio(Long tipoOficioId);
  
}
