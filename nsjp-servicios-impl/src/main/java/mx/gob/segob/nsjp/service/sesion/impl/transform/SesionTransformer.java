package mx.gob.segob.nsjp.service.sesion.impl.transform;

import static mx.gob.segob.nsjp.service.sesion.impl.transform.NotaEvolucionTransformer.transformarNotaEvolucion;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;
import mx.gob.segob.nsjp.dto.sesion.NotaEvolucionDTO;
import mx.gob.segob.nsjp.dto.sesion.SesionDTO;
import mx.gob.segob.nsjp.model.EntrevistaComplementaria;
import mx.gob.segob.nsjp.model.EntrevistaInicial;
import mx.gob.segob.nsjp.model.NotaEvolucion;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Sesion;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

/**
 * @author rgama
 * 
 */
public class SesionTransformer {

	public static Sesion transformarSesionDTO(SesionDTO dto) {
		
		Sesion acu = new Sesion();		
		
		if(dto instanceof EntrevistaInicialDTO)
			acu = EntrevistaInicialTransformer.transformarEntrevistaInicialDTO((EntrevistaInicialDTO)dto);
		
		if(dto instanceof EntrevistaComplementariaDTO)
			acu = EntrevistaComplementariaTransformer.transformarEntrevistaComplementariaDTO((EntrevistaComplementariaDTO)dto);
		
		if(dto instanceof NotaEvolucionDTO)
			acu = NotaEvolucionTransformer.transformarNotaEvolucionDTO((NotaEvolucionDTO)dto);
		
		acu.setSesionId(dto.getSesionId());
		acu.setFechaSesion(dto.getFechaSesion());
		acu.setEsPresente(dto.getEsPresente());
		acu.setNumeroSesion(dto.getNumeroSesion());
		if(dto.getTipoSesion() != null && dto.getTipoSesion().getIdCampo() != null)
			acu.setTipoSesion(new Valor(dto.getTipoSesion().getIdCampo()));
		if(dto.getNumeroExpediente() != null && dto.getNumeroExpediente().getNumeroExpedienteId() != null)
		acu.setNumeroExpediente(new NumeroExpediente(dto
					.getNumeroExpediente().getNumeroExpedienteId()));		
		return acu;
	}

	public static SesionDTO transformarSesion(Sesion aoSesion) {
		SesionDTO acu = new SesionDTO();
		
		if(aoSesion instanceof EntrevistaInicial)
			acu = EntrevistaInicialTransformer.transformarEntrevistaInicial((EntrevistaInicial)aoSesion);
		
		if(aoSesion instanceof EntrevistaComplementaria)
			acu = EntrevistaComplementariaTransformer.transformarEntrevistaComplementaria((EntrevistaComplementaria)aoSesion);
		
		if(aoSesion instanceof NotaEvolucion)
			acu = transformarNotaEvolucion((NotaEvolucion)aoSesion);
		
		acu.setSesionId(aoSesion.getSesionId());
		acu.setFechaSesion(aoSesion.getFechaSesion());
		acu.setEsPresente(aoSesion.getEsPresente());
		acu.setNumeroSesion(aoSesion.getNumeroSesion());
		
		if(aoSesion.getTipoSesion() != null && aoSesion.getTipoSesion().getValorId() != null)
			acu.setTipoSesion(new ValorDTO(aoSesion.getTipoSesion().getValorId(),aoSesion.getTipoSesion().getValor()));
		
		if(aoSesion.getNumeroExpediente() != null)
			acu.setNumeroExpediente(ExpedienteTransformer.transformarExpediente(aoSesion.getNumeroExpediente()));
		return acu;
	}
}
