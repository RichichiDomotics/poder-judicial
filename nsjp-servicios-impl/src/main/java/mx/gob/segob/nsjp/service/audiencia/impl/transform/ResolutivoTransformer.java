package mx.gob.segob.nsjp.service.audiencia.impl.transform;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoViewDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Resolutivo;

public class ResolutivoTransformer {

	public static Resolutivo transformarResolutivo(ResolutivoDTO resolutivo){
		Resolutivo resol = new Resolutivo();
		if(resolutivo.getAudiencia() != null){
			Audiencia aud = new Audiencia();
			aud.setAudienciaId(resolutivo.getAudiencia().getId());
			resol.setAudiencia(aud);
		}
		resol.setDetalle(resolutivo.getDetalle());
		resol.setTemporizador(resolutivo.getTemporizador());
		return resol;
	}
	
	public static ResolutivoDTO transformarResolutivo(Resolutivo resolutivo){
		ResolutivoDTO resolutivoDTO = new ResolutivoDTO();
		resolutivoDTO.setResolutivoId(resolutivo.getResolutivoId());
		if(resolutivo.getAudiencia() != null){
			AudienciaDTO aud = EventoTransformer.transformarAudienciaBasico(resolutivo.getAudiencia());
			resolutivoDTO.setAudiencia(aud);
		}
		resolutivoDTO.setDetalle(resolutivo.getDetalle());
		resolutivoDTO.setTemporizador(resolutivo.getTemporizador());
		if(resolutivo.getDocumento() != null){
			//TODO agregar el código para transformar el documento
		}
		return resolutivoDTO;
	}
	
	public static ResolutivoViewDTO transformarResolutivoView(Resolutivo resolutivo){
		ResolutivoViewDTO dto = new ResolutivoViewDTO();
		dto.setResolutivoId(resolutivo.getResolutivoId());
		dto.setTemporizador(DateUtils.formatearHoraSegs(resolutivo.getTemporizador()));
		dto.setDetalle(resolutivo.getDetalle());
		return dto;
	}
	
}

