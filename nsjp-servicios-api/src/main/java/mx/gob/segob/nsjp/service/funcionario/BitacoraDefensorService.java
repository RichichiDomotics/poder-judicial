/**
 * Nombre del Programa  : BitacoraDefensorService.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 21 Jul 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Provee los servicios para la manipulación de los objetos
 * 						  Bitacora de Defensor
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.funcionario;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.BitacoraDefensorDTO;

/**
 * Provee los servicios para la manipulación de los objetos Bitacora de Defensor
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
public interface BitacoraDefensorService {

	/**
	 * Almacena la bitacora de un expediente que esta manejando un defensor. 
	 * @param bitacora Bitacora con la información que se va a almacenar
	 * @return identificador con el que se almaceno la bitacora.
	 * @throws NSJPNegocioException
	 */
	public void guardarBitacoraDefensor(BitacoraDefensorDTO bitacora) throws NSJPNegocioException;
	
}
