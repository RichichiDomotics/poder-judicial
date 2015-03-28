/**
*
* Nombre del Programa : DelitoDAOImplTest.java                                    
* Autor                            : Ricardo Gama                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Prueba unitaria para el DAO de Delito                      
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

package mx.gob.segob.nsjp.dao.test.delito.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatDelito;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Expediente;

/**
 * 
 * @author rgama
 * @version 1.0
 *
 */
public class DelitoDAOImplTest extends BaseTestPersistencia<DelitoDAO> {

	public void _testConsultarDelitosPorCaso () {
		logger.info("Prueba unitaria para consultar Delitos por caso");
						
		List<Delito> respuesta = daoServcice.consultarDelitosPorCaso("numGenCasByGama11");
		assertFalse("La lista debe tener registros de Delito ", respuesta.isEmpty());		
		logger.info("Delitos recuperados : " + respuesta.size());		
	}
	
	
	public void testConsultarDelitosPorExpediente () {
		logger.info("Prueba unitaria para consultar los delitos relacionados a un expediente");
		
		List<Delito> respuesta = daoServcice.obtenerDelitoPorExpediente(12L);
		assertTrue("La lista de delitos debe tener al menos un registro ", respuesta.size()>0);
		logger.info("Delitos obtenidos : " + respuesta.size());
	}
	
	public void testGuardarDelito(){
		logger.info("Prueba unitaria para guardar o actualizar delitos de un expediente");
		
		List<Delito> delitos=delitosParaGuardar();
		Expediente expediente=new Expediente(1L);
		daoServcice.guardarDelito(delitos, expediente);
			
			
	}


	private List<Delito> delitosParaGuardar() {
//		 CatDelito_id     cClaveDelito     cNombre                  bEsGrave     JerarquiaOrganizacional_id    
//		 ---------------  ---------------  -----------------------  -----------  ----------------------------- 
//		 1                1                Violación                1            12                            
//		 2                2                Robo con violencia       1            13                            
//		 3                3                Daño en propiedad ajena  0            11                            
//		 4                4                Narcomenudeo             1            6        
		List<Delito> delitos =new ArrayList<Delito>();
//		delitos.add(new Delito(null, new Expediente(1L), new CatDelito(1L), true, false));
		delitos.add(new Delito(null, new Expediente(1L), new CatDelito(2L), true, false));
		delitos.add(new Delito(null, new Expediente(1L), new CatDelito(3L), true, true));
//		delitos.add(new Delito(null, new Expediente(1L), new CatDelito(4L), true, false));
		
		return delitos;
	}
	
	public void testObtenerTipoDelitoPorMes () {
		
		try {
			List<Object[]> respuesta = daoServcice.obtenerTipoDelitoPorMes(DateUtils.obtener("01/07/2011"), DateUtils.obtener("31/08/2011"), true);
		
			assertTrue("La lista debe regresar minimo un registro", respuesta.size()>0);
			for (Object[] objects : respuesta) {
				logger.info(" MES " + objects[0] + " Num del " + objects[1]);
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}		
	}
	
	public void testObtenerDelitoPorMes() {
		try {
			List<Object[]> respuesta = daoServcice.obtenerDelitoPorMes(DateUtils.obtener("01/07/2011"), DateUtils.obtener("31/08/2011"), new Long(1));
		
			assertTrue("La lista debe regresar minimo un registro", respuesta.size()>0);
			for (Object[] objects : respuesta) {
				logger.info(" MES " + objects[0] + " Num del " + objects[1]);
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testObtenerListaDeDelitoPorExpediente(){
		List<Delito> resp = daoServcice.obtenerListaDeDelitoPorExpediente(119L);
		
		logger.info("ListaDelitos::::::"+resp);
		
		for (Delito delito : resp) {
			logger.info("DelitoId"+delito.getDelitoId());
}
		
	}
	
	
	
	public void testReadDelito() {
	 	Delito delitoPojo = new Delito();
        delitoPojo.setCatDelito(new CatDelito(1L));
        delitoPojo.setEsProbable(Boolean.TRUE);
        delitoPojo.setExpediente(new Expediente(1L));
        delitoPojo.setEsPrincipal(false);
        
        Long idDelito = daoServcice.create(delitoPojo);
        daoServcice.read(idDelito);
     
	}
			
	
}
