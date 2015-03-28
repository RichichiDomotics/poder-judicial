/**
 * 
 */
package mx.gob.segob.nsjp.service.evidencia.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.eslabon.EslabonDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.evidencia.ConsultarDocumentosDEvidenciaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adrian
 *
 */
@Service
@Transactional
public class ConsultarDocumentosDEvidenciaServiceImpl implements
		ConsultarDocumentosDEvidenciaService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarDocumentosDEvidenciaServiceImpl.class);
	
	@Autowired
	private EslabonDAO eslabonDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.evidencia.ConsultarDocumentosDEvidenciaService#consultarDocumentosXEslabonesDEvidencia(mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO)
	 */
	@Override
	public List<DocumentoDTO> consultarDocumentosXEslabonesDEvidencia(
			EvidenciaDTO evidenciaDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LOS DOCUMENTOS DE UNA EVIDENCIA EN SUS DISTINTOS ESLABONES ****/");
		
		if(evidenciaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(evidenciaDTO.getEvidenciaId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		List<Documento> documentos =eslabonDAO.consultarDocumentosXEslabonesDEvidencia(evidenciaDTO.getEvidenciaId());
		List<DocumentoDTO> docsDTO=new ArrayList<DocumentoDTO>();
		for (Documento documento : documentos) {
			docsDTO.add(DocumentoTransformer.transformarDatosLista(documento));
		}
		
		return docsDTO;
	}

}
