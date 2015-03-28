/**
* Nombre del Programa : ConsultarInformePolicialHomologadoService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/09/2011
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
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;

/**
 * Interfaz del servicio que permite la consulta del IPH completo.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface ConsultarInformePolicialHomologadoService {

	/**
	 * Servicio que permite la consulta del Informe Policial Homologado
	 * completo mediante el folioIPH.  
	 * 
	 * @param folioIPH
	 * @return
	 * @throws NSJPNegocioException
	 */
	InformePolicialHomologadoDTO consultarInformePolicialHomologadoPorFolio(Long folioIPH)throws NSJPNegocioException ;
}
