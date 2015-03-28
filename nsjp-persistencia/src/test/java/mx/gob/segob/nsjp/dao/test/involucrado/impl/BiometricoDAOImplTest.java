/**
* Nombre del Programa : BiometricoDAOImplTest.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 27/04/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Implementarion del DAO para los objetos de Biometrico                
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
package mx.gob.segob.nsjp.dao.test.involucrado.impl;

import java.util.List;
import mx.gob.segob.nsjp.dao.involucrado.BiometricoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Biometrico;

/**
 * Implementarion del DAO para los objetos de Biometrico
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class BiometricoDAOImplTest extends  BaseTestPersistencia<BiometricoDAO> {

	public void testConsultarBiometricoByInvolucrado () {
		List<Biometrico> biometricos = daoServcice.consultarBiometricoByInvolucrado(1L);
		
		assertFalse("La lista no puede estar vacia", biometricos.isEmpty());
	}
}
