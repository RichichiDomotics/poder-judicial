package mx.gob.segob.nsjp.service.test.sesion.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.sesion.NotaEvolucionDTO;
import mx.gob.segob.nsjp.service.sesion.GuardarNotaEvolucionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author rgama
 * 
 */
public class GuardarNotaEvolucionServiceImplTest extends
		BaseTestServicios<GuardarNotaEvolucionService> {

	public void testGuardarNotaEvolucion() {
		NotaEvolucionDTO loNotaEvolucionDTO = new NotaEvolucionDTO();		
		//Datos genericos
		loNotaEvolucionDTO.setFechaSesion(new Date());
		loNotaEvolucionDTO.setEsPresente(false);
		loNotaEvolucionDTO.setNumeroSesion((short)10);
		loNotaEvolucionDTO.setTipoSesion(new ValorDTO(1L));
		ExpedienteDTO loExpedienteDTO = new ExpedienteDTO();
		loExpedienteDTO.setNumeroExpedienteId(865L);
		loNotaEvolucionDTO.setNumeroExpediente(loExpedienteDTO);
		//Datos particulares
		loNotaEvolucionDTO.setSeguimiento("Seguimiento");
		loNotaEvolucionDTO.setObjetivo("Objetivo");
		loNotaEvolucionDTO.setAnalisisSesion("analisis Sesion");
		loNotaEvolucionDTO.setPlanteamientoTerap("Plantemaiento Terapia");
		loNotaEvolucionDTO.setObsFaltaInteres("observaciones Falta Interes");
		//loNotaEvolucionDTO.setVictima(new InvolucradoDTO(1L));
		
		//Guardamos el objeto
		try {
			NotaEvolucionDTO loNotaEvolucionBD = service.guardarNotaEvolucion(loNotaEvolucionDTO);
			logger.debug("El id del objeto creado es " + loNotaEvolucionBD.getSesionId());
			assertTrue(loNotaEvolucionBD.getSesionId() > 0);
		} catch (NSJPNegocioException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}
	
	public void testAcutlizaNotaEvolucion() {
		NotaEvolucionDTO loNotaEvolucionDTO = new NotaEvolucionDTO(41L);		
		//Datos genericos
		loNotaEvolucionDTO.setFechaSesion(new Date());
		loNotaEvolucionDTO.setEsPresente(true);
		loNotaEvolucionDTO.setNumeroSesion((short)99);
		loNotaEvolucionDTO.setTipoSesion(new ValorDTO(3L));
		ExpedienteDTO loExpedienteDTO = new ExpedienteDTO();
		loExpedienteDTO.setNumeroExpedienteId(2L);
		loNotaEvolucionDTO.setNumeroExpediente(loExpedienteDTO);
		//Datos particulares
		loNotaEvolucionDTO.setSeguimiento("Seguimiento Modificada");
		loNotaEvolucionDTO.setObjetivo("Objetivo Modificada");
		loNotaEvolucionDTO.setAnalisisSesion("analisis Sesion Modificada");
		loNotaEvolucionDTO.setPlanteamientoTerap("Plantemaiento Terapia Modificada");
		loNotaEvolucionDTO.setObsFaltaInteres("observaciones Falta Interes Modificada");
		loNotaEvolucionDTO.setVictima(new InvolucradoDTO(4L));
		
		//Guardamos el objeto
		try {
			NotaEvolucionDTO loNotaEvolucionBD = service.guardarNotaEvolucion(loNotaEvolucionDTO);
			logger.debug("El objeto con id " + loNotaEvolucionBD.getSesionId() + " se actualizo de forma correcta");
			logger.debug("El nemero de sesion es " + loNotaEvolucionBD.getNumeroSesion());
			assertTrue(loNotaEvolucionBD.getSesionId() > 0);
		} catch (NSJPNegocioException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

}
