/**
 * 
 */
package mx.gob.segob.nsjp.service.policiaministerial.impl.transformer;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.model.Comentario;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.LineaInvestigacion;
import mx.gob.segob.nsjp.model.SeguimientoLI;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

/**
 * @author adrian
 * 
 */
public class LineaInvestigacionTransformer {

	public static LineaInvestigacion transformarInvestigacionDTO(
			LineaInvestigacionDTO dto) {
		Boolean esImpreso=false;
		if(dto.getEsImpreso()!=null)
			esImpreso=dto.getEsImpreso();
		
		LineaInvestigacion resp = new LineaInvestigacion(
				dto.getLineaInvestigacionId(), dto.getFechaCreacion(),
				dto.getDescripcion(), dto.getNumComentarios(),
				dto.getFechaUltimoComentario(),esImpreso);
		
		if(dto.getTipoLineaInvestigacioValorDTO()!=null)
			resp.setTipoLineaInvestigacion(new Valor(dto.getTipoLineaInvestigacioValorDTO().getIdCampo()));

		if (dto.getSeguimientoLIDTO() != null)
			resp.setSeguimientoLI(new SeguimientoLI(dto.getSeguimientoLIDTO()
					.getSeguimientoLIId()));
		
		if(dto.getExpedienteDTO()!=null && dto.getExpedienteDTO().getExpedienteId()!=null)
			resp.setExpediente(new Expediente(dto.getExpedienteDTO().getExpedienteId()));

		return resp;
	}

	public static LineaInvestigacionDTO transformarInvestigacion(
			LineaInvestigacion scr) {
		LineaInvestigacionDTO dto=new LineaInvestigacionDTO();
		
		dto.setLineaInvestigacionId(scr.getLineaInvestigacionId());
		dto.setFechaCreacion(scr.getFechaCreacion());
		dto.setDescripcion(scr.getDescripcion());
		dto.setEsImpreso(scr.getEsImpreso());
		
		if (scr.getConsecutivo()!=null) {
			dto.setConsecutivo(new Long(scr.getConsecutivo()));
		}
		
		if(scr.getTipoLineaInvestigacion()!=null)
			dto.setTipoLineaInvestigacioValorDTO(new ValorDTO(scr.getTipoLineaInvestigacion().getValorId(), scr.getTipoLineaInvestigacion().getValor()));
		if(scr.getSeguimientoLI()!=null){
			SeguimientoLI seguim=scr.getSeguimientoLI();
			seguim.setLineasInvestigacion(null);//Para que no se ciclen los transformer
			dto.setSeguimientoLIDTO(SeguimientoLITransformer.transformarSeguimiento(seguim));
		}
		if(scr.getComentarios()!=null){
			List<ComentarioDTO> comentariosDTO=new ArrayList<ComentarioDTO>();
			for (Comentario com : scr.getComentarios()) {
				comentariosDTO.add(ComentarioTransformer.transformarComentario(com));
			}
			
			dto.setComentarioDTOs(comentariosDTO);
		}
		dto.setNumComentarios(scr.getNumComentarios());
		dto.setFechaUltimoComentario(scr.getFechaUltimoComentario());
		dto.setExpedienteDTO(ExpedienteTransformer.transformaExpediente(scr.getExpediente()));
		
		return dto;
	}

}
