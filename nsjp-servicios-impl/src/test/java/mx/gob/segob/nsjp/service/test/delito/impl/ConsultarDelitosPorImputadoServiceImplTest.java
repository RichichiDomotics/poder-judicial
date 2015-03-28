/**
* Nombre del Programa : ConsultarDelitoServiceImplTest.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Pruebas unitarias para los metodos de consulta de los delitos
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.delito.ConsultarDelitosPorImputadoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Pruebas unitarias para los metodos de consulta de los delitos.
 * @version 1.0
 * @author rgama
 *
 */
public class ConsultarDelitosPorImputadoServiceImplTest extends BaseTestServicios<ConsultarDelitosPorImputadoService> {

	public void testConsultarDelitoExpediente () {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO(133L);
		Long imputado=8L;
		
		
		try {  
			List<DelitoDTO> respuesta = service.consultarDelitosPorImputado(imputado,expedienteDTO.getExpedienteId());
			assertNotNull("La lista no puede ser nula ", respuesta);
			for (DelitoDTO delitoDTO : respuesta) {
				logger.info("/**** Delito id: " + delitoDTO.getDelitoId() + 
//						"\n Nombre delito: " + delitoDTO.getNombreDelito()+
						"\n Es probable: "+delitoDTO.getEsProbable()+
						"\n Es principal: "+delitoDTO.getEsPrincipal());
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}				
	}
}
