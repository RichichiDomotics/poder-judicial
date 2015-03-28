/**
* Nombre del Programa : ImplicadoDAO.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dao.quejaciudadana;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Implicado;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ImplicadoDAO extends GenericDao<Implicado, Long> {

	/**
	 * Operación que permite consultar un implicado por id
	 * @param implicadoId (Obligatorio)
	 * @return Implicado
	 */
	public Implicado consultarImplicadoPorId(
			Implicado aoImplicado);
}
