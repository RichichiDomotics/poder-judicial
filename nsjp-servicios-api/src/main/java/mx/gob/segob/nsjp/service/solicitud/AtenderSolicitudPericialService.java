/**
* Nombre del Programa : AtenderSolicitudPericialService.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25/07/2011
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
package mx.gob.segob.nsjp.service.solicitud;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;

/**
 * Interfaz del servicio de negocio para darle atención y progreso a una solicitud
 * de servicios periciales, generar dictámenes o informes y adjuntar notas a documentos
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface AtenderSolicitudPericialService {

	/**
	 * Crea un nuevo dictamen o informe que será asociado a una solicitud de pericial
	 * @param dictamen Datos de origen para el dictamen o informe y la solicitud de pericial
	 * asociada
	 * @return Objeto de dictamen origen con el ID del dictamen asociado
	 */
	DictamenDTO registrarDictamenParaSolicitudPericial(DictamenDTO dictamen) throws NSJPNegocioException;
	
}
