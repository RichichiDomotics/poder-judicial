/**
* Nombre del Programa : ConsultarCatCategoriaRelacionServiceImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/07/2011
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
package mx.gob.segob.nsjp.service.test.relacion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.relacion.CatCategoriaRelacionDTO;
import mx.gob.segob.nsjp.service.relacion.ConsultarCatCategoriaRelacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class ConsultarCatCategoriaRelacionServiceImplTest extends
		BaseTestServicios<ConsultarCatCategoriaRelacionService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.relacion.impl.ConsultarCatCategoriaRelacionServiceImpl#consultarCatCategoriaRelacionSiDocumento(java.lang.Boolean)}.
	 */
	public void testConsultarCatCategoriaRelacionSiDocumento() {
		try {
			List<CatCategoriaRelacionDTO> ccRelaciones = service.consultarCatCategoriaRelacionSiDocumento(false);
			logger.info("Existen " + ccRelaciones.size()
					+ " catCatetoriaRelaciones");
			for (CatCategoriaRelacionDTO ccRel : ccRelaciones) {
				logger.info("--------------------------");
				logger.info("ID: " + ccRel.getCatCategoriaRelacionId());
				logger.info("Relacion: " + ccRel.getDesCategoriaRelacion());
				String doc = (ccRel.getEsDocumento()) ? "Sí" : "No";
				logger.info("Documento: " + doc);
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}
