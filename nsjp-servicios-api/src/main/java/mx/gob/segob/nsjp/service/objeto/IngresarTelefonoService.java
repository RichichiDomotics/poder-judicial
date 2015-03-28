/**
* Nombre del Programa : IngresarTelefonoService.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 5 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Contrato del servicio para ingresar Telefonia.                      
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
package mx.gob.segob.nsjp.service.objeto;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.objeto.TelefoniaDTO;

/**
 * Contrato del servicio para ingresar Telefonia.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarTelefonoService {
	
	/**
	 * 
	 * @param telefoniaDTO
	 * @return Long
	 * @throws NSJPNegocioException
	 */
	
	Long ingresarTelefono(TelefoniaDTO telefoniaDTO)throws NSJPNegocioException;

}
