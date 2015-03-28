/**
* Nombre del Programa : AsignarEvidenciaService.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20/07/2011
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
package mx.gob.segob.nsjp.service.evidencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;

/**
 * Interfaz para definir los métodos utilizados para asignar evidencias
 * y crear eslabones necesarios
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface AsignarEvidenciaService {
	/**
	 * Asigna una evidencia a los elementos relacionados a la solicitud pericial, este 
	 * método no crea cadenas de custodia ni eslabones
	 * @param evidencia Evidencia a asignar
	 * @param solicitud Solicitud inicial para la cual se debe buscar el perito
	 * donde se va a relacionar la evidencia
	 * @param perito Perito a asignar evidencias 
	 */
	void asignarEvidenciaASolicitudPericial(EvidenciaDTO evidencia,SolicitudPericialDTO solicitud,FuncionarioDTO perito) throws NSJPNegocioException;
	
	
	/**
	 * Crea una nueva solicitud pericial 
	 * Asignado las evidencias enviadas
	 * Se crean 2 SolicitudesPericiales
	 * 1 - Solicitud funcionario solicitante con estatus "EN PROCESO"
	 * 2 - Solicitud funcionario destinatario con estatus "ABIERTA"
	 * Se crean las relaciones en la tabla Documento_Elemento 
	 * @param evidencia Evidencia a asignar
	 * @param solicitud Solicitud inicial para la cual se debe buscar el perito
	 * donde se va a relacionar la evidencia
	 * @param perito Perito a asignar evidencias 
	 * @throws NSJPNegocioException 
	 * @throws Exception 
	 */
	Long asignarEvidenciaASolicitudPericial(List<EvidenciaDTO> evidencias, SolicitudPericialDTO solicitud) throws NSJPNegocioException, Exception;

}
