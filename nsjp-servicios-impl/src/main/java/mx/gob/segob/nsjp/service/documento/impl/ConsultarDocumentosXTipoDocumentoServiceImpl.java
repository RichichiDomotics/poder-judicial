/**
 * Nombre del Programa : ConsultarDocumentosXTipoDocumentoServiceImpl.java
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
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentosXTipoDocumentoService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.forma.impl.ConsultarFormaPlantillaServiceImpl;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
@Service
@Transactional
public class ConsultarDocumentosXTipoDocumentoServiceImpl implements
		ConsultarDocumentosXTipoDocumentoService {

	public final static Logger logger = Logger
			.getLogger(ConsultarFormaPlantillaServiceImpl.class);

	@Autowired
	private DocumentoDAO docDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.documento.ConsultarDocumentosXTipoDocumentoService
	 * #consultarDocumentosXTipoDocumento(mx.gob.segob.nsjp.dto.expediente.
	 * ExpedienteDTO, java.lang.Long)
	 */
	@Override
	public List<DocumentoDTO> consultarDocumentosXTipoDocumento(
			ExpedienteDTO expedienteDTO, Long tipoDocumento)
			throws NSJPNegocioException {
		
		/* Revisar parámetros */
		if(expedienteDTO==null||tipoDocumento==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(expedienteDTO.getNumeroExpedienteId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		/* Operación */
		List<Documento> documentos = docDao.consultarDocumentosXExpedienteYTipoDocumento(expedienteDTO.getNumeroExpedienteId(),tipoDocumento);

		/* Transformación */
		List<DocumentoDTO> docsDTO = new ArrayList<DocumentoDTO>();
		for (Documento doc : documentos) {
			docsDTO.add(DocumentoTransformer.transformarDatosLista(doc));
//			docsDTO.add(DocumentoTransformer.transformarDocumento(doc));// Tiene loops
		}
		return docsDTO;
	}

}
