package mx.gob.segob.nsjp.service.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.DiaInhabilDTO;
import mx.gob.segob.nsjp.service.catalogo.DiaInhabilService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class DiaInhabilServiceImplTest extends BaseTestServicios<DiaInhabilService> {

	
	
	public void testGuardarDiaInhabil() throws NSJPNegocioException{
		
		DiaInhabilDTO dia = new DiaInhabilDTO();
		dia.setDescripcion("Día de la constitucion");
		dia.setDia((short)5);
		dia.setMes((short)2);
		
		Long id = service.guardarDiaInhabil(dia);
		logger.info("Id del nuevo día inhabil :: "+id);
	}
	
	public void testConsultarDiasInhabiles() throws NSJPNegocioException{
		List<DiaInhabilDTO> dias = service.consultarDiasInhabiles();
		for (DiaInhabilDTO dia : dias) {
			logger.info("ID: "+dia.getDiaInhabilId()+" dia: "+dia.getDia()+" mes: "+dia.getMes()+" "+dia.getDescripcion());
		}
	}
	
	public void testConsultarDiasInhabilesPorMes() throws NSJPNegocioException{
		Short mesPrueba = 6;
		List<DiaInhabilDTO> dias = service.consultarDiasInhabilesPorMes(mesPrueba);
		for (DiaInhabilDTO dia : dias) {
			logger.info("ID: "+dia.getDiaInhabilId()+" dia: "+dia.getDia()+" mes: "+dia.getMes()+" "+dia.getDescripcion());
		}
	}

	public void testEliminarDiaInhabil() throws NSJPNegocioException{
		DiaInhabilDTO dia = new DiaInhabilDTO();
		dia.setDiaInhabilId(7L);
		service.eliminarDiaInhabil(dia);

	}

	
}
