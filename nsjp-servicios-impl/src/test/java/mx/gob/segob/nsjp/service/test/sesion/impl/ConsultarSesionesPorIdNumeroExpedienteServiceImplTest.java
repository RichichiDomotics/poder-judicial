package mx.gob.segob.nsjp.service.test.sesion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;
import mx.gob.segob.nsjp.dto.sesion.NotaEvolucionDTO;
import mx.gob.segob.nsjp.dto.sesion.SesionDTO;
import mx.gob.segob.nsjp.service.sesion.ConsultarSesionesPorIdNumeroExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author rgama
 * 
 */
public class ConsultarSesionesPorIdNumeroExpedienteServiceImplTest extends
		BaseTestServicios<ConsultarSesionesPorIdNumeroExpedienteService> {

	public void testConsultarSesionPorIdService() {
	try {
		ExpedienteDTO loExpedienteDTO = new ExpedienteDTO();
		loExpedienteDTO.setNumeroExpedienteId(2L);
		List<SesionDTO> loSesionesBD = service.consultarSesionesPorIdNumeroExpediente(loExpedienteDTO);
		assertNotNull(loSesionesBD);
		for (SesionDTO sesionDTO : loSesionesBD) {
			if(sesionDTO instanceof EntrevistaInicialDTO){
				EntrevistaInicialDTO loEntrevistaInicialBD = (EntrevistaInicialDTO)sesionDTO;
				System.out.println("ENTREVISTA INICIAL");
				//Datos genericos
				System.out.println(" ID:" + loEntrevistaInicialBD.getSesionId());
				System.out.println(" FechaSesion:" + loEntrevistaInicialBD.getFechaSesion());
				System.out.println(" EsPresente:" + loEntrevistaInicialBD.getEsPresente());
				System.out.println(" NumeroSesion:" + loEntrevistaInicialBD.getNumeroSesion());
				System.out.println(" TipoSesion:" + loEntrevistaInicialBD.getTipoSesion().getValor());
				System.out.println(" NumeroExpediente:" + loEntrevistaInicialBD.getNumeroExpediente().getNumeroExpediente());
				System.out.println(" ID NumExp:" + loEntrevistaInicialBD.getNumeroExpediente().getNumeroExpedienteId());
				//Datos particulares
				System.out.println(" EsVictimaDirecta:" + loEntrevistaInicialBD.getEsVictimaDirecta());
				System.out.println(" MotivoAtencion:" + loEntrevistaInicialBD.getMotivoAtencion());		
			}
			if(sesionDTO instanceof EntrevistaComplementariaDTO){
				EntrevistaComplementariaDTO loEntrevistaComplementariaBD = (EntrevistaComplementariaDTO)sesionDTO;
				System.out.println("ENTREVISTA COMPLEMENTARIA");
				//Datos genericos
				System.out.println(" ID:" + loEntrevistaComplementariaBD.getSesionId());
				System.out.println(" FechaSesion:" + loEntrevistaComplementariaBD.getFechaSesion());
				System.out.println(" EsPresente:" + loEntrevistaComplementariaBD.getEsPresente());
				System.out.println(" NumeroSesion:" + loEntrevistaComplementariaBD.getNumeroSesion());
				System.out.println(" TipoSesion:" + loEntrevistaComplementariaBD.getTipoSesion().getValor());
				System.out.println(" NumeroExpediente:" + loEntrevistaComplementariaBD.getNumeroExpediente().getNumeroExpediente());
				System.out.println(" ID NumExp:" + loEntrevistaComplementariaBD.getNumeroExpediente().getNumeroExpedienteId());
				//Datos particulares
				System.out.println(" MotivoConsulta:" + loEntrevistaComplementariaBD.getMotivoConsulta());
				System.out.println(" ConcienciaProblema:" + loEntrevistaComplementariaBD.getConcienciaProblema());	
				System.out.println(" ImpresionDiagnostico:" + loEntrevistaComplementariaBD.getImpresionDiagnostico());	
				System.out.println(" HipotesisFamilia:" + loEntrevistaComplementariaBD.getHipotesisFamilia());
				for (FamiliarDTO loFamiliarBD : loEntrevistaComplementariaBD.getFamiliares()) {
					System.out.println("	ID:" + loFamiliarBD.getFamiliarId());
					System.out.println("	Nombre:" + loFamiliarBD.getNombre());
					System.out.println("	ApellidoPaterno:" + loFamiliarBD.getApellidoPaterno());
					System.out.println("	ApellidoMaterno:" + loFamiliarBD.getApellidoMaterno());
					System.out.println("	Edad:" + loFamiliarBD.getEdad());
					if(loFamiliarBD.getRelacion() != null )
						System.out.println("	Parentesco:" + loFamiliarBD.getRelacion().getDescripcionRelacion());
					System.out.println("	Escolaridad:" + loFamiliarBD.getEscolaridad());
					System.out.println("	EstadoCivil:" + loFamiliarBD.getEstadoCivil());
					System.out.println("	Ocupacion:" + loFamiliarBD.getOcupacion());
					System.out.println("	EntrevistaComplementaria:" + loFamiliarBD.getEntrevistaComplementaria());
				}
			}
			if(sesionDTO instanceof NotaEvolucionDTO){
				NotaEvolucionDTO loNotaEvolucionBD = (NotaEvolucionDTO)sesionDTO;
				System.out.println("NOTA EVOLUCION");
				//Datos genericos
				System.out.println(" ID:" + loNotaEvolucionBD.getSesionId());
				System.out.println(" FechaSesion:" + loNotaEvolucionBD.getFechaSesion());
				System.out.println(" EsPresente:" + loNotaEvolucionBD.getEsPresente());
				System.out.println(" NumeroSesion:" + loNotaEvolucionBD.getNumeroSesion());
				System.out.println(" TipoSesion:" + loNotaEvolucionBD.getTipoSesion().getValor());
				System.out.println(" NumeroExpediente:" + loNotaEvolucionBD.getNumeroExpediente().getNumeroExpediente());
				System.out.println(" ID NumExp:" + loNotaEvolucionBD.getNumeroExpediente().getNumeroExpedienteId());
				//Datos particulares
				System.out.println(" Seguimiento:" + loNotaEvolucionBD.getSeguimiento());
				System.out.println(" Objetivo:" + loNotaEvolucionBD.getObjetivo());	
				System.out.println(" AnalisisSesion:" + loNotaEvolucionBD.getAnalisisSesion());	
				System.out.println(" PlanteamientoTerap:" + loNotaEvolucionBD.getPlanteamientoTerap());	
				System.out.println(" ObsFaltaInteres:" + loNotaEvolucionBD.getObsFaltaInteres());	
				System.out.println(" Victima:" + loNotaEvolucionBD.getVictima().getNombreCompleto() + " " 
						+ loNotaEvolucionBD.getVictima().getElementoId());
		
			}
		}
		
		
		System.out.println("TOTAL DE SESIONES ENCONTRADAS:" + loSesionesBD.size());

		
	} catch (NSJPNegocioException e) {
		e.printStackTrace();
	}	
	}

}
