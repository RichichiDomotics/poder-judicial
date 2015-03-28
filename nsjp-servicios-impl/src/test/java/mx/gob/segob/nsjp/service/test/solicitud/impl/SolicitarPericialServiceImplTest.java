/**
* Nombre del Programa : SolicitarPericialServiceImplTest.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/08/2011
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
package mx.gob.segob.nsjp.service.test.solicitud.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.solicitud.SolicitarPericialService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de pruebas unitarias para el servicio de solicitud de periciales
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class SolicitarPericialServiceImplTest  extends
BaseTestServicios<SolicitarPericialService>{
	
	public void testFinalizarSolicitudPericial(){		
		service.finalizarSolicitudPericial(280L);		
	}
	
	public void testConsultarPadre() throws NSJPNegocioException{
		Long doc=516L;
		Long resultado=service.consultarPadreSolicitudPericial(doc);
		logger.debug("EL PADRE ES:  "  + resultado);		
	}

	/**
	 * Prueba unitaria para la asignación de una solicitud a un funcionario
	 * @author Emigdio Hernández
	 */
	public void testAsignarSolicitudAFuncionario(){
		
		try {
			service.asignarFuncionarioASolicitud(35L, 54L);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			fail();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}

}
