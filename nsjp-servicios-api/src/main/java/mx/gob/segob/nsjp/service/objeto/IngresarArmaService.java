/**
* Nombre del Programa : IngresarArmaService.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 5 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Contrato del servicio para consultar un arma de acuerdo a ciertos parametros de busqueda.                      
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
import mx.gob.segob.nsjp.dto.objeto.ArmaDTO;

/**
 * Contrato del servicio para consultar un arma de acuerdo a ciertos parametros de busqueda.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarArmaService {

	/**
	 * Para indicar a este metodo que se requiere guardar un nuevo registro
         * en la base de datos debe pasar lo siguiente:
         * {@code armaDto.elementoId == null} o {@code armaDto.elementoId == 0}<br/>
         * Los campos requeridos para guardar un arma son:
         * <ol>
         * <li> armaDto.expediente.expedienteId
         * <li> armaDto.calidad.calidades
         * <li> armaDto.valorByCondicionFisicaVal
         * </ol>
         * Para actualizar un arma se requiere que {@code
         * armaDto.elementoId != null} y que {@code armaDto.elementoId > 0}
	 * @param armaDto El arma a guardar o actualizar.
	 * @return Long El id del arma guardada o actualizada.
	 * @throws NSJPNegocioException Siempre se lanza cuando
         * {@code armaDto == null}.<br/>
         * En el caso de guardar un arma esta
         * excepcion se lanza cuando se cumple alguna de las siguiente
         * condiciones:
         * <ol>
         * <li> armaDto.getExpedienteDTO() == null
         * <li> armaDto.getExpedienteDTO().getExpedienteId() == null
         * </ol>
         * En caso de actualizar un arma se lanza esta excepcion cuando
         * {@code armaDto.elementoId < 0}
	 */
	Long ingresarArma(ArmaDTO armaDto)throws NSJPNegocioException;
}
