/**
* Nombre del Programa : ConsultarCarpetaDeInvestigacionServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/07/2011
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
package mx.gob.segob.nsjp.service.test.expediente.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.expediente.ConsultarCarpetaDeInvestigacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.services.dtos.print.ExpedientePrint;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ConsultarCarpetaDeInvestigacionServiceImplTest extends BaseTestServicios<ConsultarCarpetaDeInvestigacionService> {

	public void testConsultarCarpetaDeInvestigacionService(){
		String numeroGeneralCaso ="YUC/PG/XX/PGE/2011/AA-00023";

		try {
			ExpedienteDTO expedienteDTO = service.consultarCarpetaDeInvestigacion(numeroGeneralCaso);
			assertNotNull("Debe de existir un Expediente asociado al caso con los parámetros de búsqueda", expedienteDTO);
			logger.info(" IdExpediente: "+expedienteDTO.getExpedienteId());
			ExpedientePrint.imprimirDatosExpediente(expedienteDTO);
			logger.info("#################################################################################################################");
			//Test de Tranformar el expediente de DTO a WSDTO
			//ExpedienteWSDTO expedienteWSDTO = ExpedienteWSDTOTransformer.transformarWSDTO(expedienteDTO);
			//ExpedientePrint.imprimirDatosExpediente(expedienteWSDTO);
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
}
	
	
