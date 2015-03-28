/**
* Nombre del Programa : ValorDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para los metodos de acceso a datos de la entidad Valor
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
package mx.gob.segob.nsjp.dao.catalogo;

import java.util.List;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Contrato para los metodos de acceso a datos de la entidad Valor.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ValorDAO extends GenericDao<Valor, Long> {

	/**
	 * Método que consulta la complejidad asociada a un tipo de Audiencia
	 * @param tipo
	 * @return
	 */
	Valor consultarComplejidadTipoAudiencia(Valor tipo);

	int actualizarComplejidadTipoAudiencia(Long registroId, Long complejidad);

    List<Valor> existeNombreValor(String estatusExpedienteVal);
    List<String> obtenerNombresColumnas(Long idCat);
    
    Long getNext();
    
    
}
