/**
*
* Nombre del Programa : CasoDAOImplTest.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Prueba para el DAO de Persona                      
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

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.persona.PersonaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Persona;
import mx.gob.segob.nsjp.model.Valor;

/**
 * 
 * @author CesarAgustin
 * @version 1.0
 *
 */
public class PersonaDAOImplTest extends BaseTestPersistencia<PersonaDAO> {
	

	public void testIngresarPersona() {
		logger.debug("Prueba para ingresar una nueva persona");
		
		Persona persona = new Persona();
		
		persona.setEsVivo(true);
		persona.setFolioIdentificacion("00000000001");
		persona.setTipoDoctoId(new Valor(103L));
		
		//Atributos elemento
		//persona.setElementoId(1L);
		persona.setFechaCreacionElemento(new Date());
		persona.setCalidad(new Calidad(2L));
		persona.setExpediente(new Expediente(1L));
		persona.setTipoElemento(new Valor(232L));
		
		
		Long idPersona = daoServcice.create(persona);
		assertTrue("El idElemento tiene que ser mayor a 0 : ", idPersona>0);
	}
	
	public void _testObtenerPersonas() {
		logger.debug("Prueba para obtener los registros de persona");
		
		List<Persona> personas = daoServcice.obtenerPersonasAll();
		assertFalse("La lista no puede venir vacia", personas.isEmpty());
		for (int i=0;i<personas.size();i++){
			logger.debug("Persona: " + personas.get(i).getEsVivo());
		}
	}
	
	public void _testAll ()  {
		logger.debug("Prueba para obtener todos los id´s de los registros de Persona ");
		
		List<Long> respuesta = daoServcice.findAllId();
		assertFalse("La lista no puede venir vacia", respuesta.isEmpty());
		
		logger.info("Respuesta :: " + respuesta);
	}
	
}
