/**
 * Nombre del Programa : ProcesoDelegateImpl.java
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
package mx.gob.segob.nsjp.delegate.programa.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.programa.ProgramaDelegate;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.dto.niveles.CatTipoNivelAcademicoDTO;
import mx.gob.segob.nsjp.dto.programas.CatCategoriaCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoTrabajoExternoDTO;
import mx.gob.segob.nsjp.dto.programas.CatTrabajoDTO;
import mx.gob.segob.nsjp.service.programa.ProgramaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author AntonioBV
 * 
 */
@Service
@Transactional
public class ProgramaDelegateImpl implements ProgramaDelegate {

	@Autowired
	ProgramaService programaService;

	/**
	 * M&eacute;todo que consulta todos los programas.
	 * 
	 * @return Lista de ProgramaDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatProgramaDTO> consultarProgramas() throws NSJPNegocioException {
		return programaService.consultarProgramas();
	}

	/**
	 * M&eacute;todo que consulta todos las categor&iacute;as de un curso.
	 * 
	 * @return Lista de CatCursoDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatCursoDTO> consultarCatCurso() throws NSJPNegocioException {
		return programaService.consultarCatCurso();
	}

	/**
	 * M&eacute;todo que consulta todos los tipos de cursos.
	 * 
	 * @return Lista de CatTipoCursoDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatTipoCursoDTO> consultarCatTipoCurso()
			throws NSJPNegocioException {
		return programaService.consultarCatTipoCurso();
	}

	/**
	 * M&eacute;todo que consulta todos los tipos de programas.
	 * 
	 * @return Lista de CatTipoProgramaDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatTipoProgramaDTO> consultarCatTipoPrograma()
			throws NSJPNegocioException {
		return programaService.consultarCatTipoPrograma();
	}

	/**
	 * M&eacute;todo que consulta todos los tipos de trabajo externo.
	 * 
	 * @return Lista de CatTipoTrabajoExternoDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatTipoTrabajoExternoDTO> consultarCatTipoTrabajoExterno()
			throws NSJPNegocioException {
		return programaService.consultarCatTipoTrabajoExterno();
	}

	/**
	 * M&eacute;todo que consulta todos todos los trabajos.
	 * 
	 * @return Lista de CatTrabajoDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatTrabajoDTO> consultarCatTrabajo()
			throws NSJPNegocioException {
		return programaService.consultarCatTrabajo();
	}

	/**
	 * M&eacute;todo que consulta todos las categor&iacute;as de un curso.
	 * 
	 * @return Lista de CatTrabajoInternoDTO
	 * @throws NSJPNegocioException
	 */
	public List<CatCategoriaCursoDTO> consultarCatCategoriaCurso()
			throws NSJPNegocioException {
		return programaService.consultarCatCategoriaCurso();
	}

	/**
	 * M&eacute;todo que inserta un trabajo.
	 * @return CatTrabajoDTO
	 * @throws NSJPNegocioException
	 */
	public CatTrabajoDTO insertarCatTrabajo(CatTrabajoDTO catTrabajoDTO)throws NSJPNegocioException{
		return programaService.insertarCatTrabajo(catTrabajoDTO);
	}

	/**
	 * M&eacute;todo que inserta un curso.
	 * @return CatCursoDTO
	 * @throws NSJPNegocioException
	 */
	public CatCursoDTO insertarCatCurso(CatCursoDTO catCursoDTO)throws NSJPNegocioException{
		return programaService.insertarCatCurso(catCursoDTO);
	}
	
	/**
	 * M&eacute;todo que consulta todos los trabajos.
	 * @return CatProgramaDTO
	 * @throws NSJPNegocioException
	 */
	public CatProgramaDTO insertarCatPrograma(CatProgramaDTO catProgramaDTO)throws NSJPNegocioException{
		return programaService.insertarCatPrograma(catProgramaDTO);
	}

	/**
	 * M&eacute;todo que consulta todos los tipos de niveles acad&eacute;micos.
	 * @return Lista de CatTipoNivelAcademicoDTO
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<CatTipoNivelAcademicoDTO> consultarCatTipoNivelAcademico()
			throws NSJPNegocioException {
		return programaService.consultarCatTipoNivelAcademico();
	}
		
	/**
	 * M&eacute;todo que consulta un programa por id
	 * @return CatProgramaDTO
	 * @throws NSJPNegocioException
	 */
	public CatProgramaDTO consultarProgramaPorId(CatProgramaDTO catProgramaDTO) throws NSJPNegocioException {
		return programaService.consultarProgramaPorId(catProgramaDTO);
	}	
	
	/**
	 * M&eacute;todo que consulta un curso por id
	 * @return CatCursoDTO
	 * @throws NSJPNegocioException
	 */
	public CatCursoDTO consultarCursoPorId(CatCursoDTO catCursoDTO) throws NSJPNegocioException {
		return programaService.consultarCursoPorId(catCursoDTO);
	}	
	
	/**
	 * M&eacute;todo que consulta un Trabajo por id
	 * @return CatTrabajoDTO
	 * @throws NSJPNegocioException
	 */
	public CatTrabajoDTO consultarTrabajoPorId(CatTrabajoDTO catTrabajoDTO) throws NSJPNegocioException {
		return programaService.consultarTrabajoPorId(catTrabajoDTO);
		
}

	/**
	 * M&eacute;todo que actualiza un programa
	 * @throws NSJPNegocioException
	 */
	public void actualizarPrograma(CatProgramaDTO catProgramaDTO) throws NSJPNegocioException {
		programaService.actualizarPrograma(catProgramaDTO);
	}	

	/**
	 * M&eacute;todo que actualiza un curso 
	 * @throws NSJPNegocioException
	 */
	public void actualizarCurso(CatCursoDTO catCursoDTO) throws NSJPNegocioException {
		programaService.actualizarCurso(catCursoDTO);
	}	

	/**
	 * M&eacute;todo que actualiza un Trabajo
	 * @throws NSJPNegocioException
	 */
	public void actualizarTrabajo(CatTrabajoDTO catTrabajoDTO) throws NSJPNegocioException {
		programaService.actualizarTrabajo(catTrabajoDTO);
		
	}	

	/**
	 * M&eacute;todo que elimina un programa
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarPrograma(CatProgramaDTO catProgramaDTO) throws NSJPNegocioException {	
		return programaService.eliminarPrograma(catProgramaDTO);
	}

	/**
	 * M&eacute;todo que elimina un curso 
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarCurso(CatCursoDTO catCursoDTO) throws NSJPNegocioException {	
		return programaService.eliminarCurso(catCursoDTO);
	}	

	/**
	 * M&eacute;todo que elimina un Trabajo
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarTrabajo(CatTrabajoDTO catTrabajoDTO) throws NSJPNegocioException {
		return programaService.eliminarTrabajo(catTrabajoDTO);
	}

	/**
	 * M&eacute;todo que consulta todos los programas que se 
	 * encuentran asociados a un centro de detencion.
	 * @return Lista de CatPrograma
	 * @throws NSJPNegocioException
	 */
	public List<CatProgramaDTO> consultarProgramasPorCentroDetencion(
			CentroDetencionDTO centroDetencion) throws NSJPNegocioException {
		return programaService.consultarProgramasPorCentroDetencion(centroDetencion);
	}

	/**
	 * M&eacute;todo que consulta todos los programas que se 
	 * encuentran asociados a un centro de detencion, que no 
	 * se encuentren previamente asignados y cuya fecha de 
	 * inicio sea mayor a la fecha actual.
	 * @return Lista de CatProgramaDTO
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<CatProgramaDTO> consultarProgramasDisponibles(
			CentroDetencionDTO centroDetencion,
			List<CatProgramaDTO> programasAsignados, Date fechaActual)
			throws NSJPNegocioException {
		return programaService.consultarProgramasDisponibles(centroDetencion, programasAsignados, fechaActual);
	}	
	
}
