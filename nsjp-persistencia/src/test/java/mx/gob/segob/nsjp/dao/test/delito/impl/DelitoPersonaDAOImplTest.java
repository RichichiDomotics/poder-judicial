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
import mx.gob.segob.nsjp.dao.persona.DelitoPersonaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.DelitoPersona;
import mx.gob.segob.nsjp.model.Involucrado;

/**
 * 
 * @author rgama
 * @version 1.0
 *
 */
public class DelitoPersonaDAOImplTest extends BaseTestPersistencia<DelitoPersonaDAO> {

	public void testConsultarDelitosPorImputado () {
		logger.info("Prueba unitaria para consultar Delitos por caso");						
		List<DelitoPersona> respuesta = daoServcice.consultarDelitosPorImputado(36L,1L);
		assertFalse("La lista debe tener registros de Delito ", respuesta.isEmpty());		
		logger.info("Delitos recuperados : " + respuesta.size());		
	}	
	
	public void testConsultarDelitoPerByInvolucrado () {
		logger.info("Prueba unitaria para consultar los delitos relacionados a un expediente");		
		List<DelitoPersona> respuesta = daoServcice.consultarDelitoPerByInvolucrado(2004L);
		logger.info("Delitos obtenidos : " + respuesta);
		assertTrue("La lista de delitos debe tener al menos un registro ", respuesta.size()>0);
		logger.info("Delitos obtenidos : " + respuesta.size());
	}
	
	public void testConsultarDelitosPorImputadoResponsable(){
		Long idInvolucrado=4L; //348L //
		Long idExpediente=1L;
		List<DelitoPersona> delitos = daoServcice.consultarDelitosPorImputadoResponsable(idInvolucrado, idExpediente);
		logger.info("Existen "+delitos.size()+" delitoPersona");
		for (DelitoPersona dPer : delitos) {
			logger.info("-------------------------------");
			logger.info("ID: "+dPer.getDelitoPersonaId());
			logger.info("Responsable: "+dPer.getProbableResponsable().getElementoId());
			logger.info("Forma: "+dPer.getFormaParticipacion().getValor());
			logger.info("Victima: "+dPer.getVictima().getElementoId());
			
		}
	}
	
	public void testConsultarVictimaImputadoPorDelito(){
		Long idDelito= null;//13L;
		Long idExpediente=34L;
		List<DelitoPersona> delitos = daoServcice.consultarVictimaImputadoPorDelito(idDelito, idExpediente);
		logger.info("Existen "+delitos.size()+" delitoPersona");
		for (DelitoPersona dPer : delitos) {
			logger.info("-------------------------------");
			logger.info("ID: "+dPer.getDelitoPersonaId());
			logger.info("Responsable: "+dPer.getProbableResponsable().getElementoId());
			logger.info("Forma: "+dPer.getFormaParticipacion().getValor());
			logger.info("Victima: "+dPer.getVictima().getElementoId());
			
		}
	}
	
	public void testConsultarDelitosPersonaPorExpedienteIdsDelito(){
		Long idExpediente=34L;
		List<Long> idsDelitos = new ArrayList<Long>();
//		idsDelitos.add(13L);
//		idsDelitos.add(211L);
//		idsDelitos.add(212L);
		
		List<DelitoPersona> delitos = daoServcice.consultarDelitosPersonaPorExpedienteIdsDelito(idsDelitos , idExpediente);
		logger.info("Existen "+delitos.size()+" delitoPersona");
		for (DelitoPersona dPer : delitos) {
			logger.info("-------------------------------");
			logger.info("ID: "+dPer.getDelitoPersonaId());
			logger.info("Responsable: "+dPer.getProbableResponsable().getElementoId());
			logger.info("Forma: "+dPer.getFormaParticipacion().getValor());
			logger.info("Victima: "+dPer.getVictima().getElementoId());
			
		}
	}
	public void testInsertar(){
	    DelitoPersona dp = new DelitoPersona();
	    dp.setProbableResponsable(new Involucrado(354L));
	    Delito del = new Delito();
	    del.setDelitoId(19L);
	    dp.setDelito(del);
	    daoServcice.create(dp);
	}
	
	public void testConsultarDelitosProbableResp(){
		List<DelitoPersona> delitos = daoServcice.consultarDelitosPorImputadoResponsable(4L);
		logger.debug("Delitos: " + delitos.size());
	}
	
	public void desactivarDelitoPersona(){
		daoServcice.desactivarDelitoPersona(4L);
	}
	
	public void testObtenerDetenidosPorMesYDelito() {		
		try {
			Long respuesta = daoServcice.obtenerDetenidosPorMesYDelito(DateUtils.obtener("01/07/2011"), 
											DateUtils.obtener("31/08/2011"), new Long(1));
			
			assertNotNull("La lista debe tener minimo un registro ", respuesta);
			logger.info("La lista debe tener minimo un registro " + respuesta);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

	public void testExisteRelacionProbableResponsableVictimaDelito() {
		Long idDelito = 1404L;
		Long idProbableResponsable = 8900L;
		Long idVictima = 8897L;
		Long idFormaParticipacion = 2162L;
		
		Boolean existe = false;
		
		existe = daoServcice
				.existeRelacionProbableResponsableVictimaDelitoFormaParticipacion(
						idDelito, idProbableResponsable, idVictima,
						idFormaParticipacion);
		
		logger.info("Existe:"+ existe);
	}
}
