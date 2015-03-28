/**
* Nombre del Programa : RelacionTransformer.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para convertir objeto Relacion a RelacionDTO y viseversa
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
package mx.gob.segob.nsjp.service.relacion.impl.transform;

import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDTO;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.service.relacion.impl.CatRelacionTransformer;

/**
 * Clase para convertir objeto Relacion a RelacionDTO y viseversa. 
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class RelacionTransformer  {

	public static RelacionDTO transformarRelacion (Relacion relacion) {
		RelacionDTO relacionDTO = new RelacionDTO();
		
		relacionDTO.setRelacionId(relacion.getRelacionId());
		relacionDTO.setTipoRelacion(relacion.getTipoRelacion());
		relacionDTO.setElementoByComplementoId(new ElementoDTO(relacion.getElementoByComplementoId().getElementoId()));
		relacionDTO.setElementoBySujetoId(new ElementoDTO(relacion.getElementoBySujetoId().getElementoId()));
		if(relacion.getCatRelacion()!= null)
			relacionDTO.setCatRelacion(CatRelacionTransformer.transformarCatRelacion(relacion.getCatRelacion()));
		relacionDTO.setEsActivo(relacion.getEsActivo()==null?true:relacion.getEsActivo());
		return relacionDTO;
	}
	
}
