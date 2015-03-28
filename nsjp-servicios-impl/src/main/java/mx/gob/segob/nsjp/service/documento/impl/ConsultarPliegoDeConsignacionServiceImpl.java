/**
 * 
 */
package mx.gob.segob.nsjp.service.documento.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.OficioEstructuradoDAO;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.service.documento.ConsultarPliegoDeConsignacionService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;

/**
 * @author AlejandroGA
 *
 */
@Service
@Transactional
public class ConsultarPliegoDeConsignacionServiceImpl implements ConsultarPliegoDeConsignacionService {
	
	public final static Logger logger = Logger
			.getLogger(ConsultarPliegoDeConsignacionServiceImpl.class);
	
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
	public DocumentoDTO consultarPliegoDeConsignacionXExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR PLIEGO DE CONSIGNACION ****/");

		/* Verificación de parámetros */
		if (expedienteDTO == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if (expedienteDTO.getExpedienteId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		/* Operación */
		DocumentoDTO pliegoDTO = new DocumentoDTO();
		Documento pliego = actividadDAO.consultarDocumentosXExpediente(
				expedienteDTO.getExpedienteId(),
				TipoDocumento.PLIEGO_DE_CONSIGNACION.getValorId());
		if (pliego != null) {
			pliego.setOficioEstructurado(estructuradoDAO
					.consultarOficioXDocumento(pliego.getDocumentoId()));
			pliegoDTO = DocumentoTransformer.transformarDatosLista(pliego);
		}

		return pliegoDTO;
	}
}


