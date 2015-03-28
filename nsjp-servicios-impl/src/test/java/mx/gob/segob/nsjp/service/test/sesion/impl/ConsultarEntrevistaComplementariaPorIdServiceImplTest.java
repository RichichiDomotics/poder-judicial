package mx.gob.segob.nsjp.service.test.sesion.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;
import mx.gob.segob.nsjp.service.sesion.ConsultarEntrevistaComplementariaPorIdService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author rgama
 * 
 */
public class ConsultarEntrevistaComplementariaPorIdServiceImplTest extends
		BaseTestServicios<ConsultarEntrevistaComplementariaPorIdService> {

	public void testConsultarEntrevistaComplementariaPorIdService() {
		EntrevistaComplementariaDTO loEntrevistaComplementariaBD = null;
	try {
		loEntrevistaComplementariaBD = service.consultarEntrevistaComplementariaPorId(new EntrevistaComplementariaDTO(4L));
		assertNotNull(loEntrevistaComplementariaBD);
		//Datos genericos
		System.out.println("ID:" + loEntrevistaComplementariaBD.getSesionId());
		System.out.println("FechaSesion:" + loEntrevistaComplementariaBD.getFechaSesion());
		System.out.println("EsPresente:" + loEntrevistaComplementariaBD.getEsPresente());
		System.out.println("NumeroSesion:" + loEntrevistaComplementariaBD.getNumeroSesion());
		System.out.println("TipoSesion:" + loEntrevistaComplementariaBD.getTipoSesion().getValor());
		System.out.println("NumeroExpediente:" + loEntrevistaComplementariaBD.getNumeroExpediente().getNumeroExpediente());
		System.out.println("ID NumExp:" + loEntrevistaComplementariaBD.getNumeroExpediente().getNumeroExpedienteId());
		//Datos particulares
		System.out.println("MotivoConsulta:" + loEntrevistaComplementariaBD.getMotivoConsulta());
		System.out.println("ConcienciaProblema:" + loEntrevistaComplementariaBD.getConcienciaProblema());	
		System.out.println("ImpresionDiagnostico:" + loEntrevistaComplementariaBD.getImpresionDiagnostico());	
		System.out.println("HipotesisFamilia:" + loEntrevistaComplementariaBD.getHipotesisFamilia());
		for (FamiliarDTO loFamiliarBD : loEntrevistaComplementariaBD.getFamiliares()) {
			System.out.println("	ID:" + loFamiliarBD.getFamiliarId());
			System.out.println("	Nombre:" + loFamiliarBD.getNombre());
			System.out.println("	ApellidoPaterno:" + loFamiliarBD.getApellidoPaterno());
			System.out.println("	ApellidoMaterno:" + loFamiliarBD.getApellidoMaterno());
			System.out.println("	Edad:" + loFamiliarBD.getEdad());
			System.out.println("	Parentesco:" + loFamiliarBD.getRelacion().getDescripcionRelacion());
			System.out.println("	Escolaridad:" + loFamiliarBD.getEscolaridad());
			System.out.println("	EstadoCivil:" + loFamiliarBD.getEstadoCivil());
			System.out.println("	Ocupacion:" + loFamiliarBD.getOcupacion());
			System.out.println("	EntrevistaComplementaria:" + loFamiliarBD.getEntrevistaComplementaria());
		}
		
		
	} catch (NSJPNegocioException e) {
		e.printStackTrace();
	}	
	}

}
