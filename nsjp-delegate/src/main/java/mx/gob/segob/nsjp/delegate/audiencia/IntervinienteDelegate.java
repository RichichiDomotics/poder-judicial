/**
* Nombre del Programa : IntervinienteDelegate.java
* Autor                            : LuisMG
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/10/2011
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
package mx.gob.segob.nsjp.delegate.audiencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.IntervinienteDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author LuisMG
 *
 */
public interface IntervinienteDelegate {
	
	/**
	 * De acuerdo a los IdAudiencia y idInvolucrado, 
	 * se crea un interviniente con los datos en default es decir en null
	 * @param idAudiencia
	 * @param idInvolucrado
	 * @return
	 * @throws NSJPNegocioException
	 */
	 IntervinienteDTO asociarIntervinienteInvolucrado(Long idAudiencia, 
	 Long idInvolucrado) throws NSJPNegocioException;
	 
	 /**
	  * De acuerdo a los IdAudiencia y idFuncionario, 
	  * se crea un interviniente con los datos en default es decir en null
	  * @param idAudiencia
	  * @param idFuncionario
	  * @return
	  * @throws NSJPNegocioException
	  */
	 IntervinienteDTO asociarIntervinienteFuncionario(Long idAudiencia, 
	 Long idFuncionario) throws NSJPNegocioException;
	 
	 
	 /**
	  * 
	  * @param idAudiencia
	  * @return
	  * @throws NSJPNegocioException
	  */
	 List<IntervinienteDTO> consultarIntervinientesPorAudiencia (Long idAudiencia) 
	 throws NSJPNegocioException;
	 
	 /**
	  * En este servicio se va a actualizar los valores del interviniente: 
	  * esAceptado, esPresente.. etc.
	  * @return
	  */
	 IntervinienteDTO actualizarInterviniente(IntervinienteDTO interviniente) 
	 throws NSJPNegocioException;
	 

	 
	 
}
