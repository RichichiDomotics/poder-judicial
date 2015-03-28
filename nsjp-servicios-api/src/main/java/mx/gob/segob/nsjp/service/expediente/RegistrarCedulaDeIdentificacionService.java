/**
* Nombre del Programa : RegistrarCedulaDeIdentificacionService.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato para el servicio Recibir una solicitud de defensoria por un ciudadano
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
package mx.gob.segob.nsjp.service.expediente;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * Contrato para el servicio Ingresar cédula de identificación
 * Servicio que permite ingresar los siguientes datos de una persona detenida para
 *  un expediente en etapa de integración:
 *    - Datos generales
 *    - Media filiación.
 *    - Delito(s)
 *    - Hechos.
 * @version 1.0
 * @author rgama
 *
 */
public interface RegistrarCedulaDeIdentificacionService {	
	
	
	/**
	 * 
	 * @param imputado
	 * @return InvolucradoDTO
	 * @throws NSJPNegocioException
	 */
	InvolucradoDTO registrarCedula(InvolucradoDTO imputado) throws NSJPNegocioException;

}
