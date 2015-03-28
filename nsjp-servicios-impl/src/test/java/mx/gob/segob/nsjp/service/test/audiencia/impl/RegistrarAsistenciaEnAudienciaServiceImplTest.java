/**
* Nombre del Programa : RegistrarAsistenciaEnAudienciaServiceImplTest.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 09/09/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.test.audiencia.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioAudienciaDTO;
import mx.gob.segob.nsjp.service.audiencia.RegistrarAsistenciaEnAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;


/**
 * Clase de pruebas unitarias para el servicio de actualización del indicador 
 * de asistencia de una persona a una audiencia
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class RegistrarAsistenciaEnAudienciaServiceImplTest extends BaseTestServicios<RegistrarAsistenciaEnAudienciaService>{
	
	/**
	 * Prueba unitaria para actualizar el indicador de asistencia de un involucrado
	 * @author Emigdio Hernández
	 */
	public void testActualizarAsistenciaInvolucrado(){
		
		try {
			service.registrarAsistenciaInvolucrado(1L, 6L, true);
		} catch (NSJPNegocioException e) {
			
			e.printStackTrace();
			fail();
		}
		
	}
	
	/**
	 * Prueba unitaria para actualizar el indicador de asistencia de un funcionario
	 * @author Emigdio Hernández
	 */
	public void testActualizarAsistenciaFuncionario(){
		
		try {
			service.registrarAsistenciaFuncionario(10L, 6L, true);
		} catch (NSJPNegocioException e) {
			
			e.printStackTrace();
			fail();
		}
		
	}
	
	/**
	 * Prueba unitaria para consultar indicadores de Titular y Presente de un funcionario
	 * @author Eduardo Alvarado
	 */
	public void testConsultarIndicadorPresenteInvolucrado(){
		
		try {
			Long claveFuncionario = 16L;
			Long audienciaId = 46L;
			FuncionarioAudienciaDTO resp = service.consultarAsistenciaFuncionario(claveFuncionario, audienciaId);
			assertNotNull(resp);
			if(resp.getEsTitular() != null){
				logger.info("FuncionarioAudienciaDTO :esTitular : " + resp.getEsTitular());
			}else{
				logger.info("FuncionarioAudienciaDTO :esTitular : nulo");
			}
            if(resp.getEsPresente() != null){
            	logger.info("FuncionarioAudienciaDTO :esPresente: " + resp.getEsPresente());
            }else{
            	logger.info("FuncionarioAudienciaDTO :esPresente: nulo");
            }
            
		} catch (NSJPNegocioException e) {
			
			e.printStackTrace();
			fail();
		}
		
	}
}
