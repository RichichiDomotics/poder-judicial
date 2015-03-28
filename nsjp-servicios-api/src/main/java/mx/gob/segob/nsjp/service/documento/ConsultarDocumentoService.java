/**
* Nombre del Programa : ConsultarDocumentoService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar las consultas de Documento
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

import mx.gob.segob.nsjp.comun.enums.documento.TipoForma;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.NotaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Contrato del servicio para realizar las consultas de Documento.
 * @version 1.0
 * @author cesarAgutin
 *
 */
public interface ConsultarDocumentoService {

	/**
	 * Consulta los documentos y obtiene los documentos que estan asociados a un expediente 
	 * y al area del usuario.
	 * @param expedienteDTO
	 * @param usuarioDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<DocumentoDTO> consultarDocumentosExpediente (ExpedienteDTO expedienteDTO, UsuarioDTO usuarioDTO) throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de consultar las notas asociadas a un documento.
	 * 
	 * @param idDocumento   Recibe el identificador del documento.
	 * @return  Devuelve un listado de notas asociadas al documento
	 * @throws NSJPNegocioException
	 */
	List<NotaDTO> consultarNotasPorDocumento(Long idDocumento) throws NSJPNegocioException;
	

	/**
		 * Consulta los documentos y obtiene los documentos que estan asociados a un expediente
		 * @param expedienteDTO
		 * @param usuarioDTO
		 * @return Lista de documentos con los siguientes valores
		 * - id Documento
		 * - Tipo documento
		 * - Nombre documento
		 * - Fecha creación
		 * @throws NSJPNegocioException
		 */
		List<DocumentoDTO> consultarDocumentosExpediente(ExpedienteDTO expedienteDTO)
		throws NSJPNegocioException;

	/**
	* Consulta los documentos que tienen unba forma del tipo de forma enviado, 
	* relacionados a una Audiencia. 
	* @author cesarAgustin
	* @param audienciaDTO
	* 		-Identificador de la Audiencia <li>id<li>
	* @return Lista de documentos relacionados a la Audiencia
	* @throws NSJPNegocioException
	*/	
	List<DocumentoDTO> consultarDocumentosAudienciaByTipoForma(
			AudienciaDTO audienciaDTO, TipoForma tipoForma) throws NSJPNegocioException;
	
	/**
	 * Consulta el detalle de una nota en base a su ID
	 * @param notaId
	 * @return
	 * @throws NSJPNegocioException
	 */
	NotaDTO consultarDetalleNotaPorId(Long notaId) throws NSJPNegocioException;
	
    /**
     * Consulta los Documentos que estén asociados a un expediente y  Usuario de Reinsercion Social
     * 
     * @param usuario
     *            El usuario del que se consultan sus documentos
     * @param documento
     *            Los datos del documento solicitado, por default el NumeroExpediente_id.
     * @return El listado de documentos asociados al Usuario. Si el usuario no
     *         existe en la base de datos o si no tiene documentos asociados, se
     *         regresa la lista vacia.
     */    
	List<DocumentoDTO> consultarDocumentosReinsercionSocial(FuncionarioDTO funcionarioDTO, DocumentoDTO documentoDTO) throws NSJPNegocioException;	
	
	/**
	* Consulta los documentos relacionados a una Audiencia. 
	* @author AlejandroGA
	* @param audienciaDTO
	* 		-Identificador de la Audiencia <li>id<li>
	* @return Lista de documentos relacionados a la Audiencia
	* @throws NSJPNegocioException
	*/	
	List<DocumentoDTO> consultarDocumentosAudiencia(
			AudienciaDTO audienciaDTO) throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo que lleva a cabo la consulta de un documento en base a su Identificador.
	 * @param idDocumento - Identificador del documento a consultar.
	 * @return DocumentoDTO - DTO con la informaci&oacute;n asociada al documento en BD.
	 */
	DocumentoDTO consultarDocumentoXId(Long idDocumento);
}
