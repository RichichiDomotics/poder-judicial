/**
* Nombre del Programa : IngresarObjetoService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para ingresar un Objeto dependiendo de la instancia 
* 							de la cual fue creado el objeto.
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
package mx.gob.segob.nsjp.service.objeto;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;

/**
 * Contrato del servicio para ingresar una Lista o un Objeto en particular, 
 * dependiendo de la instancia de la cual fue creado el objeto, se invoca
 * el servicio respectivo para ingresar el objeto.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface IngresarObjetoService {

	/**
	 * Registra una lista de objetos, independiende de la instancia 
	 * de la cual fue creada.
	 * 
	 * @param objetosDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long ingresarObjetos(List<ObjetoDTO> objetosDTO) throws NSJPNegocioException;
	
	/**
	 * Servicio que permite registrar un objeto de acuerdo a
	 * la instacia que fue creado. 
	 * 
	 * @param objetoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long ingresarObjeto(ObjetoDTO objetoDTO) throws NSJPNegocioException;

	/**
	 * Ingresa un objeto que fue recibido a traves del servicio de EnviarCarpetaDeInvestigacion
	 * @param objetoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long ingresarObjetoCarpetaInvestigacion(ObjetoDTO objetoDTO)
			throws NSJPNegocioException;
	
}
