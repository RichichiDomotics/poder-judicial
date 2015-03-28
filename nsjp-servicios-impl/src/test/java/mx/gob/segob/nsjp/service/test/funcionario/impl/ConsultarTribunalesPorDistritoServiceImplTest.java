/**
 * Nombre del Programa : ConsultarFuncionarioPorFiltroServiceImplTest.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20/07/2011
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
package mx.gob.segob.nsjp.service.test.funcionario.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteWSDTO;
import mx.gob.segob.nsjp.service.catalogo.ConsultarTribunalesPorDistritoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class ConsultarTribunalesPorDistritoServiceImplTest extends
		BaseTestServicios<ConsultarTribunalesPorDistritoService> {

	public void testConsultarTribunalesXDistrito(){
		try {
			Long distritoID = 2L;
			List<CatDiscriminanteWSDTO> tribunalesDTO = (List<CatDiscriminanteWSDTO>)service.consultarTribunalesPorDistrito(distritoID);
			assertNotNull(tribunalesDTO);

			for (CatDiscriminanteWSDTO dto : tribunalesDTO) {
				logger.info("----------------------");
				logger.info("ID: "+dto.getCatDiscriminanteId());
				logger.info("Clave: "+dto.getClave());
				logger.info("Nombre: "+dto.getNombre());
				
			}
			
			logger.info("Existen "+tribunalesDTO.size()+ " Tribunales del distrito" + distritoID);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
}
