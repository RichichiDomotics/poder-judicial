/**
 * 
 */
package mx.gob.segob.nsjp.service.prueba.impl.transformer;

import mx.gob.segob.nsjp.dto.prueba.RelacionPruebaInvolucradoDTO;
import mx.gob.segob.nsjp.model.RelacionPruebaInvolucrado;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

/**
 * @author adrian
 *
 */
public class RelacionPruebaInvolucradoTransformer {

	public static RelacionPruebaInvolucradoDTO transformarRelacionInvolucrado(
			RelacionPruebaInvolucrado scr) {
		RelacionPruebaInvolucradoDTO dto=new RelacionPruebaInvolucradoDTO();
		
		dto.setRelacionPruebaInvolucradoId(scr.getRelacionPruebaInvolucradoId());
		if(scr.getDatoPrueba()!=null)
			dto.setDatoPrueba(DatoPruebaTransformer.transformarDatoPruebaBasico(scr.getDatoPrueba()));
		if(scr.getInvolucrado()!=null)
			dto.setInvolucrado(InvolucradoTransformer.transformarInvolucradoBasico(scr.getInvolucrado()));
		
		return dto;
	}

}
