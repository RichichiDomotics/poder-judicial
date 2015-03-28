package mx.gob.segob.nsjp.service.test.sesion.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;
import mx.gob.segob.nsjp.service.sesion.GuardarEntrevistaInicialService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author rgama
 * 
 */
public class GuardarEntrevistaInicialServiceImplTest extends
		BaseTestServicios<GuardarEntrevistaInicialService> {

	public void testGuardarEntrevistaInicial() {
		EntrevistaInicialDTO loEntrevistaInicialDTO = new EntrevistaInicialDTO();		
		//Datos genericos
		loEntrevistaInicialDTO.setFechaSesion(new Date());
		loEntrevistaInicialDTO.setEsPresente(false);
		loEntrevistaInicialDTO.setNumeroSesion((short)10);
		loEntrevistaInicialDTO.setTipoSesion(new ValorDTO(100L));
		ExpedienteDTO loExpedienteDTO = new ExpedienteDTO();
		loExpedienteDTO.setNumeroExpedienteId(1L);
		loEntrevistaInicialDTO.setNumeroExpediente(loExpedienteDTO);
		//Datos particulares
		loEntrevistaInicialDTO.setEsVictimaDirecta(false);
		loEntrevistaInicialDTO.setMotivoAtencion("Motivo de atencion");
		
		//Guardamos el objeto
		try {
			EntrevistaInicialDTO loEntrevistaInicialBD = service.guardarEntrevistaInicial(loEntrevistaInicialDTO);
			logger.debug("El id del objeto creado es " + loEntrevistaInicialBD.getSesionId());
			logger.debug("El nemero de sesion es " + loEntrevistaInicialBD.getNumeroSesion());
			assertTrue(loEntrevistaInicialBD.getSesionId() > 0);
		} catch (NSJPNegocioException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}
	
	public void testAcutlizaEntrevistaInicial() {
		EntrevistaInicialDTO loEntrevistaInicialDTO = new EntrevistaInicialDTO(36L);		
		//Datos genericos
		loEntrevistaInicialDTO.setFechaSesion(new Date());
		loEntrevistaInicialDTO.setEsPresente(true);
		loEntrevistaInicialDTO.setNumeroSesion((short)99);
		loEntrevistaInicialDTO.setTipoSesion(new ValorDTO(1L));
		ExpedienteDTO loExpedienteDTO = new ExpedienteDTO();
		loExpedienteDTO.setNumeroExpedienteId(2L);
		loEntrevistaInicialDTO.setNumeroExpediente(loExpedienteDTO);
		//Datos particulares
		loEntrevistaInicialDTO.setEsVictimaDirecta(true);
		loEntrevistaInicialDTO.setMotivoAtencion("Motivo de atencion Modificada");
		
		//Guardamos el objeto
		try {
			EntrevistaInicialDTO loEntrevistaInicialBD = service.guardarEntrevistaInicial(loEntrevistaInicialDTO);
			logger.debug("El objeto con id " + loEntrevistaInicialBD.getSesionId() + " se actualizo de forma correcta");
			logger.debug("El nemero de sesion es " + loEntrevistaInicialBD.getNumeroSesion());
			assertTrue(loEntrevistaInicialBD.getSesionId() > 0);
		} catch (NSJPNegocioException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

}
