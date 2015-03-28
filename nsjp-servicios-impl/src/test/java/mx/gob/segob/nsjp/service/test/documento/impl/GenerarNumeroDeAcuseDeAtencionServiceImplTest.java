/**
** Nombre del Programa : GenerarNumeroDeAcuseDeAtencionServiceImplTest.java                                    
* Autor                            : Emigdio Hernández                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 22/06/2011
* Marca de cambio        : N/A                                                     
* Descripcion General    : Prueba unitaria para el servicio Generar Numero de Acuse de Atencion                    
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
package mx.gob.segob.nsjp.service.test.documento.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.documento.GenerarNumeroDeAcuseDeAtencionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio Generar Numero de Acuse de Atencion
 * @author rgama
 * @version 1.0
 */
public class GenerarNumeroDeAcuseDeAtencionServiceImplTest extends BaseTestServicios<GenerarNumeroDeAcuseDeAtencionService> {

	public void testgenerarNumeroDeAcuseDeAtencion() {
			
		try {			
			Long numeroDeFolio = service.generarNumeroDeAcuse(1L,29L);			
			assertTrue("El numero de acuse asignado debe de ser mayor a cero", numeroDeFolio >0);
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}
	
}
