/**
* Nombre del Programa : ConsultarComplejidadAudienciaServiceImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/06/2011
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
package mx.gob.segob.nsjp.service.test.audiencia.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.service.audiencia.ConsultarComplejidadAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class ConsultarComplejidadAudienciaServiceImplTest extends
		BaseTestServicios<ConsultarComplejidadAudienciaService> {

//	/**
//	 * Test method for {@link mx.gob.segob.nsjp.service.audiencia.impl.ConsultarComplejidadAudienciaServiceImpl#consultarComplejidadAudiencia(mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO)}.
//	 */
//	public void testConsultarComplejidadAudiencia() {
//		AudienciaDTO audienciaDTO=new AudienciaDTO();
//		audienciaDTO.setAudienciaID(1L);
//		try {
//			ValorDTO complejidad = service.consultarComplejidadAudiencia(audienciaDTO);
//			if(complejidad!=null)
//				logger.info("La complejidad es: "+complejidad);
//			else
//				assertTrue("FALLO: consultarComplejidadAudiencia",false);
//		} catch (NSJPNegocioException e) {
//			e.printStackTrace();
//			assertTrue("FALLO: consultarComplejidadAudiencia",false);
//		}
//	}
	
	public void testConsultarComplejidadAudiencia() throws NSJPNegocioException{
		service.actualizarComplejidadAudiencia(1714L, 1L);
	}

}
