/**
* Nombre del Programa : MedidaCautelarDAO.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 6 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato DAO para la obtencion de medidas cautelares
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

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.MedidaCautelar;

/**
 * Contrato DAO para la obtencion de medidas cautelares
 * 
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface MedidaCautelarDAO extends GenericDao<MedidaCautelar, Long> {

	/**
	 * 
	 * @param invId
	 * @return List<MedidaCautelar>
	 */
	List<MedidaCautelar> consultarMedidasCautelaesPorInvolucrado(Long invId);
	
	/**
	 * 
	 * @param idMed
	 * @param idInv
	 * @return MedidaCautelar
	 */
	MedidaCautelar obtenerMedidaCautelar(Long idMed, Long idInv);
	/**
	 * Consulta las medidas cautelares asociadas a cierto numero de expediente 
	 * @param numeroExpedienteId Número de expediente de la medida cautelar buscada
	 * @author Emigdio Hernández
	 * @return Lista de medidas cautelares encontradas
	 */
	List<MedidaCautelar> obtenerMedidasCautelaresPorExpediente(Long numeroExpedienteId);
	
	/**
	 * Consulta las medidas cautelares deacuerdo a su Estatus
	 * @param medidaCautelar DTO de la medida cautelar con el estatus deseado
	 * @author Marco Gallardo
	 * @return Lista de medidas cautelares encontradas
	 * 
	 */
	
	List<MedidaCautelar> consultarMedidasCautelaresPorEstatus(Long estatusId);
	
	/**
	 * Obtiene el numero de resgistro de un tipo de medida cautelar, dentro de un rango de fechas.
	 * @author cesarAgustin
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoMedida
	 * @return
	 */
	Long obtenerMedCauPorFechasYTipoNedida(Date fechaInicio, Date fechaFin, Long tipoMedida);
	
	/**
	 * Permite consultar las medidas cautelares que cuentan con una fecha de incumplimiento
	 * del dia anterior a la fecha actual
	 * @return List<MedidaCautelar> 
	 */
	public List<MedidaCautelar> obtenerMedidasCautelaresPorFechaIncumplientoDelDiaAnterior();
	/**
	 *     
	 * @param numeroExpediente     
	 * @return
	 */
	List<MedidaCautelar> obtenerMedidasCautelaresPorNumeroExpediente(
            String numeroExpediente,Long discriminanteId );
	
}
