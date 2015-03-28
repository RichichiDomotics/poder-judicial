/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo.impl.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.catalogo.TipoDiscriminante;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.CatDistrito;

/**
 * @author AlineGS
 *
 */
public class CatDistritoTransformer {

	public static CatDistrito transformarDistritoDTO(CatDistritoDTO dto) {
		CatDistrito dist=new CatDistrito(dto.getCatDistritoId(), dto.getClaveDistrito(), dto.getNombreDist());
		return dist;
	}

	/**
	 * Servicio que transforma sólo el distrito
	 * @param src
	 * @return
	 */
	public static CatDistritoDTO transformarDistritoSimple(CatDistrito src) {
		CatDistritoDTO dto=new CatDistritoDTO(src.getCatDistritoId(), src.getClaveDistrito(), src.getNombreDist());
		return dto;
	}

	/**
	 * Servicio que transforma tanto el distrito como la lista de sus discriminantes
	 * @param src
	 * @return
	 */
	public static CatDistritoDTO transformarDistritoCompleto(
			CatDistrito src) {
		CatDistritoDTO dto=transformarDistritoSimple(src);
		
		if(src.getDiscriminantes()!=null){
			List<CatDiscriminanteDTO> discriminantesDTO=new ArrayList<CatDiscriminanteDTO>();
			Set<CatDiscriminante> dicriminantes = src.getDiscriminantes();
			for (CatDiscriminante discr : dicriminantes) {
				discriminantesDTO.add(CatDiscriminanteTransformer.transformarCatDiscriminanteSimple(discr));
			}
			dto.setDiscriminantes(discriminantesDTO);
		}
		return dto;
	}

	public static CatDiscriminante transformarDistritoDTOFantasma(
			CatDistritoDTO dto, Long idDistrito) {
		CatDistrito catDistrito = new CatDistrito(idDistrito);
		CatDiscriminante disc=new CatDiscriminante(dto.getCatDistritoId(), catDistrito, dto.getClaveDistrito(), dto.getNombreDist(), (short) TipoDiscriminante.FANTASMA.ordinal());
		return disc;
	}

}
