/**
* Nombre del Programa : ParametroDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 7 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos para la entidad Parametros
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
package mx.gob.segob.nsjp.dao.parametro;

import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Parametro;

/**
 * Contrato de metodos de acceso a datos para la entidad Parametros.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ParametroDAO extends GenericDao<Parametro, Long> {

    Parametro obtenerPorClave(Parametros clveParam);
    
}
