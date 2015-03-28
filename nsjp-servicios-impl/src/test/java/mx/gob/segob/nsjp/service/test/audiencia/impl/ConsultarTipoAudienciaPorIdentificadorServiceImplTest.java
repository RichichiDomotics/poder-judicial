/**
* Nombre del Programa : ConsultarTipoAudienciaPorIdentificadorServiceImplTest.java
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
import mx.gob.segob.nsjp.service.audiencia.ConsultarTipoAudienciaPorIdentificadorService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class ConsultarTipoAudienciaPorIdentificadorServiceImplTest extends
		BaseTestServicios<ConsultarTipoAudienciaPorIdentificadorService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.audiencia.impl.ConsultarTipoAudienciaPorIdentificadorServiceImpl#consultarTipoAudienciaPorIdentificador(mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO)}.
	 */
	public void testConsultarTipoAudienciaPorIdentificador() {
		AudienciaDTO audienciaDTO = new AudienciaDTO();
		audienciaDTO.setId(2L);
		try {
			ValorDTO tipoDTO = service.consultarTipoAudienciaPorIdentificador(audienciaDTO);
			logger.info("El tipo es: "+tipoDTO.getValor());
			assertTrue("EXITO. El tipo es: "+tipoDTO.getValor(),tipoDTO!=null);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue("FALLO: consultarTipoAudienciaPorIdentificador", false);
		}
	}

}
