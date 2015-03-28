/**
*
* Nombre del Programa : NombreDemograficoDAOImplTest.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Prueba para el DAO de Nombre demografico                      
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

import java.util.List;

import mx.gob.segob.nsjp.dao.persona.NombreDemograficoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Persona;

/**
 * 
 * @author CesarAgustin
 * @version
 *
 */
public class NombreDemograficoDAOImplTest extends BaseTestPersistencia<NombreDemograficoDAO> {

	public void testAll ()  {
		logger.debug("Prueba para obtener todos los registros de Nombre Demografico ");
		
		List<Long> respuesta = daoServcice.findAllId();
		assertFalse("La lista no puede venir vacia", respuesta.isEmpty());
		
		logger.info("Respuesta :: " + respuesta);
	}
	
//	public void testRegistra(){
//		NombreDemografico demo=new NombreDemografico();
//		demo.setPersona(new Persona(474L));
//		demo.setNombre("Arturo");
//		demo.setApellidoPaterno("Fernandez");
//		demo.setApellidoMaterno("Gómez");
//		demo.setSexo("M");
//		demo.setRfc("rfc");
//		demo.setCurp("curp");
//		demo.setEdadAproximada((short) 30);
//	    demo.setEsVerdadero(true);
//	    demo.setLugarNacimiento("México DF");
//	    demo.setPaisNacimiento(new Valor(10L));
//	    demo.setMunicipioNacimiento(new Municipio(180L));
//	    demo.setEntidadFederativaNacimiento(new EntidadFederativa(12L));
//	    
//		Long idDemo = daoServcice.create(demo);
//		assertNotNull(idDemo);
//	}
	
	public void testObtenerPersonaXDemografico(){
		 Persona persona = daoServcice.obtenerPersonaXDemografico(3L);
		assertNotNull(persona);
		logger.info("Res: "+persona);
		logger.info("elemento id: "+persona.getFolioElemento());
		logger.info("calidad: "+persona.getCalidad());
		logger.info("calidad: "+persona.getCalidad().getTipoCalidad().getValor());
	}
}
