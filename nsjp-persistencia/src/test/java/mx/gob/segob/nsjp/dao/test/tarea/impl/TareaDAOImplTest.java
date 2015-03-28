/**
* Nombre del Programa : TareaDAOImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Pruebas unitarias para los metodos de acceso a datos de la entidad Tarea
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
package mx.gob.segob.nsjp.dao.test.tarea.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.tarea.TareaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Tarea;

/**
 * Pruebas unitarias para los metodos de acceso a datos de la entidad Tarea.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class TareaDAOImplTest extends BaseTestPersistencia<TareaDAO> {

	public void testSumCargaDiaria () {
		Long respuesta = daoServcice.sumCargaDiaria(new Date(), 1L);
		
		assertTrue("La carga debe ser mayor a cero : ", respuesta>0);
		logger.info("La carga debe ser mayor a cero : " + respuesta);
	}
	
	public void testObtenerDisponibilidadHorarioActividad () {
		List<Tarea> respuesta  = daoServcice.obtenerDisponibilidadHorarioActividad(new Date(), (short) 120, 1L);
	
		logger.info("Respuesta :: "+  respuesta);
		assertNull(respuesta);		
	}
}
