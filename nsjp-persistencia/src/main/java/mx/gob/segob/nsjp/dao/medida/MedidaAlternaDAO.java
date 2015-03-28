/**
* Nombre del Programa : MedidaAlternaDAO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/08/2011
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
package mx.gob.segob.nsjp.dao.medida;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.MedidaAlterna;

/**
 * Contrato para el objeto de acceso a datos de la Medida Alterna.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface MedidaAlternaDAO extends GenericDao<MedidaAlterna, Long> {

	/**
	 * 
	 * @param numeroExpedienteId
	 * @return
	 */
	List<MedidaAlterna> consultarMedidasAlternasPorNumeroExpediente(
			Long numeroExpedienteId);
	
	List<MedidaAlterna> consultarMedidasAlternasPorEstatus(
			Long estatusMedida);
	
	/**
	 * Obtiene el numero de resgistro de un tipo de medida alterna, dentro de un rango de fechas.
	 * @author cesarAgustin
	 * @param fechaInicio
	 * @param fechaFin
	 * @param tipoMedida
	 * @return
	 */
	Long obtenerMedAltPorFechasYTipoNedida(Date fechaInicio, Date fechaFin, Long tipoMedida);
	
	/**
	 * Obtener la Medida Alterna por medio del Id de la Medida y del Involucrado
	 * @param idMed
	 * @param idInv
	 * @return
	 */
	MedidaAlterna obtenerMedidaAlterna(Long idMed, Long idInv);
	
	/**
	 * Permite consultar las medidas alternas que cuentan con una fecha de incumplimiento
	 * del dia anterior a la fecha actual
	 * @return List<MedidaAlterna> 
	 */
	public List<MedidaAlterna> obtenerMedidasAlternasIncumplidasDelDiaAnterior();

	
}
