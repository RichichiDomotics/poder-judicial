/**
* Nombre del Programa : ConsultarAudienciasPorEstatusServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio de consulta de audiancias
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
package mx.gob.segob.nsjp.service.test.audiencia.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.service.audiencia.ConsultarAudienciasPorEstatusService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de consulta de audiancias.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class ConsultarAudienciasPorEstatusServiceImplTest extends
		BaseTestServicios<ConsultarAudienciasPorEstatusService> {

	public void testConsultarAudienciasPorEstatus() {
		try {
			List<AudienciaDTO> respuesta = service.consultarAudienciasPorEstatus(EstatusAudiencia.PROGRAMADA.getValorId());
			assertTrue("La lista debe tener minimo una audiencia ", respuesta.size()>0);
			logger.info("Audiencias ::: "+respuesta.size());
			for (AudienciaDTO audienciaDTO : respuesta) {
				logger.info("-----------------------");
				logger.info("Audiencia ID :: "+audienciaDTO.getId());
				logger.info("Audiencia tipo :: "+audienciaDTO.getTipoAudiencia().getValor());
				if (audienciaDTO.getExpediente()!=null) {
					logger.info("Numero expediente :: "+audienciaDTO.getExpediente());
					if (audienciaDTO.getExpediente().getCasoDTO()!=null) {
						logger.info("Caso num :: "+audienciaDTO.getExpediente().getCasoDTO().getNumeroGeneralCaso());
					}
				}
				logger.info("-----------------------");
			}
		} catch (NSJPNegocioException e) {
		logger.error(e.getMessage());
		}
	}
}
