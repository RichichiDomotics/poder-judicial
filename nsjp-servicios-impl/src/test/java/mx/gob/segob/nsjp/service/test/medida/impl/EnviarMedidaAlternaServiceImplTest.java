/**
* Nombre del Programa : EnviarMedidaAlternaServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01/11/2011
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
package mx.gob.segob.nsjp.service.test.medida.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.medidasalternas.EnviarMedidaAlternaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class EnviarMedidaAlternaServiceImplTest   extends BaseTestServicios<EnviarMedidaAlternaService> {
	
	public void testEnviarMedidaAlternaASSP() throws NSJPNegocioException{
		Long idMedida = 5L;
		service.enviarMedidaAlternaASSP(idMedida);
	}


}
