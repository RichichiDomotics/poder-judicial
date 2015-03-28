package mx.gob.segob.nsjp.dao.sesion;

/**
* Nombre del Programa : EntrevistaInicial.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos de la entidad EntrevistaInicial
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

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.EntrevistaInicial;


/**
 * Contrato de metodos de acceso a datos de la entidad EntrevistaInicial.
 * @author rgama
 *
 */
public interface EntrevistaInicialDAO extends GenericDao<EntrevistaInicial, Long> {
	
	/**
	 * Consulta EntrevistaInicial por identificador
	 * @param aoEntrevistaInicialDTO
	 * 		Obligatorio <b>sesionId</b>.
	 * @return EntrevistaInicial
	 */
	public EntrevistaInicial consultarEntrevistaInicialPorId(EntrevistaInicial aoEntrevistaInicialDTO);

}
