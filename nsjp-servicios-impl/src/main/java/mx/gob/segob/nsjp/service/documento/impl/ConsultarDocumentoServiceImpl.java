/**
* Nombre del Programa : ConsultarDocumentoServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar las consultas de Documento
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
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.TipoForma;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.AudienciaDocumentoDAO;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dao.documento.NotaDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.NotaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Nota;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentoService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.documento.impl.tranform.NotaTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para realizar las consultas de Documento.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ConsultarDocumentoServiceImpl implements ConsultarDocumentoService {

	/**
	 * 
	 */
	public final static Logger LOGGER = Logger.getLogger(ConsultarDocumentoServiceImpl.class); 

	@Autowired
	private DocumentoDAO documentoDAO;
	@Autowired
	private NotaDAO notaDAO;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private AudienciaDocumentoDAO audienciaDocumentoDAO; 
	
	@Override
	public List<DocumentoDTO> consultarDocumentosExpediente(ExpedienteDTO expedienteDTO, UsuarioDTO usuarioDTO)
			throws NSJPNegocioException {

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("/**** SERVICIO PARA CONSULTAR DOCUMENTOS DE UN EXPEDIENTE ****/");
		
		if (expedienteDTO==null || usuarioDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Documento> documentosExpediente = documentoDAO.consultarDocumentoPorExpediente(expedienteDTO.getExpedienteId());
		
		List<DocumentoDTO> documentosDTO = new ArrayList<DocumentoDTO>();
		for (Documento documento : documentosExpediente) {
			if (usuarioDTO.getArea().getAreaId().equals(new Long(Areas.JUSTICIA_ALTERNATIVA_RESTAURATIVA.ordinal()))) {
				if (documento.getForma().getFormaId().equals(new Long(1)))
					documentosDTO.add(DocumentoTransformer.transformarDocumento(documento));
			}			
		}
		
		return documentosDTO;
	}

	@Override
	public List<NotaDTO> consultarNotasPorDocumento(Long idDocumento) throws NSJPNegocioException{
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Servicio para consultar las Notas asociadas a un documento.");
		
		if (idDocumento==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<NotaDTO> lNotasDTO = new ArrayList<NotaDTO>();
		
		List<Nota> lNotas = notaDAO.consultarNotasPorDocumento(idDocumento);
		
		for (Nota nota : lNotas) {
			lNotasDTO.add(NotaTransformer.transformarNota(nota));
		}
		return lNotasDTO;
	}
	
	@Override
	public List<DocumentoDTO> consultarDocumentosExpediente(ExpedienteDTO expedienteDTO)
			throws NSJPNegocioException {

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("/**** SERVICIO PARA CONSULTAR DOCUMENTOS DE UN EXPEDIENTE ****/");
		
		if (expedienteDTO.getNumeroExpedienteId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);		
		
    	NumeroExpediente loNumExp = numeroExpedienteDAO.read(expedienteDTO.getNumeroExpedienteId());    	
		expedienteDTO.setExpedienteId(loNumExp.getExpediente().getExpedienteId());
		List<Documento> documentosExpediente = documentoDAO.consultarDocumentoPorExpediente(expedienteDTO.getExpedienteId());
		
		List<DocumentoDTO> documentosDTO = new ArrayList<DocumentoDTO>();
		for (Documento documento : documentosExpediente) {
				documentosDTO.add(DocumentoTransformer.transformarDocumento(documento));
		}		
		return documentosDTO;
	}

	@Override
	public List<DocumentoDTO> consultarDocumentosAudienciaByTipoForma(
			AudienciaDTO audienciaDTO, TipoForma tipoForma) throws NSJPNegocioException {
		List<Documento> documentos = documentoDAO.consultarDocumentosAudienciaByTipoForma(audienciaDTO.getId(), tipoForma.getValorId());
		List<DocumentoDTO> documentosDTO = new ArrayList<DocumentoDTO>();
		for (Documento documento : documentos) {
			documentosDTO.add(DocumentoTransformer.transformarDocumento(documento));
		}
		
		return documentosDTO;
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.ConsultarDocumentoService#consultarDetalleNotaPorId(java.lang.Long)
	 */
	@Override
	public NotaDTO consultarDetalleNotaPorId(Long notaId)
			throws NSJPNegocioException {
		return NotaTransformer.transformarNota(notaDAO.read(notaId));
	}


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
	public List<DocumentoDTO> consultarDocumentosReinsercionSocial(FuncionarioDTO funcionarioDTO, DocumentoDTO documentoDTO) throws NSJPNegocioException {
		Funcionario funcionario = FuncionarioTransformer.transformarFuncionario(funcionarioDTO);
		Documento documento = DocumentoTransformer.transformarDocumentoDTO(documentoDTO);
		ExpedienteDTO expedienteDTO = documentoDTO.getExpedienteDTO();
		NumeroExpediente numeroExpediente = new NumeroExpediente(
				expedienteDTO.getNumeroExpedienteId(),
				expedienteDTO.getNumeroExpediente(),
				null);
		
		List<Documento> lstDocumentos = documentoDAO.consultarDocumentosReinsercionSocial(funcionario, documento, numeroExpediente);
		List<DocumentoDTO>  lstDocumentosDTO = new ArrayList<DocumentoDTO>();
		for (Documento tmp : lstDocumentos){
			DocumentoDTO tmpDTO = DocumentoTransformer.transformarDocumento(tmp);
			lstDocumentosDTO.add(tmpDTO);
		}
		
		return lstDocumentosDTO;
	}

	@Override
	public List<DocumentoDTO> consultarDocumentosAudiencia(
			AudienciaDTO audienciaDTO) throws NSJPNegocioException {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("/**** SERVICIO PARA CONSULTAR DOCUMENTOS DE UN EXPEDIENTE ****/");
		
		if (audienciaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);		
		if (audienciaDTO.getId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		List<Documento> lstDocumentos = audienciaDocumentoDAO.consultarDocumentosAudiencia(audienciaDTO.getId());
		List<DocumentoDTO>  lstDocumentosDTO = new ArrayList<DocumentoDTO>();
		for (Documento tmp : lstDocumentos){
			DocumentoDTO tmpDTO = DocumentoTransformer.transformarDocumento(tmp);
			lstDocumentosDTO.add(tmpDTO);
		}
		
		return lstDocumentosDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.ConsultarDocumentoService#consultarDocumentoXId(java.lang.Long)
	 */
	@Override
	public DocumentoDTO consultarDocumentoXId(Long idDocumento) {
		Documento d = documentoDAO.read(idDocumento);
		return DocumentoTransformer.transformarDocumento(d);
	}
}
