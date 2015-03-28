/**
*
* Nombre del Programa : MedioDeContactoDAOImplTest.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Prueba para el DAO de Medio de contacto                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/

package mx.gob.segob.nsjp.dao.test.persona.impl;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.segob.nsjp.dao.persona.MedioDeContactoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;

/**
 * 
 * @author CesarAgustin
 * @version 1.0
 *
 */
public class MedioDeContactoDAOImplTest extends BaseTestPersistencia<MedioDeContactoDAO> {

	public void testAll ()  {
		logger.debug("Prueba para obtener todos los registros de Medio De Contacto ");
		
		List<BigDecimal> respuesta = daoServcice.findAllId();
		assertFalse("La lista no puede venir vacia", respuesta.isEmpty());
		
		logger.info("Respuesta :: " + respuesta);
	}
}
