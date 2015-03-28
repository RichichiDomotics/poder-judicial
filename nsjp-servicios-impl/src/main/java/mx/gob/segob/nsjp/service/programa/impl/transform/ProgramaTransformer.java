/**
* Nombre del Programa : ProgramaTransformer.java
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
package mx.gob.segob.nsjp.service.programa.impl.transform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class ProgramaTransformer {

	
	
	public static CatProgramaDTO transformar(CatPrograma catPrograma){

		CatProgramaDTO catProgramaDTO = new CatProgramaDTO();

		if(catPrograma != null){

			CatTipoProgramaDTO catTipoProgramaDTO = transformar(catPrograma.getCatTipoPrograma());

			List<CatCursoDTO> lstCatCursoDTO = new ArrayList<CatCursoDTO>();
			for (CatCurso catCurso : catPrograma.getCatCursos()) {
				CatCursoDTO catCursoDTO = ProgramaTransformer.transformar(catCurso);
				lstCatCursoDTO.add(catCursoDTO);
			}			
			
			List<CatTrabajoDTO> lstCatTrabajoDTO = new ArrayList<CatTrabajoDTO>();
			for (CatTrabajo catTrabajo : catPrograma.getCatTrabajos()) {
				CatTrabajoDTO catTrabajoDTO = ProgramaTransformer.transformar(catTrabajo);
				lstCatTrabajoDTO.add(catTrabajoDTO);
			}
			
			List<CentroDetencionDTO> lstCentroDetencionDTO = new ArrayList<CentroDetencionDTO>();
			for (CentroDetencion centroDetencion : catPrograma.getCentroDetencions()) {
				CentroDetencionDTO centroDetencionDTO = CentroDetencionTransformer.transformar(centroDetencion);
				lstCentroDetencionDTO.add(centroDetencionDTO);
			}
			
			catProgramaDTO.setProgramaId(catPrograma.getCatProgramaId());
			catProgramaDTO.setNombre(catPrograma.getCnombre());
			catProgramaDTO.setDescripcion(catPrograma.getCdescripcion());
			catProgramaDTO.setFechaInicioPrograma(catPrograma.getDfechaInicioPrograma());
			catProgramaDTO.setFechaFinPrograma(catPrograma.getDfechaFinPrograma());								
			catProgramaDTO.setCatTipoProgramaDTO(catTipoProgramaDTO);
			catProgramaDTO.setLstCatCursoDTO(lstCatCursoDTO);
			catProgramaDTO.setLstCatTrabajoDTO(lstCatTrabajoDTO);
			catProgramaDTO.setLstCentroDetencionesDTO(lstCentroDetencionDTO);
			catProgramaDTO.setbActivo(catPrograma.getBActivo());
			
		}
		
		return catProgramaDTO;
	}

	public static CatPrograma transformar(CatProgramaDTO catProgramaDTO){
		CatPrograma catPrograma = new CatPrograma();
		
		if(catProgramaDTO != null){
		
			catPrograma.setCatProgramaId(catProgramaDTO.getProgramaId());
			catPrograma.setCnombre(catProgramaDTO.getNombre());
			catPrograma.setCdescripcion(catProgramaDTO.getDescripcion());
			catPrograma.setDfechaInicioPrograma(catProgramaDTO.getFechaInicioPrograma());
			catPrograma.setDfechaFinPrograma(catProgramaDTO.getFechaFinPrograma());
			catPrograma.setBActivo(catProgramaDTO.getbActivo());
			
			CatTipoPrograma catTipoPrograma = transformar(catProgramaDTO.getCatTipoProgramaDTO());
			catPrograma.setCatTipoPrograma(catTipoPrograma);
			
			List<CatTrabajo> lstCatTrabajo = new ArrayList<CatTrabajo>();
			if(catProgramaDTO.getLstCatTrabajoDTO()!= null){
			for (CatTrabajoDTO catTrabajoDTO : catProgramaDTO.getLstCatTrabajoDTO()) {
				CatTrabajo catTrabajo = transformar(catTrabajoDTO);
					lstCatTrabajo.add(catTrabajo);
			}
			}
			Set<CatTrabajo> catTrabajos = new HashSet<CatTrabajo>(lstCatTrabajo);
			
			List<CentroDetencion> lstCentroDetencion = new ArrayList<CentroDetencion>();
			if(catProgramaDTO.getLstCentroDetencionesDTO()!= null){
			for (CentroDetencionDTO centroDetencionDTO : catProgramaDTO.getLstCentroDetencionesDTO()) {
				CentroDetencion centroDetencion = CentroDetencionTransformer.transformar(centroDetencionDTO);
					lstCentroDetencion.add(centroDetencion);
			}
			}
			Set<CentroDetencion> centroDetencions = new HashSet<CentroDetencion>(lstCentroDetencion);
						
			List<CatCurso> lstCatCurso = new ArrayList<CatCurso>();
			if(catProgramaDTO.getLstCatCursoDTO()!= null){
			for (CatCursoDTO catCursoDTO : catProgramaDTO.getLstCatCursoDTO()) {
				CatCurso catCurso = transformar(catCursoDTO);
					lstCatCurso.add(catCurso);
			}
			}
			Set<CatCurso> catCursos = new HashSet<CatCurso>(lstCatCurso);
			catPrograma.setCatCursos(catCursos);
			catPrograma.setCatTrabajos(catTrabajos);
			catPrograma.setCentroDetencions(centroDetencions);
		}
		return catPrograma;
	}

	public static CatTipoProgramaDTO transformar(CatTipoPrograma catTipoPrograma){

		CatTipoProgramaDTO catTipoProgramaDTO = new CatTipoProgramaDTO();

		if(catTipoPrograma != null){
			catTipoProgramaDTO.setCatTipoProgramaId(catTipoPrograma.getCatTipoProgramaId());
			catTipoProgramaDTO.setDescripcion(catTipoPrograma.getCdescripcion());
			
		}
		
		return catTipoProgramaDTO;
	}
	
	public static CatTipoPrograma transformar(CatTipoProgramaDTO catTipoProgramaDTO){
		CatTipoPrograma catTipoPrograma = new CatTipoPrograma();
		
		if(catTipoProgramaDTO != null){
		
			catTipoPrograma.setCatTipoProgramaId(catTipoProgramaDTO.getCatTipoProgramaId());
			catTipoPrograma.setCdescripcion(catTipoProgramaDTO.getDescripcion());
				
		}
		return catTipoPrograma;
	}

	
	public static CatTrabajoDTO transformar(CatTrabajo catTrabajo){

		CatTrabajoDTO catTrabajoDTO = new CatTrabajoDTO();

		if(catTrabajo != null){
			catTrabajoDTO.setCatTrabajoId(catTrabajo.getCatTrabajoId());
			catTrabajoDTO.setCnombre(catTrabajo.getCnombre());
			catTrabajoDTO.setCdescripcion(catTrabajo.getCdescripcion());
			catTrabajoDTO.setIpuntos(catTrabajo.getIpuntos());
			catTrabajoDTO.setBesExterno(catTrabajo.getBesExterno());
			catTrabajoDTO.setbActivo(catTrabajo.getBActivo());
			
			if(catTrabajoDTO.getBesExterno()!=null && catTrabajoDTO.getBesExterno()){
			catTrabajoDTO.setCatTipoTrabajoExterno(transformar(catTrabajo.getCatTipoTrabajoExterno()));
				catTrabajoDTO.setCnumeroConvenio(catTrabajo.getCnumeroConvenio());
		}
		}
		
		return catTrabajoDTO;
	}
	
	public static CatTrabajo transformar(CatTrabajoDTO catTrabajoDTO){
		CatTrabajo catTrabajo = new CatTrabajo();
		
		if(catTrabajoDTO != null){
			catTrabajo.setCatTrabajoId(catTrabajoDTO.getCatTrabajoId());
			catTrabajo.setCnombre(catTrabajoDTO.getCnombre());
			catTrabajo.setCdescripcion(catTrabajoDTO.getCdescripcion());
			catTrabajo.setBesExterno(catTrabajoDTO.getBesExterno());
			catTrabajo.setBActivo(catTrabajoDTO.getbActivo());
			if(catTrabajo.getBesExterno()!=null && catTrabajo.getBesExterno()){
			catTrabajo.setCatTipoTrabajoExterno(transformar(catTrabajoDTO.getCatTipoTrabajoExterno()));
				catTrabajo.setCnumeroConvenio(catTrabajoDTO.getCnumeroConvenio());
			}
			catTrabajo.setIpuntos(catTrabajoDTO.getIpuntos());
		}
		return catTrabajo;
	}

	public static CatTipoTrabajoExternoDTO transformar(CatTipoTrabajoExterno catTipoTrabajoExterno){

		CatTipoTrabajoExternoDTO catTipoTrabajoExternoDTO = new CatTipoTrabajoExternoDTO();

		if(catTipoTrabajoExterno != null){
			catTipoTrabajoExternoDTO.setCatTipoExternoId(catTipoTrabajoExterno.getCatTipoTrabajoExternoId());
			catTipoTrabajoExternoDTO.setDescripcion(catTipoTrabajoExterno.getCdescripcion());
		}
		
		return catTipoTrabajoExternoDTO;
	}
	
	public static CatTipoTrabajoExterno transformar(CatTipoTrabajoExternoDTO catTipoTrabajoExternoDTO){
		CatTipoTrabajoExterno catTipoTrabajoExterno = new CatTipoTrabajoExterno();
		
		if(catTipoTrabajoExternoDTO != null){
			catTipoTrabajoExterno.setCatTipoTrabajoExternoId(catTipoTrabajoExternoDTO.getCatTipoExternoId());
			catTipoTrabajoExterno.setCdescripcion(catTipoTrabajoExternoDTO.getDescripcion());
		}
		return catTipoTrabajoExterno;
	}

	public static CatCursoDTO transformar(CatCurso catCurso){

		CatCursoDTO catCursoDTO = new CatCursoDTO();
				
		if(catCurso != null){
			
			catCursoDTO.setCatCursoId(catCurso.getCatCursoId());
			catCursoDTO.setCatTipoCursoDTO(transformar(catCurso.getCatTipoCurso()));
			catCursoDTO.setCatTipoNivelAcademicoDTO(transformar(catCurso.getCatTipoNivelAcademico()));
			catCursoDTO.setCatCategoriaCursoDTO(transformar(catCurso.getCatCategoriaCurso()));
			catCursoDTO.setCdescripcion(catCurso.getCdescripcion());
			catCursoDTO.setCduracion(catCurso.getCduracion());
			catCursoDTO.setCnombre(catCurso.getCnombre());
			catCursoDTO.setIpuntos(catCurso.getIpuntos());	
			catCursoDTO.setbActivo(catCurso.getBActivo());
		}
		
		return catCursoDTO;
	}
	
	public static CatCurso transformar(CatCursoDTO catCursoDTO){
		CatCurso catCurso = new CatCurso();
		
		if(catCursoDTO != null){
						
			catCurso.setCatCursoId(catCursoDTO.getCatCursoId());
			catCurso.setCatTipoCurso(transformar(catCursoDTO.getCatTipoCursoDTO()));
			catCurso.setCatTipoNivelAcademico(transformar(catCursoDTO.getCatTipoNivelAcademicoDTO()));
			catCurso.setCatCategoriaCurso(transformar(catCursoDTO.getCatCategoriaCursoDTO()));
			catCurso.setCdescripcion(catCursoDTO.getCdescripcion());
			catCurso.setCduracion(catCursoDTO.getCduracion());
			catCurso.setCnombre(catCursoDTO.getCnombre());
			catCurso.setIpuntos(catCursoDTO.getIpuntos());	
			catCurso.setBActivo(catCursoDTO.getbActivo());
		}
		return catCurso;
	}	

	public static CatCategoriaCursoDTO transformar(CatCategoriaCurso catCategoriaCurso){

		CatCategoriaCursoDTO catCategoriaCursoDTO = new CatCategoriaCursoDTO();

		if(catCategoriaCurso != null){
			catCategoriaCursoDTO.setCatCategoriaCursoId(catCategoriaCurso.getCategoriaCursoId());
			catCategoriaCursoDTO.setDescripcion(catCategoriaCurso.getCdescripcion());
		}
		
		return catCategoriaCursoDTO;
	}
	
	public static CatCategoriaCurso transformar(CatCategoriaCursoDTO catCategoriaCursoDTO){
		CatCategoriaCurso catCategoriaCurso = new CatCategoriaCurso();
		
		if(catCategoriaCursoDTO != null){
			catCategoriaCurso.setCategoriaCursoId(catCategoriaCursoDTO.getCatCategoriaCursoId());
			catCategoriaCurso.setCdescripcion(catCategoriaCursoDTO.getDescripcion());
		}
		return catCategoriaCurso;
	}	
	
	public static CatTipoCursoDTO transformar(CatTipoCurso catTipoCurso){

		CatTipoCursoDTO catTipoCursoDTO = new CatTipoCursoDTO();

		if(catTipoCurso != null){
			catTipoCursoDTO.setCatTipoCursoId(catTipoCurso.getCatTipoCursoId());
			catTipoCursoDTO.setDescripcion(catTipoCurso.getCdescripcion());
		}
		
		return catTipoCursoDTO;
	}
	
	public static CatTipoCurso transformar(CatTipoCursoDTO catTipoCursoDTO){
		CatTipoCurso catTipoCurso  = new CatTipoCurso();
		
		if(catTipoCursoDTO != null){
			catTipoCurso.setCatTipoCursoId(catTipoCursoDTO.getCatTipoCursoId());
			catTipoCurso.setCdescripcion(catTipoCursoDTO.getDescripcion());
		}
		return catTipoCurso;
	}	

	public static CatTipoNivelAcademicoDTO transformar(CatTipoNivelAcademico catTipoNivelAcademico){

		CatTipoNivelAcademicoDTO catTipoNivelAcademicoDTO = new CatTipoNivelAcademicoDTO();

		if(catTipoNivelAcademico != null){
			catTipoNivelAcademicoDTO.setCatTipoNivelAcademicoId(catTipoNivelAcademico.getCatTipoNivelAcademicoId());
			catTipoNivelAcademicoDTO.setDescripcion(catTipoNivelAcademico.getCdescripcion());
		}
		
		return catTipoNivelAcademicoDTO;
	}
	
	public static CatTipoNivelAcademico transformar(CatTipoNivelAcademicoDTO catTipoNivelAcademicoDTO){

		CatTipoNivelAcademico catTipoNivelAcademico = new CatTipoNivelAcademico();

		if(catTipoNivelAcademicoDTO != null){
			catTipoNivelAcademico.setCatTipoNivelAcademicoId(catTipoNivelAcademicoDTO.getCatTipoNivelAcademicoId());
			catTipoNivelAcademico.setCdescripcion(catTipoNivelAcademicoDTO.getDescripcion());
		}
		
		return catTipoNivelAcademico;
	}	
	
}
