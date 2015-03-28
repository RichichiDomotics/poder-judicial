/**
* Nombre del Programa : BiometricoTransformer.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 27/04/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Tranformer de los objetos Biometrico                       
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

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.involucrado.BiometricoDTO;
import mx.gob.segob.nsjp.model.Biometrico;

/**
 * Tranformer de los objetos Biometrico
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class BiometricoTransformer {
	public static List<BiometricoDTO> transformarBiometrico(List<Biometrico> bio){
		
		List<BiometricoDTO> biometricosDto = new ArrayList<BiometricoDTO>();
		
		for(Biometrico biometrico: bio){
			BiometricoDTO biometricoDto = new BiometricoDTO();
		
			biometricoDto.setBiometricoId(biometrico.getBiometricoId());
			biometricoDto.setRasgoBiometrico(biometrico.getRasgoBiometrico());
			biometricosDto.add(biometricoDto);
		}
		
		return biometricosDto;
		
	}
	
	public static BiometricoDTO transfomarBiometrico(Biometrico biometrico){
		BiometricoDTO biometricoDto = new BiometricoDTO();
		
		biometricoDto.setBiometricoId(biometrico.getBiometricoId());
		biometricoDto.setRasgoBiometrico(biometrico.getRasgoBiometrico());

		
		return biometricoDto;
	}
	
	public static Biometrico transfomarBiometrico(BiometricoDTO biometricoDTO){
		Biometrico biometrico = new Biometrico();
		
		if (biometricoDTO.getBiometricoId()!=null && biometricoDTO.getBiometricoId()>0)
			biometrico.setBiometricoId(biometricoDTO.getBiometricoId());
		
		biometrico.setRasgoBiometrico(biometricoDTO.getRasgoBiometrico());
		
		return biometrico;
	}

}
