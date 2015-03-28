/**
* Nombre del Programa : RegistrarDelitoDenunciaServiceImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31 May 2011
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
package mx.gob.segob.nsjp.service.test.delito.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.delito.RegistrarDelitoDenunciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesarAgutin
 *
 */
public class RegistrarDelitoDenunciaServiceImplTest extends
		BaseTestServicios<RegistrarDelitoDenunciaService> {

	public void testRegistrarDelitoDenuncia () {
		List<DelitoDTO> delitosDTO = new ArrayList<DelitoDTO>();
		
		DelitoDTO delitoDTO = new DelitoDTO();
		delitoDTO.setEsPrincipal(false);
		delitoDTO.setEsProbable(true);
//		delitoDTO.setNombreDelito("Homicidio calificado");
		delitoDTO.setExpedienteDTO(new ExpedienteDTO(3L));
		delitosDTO.add(delitoDTO);
		
		DelitoDTO delitoDTO1 = new DelitoDTO();
		delitoDTO1.setEsPrincipal(true);
		delitoDTO1.setEsProbable(true);
//		delitoDTO1.setNombreDelito("Robo casa habitacion");
		delitoDTO1.setExpedienteDTO(new ExpedienteDTO(3L));
		delitosDTO.add(delitoDTO1);		
		
		try {
			List<DelitoDTO> respuesta = service.registrarDelitoDenuncia(delitosDTO);
			assertTrue("La lista de delitos debe traer al menos un regitro", respuesta.size()>0);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
}

