/**
* Nombre del Programa : AtenderSolicitudPericialServiceImplTest.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25/07/2011
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

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.service.solicitud.AtenderSolicitudPericialService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de pruebas unitarias para el servicio de atención y seguimiento de solicitudes
 * de pericial
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class AtenderSolicitudPericialServiceImplTest extends BaseTestServicios<AtenderSolicitudPericialService> {

	public void testRegistrarDictamenParaSolicitudPericial(){
		DictamenDTO dictamen = new DictamenDTO();
		
		dictamen.setSolicitudPericial(new SolicitudPericialDTO(38L));
		dictamen.setFechaCreacion(new Date());
		dictamen.setFormaDTO(new FormaDTO(Formas.DICTAMEN_PERICIAL.getValorId()));
		
		try {
			dictamen = service.registrarDictamenParaSolicitudPericial(dictamen);
			assertNotNull(dictamen);
			logger.debug(dictamen);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			fail();
		}
		
		
	}
	
}
