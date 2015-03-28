/**
* Nombre del Programa : ConsultarDelitoServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Pruebas unitarias para los metodos de consulta de los delitos
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.delito.ConsultarDelitoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Pruebas unitarias para los metodos de consulta de los delitos.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class ConsultarDelitoServiceImplTest extends BaseTestServicios<ConsultarDelitoService> {

	public void testConsultarDelitoExpediente () {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO(11L);
		
		try {
			List<DelitoDTO> respuesta = service.consultarDelitoExpediente(expedienteDTO);
			assertNotNull("La lista no puede ser nula ", respuesta);
			for (DelitoDTO delito : respuesta) {
				logger.info("/**** Delito id: " + delito.getDelitoId());// + " ," + delitoDTO.getNombreDelito()+",esPrincipal:"+delitoDTO.getEsPrincipal()+", esProbable:"+delitoDTO.getEsProbable());
				logger.info("Nombre: "+delito.getCatDelitoDTO().getNombre());
				logger.info("Expediente: "+delito.getExpedienteDTO().getNumeroExpediente()+ " ("+delito.getExpedienteDTO().getExpedienteId()+")");
				logger.info("Probable: "+delito.getEsProbable());
				logger.info("Principal: "+delito.getEsPrincipal());
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}				
	}
	
	public void consultarMediaAritmeticaDelitosExpedienteImplTest(){
				
		try {
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			expedienteDTO.setNumeroExpedienteId(2L);
			Boolean respuesta = false;
			respuesta = service.consultarMediaAritmeticaDelitosExpediente(expedienteDTO);
			logger.info("Respuesta Obtenido: "+respuesta);
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
}
		
		
	}
}
