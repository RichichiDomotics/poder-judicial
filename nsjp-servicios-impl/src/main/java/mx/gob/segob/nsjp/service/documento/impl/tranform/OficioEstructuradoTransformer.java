/**
 * 
 */
package mx.gob.segob.nsjp.service.documento.impl.tranform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.CuerpoOficioEstructuradoDTO;
import mx.gob.segob.nsjp.dto.documento.OficioEstructuradoDTO;
import mx.gob.segob.nsjp.model.CuerpoOficioEstructurado;
import mx.gob.segob.nsjp.model.OficioEstructurado;
import mx.gob.segob.nsjp.model.Valor;

/**
 * @author adrian
 * 
 */
public class OficioEstructuradoTransformer {

	public static OficioEstructuradoDTO transformarOficio(OficioEstructurado ofi) {
		OficioEstructuradoDTO dto = new OficioEstructuradoDTO();

		if (ofi.getOficioEstructuradoId() != null)
			dto.setOficioEstructuradoId(ofi.getOficioEstructuradoId());
		if (ofi.getEncabezado() != null)
			dto.setEncabezado(ofi.getEncabezado());
		if (ofi.getPie() != null)
			dto.setPie(ofi.getPie());
		if(ofi.getTipoOficio()!=null)
			dto.setTipoOficio(new ValorDTO(ofi.getTipoOficio().getValorId(), ofi.getTipoOficio().getValor()));
		if (ofi.getCuerposOficio() != null) {
			List<CuerpoOficioEstructuradoDTO> cuerposOficio = new ArrayList<CuerpoOficioEstructuradoDTO>();
			for (CuerpoOficioEstructurado cuerp : ofi.getCuerposOficio()) {
				cuerposOficio.add(CuerpoOficioEstructuradoTransformer.transformarCuerpoSimple(cuerp));
			}
			dto.setCuerposOficio(cuerposOficio);
		}

		return dto;
	}

	public static OficioEstructurado transformarOficioDTO(
			OficioEstructuradoDTO ofi) {
	    if (ofi==null) {
	        return null;
	    }
		OficioEstructurado ent=new OficioEstructurado();
		
		if (ofi.getOficioEstructuradoId() != null)
			ent.setOficioEstructuradoId(ofi.getOficioEstructuradoId());
		if (ofi.getEncabezado() != null)
			ent.setEncabezado(ofi.getEncabezado());
		if (ofi.getPie() != null)
			ent.setPie(ofi.getPie());
		if(ofi.getTipoOficio()!=null)
			ent.setTipoOficio(new Valor(ofi.getTipoOficio().getIdCampo()));
		if (ofi.getCuerposOficio() != null) {
			List<CuerpoOficioEstructurado> cuerposOficio = new ArrayList<CuerpoOficioEstructurado>();
			for (CuerpoOficioEstructuradoDTO cuerp : ofi.getCuerposOficio()) {
				CuerpoOficioEstructurado cuerpoTransf=CuerpoOficioEstructuradoTransformer.transformarCuerpoDTO(cuerp);
				cuerposOficio.add(cuerpoTransf);
			}
			ent.setCuerposOficio(cuerposOficio);
		}
		return ent;
	}

}
