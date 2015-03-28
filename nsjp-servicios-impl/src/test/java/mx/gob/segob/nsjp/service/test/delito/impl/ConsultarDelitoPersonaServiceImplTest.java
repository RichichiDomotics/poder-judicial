/**
* Nombre del Programa : ConsultarDelitoPersonaServiceImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08/07/2011
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
package mx.gob.segob.nsjp.service.test.delito.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoPersonaDTO;
import mx.gob.segob.nsjp.service.delito.ConsultarDelitoPersonaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class ConsultarDelitoPersonaServiceImplTest extends BaseTestServicios<ConsultarDelitoPersonaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.delito.impl.ConsultarDelitoPersonaServiceImpl#consultarDelitosVictimaPorImputado(java.lang.Long, java.lang.Long)}.
	 */
	public void testConsultarDelitosVictimaPorImputado() {
		Long idInvolucrado=1480L;
		Long idExpediente=772L;
		try {
			List<DelitoPersonaDTO> delitos = service.consultarDelitosVictimaPorImputado(idInvolucrado, idExpediente);
			logger.info("Existen "+delitos.size()+" delitoPersona");
			for (DelitoPersonaDTO dPer : delitos) {
				logger.info("-------------------------------");
				logger.info("ID: "+dPer.getDelitoPersonaId());
				logger.info("Responsable: "+dPer.getProbableResponsable().getElementoId());
				logger.info("Forma: "+dPer.getFormaParticipacion().getValor());
				logger.info("Victima: "+dPer.getVictima().getElementoId());
				logger.info("Victima Nombre: "+dPer.getVictima().getNombreCompleto());
			}
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}
	
	public void testValidarRelacionPersonaDelitoExpediente(){
		Long idDelito=16L;
		Long idExpediente=34L;
		try {
			Boolean existeRelacion = service.existeRelacionPersonaDelitoExpediente(idDelito, idExpediente);
			logger.info("Existen Relacion:"+ existeRelacion);
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}
	
	public void testConsultarVictimaImputadoPorDelito(){
		Long idDelito=13L;
		Long idExpediente=34L;
		try {
			List<DelitoPersonaDTO> delitos = service.consultarVictimaImputadoPorDelito(idDelito, idExpediente);
			logger.info("Existen "+delitos.size()+" delitoPersona");
			for (DelitoPersonaDTO dPer : delitos) {
				logger.info("-------------------------------");
				logger.info("ID: "+dPer.getDelitoPersonaId());
				logger.info("Responsable: "+dPer.getProbableResponsable().getElementoId());
				logger.info("Responsable: "+dPer.getProbableResponsable().getNombreCompleto());
				logger.info("Forma: "+dPer.getFormaParticipacion().getValor());
				logger.info("Victima: "+dPer.getVictima().getElementoId());
				logger.info("Victima Nombre: "+dPer.getVictima().getNombreCompleto());
			}
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}
	
	
	public void testConsultarDelitosPersonaPorExpedienteIdsDelito(){
		Long idExpediente=34L;
		List<Long> idsDelitos = new ArrayList<Long>();
		idsDelitos.add(13L);
		idsDelitos.add(211L);
		idsDelitos.add(212L);
		
		try {
			
			List<DelitoPersonaDTO> delitos = service.consultarDelitosPersonaPorExpedienteIdsDelito(idsDelitos, idExpediente);
			logger.info("Existen "+delitos.size()+" delitoPersona");
			for (DelitoPersonaDTO dPer : delitos) {
				logger.info("-------------------------------");
				logger.info("ID: "+dPer.getDelitoPersonaId());
				logger.info("Responsable: "+dPer.getProbableResponsable().getElementoId());
				logger.info("Responsable: "+dPer.getProbableResponsable().getNombreCompleto());
				logger.info("Forma: "+dPer.getFormaParticipacion().getValor());
				logger.info("Victima: "+dPer.getVictima().getElementoId());
				logger.info("Victima Nombre: "+dPer.getVictima().getNombreCompleto());
			}
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}
	
	public void testConsultarVictimaImputadoPorExpediente(){
		Long idExpediente=69L;
		try {
			List<DelitoPersonaDTO> delitos = service.consultarVictimaImputadoPorExpediente(idExpediente);
			logger.info("Existen "+delitos.size()+" delitoPersona");
			for (DelitoPersonaDTO dPer : delitos) {
				logger.info("-------------------------------");
				logger.info("ID: "+dPer.getDelitoPersonaId());
				logger.info("Delito: "+dPer.getDelito().getNombreDelito());
				logger.info("Responsable: "+dPer.getProbableResponsable().getElementoId());
				logger.info("Responsable: "+dPer.getProbableResponsable().getNombreCompleto());
				logger.info("Forma: "+dPer.getFormaParticipacion().getValor());
				logger.info("Victima: "+dPer.getVictima().getElementoId());
				logger.info("Victima Nombre: "+dPer.getVictima().getNombreCompleto());
			}
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}
	
	
	public void testDesactivarDelitoPersona() {
			try {
				service.desactivarDelitoPersona(5L);
			} catch (NSJPNegocioException e) {
				logger.info(e.getMessage(), e);
			}
	}
	
	public void testConsultarDelitosVictimaPorImputadoUno() {		
		List<DelitoPersonaDTO> respuesta = service.consultarDelitosVictimaPorImputado(725L);
		
		assertTrue("La lista debe tener minimo un registro :: ",respuesta.size()>0);
		for (DelitoPersonaDTO delitoPersonaDTO : respuesta) {
			logger.info("Delito persona :: "+delitoPersonaDTO.getDelitoPersonaId());
		}
	}

	public void testEliminarDelitoPersona(){
		Long delitoPersonaId = 544L;
		Boolean eliminado;
		try {
			eliminado = service.eliminarDelitoPersona(delitoPersonaId);
			logger.info("Valor:"+ eliminado);
			assertFalse("Se ha eliminado con exito.",eliminado);
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}
	
}
