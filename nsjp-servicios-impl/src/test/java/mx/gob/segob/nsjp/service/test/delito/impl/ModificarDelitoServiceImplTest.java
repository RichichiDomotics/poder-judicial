/**
* Nombre del Programa : ModificarDelitoServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio de modificacion delito
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
package mx.gob.segob.nsjp.service.test.delito.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.service.delito.ModificarDelitoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de modificacion delito.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class ModificarDelitoServiceImplTest extends
		BaseTestServicios<ModificarDelitoService> {

	public void testModificarDelito () {
		DelitoDTO delitoDTO = new DelitoDTO(0L);
		DelitoDTO delitoDTO1 = new DelitoDTO(1L);
		DelitoDTO delitoDTO2 = new DelitoDTO(2L);
		List<DelitoDTO> delitos = new ArrayList<DelitoDTO>();
		
		delitoDTO.setEsProbable(false);
		delitoDTO.setEsPrincipal(false);
		delitos.add(delitoDTO);
		delitoDTO1.setEsProbable(false);
		delitoDTO1.setEsPrincipal(false);
		delitos.add(delitoDTO1);
		delitoDTO2.setEsProbable(true);
		delitoDTO2.setEsPrincipal(true);
		delitos.add(delitoDTO2);
		
		try {
			service.modificarDelito(delitos);			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
}
