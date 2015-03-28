/**
 * Nombre del Programa : CatCategoriaRelacionDAOImplTest.java
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
package mx.gob.segob.nsjp.dao.test.relacion.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.relacion.CatCategoriaRelacionDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatCategoriaRelacion;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
public class CatCategoriaRelacionDAOImplTest extends
		BaseTestPersistencia<CatCategoriaRelacionDAO> {

	/**
	 * Test method for
	 * {@link mx.gob.segob.nsjp.dao.relacion.impl.CatCategoriaRelacionDAOImpl#consultarCatCategoriaRelacionSiDocumento(java.lang.Boolean)}
	 * .
	 */
	public void testConsultarCatCategoriaRelacionSiDocumento() {
		List<CatCategoriaRelacion> ccRelaciones = daoServcice
				.consultarCatCategoriaRelacionSiDocumento(false);
		logger.info("Existen " + ccRelaciones.size()
				+ " catCatetoriaRelaciones");
		for (CatCategoriaRelacion ccRel : ccRelaciones) {
			logger.info("--------------------------");
			logger.info("ID: " + ccRel.getCatCategoriaRelacionId());
			logger.info("Relacion: " + ccRel.getDesCategoriaRelacion());
			String doc = (ccRel.getEsDocumento()) ? "Sí" : "No";
			logger.info("Documento: " + doc);
		}
	}

}
