/**
* Nombre del Programa : ProgramaServiceImpl.java
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
package mx.gob.segob.nsjp.service.programa.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.niveles.CatTipoNivelAcademicoDAO;
import mx.gob.segob.nsjp.dao.programa.CatCategoriaCursoDAO;
import mx.gob.segob.nsjp.dao.programa.CatCursoDAO;
import mx.gob.segob.nsjp.dao.programa.CatProgramaDAO;
import mx.gob.segob.nsjp.dao.programa.CatTipoCursoDAO;
import mx.gob.segob.nsjp.dao.programa.CatTipoProgramaDAO;
import mx.gob.segob.nsjp.dao.programa.CatTipoTrabajoExternoDAO;
import mx.gob.segob.nsjp.dao.programa.CatTrabajoDAO;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.dto.niveles.CatTipoNivelAcademicoDTO;
import mx.gob.segob.nsjp.dto.programas.CatCategoriaCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoCursoDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoTrabajoExternoDTO;
import mx.gob.segob.nsjp.dto.programas.CatTrabajoDTO;
import mx.gob.segob.nsjp.model.CatCategoriaCurso;
import mx.gob.segob.nsjp.model.CatCurso;
import mx.gob.segob.nsjp.model.CatPrograma;
import mx.gob.segob.nsjp.model.CatTipoCurso;
import mx.gob.segob.nsjp.model.CatTipoNivelAcademico;
import mx.gob.segob.nsjp.model.CatTipoPrograma;
import mx.gob.segob.nsjp.model.CatTipoTrabajoExterno;
import mx.gob.segob.nsjp.model.CatTrabajo;
import mx.gob.segob.nsjp.model.CentroDetencion;
import mx.gob.segob.nsjp.service.detencion.transform.CentroDetencionTransformer;
import mx.gob.segob.nsjp.service.programa.ProgramaService;
import mx.gob.segob.nsjp.service.programa.impl.transform.ProgramaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */

@Service
@Transactional
public class ProgramaServiceImpl implements ProgramaService {

	public static final Logger LOGGER = Logger.getLogger(ProgramaServiceImpl.class);
	
	@Autowired
	private CatProgramaDAO programaDAO;
	
	@Autowired
	private CatTipoProgramaDAO catTipoProgramaDAO;
	
	@Autowired
	private CatTrabajoDAO catTrabajoDAO;
	
	@Autowired
	private CatTipoTrabajoExternoDAO catTipoTrabajoExternoDAO;	
	
	@Autowired
	private CatCursoDAO catCursoDAO;

	@Autowired
	private CatTipoCursoDAO catTipoCursoDAO;

	@Autowired
	private CatCategoriaCursoDAO catCategoriaCursoDAO;
	
	@Autowired
	private CatTipoNivelAcademicoDAO catTipoNivelAcademicoDAO;
	
	
	/**
	 * M&eacute;todo que consulta todos los procesos
	 * @return Lista de ProcesoDTO
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<CatProgramaDTO> consultarProgramas() throws NSJPNegocioException {
		List<CatPrograma> result = programaDAO.consultarProgramas();
		List<CatProgramaDTO> lstPrograma = new ArrayList<CatProgramaDTO>();
		for (CatPrograma catPrograma : result) {
			lstPrograma.add(ProgramaTransformer.transformar(catPrograma));
		}

		return lstPrograma;
	}
	/**
	 * M&eacute;todo que consulta todos las categor&iacute;as de un curso
	 * @return Lista de CatCurso
	 * @throws NSJPNegocioException
	 */	
	@Override
	public List<CatCursoDTO> consultarCatCurso() throws NSJPNegocioException {
		List<CatCurso> result = catCursoDAO.consultarCatCurso();
		List<CatCursoDTO> lstCatCursoDTO = new ArrayList<CatCursoDTO>();
		for (CatCurso curso : result) {
			lstCatCursoDTO.add(ProgramaTransformer.transformar(curso));
		}

		return lstCatCursoDTO;
	}
	
	/**
	 * M&eacute;todo que consulta todos los tipos de cursos
	 * @return Lista de CatTipoCurso
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<CatTipoCursoDTO> consultarCatTipoCurso() throws NSJPNegocioException {
		List<CatTipoCurso> result = catTipoCursoDAO.consultarCatTipoCurso();
		List<CatTipoCursoDTO> lstCatTipoCursoDTO = new ArrayList<CatTipoCursoDTO>();
		for (CatTipoCurso catTipoCurso : result) {
			lstCatTipoCursoDTO.add(ProgramaTransformer.transformar(catTipoCurso));
		}

		return lstCatTipoCursoDTO;
	}

	/**
	 * M&eacute;todo que consulta todos los tipos de programas
	 * @return Lista de CatTipoPrograma
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<CatTipoProgramaDTO> consultarCatTipoPrograma() throws NSJPNegocioException {
		List<CatTipoPrograma> result = catTipoProgramaDAO.consultarCatTipoPrograma();
		List<CatTipoProgramaDTO> lstCatTipoProgramaDTO = new ArrayList<CatTipoProgramaDTO>();
		for (CatTipoPrograma catTipoPrograma : result) {
			lstCatTipoProgramaDTO.add(ProgramaTransformer.transformar(catTipoPrograma));
		}
		return lstCatTipoProgramaDTO;
	}

	/**
	 * M&eacute;todo que consulta todos los tipos de trabajo externo
	 * @return Lista de CatTipoTrabajoExterno
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<CatTipoTrabajoExternoDTO> consultarCatTipoTrabajoExterno() throws NSJPNegocioException {
		List<CatTipoTrabajoExterno> result = catTipoTrabajoExternoDAO.consultarCatTipoTrabajoExterno();
		List<CatTipoTrabajoExternoDTO> lstCatTipoTrabajoExternoDTO = new ArrayList<CatTipoTrabajoExternoDTO>();
		for (CatTipoTrabajoExterno catTipoTrabajoExterno : result) {
			lstCatTipoTrabajoExternoDTO.add(ProgramaTransformer.transformar(catTipoTrabajoExterno));
		}

		return lstCatTipoTrabajoExternoDTO;
	}

	/**
	 * M&eacute;todo que consulta todos las categor&iacute;as de un curso
	 * @return Lista de CatTrabajoInterno
	 * @throws NSJPNegocioException
	 */		
	@Override
	public List<CatCategoriaCursoDTO> consultarCatCategoriaCurso() throws NSJPNegocioException {
		List<CatCategoriaCurso> result = catCategoriaCursoDAO.consultarCatCategoriasCurso();
		List<CatCategoriaCursoDTO> lstCatCategoriaCursoDTO = new ArrayList<CatCategoriaCursoDTO>();
		for (CatCategoriaCurso catCategoriaCurso : result) {
			lstCatCategoriaCursoDTO.add(ProgramaTransformer.transformar(catCategoriaCurso));
		}

		return lstCatCategoriaCursoDTO;
	}

	/**
	 * M&eacute;todo que consulta todos todos los trabajos
	 * @return Lista de CatTrabajoExterno
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<CatTrabajoDTO> consultarCatTrabajo() throws NSJPNegocioException {
		List<CatTrabajo> result = catTrabajoDAO.consultarCatTrabajo();
		List<CatTrabajoDTO> lstCatTrabajoDTO = new ArrayList<CatTrabajoDTO>();
		for (CatTrabajo catTrabajo : result) {
			lstCatTrabajoDTO.add(ProgramaTransformer.transformar(catTrabajo));
		}

		return lstCatTrabajoDTO;
	}	

	/**
	 * M&eacute;todo que inserta un trabajo.
	 * @return CatTrabajoDTO
	 * @throws NSJPNegocioException
	 */
	public CatTrabajoDTO insertarCatTrabajo(CatTrabajoDTO catTrabajoDTO)throws NSJPNegocioException{
		CatTrabajo catTrabajo = ProgramaTransformer.transformar(catTrabajoDTO);
		Long catTrabajoId = catTrabajoDAO.create(catTrabajo); 
		catTrabajoDAO.flush();
		catTrabajoDTO.setCatTrabajoId(catTrabajoId);
		return catTrabajoDTO;
	}

	/**
	 * M&eacute;todo que inserta un curso.
	 * @return CatCursoDTO
	 * @throws NSJPNegocioException
	 */
	public CatCursoDTO insertarCatCurso(CatCursoDTO catCursoDTO)throws NSJPNegocioException{
		CatCurso catCurso = ProgramaTransformer.transformar(catCursoDTO);
		Long catCursoId = catCursoDAO.create(catCurso); 
		catCursoDAO.flush();
		catCursoDTO.setCatCursoId(catCursoId);
		return catCursoDTO;		
	}
	
	/**
	 * M&eacute;todo que consulta todos los trabajos.
	 * @return CatProgramaDTO
	 * @throws NSJPNegocioException
	 */
	public CatProgramaDTO insertarCatPrograma(CatProgramaDTO catProgramaDTO)throws NSJPNegocioException{
		CatPrograma catPrograma = ProgramaTransformer.transformar(catProgramaDTO);
		Long catProgramaId = programaDAO.create(catPrograma); 
		programaDAO.flush();
		catProgramaDTO.setProgramaId(catProgramaId);
		return catProgramaDTO;
		
	}
	
	/**
	 * M&eacute;todo que consulta todos los tipos de niveles academicos.
	 * @return List<CatTipoNivelAcademicoDTO>
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<CatTipoNivelAcademicoDTO> consultarCatTipoNivelAcademico()
			throws NSJPNegocioException {
		List<CatTipoNivelAcademico> result = catTipoNivelAcademicoDAO.consultarCatTipoNivelAcademico();
		List<CatTipoNivelAcademicoDTO> lstCatTipoNivelAcademicoDTO = new ArrayList<CatTipoNivelAcademicoDTO>();
		for (CatTipoNivelAcademico catTipoNivelAcademico : result) {
			lstCatTipoNivelAcademicoDTO.add(ProgramaTransformer.transformar(catTipoNivelAcademico));
		}

		return lstCatTipoNivelAcademicoDTO;
	}	
	
	/**
	 * M&eacute;todo que consulta un programa por id
	 * @return CatProgramaDTO
	 * @throws NSJPNegocioException
	 */
	public CatProgramaDTO consultarProgramaPorId(CatProgramaDTO catProgramaDTO) throws NSJPNegocioException {	
		CatPrograma catPrograma = ProgramaTransformer.transformar(catProgramaDTO); 
		catPrograma = programaDAO.consultarProgramaPorId(catPrograma);
		if(catPrograma!=null){
			catProgramaDTO = ProgramaTransformer.transformar(catPrograma);
			return catProgramaDTO;
		}
		return null;
    }

	/**
	 * M&eacute;todo que consulta un curso por id
	 * @return CatCursoDTO
	 * @throws NSJPNegocioException
	 */
	public CatCursoDTO consultarCursoPorId(CatCursoDTO catCursoDTO) throws NSJPNegocioException {	
		CatCurso catCurso = ProgramaTransformer.transformar(catCursoDTO); 
		catCurso = catCursoDAO.consultarCatCursoPorId(catCurso);
		if(catCurso!=null){
			catCursoDTO = ProgramaTransformer.transformar(catCurso);
			return catCursoDTO;
		}
		return null;
    }	

	/**
	 * M&eacute;todo que consulta un Trabajo por id
	 * @return CatTrabajoDTO
	 * @throws NSJPNegocioException
	 */
	public CatTrabajoDTO consultarTrabajoPorId(CatTrabajoDTO catTrabajoDTO) throws NSJPNegocioException {
		CatTrabajo catTrabajo = ProgramaTransformer.transformar(catTrabajoDTO); 
		catTrabajo = catTrabajoDAO.consultarCatTrabajoPorId(catTrabajo);
		if(catTrabajo!=null){
			catTrabajoDTO = ProgramaTransformer.transformar(catTrabajo);
			return catTrabajoDTO;
		}
		return null;
    }	

	/**
	 * M&eacute;todo que actualiza un programa
	 * @throws NSJPNegocioException
	 */
	public void actualizarPrograma(CatProgramaDTO catProgramaDTO) throws NSJPNegocioException {	
		CatPrograma catPrograma = ProgramaTransformer.transformar(catProgramaDTO); 
		programaDAO.merge(catPrograma);
    }

	/**
	 * M&eacute;todo que actualiza un curso 
	 * @throws NSJPNegocioException
	 */
	public void actualizarCurso(CatCursoDTO catCursoDTO) throws NSJPNegocioException {	
		CatCurso catCurso = ProgramaTransformer.transformar(catCursoDTO); 
		catCursoDAO.update(catCurso);
    }	

	/**
	 * M&eacute;todo que actualiza un Trabajo
	 * @throws NSJPNegocioException
	 */
	public void actualizarTrabajo(CatTrabajoDTO catTrabajoDTO) throws NSJPNegocioException {
		CatTrabajo catTrabajo = ProgramaTransformer.transformar(catTrabajoDTO); 
		catTrabajoDAO.update(catTrabajo);
    }

	/**
	 * M&eacute;todo que elimina un programa
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarPrograma(CatProgramaDTO catProgramaDTO) throws NSJPNegocioException {	
		CatPrograma catPrograma = ProgramaTransformer.transformar(catProgramaDTO); 
		return programaDAO.eliminarProgramaPorId(catPrograma);
    }

	/**
	 * M&eacute;todo que elimina un curso 
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarCurso(CatCursoDTO catCursoDTO) throws NSJPNegocioException {	
		CatCurso catCurso = ProgramaTransformer.transformar(catCursoDTO); 
		return catCursoDAO.eliminarCursoPorId(catCurso);
    }	

	/**
	 * M&eacute;todo que elimina un Trabajo
	 * @throws NSJPNegocioException
	 */
	public boolean eliminarTrabajo(CatTrabajoDTO catTrabajoDTO) throws NSJPNegocioException {
		CatTrabajo catTrabajo = ProgramaTransformer.transformar(catTrabajoDTO); 
		return catTrabajoDAO.eliminarTrabajoPorId(catTrabajo);
    }
	
	/**
	 * M&eacute;todo que consulta todos los programas que se 
	 * encuentran asociados a un centro de detencion.
	 * @return Lista de CatPrograma
	 * @throws NSJPNegocioException
	 */
	public List<CatProgramaDTO> consultarProgramasPorCentroDetencion(
			CentroDetencionDTO centroDetencion) throws NSJPNegocioException {
		CentroDetencion cd = CentroDetencionTransformer.transformar(centroDetencion);
		List<CatProgramaDTO> programasDTO = new ArrayList<CatProgramaDTO>();
		List<CatPrograma> programas = programaDAO.consultarProgramasPorCentroDetencion(cd);
		for (CatPrograma cp : programas){
			programasDTO.add(ProgramaTransformer.transformar(cp));
		}
		return programasDTO;
	}
	
	/**
	 * M&eacute;todo que consulta todos los programas que se 
	 * encuentran asociados a un centro de detencion, que no 
	 * se encuentren previamente asignados y cuya fecha de 
	 * inicio sea mayor a la fecha actual.
	 * @return Lista de CatPrograma
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<CatProgramaDTO> consultarProgramasDisponibles(
			CentroDetencionDTO centroDetencion,
			List<CatProgramaDTO> programasAsignados, Date fechaActual)
			throws NSJPNegocioException {
		CentroDetencion cd = CentroDetencionTransformer.transformar(centroDetencion);
		List<CatProgramaDTO> programasDTO = new ArrayList<CatProgramaDTO>();
		List<CatPrograma> catProgramasAsignados = new ArrayList<CatPrograma>();
		if (programasAsignados != null && !programasAsignados.isEmpty()){
			for (CatProgramaDTO cp : programasAsignados){
				catProgramasAsignados.add(ProgramaTransformer.transformar(cp));
			}
		}
		List<CatPrograma> programas = programaDAO.consultarProgramasDisponibles(cd, catProgramasAsignados, fechaActual);
		for (CatPrograma cp : programas){
			programasDTO.add(ProgramaTransformer.transformar(cp));
		}
		return programasDTO;
	}
	
}