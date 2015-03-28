package mx.gob.segob.nsjp.service.test.sesion.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.relacion.CatRelacionDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;
import mx.gob.segob.nsjp.service.familiar.GuardarFamiliarService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author rgama
 * 
 */
public class GuardarFamiliarServiceImplTest extends
		BaseTestServicios<GuardarFamiliarService> {

	public void testGuardarFamiliar() {
		FamiliarDTO loFamiliarDTO = new FamiliarDTO();		
		//Datos particulares
		loFamiliarDTO.setNombre("nombre");
		loFamiliarDTO.setApellidoPaterno("apellidoPaterno");
		loFamiliarDTO.setApellidoMaterno("apellidoMaterno");
		loFamiliarDTO.setEdad((short)18);
		
		loFamiliarDTO.setRelacion(new CatRelacionDTO(1L));
		loFamiliarDTO.setEscolaridad(new ValorDTO(101L));
		loFamiliarDTO.setEstadoCivil(new ValorDTO(102L));
		loFamiliarDTO.setOcupacion(new ValorDTO(103L));
		loFamiliarDTO.setEntrevistaComplementaria(new EntrevistaComplementariaDTO(38L));
		//Guardamos el objeto
		try {
			FamiliarDTO loFamiliarBD = service.guardarFamiliar(loFamiliarDTO);
			logger.debug("El id del objeto creado es " + loFamiliarBD.getFamiliarId());
			assertTrue(loFamiliarBD.getFamiliarId() > 0);
		} catch (NSJPNegocioException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}
	
	public void testAcutlizaFamiliar() {
		FamiliarDTO loFamiliarDTO = new FamiliarDTO(3L);		
		//Datos particulares
		loFamiliarDTO.setNombre("nombre Modificado");
		loFamiliarDTO.setApellidoPaterno("apellidoPaterno Modificado");
		loFamiliarDTO.setApellidoMaterno("apellidoMaterno Modificado");
		loFamiliarDTO.setEdad((short)19);
		loFamiliarDTO.setRelacion(new CatRelacionDTO(1L));
		loFamiliarDTO.setEscolaridad(new ValorDTO(501L));
		loFamiliarDTO.setEstadoCivil(new ValorDTO(502L));
		loFamiliarDTO.setOcupacion(new ValorDTO(503L));
		loFamiliarDTO.setEntrevistaComplementaria(new EntrevistaComplementariaDTO(4L));
		
		//Guardamos el objeto
		try {
			FamiliarDTO loFamiliarBD = service.guardarFamiliar(loFamiliarDTO);
			logger.debug("El objeto con id " + loFamiliarBD.getFamiliarId() + " se actualizo de forma correcta");
			assertTrue(loFamiliarBD.getFamiliarId() > 0);
		} catch (NSJPNegocioException e) {
			assertTrue(false);
			e.printStackTrace();
		}
	}

}
