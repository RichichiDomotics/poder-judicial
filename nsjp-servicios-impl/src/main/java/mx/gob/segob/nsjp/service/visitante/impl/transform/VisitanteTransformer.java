/**
 * 
 */
package mx.gob.segob.nsjp.service.visitante.impl.transform;

import mx.gob.segob.nsjp.dto.visitante.VisitanteDTO;
import mx.gob.segob.nsjp.model.Visitante;

/**
 * @author LuisMG
 * 
 */
public class VisitanteTransformer {

	public static Visitante transformarDTO(VisitanteDTO visitDTO) {
		Visitante visit = new Visitante();
		if (visitDTO != null) {
			visit.setcIp(visitDTO.getcIP());
			visit.setiIntentos(1);
			if (visitDTO.getcMac() != null)
				visit.setcMac(visitDTO.getcMac());
			else
				visit.setcMac("");
		}
		return visit;
	}

	public static VisitanteDTO transformar(Visitante visit) {
		VisitanteDTO visitDTO = new VisitanteDTO();
		if (visit != null) {
			visitDTO.setcIP(visit.getcIp());
			if (visit.getcMac() != null)
				visitDTO.setcMac(visit.getcMac());
			else
				visitDTO.setcMac("");
			visitDTO.setiIntentos(visit.getiIntentos());
		}
		return visitDTO;
	}
}
