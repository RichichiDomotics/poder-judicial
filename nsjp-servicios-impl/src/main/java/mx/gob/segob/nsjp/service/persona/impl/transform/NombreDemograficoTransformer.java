/**
* Nombre del Programa : NombreDemograficoTransformer.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para convertir objetos NombreDemografico a NombreDemograficoDTO y viceversa
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
package mx.gob.segob.nsjp.service.persona.impl.transform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.model.EntidadFederativa;
import mx.gob.segob.nsjp.model.Municipio;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Clase para convertir objetos NombreDemografico a NombreDemograficoDTO y viceversa.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class NombreDemograficoTransformer {

	/**
	 * Convierte una lista de NombreDemografico, en una lista de NombreDemograficoDTO
	 * @param nombres
	 * @return
	 */
	public static List<NombreDemograficoDTO> transformarNombreDemografico(List<NombreDemografico> nombres) {
		
		List<NombreDemograficoDTO> nombresDTO = new ArrayList<NombreDemograficoDTO>();
		
		for (NombreDemografico nombre : nombres) {
			NombreDemograficoDTO nombreDemograficoDTO = new NombreDemograficoDTO();
			
			nombreDemograficoDTO.setNombre(nombre.getNombre());
			nombreDemograficoDTO.setApellidoPaterno(nombre.getApellidoPaterno());
			nombreDemograficoDTO.setApellidoMaterno(nombre.getApellidoMaterno());
			nombreDemograficoDTO.setCurp(nombre.getCurp());
			nombreDemograficoDTO.setRfc(nombre.getRfc());
			nombreDemograficoDTO.setFechaNacimiento(  nombre.getFechaNacimiento());
			nombreDemograficoDTO.setStrFechaNacimiento( DateUtils.formatear(nombre.getFechaNacimiento()));
			nombreDemograficoDTO.setLugarNacimiento(nombre.getLugarNacimiento());
			nombreDemograficoDTO.setEdadAproximada(nombre.getEdadAproximada());
			nombreDemograficoDTO.setEsVerdadero(nombre.getEsVerdadero());
			nombreDemograficoDTO.setSexo(nombre.getSexo());
			
			if (nombre.getEntidadFederativaNacimiento()!=null) {
				EntidadFederativaDTO entFederativaDTO = new EntidadFederativaDTO();
				entFederativaDTO.setEntidadFederativaId(nombre.getEntidadFederativaNacimiento().getEntidadFederativaId());
				entFederativaDTO.setNombreEntidad(nombre.getEntidadFederativaNacimiento().getNombre());
				nombreDemograficoDTO.setEntidadFederativaDTO(entFederativaDTO);
			}
			
			if (nombre.getMunicipioNacimiento()!=null) {
				MunicipioDTO municipioDTO = new MunicipioDTO();
				municipioDTO.setMunicipioId(nombre.getMunicipioNacimiento().getMunicipioId());
				municipioDTO.setNombreMunicipio(nombre.getMunicipioNacimiento().getNombreMunicipio());
				nombreDemograficoDTO.setMunicipioDTO(municipioDTO);
			}
			
			if (nombre.getPaisNacimiento()!=null) 
				nombreDemograficoDTO.setPaisValorDTO(new ValorDTO(nombre.getPaisNacimiento().getValorId(),
														nombre.getPaisNacimiento().getValor()));
						
			nombresDTO.add(nombreDemograficoDTO);
		}
		
		return nombresDTO;
	}
	

    /**
     * Convierte una lista de NombreDemografico, en una lista de NombreDemograficoDTO
     * @param nombres
     * @return
     */
    public static List<NombreDemograficoDTO> transformarNombreDemograficoBasico(List<NombreDemografico> nombres) {
        
        List<NombreDemograficoDTO> nombresDTO = new ArrayList<NombreDemograficoDTO>();
        
        for (NombreDemografico nombre : nombres) {
            NombreDemograficoDTO nombreDemograficoDTO = new NombreDemograficoDTO();
            
            nombreDemograficoDTO.setNombre(nombre.getNombre());
            nombreDemograficoDTO.setApellidoPaterno(nombre.getApellidoPaterno());
            nombreDemograficoDTO.setApellidoMaterno(nombre.getApellidoMaterno());
            nombreDemograficoDTO.setCurp(nombre.getCurp());
            nombreDemograficoDTO.setRfc(nombre.getRfc());
            nombreDemograficoDTO.setFechaNacimiento(  nombre.getFechaNacimiento());
            nombreDemograficoDTO.setStrFechaNacimiento( DateUtils.formatear(nombre.getFechaNacimiento()));
            nombreDemograficoDTO.setEdadAproximada(nombre.getEdadAproximada());
            nombreDemograficoDTO.setEsVerdadero(nombre.getEsVerdadero());
            nombreDemograficoDTO.setSexo(nombre.getSexo());
            nombresDTO.add(nombreDemograficoDTO);
        }
        
        return nombresDTO;
    }
    
	
	/**
	 * Transforma un objeto NombreDemograficoDTO a una NombreDemografico,  
	 * 
	 * @param nomDemograficoDTO 
	 * @return nombreDemografico 
	 */
	public static NombreDemografico transformarNombreDemografico(NombreDemograficoDTO nomDemograficoDTO) {
		NombreDemografico nombreDemografico = NombreDemograficoTransformer.transformarNombreDemograficoUpdate(null, nomDemograficoDTO);
		return nombreDemografico;
	}
	
	/**
	 * Transforma un objeto NombreDemograficoDTO a un NombreDemografico,  
	 * En caso de que el parámetro nomDemografico sea null, es una
	 * transformacion normal.
	 * Si es diferente de null, se trata de un objeto de fue 
	 * extraido de BD y contiene referencias a otras entidades. El 
	 * objetivo es setear los valores que viene de la interfaz 
	 * (presentación) y ser actualizado en BD. 
	 * 
	 * @param nomDemografico  objeto a actualizar. 
	 * @param nomDemograficoDTO 
	 * @return nomDemografico 
	 */
	public static NombreDemografico transformarNombreDemograficoUpdate(
			NombreDemografico nomDemografico, NombreDemograficoDTO nomDemograficoDTO) {
		
		if( nomDemografico == null )
			nomDemografico = new NombreDemografico();
		
		if (nomDemograficoDTO.getNombreDemograficoId()!=null && nomDemograficoDTO.getNombreDemograficoId()>0)
			nomDemografico.setNombreDemograficoId(nomDemograficoDTO.getNombreDemograficoId());
		
		nomDemografico.setNombre(nomDemograficoDTO.getNombre());
		nomDemografico.setApellidoPaterno(nomDemograficoDTO.getApellidoPaterno());
		nomDemografico.setApellidoMaterno(nomDemograficoDTO.getApellidoMaterno());
		nomDemografico.setCurp(nomDemograficoDTO.getCurp());
		nomDemografico.setRfc(nomDemograficoDTO.getRfc());
		nomDemografico.setFechaNacimiento(nomDemograficoDTO.getFechaNacimiento());
		nomDemografico.setLugarNacimiento(nomDemograficoDTO.getLugarNacimiento());
		nomDemografico.setEdadAproximada(nomDemograficoDTO.getEdadAproximada());
		nomDemografico.setEsVerdadero(nomDemograficoDTO.getEsVerdadero());
		nomDemografico.setSexo(nomDemograficoDTO.getSexo());
		
		if (nomDemograficoDTO.getPaisValorDTO()!=null)
			nomDemografico.setPaisNacimiento(new Valor(nomDemograficoDTO.getPaisValorDTO().getIdCampo()));
		if (nomDemograficoDTO.getMunicipioDTO()!=null)
			nomDemografico.setMunicipioNacimiento(new Municipio(nomDemograficoDTO.getMunicipioDTO().getMunicipioId()));
		if (nomDemograficoDTO.getEntidadFederativaDTO()!=null)
			nomDemografico.setEntidadFederativaNacimiento(new EntidadFederativa(nomDemograficoDTO.getEntidadFederativaDTO().getEntidadFederativaId()));
		
		return nomDemografico;
	}
	
	/**
	 * 
	 * @param nomDemograficoDTO
	 * @return
	 */
	public static NombreDemograficoDTO transformarNombreDemografico(NombreDemografico nomDemografico) {
		NombreDemograficoDTO nomDemograficoDTO = new NombreDemograficoDTO();
		
		if (nomDemografico.getNombreDemograficoId()!=null)
			nomDemograficoDTO.setNombreDemograficoId(nomDemografico.getNombreDemograficoId());
		
		nomDemograficoDTO.setNombre(nomDemografico.getNombre());
		nomDemograficoDTO.setApellidoPaterno(nomDemografico.getApellidoPaterno());
		nomDemograficoDTO.setApellidoMaterno(nomDemografico.getApellidoMaterno());
		nomDemograficoDTO.setCurp(nomDemografico.getCurp());
		nomDemograficoDTO.setRfc(nomDemografico.getRfc());
		nomDemograficoDTO.setFechaNacimiento(nomDemografico.getFechaNacimiento());
		nomDemograficoDTO.setLugarNacimiento(nomDemografico.getLugarNacimiento());
		nomDemograficoDTO.setEdadAproximada(nomDemografico.getEdadAproximada());
		nomDemograficoDTO.setEsVerdadero(nomDemografico.getEsVerdadero());
		nomDemograficoDTO.setSexo(nomDemografico.getSexo());
		
		if (nomDemografico.getPaisNacimiento()!=null)
			nomDemograficoDTO.setPaisValorDTO(new ValorDTO(nomDemografico.getPaisNacimiento().getValorId()));
		if (nomDemografico.getMunicipioNacimiento()!=null)
			nomDemograficoDTO.setMunicipioDTO(new MunicipioDTO(nomDemografico.getMunicipioNacimiento().getMunicipioId()));
		if (nomDemografico.getEntidadFederativaNacimiento()!=null)
			nomDemograficoDTO.setEntidadFederativaDTO(new EntidadFederativaDTO(nomDemografico.getEntidadFederativaNacimiento().getEntidadFederativaId()));
		
		return nomDemograficoDTO;
	}
}
