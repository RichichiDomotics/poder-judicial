/**
* Nombre del Programa : RegistrarAvisoDetencionDeAreaExterna.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01/07/2011
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
package mx.gob.segob.nsjp.service.avisodetencion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionWSDTO;

/**
 * Interfaz de servicio de negocio para recibir un aviso de detención de otra
 * institución
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface RegistrarAvisoDetencionDeAreaExterna {

	/**
	 * Recibe un aviso de detención de una institución externa con los datos
	 * básicos para el registro en la base de datos local
	 * 
	 * @param aviso Aviso de detención a registrar
	 * @param idAgencia    Representa el id de Fiscalia de donde proviene la solicitud
	 * @param claveAgencia Representa la clave de Fiscalia de donde proviene la solicitud
	 * @return DTO de entrada con campos extra llenados
	 */
	AvisoDetencionWSDTO registrarAvisoDetencion(AvisoDetencionWSDTO aviso,Long idAgencia, String claveAgencia,Long idFuncionarioSolicitante) throws NSJPNegocioException;
	
}
