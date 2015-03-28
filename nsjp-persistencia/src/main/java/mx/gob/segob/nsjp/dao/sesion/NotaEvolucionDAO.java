package mx.gob.segob.nsjp.dao.sesion;

/**
* Nombre del Programa : NotaEvolucion.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos de la entidad NotaEvolucion
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
import mx.gob.segob.nsjp.model.NotaEvolucion;


/**
 * Contrato de metodos de acceso a datos de la entidad NotaEvolucion.
 * @author rgama
 *
 */
public interface NotaEvolucionDAO extends GenericDao<NotaEvolucion, Long> {
	 
	/**
	 * Consulta NotaEvolucion por identificador
	 * @param aoNotaEvolucion
	 * 		Obligatorio <b>sesionId</b>.
	 * @return
	 * @throws NSJPNegocioException
	 */
    NotaEvolucion consultarNotaEvolucionPorId(NotaEvolucion aoNotaEvolucion);

}
