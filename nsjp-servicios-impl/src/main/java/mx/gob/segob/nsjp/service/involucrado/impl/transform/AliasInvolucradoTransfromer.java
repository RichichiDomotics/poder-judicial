/**
* Nombre del Programa : AliasInvolucradoTransfromer.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26 Apr 2011
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
package mx.gob.segob.nsjp.service.involucrado.impl.transform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.model.AliasInvolucrado;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class AliasInvolucradoTransfromer {

	public static List<AliasInvolucradoDTO> transformarAlias(List<AliasInvolucrado> alias) {
		List<AliasInvolucradoDTO> aliasDTO = new ArrayList<AliasInvolucradoDTO>();
		
		for (AliasInvolucrado aliasInvolucrado : alias) {
			AliasInvolucradoDTO aliasInvolucradoDTO = new AliasInvolucradoDTO();
			
			aliasInvolucradoDTO.setAlias(aliasInvolucrado.getAlias());
			aliasInvolucradoDTO.setAliasInvolucradoId(aliasInvolucrado.getAliasInvolucradoId());
			aliasDTO.add(aliasInvolucradoDTO);
		}
		
		return aliasDTO;
	}
	
	public static AliasInvolucradoDTO trasnformarAliasInv(AliasInvolucrado aliasinv){
		AliasInvolucradoDTO aliasinvDto = new AliasInvolucradoDTO();
		
		aliasinvDto.setAlias(aliasinv.getAlias());
		aliasinvDto.setAliasInvolucradoId(aliasinv.getAliasInvolucradoId());

		
		return aliasinvDto;
	}
	
	public static AliasInvolucrado transformarAliasInv (AliasInvolucradoDTO aliasInvDTO) {
		AliasInvolucrado aliasInv = new AliasInvolucrado();
		
		if (aliasInvDTO.getAliasInvolucradoId()!=null && aliasInvDTO.getAliasInvolucradoId()>0)		
			aliasInv.setAliasInvolucradoId(aliasInvDTO.getAliasInvolucradoId());
					
		aliasInv.setAlias(aliasInvDTO.getAlias());
		
		return aliasInv;
	}
}
