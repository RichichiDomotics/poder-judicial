package mx.gob.segob.nsjp.service.sesion.impl.transform;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;
import mx.gob.segob.nsjp.dto.sesion.NotaEvolucionDTO;
import mx.gob.segob.nsjp.model.EntrevistaInicial;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.NotaEvolucion;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

/**
 * @author rgama
 * 
 */
public class NotaEvolucionTransformer {

	public static NotaEvolucion transformarNotaEvolucionDTO(NotaEvolucionDTO dto) {
		
		NotaEvolucion acu = new NotaEvolucion();		
	
		acu.setSeguimiento(dto.getSeguimiento());
		acu.setObjetivo(dto.getObjetivo());
		acu.setAnalisisSesion(dto.getAnalisisSesion());
		acu.setPlanteamientoTerap(dto.getPlanteamientoTerap());
		acu.setObsFaltaInteres(dto.getObsFaltaInteres());
		if(dto.getVictima() != null)
			acu.setVictima(InvolucradoTransformer.transformarInvolucrado(dto.getVictima()));	
		return acu;
	}

	public static NotaEvolucionDTO transformarNotaEvolucion(NotaEvolucion aoNotaEvolucion) {
		NotaEvolucionDTO dto = new NotaEvolucionDTO();		
		dto.setSeguimiento(aoNotaEvolucion.getSeguimiento());
		dto.setObjetivo(aoNotaEvolucion.getObjetivo());
		dto.setAnalisisSesion(aoNotaEvolucion.getAnalisisSesion());
		dto.setPlanteamientoTerap(aoNotaEvolucion.getPlanteamientoTerap());
		dto.setObsFaltaInteres(aoNotaEvolucion.getObsFaltaInteres());
		if(aoNotaEvolucion.getVictima() != null)
			dto.setVictima(InvolucradoTransformer.transformarInvolucrado(aoNotaEvolucion.getVictima()));
		return dto;
	}
}
