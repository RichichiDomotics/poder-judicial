/**
* Nombre del Programa : MarcarAudienciaResolutivosServiceImplTest.java
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
import mx.gob.segob.nsjp.service.audiencia.MarcarAudienciaResolutivosService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class MarcarAudienciaResolutivosServiceImplTest extends
		BaseTestServicios<MarcarAudienciaResolutivosService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.audiencia.impl.MarcarAudienciaResolutivosServiceImpl#marcarAudienciaResolutivos(java.lang.Long)}.
	 */
	public void testMarcarAudienciaResolutivos() {
		
		Long idAudiencia=14L;
		try {
			service.marcarAudienciaResolutivos(idAudiencia);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue("FALLO: marcarAudienciaResolutivos", false);
		}
	}

}
