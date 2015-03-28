/**
* Nombre del Programa : ConsultarMovimientosDeObjetosAlmacenServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Pruebas unitarias del servicio.
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
package mx.gob.segob.nsjp.service.test.almacen.impl;

import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.service.almacen.ConsultarMovimientosDeObjetosAlmacenService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Pruebas unitarias del servicio.
 * @version 1.0
 * @author GustavoBP
 */
public class ConsultarMovimientosDeObjetosAlmacenServiceImplTest  
	extends BaseTestServicios<ConsultarMovimientosDeObjetosAlmacenService> {

	public void testConsultarEvidenciasAsociadasCadenaCustodiaPorNumeroExpediente(){
		logger.info("Probando el servicio de: consultarEvidenciasAsociadasCadenaCustodiaPorNumeroExpediente");

		//String numeroExpediente = "NSJYUCPG20104486";
		String numeroExpediente = "NSJYUCPG20104451";
		
		try {
			List<EvidenciaDTO> evidenciasDTO = service.consultarEvidenciasAsociadasCadenaCustodiaPorNumeroExpediente(numeroExpediente);
			assertFalse("Se obtuvo una lista de evidencias vacia", evidenciasDTO.isEmpty() );
			
			for (EvidenciaDTO evidenciaDTO : evidenciasDTO) {
				logger.info("********");
				logger.info("EvidenciaDTO - EvidenciaId:" + evidenciaDTO.getEvidenciaId());
				logger.info("EvidenciaDTO - FechaLevantamiento:" + evidenciaDTO.getFechaLevantamiento());
				logger.info("EvidenciaDTO - ElementoId:" + evidenciaDTO.getObjeto().getElementoId());
				if (evidenciaDTO.getObjeto()!= null){
					logger.info("EvidenciaDTO - Objeto - TipoObjeto:" + evidenciaDTO.getObjeto().getTipoObjeto().toString());
					logger.info("EvidenciaDTO - Objeto - TipoObjeto:" + evidenciaDTO.getObjeto().getTipoObjeto().getNombreEntity());
					logger.info("EvidenciaDTO - Objeto - TipoObjeto:" + new  ValorDTO(evidenciaDTO.getObjeto().getTipoObjeto().getValorId()).getNombreCampo());
					logger.info("EvidenciaDTO - Objeto - TipoObjeto:" + evidenciaDTO.getObjeto().getTipoObjeto().getValorId());
					logger.info("EvidenciaDTO - Objeto - TipoObjeto:" + evidenciaDTO.getObjeto().getTipoObjeto().getNombreEntity());
					logger.info("EvidenciaDTO - Objeto - Descripcion:" + evidenciaDTO.getObjeto().getDescripcion());
				}
				logger.info("EvidenciaDTO - Descripcion:" + evidenciaDTO.getDescripcion());
				if (evidenciaDTO.getEslabones()!= null){
					Set<EslabonDTO> eslabonesDTO = evidenciaDTO.getEslabones();
					for (EslabonDTO eslabonDTO : eslabonesDTO) {
						logger.info("eslabonDTO :" + eslabonDTO.getEslabonId());

						logger.info("eslabonDTO Datos de la Entrega ");
						logger.info("eslabonDTO Nombre:"+ eslabonDTO.getFuncionariEntrega());
						logger.info("****eslabonDTO Fecha Entrega:"+ eslabonDTO.getFechaInicioMovimiento());
						logger.info("eslabonDTO :" + eslabonDTO.getTipoEslabon());
						logger.info("eslabonDTO :" + eslabonDTO.getObservacion());
						
						logger.info("eslabonDTO Datos de la Recepcion ");
						logger.info("eslabonDTO Nombre:"+ eslabonDTO.getFuncionariRecibe());
						logger.info("****eslabonDTO :" + eslabonDTO.getFechaFinMovimiento());
						logger.info("eslabonDTO :" + eslabonDTO.getObservacion());
					}
				}
			}
			logger.info("TOTAL EVIDENCIAS: "+ evidenciasDTO.size());
			
		} catch (NSJPNegocioException e) {
			logger.info(e);
            fail("Ocurrio una excepcion al ejecutar el test de consultarEvidenciasAsociadasCadenaCustodiaPorNumeroExpediente");
		}
		
		
	}
	
}

