/**
* Nombre del Programa : AsociarArchivoDigitalAMedidaServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio AsociarArchivoDigitalAMedida
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
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.service.medida.AsociarArchivoDigitalAMedidaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio AsociarArchivoDigitalAMedida.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class AsociarArchivoDigitalAMedidaServiceImplTest extends
		BaseTestServicios<AsociarArchivoDigitalAMedidaService> {

	public void testAsociarArchivoDigitalAMedida() {
		MedidaDTO medidaDTO = new MedidaDTO();
		medidaDTO.setDocumentoId(new Long(29));
		
		ArchivoDigitalDTO archivoDigitalDTO = new ArchivoDigitalDTO();
		archivoDigitalDTO.setArchivoDigitalId(new Long(1));
		
		try {
			service.asociarArchivoDigitalAMedida(archivoDigitalDTO, medidaDTO);
		} catch (NSJPNegocioException e) {			
			fail();
		}
	
	}
	
}
