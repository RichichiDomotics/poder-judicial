package mx.gob.segob.nsjp.service.test.sesion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;
import mx.gob.segob.nsjp.service.familiar.ConsultarFamiliaresPorIdEntrevistaComplementariaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author rgama
 * 
 */
public class ConsultarFamiliaresPorIdEntrevistaComplementariaServiceImplTest extends
		BaseTestServicios<ConsultarFamiliaresPorIdEntrevistaComplementariaService> {

	public void testConsultarFamiliarPorIdService() {
	try {
		List<FamiliarDTO> loFamiliaresBD = service.consultarFamiliaresPorIdEntrevistaComplementaria(new EntrevistaComplementariaDTO(38L));
		assertNotNull(loFamiliaresBD);		
		for (FamiliarDTO loFamiliarBD : loFamiliaresBD) {
			//Datos particulares
			System.out.println("ID:" + loFamiliarBD.getFamiliarId());
			System.out.println("Nombre:" + loFamiliarBD.getNombre());
			System.out.println("ApellidoPaterno:" + loFamiliarBD.getApellidoPaterno());
			System.out.println("ApellidoMaterno:" + loFamiliarBD.getApellidoMaterno());
			System.out.println("Edad:" + loFamiliarBD.getEdad());
			System.out.println("Parentesco:" + loFamiliarBD.getRelacion().getDescripcionRelacion());
			System.out.println("Escolaridad:" + loFamiliarBD.getEscolaridad());
			System.out.println("EstadoCivil:" + loFamiliarBD.getEstadoCivil());
			System.out.println("Ocupacion:" + loFamiliarBD.getOcupacion());
			System.out.println("EntrevistaComplementaria:" + loFamiliarBD.getEntrevistaComplementaria());
		}
		System.out.println("TOTAL DE FAMILIARES ENCONTRADOS:" + loFamiliaresBD.size());

		
	} catch (NSJPNegocioException e) {
		e.printStackTrace();
	}	
	}

}
