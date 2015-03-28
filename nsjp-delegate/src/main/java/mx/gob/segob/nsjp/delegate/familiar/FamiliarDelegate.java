/**
* Nombre del Programa : FamiliarDelegate.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del delegate para los metodos de comunicacion de Familiar entre la vista y los servicios
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
package mx.gob.segob.nsjp.delegate.familiar;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;

/**
 * Contrato del delegate para los metodos de comunicacion de Familiar entre la vista y los servicios.
 * @version 1.0
 * @author rgama
 *
 */
public interface FamiliarDelegate {	
	
	/**
	 * Permite guardar/actualizar la informacion de un Familiar
	 * @param aoFamiliarDTO
	 * 	 Obligatorio <b>entrevistaComplementaria.sesionId</b>.
	 * @return FamiliarDTO
	 * @throws NSJPNegocioException
	 */
	FamiliarDTO guardarFamiliar(FamiliarDTO aoFamiliarDTO)
		throws NSJPNegocioException;
	
	
	/**
	 * Consulta Familiar por identificador
	 * @param aoFamiliarDTO
	 * 		Obligatorio <b>familiarId</b>.
	 * @return
	 * @throws NSJPNegocioException
	 */
    FamiliarDTO consultarFamiliarPorId(FamiliarDTO aoFamiliarDTO)
            throws NSJPNegocioException;
	
	/**
	 * Consulta todos los familiares asociados a una Entrevista complementaria
	 * @param aoEntrevistaComplementariaDTO
	 * 	 Obligatorio <b>entrevistaComplementaria.sesionId</b>.
	 * @return
	 * @throws NSJPNegocioException
	 */
    List<FamiliarDTO> consultarFamiliaresPorIdEntrevistaComplementaria(EntrevistaComplementariaDTO aoEntrevistaComplementariaDTO)
            throws NSJPNegocioException;
   
}
