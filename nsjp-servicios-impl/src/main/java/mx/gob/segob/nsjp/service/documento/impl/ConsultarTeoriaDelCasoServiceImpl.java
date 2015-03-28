/**
 * 
 */
package mx.gob.segob.nsjp.service.documento.impl;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.OficioEstructuradoDAO;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.service.documento.ConsultarTeoriaDelCasoService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;

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
public class ConsultarTeoriaDelCasoServiceImpl implements
		ConsultarTeoriaDelCasoService {
	public final static Logger logger = Logger
			.getLogger(ConsultarTeoriaDelCasoServiceImpl.class);
	@Autowired
	private ActividadDAO actividadDAO;
	@Autowired
	private OficioEstructuradoDAO estructuradoDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.service.documento.ConsultarTeoriaDelCasoService#
	 * consultarTeoriasDelCasoXExpediente
	 * (mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public DocumentoDTO consultarTeoriasDelCasoXExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR TEORIAS DEL CASO POR EXPEDIENTE ****/");

		/* Verificación de parámetros */
		if (expedienteDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (expedienteDTO.getExpedienteId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		/* Operación */
		DocumentoDTO teoriaDTO=new DocumentoDTO();
		Documento teo = actividadDAO
				.consultarDocumentosXExpediente(
						expedienteDTO.getExpedienteId(),
						TipoDocumento.TEORIA_DEL_CASO.getValorId());
		if(teo!=null){
			teo.setOficioEstructurado(estructuradoDAO.consultarOficioXDocumento(teo.getDocumentoId()));
			teoriaDTO = DocumentoTransformer.transformarDatosLista(teo);
		}
			

		return teoriaDTO;
	}

}
