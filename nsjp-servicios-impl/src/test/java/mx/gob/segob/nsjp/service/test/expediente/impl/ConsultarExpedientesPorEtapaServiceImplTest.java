/**
* Nombre del Programa : ConsultarExpedientesPorEtapaServiceImplTest.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio de Consultar Expedientes por etapa
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

import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.expediente.ConsultarExpedientesPorEtapaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de Consultar Expedientes por etapa
 * en base al area y al usuario logueado en el sistema
 * @version 1.0
 * @author rgama
 *
 */
public class ConsultarExpedientesPorEtapaServiceImplTest extends BaseTestServicios<ConsultarExpedientesPorEtapaService> {

	public void testConsultarExpedientesPorEtapa () {
		Long usuarioId = 3L;
		//Long areaId = 1L;
		
		try {
			List<ExpedienteDTO> loResultado =service.consultarExpedientesPorEtapa(EtapasExpediente.TECNICA, usuarioId, null);
			assertTrue("La lista de Expedientes debe de cer mayor a cero", loResultado.size()>0);
			for (ExpedienteDTO expedienteDTO : loResultado) {
				System.out.println("-------------------------------------");
				System.out.println("El id del expediente es: " + expedienteDTO.getExpedienteId());
				System.out.println("El numero expediente es: " + expedienteDTO.getNumeroExpedienteId()+" ("+expedienteDTO.getNumeroExpediente()+")");
			}
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testConsultarEtapaDelExpediente() {
		try {
			EtapasExpediente respuesta = service.consultarEtapaDelExpediente(5L);
			assertNotNull("la etapa del expediente no puede ser null ", respuesta);
			logger.info("Etapa expediente :: "+respuesta);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testCambiarEtapaDelExpediente() {
		try {
			service.cambiarEtapaDelExpediente(5L, EtapasExpediente.INTEGRACION);
			logger.info("/***** LA ACTUALIZACION SE REALIZO CON EXITO ****/");
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
			fail();
		}
	}
}
