/**
 * Nombre del Programa : AsociarDocumentoCausaTOCAServiceImpl.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24/06/2011
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
package mx.gob.segob.nsjp.service.expediente.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.expediente.AsociarDocumentoCausaTOCAService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ActividadTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
@Service
@Transactional
public class AsociarDocumentoCausaTOCAServiceImpl implements
		AsociarDocumentoCausaTOCAService {

	private final static Logger logger = Logger
			.getLogger(AsociarDocumentoCausaTOCAServiceImpl.class);

	@Autowired
	private NumeroExpedienteDAO numExDao;
	@Autowired
	private ActividadDAO actDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.expediente.AsociarDocumentoCausaTOCAService
	 * #asociarDocumentoCausaTOCA(mx.gob.segob.nsjp.dto.documento.DocumentoDTO,
	 * mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public void asociarDocumentoCausaTOCA(DocumentoDTO documentoDTO,
			ExpedienteDTO causa, ActividadDTO actividadDTO)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ASOCIAR UN DOCUMENTO AL EXPEDIENTE ****/");

		/* Verificación de parámetros */
		if (documentoDTO == null || causa == null
				|| causa.getExpedienteId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		/* Operación */
		NumeroExpediente numExp = numExDao.read(causa.getNumeroExpedienteId());
		Documento documento = new Documento();
		documento.setDocumentoId(documentoDTO.getDocumentoId());

		// Actividad actividad= new Actividad();
		Actividad actividad = ActividadTransformer
				.transformarActividadDTO(actividadDTO);
		
		actividad.setExpediente(new Expediente(numExp.getExpediente()
				.getExpedienteId()));
		actividad.setDocumento(documento);
		
		if (actividad.getActividadId() == null)
			actDao.create(actividad);
		else
			actDao.update(actividad);
	}

}
