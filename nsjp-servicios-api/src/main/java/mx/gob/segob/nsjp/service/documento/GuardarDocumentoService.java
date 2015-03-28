/**
* Nombre del Programa : GuardarDocumentoService.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/05/2011
* Marca de cambio        : N/A
* Descripcion General    : Define el contrato de negocio del servicio para guardar un documento generado por el sistema
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Define el contrato de negocio del servicio para guardar un documento generado por el sistema.
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface GuardarDocumentoService {
	
	/**
	  * Almacena los cambios de un Documento, en caso de que el documento ya cuente con ID entonces lo 
	  * crea y devuelve el ID generado
	  * @param documento Documento a guardar
	  * @param expedienteActual expediente para el cual debe ser relacionado el documento
	  * @return ID del documento creado/actualizado
	  */
	Long guardarDocumento(DocumentoDTO documento,ExpedienteDTO expedienteActual) throws NSJPNegocioException;

	Long guardarDocumentoTranscripcion(DocumentoDTO documentoDTO, Long idSolTrans) throws NSJPNegocioException;

	Long guardarActaAudiencia(DocumentoDTO documento, ExpedienteDTO expTrabajo) throws NSJPNegocioException;
	
	/**
	 * Operación que realiza el guardado del oficio estructurado previo al documento
	 * @param documentoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarTeoriaDeCaso(DocumentoDTO documentoDTO)throws NSJPNegocioException;
	
	/**
	 * Operación que realiza el guardado del oficio estructurado previo al documento
	 * @param documentoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarPliegoConsignacion(DocumentoDTO documentoDTO)throws NSJPNegocioException;
	
	/**
	 * 
	 * @param documentoDTO
	 * @param expedienteDTO
	 * @return
	 */
    Long guardarDocumentoIntraInstitucion(DocumentoDTO documentoDTO,
            ExpedienteDTO expedienteDTO);
}
