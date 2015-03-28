/**
* Nombre del Programa : ConsultarInvolucradosPorAudienciaServiceImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/06/2011
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
package mx.gob.segob.nsjp.service.test.involucrado.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradosPorAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class ConsultarInvolucradosPorAudienciaServiceImplTest extends
		BaseTestServicios<ConsultarInvolucradosPorAudienciaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.involucrado.impl.ConsultarInvolucradosPorAudienciaServiceImpl#consultarInvolucradosPorAudiencia(java.lang.Long, mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO)}.
	 */
	public void testConsultarInvolucradosPorAudiencia() {
		Calidades calidadValor = Calidades.PROBABLE_RESPONSABLE_PERSONA;
		AudienciaDTO audienciaDTO=new AudienciaDTO();
		audienciaDTO.setId(2L);
		
		try {
			List<InvolucradoDTO> involucrados = service.consultarInvolucradosPorAudiencia(calidadValor.getValorId(), audienciaDTO);
			
			logger.info("\n\r Existen "+involucrados.size()+ " involucrados");
			for (InvolucradoDTO dto : involucrados) {
				logger.info("Involucrado ID: "+dto.getFolioIdentificacion());
				logger.info("Calidad: "+dto.getCalidadDTO().getValorIdCalidad().getValor());
			}
			
		} catch (NSJPNegocioException e) {
			assertTrue("Fallo: consultarInvolucradosPorAudiencia", false);
			e.printStackTrace();
		}
	}

}
