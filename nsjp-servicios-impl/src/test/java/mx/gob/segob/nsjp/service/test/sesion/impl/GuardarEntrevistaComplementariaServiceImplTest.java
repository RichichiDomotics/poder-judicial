package mx.gob.segob.nsjp.service.test.sesion.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.service.sesion.GuardarEntrevistaComplementariaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author rgama
 * 
 */
public class GuardarEntrevistaComplementariaServiceImplTest extends
		BaseTestServicios<GuardarEntrevistaComplementariaService> {

	public void testGuardarEntrevistaComplementaria() {
		EntrevistaComplementariaDTO loEntrevistaComplementariaDTO = new EntrevistaComplementariaDTO();		
		//Datos genericos
		loEntrevistaComplementariaDTO.setFechaSesion(new Date());
		loEntrevistaComplementariaDTO.setEsPresente(false);
		loEntrevistaComplementariaDTO.setNumeroSesion((short)10);
		loEntrevistaComplementariaDTO.setTipoSesion(new ValorDTO(1L));
		ExpedienteDTO loExpedienteDTO = new ExpedienteDTO();
		loExpedienteDTO.setNumeroExpedienteId(1L);
		loEntrevistaComplementariaDTO.setNumeroExpediente(loExpedienteDTO);
		//Datos particulares
		loEntrevistaComplementariaDTO.setMotivoConsulta("Motivo de consulta");
		loEntrevistaComplementariaDTO.setConcienciaProblema("Conciencia Problema");
		loEntrevistaComplementariaDTO.setImpresionDiagnostico("Impresion Diagnostico");
		loEntrevistaComplementariaDTO.setHipotesisFamilia("Hipotesis Familia");
		
		//Guardamos el objeto
		try {
			EntrevistaComplementariaDTO loEntrevistaComplementariaBD = service.guardarEntrevistaComplementaria(loEntrevistaComplementariaDTO);
			logger.debug("El id del objeto creado es " + loEntrevistaComplementariaBD.getSesionId());
			assertTrue(loEntrevistaComplementariaBD.getSesionId() > 0);
		} catch (NSJPNegocioException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}
	
	public void testAcutlizaEntrevistaComplementaria() {
		EntrevistaComplementariaDTO loEntrevistaComplementariaDTO = new EntrevistaComplementariaDTO(37L);		
		//Datos genericos
		loEntrevistaComplementariaDTO.setFechaSesion(new Date());
		loEntrevistaComplementariaDTO.setEsPresente(true);
		loEntrevistaComplementariaDTO.setNumeroSesion((short)999);
		loEntrevistaComplementariaDTO.setTipoSesion(new ValorDTO(2L));
		ExpedienteDTO loExpedienteDTO = new ExpedienteDTO();
		loExpedienteDTO.setNumeroExpedienteId(2L);
		loEntrevistaComplementariaDTO.setNumeroExpediente(loExpedienteDTO);
		//Datos particulares
		loEntrevistaComplementariaDTO.setMotivoConsulta("Motivo de consulta Modificada");
		loEntrevistaComplementariaDTO.setConcienciaProblema("Conciencia Problema Modificada");
		loEntrevistaComplementariaDTO.setImpresionDiagnostico("Impresion Diagnostico Modificada");
		loEntrevistaComplementariaDTO.setHipotesisFamilia("Hipotesis Familia Modificada");
		
		//Guardamos el objeto
		try {
			EntrevistaComplementariaDTO loEntrevistaComplementariaBD = service.guardarEntrevistaComplementaria(loEntrevistaComplementariaDTO);
			logger.debug("El objeto con id " + loEntrevistaComplementariaBD.getSesionId() + " se actualizo de forma correcta");
			logger.debug("El nemero de sesion es " + loEntrevistaComplementariaBD.getNumeroSesion());
			assertTrue(loEntrevistaComplementariaBD.getSesionId() > 0);
		} catch (NSJPNegocioException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

}
