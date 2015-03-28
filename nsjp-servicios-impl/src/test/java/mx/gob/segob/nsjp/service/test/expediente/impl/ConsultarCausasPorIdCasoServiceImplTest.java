/**
* Nombre del Programa : ConsultarCausasPorIdCasoServiceImplTest.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Jul 2011
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.expediente.ConsultarCausasPorIdCasoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de Consultar tocas asociadas a un idCaso
 * @version 1.0
 * @author rgama
 *
 */
public class ConsultarCausasPorIdCasoServiceImplTest extends BaseTestServicios<ConsultarCausasPorIdCasoService> {

	public void testConsultarCausasPorIdCaso () {
		Long idCaso = 4L;  
		
		List<ExpedienteDTO> expedientes;
		try {
			expedientes = service.consultarCausasPorIdCasoService(idCaso);
			if(expedientes!= null ){
				for (ExpedienteDTO expediente : expedientes) {
					logger.info("IDExpedienteDTO:"+expediente.getExpedienteId());
					logger.info("IDNumeroExpedienteDTO:"+expediente.getNumeroExpedienteId());
					logger.info("Numero ExpedienteDTO:"+expediente.getNumeroExpediente());
				} 
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
		
	}
}
