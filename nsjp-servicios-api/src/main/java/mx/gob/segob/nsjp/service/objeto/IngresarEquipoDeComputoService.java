/**
* Nombre del Programa : IngresarEquipoDeComputoService.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 4 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Contrato del servicio para ingresar equipo de computo.                 
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
import mx.gob.segob.nsjp.dto.objeto.EquipoComputoDTO;

/**
 * Contrato del servicio para ingresar equipo de computo.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarEquipoDeComputoService {
	
	/**
	 * 
	 * @param e
	 * @return Long
	 * @throws NSJPNegocioException
	 */
	
	Long ingresarEquipoComputo(EquipoComputoDTO e)throws NSJPNegocioException;

}
