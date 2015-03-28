/**
* Nombre del Programa : MedidaAdjuntosDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrsato para el objeto de acceso a datos de la entidad MedidaAdjuntos
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
package mx.gob.segob.nsjp.dao.medida;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.MedidaAdjuntos;
import mx.gob.segob.nsjp.model.MedidaAdjuntosId;

/**
 * Contrsato para el objeto de acceso a datos de la entidad MedidaAdjuntos.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface MedidaAdjuntosDAO extends GenericDao<MedidaAdjuntos, MedidaAdjuntosId> {

	/**
	 * 
	 * @param documentoId
	 * @return
	 */
	List<ArchivoDigital> consultarArchivosDigitalesPorMedida(Long medidaId);

}
