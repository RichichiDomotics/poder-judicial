/**
* Nombre del Programa : ConsultarRelNumExpedientePorIdAuditoriaServiceTest.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio de ConsultarRelNumExpedientePorIdAuditoriaService
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.RelNumExpedienteAuditoriaDTO;
import mx.gob.segob.nsjp.service.expediente.ConsultarRelNumExpedientePorIdAuditoriaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de Consultar RelNumExpedientePorIdAuditoria por id Auditoria
 * @version 1.0
 * @author rgama
 *
 */
public class ConsultarRelNumExpedientePorIdAuditoriaServiceTest extends BaseTestServicios<ConsultarRelNumExpedientePorIdAuditoriaService
> {

	public void testConsultarExpedientesPorEtapa() {
		Long idAuditoria = 5L;		
		
		try {
			RelNumExpedienteAuditoriaDTO relacion =service.consultarRelacionPorIdAuditoria(idAuditoria);

			if(relacion != null){
				assertNotNull(relacion);
				logger.info("ID de la realcion: "+ relacion.getRelNumExpedienteAuditoriaId());

				ExpedienteDTO loNumeroExpediente = relacion.getNumeroExpediente();
				if(loNumeroExpediente != null){
					logger.info("INFO DEL NUMERO EXPEDIENTE BASE");
					logger.info("  Nombre del AMP dueño del expediente auditado: "+ (loNumeroExpediente.getUsuario() != null && loNumeroExpediente.getUsuario().getFuncionario() != null
							&& loNumeroExpediente.getUsuario().getFuncionario().getNombreCompleto() != null?
									loNumeroExpediente.getUsuario().getFuncionario().getNombreCompleto() :"-"));
					logger.info("  Numero de expediente auditado: "+ (loNumeroExpediente.getNumeroExpediente() != null ? loNumeroExpediente.getNumeroExpediente() :"-"));
					logger.info("  Area del expediente auditado: "+ (loNumeroExpediente.getArea() != null &&
							loNumeroExpediente.getArea().getNombre() != null ? loNumeroExpediente.getArea().getNombre() :"-"));
					logger.info("  Fecha de creacion de la carpeta auditada: "+ (loNumeroExpediente.getFechaAperturaNumeroExp()!= null && loNumeroExpediente.getFechaAperturaNumeroExp() != null?
							DateUtils.formatear(loNumeroExpediente.getFechaAperturaNumeroExp()) :"-"));
				}
				
			}else{
				assertNull(relacion);
			}
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}

}
