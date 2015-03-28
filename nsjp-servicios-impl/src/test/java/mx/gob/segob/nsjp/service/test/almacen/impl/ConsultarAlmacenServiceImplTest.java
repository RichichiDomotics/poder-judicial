/**
* Nombre del Programa : ConsultarAlmacenServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio de consulta de almacenes
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
package mx.gob.segob.nsjp.service.test.almacen.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.almacen.ConsultarAlmacenService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de consulta de almacenes.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class ConsultarAlmacenServiceImplTest extends
		BaseTestServicios<ConsultarAlmacenService> {

	public void testoObtenerAlmacenDelExpediente () {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(1L);
		
		try {
			AlmacenDTO respuesta  = service.obtenerAlmacenDelExpediente(expedienteDTO);
			
			assertNotNull("El almacen no puede ser null ", respuesta);
			logger.info("Almacen de :: "+respuesta);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}		
	}
	
	public void testConsultarAlmacenesExpedientePorEstatus () {
		try {
			List<AlmacenDTO> respuesta = service.consultarAlmacenesExpedientePorEstatus(true, false);
			assertFalse("La lista no puede estar vacia ",respuesta.isEmpty());
			logger.info("La lista no puede estar vacia "+respuesta.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testConsultarDetalleAlmacenPorId () {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(1L);
		
		try {
			AlmacenDTO almacen  = service.consultarDetalleAlmacenPorId(1L);
			
			assertNotNull("El almacen no puede ser null ", almacen);
			logger.info("Almacen de :: "+almacen);
			logger.info("Dirección de :: "+almacen.getDomicilio());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}		
	}
}
