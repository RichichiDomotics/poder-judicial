/**
* Nombre del Programa : ServidorPublicoDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos para la entidad ServidorPublico
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
package mx.gob.segob.nsjp.dao.involucrado;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.ServidorPublico;

/**
 * Contrato de metodos de acceso a datos para la entidad ServidorPublico.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ServidorPublicoDAO extends GenericDao<ServidorPublico, Long> {

	/**
	 * Obtiene los datos como servidor publico del involucrado que se recibe como parametro
	 * @param servidorPublicoId
	 * @return
	 * @throws NSJPNegocioException
	 */
	public ServidorPublico obtenerServidorPublicoInvolucrado(Long servidorPublicoId) throws NSJPNegocioException;
}
