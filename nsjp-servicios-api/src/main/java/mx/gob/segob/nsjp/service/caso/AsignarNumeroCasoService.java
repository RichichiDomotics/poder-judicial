/**
* Nombre del Programa : AsignarNumeroCasoService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para el servico de generacion de un nuevo numero de caso
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
package mx.gob.segob.nsjp.service.caso;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Contrato para el servico de generacion de un nuevo numero de caso
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface AsignarNumeroCasoService {
	
	/**
	 * 
	 * @param casoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public CasoDTO asignarNumeroCaso(CasoDTO casoDTO,FuncionarioDTO funcionarioDTO) throws NSJPNegocioException;

	/**
	 * Servicio que permite consultar el prefijo del estado, donde se tiene instalada la aplicación.
	 * El valor de la institución (entidad Federativa en BD) se encuentra en la tabla parametro.
	 *  
	 * @return
	 */
	public String obtenerPrefijoDelEstado();
}
