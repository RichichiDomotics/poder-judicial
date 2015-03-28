/**
* Nombre del Programa : ConsultarNotasPorExpedienteServiceImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio de Consultar Notas de expediente
* en base al area y al usuario logueado en el sistema
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
package mx.gob.segob.nsjp.service.test.expediente.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.NotaExpedienteDTO;
import mx.gob.segob.nsjp.service.expediente.ConsultarNotasPorExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de Consultar Notas de expediente
 * @version 1.0
 * @author rgama
 *
 */
public class ConsultarNotasPorExpedienteServiceImplTest 
 extends BaseTestServicios<ConsultarNotasPorExpedienteService> {

	public void testConsultarNotasService(){
	        try {
	            logger.info("Probando el servicio de: ConsultarNotasPorExpedienteService");
	    		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
	    		expedienteDTO.setNumeroExpedienteId(2L);    		
	    		List<NotaExpedienteDTO> loNotas = service.consultarNotas(expedienteDTO);
	    		
	    		for (NotaExpedienteDTO notaDTO : loNotas) {	    			
	    			System.out.println("----------------------ID nota: " + notaDTO.getIdNota() +"--------------------------------");
		    		System.out.println("Nombre nota:" + notaDTO.getNombreNota());
		    		System.out.println("Descripcion nota:" + notaDTO.getDescripcion());
		    		System.out.println("Fecha de creacion:" + notaDTO.getFechaCreacion());
		    		System.out.println("Fecha de actuazizacion:" + notaDTO.getFechaActualizacion());
		    		System.out.println("-------------------------------------------------------");
				}
	    		System.out.println("El total de Notas consultadas es:" + loNotas.size());
	            
	        } catch (NSJPNegocioException ex) {
	            if (logger.isDebugEnabled()) {
	                logger.debug(ex);
	            }
	            fail("Ocurrio una excepcion al consultar el test ConsultarNotasService");
	        }
	    }
	
	public void testConsultarNotaPorId(){
		
		Long idNota=1L;
		try {
			NotaExpedienteDTO notaExpedienteDTO = service.consultarNotaPorId(idNota);
			assertNotNull("No se encontro objeto", notaExpedienteDTO);
			logger.info(" getIdNota:"+ notaExpedienteDTO.getIdNota());
			logger.info(" getDescripcion:"+ notaExpedienteDTO.getDescripcion());
			
		}catch (NSJPNegocioException ex) {
			logger.error(ex.getMessage(), ex);
        }
		
	}
	
}
