/**
 * Nombre del Programa : ConsultarAudienciasByFechasYEstatusServiceImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 9 Aug 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion la consulta de audiencias de acuerdo a un estatus y/o rango de fechas
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

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDefensoriaWSDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.service.audiencia.ConsultarAudienciasByFechasYEstatusService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.AudienciaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion la consulta de audiencias de acuerdo a un estatus y/o rango de
 * fechas.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Service("consultarAudienciasByFechasYEstatusService")
@Transactional
public class ConsultarAudienciasByFechasYEstatusServiceImpl implements
		ConsultarAudienciasByFechasYEstatusService {

	/**
	 * 
	 */
	public static final Logger logger = Logger
			.getLogger(ConsultarAudienciasByFechasYEstatusServiceImpl.class);

	@Autowired
	private AudienciaDAO audienciaDAO;

	@Override
	public List<AudienciaDefensoriaWSDTO> consultarAudienciasByFechasYEstatus(
			AudienciaDefensoriaWSDTO audienciaWSDTO) throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR AUDIENCIAS REQUERIDAS POR DEFENSORIA ****/");

		List<Audiencia> audiencias = audienciaDAO
				.buscarAudiencias(null, null, audienciaWSDTO.getFiltroFechaInicio(), audienciaWSDTO.getFiltroFechaFinal(), true, null, false,null,audienciaWSDTO.getIdDistritoFiltroAudiencias());

		List<AudienciaDefensoriaWSDTO> audienciasWS = new ArrayList<AudienciaDefensoriaWSDTO>();
		
		for (Audiencia audiencia : audiencias) {
			audienciasWS.add(AudienciaTransformer.transformarWSDTOBasicoConCaracter(audiencia));
		}
		return audienciasWS;
	}

	@Override
	public AudienciaDefensoriaWSDTO consultarAudienciaById(AudienciaDefensoriaWSDTO audienciaWSDTO)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR UNA AUDIENCIA REQUERIDAS POR DEFENSORIA ****/");
		Audiencia audiencia = audienciaDAO
				.read(audienciaWSDTO.getAudienciaId());
		logger.debug(audiencia.getNumeroExpediente());
		return AudienciaTransformer.transformarWSDTOCompleto(audiencia);
	}

}
