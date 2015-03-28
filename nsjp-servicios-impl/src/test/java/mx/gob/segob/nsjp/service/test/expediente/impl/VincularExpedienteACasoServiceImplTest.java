/**
* Nombre del Programa : VincularExpedienteACasoServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 6 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para pruebas unitarias de los metodos del servicio vincular expediente a caso
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
package mx.gob.segob.nsjp.service.test.expediente.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.expediente.VincularExpedienteACasoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase para pruebas unitarias de los metodos del servicio vincular expediente a caso.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class VincularExpedienteACasoServiceImplTest extends
		BaseTestServicios<VincularExpedienteACasoService> {

	public void testVincularExpedienteACaso () {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		CasoDTO casoDTO = new CasoDTO();		
		
		expedienteDTO.setExpedienteId(new Long(183));
		casoDTO.setCasoId(new Long(3));
		
		try {
			service.vincularExpedienteACaso(expedienteDTO, casoDTO, null);	
			fail("Deberia haber lanzado una excepcion");			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
}
