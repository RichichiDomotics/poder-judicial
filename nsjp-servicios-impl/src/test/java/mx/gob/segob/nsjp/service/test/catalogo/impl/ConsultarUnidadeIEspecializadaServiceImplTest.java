/**
* Nombre del Programa : ConsultarUnidadeIEspecializadaServiceImplTest.java
* Autor                            : AdrianECH
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/03/2012
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
package mx.gob.segob.nsjp.service.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO;
import mx.gob.segob.nsjp.service.catalogo.ConsultarUnidadeIEspecializadaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * Fecha: 06/03/2012
 * @version 1.0
 * @author AdrianECH
 * 
 */
public class ConsultarUnidadeIEspecializadaServiceImplTest extends
		BaseTestServicios<ConsultarUnidadeIEspecializadaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.catalogo.impl.ConsultarUnidadeIEspecializadaServiceImpl#consultarUnidadesIEspecializadas()}.
	 */
	public void testConsultarUnidadesIEspecializadas() {
		try {
			List<CatUIEspecializadaDTO> unidades = service.consultarUnidadesIEspecializadas();
			assertNotNull(unidades);
			logger.info("Existen "+unidades.size()+" unidades");
			for (CatUIEspecializadaDTO dto : unidades) {
				logger.info("-------------------------");
				logger.info("Id: "+dto.getCatUIEId());
				logger.info("Clave: "+dto.getClaveUIE());
				logger.info("Nombre: "+dto.getNombreUIE());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.catalogo.impl.ConsultarUnidadeIEspecializadaServiceImpl#consultarUnidadIEspecializada(java.lang.Long)}.
	 */
	public void testConsultarUnidadIEspecializada() {
		try {
			CatUIEspecializadaDTO uie = service.consultarUnidadIEspecializada(1L);
			assertNotNull(uie);
				logger.info("-------------------------");
				logger.info("Id: "+uie.getCatUIEId());
				logger.info("Clave: "+uie.getClaveUIE());
				logger.info("Nombre: "+uie.getNombreUIE());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
