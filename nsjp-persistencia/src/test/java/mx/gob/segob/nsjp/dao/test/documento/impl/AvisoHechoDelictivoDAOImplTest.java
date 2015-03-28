/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.dao.documento.AvisoHechoDelictivoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.AvisoHechoDelictivo;

/**
 * @author adrian
 *
 */
public class AvisoHechoDelictivoDAOImplTest extends BaseTestPersistencia<AvisoHechoDelictivoDAO> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.dao.documento.impl.AvisoHechoDelictivoDAOImpl#consultarAvisosHDelictivoPorEstatus(java.lang.Long)}.
	 */
	public void testConsultarAvisosHDelictivoPorEstatus() {
		List<AvisoHechoDelictivo> avisos = daoServcice.consultarAvisosHDelictivoPorEstatus(EstatusNotificacion.NO_ATENDIDA.getValorId());
		assertNotNull("Trae resultados", avisos);
		logger.info("Existen "+avisos.size()+" avisos");
		for (AvisoHechoDelictivo ahd : avisos) {
			logger.info("----");
		}
	}

	
	public void testObtenerUltimoFolio() {
		String respuesta = daoServcice.obtenerUltimoFolio();
		
		assertNotNull(respuesta);
		logger.info("---------------------------");
		logger.info("Ultimo Folio :: "+respuesta);
		logger.info("---------------------------");
	}
}
