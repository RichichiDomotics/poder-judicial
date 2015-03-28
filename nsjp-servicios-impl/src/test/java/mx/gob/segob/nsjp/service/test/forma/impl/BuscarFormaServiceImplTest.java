/**
*
* Nombre del Programa : GuardarArchivoDigitalServiceImplTest.java                                    
* Autor                            : Emigdio Hernández                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/05/2011
* Marca de cambio        : N/A                                                     
* Descripcion General    : Prueba unitaria para el servicio de guardar archivo digital                    
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.service.test.forma.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.service.forma.BuscarFormaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de guardar archivo digital
 * @author Emigdio Herández
 * @version 1.0
 */
public class BuscarFormaServiceImplTest extends BaseTestServicios<BuscarFormaService> {

	public void testBuscarForma() {

			
				
		try {
			FormaDTO forma = service.buscarForma(new Long(1));
			
			assertTrue("El servicio debe retornar la Forma consultada", forma != null);
			
			
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
		
	}
}
