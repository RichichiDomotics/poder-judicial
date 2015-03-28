package mx.gob.segob.nsjp.service.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.ResolutivoDAO;
import mx.gob.segob.nsjp.dao.documento.AudienciaDocumentoDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.model.AudienciaDocumento;
import mx.gob.segob.nsjp.model.AudienciaDocumentoId;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Resolutivo;
import mx.gob.segob.nsjp.service.documento.AsociarDocumentoAService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
@Transactional
public class AsociarDocumentoAServiceImpl implements AsociarDocumentoAService {

	public final static Logger LOGGER = Logger.getLogger(ConsultarDocumentoServiceImpl.class); 
	
	@Autowired
	ResolutivoDAO resolutivoDAO;
	@Autowired
	AudienciaDocumentoDAO audienciaDocumentoDAO; 
	
	
	public void asociarDocuementoA(ResolutivoDTO resolutivo,
			DocumentoDTO documento) throws NSJPNegocioException {

		Resolutivo resolv = resolutivoDAO.read(resolutivo.getResolutivoId());
//		resolv.setDocumento(DocumentoTransformer.transformarDocumentoDTO(documento)); FIXME
		resolutivoDAO.saveOrUpdate(resolv);
		
	}

	@Override
	public DocumentoDTO asociarDocumentoAAudiencia(AudienciaDTO audienciaDTO,
			DocumentoDTO documentoDTO) throws NSJPNegocioException {
		
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("/**** SERVICIO PARA CONSULTAR DOCUMENTOS DE UN EXPEDIENTE ****/");
		
		if (audienciaDTO==null || audienciaDTO.getId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if (documentoDTO==null || documentoDTO.getDocumentoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<Documento> lstDocumentos = audienciaDocumentoDAO.consultarDocumentosAudiencia(audienciaDTO.getId());
		
		//Buscamos si el documento ya se encuentra asociado a la audiencia
		for (Documento tmp : lstDocumentos){
			if(tmp.getDocumentoId().equals(documentoDTO.getDocumentoId())){
				throw new NSJPNegocioException(CodigoError.DOCUMENTO_YA_ASOCIADO);
			}
		}

		AudienciaDocumentoId audienciaDocId = new AudienciaDocumentoId();
		
		audienciaDocId.setAudienciaId(audienciaDTO.getId());
		audienciaDocId.setDocumentoId(documentoDTO.getDocumentoId());
		
		AudienciaDocumento audienciaDocumento = new AudienciaDocumento();
		audienciaDocumento.setId(audienciaDocId);
		
		audienciaDocId = audienciaDocumentoDAO.create(audienciaDocumento);
				
		DocumentoDTO docDTO = new DocumentoDTO();
		docDTO.setDocumentoId(audienciaDocId.getDocumentoId());
		
		return docDTO;
	}

}
