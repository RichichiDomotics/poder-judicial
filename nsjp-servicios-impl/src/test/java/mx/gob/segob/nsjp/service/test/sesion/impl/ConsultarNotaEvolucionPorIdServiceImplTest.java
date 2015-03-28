package mx.gob.segob.nsjp.service.test.sesion.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.NotaEvolucionDTO;
import mx.gob.segob.nsjp.service.sesion.ConsultarNotaEvolucionPorIdService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author rgama
 * 
 */
public class ConsultarNotaEvolucionPorIdServiceImplTest extends
		BaseTestServicios<ConsultarNotaEvolucionPorIdService> {

	public void testConsultarNotaEvolucionPorIdService() {
		NotaEvolucionDTO loNotaEvolucionBD = null;
	try {
		loNotaEvolucionBD = service.consultarNotaEvolucionPorId(new NotaEvolucionDTO(1L));
		assertNotNull(loNotaEvolucionBD);
		//Datos genericos
		System.out.println("ID:" + loNotaEvolucionBD.getSesionId());
		System.out.println("FechaSesion:" + loNotaEvolucionBD.getFechaSesion());
		System.out.println("EsPresente:" + loNotaEvolucionBD.getEsPresente());
		System.out.println("NumeroSesion:" + loNotaEvolucionBD.getNumeroSesion());
		System.out.println("TipoSesion:" + loNotaEvolucionBD.getTipoSesion().getValor());
		System.out.println("NumeroExpediente:" + loNotaEvolucionBD.getNumeroExpediente().getNumeroExpediente());
		System.out.println("ID NumExp:" + loNotaEvolucionBD.getNumeroExpediente().getNumeroExpedienteId());
		//Datos particulares
		System.out.println("Seguimiento:" + loNotaEvolucionBD.getSeguimiento());
		System.out.println("Objetivo:" + loNotaEvolucionBD.getObjetivo());	
		System.out.println("AnalisisSesion:" + loNotaEvolucionBD.getAnalisisSesion());	
		System.out.println("PlanteamientoTerap:" + loNotaEvolucionBD.getPlanteamientoTerap());	
		System.out.println("ObsFaltaInteres:" + loNotaEvolucionBD.getObsFaltaInteres());	
		System.out.println("Victima:" + loNotaEvolucionBD.getVictima().getNombreCompleto() + " " 
				+ loNotaEvolucionBD.getVictima().getElementoId());
		
	} catch (NSJPNegocioException e) {
		e.printStackTrace();
	}	
	}

}
