/**
* Nombre del Programa : AsignarJuezAudienciaService.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24/06/2011
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
package mx.gob.segob.nsjp.service.audiencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface AsignarJuezAudienciaService {

	/**
	 * Operación que realiza la funcionalidad de asignar el juez a la audiencia.
	 * 
	 * @param audiencia
	 * @param jueces
	 * @return Devuelve un valor boleano, verdadero en caso de que se haya asignado el juez a la audiencia, 
	 * 										falso en caso contrario.
	 * @throws NSJPNegocioException
	 */
	void asignarJuezAudiencia(AudienciaDTO audiencia,
			List<FuncionarioDTO> jueces) throws NSJPNegocioException;

}
