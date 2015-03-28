/**
* Nombre del Programa : EnviarInformePolicialHomologadoService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/09/2011
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
package mx.gob.segob.nsjp.service.informepolicial;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoWSDTO;

/**
 * Interfaz del servicio que permite enviar el Informe Policial Homologado
 * a otra institución mediante un WebService.
 *  
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface EnviarInformePolicialHomologadoService {


	/**
	 * Servicio que permite enciar el IPH a otra institución
	 * 
	 * @param iphWSDTO
	 * @param idAgencia Agencia que se asociara al momento de generar el numero de expediente, valor que se ligara al Expediente para poderlo filtrar por agencias 
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long enviarInformePolicialHomologado(InformePolicialHomologadoWSDTO iphWSDTO, Long idAgencia)throws NSJPNegocioException;
	
}
