/**
* Nombre del Programa : PerfilTransformer.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 29/04/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Integración xxxxxxxxxxx                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.service.involucrado.impl.transform;

import mx.gob.segob.nsjp.dto.involucrado.PerfilDTO;
import mx.gob.segob.nsjp.model.Perfil;

/**
 * Describir el objetivo de la clase
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class PerfilTransformer {
	public static PerfilDTO transformarPerfil(Perfil perfil){
		PerfilDTO perfilDto = new PerfilDTO();
		
		perfilDto.setAdicciones(perfil.getAdicciones());
		perfilDto.setAnsiedades(perfil.getAnsiedades());
		perfilDto.setDepresiones(perfil.getDepresiones());
		perfilDto.setFilias(perfil.getFilias());
		perfilDto.setFobias(perfil.getFobias());
		perfilDto.setParanoias(perfil.getParanoias());
		perfilDto.setPerfilId(perfil.getPerfilId());
		perfilDto.setPerfilPsicologico(perfil.getPerfilPsicologico());
		perfilDto.setPreferenciaSexual(perfil.getPreferenciaSexual());
		
		return perfilDto;
	}
	
	public static Perfil transformarPerfil(PerfilDTO perfilDTO){
		Perfil perfil = new Perfil();
		
		if (perfilDTO.getPerfilId()!=null && perfilDTO.getPerfilId()>0)
			perfil.setPerfilId(perfilDTO.getPerfilId());
		
		perfil.setAdicciones(perfilDTO.getAdicciones());
		perfil.setAnsiedades(perfilDTO.getAnsiedades());
		perfil.setDepresiones(perfilDTO.getDepresiones());
		perfil.setFilias(perfilDTO.getFilias());
		perfil.setFobias(perfilDTO.getFobias());
		perfil.setParanoias(perfilDTO.getParanoias());		
		perfil.setPerfilPsicologico(perfilDTO.getPerfilPsicologico());
		perfil.setPreferenciaSexual(perfilDTO.getPreferenciaSexual());
		
		return perfil;
	}

}
