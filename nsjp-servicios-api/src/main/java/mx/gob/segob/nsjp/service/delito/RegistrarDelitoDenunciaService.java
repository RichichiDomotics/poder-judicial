/**
* Nombre del Programa : RegistrarDelitoDenunciaService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para registrar los delitos de la denuncia
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
package mx.gob.segob.nsjp.service.delito;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;

/**
 * Contrato del servicio para registrar los delitos de la denuncia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface RegistrarDelitoDenunciaService {
	
	/**
	 * Inserta los delitos de la denuncia
	 * @param delitosDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<DelitoDTO> registrarDelitoDenuncia(List<DelitoDTO> delitosDTO) throws NSJPNegocioException;

}
