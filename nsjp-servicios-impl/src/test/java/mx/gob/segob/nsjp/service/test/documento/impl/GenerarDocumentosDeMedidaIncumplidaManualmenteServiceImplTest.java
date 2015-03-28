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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.service.documento.GenerarDocumentoDeMedidaIncumplidaManualmenteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de consulta de documentos de medidas incumplidas
 * @version 1.0
 * @author rgama
 *
 */
public class GenerarDocumentosDeMedidaIncumplidaManualmenteServiceImplTest extends BaseTestServicios<GenerarDocumentoDeMedidaIncumplidaManualmenteService> {

	public void testGenerarDocumentosDeMedidasIncumplidas() {
		try {
			DocumentoDTO respuesta = service.generarDocumentoDeMedidaIncumplida(111L);
			System.out.println("Se envio el documento con ID " + respuesta.getDocumentoId());

		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
}

