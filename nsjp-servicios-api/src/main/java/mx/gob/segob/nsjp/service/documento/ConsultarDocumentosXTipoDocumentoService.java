/**
* Nombre del Programa : ConsultarDocumentosXTipoDocumentoService.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 28/06/2011
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
package mx.gob.segob.nsjp.service.documento;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface ConsultarDocumentosXTipoDocumentoService {

	/**
	  * Operación que realiza la funcionalidad de consultar los documentos asociados a un expediente y al tipo de documento
	  * 
	  * @param expedienteDTO: El número de expediente
	  * @param tipoDocumento: El tipo de documento a consultar.
	  * @return Regresa un listado de objetos de tipo Documento, en caso contrario regresa null.
	  * @throws NSJPNegocioException
	  */
	List<DocumentoDTO> consultarDocumentosXTipoDocumento(
			ExpedienteDTO expedienteDTO, Long tipoDocumento)throws NSJPNegocioException;

}
