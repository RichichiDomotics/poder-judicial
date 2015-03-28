/**
 * Nombre del Programa : DocumentoDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de Acceso a Datos para el documento.
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
package mx.gob.segob.nsjp.dao.documento;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.TipoForma;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Usuario;

/**
 * Objeto de Acceso a Datos para el documento.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface DocumentoDAO extends GenericDao<Documento, Long> {

    /**
     * Obtiene los ducumentos de un expediente que ya cuentan con un archivo
     * digital generado
     * 
     * @param expedienteId
     * @return lista de documentos recuperados
     */
    public List<Documento> consultarDocumentoPorExpediente(Long expedienteId);

    /**
     * Operación que realiza la funcionalidad de consultar los documentos
     * asociados a un expediente y al tipo de documento
     * 
     * @param expedienteDTO
     *            : El número de expediente
     * @param tipoDocumento
     *            : El tipo de documento a consultar.
     * @return Regresa un listado de objetos de tipo Documento, en caso
     *         contrario regresa null.
     */
    public List<Documento> consultarDocumentosXExpedienteYTipoDocumento(
            Long numeroExpedienteId, Long tipoDocumento);

    /**
     * Consulta los Documentos que estén asociados a un Usuario
     * 
     * @param usuario
     *            El usuario del que se consultan sus documentos
     * @param tipoDocumento
     *            El tipo de documento por el que se filtraran los documentos
     *            para la consultar. (Dictamen / Informe) asociados.
     * @return El listado de documentos asociados al Usuario. Si el usuario no
     *         existe en la base de datos o si no tiene documentos asociados, se
     *         regresa la lista vacia.
     */
    List<Documento> consultarDocumentosPorUsuario(Usuario usuario,
            Long tipoDocumento);

    /**
     * Consulta un documento del tipo {@code tipoDocumento} asociado al numero
     * de expediente indicado por {@code expediente.numeroExpediente}.
     * 
     * @param expedienteDto
     *            Indica el numero de expediente del que buscamos su documento
     *            asociado.
     * @param tipoDocumento
     *            El tipo del documento que estamos buscando.
     * @return El documento asociado a los parametros de busqueda o {@code null}
     *         en caso de no existir un documento asociado del tipo indicado.
     */
    Documento consultarDocumentoXExpediente(Expediente expediente,
            Long tipoDocumento);
    /**
     * Obtiene los ducumentos de un expediente que ya cuentan con un archivo
     * digital generado
     * 
     * @param numeroExpedienteId
     *            ID del numero de expediente para el cual se desean consultar
     *            los documentos
     * @return lista de documentos recuperados
     */
    List<Documento> consultarDocumentosPorNumeroExpedienteId(
            Long numeroExpedienteId);

    /**
     * Consulta los documentos que tienen unba forma del tipo de forma enviado,
     * relacionados a una Audiencia.
     * 
     * @author cesarAgustin
     * @param id
     * @param valorId
     * @return
     */
    public List<Documento> consultarDocumentosAudienciaByTipoForma(Long id,
            Long valorId);

    /**
     * Obtiene los ducumentos de un expediente que ya cuentan con un archivo
     * digital generado
     * 
     * @param documentoId
     * @return Un documento:<br>
     *         <code>Documento(d.documentoId, d.nombreDocumento, d.forma.formaId, d.tipoDocumento.valorId, d.tipoDocumento.valor, d.fechaCreacion</code>
     */
    public Documento consultarDocumentoPorDocumentoIdLigero(Long documentoId);

    public Documento consultarDocumentoPorId(Long documentoId);

	public List<Documento> consultarDocumentosXTipoDocumento(Long idTipoDocumento);

	/**
	 * Obtiene el último folio asignado a un documento.
	 * @return
	 * @throws NSJPNegocioException 
	 */
	public String obtenerUltimoFolioDocumento() throws NSJPNegocioException;
	/**
	 * 
	 * @param expedienteId
	 * @param tipoforma
	 * @return
	 */
	public List<Documento> consultarDocumentosByExpedienteIdYForma(Long expedienteId, Formas tipoforma);
	/**
	 * @param archivoDigitalId
	 * 
	 * @return la lista de documentos que pertenece a este Id
	 */	
    public Documento consultarDocumentoPorArchivoDigital(Long archivoDigitalId);

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
	List<Documento> consultarDocumentosReinsercionSocial(Funcionario funcionario, 
    		Documento documento,
    		NumeroExpediente numeroExpediente)  throws NSJPNegocioException;

}
