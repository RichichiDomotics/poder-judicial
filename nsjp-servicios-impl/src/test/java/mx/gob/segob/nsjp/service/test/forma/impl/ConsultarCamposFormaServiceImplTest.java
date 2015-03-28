/**
* Nombre del Programa : ConsultarCamposFormaServiceImplTest.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para la funcionalidad del servicio de consulta de campos configurables
 * de una forma
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
package mx.gob.segob.nsjp.service.test.forma.impl;

import java.util.List;

import mx.gob.segob.nsjp.dto.forma.CamposFormaDTO;
import mx.gob.segob.nsjp.service.forma.ConsultarCamposFormaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para la funcionalidad del servicio de consulta de campos configurables
 * de una forma
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class ConsultarCamposFormaServiceImplTest extends BaseTestServicios<ConsultarCamposFormaService> {
	/**
	 * Prueba de consulta general
	 */
	public void testConsultarCampos(){
		List <CamposFormaDTO> campos = service.consultarCamposForma();
		assertNotNull("La lista no puede ser nula",campos);
	}
	
}
