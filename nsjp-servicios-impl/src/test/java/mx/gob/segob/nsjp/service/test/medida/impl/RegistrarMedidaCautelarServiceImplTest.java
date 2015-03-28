package mx.gob.segob.nsjp.service.test.medida.impl;

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.medida.MedidaCautelarWSDTO;
import mx.gob.segob.nsjp.service.medida.RegistrarMedidaCautelarService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class RegistrarMedidaCautelarServiceImplTest extends
		BaseTestServicios<RegistrarMedidaCautelarService> {

	public void testRegistrarMedidaCautelar() throws NSJPNegocioException{
	
		MedidaCautelarWSDTO medida = new MedidaCautelarWSDTO();
		
		medida.setNombreSujeto("Alejandro");
		medida.setaPaternoSujeto("Guerrero");
		medida.setaMaternoSujeto("Ortíz");
		medida.setFolioDocumento("PJ/201100001");
        medida.setIdValorPeriodicidad(1L);
        medida.setIdValorTipoMedida(1L);
        medida.setFechaInicio(Calendar.getInstance().getTime());
        medida.setFechaFin(Calendar.getInstance().getTime());
        medida.setDescripcionMedida("Descripcion de la medida cautelar");
        medida.setActivo(true);
        medida.setJuezOrdena("Daniel Alejandro Jiménez Ventura");
        medida.setFechaCreacion(Calendar.getInstance().getTime());
		
        //TODO falta agregar el archivo
        
		service.registrarMedidaCautelar(medida);
	}
}
