/**
* Nombre del Programa : MedioPruebaDAO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/09/2011
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
package mx.gob.segob.nsjp.dao.prueba;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.DatoPrueba;
import mx.gob.segob.nsjp.model.MedioPrueba;

/**
 * Interfaz para los metodos de acceso a los datos de la entidad MedioPrueba. 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface MedioPruebaDAO extends GenericDao<MedioPrueba, Long> {

	List<MedioPrueba> consultarMediosPruebaPorDatoPrueba(Long datoPruebaId,
			Long expedienteID, Boolean relacionados);

	List<DatoPrueba> consultarDatosPruebaXMedioPrueba(Long medioPruebaId); 

}
