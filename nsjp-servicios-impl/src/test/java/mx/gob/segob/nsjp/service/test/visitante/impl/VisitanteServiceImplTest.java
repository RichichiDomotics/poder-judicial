/**
 * 
 */
package mx.gob.segob.nsjp.service.test.visitante.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.visitante.VisitanteDTO;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.service.visitante.VisitanteService;


/**
 * @author LuisMG
 *
 */
public class VisitanteServiceImplTest extends BaseTestServicios<VisitanteService>{

	public void testRegistrarVisitante() {

		VisitanteDTO visitanteDTO = new VisitanteDTO();
		visitanteDTO.setcIP("0.0.0.2");
		try {
			service.registrarVisitante(visitanteDTO);

		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}

	}
	
	public void testConsultarVisitante(){
		VisitanteDTO visitanteDTO = new VisitanteDTO();
		visitanteDTO.setcIP("127.0.0.1");
		System.out.println("La IP es:"+ visitanteDTO.getcIP() );
		try{
			visitanteDTO=service.consultarVisitantePorIP(visitanteDTO);
			if (visitanteDTO!=null)
			System.out.println("No visitas:"+ visitanteDTO.getiIntentos() );
			else
				System.out.println("Registro no encontrado" );
			
		}catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}
	}
	
	public void testEliminarVisitante(){
		VisitanteDTO visitanteDTO = new VisitanteDTO();
		visitanteDTO.setcIP("0.0.0.2");
		try{
			if (service.eliminarVisitantePorIP(visitanteDTO))
				System.out.println("Registro Elminado" );
			else
				System.out.println("Registro no encontrado" );
			
		}catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}
	}
	
}
