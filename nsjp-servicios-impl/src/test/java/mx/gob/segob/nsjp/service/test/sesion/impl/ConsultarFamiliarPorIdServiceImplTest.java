package mx.gob.segob.nsjp.service.test.sesion.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;
import mx.gob.segob.nsjp.service.familiar.ConsultarFamiliarPorIdService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author rgama
 * 
 */
public class ConsultarFamiliarPorIdServiceImplTest extends
		BaseTestServicios<ConsultarFamiliarPorIdService> {

	public void testConsultarFamiliarPorIdService() {
		FamiliarDTO loFamiliarBD = null;
	try {
		loFamiliarBD = service.consultarFamiliarPorId(new FamiliarDTO(3L));
		assertNotNull(loFamiliarBD);
	
		//Datos particulares
		System.out.println("ID:" + loFamiliarBD.getFamiliarId());
		System.out.println("Nombre:" + loFamiliarBD.getNombre());
		System.out.println("ApellidoPaterno:" + loFamiliarBD.getApellidoPaterno());
		System.out.println("ApellidoMaterno:" + loFamiliarBD.getApellidoMaterno());
		System.out.println("Edad:" + loFamiliarBD.getEdad());
		if(loFamiliarBD.getRelacion() != null)
			System.out.println("Parentesco:" + loFamiliarBD.getRelacion().getDescripcionRelacion());
		System.out.println("Escolaridad:" + loFamiliarBD.getEscolaridad());
		System.out.println("EstadoCivil:" + loFamiliarBD.getEstadoCivil());
		System.out.println("Ocupacion:" + loFamiliarBD.getOcupacion());
		System.out.println("EntrevistaComplementaria:" + loFamiliarBD.getEntrevistaComplementaria());
		
	} catch (NSJPNegocioException e) {
		e.printStackTrace();
	}	
	}

}
