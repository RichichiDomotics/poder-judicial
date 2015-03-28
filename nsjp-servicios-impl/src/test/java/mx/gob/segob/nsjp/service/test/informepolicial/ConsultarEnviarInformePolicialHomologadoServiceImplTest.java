/**
* Nombre del Programa : ConsultarEnviarInformePolicialHomologadoServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/09/2011
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
package mx.gob.segob.nsjp.service.test.informepolicial;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.service.informepolicial.ConsultarEnviarInformePolicialHomologadoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de Pruebas Unitarias para Consultar y enviar el IPH 
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ConsultarEnviarInformePolicialHomologadoServiceImplTest extends BaseTestServicios<ConsultarEnviarInformePolicialHomologadoService> {

	public void testConsultarEnviarInformePolicialHomologado(){
		Long folioIPH = 2011000079L;
		logger.info("Prueba con ConsultarEnviarInformePolicialHomologado");
		try {
			Long idArea = 5L;//Representa la agencia a donde sera enviado el IPH
			InformePolicialHomologadoDTO iphDTO= service.consultarEnviarInformePolicialHomologado(folioIPH, idArea);
			assertNotNull("enviarCarpetaDeInvestigacion", iphDTO);
		} catch (NSJPNegocioException ex) {
			fail("Ocurrio una excepcion al ejecutar el test ConsultarEnviarInformePolicialHomologado");
		}
		
		
	}
	
}
