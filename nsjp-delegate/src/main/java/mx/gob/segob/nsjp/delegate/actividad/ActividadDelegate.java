/**
 * Nombre del Programa : ActividadDelegate.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21/07/2011
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
package mx.gob.segob.nsjp.delegate.actividad;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface ActividadDelegate {
	/**
	 * Registra una actividad según el tipo para un expediente dado
	 * @param expedienteDTO: idExpediente
	 * @param funcionarioDTO: idFuncionario
	 * @param tipoActividad: idTipoActividad
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long registrarActividad (ExpedienteDTO expedienteDTO, FuncionarioDTO funcionarioDTO,Long tipoActividad) throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de elimninar la asociación de un archivo digital a un expediente.
	 * @param expedienteDTO: numeroExpediente y área
	 * @param documentoDTO: idDocumento
	 * @throws NSJPNegocioException
	 */
	void eliminarAsocArchivoAExpediente(ExpedienteDTO expedienteDTO, DocumentoDTO documentoDTO)throws NSJPNegocioException;
	
	/**
	 * Permite consultar las Actividades asociadas a un conjunto de CatDelitos
	 * @param idsCatDelito: Lista de los identificadores de CatDelito
	 * @return List<ValorDTO>
	 * @throws NSJPNegocioException
	 */
	List<ValorDTO> consultarActividadesPorIdsCatDelito(List<Long> idsCatDelito) throws NSJPNegocioException;

	/**
	 * Consulta todas las actividades asociadas al expediente (opcional) y de acuerdo al tipo de Actividad (opcional),
	 * se considera si se considera el documento generado o solo la actividad generada. 
	 * 
	 * @param idExpediente
	 * @param idTipoActividades
	 * @param documentoRec
     * @return
     * @throws NSJPNegocioException
     */
    List<ActividadDTO> consultarActividadesPorTipoActividadExpedienteId (Long idExpediente, List<Long> idTipoActividades, Boolean documentoRec)throws NSJPNegocioException;
    
    /**
     * Consulta el numero de actividades relacionadas al numero de expediente y a al tipo de atencion que recibe
     * @param expedienteId
     * @param tipoAtencion
     * @return
     * @throws NSJPNegocioException
     */
    Long consultarNumeroActividadesPorTipoAtencionExpedienteId(Long expedienteId,Long tipoAtencion) throws NSJPNegocioException;

	/**
	 * Obtiene el expediente deacuerdo al id del Expediente 
	 * @param expedienteId
	 * @param atenderCanalizacionJar
	 */
    ActividadDTO obtenerActPorExpTipoAct(Long expedienteId,	Actividades actividad) throws NSJPNegocioException;    
    
   
}
