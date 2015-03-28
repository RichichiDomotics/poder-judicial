/**
* Nombre del Programa : PermisoExpedienteDAO.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 4 Oct 2011
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
package mx.gob.segob.nsjp.dao.expediente;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.PermisoExpediente;
import mx.gob.segob.nsjp.model.PermisoExpedienteId;

/**
 * @author cesar
 *
 */
public interface PermisoExpedienteDAO extends
		GenericDao<PermisoExpediente, PermisoExpedienteId> {

	/**
	 * 
	 * @param claveFuncionario
	 * @param numExpId
	 * @return
	 */
	PermisoExpediente obtnerPermisoFuncionario(Long claveFuncionario,
			Long numExpId);

	/**
	 * 
	 * @param claveFuncionario
	 * @return
	 */
	List<NumeroExpediente> consultarExpedientescConPermisoFuncionario(
			Long claveFuncionario, Long agenciaId);

}
