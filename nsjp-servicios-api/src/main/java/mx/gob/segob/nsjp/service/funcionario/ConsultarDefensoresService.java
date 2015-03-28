/**
* Nombre del Programa : consultarDefensoresService.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    
* Marca de cambio        : N/A
* Descripcion General    : Servicio para designar un abogado defensor
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
package mx.gob.segob.nsjp.service.funcionario;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Servicio para consultar Abogados Defensores
 * @version 1.0
 * @author rgama
 *
 */
public interface ConsultarDefensoresService {
	/**
	 * Metodo que permite consultar los defensores
	 * @return Devuelve un listado de defensores
	 * @throws NSJPNegocioException
     * @author ricardo
	 */
	public List<FuncionarioDTO> consultarDefensores() throws NSJPNegocioException;
	
	/**
     * Metodo que permite consultar los defensores de acuerdo a un tipo de Defensa
     * @param idTipoDefensa Recibe el tipo de defensa que se va a consultar.
     * @return Devuelve un listado de defensores que ejercen ese tipo de Defensa.
     * @author ricardo
     */	
	public List<FuncionarioDTO> consultarDefensorPorTipoDefensa(Long idTipoDefensa) throws NSJPNegocioException;
	
	/**
	 * Obtiene la informacion del defensor
	 * @param defensor
	 * @return DefensorDTO
	 * @throws NSJPNegocioException
	 */
	FuncionarioDTO obtenerInformacionDefensor(FuncionarioDTO defensor)throws NSJPNegocioException;
				
}
