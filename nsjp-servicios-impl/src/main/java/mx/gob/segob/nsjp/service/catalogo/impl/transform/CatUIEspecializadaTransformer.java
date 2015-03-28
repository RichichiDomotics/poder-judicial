/**
* Nombre del Programa : CatUIEspecializadaTransformer.java
* Autor                            : AdrianECH
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/03/2012
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
package mx.gob.segob.nsjp.service.catalogo.impl.transform;

import mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO;
import mx.gob.segob.nsjp.model.CatUIEspecializada;

/**
 * Describir el objetivo de la clase con punto al final.
 * Fecha: 06/03/2012
 * @version 1.0
 * @author AdrianECH
 * 
 */
public class CatUIEspecializadaTransformer {

	public static CatUIEspecializada transformarCatUIEspecializadaDTO(
			CatUIEspecializadaDTO dto) {
		CatUIEspecializada uie=new CatUIEspecializada();
		
		uie.setCatUIEId(dto.getCatUIEId());
		uie.setClaveUIE(dto.getClaveUIE());
		uie.setNombreUIE(dto.getNombreUIE());
		
		return uie;
	}

	public static CatUIEspecializadaDTO transformarCatUIEspecializada(
			CatUIEspecializada uie) {
		CatUIEspecializadaDTO dto=new CatUIEspecializadaDTO(uie.getCatUIEId(), uie.getClaveUIE(), uie.getNombreUIE());
		return dto;
	}


}
