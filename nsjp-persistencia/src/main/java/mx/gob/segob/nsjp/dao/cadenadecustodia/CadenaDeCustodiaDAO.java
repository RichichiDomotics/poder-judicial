/**
 * Nombre del Programa : CadenaDeCustodiaDAO.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-jun-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dao.cadenadecustodia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Expediente;

/**
 *
 * @author Jacob Lobaco
 */
public interface CadenaDeCustodiaDAO extends GenericDao<CadenaDeCustodia, Long>{

    /**
     * Operación que realiza la funcionalidad de consultar la cadena de custodia
     * asociada al folio
     * Los datos que consulta son:
     * - Número de la evidencia
     * - Información de la evidencia
     * - Último eslabón asociado
     * - Número de eslabón
     * - Tipo de eslabón
     * - Almacén
     * Devuelve
     * @param folio El folio de la cadena de custodia.
     * @return Un objeto de tipo CadenaDeCustodiaDTO con la informacion
     * mencionada, en caso que el folio buscado no existaregresa NULL.
     */
    CadenaDeCustodia consultarCadenaDeCustodiaXFolio(String folio);

    /**
     * Operación que realiza la funcionalidad de consultar la cadena de
     * custodia relacionada al expediente
     * Devuelve un listado de cadenas de custodia
     * @param expedienteDto El identificador del expediente.
     * @return La cadena de custodia asociada al expediente.
     * @throws NSJPNegocioException
     */
    List<CadenaDeCustodia> consultarCadenaCustodiaXExpediente(Expediente expediente);
    
    List<CadenaDeCustodia> consultarCadenaCustodiaXNumeroExpediente(Long expedienteId, String folioCadena);

    /**
     * Operación que consulta las cadenas de custodia relacionadas a un almacen
     * @param identificadorAlmacen
     * @return
     */
	List<CadenaDeCustodia> consultarCadenaCustodiaXAlmacen(
			Long identificadorAlmacen, Long idCaso);

	
	/**
	 * Consulta una cadena de custodia con por folio y el expediente al que pertenee 
	 * @param folio
	 * @param idExpediente
	 * @return
	 * @throws NSJPNegocioException 
	 */
	public CadenaDeCustodia consultarCadenaDeCustodiaPorFolioExpediente(String folio,
			Long idExpediente) throws NSJPNegocioException;
	
	/**
	 * Metodo que permite obtener unicamente el folio de una cadena de custodia asociada a un elemento
	 * @param idObjeto
	 * @param numeroExpedienteId
	 * @return List<CadenaDeCustodia>
	 * @throws NSJPNegocioException
	 */
	public CadenaDeCustodia consultarFolioDeCadenaXObjetoId(Long idObjeto, Long numeroExpedienteId)  throws NSJPNegocioException;


}
