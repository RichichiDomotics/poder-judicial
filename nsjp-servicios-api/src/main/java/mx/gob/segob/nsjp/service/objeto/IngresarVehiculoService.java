/**
* Nombre del Programa : IngresarVehiculoService.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 2 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Contrato del servicio para ingresar Vehiculo.                      
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
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;

/**
 * Contrato del servicio para ingresar Vehiculo.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface IngresarVehiculoService {
	
	/**
	 * En caso que {@code vehiculoDto.ElementoId == null ||
         * vehiculoDto.ElementoId == 0} se crea un nuevo registro en la base.
         * En caso que {@code vehiculo.ElementoId != null
         * && vehiculo.ElementoId > 0} se actualiza el registro del vehiculo.
	 * @param vehiculoDto El vehiculo a ingresar o actualizar.
	 * throws NSJPNegocioException En caso que:
         * <ol>
         * <li> {@code vehiculoDto == null}
         * <li> {@code vehiculoDto.ElementoId < 0}
         * <li> {@code vehiculoDto.ExpedienteDTO == null}
         * <li> {@code vehiculoDto.ExpedienteDTO.ExpedienteId() == null}
         * </ol>
	 */
	public Long ingresarVehiculo(VehiculoDTO vehiculoDto)throws NSJPNegocioException;;

}
