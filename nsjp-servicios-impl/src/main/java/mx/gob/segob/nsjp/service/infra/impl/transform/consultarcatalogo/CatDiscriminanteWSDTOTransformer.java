/**
 * 
 */
package mx.gob.segob.nsjp.service.infra.impl.transform.consultarcatalogo;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteWSDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;

/**
 * 
 * @author GustavoBP
 * @version 1.0
 */
public class CatDiscriminanteWSDTOTransformer extends GenericWSDTO {

	public static CatDiscriminanteWSDTO transformarCatDiscriminante(
			CatDiscriminanteDTO scr) {
		
		CatDiscriminanteWSDTO wsdto = new CatDiscriminanteWSDTO();
		
		if(scr == null )
			return wsdto;
		
		if(scr.getDistrito() != null && scr.getDistrito().getCatDistritoId()!= null)
			wsdto.setDistritoId(scr.getDistrito().getCatDistritoId());
		
		wsdto.setCatDiscriminanteId(scr.getCatDiscriminanteId());
		wsdto.setClave(scr.getClave());
		wsdto.setNombre(scr.getNombre());
		wsdto.setTipo(scr.getTipo());
		
		return wsdto;
	}
	

	public static CatDiscriminanteDTO transformarCatDiscriminante(
			mx.gob.segob.nsjp.ws.cliente.consultartribunalespordistrito.CatDiscriminanteWSDTO scr) {
		
		CatDiscriminanteDTO dto = new CatDiscriminanteDTO();
		
		if(scr == null )
			return dto;
		
		if(scr.getDistritoId() !=null)
			dto.setDistrito(new CatDistritoDTO(scr.getDistritoId()));
		
		dto.setCatDiscriminanteId(scr.getCatDiscriminanteId());
		dto.setClave(scr.getClave());
		dto.setNombre(scr.getNombre());
		dto.setTipo(scr.getTipo());
		
		return dto;
	}
}
