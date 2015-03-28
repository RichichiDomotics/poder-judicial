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
package mx.gob.segob.nsjp.service.test.archivo.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.service.archivo.GuardarArchivoDigitalService;
import mx.gob.segob.nsjp.service.documento.CargarDocumentoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de guardar archivo digital
 * @author Emigdio Herández
 * @version 1.0
 */
public class GuardarArchivoDigitalServiceImplTest extends BaseTestServicios<GuardarArchivoDigitalService> {

	
	public void testGuardarArchivoDigital() {

		ArchivoDigitalDTO archivo = new ArchivoDigitalDTO();
		
		archivo.setContenido("Contenido de la prueba unitaria".getBytes());
		archivo.setNombreArchivo("pruebaUnitaria");
		archivo.setTipoArchivo(".txt");
		
		
				
		try {
			Long resultado = service.guardarArchivoDigital(archivo);
			
			assertTrue("El servicio debe retornar el identificador del individuo creado ", resultado>0);
			
			
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
