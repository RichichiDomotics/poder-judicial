/**
* Nombre del Programa : AsociarArchivoDigitalAMedida.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para asociar un ArchivoDigital a una Medida
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
package mx.gob.segob.nsjp.service.medida;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.MedidaCautelarDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;

/**
 * Contrato del servicio para asociar un ArchivoDigital a una Medida.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface AsociarArchivoDigitalAMedidaService {

	/**
	 * Asocia un archivo digital a una medida.
	 * @author cesarAgustin
	 * @param medidaDTO
	 * 			<li>documentoId<li>
	 * @param archivoDigitalDTO
	 * 			<li>archivoDigitalId<li>
	 * @throws NSJPNegocioException
	 */
	void asociarArchivoDigitalAMedida(ArchivoDigitalDTO archivoDigitalDTO,
			MedidaDTO medidaDTO) throws NSJPNegocioException;

	/**
	 * Asocia un documento con archivo digital a una medida
	 * @author cesarAgustin
	 * @param documentoDTO
	 * @param medidaCautelarDTO
	 * @throws NSJPNegocioException
	 */
	void asociarDocumentoConMedidaCautelar(DocumentoDTO documentoDTO,
			MedidaCautelarDTO medidaCautelarDTO) throws NSJPNegocioException;

}
