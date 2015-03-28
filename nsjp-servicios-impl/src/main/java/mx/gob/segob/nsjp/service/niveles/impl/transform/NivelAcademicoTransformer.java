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
package mx.gob.segob.nsjp.service.niveles.impl.transform;

import mx.gob.segob.nsjp.dto.niveles.CatTipoNivelAcademicoDTO;
import mx.gob.segob.nsjp.dto.niveles.NivelAcademicoDTO;
import mx.gob.segob.nsjp.model.CatTipoNivelAcademico;
import mx.gob.segob.nsjp.model.NivelAcademico;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class NivelAcademicoTransformer {

	

	public static NivelAcademicoDTO transformar(NivelAcademico nivelAcademico){

		NivelAcademicoDTO nivelAcademicoDTO = new NivelAcademicoDTO();

		if(nivelAcademico != null){
			
			CatTipoNivelAcademicoDTO catTipoNivelAcademicoDTO = transformar(nivelAcademico.getCatTipoNivelAcademico());
			
			nivelAcademicoDTO.setNivelAcademicoId(nivelAcademico.getNivelAcademicoId());
			nivelAcademicoDTO.setObservaciones(nivelAcademico.getCobservaciones());
			nivelAcademicoDTO.setOtros(nivelAcademico.getCotros());
			nivelAcademicoDTO.setCatTipoNivelAcademico(catTipoNivelAcademicoDTO);
			
		}
		
		return nivelAcademicoDTO;
	}
	
	public static NivelAcademico transformar(NivelAcademicoDTO nivelAcademicoDTO){

		NivelAcademico nivelAcademico = new NivelAcademico();

		if(nivelAcademicoDTO != null){
			
			CatTipoNivelAcademico catTipoNivelAcademico = transformar(nivelAcademicoDTO.getCatTipoNivelAcademico());
			
			nivelAcademico.setNivelAcademicoId(nivelAcademicoDTO.getNivelAcademicoId());
			nivelAcademico.setCobservaciones(nivelAcademicoDTO.getObservaciones());
			nivelAcademico.setCotros(nivelAcademicoDTO.getOtros());
			nivelAcademico.setCatTipoNivelAcademico(catTipoNivelAcademico);
			
		}
		
		return nivelAcademico;
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
