/**
* Nombre del Programa : GuardarCadenaCustodiaServiceImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18/07/2011
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
package mx.gob.segob.nsjp.service.test.cadenadecustodia.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.cadenadecustodia.GuardarCadenaCustodiaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class GuardarCadenaCustodiaServiceImplTest extends BaseTestServicios<GuardarCadenaCustodiaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.cadenadecustodia.impl.GuardarCadenaCustodiaServiceImpl#guardarCadenaCustodia(mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO, mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)}.
	 */
	public void testGuardarCadenaCustodia() {
		ExpedienteDTO expedienteDTO=new ExpedienteDTO(326L);
		CadenaDeCustodiaDTO cust=new CadenaDeCustodiaDTO();
		cust.setFechaLevantamiento(new Date());
		cust.setQuienEmbala("quienEmbala");
		cust.setQuienEntrega("quienEntrega");
		cust.setQuienRecibe("quienRecibe");
		cust.setQuienTransporta("quienTransporta");
			
		try {
			CadenaDeCustodiaDTO cadena = service.guardarCadenaCustodia(cust, expedienteDTO);
			if(cadena!=null){
				logger.info("Exito con la cadena: "+cadena.getCadenaDeCustodiaId());
				logger.info("Folio: "+cadena.getFolio());
				assertTrue("Exito", true);
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue("FALLA", false);
		}
		
	}

}
