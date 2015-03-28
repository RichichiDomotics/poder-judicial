/**
 * Nombre del Programa : MarcarAudienciaResolutivosServiceImpl.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 23/06/2011
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
package mx.gob.segob.nsjp.service.audiencia.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.service.audiencia.MarcarAudienciaResolutivosService;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
@Service
@Transactional
public class MarcarAudienciaResolutivosServiceImpl implements
		MarcarAudienciaResolutivosService {

	private final static Logger logger = Logger
			.getLogger(ConsultarAudienciaServiceImpl.class);

	@Autowired
	private AudienciaDAO audDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.audiencia.MarcarAudienciaResolutivosService
	 * #marcarAudienciaResolutivos(java.lang.Long)
	 */
	@Override
	public void marcarAudienciaResolutivos(Long idAudiencia)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR RESOLUTIVOS DE UNA AUDIENCIA ****/");

		/* Verificación de parámetros */
		if (idAudiencia == null || idAudiencia < 0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		/* Operación */
		Audiencia aud = audDao.read(idAudiencia);
		aud.setConResolutivos(true);
		
		audDao.update(aud);
	}

}
