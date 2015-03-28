/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo.impl.transform;

import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.CatDistrito;

/**
 * @author AlineGS
 *
 */
public class CatDiscriminanteTransformer {

	public static CatDiscriminanteDTO transformarCatDiscriminante(
			CatDiscriminante scr) {
		CatDistritoDTO catDistrito=new CatDistritoDTO(scr.getDistrito().getCatDistritoId(), scr.getDistrito().getClaveDistrito(), scr.getDistrito().getNombreDist());
		
		CatDiscriminanteDTO dto= new CatDiscriminanteDTO(scr.getCatDiscriminanteId(), catDistrito, scr.getClave(), scr.getNombre(), scr.getClasificacion());
		return dto;
	}

	public static CatDiscriminanteDTO transformarCatDiscriminanteSimple(
			CatDiscriminante scr) {
		CatDistritoDTO catDistrito=null;
		
		CatDiscriminanteDTO dto= new CatDiscriminanteDTO(scr.getCatDiscriminanteId(), catDistrito, scr.getClave(), scr.getNombre(), scr.getClasificacion());
		return dto;
	}

	public static CatDiscriminante transformarCatDiscriminanteDTO(
			CatDiscriminanteDTO dto) {
		CatDistrito catDistrito=null;
		if(dto.getDistrito()!=null)
			catDistrito=new CatDistrito(dto.getDistrito().getCatDistritoId());
		CatDiscriminante disc=new CatDiscriminante(dto.getCatDiscriminanteId(), catDistrito, dto.getClave(), dto.getNombre(), dto.getTipo());
		return disc;
	}

	public static CatDiscriminante transformarCatDiscriminanteUpdate(CatDiscriminante discBD,
			CatDiscriminanteDTO dto) {
		
		discBD.setClasificacion(dto.getTipo());
		discBD.setClave(dto.getClave());
		discBD.setNombre(dto.getNombre());
		
		return discBD;
	}


}
