/**
* Nombre del Programa : MedidaCautelarDAOImplTest.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18/08/2011
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
package mx.gob.segob.nsjp.dao.test.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.medida.TipoMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.documento.MedidaCautelarDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.MedidaCautelar;

/**
 * Clase de pruebas unitarias para el dao de MedidaCautelar
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author Emigdio
 *
 */
public class MedidaCautelarDAOImplTest extends BaseTestPersistencia<MedidaCautelarDAO>{
	
	public void testConsultarMedidasCautelaresPorExpediente(){
		
		List<MedidaCautelar> medidas = daoServcice.obtenerMedidasCautelaresPorExpediente(1L);
		logger.debug("Medidas:" + medidas.size());
		
	}
	
	   public void testobtenerMedidasCautelaresPorNumeroExpediente(){
	        
	        List<MedidaCautelar> medidas = daoServcice.obtenerMedidasCautelaresPorNumeroExpediente("NSJYUCPJ20114433333",26L);
	        logger.debug("Medidas:" + medidas.size());
	        
	    }
	
	public void testObtenerMedCauPorFechasYTipoNedida() {
		try {
			Long respuesta = daoServcice.obtenerMedCauPorFechasYTipoNedida(DateUtils.obtener("01/07/2011"), DateUtils.obtener("01/09/2011"), TipoMedida.GARANTIA_ECONOMICA.getValorId());
		
			assertNotNull(respuesta);
			logger.info("Respuesta :: "+respuesta);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}

}
