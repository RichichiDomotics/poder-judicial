/**
* Nombre del Programa : SalaDAOImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18/10/2011
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
package mx.gob.segob.nsjp.dao.test.audiencia.impl;

import mx.gob.segob.nsjp.dao.audiencia.SalaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Sala;

/**
 * Clase de Pruebas Unitarias sobre la entidad de Sala 
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class SalaDAOImplTest extends BaseTestPersistencia<SalaDAO> {

	public void testConsultarSala(){
		
		Long salaId = 1L;
		Sala sala = daoServcice.read(salaId );
		assertNotNull("La entidad no debe ser nula ", sala);
		logger.info(" SalaID:"+ sala.getSalaId());
		logger.info(" Sala - getDomicilioSala:"+ sala.getDomicilioSala());
		logger.info(" Sala - getUbicacionSala:"+ sala.getUbicacionSala());
		logger.info(" Sala - getEsActivo:"+ sala.getEsActivo());
	}
	
}
