/**
* Nombre del Programa : CamposFormaDAO.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 02/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del objeto de acceso a datos para la configuración de los campos que se pueden
 * llenar automaticamente de una Forma al emitir un Documento.
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
package mx.gob.segob.nsjp.dao.forma;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CamposForma;

/**
 * Contrato del objeto de acceso a datos para la configuración de los campos que se pueden
 * llenar automaticamente de una Forma al emitir un Documento.
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface CamposFormaDAO extends GenericDao<CamposForma, Long> {
	/**
	 * Obtiene la lista de los camposForma ordenada por ID
	 * @return Lista de CamposForma
	 */
	List<CamposForma> obtenerCamposForma();
	
}
