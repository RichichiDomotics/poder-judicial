package mx.gob.segob.nsjp.service.test.sesion.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;
import mx.gob.segob.nsjp.service.sesion.ConsultarEntrevistaInicialPorIdService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author rgama
 * 
 */
public class ConsultarEntrevistaInicialPorIdServiceImplTest extends
		BaseTestServicios<ConsultarEntrevistaInicialPorIdService> {

	public void testConsultarEntrevistaInicialPorIdService() {
		EntrevistaInicialDTO loEntrevistaInicialBD = null;
	try {
		loEntrevistaInicialBD = service.consultarEntrevistaInicialPorId(new EntrevistaInicialDTO(36L));
		assertNotNull(loEntrevistaInicialBD);
		//Datos genericos
		System.out.println("ID:" + loEntrevistaInicialBD.getSesionId());
		System.out.println("FechaSesion:" + loEntrevistaInicialBD.getFechaSesion());
		System.out.println("EsPresente:" + loEntrevistaInicialBD.getEsPresente());
		System.out.println("NumeroSesion:" + loEntrevistaInicialBD.getNumeroSesion());
		System.out.println("TipoSesion:" + loEntrevistaInicialBD.getTipoSesion().getValor());
		System.out.println("NumeroExpediente:" + loEntrevistaInicialBD.getNumeroExpediente().getNumeroExpediente());
		System.out.println("ID NumExp:" + loEntrevistaInicialBD.getNumeroExpediente().getNumeroExpedienteId());
		//Datos particulares
		System.out.println("EsVictimaDirecta:" + loEntrevistaInicialBD.getEsVictimaDirecta());
		System.out.println("MotivoAtencion:" + loEntrevistaInicialBD.getMotivoAtencion());		
	} catch (NSJPNegocioException e) {
		e.printStackTrace();
	}	
	}

}
