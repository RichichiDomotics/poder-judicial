/**
* Nombre del Programa : CompromisoPeriodicoDAOImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12/08/2011
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
package mx.gob.segob.nsjp.dao.test.medida.impl;

import mx.gob.segob.nsjp.dao.medida.CompromisoPeriodicoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CompromisoPeriodico;

/**
 * Pruebas unitarias del CompromisoPeriodicoDAO
 * @version 1.0
 * @author GustavoBP
 *
 */
public class CompromisoPeriodicoDAOImplTest extends BaseTestPersistencia<CompromisoPeriodicoDAO> {

	public void testConsultarCompromisoPeriodicoPorMedida(){
		logger.debug("Prueba para consultar los Comprimisos Periodicos de acuerdo a una Medida.");
		
		Long idMedida = 272L;
		
		CompromisoPeriodico compromisoPeriodico = daoServcice.consultarCompromisoPeriodicoPorMedida(idMedida);
		logger.info(" compromisoPeriodico: " + compromisoPeriodico);
		if(compromisoPeriodico!= null){
			logger.info("CompromisoPeriodico - getCompromisoPeriodicoId:"+compromisoPeriodico.getCompromisoPeriodicoId());
			logger.info("CompromisoPeriodico - getMonto:"+compromisoPeriodico.getMonto());
			logger.info("CompromisoPeriodico - getLugarPresentacion:"+compromisoPeriodico.getLugarPresentacion());
			logger.info("CompromisoPeriodico - getFechaCompromisos:"+compromisoPeriodico.getFechaCompromisos());
		}
		
		
	}
}
