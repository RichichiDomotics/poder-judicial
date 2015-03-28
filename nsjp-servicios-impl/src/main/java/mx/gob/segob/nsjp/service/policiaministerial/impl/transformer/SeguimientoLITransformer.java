/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial.impl.transformer;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.LineaInvestigacion;
import mx.gob.segob.nsjp.model.SeguimientoLI;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

/**
 * @author adrian
 *
 */
public class SeguimientoLITransformer {

	public static SeguimientoLI transformarSeguimientoDTO(
			SeguimientoLIDTO dto) {
		Expediente loExpediente=null;
		if (dto.getExpedienteDTO() != null)
			loExpediente = new Expediente(dto.getExpedienteDTO().getExpedienteId());
		
		return new SeguimientoLI(dto.getSeguimientoLIId(), dto.getFechaCreacion(), dto.getHipotesis(), loExpediente);
	}

	public static SeguimientoLIDTO transformarSeguimiento(SeguimientoLI scr) {
		SeguimientoLIDTO dto=new SeguimientoLIDTO();
		dto.setSeguimientoLIId(scr.getSeguimientoLIId());
		dto.setFechaCreacion(scr.getFechaCreacion());
		dto.setHipotesis(scr.getHipotesis());
		
		if(scr.getExpediente()!=null){
			dto.setExpedienteDTO(ExpedienteTransformer.transformaExpediente(scr.getExpediente()));
		}
		if(scr.getLineasInvestigacion()!=null){
			List<LineaInvestigacionDTO> lineasDTO= new ArrayList<LineaInvestigacionDTO>();
			for (LineaInvestigacion lin : scr.getLineasInvestigacion()) {
				lineasDTO.add(LineaInvestigacionTransformer.transformarInvestigacion(lin));
			}
			dto.setLineasInvestigacionDTO(lineasDTO);
		}
		return dto;
	}

}
