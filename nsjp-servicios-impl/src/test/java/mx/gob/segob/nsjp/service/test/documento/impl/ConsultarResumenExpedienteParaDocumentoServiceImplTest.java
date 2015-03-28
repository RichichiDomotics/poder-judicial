/**
* Nombre del Programa : ConsultarResumenExpedienteParaDocumentoServiceImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio de consulta de resumen de expediente
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
package mx.gob.segob.nsjp.service.test.documento.impl;

import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.ParametrosDocumentoDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.service.documento.ConsultarResumenExpedienteParaDocumentoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de consulta de resumen de expediente
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class ConsultarResumenExpedienteParaDocumentoServiceImplTest  extends BaseTestServicios<ConsultarResumenExpedienteParaDocumentoService> {

	@SuppressWarnings("unused")
	public void testConsultarResumenExpediente(){
		ParametrosDocumentoDTO resumen = null;
		try{
			ExpedienteDTO exp = new ExpedienteDTO((long)2);
			exp.setArea(new AreaDTO(Areas.COORDINACION_ATENCION_TEMPRANA_PG));
			resumen = service.consultarResumenExpedienteParaDocumento(exp);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
