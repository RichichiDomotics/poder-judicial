/**
* Nombre del Programa : IngresarIndividuoMenorService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de servicio para realizar el registro de un involucrado menor de edad y su tutor
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
package mx.gob.segob.nsjp.service.involucrado;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * Contrato de servicio para realizar el registro de un involucrado menor de edad,
 * y su tutor.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface IngresarIndividuoMenorService {

	/**
	 * 
	 * @param involucradoDTO
	 * @param tutorDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<InvolucradoDTO> ingresarIndividuoMenor(InvolucradoDTO involucradoDTO, 
						InvolucradoDTO tutorDTO) throws NSJPNegocioException;
}
