/**
* Nombre del Programa : GenerarDocumentosDeMedidasIncumplidasServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 4 Nov 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio de consulta de documentos de medidas incumplidas
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
package mx.gob.segob.nsjp.service.test.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.service.documento.GenerarDocumentosDeMedidasIncumplidasService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de consulta de documentos de medidas incumplidas
 * @version 1.0
 * @author rgama
 *
 */
public class GenerarDocumentosDeMedidasIncumplidasServiceImplTest extends BaseTestServicios<GenerarDocumentosDeMedidasIncumplidasService> {

	public void testGenerarDocumentosDeMedidasIncumplidas() {
		try {
			List<DocumentoDTO> respuesta = service.generarDocumentosDeMedidasIncumplidas();
			assertTrue("Lalista debe tener minimo un registro", respuesta.size()>0);
			for (DocumentoDTO documentoDTO : respuesta) {
				System.out.println("Se genero el documento con ID " + documentoDTO.getDocumentoId());
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
}

