/**
* Nombre del Programa : MedidaAlternaDAOImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 1 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para metodos de acceso a datos de la entidad Medida Alterna
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
package mx.gob.segob.nsjp.dao.test.medida.impl;

import mx.gob.segob.nsjp.comun.enums.medida.TipoMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.medida.MedidaAlternaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;

/**
 * Prueba unitaria para metodos de acceso a datos de la entidad Medida Alterna.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class MedidaAlternaDAOImplTest extends
		BaseTestPersistencia<MedidaAlternaDAO> {
	
	public void testObtenerMedAltPorFechasYTipoNedida () {
		try {
			Long respuesta = daoServcice.obtenerMedAltPorFechasYTipoNedida(DateUtils.obtener("01/07/2011"), DateUtils.obtener("01/09/2011"), TipoMedida.GARANTIA_ECONOMICA.getValorId());
		
			assertNotNull(respuesta);
			logger.info("Respuesta :: "+respuesta);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}

}
