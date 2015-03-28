/**
* Nombre del Programa : IngresarTiempoServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para ingresar tiempo
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
package mx.gob.segob.nsjp.service.test.hecho.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.hecho.TiempoDTO;
import mx.gob.segob.nsjp.service.hecho.IngresarTiempoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para ingresar tiempo.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class IngresarTiempoServiceImplTest extends BaseTestServicios<IngresarTiempoService> {

	public void testIngresarTiempo () {
		TiempoDTO tiempoDTO = new TiempoDTO();
		
		tiempoDTO.setDescripcion("Pru Uni");
		tiempoDTO.setFechaFin(new Date());
		tiempoDTO.setFechaInicio(new Date());
		tiempoDTO.setTipoRegistro(new ValorDTO(70L));
		
		try {
			TiempoDTO respuesta = service.ingresarTiempo(tiempoDTO);
			assertNotNull(respuesta);
			assertTrue("El identificador del tiempo debe ser mayor a cero", respuesta.getTiempoId()>0);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
}
