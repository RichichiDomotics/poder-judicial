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
package mx.gob.segob.nsjp.service.test.documento.impl;

import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.service.documento.CargarDocumentoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de guardar archivo digital
 * @author Emigdio Herández
 * @version 1.0
 */
public class CargarDocumentoServiceImplTest extends BaseTestServicios<CargarDocumentoService> {

	public void testCargarDocumento() {
		try {
			ExpedienteDTO expediente = new ExpedienteDTO(new Long(151));
			expediente.setNumeroExpedienteId(199L);
			FormaDTO forma = new FormaDTO(Formas.DENUNCIA.getValorId());
			DocumentoDTO doc = service.cargarDocumentoPorExpedienteYForma(expediente, forma);
			
			assertTrue("El servicio debe retornar el documento cargado", doc != null);
			logger.info("Texto Parcial: "+doc.getTextoParcial());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}
	
	public void testCargarDocumentoPorId(){
	
		
		try {
			
			DocumentoDTO doc = service.cargarDocumentoPorId(5L);
			
			assertTrue("El servicio debe retornar el documento cargado", doc != null);
			
			
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}
	
}
