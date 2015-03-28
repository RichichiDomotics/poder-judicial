/**
* Nombre del Programa : ProgramaService.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/01/2012
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
package mx.gob.segob.nsjp.service.programa;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.dto.niveles.CatTipoNivelAcademicoDTO;
import mx.gob.segob.nsjp.dto.programas.CatCategoriaCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoTrabajoExternoDTO;
import mx.gob.segob.nsjp.dto.programas.CatTrabajoDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public interface ProgramaService {

	/**
	 * M&eacute;todo que consulta todos los programas
	 * @return Lista de ProgramaDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatProgramaDTO> consultarProgramas()throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que consulta todos las categor&iacute;as de un curso
	 * @return Lista de CatCursoDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatCursoDTO> consultarCatCurso()throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que consulta todos los tipos de cursos
	 * @return Lista de CatTipoCursoDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatTipoCursoDTO> consultarCatTipoCurso()throws NSJPNegocioException;

	/**
	 * M&eacute;todo que consulta todos los tipos de programas
	 * @return Lista de CatTipoProgramaDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatTipoProgramaDTO> consultarCatTipoPrograma()throws NSJPNegocioException;

	/**
	 * M&eacute;todo que consulta todos los tipos de trabajo externo
	 * @return Lista de CatTipoTrabajoExternoDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatTipoTrabajoExternoDTO> consultarCatTipoTrabajoExterno()throws NSJPNegocioException;

	/**
	 * M&eacute;todo que consulta todos las categor&iacute;as de un curso
	 * @return Lista de CatTrabajoInternoDTO
	 * @throws NSJPNegocioException
	 */		
	public List<CatCategoriaCursoDTO> consultarCatCategoriaCurso() throws NSJPNegocioException;

	/**
	 * M&eacute;todo que consulta todos los trabajos.
	 * @return Lista de CatTrabajoDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatTrabajoDTO> consultarCatTrabajo()throws NSJPNegocioException;

	/**
	 * M&eacute;todo que inserta un trabajo.
	 * @return CatTrabajoDTO
	 * @throws NSJPNegocioException
	 */
	public CatTrabajoDTO insertarCatTrabajo(CatTrabajoDTO catTrabajoDTO)throws NSJPNegocioException;

	/**
	 * M&eacute;todo que inserta un curso.
	 * @return CatCursoDTO
	 * @throws NSJPNegocioException
	 */
	public CatCursoDTO insertarCatCurso(CatCursoDTO catCursoDTO)throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que consulta todos los trabajos.
	 * @return CatProgramaDTO
	 * @throws NSJPNegocioException
	 */
	public CatProgramaDTO insertarCatPrograma(CatProgramaDTO catProgramaDTO)throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que consulta todos los tipos de niveles acad&eacute;micos.
	 * @return Lista de CatTipoNivelAcademicoDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatTipoNivelAcademicoDTO> consultarCatTipoNivelAcademico() throws NSJPNegocioException;
		
	/**
	 * M&eacute;todo que consulta un programa por id
	 * @throws NSJPNegocioException
	 */
	public CatProgramaDTO consultarProgramaPorId(CatProgramaDTO catProgramaDTO) throws NSJPNegocioException;	
	
	/**
	 * M&eacute;todo que consulta un curso por id
	 * @return CatCursoDTO
	 * @throws NSJPNegocioException
	 */
	public CatCursoDTO consultarCursoPorId(CatCursoDTO catCursoDTO) throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que consulta un Trabajo por id
	 * @return CatTrabajoDTO
	 * @throws NSJPNegocioException
	 */
	public CatTrabajoDTO consultarTrabajoPorId(CatTrabajoDTO catTrabajoDTO) throws NSJPNegocioException;	
	
	/**
	 * M&eacute;todo que actualiza un programa
	 * @throws NSJPNegocioException
	 */
	public void actualizarPrograma(CatProgramaDTO catProgramaDTO) throws NSJPNegocioException;

	/**
	 * M&eacute;todo que actualiza un curso 
	 * @throws NSJPNegocioException
	 */
	public void actualizarCurso(CatCursoDTO catCursoDTO) throws NSJPNegocioException;

	/**
	 * M&eacute;todo que actualiza un Trabajo
	 * @throws NSJPNegocioException
	 */
	public void actualizarTrabajo(CatTrabajoDTO catTrabajoDTO) throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que elimina un programa
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarPrograma(CatProgramaDTO catProgramaDTO) throws NSJPNegocioException;
	/**
	 * M&eacute;todo que elimina un curso 
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarCurso(CatCursoDTO catCursoDTO) throws NSJPNegocioException;
	/**
	 * M&eacute;todo que elimina un Trabajo
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarTrabajo(CatTrabajoDTO catTrabajoDTO) throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que consulta todos los programas que se 
	 * encuentran asociados a un centro de detencion.
	 * @return Lista de CatPrograma
	 * @throws NSJPNegocioException
	 */
	public List<CatProgramaDTO> consultarProgramasPorCentroDetencion(CentroDetencionDTO centroDetencion)throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que consulta todos los programas que se 
	 * encuentran asociados a un centro de detencion, que no 
	 * se encuentren previamente asignados y cuya fecha de 
	 * inicio sea mayor a la fecha actual.
	 * @return Lista de CatPrograma
	 * @throws NSJPNegocioException
	 */
	public List<CatProgramaDTO> consultarProgramasDisponibles(CentroDetencionDTO centroDetencion, List<CatProgramaDTO> programasAsignados, 
														   Date fechaActual) throws NSJPNegocioException;
	
}
