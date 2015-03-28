/**
* Nombre del Programa : ConsultarFuncionServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 1 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Pruebas unitaria de los metodos para consultar las funciones
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.test.usuario.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.service.usuario.ConsultarFuncionService;

/**
 * Pruebas unitaria de los metodos para consultar las funciones.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class ConsultarFuncionServiceImplTest extends
		BaseTestServicios<ConsultarFuncionService> {

	public void testConsultarFunciones() {
		try {
			List<FuncionDTO> respuesta = service.consultarFunciones();
			
			assertTrue("La lista debe tener minimo un registro : ", respuesta.size()>0);
			logger.info("La lista debe tener minimo un registro : "+respuesta.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testConsultarFuncionesByRol() {
		RolDTO rolDTO = new RolDTO();
		rolDTO.setRolId(1L);
		
		try {
			List<FuncionDTO> respuesta = service.consultarFuncionesByRol(rolDTO);
			
			assertTrue("La lista debe tener minimo un registro : ", respuesta.size()>0);
			logger.info("La lista debe tener minimo un registro : "+respuesta.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testConsultarFuncionXNombre(){
		FuncionDTO fncDTO= new FuncionDTO ("1");
		try{
			fncDTO= service.consultarFuncionXNombre(fncDTO);
			if(fncDTO!=null){
				logger.info("Función Encontrada no ID: " + fncDTO.getFuncionId());
			}else{
				logger.info("Función NO valida");
				
					
			}
		}catch (NSJPNegocioException e){
			e.printStackTrace();
		}
		
	}
	
}
